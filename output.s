	.text

	.globl	main
	.p2align	2
	.type	main,@function
main:
.main_INIT:
	addi	sp, sp, -36
	sw	ra, 32(sp)
	sw	s0, 28(sp)
	addi	s0, sp, 36
	sw	s1, -24(s0)
	j	.main_L0
.main_L0:
	call	_INIT_
	la	t0, str1
	sw	t0, -12(s0)
	la	t0, str2
	sw	t0, -16(s0)
	lw	a0, -12(s0)
	lw	a1, -16(s0)
	call	_str_concatenate
	sw	a0, -20(s0)
	lw	a0, -12(s0)
	call	println
	lw	a0, -16(s0)
	call	println
	lw	a0, -20(s0)
	call	println
	lw	a0, -20(s0)
	call	_str_length
	call	printlnInt
	lw	a0, -20(s0)
	li	a1, 5
	call	_str_ord
	call	printlnInt
	lw	a0, -20(s0)
	call	_str_length
	mv	s1, a0
	lw	a0, -20(s0)
	li	a1, 5
	call	_str_ord
	add	a0, s1, a0
	lw	s1, -24(s0)
	j	.main_RETURN
.main_RETURN:
	lw	s0, 28(sp)
	lw	ra, 32(sp)
	addi	sp, sp, 36
	ret

	.globl	_INIT_
	.p2align	2
	.type	_INIT_,@function
_INIT_:
._INIT__INIT:
	addi	sp, sp, -20
	sw	ra, 16(sp)
	sw	s0, 12(sp)
	addi	s0, sp, 20
	j	._INIT__L0
._INIT__L0:
	mv	a0, zero
	j	._INIT__RETURN
._INIT__RETURN:
	lw	s0, 12(sp)
	lw	ra, 16(sp)
	addi	sp, sp, 20
	ret


	.type	str1,@object
	.section	.rodata
str1:
	.asciz	"aaa"
	.size	str1, 3

	.type	str2,@object
	.section	.rodata
str2:
	.asciz	"bbbbb"
	.size	str2, 5

