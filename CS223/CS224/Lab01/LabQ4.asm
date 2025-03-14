.text

main:
la $a0, entr
li $v0, 4 
syscall

li $v0, 5
syscall
move $t0, $v0
move $s0, $t0
li $t1, 0
loop:
	
	blez	$t0, loop_exit
	
	la $a0, entrarr
	li $v0, 4 
	syscall
	
	li $v0, 5
	syscall
	sw $v0, array($t1)
	
	
	
	add, $t1, $t1, 4
	sub $t0, $t0, 1
	j loop
	
loop_exit:

li $t1, 0
lw $t2, array($t1)


move $t0, $s0

max_loop:

	blez	$t0, maxloop_exit
	addi $t1, $t1, 4
	lw $t3, array($t1)
	
	bgt $t3, $t2, loopcheck
	j loopend
	loopcheck:
		move $t2, $t3
	loopend:
		
	
	sub $t0, $t0, 1
	j max_loop

maxloop_exit:
move $t1, $t2

li $t2, 0
li $t3, 0

move $t0, $s0
maxnum:

	blez	$t0, maxnum_exit
	lw $t4, array($t2)
	beq $t1, $t4, equal
	j equalend
	equal:
		addi $t3, $t3, 1
	equalend:
	addi $t2, $t2, 4
	
	sub $t0, $t0, 1
	
	j maxnum
	
maxnum_exit:

li $t2, 0
li $t7, 0

move $t0, $s0
maxdiv:

	blez	$t0, maxdiv_exit
	lw $t4, array($t2)
	div $t1, $t4	
	
	mfhi $t6
	beqz $t6, eq
	j eqend
	eq:
		beqz $t4, eqend
		beq $t1, $t4, eqend
		addi $t7,$t7,1
	eqend:	
	
	addi $t2, $t2, 4
	sub $t0, $t0, 1
	
	
	j maxdiv
	
	
	
	
maxdiv_exit:

mainloop:
la $a0, loopstr
li $v0, 4 
syscall

li $v0, 5
syscall
move $t4, $v0

beq $t4, 1, maxdis
beq $t4, 2, maxnumdis
beq $t4, 3, maxdivdis
beq $t4, 4, exit
j mainloop
maxdis:
	
	la $a0, maxtext
	li $v0, 4 
	syscall
	
	move $a0, $t1
	li $v0, 1
	syscall
	j mainloop
maxnumdis:

	la $a0, maxnumtext
	li $v0, 4 
	syscall
	
	move $a0, $t3
	li $v0, 1
	syscall
	j mainloop
	
maxdivdis: 

	la $a0, maxdivtext
	li $v0, 4 
	syscall
	
	move $a0, $t7
	li $v0, 1
	syscall
	j mainloop


exit: 
	li $v0, 10
	syscall


.data

.align 4

array:	.space	400

entr:	.asciiz "\n enter the number of elements: "

entrarr:.asciiz "\n  elements one by one:"

loopstr:.asciiz "\n comment based menu 1 2 3 4"

maxtext:.asciiz "\n max number in array is:"

maxnumtext:.asciiz "\n max number count in array is:"

maxdivtext:.asciiz "\n max number is divisible by this many numbers:"







