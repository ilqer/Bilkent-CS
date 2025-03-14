.text

li $v0, 4  
la $a0, inputB
syscall

li $v0, 5              
syscall
move $s0, $v0          

li $v0, 4  
la $a0, inputC
syscall

li $v0, 5              
syscall
move $s1, $v0   

li $v0, 4  
la $a0, inputD
syscall

li $v0, 5              
syscall
move $s2, $v0   

li $t0, 0
la $t1, ($s0)
la $t2, ($s1)

division:

	bgt $t2, $t1, end_division
	addi $t0, $t0, 1
	sub $t1, $t1, $t2
	j division

end_division:


la $t1, ($s2)
la $t2, ($s0)

mod:

	bgt $t2, $t1, end_mod
	sub $t1, $t1, $t2	
	j mod
end_mod:

add $t3, $t0, $t1
sub $t3, $t3, $s1

li $t0, 0
la $t1, ($t3)
la $t2, ($s0)
division2:

	bgt $t2, $t1, end_division2
	addi $t0, $t0, 1
	sub $t1, $t1, $t2
	j division2

end_division2:

li $v0, 4  
la $a0, output
syscall

la $a0, ($t0)
li $v0, 1  
syscall

.data

inputB:		.asciiz "\n Enter B: "
inputC:		.asciiz "\n Enter C: "
inputD:		.asciiz "\n Enter D: "

output:         .asciiz "\n Output is: "


endl:       .asciiz "\n"
