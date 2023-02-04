	.text

	.globl	main
	.p2align	2
	.type	main,@function
main:
.main_INIT:
	addi	sp, sp, -20
	sw	ra, 16(sp)
	sw	s0, 12(sp)
	addi	s0, sp, 20
	mv	VR_EX_0, s0
	mv	VR_EX_1, s1
	mv	VR_EX_2, s2
	mv	VR_EX_3, s3
	mv	VR_EX_4, s4
	mv	VR_EX_5, s5
	mv	VR_EX_6, s6
	mv	VR_EX_7, s7
	mv	VR_EX_8, s8
	mv	VR_EX_9, s9
	mv	VR_EX_10, s10
	mv	VR_EX_11, s11
	j	.main_L0
.main_L0:
	call	_INIT_
	mv	s0, VR_EX_0
	mv	s1, VR_EX_1
	mv	s2, VR_EX_2
	mv	s3, VR_EX_3
	mv	s4, VR_EX_4
	mv	s5, VR_EX_5
	mv	s6, VR_EX_6
	mv	s7, VR_EX_7
	mv	s8, VR_EX_8
	mv	s9, VR_EX_9
	mv	s10, VR_EX_10
	mv	s11, VR_EX_11
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
	mv	VR_EX_13, s0
	mv	VR_EX_14, s1
	mv	VR_EX_15, s2
	mv	VR_EX_16, s3
	mv	VR_EX_17, s4
	mv	VR_EX_18, s5
	mv	VR_EX_19, s6
	mv	VR_EX_20, s7
	mv	VR_EX_21, s8
	mv	VR_EX_22, s9
	mv	VR_EX_23, s10
	mv	VR_EX_24, s11
	j	._INIT__L0
._INIT__L0:
	mv	s0, VR_EX_13
	mv	s1, VR_EX_14
	mv	s2, VR_EX_15
	mv	s3, VR_EX_16
	mv	s4, VR_EX_17
	mv	s5, VR_EX_18
	mv	s6, VR_EX_19
	mv	s7, VR_EX_20
	mv	s8, VR_EX_21
	mv	s9, VR_EX_22
	mv	s10, VR_EX_23
	mv	s11, VR_EX_24
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

