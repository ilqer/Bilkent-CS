.text

	
	la $a0, numberOfNodesText
	li $v0, 4
	syscall
	
	li $v0, 5
	syscall
	
	
	
	move	$a0, $v0 		#XXX create a linked list with v0 nodes
	move	$s0, $v0		#save length of linked list
	jal	createLinkedList
	
	
# Linked list is pointed by $v0	
	move	$a0, $v0	# Pass the linked list address in $a0
	move	$a1, $s0	#pass length of list
	
	jal	sortLinkedList
	move	$s1, $v0		#save address of linked list
	
	
	jal	printLinkedList
	
	
	move? ? ? ? $a0, $s1	#pass sorted list
	move	$a1, $s0	#pass length of list
	jal	summaryList

	move	$a0, $v1
	move	$s2, $v1	#pass summary head
	
	jal 	printLinkedList
	
	
	
	move	$a0, $s2
	jal	printReverse
	
# Stop. 
	li	$v0, 10
	syscall

createLinkedList:
# $a0: No. of nodes to be created ($a0 >= 1)
# $v0: returns list head
# Node 1 contains 4 in the data field, node i contains the value 4*i in the data field.
# By inserting a data value like this
# when we print linked list we can differentiate the node content from the node sequence no (1, 2, ...).
	addi	$sp, $sp, -24
	sw	$s0, 20($sp)
	sw	$s1, 16($sp)
	sw	$s2, 12($sp)
	sw	$s3, 8($sp)
	sw	$s4, 4($sp)
	sw	$ra, 0($sp) 	# Save $ra just in case we may want to call a subprogram
	
	move	$s0, $a0	# $s0: no. of nodes to be created.
	li	$s1, 1		# $s1: Node counter
# Create the first node: header.
# Each node is 12 bytes: link field then 8 bit data field with key.
	li	$a0, 12
	li	$v0, 9
	syscall
# OK now we have the list head. Save list head pointer 
	move	$s2, $v0	# $s2 points to the first and last node of the linked list.
	move	$s3, $v0	# $s3 now points to the list head.
	
	la $a0, nodeKeyText
	li $v0, 4
	syscall
	
	li $v0, 5
	syscall
	move $s4, $v0
	
	
	#NOOOOOOOOOOOOOOOOOOOOO sll	$s4, $s1, 2	
#NOOOOOOOOOOOOOOOOOOOOOOOOOOO sll: So that node 1 data value will be 4, node i data value will be 4*i

	sw	$s4, 4($s2)	# Store the data value.
	
	la $a0, nodeDataText
	li $v0, 4
	syscall
	
	li $v0, 5
	syscall
	move $s4, $v0
	
	sw	$s4, 8($s2)
	
	
addNode:
# Are we done?
# No. of nodes created compared with the number of nodes to be created.
	beq	$s1, $s0, allDone
	addi	$s1, $s1, 1	# Increment node counter.
	li	$a0, 12 	# Remember: Node size is 12 bytes.
	li	$v0, 9
	syscall
	
# Connect the this node to the lst node pointed by $s2.

	sw	$v0, 0($s2)
	
# Now make $s2 pointing to the newly created node.
	move	$s2, $v0	# $s2 now points to the new node.
	
	la $a0, nodeKeyText
	li $v0, 4
	syscall
	
	li $v0, 5
	syscall
	move $s4, $v0
	
	
	sw	$s4, 4($s2)	# Store the data value.
	
	

	la $a0, nodeDataText
	li $v0, 4
	syscall
	
	li $v0, 5
	syscall
	move $s4, $v0
	
	sw	$s4, 8($s2)
	
	j	addNode
allDone:
# Make sure that the link field of the last node cotains 0.
# The last node is pointed by $s2.
	sw	$zero, 0($s2)
	move	$v0, $s3	# Now $v0 points to the list head ($s3).
	
