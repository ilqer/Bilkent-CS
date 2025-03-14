.text

main:
la $a0, entr
li $v0, 4 
syscall

li $v0, 5
syscall
move $s0, $v0

bltz $s0, exit
li $s1, 20
bgt $s0, $s1, exit

la $a1, array
li $s1, 0

loop: 
	beq $s0, $s1, exit_loop 
	addi $s1, $s1, 1 
	la $a0, prompt
	li $v0, 4
	syscall
	
	li $v0, 5
	syscall
	move $t2, $v0
	sw $t2, 0($a1)
	addi $a1, $a1, 4
	j loop

exit_loop:

li $s1, 0
la $t2, array 

la $a0, mssg
li $v0, 4
syscall

originalout_loop:
	
	beq $s0, $s1, exitoriginal_loop 
	addi $s1, $s1, 1 
	lw $a0, 0($t2) 
	li $v0, 1 
	syscall
	addi $t2, $t2, 4
	la $a0, endl
	li $v0, 4
	syscall
	j originalout_loop
	
exitoriginal_loop:



move $t0, $s0
srl $t0, $t0, 1
la $t1, array
li $s1, 0
la $t2, array
move $t3, $s0
sll $t3, $t3, 2
add $t2, $t2, $t3
add $t2, $t2, -4


swap_loop:
	
	beq $t0, $s1, exitswap_loop 
	addi $s1, $s1, 1
	lw $t4, 0($t1)
	lw $t5, 0($t2)
	sw $t5, 0($t1)               
    	sw $t4, 0($t2)               
    	addi $t1, $t1, 4            
    	addi $t2, $t2, -4             
    	j swap_loop                  
    	
exitswap_loop:

li $s1, 0
la $t2, array 

la $a0, mssg2
li $v0, 4
syscall


swapout_loop:
	beq $s0, $s1, exitswapout_loop 
	addi $s1, $s1, 1 
	lw $a0, 0($t2) 
	li $v0, 1 
	syscall
	addi $t2, $t2, 4
	la $a0, endl
	li $v0, 4
	syscall
	j swapout_loop
	
exitswapout_loop:

exit: 
	li $v0, 10
	syscall

.data
.align 4

array:      .space 80


endl:       .asciiz "\n"

entr:       .asciiz "\n number of elements?"

prompt:     .asciiz "\n Enter a number: "

mssg:       .asciiz "\n original array: "

mssg2:       .asciiz "\n swapped array: " 

