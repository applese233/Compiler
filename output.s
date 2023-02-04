	.text

	.globl	getHash
	.p2align	2
	.type	getHash,@function
getHash:
.getHash_INIT:
	addi	sp, sp, -24
	sw	ra, 20(sp)
	sw	s0, 16(sp)
	addi	s0, sp, 24
	j	.getHash_L0
.getHash_L0:
	sw	a0, -12(s0)
	lw	t1, -12(s0)
	li	t0, 237
	mul	t0, t1, t0
	lw	t1, hashsize
	rem	a0, t0, t1
	j	.getHash_RETURN
.getHash_RETURN:
	lw	s0, 16(sp)
	lw	ra, 20(sp)
	addi	sp, sp, 24
	ret

	.globl	put
	.p2align	2
	.type	put,@function
put:
.put_INIT:
	addi	sp, sp, -40
	sw	ra, 36(sp)
	sw	s0, 32(sp)
	addi	s0, sp, 40
	sw	s1, -28(s0)
	j	.put_L0
.put_L0:
	sw	a0, -12(s0)
	sw	a1, -16(s0)
	sw	zero, -24(s0)
	lw	a0, -12(s0)
	call	getHash
	sw	a0, -20(s0)
	lw	t0, -20(s0)
	lw	t1, table
	li	t2, 4
	mul	t2, t0, t2
	add	t0, t1, t2
	lw	t0, 0(t0)
	xor	t0, t0, zero
	seqz	t0, t0
	beqz	t0,	.put_L2
	j	.put_L1
.put_L1:
	lw	t2, -20(s0)
	lw	t0, table
	li	t1, 4
	mul	t1, t2, t1
	add	s1, t0, t1
	li	a0, 16
	call	_f_malloc
	sw	a0, 0(s1)
	lw	t0, -20(s0)
	lw	t1, table
	li	t2, 4
	mul	t2, t0, t2
	add	t0, t1, t2
	lw	t0, 0(t0)
	addi	t1, t0, 0
	lw	t0, -12(s0)
	sw	t0, 0(t1)
	lw	t1, -20(s0)
	lw	t2, table
	li	t0, 4
	mul	t0, t1, t0
	add	t0, t2, t0
	lw	t0, 0(t0)
	addi	t1, t0, 4
	lw	t0, -16(s0)
	sw	t0, 0(t1)
	lw	t1, -20(s0)
	lw	t0, table
	li	t2, 4
	mul	t2, t1, t2
	add	t0, t0, t2
	lw	t0, 0(t0)
	addi	t0, t0, 8
	sw	zero, 0(t0)
	lw	s1, -28(s0)
	mv	a0, zero
	j	.put_RETURN
.put_L2:
	lw	t2, -20(s0)
	lw	t0, table
	li	t1, 4
	mul	t1, t2, t1
	add	t0, t0, t1
	lw	t0, 0(t0)
	sw	t0, -24(s0)
	j	.put_L3
.put_L3:
	lw	t0, -24(s0)
	addi	t0, t0, 0
	lw	t1, 0(t0)
	lw	t0, -12(s0)
	xor	t0, t1, t0
	snez	t0, t0
	beqz	t0,	.put_L5
	j	.put_L4
.put_L4:
	lw	t0, -24(s0)
	addi	t0, t0, 8
	lw	t0, 0(t0)
	xor	t0, t0, zero
	seqz	t0, t0
	beqz	t0,	.put_L7
	j	.put_L6
.put_L6:
	lw	t0, -24(s0)
	addi	s1, t0, 8
	li	a0, 16
	call	_f_malloc
	sw	a0, 0(s1)
	lw	t0, -24(s0)
	addi	t0, t0, 8
	lw	t0, 0(t0)
	addi	t0, t0, 0
	lw	t1, -12(s0)
	sw	t1, 0(t0)
	lw	t0, -24(s0)
	addi	t0, t0, 8
	lw	t0, 0(t0)
	addi	t0, t0, 8
	sw	zero, 0(t0)
	j	.put_L7
.put_L7:
	lw	t0, -24(s0)
	addi	t0, t0, 8
	lw	t0, 0(t0)
	sw	t0, -24(s0)
	j	.put_L3
.put_L5:
	lw	t0, -24(s0)
	addi	t0, t0, 4
	lw	t1, -16(s0)
	sw	t1, 0(t0)
	lw	s1, -28(s0)
	mv	a0, zero
	j	.put_RETURN
.put_RETURN:
	lw	s0, 32(sp)
	lw	ra, 36(sp)
	addi	sp, sp, 40
	ret

	.globl	get
	.p2align	2
	.type	get,@function
get:
.get_INIT:
	addi	sp, sp, -28
	sw	ra, 24(sp)
	sw	s0, 20(sp)
	addi	s0, sp, 28
	j	.get_L0
