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

@str1 = constant [4 x i8] c"aaa\00"
@str2 = constant [6 x i8] c"bbbbb\00"

define i32 @main() {
ALLOCA:
	%R0 = alloca i8*
	%R2 = alloca i8*
	%R4 = alloca i8*
	br label %L0

L0:
	call void @_INIT_()
	%R1 = getelementptr inbounds [4 x i8], [4 x i8]* @str1, i32 0, i32 0
	store i8* %R1, i8** %R0
	%R3 = getelementptr inbounds [6 x i8], [6 x i8]* @str2, i32 0, i32 0
	store i8* %R3, i8** %R2
	%R5 = load i8*, i8** %R0
	%R6 = load i8*, i8** %R2
	%R7 = call i8* @_str_concatenate(i8* %R5, i8* %R6)
	store i8* %R7, i8** %R4
	%R8 = load i8*, i8** %R0
	call void @println(i8* %R8)
	%R9 = load i8*, i8** %R2
	call void @println(i8* %R9)
	%R10 = load i8*, i8** %R4
	call void @println(i8* %R10)
	%R11 = load i8*, i8** %R4
	%R12 = call i32 @_str_length(i8* %R11)
	call void @printlnInt(i32 %R12)
	%R13 = load i8*, i8** %R4
	%R14 = call i32 @_str_ord(i8* %R13, i32 5)
	call void @printlnInt(i32 %R14)
	%R15 = load i8*, i8** %R4
	%R16 = call i32 @_str_length(i8* %R15)
	%R17 = load i8*, i8** %R4
	%R18 = call i32 @_str_ord(i8* %R17, i32 5)
	%R19 = add i32 %R16, %R18
	ret i32 %R19

}

define void @_INIT_() {
ALLOCA:
	br label %L0

L0:
	ret void

}

