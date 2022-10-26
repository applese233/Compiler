package frontend;

import java.util.ArrayList;

import AST.*;
import AST.DefNode.*;
import AST.StmtNode.*;
import AST.ExprNode.*;
import Util.*;

public class SymbolCollector implements ASTVisitor {
	public Scope globalScope, nowScope;
	public String nowClass = null;

	public SymbolCollector(Scope _globalScope) {
		globalScope = _globalScope;

		globalScope.typeMap.put("int", new Type(Type.basicType.Int, -1));
		// System.out.println(globalScope.typeMap.get("int").type);
		globalScope.typeMap.put("bool", new Type(Type.basicType.Bool, -1));
		globalScope.typeMap.put("string", new Type(Type.basicType.String, -1));
		globalScope.typeMap.put("null", new Type(Type.basicType.Null, -1));
		globalScope.typeMap.put("void", new Type(Type.basicType.Void, -1));

		ArrayList<Type> paratmp1 = new ArrayList<>();
		paratmp1.add(new Type(Type.basicType.String, "str"));
		Type tmp1 = new Type("print", new Type(Type.basicType.Void, -1), paratmp1);
		globalScope.funcMap.put("print", tmp1);

		Type tmp2 = new Type("println", new Type(Type.basicType.Void, -1), paratmp1);
		globalScope.funcMap.put("println", tmp2);

		ArrayList<Type> paratmp2 = new ArrayList<>();
		paratmp2.add(new Type(Type.basicType.Int, "n"));
		Type tmp3 = new Type("printInt", new Type(Type.basicType.Void, -1), paratmp2);
		globalScope.funcMap.put("printInt", tmp3);

		Type tmp4 = new Type("printlnInt", new Type(Type.basicType.Void, -1), paratmp2);
		globalScope.funcMap.put("printlnInt", tmp4);

		Type tmp5 = new Type("getString", new Type(Type.basicType.String, -1), new ArrayList<>());
		globalScope.funcMap.put("getString", tmp5);

		Type tmp6 = new Type("getInt", new Type(Type.basicType.Int, -1), new ArrayList<>());
		globalScope.funcMap.put("getInt", tmp6);

		ArrayList<Type> paratmp3 = new ArrayList<>();
		paratmp3.add(new Type(Type.basicType.Int, "i"));
		Type tmp7 = new Type("toString", new Type(Type.basicType.String, -1), paratmp3);
		globalScope.funcMap.put("toString", tmp7);

	}

	@Override
	public void Visit(ProgNode it) {
		nowScope = globalScope;
		it.list.forEach(x -> x.Accept(this));
	}

	@Override
	public void Visit(TypeNode it) {

	}

	@Override
	public void Visit(FuncDefNode it) {
		System.out.println(it.id);
		System.out.println(nowScope);
		System.out.println(nowScope.pScope);
		nowScope.NewFunc(it.id, new Type(it.id, new Type(it.id), new ArrayList<>()), it.pos);
	}

	@Override
	public void Visit(ClassDefNode it) {
		nowScope = new Scope(nowScope);
		nowClass = it.id;
		System.out.println(it.id);
		Type res = new Type(it.id, 0, false);
		it.varlist.forEach(x -> x.Accept(this));
		it.funclist.forEach(x -> x.Accept(this));
		if(it.struct != null) {
			res.struct = new Type(it.id, new Type(Type.basicType.Void, -1), new ArrayList<>());
		}
		System.out.println("ClassDef: " + it.id);
		System.out.println(nowScope.funcMap);
		System.out.println(nowScope.varMap);
		System.out.println(it.funclist);
		res.varMap = nowScope.varMap;
		res.funcMap = nowScope.funcMap;
		nowScope = nowScope.pScope;
		nowClass = null;
		nowScope.NewType(it.id, res, it.pos);
	}

	@Override
	public void Visit(VarDecStmtNode it) {
		nowScope.NewVar(it.id, new Type(it.id), it.pos);
	}

	@Override
	public void Visit(VarDefStmtNode it) {
		
	}

	@Override
	public void Visit(SuiteStmtNode it) {

	}

	@Override
	public void Visit(IfStmtNode it) {

	}

	@Override
	public void Visit(WhileStmtNode it) {

	}

	@Override
	public void Visit(ForStmtNode it) {

	}

	@Override
	public void Visit(ReturnStmtNode it) {

	}

	@Override
	public void Visit(BreakStmtNode it) {

	}

	@Override
	public void Visit(ContinueStmtNode it) {

	}

	@Override
	public void Visit(PureExprStmtNode it) {

	}

	@Override
	public void Visit(EmptyStmtNode it) {

	}

	@Override
	public void Visit(IntExprNode it) {

	}

	@Override
	public void Visit(BoolExprNode it) {

	}

	@Override
	public void Visit(StringExprNode it) {

	}

	@Override
	public void Visit(NullExprNode it) {

	}

	@Override
	public void Visit(ThisExprNode it) {

	}

	@Override
	public void Visit(IdExprNode it) {

	}

	@Override
	public void Visit(LambdaExprNode it) {

	}

	@Override
	public void Visit(FuncExprNode it) {

	}

	@Override
	public void Visit(IndexExprNode it) {

	}

	@Override
	public void Visit(ClassExprNode it) {

	}
	
	@Override
	public void Visit(SufExprNode it) {

	}

	@Override
	public void Visit(PreExprNode it) {

	}

	@Override
	public void Visit(NewExprNode it) {

	}

	@Override
	public void Visit(BinaryExprNode it) {

	}

	@Override
	public void Visit(ExprListNode it) {

	}

}
