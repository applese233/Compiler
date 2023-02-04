	.text

	.global	main
	.p2align	2
	.type	main,@function
main:
.main_INIT:
	addi	sp, sp, -264
	sw	ra, 260(sp)
	sw	s0, 256(sp)
	addi	s0, sp, 264
	addi	sp, sp, -24
	sw	ra, 20(sp)
	sw	s0, 16(sp)
	addi	s0, sp, 24
	mv	t5, s0
	sw	t5, -16(s0)
	mv	t5, s1
	sw	t5, -20(s0)
	mv	t5, s2
	sw	t5, -24(s0)
	mv	t5, s3
	sw	t5, -28(s0)
	mv	t5, s4
	sw	t5, -32(s0)
	mv	t5, s5
	sw	t5, -36(s0)
	mv	t5, s6
	sw	t5, -40(s0)
	mv	t5, s7
	sw	t5, -44(s0)
	mv	t5, s8
	sw	t5, -48(s0)
	mv	t5, s9
	sw	t5, -52(s0)
	mv	t5, s10
	sw	t5, -56(s0)
	mv	t5, s11
	sw	t5, -60(s0)
	j	.main_L0
.main_L0:
	call	_INIT_
	call	getInt
	mv	t5, a0
	sw	t5, -64(s0)
	la	t5, n
	sw	t5, -68(s0)
	lw	t3, -68(s0)
	lw	t4, -64(s0)
	sw	t4, 0(t3)
	call	getInt
	mv	t5, a0
	sw	t5, -72(s0)
	la	t5, p
	sw	t5, -76(s0)
	lw	t3, -76(s0)
	lw	t4, -72(s0)
	sw	t4, 0(t3)
	call	getInt
	mv	t5, a0
	sw	t5, -80(s0)
	la	t5, k
	sw	t5, -84(s0)
	lw	t3, -84(s0)
	lw	t4, -80(s0)
	sw	t4, 0(t3)
	lw	t5, p
	sw	t5, -88(s0)
	lw	t5, k
	sw	t5, -92(s0)
	lw	t3, -88(s0)
	lw	t4, -92(s0)
	sub	t5, t3, t4
	sw	t5, -96(s0)
	li	t5, 1
	sw	t5, -100(s0)
	lw	t3, -96(s0)
	lw	t4, -100(s0)
	sgt	t5, t3, t4
	sw	t5, -104(s0)
	lw	t3, -104(s0)
	beqz	t3,	.main_L2
	j	.main_L1
.main_L1:
	la	t5, str1
	sw	t5, -108(s0)
	lw	t3, -108(s0)
	mv	a0, t3
	call	print
	j	.main_L2
.main_L2:
	lw	t5, p
	sw	t5, -112(s0)
	lw	t5, k
	sw	t5, -116(s0)
	lw	t3, -112(s0)
	lw	t4, -116(s0)
	sub	t5, t3, t4
	sw	t5, -120(s0)
	la	t5, i
	sw	t5, -124(s0)
	lw	t3, -124(s0)
	lw	t4, -120(s0)
	sw	t4, 0(t3)
	j	.main_L3
.main_L3:
	lw	t5, p
	sw	t5, -128(s0)
	lw	t5, k
	sw	t5, -132(s0)
	lw	t3, -128(s0)
	lw	t4, -132(s0)
	add	t5, t3, t4
	sw	t5, -136(s0)
	lw	t5, i
	sw	t5, -140(s0)
	lw	t3, -140(s0)
	lw	t4, -136(s0)
	sgt	t5, t3, t4
	sw	t5, -144(s0)
	lw	t3, -144(s0)
	xori	t5, t3, 1
	sw	t5, -144(s0)
	lw	t3, -144(s0)
	beqz	t3,	.main_L6
	j	.main_L4
.main_L4:
	lw	t5, i
	sw	t5, -148(s0)
	li	t5, 1
	sw	t5, -152(s0)
	lw	t3, -152(s0)
	lw	t4, -148(s0)
	sgt	t5, t3, t4
	sw	t5, -156(s0)
	lw	t3, -156(s0)
	xori	t5, t3, 1
	sw	t5, -156(s0)
	lw	t3, -156(s0)
	beqz	t3,	.main_L8
	j	.main_L9
.main_L8:
	sw	zero, -12(s0)
	j	.main_L7
.main_L9:
	lw	t5, i
	sw	t5, -160(s0)
	lw	t5, n
	sw	t5, -164(s0)
	lw	t3, -160(s0)
	lw	t4, -164(s0)
	sgt	t5, t3, t4
	sw	t5, -168(s0)
	lw	t3, -168(s0)
	xori	t5, t3, 1
	sw	t5, -168(s0)
	lw	t4, -168(s0)
	sw	t4, -12(s0)
	j	.main_L7
.main_L7:
	lw	t5, -12(s0)
	sw	t5, -172(s0)
	lw	t3, -172(s0)
	beqz	t3,	.main_L11
	j	.main_L10
.main_L10:
	lw	t5, i
	sw	t5, -176(s0)
	lw	t5, p
	sw	t5, -180(s0)
	lw	t3, -176(s0)
	lw	t4, -180(s0)
	xor	t5, t3, t4
	sw	t5, -184(s0)
	lw	t3, -184(s0)
	seqz	t5, t3
	sw	t5, -188(s0)
	lw	t3, -188(s0)
	beqz	t3,	.main_L13
	j	.main_L12
