
#get register
#reverse register 
#display register

.data

regText:	.asciiz	"\n input register:"

outHexText:	.asciiz "\n output in hexadecimal:"

outText:	.asciiz "\n input in hexadecimal:"

continueText:	.asciiz "\n input 0 if you want to end: "

.text
main:

jal getRegister

jal reverseRegister

jal displayRegister

la $a0, continueText
addi $v0,$zero, 4
syscall

addi $v0,$zero, 5
syscall

beqz $v0, end


j main

end: 

addi $v0,$zero, 10
syscall

getRegister:

la $a0, regText
addi $v0,$zero, 4
syscall


addi $v0,$zero, 5
syscall
add $a3, $v0, 0				#a3 for the register
add $s3, $a3, 0				#saving initial register

jr $ra


reverseRegister:

addi $sp, $sp, -8
sw $s0, 0($sp)

sw $s1, 4($sp)

addi $s0, $zero, 0	#for reversed register
addi $s1,$zero 32	#for number of bits	

reverseLoop:

blez $s1, reverseLoopEnd

andi $a0, $a3, 1		#get lsb

sll $s0, $s0, 1

or $s0, $s0, $a0


srl $a3, $a3, 1

subi $s1, $s1, 1

j reverseLoop

reverseLoopEnd:

add $v1, $s0, 0

lw $s0, 0($sp)
lw $s1, 4($sp)
addi $sp, $sp, 8

add $a3, $s3, 0

jr $ra

displayRegister:

la $a0, outText
addi $v0,$zero 4
syscall

add $a0, $s3, 0
addi $v0,$zero 34
syscall

la $a0, outHexText
addi $v0,$zero 4
syscall

add $a0, $v1, 0
addi $v0,$zero 34
syscall

jr $ra

