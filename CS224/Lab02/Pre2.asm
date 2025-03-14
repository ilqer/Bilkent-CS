.data
ArraySizeText:	.asciiz "Enter array size: "

ArrayElementText: .asciiz "Enter number in array: "

Space:		.asciiz "  "

Comma:		.asciiz ",    "

FreqTable: 	.word 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0

.text

main:

jal CreateArray

move $a0, $v0			#a0 for the address of the array

move $a1, $v1			#a1 for the size of the array

la $a2, FreqTable

jal FindFreq

li $v0, 10
syscall				#Exit




.text 

CreateArray:

la $a0, ArraySizeText
li $v0, 4
syscall

li $v0, 5
syscall
move $v1, $v0			#array size in v1

mul $a0, $v1, 4
li $v0, 9
syscall
move $s0, $v0			#array address in s0


move $s1, $ra			#return address in s1

jal InitializeArray

jr $s1


.text

InitializeArray:

move $t0, $v1

move $t1, $s0

loop:

beqz $t0, loopEnd

la $a0, ArrayElementText
li $v0, 4
syscall

li $v0, 5
syscall



sw $v0, 0($t1)
addi $t1, $t1, 4

subi $t0, $t0, 1

j loop

loopEnd:

move $t0, $v1

move $t1, $s0

loop2:

beqz $t0, loop2End

lw $a0, 0($t1)
li $v0, 1
syscall

la $a0, Space
li $v0, 4
syscall

addi $t1, $t1, 4

subi $t0, $t0, 1

j loop2

loop2End:

move $v0, $s0

jr $ra
