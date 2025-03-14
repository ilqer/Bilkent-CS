
.text
main:
la $a0, registerNumText
li $v0, 4
syscall

li $v0, 5
syscall

move $s0, $v0

blt $s0, $zero, registerUseExit
bgt $s0, 31, registerUseExit


jal test


jal countRegiserUse

move $a0, $v0
li $v0, 1
syscall


j main



registerUseExit:
li $v0, 10
syscall


countRegiserUse:


li $v0, 0	#v0 for count
la $s1, test	#start of subprogram

loop3:

lw 	$s2, 0($s1)

beqz $s2, loop3End

controlEnd:
srl	$s3, $s2, 26
andi	$s3, $s3, 0x3F
beq	$s3, 2, rsEnd

#rd 15-11 
srl     $s3, $s2, 11        # Shift right 11 bits
andi    $s3, $s3, 0x1F      
beq     $s3, $s0, increment1 



j rdEnd
increment1:
addi $v0, $v0, 1
rdEnd:

#rt 20-16
srl     $s3, $s2, 16        # Shift right 16 bits
andi    $s3, $s3, 0x1F      
beq     $s3, $s0, increment2 

j rtEnd
increment2:
addi $v0, $v0, 1
rtEnd:

#rs 25-21
srl     $s3, $s2, 21        # Shift right 21 bits
andi    $s3, $s3, 0x1F      #5 bits form
beq     $s3, $s0, increment3 
j rsEnd
increment3:
addi $v0, $v0, 1
rsEnd:


addi $s1, $s1, 4
j       loop3

loop3End:
jr	$ra


test:

addi $t1, $zero , 4
subi $t2, $s1, 3 
addi $t1, $t4  3  
jr $ra

.data

registerNumText:		.asciiz "\n Enter register number to count: "