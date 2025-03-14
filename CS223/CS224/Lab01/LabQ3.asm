
.text

li $v0, 4  
la $a0, inputA
syscall

li $v0, 5              
syscall
move $s0, $v0          

li $v0, 4  
la $a0, inputB
syscall

li $v0, 5              
syscall
move $s1, $v0   



add $t1, $s0, 7


div:
 

	div $s1, $t1
	
	mfhi $t2
	
	sub $t3, $s1, $s0
	
	div $t2, $t3
	
	mflo $t4



li $v0, 4  
la $a0, output
syscall

move $a0, $t4
li $v0, 1  
syscall

li $v0, 10             
syscall

.data

inputB:		.asciiz "\n Enter B: "
inputA:		.asciiz "\n Enter A: "

output:         .asciiz "\n Output is: "


endl:       .asciiz "\n"