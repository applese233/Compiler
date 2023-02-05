	.text

	.globl	main
	.p2align	2
	.type	main,@function
main:
.main_INIT:
	addi	sp, sp, -40
	sw	ra, 36(sp)
	sw	s0, 32(sp)
	addi	s0, sp, 40
	sw	s0, -24(s0)
	sw	s1, -28(s0)
	j	.main_L0
.main_L0:
	call	_INIT_
	li	t0, 1
	sw	t0, -12(s0)
	j	.main_L1
.main_L1:
	lw	t0, -12(s0)
	lw	t1, N
	sgt	t0, t0, t1
	xori	t0, t0, 1
	beqz	t0,	.main_L4
	j	.main_L2
.main_L2:
	lw	t2, -12(s0)
	lw	t1, b
	li	t0, 4
	mul	t0, t2, t0
	add	t1, t1, t0
	li	t0, 1
	sw	t0, 0(t1)
	j	.main_L3
.main_L3:
	lw	t1, -12(s0)
	li	t0, 1
	add	t0, t1, t0
	sw	t0, -12(s0)
	j	.main_L1
.main_L4:
	li	t0, 2
	sw	t0, -12(s0)
	j	.main_L5
.main_L5:
	lw	t0, -12(s0)
	lw	t1, N
	sgt	t0, t0, t1
	xori	t0, t0, 1
	beqz	t0,	.main_L8
	j	.main_L6
.main_L6:
	lw	t1, -12(s0)
	lw	t2, b
	li	t0, 4
	mul	t0, t1, t0
	add	t0, t2, t0
	lw	t0, 0(t0)
	beqz	t0,	.main_L10
	j	.main_L9
.main_L9:
	li	t0, 2
	sw	t0, -16(s0)
	lw	t1, -12(s0)
	li	t0, 3
	sgt	t0, t1, t0
	beqz	t0,	.main_L12
	j	.main_L13
.main_L12:
	sw	zero, -20(s0)
	j	.main_L11
.main_L13:
	lw	t1, -12(s0)
	li	t0, 2
	sub	t1, t1, t0
	lw	t0, b
	li	t2, 4
	mul	t2, t1, t2
	add	t0, t0, t2
	lw	t0, 0(t0)
	sw	t0, -20(s0)
	j	.main_L11
.main_L11:
	lw	t0, -20(s0)
	beqz	t0,	.main_L15
	j	.main_L14
.main_L14:
	lw	t1, resultCount
	li	t0, 1
	add	t0, t1, t0
	la	t1, resultCount
	sw	t0, 0(t1)
	lw	t0, -12(s0)
	li	t1, 2
	sub	a0, t0, t1
	call	toString
	la	a1, str1
	call	_str_concatenate
	mv	s1, a0
	lw	a0, -12(s0)
	call	toString
	mv	a1, a0
	mv	a0, s1
	call	_str_concatenate
	call	println
	j	.main_L15
.main_L15:
	j	.main_L16
.main_L16:
	lw	t1, -12(s0)
	lw	t0, -16(s0)
	mul	t1, t1, t0
	lw	t0, N
	sgt	t0, t1, t0
	xori	t0, t0, 1
	beqz	t0,	.main_L18
	j	.main_L17
.main_L17:
	lw	t1, -12(s0)
	lw	t0, -16(s0)
	mul	t1, t1, t0
	lw	t2, b
	li	t0, 4
	mul	t0, t1, t0
	add	t0, t2, t0
	sw	zero, 0(t0)
	lw	t0, -16(s0)
	li	t1, 1
	add	t0, t0, t1
	sw	t0, -16(s0)
	j	.main_L16
.main_L18:
	j	.main_L10
.main_L10:
	j	.main_L7
.main_L7:
	lw	t0, -12(s0)
	li	t1, 1
	add	t0, t0, t1
	sw	t0, -12(s0)
	j	.main_L5
.main_L8:
	la	s1, str2
	lw	a0, resultCount
	call	toString
	mv	a1, a0
	mv	a0, s1
	call	_str_concatenate
	call	println
	lw	s0, -24(s0)
	lw	s1, -28(s0)
	mv	a0, zero
	j	.main_RETURN
.main_RETURN:
	lw	s0, 32(sp)
	lw	ra, 36(sp)
	addi	sp, sp, 40
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
	la	t1, N
	li	t0, 15
	sw	t0, 0(t1)
	li	t0, 15001
	li	t1, 4
	mul	t1, t0, t1
	li	t0, 4
	add	a0, t1, t0
	call	_f_malloc
	li	t0, 15001
	sw	t0, 0(a0)
	li	t0, 4
	li	t1, 1
	mul	t0, t1, t0
	add	t0, a0, t0
	la	t1, b
	sw	t0, 0(t1)
	la	t0, resultCount
	sw	zero, 0(t0)
	mv	a0, zero
	j	._INIT__RETURN
._INIT__RETURN:
	lw	s0, 12(sp)
	lw	ra, 16(sp)
	addi	sp, sp, 20
	ret


	.type	N,@object
	.section	.sbss
	.globl	N
	.p2align	2
N:
	.word	0
	.size	N, 4

	.type	b,@object
	.section	.sbss
	.globl	b
	.p2align	2
b:
	.word	0
	.size	b, 4

	.type	resultCount,@object
	.section	.sbss
	.globl	resultCount
	.p2align	2
resultCount:
	.word	0
	.size	resultCount, 4

	.type	str1,@object
	.section	.rodata
str1:
	.asciz	" "
	.size	str1, 1

	.type	str2,@object
	.section	.rodata
str2:
	.asciz	"Total: "
	.size	str2, 7

