	.text

	.global	main
	.p2align	2
	.type	main,@function
main:
.main_INIT:
	addi	sp, sp, -24
	sw	ra, 20(sp)
	sw	s0, 16(sp)
	addi	s0, sp, 24
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
	call	getInt
	mv	VR0, a0
	la	VR_EX_12, n
	sw	VR0, 0(VR_EX_12)
	call	getInt
	mv	VR1, a0
	la	VR_EX_13, p
	sw	VR1, 0(VR_EX_13)
	call	getInt
	mv	VR2, a0
	la	VR_EX_14, k
	sw	VR2, 0(VR_EX_14)
	lw	VR3, p
	lw	VR4, k
	sub	VR5, VR3, VR4
	li	VR_EX_15, 1
	sgt	VR6, VR5, VR_EX_15
	beqz	VR6,	.main_L2
	j	.main_L1
.main_L1:
	la	VR_EX_16, str1
	mv	a0, VR_EX_16
	call	print
	j	.main_L2
.main_L2:
	lw	VR8, p
	lw	VR9, k
	sub	VR10, VR8, VR9
	la	VR_EX_17, i
	sw	VR10, 0(VR_EX_17)
	j	.main_L3
.main_L3:
	lw	VR11, p
	lw	VR12, k
	add	VR13, VR11, VR12
	lw	VR14, i
	sgt	VR15, VR14, VR13
	xori	VR15, VR15, 1
	beqz	VR15,	.main_L6
	j	.main_L4
.main_L4:
	lw	VR16, i
	li	VR_EX_18, 1
	sgt	VR17, VR_EX_18, VR16
	xori	VR17, VR17, 1
	beqz	VR17,	.main_L8
	j	.main_L9
.main_L8:
	sw	zero, -12(s0)
	j	.main_L7
.main_L9:
	lw	VR19, i
	lw	VR20, n
	sgt	VR21, VR19, VR20
	xori	VR21, VR21, 1
	sw	VR21, -12(s0)
	j	.main_L7
.main_L7:
	lw	VR22, -12(s0)
	beqz	VR22,	.main_L11
	j	.main_L10
.main_L10:
	lw	VR23, i
	lw	VR24, p
	xor	VR_EX_20, VR23, VR24
	seqz	VR25, VR_EX_20
	beqz	VR25,	.main_L13
	j	.main_L12
.main_L12:
	la	VR_EX_21, str2
	mv	a0, VR_EX_21
	call	print
	lw	VR27, i
	mv	a0, VR27
	call	toString
	mv	VR28, a0
	mv	a0, VR28
	call	print
	la	VR_EX_22, str3
	mv	a0, VR_EX_22
	call	print
	j	.main_L14
.main_L13:
	lw	VR30, i
	mv	a0, VR30
	call	printInt
	la	VR_EX_23, str4
	mv	a0, VR_EX_23
	call	print
	j	.main_L14
.main_L14:
	j	.main_L11
.main_L11:
	j	.main_L5
.main_L5:
	lw	VR32, i
	li	VR_EX_24, 1
	add	VR33, VR32, VR_EX_24
	la	VR_EX_25, i
	sw	VR33, 0(VR_EX_25)
	j	.main_L3
.main_L6:
	lw	VR34, p
	lw	VR35, k
	add	VR36, VR34, VR35
	lw	VR37, n
	slt	VR38, VR36, VR37
	beqz	VR38,	.main_L16
	j	.main_L15
.main_L15:
	la	VR_EX_26, str5
	mv	a0, VR_EX_26
	call	print
	j	.main_L16
.main_L16:
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
	lw	s0, 16(sp)
	lw	ra, 20(sp)
	addi	sp, sp, 24
	ret

	.global	_INIT_
	.p2align	2
	.type	_INIT_,@function
_INIT_:
._INIT__INIT:
	addi	sp, sp, -20
	sw	ra, 16(sp)
	sw	s0, 12(sp)
	addi	s0, sp, 20
	mv	VR_EX_28, s0
	mv	VR_EX_29, s1
	mv	VR_EX_30, s2
	mv	VR_EX_31, s3
	mv	VR_EX_32, s4
	mv	VR_EX_33, s5
	mv	VR_EX_34, s6
	mv	VR_EX_35, s7
	mv	VR_EX_36, s8
	mv	VR_EX_37, s9
	mv	VR_EX_38, s10
	mv	VR_EX_39, s11
	j	._INIT__L0
._INIT__L0:
	mv	s0, VR_EX_28
	mv	s1, VR_EX_29
	mv	s2, VR_EX_30
	mv	s3, VR_EX_31
	mv	s4, VR_EX_32
	mv	s5, VR_EX_33
	mv	s6, VR_EX_34
	mv	s7, VR_EX_35
	mv	s8, VR_EX_36
	mv	s9, VR_EX_37
	mv	s10, VR_EX_38
	mv	s11, VR_EX_39
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

