package frontend;

import java.util.ArrayList;

import AST.*;
import AST.DefNode.*;
import AST.StmtNode.*;
import AST.ExprNode.*;
import Util.*;
import Util.error.semanticError;

public class TypeCollector implements ASTVisitor {
	public Scope globalScope;
	public String nowClass = null;

	public TypeCollector(Scope _globalScope) {
		globalScope = _globalScope;
	}

	@Override
	public void Visit(ProgNode it) {
		it.list.forEach(x -> x.Accept(this));
	}

	@Override
	public void Visit(TypeNode it) {

	}

	@Override
	public void Visit(FuncDefNode it) {
		System.out.println("TypeCollectFuncDef: " + it.id);
		Type tmp;
		if(nowClass == null) {
			tmp = globalScope.funcMap.get(it.id);
		}
		else {
			tmp = (globalScope.typeMap.get(nowClass)).funcMap.get(it.id);
		}
		tmp.functionReturnType = globalScope.TypeGet(it.type, it.pos);
		it.paralist.forEach(x -> tmp.functionParameters.add(new Type(globalScope.TypeGet(x.type, x.pos), x.id)));
	}

	@Override
	public void Visit(ClassDefNode it) {
		System.out.println("TypeCollectClassDef: " + it.id);
		nowClass = it.id;
		it.varlist.forEach(x -> x.Accept(this));
		it.funclist.forEach(x -> x.Accept(this));
		nowClass = null;
	}

	@Override
	public void Visit(VarDecStmtNode it) {
		System.out.println("TypeCollectVarDec: " + it.id);
		Type tmp;
		if(nowClass == null) {
			tmp = globalScope.varMap.get(it.id);
		}
		else {
			tmp = (globalScope.typeMap.get(nowClass)).varMap.get(it.id);
		}
		tmp.type = globalScope.TypeGet(it.type, it.pos).type;
	}

	@Override
	public void Visit(VarDefStmtNode it) {
		System.out.println("VarDef: " + it.pos);
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
