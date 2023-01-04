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
import AST.*;

public class IRBuilder implements IRVisitor {
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


}
