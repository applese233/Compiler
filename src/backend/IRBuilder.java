package backend;

import java.util.ArrayList;
import java.util.HashMap;

import IR.BasicBlock;
import IR.Function;
import IR.IRScope;
import IR.IRVisitor;
import IR.Module;
import IR.inst.Define;
import IR.inst.Ret;
import IR.operand.GlobalVariable;
import IR.operand.Register;
import IR.type.ClassType;
import IR.type.IRType;
import IR.type.VoidType;
import Util.Scope;
import Util.Type;
import AST.*;
import AST.DefNode.ClassDefNode;
import AST.DefNode.FuncDefNode;

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
		}
	}
}
