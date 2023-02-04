	.text

	.globl	main
	.p2align	2
	.type	main,@function
main:
.main_INIT:
	addi	sp, sp, -24
	sw	ra, 20(sp)
	sw	s0, 16(sp)
	addi	s0, sp, 24
	j	.main_L0
.main_L0:
	call	_INIT_
	call	getInt
	la	t0, n
	sw	a0, 0(t0)
	call	getInt
	la	t0, p
	sw	a0, 0(t0)
	call	getInt
	la	t0, k
	sw	a0, 0(t0)
	lw	t0, p
	lw	t1, k
	sub	t1, t0, t1
	li	t0, 1
	sgt	t0, t1, t0
	beqz	t0,	.main_L2
	j	.main_L1
.main_L1:
	la	a0, str1
	call	print
	j	.main_L2
.main_L2:
	lw	t0, p
	lw	t1, k
	sub	t0, t0, t1
	la	t1, i
	sw	t0, 0(t1)
	j	.main_L3
.main_L3:
	lw	t0, p
	lw	t1, k
	add	t1, t0, t1
	lw	t0, i
	sgt	t0, t0, t1
	xori	t0, t0, 1
	beqz	t0,	.main_L6
	j	.main_L4
.main_L4:
	lw	t1, i
	li	t0, 1
	sgt	t0, t0, t1
	xori	t0, t0, 1
	beqz	t0,	.main_L8
	j	.main_L9
.main_L8:
	sw	zero, -12(s0)
	j	.main_L7
.main_L9:
	lw	t0, i
	lw	t1, n
	sgt	t0, t0, t1
	xori	t0, t0, 1
	sw	t0, -12(s0)
	j	.main_L7
.main_L7:
	lw	t0, -12(s0)
	beqz	t0,	.main_L11
	j	.main_L10
.main_L10:
	lw	t1, i
	lw	t0, p
	xor	t0, t1, t0
	seqz	t0, t0
	beqz	t0,	.main_L13
	j	.main_L12
.main_L12:
	la	a0, str2
	call	print
	lw	a0, i
	call	toString
	call	print
	la	a0, str3
	call	print
	j	.main_L14
.main_L13:
	lw	a0, i
	call	printInt
	la	a0, str4
	call	print
	j	.main_L14
.main_L14:
	j	.main_L11
.main_L11:
	j	.main_L5
.main_L5:
	lw	t1, i
	li	t0, 1
	add	t1, t1, t0
	la	t0, i
	sw	t1, 0(t0)
	j	.main_L3
.main_L6:
	lw	t1, p
	lw	t0, k
	add	t0, t1, t0
	lw	t1, n
	slt	t0, t0, t1
	beqz	t0,	.main_L16
	j	.main_L15
.main_L15:
	la	a0, str5
	call	print
	j	.main_L16
.main_L16:
	mv	a0, zero
	j	.main_RETURN
.main_RETURN:
	lw	s0, 16(sp)
	lw	ra, 20(sp)
	addi	sp, sp, 24
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


	.type	n,@object
	.section	.sbss
	.globl	n
	.p2align	2
n:
	.word	0
	.size	n, 4

	.type	p,@object
	.section	.sbss
	.globl	p
	.p2align	2
p:
	.word	0
	.size	p, 4

	.type	k,@object
	.section	.sbss
	.globl	k
	.p2align	2
k:
	.word	0
	.size	k, 4

	.type	i,@object
	.section	.sbss
	.globl	i
	.p2align	2
i:
	.word	0
	.size	i, 4

	.type	str1,@object
	.section	.rodata
str1:
	.asciz	"<< "
	.size	str1, 3

	.type	str2,@object
	.section	.rodata
str2:
	.asciz	"("
	.size	str2, 1

	.type	str3,@object
	.section	.rodata
str3:
	.asciz	") "
	.size	str3, 2

	.type	str4,@object
	.section	.rodata
str4:
	.asciz	" "
	.size	str4, 1

	.type	str5,@object
	.section	.rodata
str5:
	.asciz	">> "
	.size	str5, 3

