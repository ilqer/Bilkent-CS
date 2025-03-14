#get 2 registers from user
#find the distance between
#output the hex of two registers
#output the distance





.data

regText:	.asciiz	"\n input register:"

outDistText:	.asciiz "\n hamming distance between inputs:"

outFirstHexText:	.asciiz "\n first input in hexadecimal:"
outSecondHexText:	.asciiz "\n second input in hexadecimal:"

continueText:	.asciiz "\n input 0 if you want to end: "

.text 
main:



jal getReg

jal outHex

jal getDistance

jal outDist

la $a0, continueText
addi $v0,$zero 4
syscall

addi $v0,$zero 5
syscall

beqz $v0, end

j main

end: 

addi $v0,$zero, 10
syscall

.text
getReg:

la $a0, regText
addi $v0,$zero, 4
syscall

addi $v0,$zero 5
syscall
add $a2, $v0, 0


la $a0, regText
addi $v0,$zero 4
syscall
addi $v0,$zero 5
syscall
add $a3, $v0, 0

jr $ra



.text? ? ? 
getDistance:

addi $sp, $sp, -8
sw $s0, 0($sp)

sw $s1, 4($sp)


addi $s0, $zero, 0	#for hamming distance
addi $s1,$zero,? ? 31	#for the number of bits

distLoop:

blez $s1, distLoopEnd

andi $a0 ,$a2, 1 #0000000000....1
andi $a1, $a3, 1

xor $a0, $a0, $a1
add $s0, $s0, $a0

srl $a2, $a2, 1
srl $a3, $a3, 1

subi $s1, $s1, 1

j distLoop

distLoopEnd:

add $v1, $a0, $s0

lw $s0, 0($sp)
lw $s1, 4($sp)
addi $sp, $sp, 8



jr $ra



.text
outDist:


la $a0, outDistText
addi $v0,$zero 4
syscall

add $a0, $v1, 0
addi $v0,$zero, 1
syscall

 jr $ra 
 
 .text
outHex:

la $a0, outFirstHexText
addi $v0,$zero, 4
syscall

add $a0, $a2, 0
addi $v0,$zero, 34
syscall

la $a0, outSecondHexText
addi $v0,$zero, 4
syscall

add $a0, $a3, 0
addi $v0,$zero, 34
syscall

jr $ra


