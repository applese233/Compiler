package backend;

import java.util.ArrayList;
import java.util.HashMap;

import IR.BasicBlock;
import IR.Function;
import IR.IRScope;
import IR.IRVisitor;
import IR.Module;
import IR.inst.Alloca;
import IR.inst.Bitcast;
import IR.inst.Br;
import IR.inst.Call;
import IR.inst.Define;
import IR.inst.Getelementptr;
import IR.inst.Global;
import IR.inst.Load;
import IR.inst.Ret;
import IR.inst.Store;
import IR.operand.BoolConst;
import IR.operand.GlobalVariable;
import IR.operand.IntConst;
import IR.operand.NullOperand;
import IR.operand.Operand;
import IR.operand.Register;
import IR.operand.StringConst;
import IR.type.ClassType;
import IR.type.IRType;
import IR.type.IntType;
import IR.type.PointerType;
import IR.type.VoidType;
import IR.type.ArrayType;
import Util.Scope;
import Util.Type;
import Util.Type.basicType;
import AST.*;
import AST.DefNode.ClassDefNode;
import AST.DefNode.FuncDefNode;
import AST.ExprNode.BoolExprNode;
import AST.ExprNode.ClassExprNode;
import AST.ExprNode.ExprNode;
import AST.ExprNode.FuncExprNode;
import AST.ExprNode.IdExprNode;
import AST.ExprNode.IndexExprNode;
import AST.ExprNode.IntExprNode;
import AST.ExprNode.LambdaExprNode;
import AST.ExprNode.NullExprNode;
import AST.ExprNode.PureExprStmtNode;
import AST.ExprNode.StringExprNode;
import AST.ExprNode.ThisExprNode;
import AST.StmtNode.BreakStmtNode;
import AST.StmtNode.ContinueStmtNode;
import AST.StmtNode.EmptyStmtNode;
import AST.StmtNode.ForStmtNode;
import AST.StmtNode.IfStmtNode;
import AST.StmtNode.ReturnStmtNode;
import AST.StmtNode.SuiteStmtNode;
import AST.StmtNode.VarDecStmtNode;
import AST.StmtNode.VarDefStmtNode;
import AST.StmtNode.WhileStmtNode;

public class IRBuilder implements ASTVisitor {
	public IR.Module module;
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

	public IRBuilder(Scope _globalScope, IR.Module _module) {
		globalScope = _globalScope;
		module = _module;
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
		module.funcList.add(initFunction);
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

		module.funcList.add(nowFunction);
		nowIRScope = nowIRScope.pIRScope;
	}

	@Override
	public void Visit(ClassDefNode it) {
		if(classCollector) {
			Type nowType = globalScope.typeMap.get(it.id);
			IRType nowIRType = nowType.GetIRType();
			for(VarDecStmtNode x : it.varlist) {
				Type xType = globalScope.TypeGet(x.type, null);
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
			module.globalList.add(new Global(nowIRType));
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
				module.globalList.add(new Global(nowVar, nowIRType, new NullOperand()));
			}
			else if(nowIRType instanceof IntType) {
				module.globalList.add(new Global(nowVar, nowIRType, new IntConst(0)));
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
		module.globalList.add(new Global(nowVar, globalIRType, new StringConst(globalIRType, res)));

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

	}
}