# Restore the register values
	lw	$ra, 0($sp)
	lw	$s4, 4($sp)
	lw	$s3, 8($sp)
	lw	$s2, 12($sp)
	lw	$s1, 16($sp)
	lw	$s0, 20($sp)
	addi	$sp, $sp, 24
	
	jr	$ra
#=========================================================

sortLinkedList:
# Save $s registers used
	addi	$sp, $sp, -28
	sw 	$s7, 24($sp)		#length of linked list
	sw	$s0, 20($sp)
	sw	$s1, 16($sp)
	sw	$s2, 12($sp)
	sw	$s3, 8($sp)
	sw	$s4, 4($sp)
	sw	$ra, 0($sp) 	# Save $ra just in case we may want to call a subprogram
	
	
	
	
# $s3: Node counter: 1, 2, ...
	li? ? ? $s5, 0	#bubble counter
	move $s7, $a1	#save length of linked list
bubble:
	move $s0, $a0	# $s0: points to the current node.
	beq	$s7, $s5,? ? sortedAll			
	addi	$s5, $s5, 1
	
loop:
	
	
		
				# $s0: Address of current node
	lw	$s1, 0($s0)	# $s1: Address of? ? next node
	
	la	$s6, 0($s1)
	
	beq	$s6, $zero, loopEnd
	
	lw	$s4, 0($s1)	#address of next nodes next node
	
	lw	$s2, 4($s0)	#key of current
	lw	$s3, 4($s1)	#key of next
	
	bgt	$s2, $s3, change
	
	j changeEnd
	
	change:
	sw	$s4, 0($s0)
	sw	$s0, 0($s1)
	
	beq? ? ? ? ? $a0, $s0, updateHead		# If current node is the head of the list update the list head pointer
? ? ? ? 	

? ? ? ? ? #Find previus node
	move? ? ? ? $s2, $a0? ? ? ? ? ? ? ? ? ? ? # Start at the head of the list
findPrev:
la	$s6, 0($s2)
	
beq	$s6, $zero, loopEnd

	lw? ? ? ? ? ? $s3, 0($s2)? ? ? ? ? ? ? ? # Load the address of the next node
	beq? ? ? ? ? $s3, $s0, foundPrev # If we found the previous node, stop
	move? ? ? ? $s2, $s3? ? ? ? ? ? ? ? ? ? ? # Move to the next node
	j? ? ? ? ? ? ? findPrev

foundPrev:
	sw? ? ? ? ? ? $s1, 0($s2)
	j? ? ? ? ? ? ? swapEnd

updateHead:
? ? ? ? # If the current node was the head, update the head to point to the next node
? ? ? ? 	move? ? ? ? $a0, $s1
		? ? 

	changeEnd: 
			
	move	$s0, $s1	# Consider next node.
	
swapEnd:					
													
j loop

loopEnd:

j bubble

sortedAll:

move	$v0, $a0



# Restore the register values
	lw	$ra, 0($sp)
	lw	$s4, 4($sp)
	lw	$s3, 8($sp)
	lw	$s2, 12($sp)
	lw	$s1, 16($sp)
	lw	$s0, 20($sp)
	lw	$s7, 24($sp)
	addi	$sp, $sp, 28
	jr	$ra




summaryList:

# a0 for address of head of first list	
# $a1: No. of nodes to be created ($a0 >= 1)
# $v1: returns list head
# By inserting a data value like this
# when we print linked list we can differentiate the node content from the node sequence no (1, 2, ...).

	addi	$sp, $sp, -36
	sw	$s5, 32($sp)
	sw	$s6, 28($sp)
	sw	$s7, 24($sp)
	sw	$s0, 20($sp)
	sw	$s1, 16($sp)
	sw	$s2, 12($sp)
	sw	$s3, 8($sp)
	sw	$s4, 4($sp)
	sw	$ra, 0($sp) 	# Save $ra just in case we may want to call a subprogram
	
	move	$s6, $a0	#address
	move	$s5, $a0	#address
	move	$s7, $a1	#length
	
	move	$s0, $a1	# $s0: no. of nodes to be created.
	li	$s1, 1		# $s1: Node counter

