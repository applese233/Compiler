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

%class.node = type {i32, i32, %class.node*}
@hashsize = global i32 0
@table = global %class.node** null
@str1 = constant [2 x i8] c" \00"

define i32 @getHash(i32 %R0) {
ALLOCA:
	%R1 = alloca i32
	br label %L0

L0:
	store i32 %R0, i32* %R1
	%R2 = load i32, i32* %R1
	%R3 = mul i32 %R2, 237
	%R4 = load i32, i32* @hashsize
	%R5 = srem i32 %R3, %R4
	ret i32 %R5

}

define void @put(i32 %R0, i32 %R1) {
ALLOCA:
	%R2 = alloca i32
	%R3 = alloca i32
	%R4 = alloca i32
	%R5 = alloca %class.node*
	br label %L0

L0:
	store i32 %R0, i32* %R2
	store i32 %R1, i32* %R3
	store %class.node* null, %class.node** %R5
	%R6 = load i32, i32* %R2
	%R7 = call i32 @getHash(i32 %R6)
	store i32 %R7, i32* %R4
	%R8 = load i32, i32* %R4
	%R9 = load %class.node**, %class.node*** @table
	%R10 = getelementptr inbounds %class.node*, %class.node** %R9, i32 %R8
	%R11 = load %class.node*, %class.node** %R10
	%R12 = icmp eq %class.node* %R11, null
	br i1 %R12, label %L1, label %L2

L1:
	%R13 = load i32, i32* %R4
	%R14 = load %class.node**, %class.node*** @table
	%R15 = getelementptr inbounds %class.node*, %class.node** %R14, i32 %R13
	%R16 = call i8* @_f_malloc(i32 16)
	%R17 = bitcast i8* %R16 to %class.node*
	store %class.node* %R17, %class.node** %R15
	%R18 = load i32, i32* %R4
	%R19 = load %class.node**, %class.node*** @table
	%R20 = getelementptr inbounds %class.node*, %class.node** %R19, i32 %R18
	%R21 = load %class.node*, %class.node** %R20
	%R22 = getelementptr inbounds %class.node, %class.node* %R21, i32 0, i32 0
	%R23 = load i32, i32* %R2
	store i32 %R23, i32* %R22
	%R24 = load i32, i32* %R4
	%R25 = load %class.node**, %class.node*** @table
	%R26 = getelementptr inbounds %class.node*, %class.node** %R25, i32 %R24
	%R27 = load %class.node*, %class.node** %R26
	%R28 = getelementptr inbounds %class.node, %class.node* %R27, i32 0, i32 1
	%R29 = load i32, i32* %R3
	store i32 %R29, i32* %R28
	%R30 = load i32, i32* %R4
	%R31 = load %class.node**, %class.node*** @table
	%R32 = getelementptr inbounds %class.node*, %class.node** %R31, i32 %R30
	%R33 = load %class.node*, %class.node** %R32
	%R34 = getelementptr inbounds %class.node, %class.node* %R33, i32 0, i32 2
	store %class.node* null, %class.node** %R34
	ret void

L2:
	%R35 = load i32, i32* %R4
	%R36 = load %class.node**, %class.node*** @table
	%R37 = getelementptr inbounds %class.node*, %class.node** %R36, i32 %R35
	%R38 = load %class.node*, %class.node** %R37
	store %class.node* %R38, %class.node** %R5
	br label %L3

L3:
	%R39 = load %class.node*, %class.node** %R5
	%R40 = getelementptr inbounds %class.node, %class.node* %R39, i32 0, i32 0
	%R41 = load i32, i32* %R40
	%R42 = load i32, i32* %R2
	%R43 = icmp ne i32 %R41, %R42
	br i1 %R43, label %L4, label %L5

L4:
	%R44 = load %class.node*, %class.node** %R5
	%R45 = getelementptr inbounds %class.node, %class.node* %R44, i32 0, i32 2
	%R46 = load %class.node*, %class.node** %R45
	%R47 = icmp eq %class.node* %R46, null
	br i1 %R47, label %L6, label %L7

L6:
	%R48 = load %class.node*, %class.node** %R5
	%R49 = getelementptr inbounds %class.node, %class.node* %R48, i32 0, i32 2
	%R50 = call i8* @_f_malloc(i32 16)
	%R51 = bitcast i8* %R50 to %class.node*
	store %class.node* %R51, %class.node** %R49
	%R52 = load %class.node*, %class.node** %R5
	%R53 = getelementptr inbounds %class.node, %class.node* %R52, i32 0, i32 2
	%R54 = load %class.node*, %class.node** %R53
	%R55 = getelementptr inbounds %class.node, %class.node* %R54, i32 0, i32 0
	%R56 = load i32, i32* %R2
	store i32 %R56, i32* %R55
	%R57 = load %class.node*, %class.node** %R5
	%R58 = getelementptr inbounds %class.node, %class.node* %R57, i32 0, i32 2
	%R59 = load %class.node*, %class.node** %R58
	%R60 = getelementptr inbounds %class.node, %class.node* %R59, i32 0, i32 2
	store %class.node* null, %class.node** %R60
	br label %L7

L7:
	%R61 = load %class.node*, %class.node** %R5
	%R62 = getelementptr inbounds %class.node, %class.node* %R61, i32 0, i32 2
	%R63 = load %class.node*, %class.node** %R62
	store %class.node* %R63, %class.node** %R5
	br label %L3

L5:
	%R64 = load %class.node*, %class.node** %R5
	%R65 = getelementptr inbounds %class.node, %class.node* %R64, i32 0, i32 1
	%R66 = load i32, i32* %R3
	store i32 %R66, i32* %R65
	ret void

}

