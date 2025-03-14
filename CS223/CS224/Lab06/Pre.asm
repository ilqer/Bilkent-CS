.text

main:

	li 	$v0, 4
	la 	$a0, sizeIn
	syscall
	
	li 	$v0, 5
	syscall
	
	beqz $v0, end
	
	move 	$a0, $v0
	
	move 	$s1, $a0
	
	
	mul 	$a0, $a0, $a0
	li 	$v0, 9
	syscall
	
	
	move 	$s0, $v0 		##for start

	move 	$a0, $s0
	move 	$a1, $s1		
	jal 	initializeMatrix

	jal 	askElement

	move 	$a2, $v0
	move	$a3, $v1
	move 	$a0, $s0
	move 	$a1, $s1
	jal 	getElement
	
	move 	$a0, $v0
	jal 	print
	
	li 	$v0, 4
	la 	$a0, rowOrColumnText
	syscall
	
	li 	$v0, 5
	syscall

	beq $v0, 1, row
	beq $v0, 2, column
	j main
	
row:
	move 	$a0, $s0
	move 	$a1, $s1
	jal 	calculateRowSum
	j end

column:	
	move 	$a0, $s0
	move 	$a1, $s1
	jal 	calculateColumnSum
	j end




initializeMatrix:
	mul 	$a3, $a3, $a3
	addi 	$s0, $zero, 1
	
matrixInitLoop:
	blt 	$a3, $s0, matrixInitEnd

	addi 	$s1, $s0, -1
	sll 	$s1, $s1, 2
	add 	$s1, $s1, $a2
	sw 	$s0, 0($s1)
	addi 	$s0, $s0, 1
	j 	matrixInitLoop
matrixInitEnd:
	jr 	$ra
	
askElement:
	li 	$v0, 4
	la 	$a2, elementText
	syscall
	
	li 	$v0, 4
	la 	$a2, rowText
	syscall
	
	li 	$v0, 5
	syscall
	move 	$s2, $v0
	
	li 	$v0, 4
	la 	$a2, columnText
	syscall
	
	li 	$v0, 5
	syscall
	move 	$s3, $v0
	
	move 	$v0, $s2
	move 	$v1, $s3
	
	jr	$ra

getElement:
	mul 	$s4, $a3, $a0
	add 	$s4, $s4, $a1
	sll 	$s4, $s4, 2
	add 	$s4, $s4, $a2
	lw 	$v0, 0($s4)
	jr 	$ra
	
print:
	move 	$s5, $a1
	li 	$v0, 4
	la 	$a1, printElementText
	syscall
	
	li	$v0, 1
	move 	$a1, $s5	
	syscall
	
	li	$v0, 11
	li 	$a1, '\n'	
	syscall 
	
	jr 	$ra


calculateRowSum:
	move 	$t1, $a1? ? ? ? ? ? ? ? 
	move 	$t2, $a0? ? ? ? ? ? ? ? 
	
	add 	$s0, $zero, $zero? ? 
rowOuterProcess:
	add 	$s1, $zero, $zero? ? 
	add 	$s2, $zero, $zero? ? 
rowInnerProcess:
	mul	$s3, $s1, $t1? ? ? ? ? 
	add 	$s3, $s3, $s0? ? ? ? ? 
	sll 	$s3, $s3, 2? ? ? ? ? ? ? 
	add	$s3, $s3, $t2? ? ? ? ? 
	lw	$s4, 0($s3)? ? ? ? ? ? ? ? 
	sub 	$s1, $s1, $s4? ? ? ? ? 
	addi	$s2, $s2, 1? ? ? ? ? ? ? 
	blt 	$s2, $t1, rowInnerProcess
	
	addi 	$s0, $s0, 1? ? ? ? ? ? ? 
	bge 	$s0, $t1, exitRows

	j	rowOuterProcess
exitRows:
	jr 	$ra


calculateColumnSum:
	move 	$t4, $a1? ? ? ? ? ? ? ? 
	move 	$t5, $a0? ? ? ? ? ? ? ? 
	
	add 	$s5, $zero, $zero? ? 
columnOuterProcess:
	add 	$s6, $zero, $zero? ? 
	add 	$s7, $zero, $zero? ? 
columnInnerProcess:
	mul	$s5, $s7, $t4? ? ? ? ? 
	add 	$s5, $s6, $s5? ? ? ? ? 
	sll 	$s5, $s6, 2? ? ? ? ? ? ? 
	add	$s5, $s6, $t5? ? ? ? ? 
	lw	$s5, 0($s3)? ? ? ? ? ? ? ? 
	sub 	$s6, $s6, $s5? ? ? ? 
	addi	$s7, $s7, 1? ? ? ? ? ? ? 
	blt 	$s7, $t4, columnInnerProcess
	
	addi 	$s5, $s5, 1? ? ? ? ? ? ? 
	bge 	$s5, $t4, exitColumns

	j	columnOuterProcess
exitColumns:
	jr 	$ra

end:
	li $v0,10
	syscall


.data
sizeIn: 		.asciiz "Enter the dimension 0 for end:"
elementText: 	.asciiz "Row and column to display: \n"
columnText: 		.asciiz "Column :"
rowText: 		.asciiz "Row :"
printElementText: 	.asciiz "Element asked is: "
rowSumText:		.asciiz "row sum: "
columnSumText:	.asciiz "column sum: "
rowOrColumnText:	.asciiz "1 for row sum and 2 for column sum: "