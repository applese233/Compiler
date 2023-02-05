declare dso_local void @print(i8* %0)
declare dso_local void @println(i8* %0)
declare dso_local void @printInt(i32 %0)
declare dso_local void @printlnInt(i32 %0)
declare dso_local i32 @getInt()
declare dso_local i8* @toString(i32 %0)
declare dso_local i8* @getString()
declare dso_local i32 @_str_ord(i8* %0, i32 %1)
declare dso_local zeroext i1 @_str_eq(i8* %0, i8* %1)
declare dso_local zeroext i1 @_str_ne(i8* %0, i8* %1)
declare dso_local zeroext i1 @_str_lt(i8* %0, i8* %1)
declare dso_local zeroext i1 @_str_le(i8* %0, i8* %1)
declare dso_local zeroext i1 @_str_gt(i8* %0, i8* %1)
declare dso_local zeroext i1 @_str_ge(i8* %0, i8* %1)
declare dso_local i8* @_str_concatenate(i8* %0, i8* %1)
declare dso_local i8* @_f_malloc(i32 %0)
declare dso_local i32 @_str_length(i8* %0)
declare dso_local i8* @_str_substring(i8* %0, i32 %1, i32 %2)
declare dso_local i32 @_str_parseInt(i8* %0)

@N = global i32 0
@b = global i1* null
@resultCount = global i32 0
@str1 = constant [2 x i8] c" \00"
@str2 = constant [8 x i8] c"Total: \00"

define i32 @main() {
ALLOCA:
	%R0 = alloca i32
	%R16 = alloca i32
	%R19 = alloca i1
	br label %L0

L0:
	call void @_INIT_()
	store i32 1, i32* %R0
	br label %L1

L1:
	%R1 = load i32, i32* %R0
	%R2 = load i32, i32* @N
	%R3 = icmp sle i32 %R1, %R2
	br i1 %R3, label %L2, label %L4

L2:
	%R4 = load i32, i32* %R0
	%R5 = load i1*, i1** @b
	%R6 = getelementptr inbounds i1, i1* %R5, i32 %R4
	store i1 true, i1* %R6
	br label %L3

L3:
	%R7 = load i32, i32* %R0
	%R8 = add i32 %R7, 1
	store i32 %R8, i32* %R0
	br label %L1

L4:
	store i32 2, i32* %R0
	br label %L5

L5:
	%R9 = load i32, i32* %R0
	%R10 = load i32, i32* @N
	%R11 = icmp sle i32 %R9, %R10
	br i1 %R11, label %L6, label %L8

L6:
	%R12 = load i32, i32* %R0
	%R13 = load i1*, i1** @b
	%R14 = getelementptr inbounds i1, i1* %R13, i32 %R12
	%R15 = load i1, i1* %R14
	br i1 %R15, label %L9, label %L10

L9:
	store i32 2, i32* %R16
	%R17 = load i32, i32* %R0
	%R18 = icmp sgt i32 %R17, 3
	br i1 %R18, label %L13, label %L12

L12:
	store i1 false, i1* %R19
	br label %L11

L13:
	%R20 = load i32, i32* %R0
	%R21 = sub i32 %R20, 2
	%R22 = load i1*, i1** @b
	%R23 = getelementptr inbounds i1, i1* %R22, i32 %R21
	%R24 = load i1, i1* %R23
	store i1 %R24, i1* %R19
	br label %L11

L11:
	%R25 = load i1, i1* %R19
	br i1 %R25, label %L14, label %L15

L14:
	%R26 = load i32, i32* @resultCount
	%R27 = add i32 %R26, 1
	store i32 %R27, i32* @resultCount
	%R28 = load i32, i32* %R0
	%R29 = sub i32 %R28, 2
	%R30 = call i8* @toString(i32 %R29)
	%R31 = getelementptr inbounds [2 x i8], [2 x i8]* @str1, i32 0, i32 0
	%R32 = call i8* @_str_concatenate(i8* %R30, i8* %R31)
	%R33 = load i32, i32* %R0
	%R34 = call i8* @toString(i32 %R33)
	%R35 = call i8* @_str_concatenate(i8* %R32, i8* %R34)
	call void @println(i8* %R35)
	br label %L15

L15:
	br label %L16

L16:
	%R36 = load i32, i32* %R0
	%R37 = load i32, i32* %R16
	%R38 = mul i32 %R36, %R37
	%R39 = load i32, i32* @N
	%R40 = icmp sle i32 %R38, %R39
	br i1 %R40, label %L17, label %L18

L17:
	%R41 = load i32, i32* %R0
	%R42 = load i32, i32* %R16
	%R43 = mul i32 %R41, %R42
	%R44 = load i1*, i1** @b
	%R45 = getelementptr inbounds i1, i1* %R44, i32 %R43
	store i1 false, i1* %R45
	%R46 = load i32, i32* %R16
	%R47 = add i32 %R46, 1
	store i32 %R47, i32* %R16
	br label %L16

L18:
	br label %L10

L10:
	br label %L7

L7:
	%R48 = load i32, i32* %R0
	%R49 = add i32 %R48, 1
	store i32 %R49, i32* %R0
	br label %L5

L8:
	%R50 = getelementptr inbounds [8 x i8], [8 x i8]* @str2, i32 0, i32 0
	%R51 = load i32, i32* @resultCount
	%R52 = call i8* @toString(i32 %R51)
	%R53 = call i8* @_str_concatenate(i8* %R50, i8* %R52)
	call void @println(i8* %R53)
	ret i32 0

}

define void @_INIT_() {
ALLOCA:
	br label %L0

L0:
	store i32 15, i32* @N
	%R1 = mul i32 15001, 4
	%R2 = add i32 %R1, 4
	%R3 = call i8* @_f_malloc(i32 %R2)
	%R4 = bitcast i8* %R3 to i32*
	store i32 15001, i32* %R4
	%R5 = getelementptr inbounds i32, i32* %R4, i32 1
	%R6 = bitcast i32* %R5 to i1*
	store i1* %R6, i1** @b
	store i32 0, i32* @resultCount
	ret void

}

