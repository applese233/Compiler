package backend;

import java.util.ArrayList;
import java.util.HashMap;

import IR.*;
import IR.inst.*;
import IR.operand.*;
import IR.type.*;
import Util.Scope;
import Util.Type;
import Util.Type.basicType;
import AST.*;
import AST.DefNode.*;
import AST.ExprNode.*;
import AST.StmtNode.*;

public class IRBuilder implements ASTVisitor {
	public IR.Module_ Module_;
	public Function nowFunction, initFunction;
	public BasicBlock nowBlock, globalEndBlock, globalIncrBlock, initBlock;
	public Scope globalScope;
	public ClassType nowClass = null;
	public Register nowClassPointer = null;
	public IRScope nowIRScope;
	public HashMap<String, GlobalVariable> globalMap;
	public Ret lastRet = null;
	public IRType retType = null;
	public ArrayList<String> classFuncList;
	public int num = 0, numString = 0, numLabel = 0;
	public boolean Global, classCollector, structFunction;

	public IRBuilder(Scope _globalScope, IR.Module_ _Module_) {
		globalScope = _globalScope;
		Module_ = _Module_;
		globalMap = new HashMap<>();
		nowIRScope = new IRScope(null);
		initFunction = new Function();
		initFunction.funcDefine = new Define(new VoidType(), "_INIT_");
		initBlock = new BasicBlock("L0", initFunction);
		structFunction = false;
	}

	@Override
	public void Visit(ProgNode it) {
		classCollector = true;
		for(ASTNode x : it.list)
			if(x instanceof ClassDefNode)
				x.Accept(this);
		classCollector = false;

		Global = true;
		it.list.forEach(x -> x.Accept(this));
		initBlock.AddInst(new Ret(initBlock));
		initFunction.blockList.add(initBlock);
		Module_.funcList.add(initFunction);
	}

	@Override
	public void Visit(TypeNode it) {

	}

	@Override
	public void Visit(FuncDefNode it) {
		Type nowType;
		IRType nowIRType;
		if(structFunction) {
			nowIRType = new VoidType();
		}
		else {
			nowType = globalScope.TypeGet(it.type, null);
			nowIRType = nowType.GetIRType();
			if(nowIRType instanceof ClassType) {
				nowIRType = new PointerType(nowIRType);
			}
			for(int i = 1; i <= it.type.dim; ++ i) {
				nowIRType = new PointerType(nowIRType);
			}
		}
		retType = nowIRType;

		nowIRScope = new IRScope(nowIRScope);
		num = numLabel = -1;

		nowFunction = new Function();
		numLabel ++;
		nowBlock = new BasicBlock("L" + numLabel, nowFunction);
		if(it.id.equals("main")) {
			nowBlock.AddInst(new Call(nowBlock, null, new VoidType(), "_INIT_", new ArrayList<>()));
		}

		String funcId;
		if(nowClass == null) {
			funcId = it.id;
		}
		else {
			funcId = nowClass.name + "_" + it.id;
		}
		Define nowDef = new Define(nowIRType, funcId);
		if(nowClass != null) {
			nowDef.typeList.add(new PointerType(nowClass));
			num ++;
			Register tmpReg = new Register(new PointerType(nowClass), Integer.toString(num));
			nowDef.regList.add(tmpReg);
		}
		if(it.paralist != null) {
			for(VarDecStmtNode x : it.paralist) {
				Type tmpType = globalScope.TypeGet(x.type, null);
				IRType tmpIRType = tmpType.GetIRType();
				if(tmpIRType instanceof ClassType) {
					tmpIRType = new PointerType(tmpIRType);
				}
				for(int i = 1; i <= x.type.dim; ++ i) {
					tmpIRType = new PointerType(tmpIRType);
				}
				nowDef.typeList.add(tmpIRType);
				num ++;
				Register tmpReg = new Register(tmpIRType, Integer.toString(num));
				nowDef.regList.add(tmpReg);
			}
		}
		nowFunction.funcDefine = nowDef;

		if(nowClass != null) {
			IRType tmpIRType = new PointerType(nowClass);
			num ++;
			Register tmpReg = new Register(tmpIRType, Integer.toString(num));
			nowBlock.AddInst(new Alloca(nowBlock, tmpReg, tmpIRType));
			nowBlock.AddInst(new Store(nowBlock, tmpIRType, new Register(tmpIRType, "0"), tmpReg));
			nowClassPointer = tmpReg;
		}
		if(it.paralist != null) {
			for(VarDecStmtNode x : it.paralist) {
				Type tmpType = globalScope.TypeGet(x.type, null);
				IRType tmpIRType = tmpType.GetIRType();
				if(tmpIRType instanceof ClassType) {
					tmpIRType = new PointerType(tmpIRType);
				}
				for(int i = 1; i <= x.type.dim; ++ i) {
					tmpIRType = new PointerType(tmpIRType);
				}

				num ++;
				Register tmpReg = new Register(new PointerType(tmpIRType), Integer.toString(num));
				nowBlock.AddInst(new Alloca(nowBlock, tmpReg, tmpIRType));
				nowIRScope.Put(x.id, tmpReg);
				int lastId = num - it.paralist.size();
				if(nowClass != null) {
					lastId --;
				}
				nowBlock.AddInst(new Store(nowBlock, tmpIRType, new Register(tmpIRType, Integer.toString(lastId)), tmpReg));
			}
		}

		boolean tmpGlobal = Global;
		Global = false;
		it.suite.Accept(this);
		Global = tmpGlobal;

		if(nowBlock.terminator == null) {
			if(it.id.equals("main")) {
				nowBlock.terminator = new Ret(nowBlock, new IntType(32), new IntConst(0));
			}
			else if(nowIRType instanceof VoidType) {
				nowBlock.terminator = new Ret(nowBlock);
			}
			else {
				nowBlock.terminator = lastRet;
			}
		}
		nowFunction.blockList.add(nowBlock);

		Module_.funcList.add(nowFunction);
		nowIRScope = nowIRScope.pIRScope;
	}

	@Override
	public void Visit(ClassDefNode it) {
		if(classCollector) {
			Type nowType = globalScope.typeMap.get(it.id);
			IRType nowIRType = nowType.GetIRType();
			for(VarDecStmtNode x : it.varlist) {
				Type xType = globalScope.TypeGet(x.type, null);
				System.out.println(xType.type);
				IRType xIRType = xType.GetIRType();
				if(xIRType instanceof ClassType) {
					xIRType = new PointerType(xIRType);
				}
				for(int i = 1; i <= x.type.dim; ++ i) {
					xIRType = new PointerType(xIRType);
				}
				((ClassType) nowIRType).typeList.add(xIRType);
				((ClassType) nowIRType).nameList.add(x.id);
			}
			if(it.struct != null) {
				((ClassType) nowIRType).hasStruct = true;
			}
			Module_.globalList.add(new Global(nowIRType));
			return;
		}

		nowClass = (ClassType) globalScope.typeMap.get(it.id).GetIRType();
		classFuncList = new ArrayList<>();
		for(FuncDefNode x : it.funclist) {
			classFuncList.add(x.id);
		}
		if(it.struct != null) {
			structFunction = true;
			it.struct.Accept(this);
			structFunction = false;
		}
		it.funclist.forEach(x -> x.Accept(this));
		nowClass = null;
	}