.main_L12:
	la	t5, str2
	sw	t5, -192(s0)
	lw	t3, -192(s0)
	mv	a0, t3
	call	print
	lw	t5, i
	sw	t5, -196(s0)
	lw	t3, -196(s0)
	mv	a0, t3
	call	toString
	mv	t5, a0
	sw	t5, -200(s0)
	lw	t3, -200(s0)
	mv	a0, t3
	call	print
	la	t5, str3
	sw	t5, -204(s0)
	lw	t3, -204(s0)
	mv	a0, t3
	call	print
	j	.main_L14
.main_L13:
	lw	t5, i
	sw	t5, -208(s0)
	lw	t3, -208(s0)
	mv	a0, t3
	call	printInt
	la	t5, str4
	sw	t5, -212(s0)
	lw	t3, -212(s0)
	mv	a0, t3
	call	print
	j	.main_L14
.main_L14:
	j	.main_L11
.main_L11:
	j	.main_L5
.main_L5:
	lw	t5, i
	sw	t5, -216(s0)
	li	t5, 1
	sw	t5, -220(s0)
	lw	t3, -216(s0)
	lw	t4, -220(s0)
	add	t5, t3, t4
	sw	t5, -224(s0)
	la	t5, i
	sw	t5, -228(s0)
	lw	t3, -228(s0)
	lw	t4, -224(s0)
	sw	t4, 0(t3)
	j	.main_L3
.main_L6:
	lw	t5, p
	sw	t5, -232(s0)
	lw	t5, k
	sw	t5, -236(s0)
	lw	t3, -232(s0)
	lw	t4, -236(s0)
	add	t5, t3, t4
	sw	t5, -240(s0)
	lw	t5, n
	sw	t5, -244(s0)
	lw	t3, -240(s0)
	lw	t4, -244(s0)
	slt	t5, t3, t4
	sw	t5, -248(s0)
	lw	t3, -248(s0)
	beqz	t3,	.main_L16
	j	.main_L15
.main_L15:
	la	t5, str5
	sw	t5, -252(s0)
	lw	t3, -252(s0)
	mv	a0, t3
	call	print
	j	.main_L16
.main_L16:
	lw	t3, -16(s0)
	mv	s0, t3
	lw	t3, -20(s0)
	mv	s1, t3
	lw	t3, -24(s0)
	mv	s2, t3
	lw	t3, -28(s0)
	mv	s3, t3
	lw	t3, -32(s0)
	mv	s4, t3
	lw	t3, -36(s0)
	mv	s5, t3
	lw	t3, -40(s0)
	mv	s6, t3
	lw	t3, -44(s0)
	mv	s7, t3
	lw	t3, -48(s0)
	mv	s8, t3
	lw	t3, -52(s0)
	mv	s9, t3
	lw	t3, -56(s0)
	mv	s10, t3
	lw	t3, -60(s0)
	mv	s11, t3
	mv	a0, zero
	j	.main_RETURN
.main_RETURN:
	lw	s0, 16(sp)
	lw	ra, 20(sp)
	addi	sp, sp, 24
	ret
	lw	s0, 256(sp)
	lw	ra, 260(sp)
	addi	sp, sp, 264
	ret

	.global	_INIT_
	.p2align	2
	.type	_INIT_,@function
_INIT_:
._INIT__INIT:
	addi	sp, sp, -68
	sw	ra, 64(sp)
	sw	s0, 60(sp)
	addi	s0, sp, 68
	addi	sp, sp, -20
	sw	ra, 16(sp)
	sw	s0, 12(sp)
	addi	s0, sp, 20
	mv	t5, s0
	sw	t5, -12(s0)
	mv	t5, s1
	sw	t5, -16(s0)
	mv	t5, s2
	sw	t5, -20(s0)
	mv	t5, s3
	sw	t5, -24(s0)
	mv	t5, s4
	sw	t5, -28(s0)
	mv	t5, s5
	sw	t5, -32(s0)
	mv	t5, s6
	sw	t5, -36(s0)
	mv	t5, s7
	sw	t5, -40(s0)
	mv	t5, s8
	sw	t5, -44(s0)
	mv	t5, s9
	sw	t5, -48(s0)
	mv	t5, s10
	sw	t5, -52(s0)
	mv	t5, s11
	sw	t5, -56(s0)
	j	._INIT__L0
._INIT__L0:
	lw	t3, -12(s0)
	mv	s0, t3
	lw	t3, -16(s0)
	mv	s1, t3
	lw	t3, -20(s0)
	mv	s2, t3
	lw	t3, -24(s0)
	mv	s3, t3
	lw	t3, -28(s0)
	mv	s4, t3
	lw	t3, -32(s0)
	mv	s5, t3
	lw	t3, -36(s0)
	mv	s6, t3
	lw	t3, -40(s0)
	mv	s7, t3
	lw	t3, -44(s0)
	mv	s8, t3
	lw	t3, -48(s0)
	mv	s9, t3
	lw	t3, -52(s0)
	mv	s10, t3
	lw	t3, -56(s0)
	mv	s11, t3
	mv	a0, zero
	j	._INIT__RETURN
._INIT__RETURN:
	lw	s0, 12(sp)
	lw	ra, 16(sp)
	addi	sp, sp, 20
	ret
	lw	s0, 60(sp)
	lw	ra, 64(sp)
	addi	sp, sp, 68
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