.get_L0:
	sw	a0, -12(s0)
	sw	zero, -16(s0)
	lw	a0, -12(s0)
	call	getHash
	lw	t0, table
	li	t1, 4
	mul	t1, a0, t1
	add	t0, t0, t1
	lw	t0, 0(t0)
	sw	t0, -16(s0)
	j	.get_L1
.get_L1:
	lw	t0, -16(s0)
	addi	t0, t0, 0
	lw	t1, 0(t0)
	lw	t0, -12(s0)
	xor	t0, t1, t0
	snez	t0, t0
	beqz	t0,	.get_L3
	j	.get_L2
.get_L2:
	lw	t0, -16(s0)
	addi	t0, t0, 8
	lw	t0, 0(t0)
	sw	t0, -16(s0)
	j	.get_L1
.get_L3:
	lw	t0, -16(s0)
	addi	t0, t0, 4
	lw	a0, 0(t0)
	j	.get_RETURN
.get_RETURN:
	lw	s0, 20(sp)
	lw	ra, 24(sp)
	addi	sp, sp, 28
	ret

	.globl	main
	.p2align	2
	.type	main,@function
main:
.main_INIT:
	addi	sp, sp, -32
	sw	ra, 28(sp)
	sw	s0, 24(sp)
	addi	s0, sp, 32
	sw	s1, -16(s0)
	sw	s2, -20(s0)
	j	.main_L0
.main_L0:
	call	_INIT_
	li	t1, 100
	li	t0, 4
	mul	t1, t1, t0
	li	t0, 4
	add	a0, t1, t0
	call	_f_malloc
	li	t0, 100
	sw	t0, 0(a0)
	li	t0, 4
	li	t1, 1
	mul	t0, t1, t0
	add	t1, a0, t0
	la	t0, table
	sw	t1, 0(t0)
	sw	zero, -12(s0)
	j	.main_L1
.main_L1:
	lw	t0, -12(s0)
	lw	t1, hashsize
	slt	t0, t0, t1
	beqz	t0,	.main_L4
	j	.main_L2
.main_L2:
	lw	t1, -12(s0)
	lw	t0, table
	li	t2, 4
	mul	t2, t1, t2
	add	t0, t0, t2
	sw	zero, 0(t0)
	j	.main_L3
.main_L3:
	lw	t0, -12(s0)
	li	t1, 1
	add	t0, t0, t1
	sw	t0, -12(s0)
	j	.main_L1
.main_L4:
	sw	zero, -12(s0)
	j	.main_L5
.main_L5:
	lw	t0, -12(s0)
	li	t1, 1000
	slt	t0, t0, t1
	beqz	t0,	.main_L8
	j	.main_L6
.main_L6:
	lw	a0, -12(s0)
	lw	a1, -12(s0)
	call	put
	j	.main_L7
.main_L7:
	lw	t0, -12(s0)
	li	t1, 1
	add	t0, t0, t1
	sw	t0, -12(s0)
	j	.main_L5
.main_L8:
	sw	zero, -12(s0)
	j	.main_L9
.main_L9:
	lw	t0, -12(s0)
	li	t1, 1000
	slt	t0, t0, t1
	beqz	t0,	.main_L12
	j	.main_L10
.main_L10:
	lw	a0, -12(s0)
	call	toString
	la	t0, str1
	add	s2, a0, t0
	lw	a0, -12(s0)
	call	get
	call	toString
	mv	s1, a0
	mv	a0, s2
	mv	a1, s1
	call	_str_concatenate
	add	a0, s2, s1
	call	println
	j	.main_L11
.main_L11:
	lw	t0, -12(s0)
	li	t1, 1
	add	t0, t0, t1
	sw	t0, -12(s0)
	j	.main_L9
.main_L12:
	lw	s1, -16(s0)
	lw	s2, -20(s0)
	mv	a0, zero
	j	.main_RETURN
.main_RETURN:
	lw	s0, 24(sp)
	lw	ra, 28(sp)
	addi	sp, sp, 32
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
	la	t0, hashsize
	li	t1, 100
	sw	t1, 0(t0)
	mv	a0, zero
	j	._INIT__RETURN
._INIT__RETURN:
	lw	s0, 12(sp)
	lw	ra, 16(sp)
	addi	sp, sp, 20
	ret


	.type	hashsize,@object
	.section	.sbss
	.globl	hashsize
	.p2align	2
hashsize:
	.word	0
	.size	hashsize, 4

	.type	table,@object
	.section	.sbss
	.globl	table
	.p2align	2
table:
	.word	0
	.size	table, 4

	.type	str1,@object
	.section	.rodata
str1:
	.asciz	" "
	.size	str1, 1