	@Override
	public void Visit(VarDecStmtNode it) {
		Type nowType = globalScope.TypeGet(it.type, null);
		IRType nowIRType = nowType.GetIRType();
		if(nowIRType instanceof ClassType) {
			nowIRType = new PointerType(nowIRType);
		}
		for(int i = 1; i <= it.type.dim; ++ i) {
			nowIRType = new PointerType(nowIRType);
		}
		if(Global) {
			BasicBlock tmpBlock = nowBlock;
			nowBlock = initBlock;
			Function tmpFunction = nowFunction;
			nowFunction = initFunction;

			GlobalVariable nowVar = new GlobalVariable(it.id, new PointerType(nowIRType));
			if(nowIRType instanceof PointerType || nowIRType instanceof ArrayType || nowIRType instanceof ClassType) {
				Module_.globalList.add(new Global(nowVar, nowIRType, new NullOperand()));
			}
			else if(nowIRType instanceof IntType) {
				Module_.globalList.add(new Global(nowVar, nowIRType, new IntConst(0)));
			}
			globalMap.put(it.id, nowVar);

			if(it.expr != null) {
				it.expr.Accept(this);

				Operand exprReg;
				IRType valueIRType = it.expr.operand.type;
				if(it.expr.operand.loadneed) {
					IRType newIRType = ((PointerType) valueIRType).type;
					num ++;
					exprReg = new Register(newIRType, Integer.toString(num));
					nowBlock.AddInst(new Load(nowBlock, (Register)exprReg, newIRType, it.expr.operand));
				}
				else {
					exprReg = it.expr.operand;
				}
				nowBlock.AddInst(new Store(nowBlock, valueIRType, exprReg, nowVar));
			}
			initBlock = nowBlock;
			nowBlock = tmpBlock;
			nowFunction = tmpFunction;
		}
		else {
			num ++;
			Register nowReg = new Register(new PointerType(nowIRType), Integer.toString(num));
			nowBlock.AddInst(new Alloca(nowBlock, nowReg, nowIRType));
			nowIRScope.Put(it.id, nowReg);

			if(it.expr != null) {
				it.expr.Accept(this);
				Operand exprReg;
				IRType valueIRType = it.expr.operand.type;
				if(it.expr.operand.loadneed) {
					IRType newIRType = ((PointerType) valueIRType).type;
					num ++;
					exprReg = new Register(newIRType, Integer.toString(num));
					nowBlock.AddInst(new Load(nowBlock, (Register)exprReg, newIRType, it.expr.operand));
				}
				else {
					exprReg = it.expr.operand;
				}
				nowBlock.AddInst(new Store(nowBlock, nowIRType, exprReg, nowReg));
			}
		}
	}

	@Override
	public void Visit(VarDefStmtNode it) {
		it.varList.forEach(x -> x.Accept(this));
	}

	@Override
	public void Visit(SuiteStmtNode it) {
		it.stmtList.forEach(x -> x.Accept(this));
	}

	@Override
	public void Visit(IfStmtNode it) {
		it.expr.Accept(this);

		if(it.falseStmt != null) {
			numLabel ++;
			BasicBlock trueBlock = new BasicBlock("L" + numLabel, nowFunction);
			numLabel ++;
			BasicBlock falseBlock = new BasicBlock("L" + numLabel, nowFunction);
			numLabel ++;
			BasicBlock endBlock = new BasicBlock("L" + numLabel, nowFunction);

			Operand exprReg;
			IRType valueIRType = it.expr.operand.type;
			if(it.expr.operand.loadneed) {
				IRType newIRType = ((PointerType) valueIRType).type;
				num ++;
				exprReg = new Register(newIRType, Integer.toString(num));
				nowBlock.AddInst(new Load(nowBlock, (Register) exprReg, newIRType, it.expr.operand));
			}
			else {
				exprReg = it.expr.operand;
			}
			nowBlock.AddInst(new Br(nowBlock, exprReg, trueBlock, falseBlock));
			nowFunction.blockList.add(nowBlock);

			nowBlock = trueBlock;
			it.trueStmt.Accept(this);
			nowBlock.AddInst(new Br(nowBlock, endBlock));
			nowFunction.blockList.add(nowBlock);

			nowBlock = falseBlock;
			it.falseStmt.Accept(this);
			nowBlock.AddInst(new Br(nowBlock, endBlock));
			nowFunction.blockList.add(nowBlock);

			nowBlock = endBlock;
		}
		else {
			numLabel ++;
			BasicBlock trueBlock = new BasicBlock("L" + numLabel, nowFunction);
			numLabel ++;
			BasicBlock endBlock = new BasicBlock("L" + numLabel, nowFunction);

			Operand exprReg;
			IRType valueIRType = it.expr.operand.type;
			if(it.expr.operand.loadneed) {
				IRType newIRType = ((PointerType) valueIRType).type;
				num ++;
				exprReg = new Register(newIRType, Integer.toString(num));
				nowBlock.AddInst(new Load(nowBlock, (Register) exprReg, newIRType, it.expr.operand));
			}
			else {
				exprReg = it.expr.operand;
			}
			nowBlock.AddInst(new Br(nowBlock, exprReg, trueBlock, endBlock));
			nowFunction.blockList.add(nowBlock);

			nowBlock = trueBlock;
			it.trueStmt.Accept(this);
			nowBlock.AddInst(new Br(nowBlock, endBlock));
			nowFunction.blockList.add(nowBlock);

			nowBlock = endBlock;
		}
	}

	@Override
	public void Visit(WhileStmtNode it) {
		numLabel ++;
		BasicBlock whileBlock = new BasicBlock("L" + numLabel, nowFunction);
		numLabel ++;
		BasicBlock trueBlock = new BasicBlock("L" + numLabel, nowFunction);
		numLabel ++;
		BasicBlock falseBlock = new BasicBlock("L" + numLabel, nowFunction);

		BasicBlock tmpEndBlock = globalEndBlock;
		globalEndBlock = falseBlock;
		BasicBlock tmpIncrBlock = globalIncrBlock;
		globalIncrBlock = whileBlock;

		nowBlock.AddInst(new Br(nowBlock, whileBlock));
		nowFunction.blockList.add(nowBlock);

		nowBlock = whileBlock;
		it.expr.Accept(this);
		
		Operand exprReg;
		IRType valueIRType = it.expr.operand.type;
		if(it.expr.operand.loadneed) {
			IRType newIRType = ((PointerType) valueIRType).type;
			num ++;
			exprReg = new Register(newIRType, Integer.toString(num));
			nowBlock.AddInst(new Load(nowBlock, (Register) exprReg, newIRType, it.expr.operand));
		}
		else {
			exprReg = it.expr.operand;
		}
		nowBlock.AddInst(new Br(nowBlock, exprReg, trueBlock, falseBlock));
		nowFunction.blockList.add(nowBlock);

		nowBlock = trueBlock;
		it.stmt.Accept(this);
		nowBlock.AddInst(new Br(nowBlock, whileBlock));
		nowFunction.blockList.add(nowBlock);

		nowBlock = falseBlock;
		globalEndBlock = tmpEndBlock;
		globalIncrBlock = tmpIncrBlock;
	}

