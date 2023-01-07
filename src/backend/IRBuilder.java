package backend;

import java.util.ArrayList;
import java.util.HashMap;

import IR.BasicBlock;
import IR.Function;
import IR.IRScope;
import IR.IRVisitor;
import IR.Module;
import IR.inst.Alloca;
import IR.inst.Call;
import IR.inst.Define;
import IR.inst.Global;
import IR.inst.Load;
import IR.inst.Ret;
import IR.inst.Store;
import IR.operand.GlobalVariable;
import IR.operand.IntConst;
import IR.operand.NullOperand;
import IR.operand.Operand;
import IR.operand.Register;
import IR.type.ClassType;
import IR.type.IRType;
import IR.type.IntType;
import IR.type.PointerType;
import IR.type.VoidType;
import IR.type.ArrayType;
import Util.Scope;
import Util.Type;
import AST.*;
import AST.DefNode.ClassDefNode;
import AST.DefNode.FuncDefNode;
import AST.StmtNode.IfStmtNode;
import AST.StmtNode.SuiteStmtNode;
import AST.StmtNode.VarDecStmtNode;
import AST.StmtNode.VarDefStmtNode;

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
		}
	}
}
