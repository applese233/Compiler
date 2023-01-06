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
import IR.inst.Ret;
import IR.inst.Store;
import IR.operand.GlobalVariable;
import IR.operand.Register;
import IR.type.ClassType;
import IR.type.IRType;
import IR.type.PointerType;
import IR.type.VoidType;
import Util.Scope;
import Util.Type;
import AST.*;
import AST.DefNode.ClassDefNode;
import AST.DefNode.FuncDefNode;
import AST.StmtNode.VarDecStmtNode;

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
			
		}
	}
}