	@Override
	public void Visit(ForStmtNode it) {
		if(it.init != null) {
			it.init.Accept(this);
		}

		numLabel ++;
		BasicBlock condBlock = new BasicBlock("L" + numLabel, nowFunction);
		numLabel ++;
		BasicBlock trueBlock = new BasicBlock("L" + numLabel, nowFunction);
		numLabel ++;
		BasicBlock incrBlock = new BasicBlock("L" + numLabel, nowFunction);
		numLabel ++;
		BasicBlock falseBlock = new BasicBlock("L" + numLabel, nowFunction);

		BasicBlock tmpEndBlock = globalEndBlock;
		globalEndBlock = falseBlock;
		BasicBlock tmpIncrBlock = globalIncrBlock;
		globalIncrBlock = incrBlock;

		nowBlock.AddInst(new Br(nowBlock, condBlock));
		nowFunction.blockList.add(nowBlock);

		nowBlock = condBlock;
		if(it.cond != null) {
			it.cond.Accept(this);
			Operand exprReg;
			IRType valueIRType = it.cond.operand.type;
			if(it.cond.operand.loadneed) {
				IRType newIRType = ((PointerType) valueIRType).type;
				num ++;
				exprReg = new Register(newIRType, Integer.toString(num));
				nowBlock.AddInst(new Load(nowBlock, (Register) exprReg, newIRType, it.cond.operand));
			}
			else {
				exprReg = it.cond.operand;
			}
			nowBlock.AddInst(new Br(nowBlock, exprReg, trueBlock, falseBlock));
		}
		else {
			nowBlock.AddInst(new Br(nowBlock, new BoolConst(true), trueBlock, falseBlock));
		}
		nowFunction.blockList.add(nowBlock);

		nowBlock = trueBlock;
		nowIRScope = new IRScope(nowIRScope);
		it.stmt.Accept(this);
		nowIRScope = nowIRScope.pIRScope;
		nowBlock.AddInst(new Br(nowBlock, incrBlock));
		nowFunction.blockList.add(nowBlock);

		nowBlock = incrBlock;
		if(it.incr != null) {
			it.incr.Accept(this);
		}
		nowBlock.AddInst(new Br(nowBlock, condBlock));
		nowFunction.blockList.add(nowBlock);

		nowBlock = falseBlock;
		globalEndBlock = tmpEndBlock;
		globalIncrBlock = tmpIncrBlock;
	}

	@Override
	public void Visit(ReturnStmtNode it) {
		if(it.expr == null) {
			lastRet = new Ret(nowBlock);
			nowBlock.AddInst(lastRet);
		}
		else {
			it.expr.Accept(this);
			Operand exprReg;
			IRType valueIRType = it.expr.operand.type;
			if(it.expr.operand.loadneed) {
				IRType newIRType = ((PointerType) valueIRType).type;
				num ++;
				exprReg = new Register(newIRType, Integer.toString(num));
				nowBlock.AddInst(new Load(nowBlock, (Register) exprReg, newIRType, it.expr.operand));
			}
			else {
				exprReg = it.expr.operand;
			}
			lastRet = new Ret(nowBlock, retType, exprReg);
			nowBlock.AddInst(lastRet);
		}
	}

	@Override
	public void Visit(BreakStmtNode it) {
		nowBlock.AddInst(new Br(nowBlock, globalEndBlock));
	}

	@Override
	public void Visit(ContinueStmtNode it) {
		nowBlock.AddInst(new Br(nowBlock, globalIncrBlock));
	}

	@Override
	public void Visit(PureExprStmtNode it) {
		it.expr.Accept(this);
	}

	@Override
	public void Visit(EmptyStmtNode it) {

	}

	@Override
	public void Visit(IntExprNode it) {
		it.operand = new IntConst(it.value);
	}

	@Override
	public void Visit(BoolExprNode it) {
		it.operand = new BoolConst(it.value);
	}

	@Override
	public void Visit(StringExprNode it) {
		String res = it.value.substring(1, it.value.length() - 1);
		res = res.replace("\\\\", "\\").replace("\\n", "\n").replace("\\t", "\t").replace("\\\"", "\"").replace("\\0", "\0");
		res = res + "\0";
		IRType globalIRType = new ArrayType(res.length(), new IntType(8));
		res = res.replace("\\", "\\5C").replace("\n", "\\0A").replace("\t", "\\09").replace("\"", "\\22").replace("\0", "\\00");
		numString ++;
		GlobalVariable nowVar = new GlobalVariable("str" + numString, globalIRType);
		Module_.globalList.add(new Global(nowVar, globalIRType, new StringConst(globalIRType, res)));

		num ++;
		Register nowReg = new Register(new PointerType(new IntType(8)), Integer.toString(num));
		nowBlock.AddInst(new Getelementptr(nowBlock, nowReg, new PointerType(nowVar.type), nowVar));
		it.operand = nowReg;
	}

	@Override
	public void Visit(NullExprNode it) {
		it.operand = new NullOperand();
	}

	@Override
	public void Visit(ThisExprNode it) {
		num ++;
		Register nowReg = new Register(new PointerType(nowClass), Integer.toString(num));
		nowBlock.AddInst(new Load(nowBlock, nowReg, nowReg.type, nowClassPointer));
		it.operand = nowReg;
	}

	@Override
	public void Visit(IdExprNode it) {
		IRType nowIRType = it.type.GetIRType();
		if(nowIRType instanceof ClassType) {
			nowIRType = new PointerType(nowIRType);
		}
		if(it.type.dim > 0) { // Array
			for(int i = 1; i <= it.type.dim; ++ i) {
				nowIRType = new PointerType(nowIRType);
			}
		}
		
		it.operand = nowIRScope.Get(it.id);
		IRScope tmpIRScope = nowIRScope.pIRScope;
		while(it.operand == null) {
			if(tmpIRScope == null) {
				break;
			}
			it.operand = tmpIRScope.Get(it.id);
			tmpIRScope = tmpIRScope.pIRScope;
		}

		if(it.operand == null) {
			it.operand = globalMap.get(it.id);
		}
		if(it.operand == null) {
			num ++;
			Register classReg = new Register(new PointerType(nowClass), Integer.toString(num));
			nowBlock.AddInst(new Load(nowBlock, classReg, classReg.type, nowClassPointer));

			int pos = nowClass.nameList.indexOf(it.id);

			num ++;
			Register nowReg = new Register(new PointerType(nowIRType), Integer.toString(num));
			ArrayList<Operand> nowparaList = new ArrayList<>();
			nowparaList.add(new IntConst(0));
			nowparaList.add(new IntConst(pos));
			nowBlock.AddInst(new Getelementptr(nowBlock, nowReg, new PointerType(nowClass), classReg, nowparaList));

			it.operand = nowReg;
		}
		it.operand.loadneed = true;
	}

	@Override
	public void Visit(LambdaExprNode it) {

	}

