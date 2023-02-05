	.text

	.globl	qpow
	.p2align	2
	.type	qpow,@function
qpow:
.qpow_INIT:
	addi	sp, sp, -40
	sw	ra, 36(sp)
	sw	s0, 32(sp)
	addi	s0, sp, 40
	j	.qpow_L0
.qpow_L0:
	sw	a0, -12(s0)
	sw	a1, -16(s0)
	sw	a2, -20(s0)
	li	t0, 1
	sw	t0, -24(s0)
	lw	t0, -12(s0)
	sw	t0, -28(s0)
	j	.qpow_L1
.qpow_L1:
	lw	t0, -16(s0)
	sgt	t0, t0, zero
	beqz	t0,	.qpow_L3
	j	.qpow_L2
.qpow_L2:
	lw	t1, -16(s0)
	li	t0, 1
	and	t1, t1, t0
	li	t0, 1
	xor	t0, t1, t0
	seqz	t0, t0
	beqz	t0,	.qpow_L5
	j	.qpow_L4
.qpow_L4:
	lw	t1, -24(s0)
	lw	t0, -28(s0)
	mul	t0, t1, t0
	lw	t1, -20(s0)
	rem	t0, t0, t1
	sw	t0, -24(s0)
	j	.qpow_L5
.qpow_L5:
	lw	t1, -28(s0)
	lw	t0, -28(s0)
	mul	t1, t1, t0
	lw	t0, -20(s0)
	rem	t0, t1, t0
	sw	t0, -28(s0)
	lw	t1, -16(s0)
	li	t0, 2
	div	t0, t1, t0
	sw	t0, -16(s0)
	j	.qpow_L1
.qpow_L3:
	lw	a0, -24(s0)
	j	.qpow_RETURN
.qpow_RETURN:
	lw	s0, 32(sp)
	lw	ra, 36(sp)
	addi	sp, sp, 40
	ret

	.globl	main
	.p2align	2
	.type	main,@function
main:
.main_INIT:
	addi	sp, sp, -20
	sw	ra, 16(sp)
	sw	s0, 12(sp)
	addi	s0, sp, 20
	j	.main_L0
.main_L0:
	call	_INIT_
	li	a0, 2
	li	a1, 10
	li	a2, 10000
	call	qpow
	call	toString
	call	println
	mv	a0, zero
	j	.main_RETURN
.main_RETURN:
	lw	s0, 12(sp)
	lw	ra, 16(sp)
	addi	sp, sp, 20
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


