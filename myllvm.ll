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


define i32 @qpow(i32 %R0, i32 %R1, i32 %R2) {
ALLOCA:
	%R3 = alloca i32
	%R4 = alloca i32
	%R5 = alloca i32
	%R6 = alloca i32
	%R7 = alloca i32
	br label %L0

L0:
	store i32 %R0, i32* %R3
	store i32 %R1, i32* %R4
	store i32 %R2, i32* %R5
	store i32 1, i32* %R6
	%R8 = load i32, i32* %R3
	store i32 %R8, i32* %R7
	br label %L1

L1:
	%R9 = load i32, i32* %R4
	%R10 = icmp sgt i32 %R9, 0
	br i1 %R10, label %L2, label %L3

L2:
	%R11 = load i32, i32* %R4
	%R12 = and i32 %R11, 1
	%R13 = icmp eq i32 %R12, 1
	br i1 %R13, label %L4, label %L5

L4:
	%R14 = load i32, i32* %R6
	%R15 = load i32, i32* %R7
	%R16 = mul i32 %R14, %R15
	%R17 = load i32, i32* %R5
	%R18 = srem i32 %R16, %R17
	store i32 %R18, i32* %R6
	br label %L5

L5:
	%R19 = load i32, i32* %R7
	%R20 = load i32, i32* %R7
	%R21 = mul i32 %R19, %R20
	%R22 = load i32, i32* %R5
	%R23 = srem i32 %R21, %R22
	store i32 %R23, i32* %R7
	%R24 = load i32, i32* %R4
	%R25 = sdiv i32 %R24, 2
	store i32 %R25, i32* %R4
	br label %L1

L3:
	%R26 = load i32, i32* %R6
	ret i32 %R26

}

define i32 @main() {
ALLOCA:
	br label %L0

L0:
	call void @_INIT_()
	%R0 = call i32 @qpow(i32 2, i32 10, i32 10000)
	%R1 = call i8* @toString(i32 %R0)
	call void @println(i8* %R1)
	ret i32 0

}

define void @_INIT_() {
ALLOCA:
	br label %L0

L0:
	ret void

}