	@Override
	public void Visit(FuncExprNode it) {
		String funcId = "";
		IRType nowIRType = it.type.GetIRType();
		if(nowIRType instanceof ClassType) {
			nowIRType = new PointerType(nowIRType);
		}
		if(it.type.dim > 0) {
			for(int i = 0; i < it.type.dim; ++ i) {
				nowIRType = new PointerType(nowIRType);
			}
		}
		ArrayList<Operand> paraList = new ArrayList<>();

		if(it.id instanceof ClassExprNode && ((ClassExprNode) it.id).name.type.type == basicType.String) {
			((ClassExprNode) it.id).name.Accept(this);
			funcId = "_str_" + ((ClassExprNode) it.id).id;

			Operand strReg;
			if(((ClassExprNode) it.id).name.operand.loadneed) {
				IRType newType = ((PointerType) ((ClassExprNode) it.id).name.operand.type).type;
				num ++;
				strReg = new Register(newType, Integer.toString(num));
				nowBlock.AddInst(new Load(nowBlock, (Register) strReg, newType, ((ClassExprNode) it.id).name.operand));
			}
			else {
				strReg = ((ClassExprNode) it.id).name.operand;
			}
			paraList.add(strReg);
		}
		else if(it.id instanceof ClassExprNode && ((ClassExprNode) it.id).name.type.dim > 0) {
			((ClassExprNode) it.id).name.Accept(this);

			Operand begReg;
			if(((ClassExprNode) it.id).name.operand.loadneed) {
				IRType newType = ((PointerType) ((ClassExprNode) it.id).name.operand.type).type;
				num ++;
				begReg = new Register(newType, Integer.toString(num));
				nowBlock.AddInst(new Load(nowBlock, (Register) begReg, newType, ((ClassExprNode) it.id).name.operand));
			}
			else {
				begReg = ((ClassExprNode) it.id).name.operand;
			}

			num ++;
			Register bitReg = new Register(new PointerType(new IntType(32)), Integer.toString(num));
			nowBlock.AddInst(new Bitcast(nowBlock, bitReg, begReg.type, begReg, bitReg.type));

			num ++;
			Register moveReg = new Register(bitReg.type, Integer.toString(num));
			paraList.add(new IntConst(-1));
			nowBlock.AddInst(new Getelementptr(nowBlock, moveReg, (PointerType) bitReg.type, bitReg, paraList));

			num ++;
			Register sizeReg = new Register(new IntType(32), Integer.toString(num));
			nowBlock.AddInst(new Load(nowBlock, sizeReg, sizeReg.type, moveReg));

			it.operand = sizeReg;
			return;
		}
		else if(it.id instanceof ClassExprNode) {
			((ClassExprNode) it.id).name.Accept(this);

			IRType classPointerType;
			IRType nameIRType = ((ClassExprNode) it.id).name.operand.type;
			classPointerType = nameIRType;
			Operand classReg;
			if(((ClassExprNode) it.id).name.operand.loadneed) {
				IRType newIRType = ((PointerType) nameIRType).type;
				classPointerType = newIRType;
				num ++;
				classReg = new Register(newIRType, Integer.toString(num));
				nowBlock.AddInst(new Load(nowBlock, (Register) classReg, newIRType, ((ClassExprNode) it.id).name.operand));
			}
			else {
				classReg = ((ClassExprNode) it.id).name.operand;
			}
			IRType classIRType = ((PointerType) classPointerType).type;

			funcId = ((ClassType) classIRType).name + "_" + ((ClassExprNode) it.id).id;

			paraList.add(classReg);
		}
		else if(it.id instanceof IdExprNode) {
			if(nowClass != null && classFuncList.contains(((IdExprNode) it.id).id)) {
				num ++;
				Register classReg = new Register(new PointerType(nowClass), Integer.toString(num));
				nowBlock.AddInst(new Load(nowBlock, classReg, classReg.type, nowClassPointer));

				funcId = nowClass.name + "_" + ((IdExprNode) it.id).id;

				paraList.add(classReg);
			}
			else {
				funcId = ((IdExprNode) it.id).id;
			}
		}

		for(ExprNode x : it.exprList.exprList) {
			x.Accept(this);

			Operand nowOp;
			IRType exprIRType = x.operand.type;
			if(x.operand.loadneed) {
				IRType newIRType = ((PointerType) exprIRType).type;
				num ++;
				nowOp = new Register(newIRType, Integer.toString(num));
				nowBlock.AddInst(new Load(nowBlock, (Register) nowOp, newIRType, x.operand));
			}
			else {
				nowOp = x.operand;
			}
			paraList.add(nowOp);
		}

		if(nowIRType instanceof VoidType) {
			nowBlock.AddInst(new Call(nowBlock, null, nowIRType, funcId, paraList));
		}
		else {
			num ++;
			Register nowReg = new Register(nowIRType, Integer.toString(num));
			nowBlock.AddInst(new Call(nowBlock, nowReg, nowIRType, funcId, paraList));

			it.operand = nowReg;
		}
	}

	@Override
	public void Visit(IndexExprNode it) {
		ArrayList<Operand> nowparaList = new ArrayList<>();
		it.bas.Accept(this);
		it.off.Accept(this);

		Operand offReg;
		IRType offIRType = it.off.operand.type;
		if(it.off.operand.loadneed) {
			IRType newIRType = ((PointerType) offIRType).type;
			num ++;
			offReg = new Register(newIRType, Integer.toString(num));
			nowBlock.AddInst(new Load(nowBlock, (Register) offReg, newIRType, it.off.operand));
		}
		else {
			offReg = it.off.operand;
		}

		nowparaList.add(offReg);

		Operand basReg;
		IRType basIRType = it.bas.operand.type, nowIRType = basIRType;
		if(it.bas.operand.loadneed) {
			IRType newIRType = ((PointerType) basIRType).type;
			nowIRType = newIRType;
			num ++;
			basReg = new Register(newIRType, Integer.toString(num));
			nowBlock.AddInst(new Load(nowBlock, (Register) basReg, newIRType, it.bas.operand));
		}
		else {
			basReg = it.bas.operand;
		}

		num ++;
		Register nowReg = new Register(nowIRType, Integer.toString(num));
		nowBlock.AddInst(new Getelementptr(nowBlock, nowReg, (PointerType)nowIRType, basReg, nowparaList));

		it.operand = nowReg;
		it.operand.loadneed = true;
	}

	@Override
	public void Visit(ClassExprNode it) {
		it.name.Accept(this);

		IRType classPointerType;
		IRType nameIRType = it.name.operand.type;
		classPointerType = nameIRType;
		Operand classReg;
		if(it.name.operand.loadneed) {
			IRType newIRType = ((PointerType) nameIRType).type;
			classPointerType = newIRType;
			num ++;
			classReg = new Register(newIRType, Integer.toString(num));
			nowBlock.AddInst(new Load(nowBlock, (Register) classReg, newIRType, it.name.operand));
		}
		else {
			classReg = it.name.operand;
		}
		IRType classIRType = ((PointerType) classPointerType).type;

		ArrayList<Operand> nowparaList = new ArrayList<>();
		nowparaList.add(new IntConst(0));
		int pos = ((ClassType) classIRType).nameList.indexOf(it.id);
		IRType nowIRType = ((ClassType) classIRType).typeList.get(pos);
		nowparaList.add(new IntConst(pos));
		num ++;
		Register nowReg = new Register(new PointerType(nowIRType), Integer.toString(num));
		nowBlock.AddInst(new Getelementptr(nowBlock, nowReg, (PointerType) classPointerType, classReg, nowparaList));

		it.operand = nowReg;
		it.operand.loadneed = true;
	}