# Create the first node: header.
# Each node is 12 bytes: link field then 8 bit data field with key.
	li	$a0, 12
	li	$v0, 9
	syscall
# OK now we have the list head. Save list head pointer 
	move	$s2, $v0	# $s2 points to the first and last node of the linked list.
	move	$s3, $v0	# $s3 now points to the list head.
	
	lw 	$s4, 4($s6)

	sw	$s4, 4($s2)	# Store the data value.
	
	
	lw 	$s4, 8($s6)	
	
	sw	$s4, 8($s2)
	
	lw	$s4, 0($s6)
	
	move	$s6, $s4

addNode2:

# Are we done?
# No. of nodes created compared with the number of nodes to be created.
	beq	$s1, $s0, allDone2
	addi	$s1, $s1, 1	# Increment node counter.
	li	$a0, 12 	# Remember: Node size is 12 bytes.
	li	$v0, 9
	syscall
	
# Connect the this node to the lst node pointed by $s2.

	sw	$v0, 0($s2)
	
# Now make $s2 pointing to the newly created node.
	move	$s2, $v0	# $s2 now points to the new node.
	
	lw	$s4, 4($s6)
	
	sw	$s4, 4($s2)	# Store the data value.
	
	

	lw	$s4, 8($s6)
	
	sw	$s4, 8($s2)
	
	lw	$s4, 0($s6)
	
	move	$s6, $s4	
	
	j	addNode2

allDone2:





# Make sure that the link field of the last node cotains 0.
# The last node is pointed by $s2.
	sw	$zero, 0($s2)
	move	$v1, $s3	# Now $v1 points to the list head ($s3).
	move	$s6, $s5
	
loop2:
	

	lw	$s4, 0($s3)	#address of next
	beq	$s4, $zero, loop2End
	
	lw	$s0, 4($s3)
	
	lw	$s1, 4($s4)
	
	beq	$s0, $s1, equal
	j	notEqual
	
equal:
	
	lw	$s0, 8($s3)
	
	lw	$s1, 8($s4)
	
	add	$s1, $s1, $s0
	
	sw	$s1, 8($s3)
	
	lw	$s0, 0($s4)
	
	sw	$s0, 0($s3)
	
	addi 	$s7, $s7, -1
	
	j loop2
	
notEqual:
	move	$s3, $s4
    j loop2

loop2End:
	
	
	
# Restore the register values
	lw	$ra, 0($sp)
	lw	$s4, 4($sp)
	lw	$s3, 8($sp)
	lw	$s2, 12($sp)
	lw	$s1, 16($sp)
	lw	$s0, 20($sp)
	lw	$s5, 32($sp)
	lw	$s6, 28($sp)
	lw	$s7, 24($sp)
	addi	$sp, $sp, 36
	
	jr	$ra
#=========================================================


printLinkedList:
# Print linked list nodes in the following format
# --------------------------------------
# Node No: xxxx (dec)
# Address of Current Node: xxxx (hex)
# Address of Next Node: xxxx (hex)
# Data Value of Current Node: xxx (dec)
# --------------------------------------

# Save $s registers used
	addi	$sp, $sp, -24
	sw	$s0, 20($sp)
	sw	$s1, 16($sp)
	sw	$s2, 12($sp)
	sw	$s3, 8($sp)
	sw	$s4, 4($sp)
	sw	$ra, 0($sp) 	# Save $ra just in case we may want to call a subprogram

# $a0: points to the linked list.
# $s0: Address of current
# s1: Address of next
# $2: Data of current
# $s3: Node counter: 1, 2, ...
	move $s0, $a0	# $s0: points to the current node.
	li? ? ? $s3, 0

