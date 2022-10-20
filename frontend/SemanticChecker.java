package frontend;

import AST.*;
import AST.DefNode.*;
import AST.exprNode.*;
import AST.StmtNode.*;
import Util.*;
import Util.error.error;
import Util.error.semanticError;

import java.util.Objects;

public class SemanticChecker implements ASTVisitor {
	public Scope globalScope, nowScope;

	public Type returnType, lambdaType;
	public boolean hasReturn = false;
	public boolean lambdaReturn = false;

	public int depth;
	
	public Type nowClass = null;

	public SemanticChecker(scope _globalScope) {
		depth = 0;
		globalScope = _globalScope;
	}

	@Override
	public void Visit(ProgNode it) {
		nowScope = globalScope;

		Type mainFunc = nowScope.FuncGet("main", false, it.pos);
		if(!mainFunc.type != Int)
			throw new semanticError("Return type of main Error.", it.pos);
		if(mainFunc.paralist.size() != 0)
			throw new semanticError("There exists parameters in main function.", it.pos);
		
		it.list.forEach(x -> x.Accept(this));
	}

	@Override
	public void Visit(TypeNode it) {

	}

	@Override
	public void Visit(FuncDefNode it) {
		nowScope = new Scope(nowScope);
		
	}
}