define i32 @get(i32 %R0) {
ALLOCA:
	%R1 = alloca i32
	%R2 = alloca %class.node*
	br label %L0

L0:
	store i32 %R0, i32* %R1
	store %class.node* null, %class.node** %R2
	%R3 = load i32, i32* %R1
	%R4 = call i32 @getHash(i32 %R3)
	%R5 = load %class.node**, %class.node*** @table
	%R6 = getelementptr inbounds %class.node*, %class.node** %R5, i32 %R4
	%R7 = load %class.node*, %class.node** %R6
	store %class.node* %R7, %class.node** %R2
	br label %L1

L1:
	%R8 = load %class.node*, %class.node** %R2
	%R9 = getelementptr inbounds %class.node, %class.node* %R8, i32 0, i32 0
	%R10 = load i32, i32* %R9
	%R11 = load i32, i32* %R1
	%R12 = icmp ne i32 %R10, %R11
	br i1 %R12, label %L2, label %L3

L2:
	%R13 = load %class.node*, %class.node** %R2
	%R14 = getelementptr inbounds %class.node, %class.node* %R13, i32 0, i32 2
	%R15 = load %class.node*, %class.node** %R14
	store %class.node* %R15, %class.node** %R2
	br label %L1

L3:
	%R16 = load %class.node*, %class.node** %R2
	%R17 = getelementptr inbounds %class.node, %class.node* %R16, i32 0, i32 1
	%R18 = load i32, i32* %R17
	ret i32 %R18

}

define i32 @main() {
ALLOCA:
	%R0 = alloca i32
	br label %L0

L0:
	call void @_INIT_()
	%R1 = mul i32 100, 4
	%R2 = add i32 %R1, 4
	%R3 = call i8* @_f_malloc(i32 %R2)
	%R4 = bitcast i8* %R3 to i32*
	store i32 100, i32* %R4
	%R5 = getelementptr inbounds i32, i32* %R4, i32 1
	%R6 = bitcast i32* %R5 to %class.node**
	store %class.node** %R6, %class.node*** @table
	store i32 0, i32* %R0
	br label %L1

L1:
	%R7 = load i32, i32* %R0
	%R8 = load i32, i32* @hashsize
	%R9 = icmp slt i32 %R7, %R8
	br i1 %R9, label %L2, label %L4

L2:
	%R10 = load i32, i32* %R0
	%R11 = load %class.node**, %class.node*** @table
	%R12 = getelementptr inbounds %class.node*, %class.node** %R11, i32 %R10
	store %class.node* null, %class.node** %R12
	br label %L3

L3:
	%R13 = load i32, i32* %R0
	%R14 = add i32 %R13, 1
	store i32 %R14, i32* %R0
	br label %L1

L4:
	store i32 0, i32* %R0
	br label %L5

L5:
	%R15 = load i32, i32* %R0
	%R16 = icmp slt i32 %R15, 1000
	br i1 %R16, label %L6, label %L8

L6:
	%R17 = load i32, i32* %R0
	%R18 = load i32, i32* %R0
	call void @put(i32 %R17, i32 %R18)
	br label %L7

L7:
	%R19 = load i32, i32* %R0
	%R20 = add i32 %R19, 1
	store i32 %R20, i32* %R0
	br label %L5

L8:
	store i32 0, i32* %R0
	br label %L9

L9:
	%R21 = load i32, i32* %R0
	%R22 = icmp slt i32 %R21, 1000
	br i1 %R22, label %L10, label %L12

L10:
	%R23 = load i32, i32* %R0
	%R24 = call i8* @toString(i32 %R23)
	%R25 = getelementptr inbounds [2 x i8], [2 x i8]* @str1, i32 0, i32 0
	%R26 = add i8* %R24, %R25
	%R27 = load i32, i32* %R0
	%R28 = call i32 @get(i32 %R27)
	%R29 = call i8* @toString(i32 %R28)
	%R30 = call i8* @_str_concatenate(i8* %R26, i8* %R29)
	%R31 = add i8* %R26, %R29
	call void @println(i8* %R31)
	br label %L11

L11:
	%R32 = load i32, i32* %R0
	%R33 = add i32 %R32, 1
	store i32 %R33, i32* %R0
	br label %L9

L12:
	ret i32 0

}

define void @_INIT_() {
ALLOCA:
	br label %L0

L0:
	store i32 100, i32* @hashsize
	ret void

}

