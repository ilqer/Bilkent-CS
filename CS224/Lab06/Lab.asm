.text

main:
	li $v0, 4
	la $a0, sizeIn
	syscall

	li $v0, 5
	syscall
	beqz $v0, end
	move $a0, $v0
	move $s1, $a0

	mul $a0, $a0, $a0
	li $v0, 9
	syscall
	move $s0, $v0

	move $a0, $s0
	move $a1, $s1
	jal initializeMatrix

	#jal askElement

	#move $a2, $v0
	#move $a3, $v1
	#move $a0, $s0
	#move $a1, $s1
	#jal getElement

	#move $a1, $v0
	#jal print

	li $v0, 4
	la $a0, rowOrColumnText
	syscall

	li $v0, 5
	syscall
	beq $v0, 1, row
	beq $v0, 2, column
	j main

row:
	move $a0, $s0
	move $a1, $s1
	jal calculateRowSum
	j end

column:
	move $a0, $s0
	move $a1, $s1
	jal calculateColumnSum
	j end

initializeMatrix:
	mul $a1, $a1, $a1
	addi $t0, $zero, 1

initLoop:
	blt $a1, $t0, initEnd
	addi $t1, $t0, -1
	sll $t1, $t1, 2
	add $t1, $t1, $a0
	sw $t0, 0($t1)
	addi $t0, $t0, 1
	j initLoop

initEnd:
	jr $ra

askElement:
	li $v0, 4
	la $a0, elementText
	syscall

	
	li $v0, 4
	la $a0, rowText
	syscall

	li $v0, 5
	syscall
	move $t0, $v0

	li $v0, 4
	la $a0, columnText
	syscall

	li $v0, 5
	syscall
	move $t1, $v0

	move $v0, $t0
	move $v1, $t1
	jr $ra

getElement:

	mul $t0, $a2, $a1
	add $t0, $t0, $a3
	sll $t0, $t0, 2
	add $t0, $t0, $a0
	lw $v0, 0($t0)
	jr $ra

print:
	li $v0, 4
	la $a0, printElementText
	syscall
	
	
	move $a0,$a1
	li $v0, 1
	syscall

	jr $ra

calculateRowSum:
	move $t0, $a0
	move $t1, $a1
	add $t2, $zero, $zero
	blt $t2, $t1, rowInnerLoopStart
	j rowSumEnd

	add $t3, $zero, $zero
	add $t4, $zero, $zero

rowInnerLoop:
	mul $t5, $t2, $t1
	add $t5, $t5, $t3
	sll $t5, $t5, 2
	add $t5, $t5, $t0
	lw $t6, 0($t5)
	add $t4, $t4, $t6
	addi $t3, $t3, 1
	blt $t3, $t1, rowInnerLoop

	li $v0, 4
	la $a0, rowSumText
	syscall

	li $v0, 1
	move $a0, $t4
	syscall

	li $v0, 11
	li $a0, '\n'
	syscall

	addi $t2, $t2, 1
	j rowOuterLoop

rowSumEnd:
	jr $ra

calculateColumnSum:
	move $t0, $a0
	move $t1, $a1
	add $t2, $zero, $zero
	blt $t2, $t1, colInnerLoopStart
	j colSumEnd


	add $t3, $zero, $zero
	add $t4, $zero, $zero

colInnerLoop:
	mul $t5, $t3, $t1
	add $t5, $t5, $t2
	sll $t5, $t5, 2
	add $t5, $t5, $t0
	lw $t6, 0($t5)
	add $t4, $t4, $t6
	addi $t3, $t3, 1
	blt $t3, $t1, colInnerLoop

	li $v0, 4
	la $a0, columnSumText
	syscall

	li $v0, 1
	move $a0, $t4
	syscall

	li $v0, 11
	li $a0, '\n'
	syscall

	addi $t2, $t2, 1
	j colOuterLoop

colSumEnd:
	jr $ra

end:
	li $v0, 10
	syscall

.data
sizeIn:? ? ? ? ? ? ? ? ? ? ? ? .asciiz "Enter the dimension (0 to exit): "
elementText:? ? ? ? ? ? ? .asciiz "Row and column to display: \n"
columnText:? ? ? ? ? ? ? ? .asciiz "Column: "
rowText:? ? ? ? ? ? ? ? ? ? ? .asciiz "Row: "
printElementText:? ? .asciiz "Element: "
rowSumText:? ? ? ? ? ? ? ? .asciiz "Row sum: "
columnSumText:? ? ? ? ? .asciiz "Column sum: "
rowOrColumnText:? ? ? .asciiz "1 for row sum, 2 for column sum: "