	@Override
	public void Visit(SufExprNode it) {
		it.expr.Accept(this);
		IRType nowIRType;

		Operand beforeReg;
		IRType beforeIRType = it.expr.operand.type;
		nowIRType = beforeIRType;
		if(it.expr.operand.loadneed) {
			IRType newIRType = ((PointerType) beforeIRType).type;
			nowIRType = newIRType;
			num ++;
			beforeReg = new Register(newIRType, Integer.toString(num));
			nowBlock.AddInst(new Load(nowBlock, (Register) beforeReg, newIRType, it.expr.operand));
		}
		else {
			beforeReg = it.expr.operand;
		}
		num ++;
		Register afterReg = new Register(nowIRType, Integer.toString(num));
		if(it.op.equals("++")) {
			nowBlock.AddInst(new Binary(nowBlock, afterReg, "add", nowIRType, beforeReg, new IntConst(1)));
		}
		else if(it.op.equals("--")) {
			nowBlock.AddInst(new Binary(nowBlock, afterReg, "add", nowIRType, beforeReg, new IntConst(-1)));
		}
		nowBlock.AddInst(new Store(nowBlock, nowIRType, afterReg, it.expr.operand));
		it.operand = beforeReg;
	}

	@Override
	public void Visit(PreExprNode it) {
		it.expr.Accept(this);
		IRType nowIRType;

		Operand beforeReg;
		IRType beforeIRType = it.expr.operand.type;
		nowIRType = beforeIRType;
		if(it.expr.operand.loadneed) {
			IRType newIRType = ((PointerType) beforeIRType).type;
			nowIRType = newIRType;
			num ++;
			beforeReg = new Register(newIRType, Integer.toString(num));
			nowBlock.AddInst(new Load(nowBlock, (Register) beforeReg, newIRType, it.expr.operand));
		}
		else {
			beforeReg = it.expr.operand;
		}

		switch(it.op) {
			case "++": {
				num ++;
				Register afterReg = new Register(nowIRType, Integer.toString(num));
				nowBlock.AddInst(new Binary(nowBlock, afterReg, "add", nowIRType, beforeReg, new IntConst(1)));
				nowBlock.AddInst(new Store(nowBlock, nowIRType, afterReg, it.expr.operand));
				it.operand = afterReg;
				break;
			}
			case "--": {
				num ++;
				Register afterReg = new Register(nowIRType, Integer.toString(num));
				nowBlock.AddInst(new Binary(nowBlock, afterReg, "add", nowIRType, beforeReg, new IntConst(-1)));
				nowBlock.AddInst(new Store(nowBlock, nowIRType, afterReg, it.expr.operand));
				it.operand = afterReg;
				break;
			}
			case "+": {
				it.operand = beforeReg;
				break;
			}
			case "-": {
				num ++;
				Register afterReg = new Register(nowIRType, Integer.toString(num));
				nowBlock.AddInst(new Binary(nowBlock, afterReg, "sub", nowIRType, new IntConst(0), beforeReg));
				it.operand = afterReg;
				break;
			}
			case "!": {
				num ++;
				Register afterReg = new Register(nowIRType, Integer.toString(num));
				nowBlock.AddInst(new Binary(nowBlock, afterReg, "xor", nowIRType, beforeReg, new BoolConst(true)));
				it.operand = afterReg;
				break;
			}
			case "~": {
				num ++;
				Register afterReg = new Register(nowIRType, Integer.toString(num));
				nowBlock.AddInst(new Binary(nowBlock, afterReg, "xor", nowIRType, beforeReg, new IntConst(-1)));
				it.operand = afterReg;
				break;
			}
		}
	}

