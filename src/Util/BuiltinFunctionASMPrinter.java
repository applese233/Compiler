package Util;

import java.io.FileOutputStream;
import java.io.IOException;

public class BuiltinFunctionASMPrinter {
    String builtin_s_as_literal = """
\t.text
\t.file	"builtin.c"
\t.globl	print                   # -- Begin function print
\t.p2align	2
\t.type	print,@function
print:                                  # @print
\t.cfi_startproc
# %bb.0:
\taddi	sp, sp, -16
\t.cfi_def_cfa_offset 16
\tsw	ra, 12(sp)
\t.cfi_offset ra, -4
\tsw	a0, 8(sp)
\tcall	printf
\tlw	ra, 12(sp)
\taddi	sp, sp, 16
\tret
.Lfunc_end0:
\t.size	print, .Lfunc_end0-print
\t.cfi_endproc

\t.globl	println                 # -- Begin function println
\t.p2align	2
\t.type	println,@function
println:                                # @println
\t.cfi_startproc
# %bb.0:
\taddi	sp, sp, -16
\t.cfi_def_cfa_offset 16
\tsw	ra, 12(sp)
\t.cfi_offset ra, -4
\tmv	a1, a0
\tsw	a0, 8(sp)
\tlui	a0, %hi(.L.str)
\taddi	a0, a0, %lo(.L.str)
\tcall	printf
\tlw	ra, 12(sp)
\taddi	sp, sp, 16
\tret
.Lfunc_end1:
\t.size	println, .Lfunc_end1-println
\t.cfi_endproc

\t.globl	printInt                # -- Begin function printInt
\t.p2align	2
\t.type	printInt,@function
printInt:                               # @printInt
\t.cfi_startproc
# %bb.0:
\taddi	sp, sp, -16
\t.cfi_def_cfa_offset 16
\tsw	ra, 12(sp)
\t.cfi_offset ra, -4
\tmv	a1, a0
\tsw	a0, 8(sp)
\tlui	a0, %hi(.L.str.1)
\taddi	a0, a0, %lo(.L.str.1)
\tcall	printf
\tlw	ra, 12(sp)
\taddi	sp, sp, 16
\tret
.Lfunc_end2:
\t.size	printInt, .Lfunc_end2-printInt
\t.cfi_endproc

\t.globl	printlnInt              # -- Begin function printlnInt
\t.p2align	2
\t.type	printlnInt,@function
printlnInt:                             # @printlnInt
\t.cfi_startproc
# %bb.0:
\taddi	sp, sp, -16
\t.cfi_def_cfa_offset 16
\tsw	ra, 12(sp)
\t.cfi_offset ra, -4
\tmv	a1, a0
\tsw	a0, 8(sp)
\tlui	a0, %hi(.L.str.2)
\taddi	a0, a0, %lo(.L.str.2)
\tcall	printf
\tlw	ra, 12(sp)
\taddi	sp, sp, 16
\tret
.Lfunc_end3:
\t.size	printlnInt, .Lfunc_end3-printlnInt
\t.cfi_endproc

\t.globl	getInt                  # -- Begin function getInt
\t.p2align	2
\t.type	getInt,@function
getInt:                                 # @getInt
\t.cfi_startproc
# %bb.0:
\taddi	sp, sp, -16
\t.cfi_def_cfa_offset 16
\tsw	ra, 12(sp)
\t.cfi_offset ra, -4
\tlui	a0, %hi(.L.str.1)
\taddi	a0, a0, %lo(.L.str.1)
\taddi	a1, sp, 8
\tcall	__isoc99_scanf
\tlw	a0, 8(sp)
\tlw	ra, 12(sp)
\taddi	sp, sp, 16
\tret
.Lfunc_end4:
\t.size	getInt, .Lfunc_end4-getInt
\t.cfi_endproc

\t.globl	toString                # -- Begin function toString
\t.p2align	2
\t.type	toString,@function
toString:                               # @toString
\t.cfi_startproc
# %bb.0:
\taddi	sp, sp, -16
\t.cfi_def_cfa_offset 16
\tsw	ra, 12(sp)
\t.cfi_offset ra, -4
\tsw	a0, 8(sp)
\taddi	a0, zero, 13
\tmv	a1, zero
\tcall	malloc
\tlw	a2, 8(sp)
\tsw	a0, 0(sp)
\tlui	a1, %hi(.L.str.1)
\taddi	a1, a1, %lo(.L.str.1)
\tcall	sprintf
\tlw	a0, 0(sp)
\tlw	ra, 12(sp)
\taddi	sp, sp, 16
\tret
.Lfunc_end5:
\t.size	toString, .Lfunc_end5-toString
\t.cfi_endproc

\t.globl	getString               # -- Begin function getString
\t.p2align	2
\t.type	getString,@function
getString:                              # @getString
\t.cfi_startproc
# %bb.0:
\taddi	sp, sp, -16
\t.cfi_def_cfa_offset 16
\tsw	ra, 12(sp)
\t.cfi_offset ra, -4
\taddi	a0, zero, 1024
\tmv	a1, zero
\tcall	malloc
\tmv	a1, a0
\tsw	a0, 8(sp)
\tlui	a0, %hi(.L.str.3)
\taddi	a0, a0, %lo(.L.str.3)
\tcall	__isoc99_scanf
\tlw	a0, 8(sp)
\tlw	ra, 12(sp)
\taddi	sp, sp, 16
\tret
.Lfunc_end6:
\t.size	getString, .Lfunc_end6-getString
\t.cfi_endproc

\t.globl	_str_ord                # -- Begin function _str_ord
\t.p2align	2
\t.type	_str_ord,@function
_str_ord:                               # @_str_ord
\t.cfi_startproc
# %bb.0:
\taddi	sp, sp, -16
\t.cfi_def_cfa_offset 16
\tsw	a0, 8(sp)
\tsw	a1, 4(sp)
\tadd	a0, a0, a1
\tlb	a0, 0(a0)
\taddi	sp, sp, 16
\tret
.Lfunc_end7:
\t.size	_str_ord, .Lfunc_end7-_str_ord
\t.cfi_endproc

\t.globl	_str_eq                 # -- Begin function _str_eq
\t.p2align	2
\t.type	_str_eq,@function
_str_eq:                                # @_str_eq
\t.cfi_startproc
# %bb.0:
\taddi	sp, sp, -16
\t.cfi_def_cfa_offset 16
\tsw	ra, 12(sp)
\t.cfi_offset ra, -4
\tsw	a0, 8(sp)
\tsw	a1, 0(sp)
\tcall	strcmp
\tseqz	a0, a0
\tlw	ra, 12(sp)
\taddi	sp, sp, 16
\tret
.Lfunc_end8:
\t.size	_str_eq, .Lfunc_end8-_str_eq
\t.cfi_endproc

\t.globl	_str_ne                 # -- Begin function _str_ne
\t.p2align	2
\t.type	_str_ne,@function
_str_ne:                                # @_str_ne
\t.cfi_startproc
# %bb.0:
\taddi	sp, sp, -16
\t.cfi_def_cfa_offset 16
\tsw	ra, 12(sp)
\t.cfi_offset ra, -4
\tsw	a0, 8(sp)
\tsw	a1, 0(sp)
\tcall	strcmp
\tsnez	a0, a0
\tlw	ra, 12(sp)
\taddi	sp, sp, 16
\tret
.Lfunc_end9:
\t.size	_str_ne, .Lfunc_end9-_str_ne
\t.cfi_endproc

\t.globl	_str_lt                 # -- Begin function _str_lt
\t.p2align	2
\t.type	_str_lt,@function
_str_lt:                                # @_str_lt
\t.cfi_startproc
# %bb.0:
\taddi	sp, sp, -16
\t.cfi_def_cfa_offset 16
\tsw	ra, 12(sp)
\t.cfi_offset ra, -4
\tsw	a0, 8(sp)
\tsw	a1, 0(sp)
\tcall	strcmp
\tsrli	a0, a0, 31
\tlw	ra, 12(sp)
\taddi	sp, sp, 16
\tret
.Lfunc_end10:
\t.size	_str_lt, .Lfunc_end10-_str_lt
\t.cfi_endproc

\t.globl	_str_le                 # -- Begin function _str_le
\t.p2align	2
\t.type	_str_le,@function
_str_le:                                # @_str_le
\t.cfi_startproc
# %bb.0:
\taddi	sp, sp, -16
\t.cfi_def_cfa_offset 16
\tsw	ra, 12(sp)
\t.cfi_offset ra, -4
\tsw	a0, 8(sp)
\tsw	a1, 0(sp)
\tcall	strcmp
\tslti	a0, a0, 1
\tlw	ra, 12(sp)
\taddi	sp, sp, 16
\tret
.Lfunc_end11:
\t.size	_str_le, .Lfunc_end11-_str_le
\t.cfi_endproc

\t.globl	_str_gt                 # -- Begin function _str_gt
\t.p2align	2
\t.type	_str_gt,@function
_str_gt:                                # @_str_gt
\t.cfi_startproc
# %bb.0:
\taddi	sp, sp, -16
\t.cfi_def_cfa_offset 16
\tsw	ra, 12(sp)
\t.cfi_offset ra, -4
\tsw	a0, 8(sp)
\tsw	a1, 0(sp)
\tcall	strcmp
\tsgtz	a0, a0
\tlw	ra, 12(sp)
\taddi	sp, sp, 16
\tret
.Lfunc_end12:
\t.size	_str_gt, .Lfunc_end12-_str_gt
\t.cfi_endproc

\t.globl	_str_ge                 # -- Begin function _str_ge
\t.p2align	2
\t.type	_str_ge,@function
_str_ge:                                # @_str_ge
\t.cfi_startproc
# %bb.0:
\taddi	sp, sp, -16
\t.cfi_def_cfa_offset 16
\tsw	ra, 12(sp)
\t.cfi_offset ra, -4
\tsw	a0, 8(sp)
\tsw	a1, 0(sp)
\tcall	strcmp
\tnot	a0, a0
\tsrli	a0, a0, 31
\tlw	ra, 12(sp)
\taddi	sp, sp, 16
\tret
.Lfunc_end13:
\t.size	_str_ge, .Lfunc_end13-_str_ge
\t.cfi_endproc

\t.globl	_str_concatenate        # -- Begin function _str_concatenate
\t.p2align	2
\t.type	_str_concatenate,@function
_str_concatenate:                       # @_str_concatenate
\t.cfi_startproc
# %bb.0:
\taddi	sp, sp, -32
\t.cfi_def_cfa_offset 32
\tsw	ra, 28(sp)
\t.cfi_offset ra, -4
\tsw	a0, 24(sp)
\tsw	a1, 16(sp)
\taddi	a0, zero, 1024
\tmv	a1, zero
\tcall	malloc
\tlw	a1, 24(sp)
\tsw	a0, 8(sp)
\tcall	strcpy
\tlw	a0, 8(sp)
\tlw	a1, 16(sp)
\tcall	strcat
\tlw	a0, 8(sp)
\tlw	ra, 28(sp)
\taddi	sp, sp, 32
\tret
.Lfunc_end14:
\t.size	_str_concatenate, .Lfunc_end14-_str_concatenate
\t.cfi_endproc

\t.globl	_f_malloc               # -- Begin function _f_malloc
\t.p2align	2
\t.type	_f_malloc,@function
_f_malloc:                              # @_f_malloc
\t.cfi_startproc
# %bb.0:
\taddi	sp, sp, -16
\t.cfi_def_cfa_offset 16
\tsw	ra, 12(sp)
\t.cfi_offset ra, -4
\tsw	a0, 8(sp)
\tsrai	a1, a0, 31
\tcall	malloc
\tlw	ra, 12(sp)
\taddi	sp, sp, 16
\tret
.Lfunc_end15:
\t.size	_f_malloc, .Lfunc_end15-_f_malloc
\t.cfi_endproc

\t.globl	_str_length             # -- Begin function _str_length
\t.p2align	2
\t.type	_str_length,@function
_str_length:                            # @_str_length
\t.cfi_startproc
# %bb.0:
\taddi	sp, sp, -16
\t.cfi_def_cfa_offset 16
\tsw	ra, 12(sp)
\t.cfi_offset ra, -4
\tsw	a0, 8(sp)
\tcall	strlen
\tlw	ra, 12(sp)
\taddi	sp, sp, 16
\tret
.Lfunc_end16:
\t.size	_str_length, .Lfunc_end16-_str_length
\t.cfi_endproc

\t.globl	_str_substring          # -- Begin function _str_substring
\t.p2align	2
\t.type	_str_substring,@function
_str_substring:                         # @_str_substring
\t.cfi_startproc
# %bb.0:
\taddi	sp, sp, -32
\t.cfi_def_cfa_offset 32
\tsw	ra, 28(sp)
\t.cfi_offset ra, -4
\tsw	a0, 24(sp)
\tsw	a1, 20(sp)
\tsw	a2, 16(sp)
\tsub	a0, a2, a1
\taddi	a0, a0, 1
\tsrai	a1, a0, 31
\tcall	malloc
\tlw	a1, 24(sp)
\tlw	a2, 20(sp)
\tlw	a3, 16(sp)
\tsw	a0, 8(sp)
\tadd	a1, a1, a2
\tsub	a2, a3, a2
\tcall	memcpy
\tlw	a0, 16(sp)
\tlw	a1, 20(sp)
\tlw	a2, 8(sp)
\tsub	a0, a0, a1
\tadd	a0, a2, a0
\tsb	zero, 0(a0)
\tlw	a0, 8(sp)
\tlw	ra, 28(sp)
\taddi	sp, sp, 32
\tret
.Lfunc_end17:
\t.size	_str_substring, .Lfunc_end17-_str_substring
\t.cfi_endproc

\t.globl	_str_parseInt           # -- Begin function _str_parseInt
\t.p2align	2
\t.type	_str_parseInt,@function
_str_parseInt:                          # @_str_parseInt
\t.cfi_startproc
# %bb.0:
\taddi	sp, sp, -16
\t.cfi_def_cfa_offset 16
\tsw	ra, 12(sp)
\t.cfi_offset ra, -4
\tsw	a0, 8(sp)
\tlui	a1, %hi(.L.str.1)
\taddi	a1, a1, %lo(.L.str.1)
\taddi	a2, sp, 4
\tcall	__isoc99_sscanf
\tlw	a0, 4(sp)
\tlw	ra, 12(sp)
\taddi	sp, sp, 16
\tret
.Lfunc_end18:
\t.size	_str_parseInt, .Lfunc_end18-_str_parseInt
\t.cfi_endproc

\t.type	.L.str,@object          # @.str
\t.section	.rodata.str1.1,"aMS",@progbits,1
.L.str:
\t.asciz	"%s\\n"
\t.size	.L.str, 4

\t.type	.L.str.1,@object        # @.str.1
.L.str.1:
\t.asciz	"%d"
\t.size	.L.str.1, 3

\t.type	.L.str.2,@object        # @.str.2
.L.str.2:
\t.asciz	"%d\\n"
\t.size	.L.str.2, 4

\t.type	.L.str.3,@object        # @.str.3
.L.str.3:
\t.asciz	"%s"
\t.size	.L.str.3, 3

\t.ident	"clang version 10.0.0-4ubuntu1 "
\t.section	".note.GNU-stack","",@progbits
""";
    public BuiltinFunctionASMPrinter(String dst) throws IOException {
        FileOutputStream out = new FileOutputStream(dst);
        out.write(builtin_s_as_literal.getBytes());
    }
}