printNextNode:
	beq	$s0, $zero, printedAll
				# $s0: Address of current node
	lw	$s1, 0($s0)	# $s1: Address of? ? next node
	lw	$s2, 4($s0)	# $s2: Key of current node
	lw	$s4, 8($s0)	# $s3: Data of current node
	addi	$s3, $s3, 1
# $s0: address of current node: print in hex.
# $s1: address of next node: print in hex.
# $s2: data field value of current node: print in decimal.
	la	$a0, line
	li	$v0, 4
	syscall		# Print line seperator
	
	la	$a0, nodeNumberLabel
	li	$v0, 4
	syscall
	
	move	$a0, $s3	# $s3: Node number (position) of current node
	li	$v0, 1
	syscall
	
	la	$a0, addressOfCurrentNodeLabel
	li	$v0, 4
	syscall
	
	move	$a0, $s0	# $s0: Address of current node
	li	$v0, 34
	syscall

	la	$a0, addressOfNextNodeLabel
	li	$v0, 4
	syscall
	move	$a0, $s1	# $s0: Address of next node
	li	$v0, 34
	syscall	
	
	la	$a0, keyValueOfCurrentNode
	li	$v0, 4
	syscall
		
	move	$a0, $s2	# $s2: Key of current node
	li	$v0, 1		
	syscall	
	
	la	$a0, dataValueOfCurrentNode
	li	$v0, 4
	syscall
		
	move	$a0, $s4	# $s4: Data of current node
	li	$v0, 1		
	syscall	
	


# Now consider next node.
	move	$s0, $s1	# Consider next node.
	j	printNextNode

printedAll:
# Restore the register values
        lw	$ra, 0($sp)
        lw	$s4, 4($sp)
        lw	$s3, 8($sp)
        lw	$s2, 12($sp)
        lw	$s1, 16($sp)
        lw	$s0, 20($sp)
        addi	$sp, $sp, 24
        jr	$ra
#=========================================================

printReverse:
    #a0 has linked list head
    li $a1, 1

printReverseStart:
    addi $sp, $sp, -8
    sw $a0, 0($sp)
    sw $ra, 4($sp)

    bne $a0, $zero, recusivePrint

    lw $a0, 0($sp)
    lw $ra, 4($sp)
    addi $sp, $sp, 8
    #beq $a1, $a0,
    jr $ra

recusivePrint:
    lw $a0, 0($a0)
    jal printReverseStart

la	$a0, line
	li	$v0, 4
	syscall		# Print line seperator
	
la	$a0, nodeNumberLabel
	li	$v0, 4
	syscall
	
	move	$a0, $a1	# $a2: Node number (position) of current node
	li	$v0, 1
	syscall
	addi $a1, $a1, 1
	
	
	la	$a0, keyValueOfCurrentNode
	li	$v0, 4
	syscall
		
    lw $a0, 0($sp)
    lw $a2, 4($a0)
    move $a0, $a2
    li $v0, 1
    syscall
	la	$a0, dataValueOfCurrentNode
	li	$v0, 4
	syscall


    lw $a0, 0($sp)
    lw $ra, 4($sp)
    lw $a2, 8($a0)
    move $a0, $a2
    li $v0, 1
    syscall

    addi $sp, $sp, 8
    #beq $a1, $a0,
    jr $ra

.data
line:		.asciiz "\n --------------------------------------"

nodeNumberLabel:	.asciiz	"\n Node No.: "

addressOfCurrentNodeLabel:	.asciiz	"\n Address of Current Node: "

addressOfNextNodeLabel:		.asciiz	"\n Address of Next Node: "

keyValueOfCurrentNode:		.asciiz	"\n Key Value of Current Node: "

dataValueOfCurrentNode:		.asciiz	"\n Data Value of Current Node: "

numberOfNodesText:	.asciiz "\n Enter number of nodes in linked list:"

nodeKeyText:		.asciiz "\n Enter key for the list element: "

nodeDataText:		.asciiz "\n Enter data for the list element: "