	@Override
	public void Visit(NewExprNode it) {
		IRType nowIRType = it.type.GetIRType();
		int dim = it.typeNode.dim;

		if(it.exprList == null) {
			int size = 0;
			for(IRType x : ((ClassType) nowIRType).typeList) {
				if(x instanceof PointerType) {
					size += 8;
				}
				else if(x instanceof IntType) {
					if(((IntType) x).wide == 32) {
						size += 4;
					}
					else {
						size ++;
					}
				}
			}

			num ++;
			Register mallocPointer = new Register(new PointerType(new IntType(8)), Integer.toString(num));
			ArrayList<Operand> nowparaList = new ArrayList<>();
			nowparaList.add(new IntConst(size));
			nowBlock.AddInst(new Call(nowBlock, mallocPointer, mallocPointer.type, "_f_malloc", nowparaList));

			num ++;
			Register classPointer = new Register(new PointerType(nowIRType), Integer.toString(num));
			nowBlock.AddInst(new Bitcast(nowBlock, classPointer, mallocPointer.type, mallocPointer, classPointer.type));

			it.operand = classPointer;

			if(((ClassType) nowIRType).hasStruct) {
				nowparaList = new ArrayList<>();
				nowparaList.add(classPointer);
				nowBlock.AddInst(new Call(nowBlock, null, new VoidType(), ((ClassType) nowIRType).name + "_" + ((ClassType) nowIRType).name, nowparaList));
			}
			return;
		}

		if(nowIRType instanceof ClassType) {
			nowIRType = new PointerType(nowIRType);
		}

		ExprNode x = it.exprList.get(0);
		x.Accept(this);
		Operand sizeReg;
		if(x.operand.loadneed) {
			num ++;
			sizeReg = new Register(new IntType(32), Integer.toString(num));
			nowBlock.AddInst(new Load(nowBlock, (Register) sizeReg, new IntType(32), x.operand));
		}
		else {
			sizeReg = x.operand;
		}

		num ++;
		Register mulReg = new Register(new IntType(32), Integer.toString(num));
		int singleSize = 4;
		nowBlock.AddInst(new Binary(nowBlock, mulReg, "mul", new IntType(32), sizeReg, new IntConst(singleSize)));

		num ++;
		Register sumReg = new Register(new IntType(32), Integer.toString(num));
		nowBlock.AddInst(new Binary(nowBlock, sumReg, "add", new IntType(32), mulReg, new IntConst(4)));

		num ++;
		Register callReg = new Register(new PointerType(new IntType(8)), Integer.toString(num));
		ArrayList<Operand> nowparaList = new ArrayList<>();
		nowparaList.add(sumReg);
		nowBlock.AddInst(new Call(nowBlock, callReg, new PointerType(new IntType(8)), "_f_malloc", nowparaList));

		PointerType sizePointerIRType = new PointerType(new IntType(32));
		num ++;
		Register sizePointerReg = new Register(sizePointerIRType, Integer.toString(num));
		nowBlock.AddInst(new Bitcast(nowBlock, sizePointerReg, new PointerType(new IntType(8)), callReg, sizePointerIRType));

		nowBlock.AddInst(new Store(nowBlock, new IntType(32), sizeReg, sizePointerReg));

		num ++;
		Register moveReg = new Register(sizePointerIRType, Integer.toString(num));
		nowparaList = new ArrayList<>();
		nowparaList.add(new IntConst(1));
		nowBlock.AddInst(new Getelementptr(nowBlock, moveReg, sizePointerIRType, sizePointerReg, nowparaList));

		IRType trueIRType = nowIRType;
		for(int i = 0; i < dim; ++ i) {
			trueIRType = new PointerType(trueIRType);
		}
		num ++;
		Register finalReg = new Register(trueIRType, Integer.toString(num));
		nowBlock.AddInst(new Bitcast(nowBlock, finalReg, sizePointerIRType, moveReg, trueIRType));

		it.operand = finalReg;

		if(it.exprList.size() > 1) {
			Operand preSizeReg;
			Register preReg, incrReg, curReg, newIncrReg, condReg, addrReg;
			for(int i = 1; i < it.exprList.size(); ++ i) {
				preReg = finalReg;
				preSizeReg = sizeReg;
				num ++;
				incrReg = new Register(new PointerType(new IntType(32)), Integer.toString(num));
				nowBlock.AddInst(new Alloca(nowBlock, incrReg, new IntType(32)));
				nowBlock.AddInst(new Store(nowBlock, new IntType(32), new IntConst(0), incrReg));

				numLabel ++;
				BasicBlock condBlock = new BasicBlock("L" + numLabel, nowFunction);
				numLabel ++;
				BasicBlock bodyBlock = new BasicBlock("L" + numLabel, nowFunction);
				numLabel ++;
				BasicBlock incrBlock = new BasicBlock("L" + numLabel, nowFunction);
				numLabel ++;
				BasicBlock endBlock = new BasicBlock("L" + numLabel, nowFunction);

				nowBlock.AddInst(new Br(nowBlock, condBlock));
				nowFunction.blockList.add(nowBlock);

				nowBlock = condBlock;
				num ++;
				curReg = new Register(new IntType(32), Integer.toString(num));
				nowBlock.AddInst(new Load(nowBlock, curReg, new IntType(32), incrReg));
				num ++;
				condReg = new Register(new IntType(1), Integer.toString(num));
				nowBlock.AddInst(new Icmp(nowBlock, condReg, "slt", new IntType(32), curReg, preSizeReg));
				nowBlock.AddInst(new Br(nowBlock, condReg, bodyBlock, endBlock));
				nowFunction.blockList.add(nowBlock);

				nowBlock = bodyBlock;
				x = it.exprList.get(i);
				x.Accept(this);
				if(x.operand.loadneed) {
					num ++;
					sizeReg = new Register(new IntType(32), Integer.toString(num));
					nowBlock.AddInst(new Load(nowBlock, (Register) sizeReg, new IntType(32), x.operand));
				}
				else {
					sizeReg = x.operand;
				}

				num ++;
				mulReg = new Register(new IntType(32), Integer.toString(num));
				singleSize = 4;
				nowBlock.AddInst(new Binary(nowBlock, mulReg, "mul", new IntType(32), sizeReg, new IntConst(singleSize)));
				
				num ++;
				sumReg = new Register(new IntType(32), Integer.toString(num));
				nowBlock.AddInst(new Binary(nowBlock, sumReg, "add", new IntType(32), mulReg, new IntConst(4)));

				num ++;
				callReg = new Register(new PointerType(new IntType(8)), Integer.toString(num));
				nowparaList = new ArrayList<>();
				nowparaList.add(sumReg);
				nowBlock.AddInst(new Call(nowBlock, callReg, new PointerType(new IntType(8)), "_f_malloc", nowparaList));

				sizePointerIRType = new PointerType(new IntType(32));
				num ++;
				sizePointerReg = new Register(sizePointerIRType, Integer.toString(num));
				nowBlock.AddInst(new Bitcast(nowBlock, sizePointerReg, new PointerType(new IntType(8)), callReg, sizePointerIRType));

				nowBlock.AddInst(new Store(nowBlock, new IntType(32), sizeReg, sizePointerReg));

				num ++;
				moveReg = new Register(sizePointerIRType, Integer.toString(num));
				nowparaList = new ArrayList<>();
				nowparaList.add(new IntConst(1));
				nowBlock.AddInst(new Getelementptr(nowBlock, moveReg, sizePointerIRType, sizePointerReg, nowparaList));

				trueIRType = nowIRType;
				for(int j = 0; j < dim - i; ++ j) {
					trueIRType = new PointerType(trueIRType);
				}
				num ++;
				finalReg = new Register(trueIRType, Integer.toString(num));
				nowBlock.AddInst(new Bitcast(nowBlock, finalReg, sizePointerIRType, moveReg, trueIRType));

				num ++;
				addrReg = new Register(preReg.type, Integer.toString(num));
				nowparaList = new ArrayList<>();
				nowparaList.add(curReg);
				nowBlock.AddInst(new Getelementptr(nowBlock, addrReg, (PointerType) preReg.type, preReg, nowparaList));
				nowBlock.AddInst(new Store(nowBlock, trueIRType, finalReg, addrReg));

				nowBlock.AddInst(new Br(nowBlock, incrBlock));
				nowFunction.blockList.add(nowBlock);

				nowBlock = incrBlock;
				++ num;
				newIncrReg = new Register(new IntType(32), Integer.toString(num));
				nowBlock.AddInst(new Binary(nowBlock, newIncrReg, "add", new IntType(32), curReg, new IntConst(1)));
				nowBlock.AddInst(new Store(nowBlock, new IntType(32), newIncrReg, incrReg));
				nowBlock.AddInst(new Br(nowBlock, condBlock));
				nowFunction.blockList.add(nowBlock);

				nowBlock = endBlock;
			}
		}
	}

