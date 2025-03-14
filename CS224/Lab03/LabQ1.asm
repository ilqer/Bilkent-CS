
.text

main:

la $a0, divideNumText
li $v0, 4
syscall

li $v0, 5
syscall
move $a2, $v0


la $a0, divisorNumText
li $v0, 4
syscall

li $v0, 5
syscall
move $a3, $v0

blez $a2, exit
blez $a3, exit

jal recusiveDiv

move $s0, $v0


la $a0, quotientText
li $v0, 4
syscall

move $a0, $s0
li $v0, 1
syscall

j main

exit:

li $v0, 10
syscall


recusiveDiv:
addi $sp, $sp -4
sw? ? ? $ra, 0($sp)

blt $a2, $a3, return

sub $a2, $a2, $a3

jal prevCase

addi $v0, $v0, 1
lw $ra, 0($sp)
addi $sp, $sp, 4
jr $ra



prevCase:
bge $a2, $a3, recusiveDiv

li $v0, 0
jr $ra


return:

li $v0, 0
jr $ra

.data

divideNumText:		.asciiz "\n Enter number to divde:"

divisorNumText:		.asciiz "\n Enter divisor number :"

quotientText:		.asciiz "\n Quotient is: "

