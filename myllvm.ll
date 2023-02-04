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

@n = global i32 0
@p = global i32 0
@k = global i32 0
@i = global i32 0
@str1 = constant [4 x i8] c"<< \00"
@str2 = constant [2 x i8] c"(\00"
@str3 = constant [3 x i8] c") \00"
@str4 = constant [2 x i8] c" \00"
@str5 = constant [4 x i8] c">> \00"

define i32 @main() {
ALLOCA:
	%R18 = alloca i1
	br label %L0

L0:
	call void @_INIT_()
	%R0 = call i32 @getInt()
	store i32 %R0, i32* @n
	%R1 = call i32 @getInt()
	store i32 %R1, i32* @p
	%R2 = call i32 @getInt()
	store i32 %R2, i32* @k
	%R3 = load i32, i32* @p
	%R4 = load i32, i32* @k
	%R5 = sub i32 %R3, %R4
	%R6 = icmp sgt i32 %R5, 1
	br i1 %R6, label %L1, label %L2

L1:
	%R7 = getelementptr inbounds [4 x i8], [4 x i8]* @str1, i32 0, i32 0
	call void @print(i8* %R7)
	br label %L2

L2:
	%R8 = load i32, i32* @p
	%R9 = load i32, i32* @k
	%R10 = sub i32 %R8, %R9
	store i32 %R10, i32* @i
	br label %L3

L3:
	%R11 = load i32, i32* @p
	%R12 = load i32, i32* @k
	%R13 = add i32 %R11, %R12
	%R14 = load i32, i32* @i
	%R15 = icmp sle i32 %R14, %R13
	br i1 %R15, label %L4, label %L6

L4:
	%R16 = load i32, i32* @i
	%R17 = icmp sle i32 1, %R16
	br i1 %R17, label %L9, label %L8

L8:
	store i1 false, i1* %R18
	br label %L7

L9:
	%R19 = load i32, i32* @i
	%R20 = load i32, i32* @n
	%R21 = icmp sle i32 %R19, %R20
	store i1 %R21, i1* %R18
	br label %L7

L7:
	%R22 = load i1, i1* %R18
	br i1 %R22, label %L10, label %L11

L10:
	%R23 = load i32, i32* @i
	%R24 = load i32, i32* @p
	%R25 = icmp eq i32 %R23, %R24
	br i1 %R25, label %L12, label %L13

L12:
	%R26 = getelementptr inbounds [2 x i8], [2 x i8]* @str2, i32 0, i32 0
	call void @print(i8* %R26)
	%R27 = load i32, i32* @i
	%R28 = call i8* @toString(i32 %R27)
	call void @print(i8* %R28)
	%R29 = getelementptr inbounds [3 x i8], [3 x i8]* @str3, i32 0, i32 0
	call void @print(i8* %R29)
	br label %L14

L13:
	%R30 = load i32, i32* @i
	call void @printInt(i32 %R30)
	%R31 = getelementptr inbounds [2 x i8], [2 x i8]* @str4, i32 0, i32 0
	call void @print(i8* %R31)
	br label %L14

L14:
	br label %L11

L11:
	br label %L5

L5:
	%R32 = load i32, i32* @i
	%R33 = add i32 %R32, 1
	store i32 %R33, i32* @i
	br label %L3

L6:
	%R34 = load i32, i32* @p
	%R35 = load i32, i32* @k
	%R36 = add i32 %R34, %R35
	%R37 = load i32, i32* @n
	%R38 = icmp slt i32 %R36, %R37
	br i1 %R38, label %L15, label %L16

L15:
	%R39 = getelementptr inbounds [4 x i8], [4 x i8]* @str5, i32 0, i32 0
	call void @print(i8* %R39)
	br label %L16

L16:
	ret i32 0

}

define void @_INIT_() {
ALLOCA:
	br label %L0

L0:
	ret void

}