	@Override
	public void Visit(BinaryExprNode it) {
		if(it.op.equals("||") || it.op.equals("&&")) {
			it.expr1.Accept(this);
			IRType nowIRType;

			Operand operand1;
			IRType expr1IRType = it.expr1.operand.type;
			nowIRType = expr1IRType;
			if(it.expr1.operand.loadneed) {
				IRType newIRType = ((PointerType) expr1IRType).type;
				nowIRType = newIRType;
				num ++;
				operand1 = new Register(newIRType, Integer.toString(num));
				nowBlock.AddInst(new Load(nowBlock, (Register) operand1, newIRType, it.expr1.operand));
			}
			else {
				operand1 = it.expr1.operand;
			}

			numLabel ++;
			BasicBlock endBlock = new BasicBlock("L" + numLabel, nowFunction);
			numLabel ++;
			BasicBlock shortBlock = new BasicBlock("L" + numLabel, nowFunction);
			numLabel ++;
			BasicBlock longBlock = new BasicBlock("L" + numLabel, nowFunction);

			if(it.op.equals("||")) {
				num ++;
				Register ctrl = new Register(new PointerType(nowIRType), Integer.toString(num));
				nowBlock.AddInst(new Alloca(nowBlock, ctrl, nowIRType));
				nowBlock.AddInst(new Br(nowBlock, operand1, shortBlock, longBlock));
				nowFunction.blockList.add(nowBlock);

				BasicBlock tmpEndBlock = globalEndBlock;
				globalEndBlock = endBlock;

				nowBlock = shortBlock;
				nowBlock.AddInst(new Store(nowBlock, nowIRType, new BoolConst(true), ctrl));
				nowBlock.AddInst(new Br(nowBlock, endBlock));
				nowFunction.blockList.add(nowBlock);

				nowBlock = longBlock;
				it.expr2.Accept(this);
				
				Operand operand2;
				IRType expr2IRType = it.expr2.operand.type;
				if(it.expr2.operand.loadneed) {
					IRType newIRType = ((PointerType) expr2IRType).type;
					num ++;
					operand2 = new Register(newIRType, Integer.toString(num));
					nowBlock.AddInst(new Load(nowBlock, (Register) operand2, newIRType, it.expr2.operand));
				}
				else {
					operand2 = it.expr2.operand;
				}
				nowBlock.AddInst(new Store(nowBlock, nowIRType, operand2, ctrl));
				nowBlock.AddInst(new Br(nowBlock, endBlock));
				nowFunction.blockList.add(nowBlock);

				nowBlock = endBlock;
				it.operand = ctrl;
				it.operand.loadneed = true;

				globalEndBlock = tmpEndBlock;
			}
			else {
				num ++;
				Register ctrl = new Register(new PointerType(nowIRType), Integer.toString(num));
				nowBlock.AddInst(new Alloca(nowBlock, ctrl, nowIRType));
				nowBlock.AddInst(new Br(nowBlock, operand1, longBlock, shortBlock));
				nowFunction.blockList.add(nowBlock);

				BasicBlock tmpEndBlock = globalEndBlock;
				globalEndBlock = endBlock;

				nowBlock = shortBlock;
				nowBlock.AddInst(new Store(nowBlock, nowIRType, new BoolConst(false), ctrl));
				nowBlock.AddInst(new Br(nowBlock, endBlock));
				nowFunction.blockList.add(nowBlock);

				nowBlock = longBlock;
				it.expr2.Accept(this);
				
				Operand operand2;
				if(it.expr2.operand.loadneed) {
					num ++;
					operand2 = new Register(nowIRType, Integer.toString(num));
					nowBlock.AddInst(new Load(nowBlock, (Register) operand2, nowIRType, it.expr2.operand));
				}
				else {
					operand2 = it.expr2.operand;
				}
				nowBlock.AddInst(new Store(nowBlock, nowIRType, operand2, ctrl));
				nowBlock.AddInst(new Br(nowBlock, endBlock));
				nowFunction.blockList.add(nowBlock);

				nowBlock = endBlock;
				it.operand = ctrl;
				it.operand.loadneed = true;

				globalEndBlock = tmpEndBlock;
			}
			return;
		}

		if(it.op.equals("=")) {
			it.expr1.Accept(this);
			it.expr2.Accept(this);

			Operand operand2;
			IRType expr2IRType = it.expr2.operand.type;
			if(it.expr2.operand.loadneed) {
				IRType newIRType = ((PointerType) expr2IRType).type;
				num ++;
				operand2 = new Register(newIRType, Integer.toString(num));
				nowBlock.AddInst(new Load(nowBlock, (Register) operand2, newIRType, it.expr2.operand));
			}
			else {
				operand2 = it.expr2.operand;
			}

			nowBlock.AddInst(new Store(nowBlock, ((PointerType) it.expr1.operand.type).type, operand2, it.expr1.operand));
			it.operand = it.expr1.operand;
			return;
		}

		it.expr1.Accept(this);
		it.expr2.Accept(this);

		IRType nowIRType = it.type.GetIRType();

		Operand operand1;
		IRType expr1IRType = it.expr1.operand.type;
		if(it.expr1.operand.loadneed) {
			IRType newIRType = ((PointerType) expr1IRType).type;
			num ++;
			operand1 = new Register(newIRType, Integer.toString(num));
			nowBlock.AddInst(new Load(nowBlock, (Register) operand1, newIRType, it.expr1.operand));
		}
		else {
			operand1 = it.expr1.operand;
		}
		Operand operand2;
		IRType expr2IRType = it.expr2.operand.type;
		if(it.expr2.operand.loadneed) {
			IRType newIRType = ((PointerType) expr2IRType).type;
			num ++;
			operand2 = new Register(newIRType, Integer.toString(num));
			nowBlock.AddInst(new Load(nowBlock, (Register) operand2, newIRType, it.expr2.operand));
		}
		else {
			operand2 = it.expr2.operand;
		}

		if(operand1 instanceof IntConst && operand2 instanceof IntConst) {
			switch(it.op) {
				case "+": {
					it.operand = new IntConst(((IntConst) operand1).val + ((IntConst) operand2).val);
					break;
				}
				case "-": {
					it.operand = new IntConst(((IntConst) operand1).val - ((IntConst) operand2).val);
					break;
				}
				case "*": {
					it.operand = new IntConst(((IntConst) operand1).val * ((IntConst) operand2).val);
					break;
				}
				case "/": {
					it.operand = new IntConst(((IntConst) operand1).val / ((IntConst) operand2).val);
					break;
				}
				case "%": {
					it.operand = new IntConst(((IntConst) operand1).val % ((IntConst) operand2).val);
					break;
				}
				case "<<": {
					it.operand = new IntConst(((IntConst) operand1).val << ((IntConst) operand2).val);
					break;
				}
				case ">>": {
					it.operand = new IntConst(((IntConst) operand1).val >> ((IntConst) operand2).val);
					break;
				}
				case "<": {
					it.operand = new BoolConst(((IntConst) operand1).val < ((IntConst) operand2).val);
					break;
				}
				case "<=": {
					it.operand = new BoolConst(((IntConst) operand1).val <= ((IntConst) operand2).val);
					break;
				}
				case ">": {
					it.operand = new BoolConst(((IntConst) operand1).val > ((IntConst) operand2).val);
					break;
				}
				case ">=": {
					it.operand = new BoolConst(((IntConst) operand1).val >= ((IntConst) operand2).val);
					break;
				}
				case "!=": {
					it.operand = new BoolConst(((IntConst) operand1).val != ((IntConst) operand2).val);
					break;
				}
				case "==": {
					it.operand = new BoolConst(((IntConst) operand1).val == ((IntConst) operand2).val);
					break;
				}
				case "^": {
					it.operand = new IntConst(((IntConst) operand1).val ^ ((IntConst) operand2).val);
					break;
				}
				case "|": {
					it.operand = new IntConst(((IntConst) operand1).val | ((IntConst) operand2).val);
					break;
				}
				case "&": {
					it.operand = new IntConst(((IntConst) operand1).val & ((IntConst) operand2).val);
					break;
				}
			}
			return;
		}
		else if(operand1 instanceof BoolConst && operand2 instanceof BoolConst) {
			switch(it.op) {
				case "||": {
					it.operand = new BoolConst(((BoolConst) operand1).val || ((BoolConst) operand2).val);
					break;
				}
				case "&&": {
					it.operand = new BoolConst(((BoolConst) operand1).val && ((BoolConst) operand2).val);
					break;
				}
			}
			return;
		}

		if(it.expr1.type.type == basicType.String) {
			switch(it.op) {
				case "+": {
					num ++;
					Register newReg = new Register(nowIRType, Integer.toString(num));
					ArrayList<Operand> nowparaList = new ArrayList<>();
					nowparaList.add(operand1);
					nowparaList.add(operand2);
					nowBlock.AddInst(new Call(nowBlock, newReg, nowIRType, "_str_concatenate", nowparaList));

					it.operand = newReg;
					break;
				}
				case "<": {
					num ++;
					Register newReg = new Register(new IntType(1), Integer.toString(num));
					ArrayList<Operand> nowparaList = new ArrayList<>();
					nowparaList.add(operand1);
					nowparaList.add(operand2);
					nowBlock.AddInst(new Call(nowBlock, newReg, new IntType(1), "_str_lt", nowparaList));

					it.operand = newReg;
					break;
				}
				case "<=": {
					num ++;
					Register newReg = new Register(new IntType(1), Integer.toString(num));
					ArrayList<Operand> nowparaList = new ArrayList<>();
					nowparaList.add(operand1);
					nowparaList.add(operand2);
					nowBlock.AddInst(new Call(nowBlock, newReg, new IntType(1), "_str_le", nowparaList));

					it.operand = newReg;
					break;
				}
				case ">": {
					num ++;
					Register newReg = new Register(new IntType(1), Integer.toString(num));
					ArrayList<Operand> nowparaList = new ArrayList<>();
					nowparaList.add(operand1);
					nowparaList.add(operand2);
					nowBlock.AddInst(new Call(nowBlock, newReg, new IntType(1), "_str_gt", nowparaList));

					it.operand = newReg;
					break;
				}
				case ">=": {
					num ++;
					Register newReg = new Register(new IntType(1), Integer.toString(num));
					ArrayList<Operand> nowparaList = new ArrayList<>();
					nowparaList.add(operand1);
					nowparaList.add(operand2);
					nowBlock.AddInst(new Call(nowBlock, newReg, new IntType(1), "_str_ge", nowparaList));

					it.operand = newReg;
					break;
				}
				case "==": {
					num ++;
					Register newReg = new Register(new IntType(1), Integer.toString(num));
					ArrayList<Operand> nowparaList = new ArrayList<>();
					nowparaList.add(operand1);
					nowparaList.add(operand2);
					nowBlock.AddInst(new Call(nowBlock, newReg, new IntType(1), "_str_eq", nowparaList));

					it.operand = newReg;
					break;
				}
				case "!=": {
					num ++;
					Register newReg = new Register(new IntType(1), Integer.toString(num));
					ArrayList<Operand> nowparaList = new ArrayList<>();
					nowparaList.add(operand1);
					nowparaList.add(operand2);
					nowBlock.AddInst(new Call(nowBlock, newReg, new IntType(1), "_str_ne", nowparaList));

					it.operand = newReg;
					break;
				}
			}
		}

		switch(it.op) {
			case "+": {
				num ++;
				Register newReg = new Register(nowIRType, Integer.toString(num));
				nowBlock.AddInst(new Binary(nowBlock, newReg, "add", operand1.type, operand1, operand2));

				it.operand = newReg;
				break;
			}
			case "-": {
				num ++;
				Register newReg = new Register(nowIRType, Integer.toString(num));
				nowBlock.AddInst(new Binary(nowBlock, newReg, "sub", operand1.type, operand1, operand2));

				it.operand = newReg;
				break;
			}
			case "*": {
				num ++;
				Register newReg = new Register(nowIRType, Integer.toString(num));
				nowBlock.AddInst(new Binary(nowBlock, newReg, "mul", operand1.type, operand1, operand2));

				it.operand = newReg;
				break;
			}
			case "/": {
				num ++;
				Register newReg = new Register(nowIRType, Integer.toString(num));
				nowBlock.AddInst(new Binary(nowBlock, newReg, "sdiv", operand1.type, operand1, operand2));

				it.operand = newReg;
				break;
			}
			case "%": {
				num ++;
				Register newReg = new Register(nowIRType, Integer.toString(num));
				nowBlock.AddInst(new Binary(nowBlock, newReg, "srem", operand1.type, operand1, operand2));

				it.operand = newReg;
				break;
			}
			case "<<": {
				num ++;
				Register newReg = new Register(nowIRType, Integer.toString(num));
				nowBlock.AddInst(new Binary(nowBlock, newReg, "shl", operand1.type, operand1, operand2));

				it.operand = newReg;
				break;
			}
			case ">>": {
				num ++;
				Register newReg = new Register(nowIRType, Integer.toString(num));
				nowBlock.AddInst(new Binary(nowBlock, newReg, "ashr", operand1.type, operand1, operand2));

				it.operand = newReg;
				break;
			}
			case "<": {
				num ++;
				Register newReg = new Register(nowIRType, Integer.toString(num));
				nowBlock.AddInst(new Icmp(nowBlock, newReg, "slt", operand1.type, operand1, operand2));

				it.operand = newReg;
				break;
			}
			case "<=": {
				num ++;
				Register newReg = new Register(nowIRType, Integer.toString(num));
				nowBlock.AddInst(new Icmp(nowBlock, newReg, "sle", operand1.type, operand1, operand2));

				it.operand = newReg;
				break;
			}
			case ">": {
				num ++;
				Register newReg = new Register(nowIRType, Integer.toString(num));
				nowBlock.AddInst(new Icmp(nowBlock, newReg, "sgt", operand1.type, operand1, operand2));

				it.operand = newReg;
				break;
			}
			case ">=": {
				num ++;
				Register newReg = new Register(nowIRType, Integer.toString(num));
				nowBlock.AddInst(new Icmp(nowBlock, newReg, "sge", operand1.type, operand1, operand2));

				it.operand = newReg;
				break;
			}
			case "!=": {
				num ++;
				Register newReg = new Register(nowIRType, Integer.toString(num));
				nowBlock.AddInst(new Icmp(nowBlock, newReg, "ne", operand1.type, operand1, operand2));

				it.operand = newReg;
				break;
			}
			case "==": {
				num ++;
				Register newReg = new Register(nowIRType, Integer.toString(num));
				nowBlock.AddInst(new Icmp(nowBlock, newReg, "eq", operand1.type, operand1, operand2));

				it.operand = newReg;
				break;
			}
			case "^": {
				num ++;
				Register newReg = new Register(nowIRType, Integer.toString(num));
				nowBlock.AddInst(new Binary(nowBlock, newReg, "xor", operand1.type, operand1, operand2));

				it.operand = newReg;
				break;
			}
			case "|": {
				num ++;
				Register newReg = new Register(nowIRType, Integer.toString(num));
				nowBlock.AddInst(new Binary(nowBlock, newReg, "or", operand1.type, operand1, operand2));

				it.operand = newReg;
				break;
			}
			case "&": {
				num ++;
				Register newReg = new Register(nowIRType, Integer.toString(num));
				nowBlock.AddInst(new Binary(nowBlock, newReg, "and", operand1.type, operand1, operand2));

				it.operand = newReg;
				break;
			}
		}
	}

	@Override
	public void Visit(ExprListNode it) {
		it.exprList.forEach(x -> x.Accept(this));
	}
}
