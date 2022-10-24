package frontend;

import AST.*;
import AST.DefNode.*;
import AST.ExprNode.BinaryExprNode;
import AST.ExprNode.ExprListNode;
import AST.ExprNode.FuncExprNode;
import AST.ExprNode.IdExprNode;
import AST.ExprNode.IndexExprNode;
import AST.ExprNode.IntExprNode;
import AST.ExprNode.LambdaExprNode;
import AST.ExprNode.NewExprNode;
import AST.ExprNode.PreExprNode;
import AST.ExprNode.PureExprStmtNode;
import AST.ExprNode.SufExprNode;
import AST.ExprNode.ThisExprNode;
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
		if(!mainFunc.type != Int) {
			throw new semanticError("Return type of main Error.", it.pos);
		}
		if(mainFunc.functionParameters.size() != 0) {
			throw new semanticError("There exists parameters in main function.", it.pos);
		}
		
		it.list.forEach(x -> x.Accept(this));
	}

	@Override
	public void Visit(TypeNode it) {

	}

	@Override
	public void Visit(FuncDefNode it) {
		nowScope = new Scope(nowScope);
		
		if(it.functionParameters != null) {
			it.functionParameters.forEach(x -> nowScope.NewVar(x.id, new Type(globalScope.TypeGet(x.type), x.id), x.pos));
		}
		
		hasReturn = false;
		if(it.type != null) {
			returnType = globalScope.TypeGet(it.type);
		}
		else {
			returnType = new Type(Void, 0);
		}
		
		it.suite.Accept(this);

		if(it.id.equals("main")) {
			hasReturn = true;
		}
		if(!hasReturn && it.type != null && !it.type.type.equals("void")) {
			throw new semanticError("Missing Return", it.pos);
		}
		
		nowScope = nowScope.pScope;
	}

	@Override
	public void Visit(ClassDefNode it) {
		nowScopre = newScope(nowScope);

		nowClass = (Type)globalScope.typeMap.get(it.id);
		nowClass.varMap.forEach((x, y) -> nowScope.NewVar(x, y, it.pos));
		nowClass.typeMap.forEach((x, y) -> nowScope.NewType(x, y, it.pos));

		it.funclist.forEach(x -> x.Accept(this));

		if(it.struct != null) {
			if(!it.struct.id.equals(it.id)) {
				throw new semanticError("Constructor Error", it.pos);
			}
			it.struct.Accept(this);
		}

		nowClass = null;
		nowScope = nowScope.pScope;

	}

	@Override
	public void Visit(VarDecStmtNode it) {
		Type tmp = globalScope.typeGet(it.type);

		if(tmp.type == Void) {
			throw new semanticError("Variable Type Error", it.pos);
		}
		
		if(it.expr != null) {
			it.expr.Accept(this);
			if(!it.expr.type.type == tmp.type) {
				throw new semanticError("Variable Type not equal to Expr Type", it.pos);
			}
		}

		nowScope.NewVar(it.id, new Type(tmp, it.id), it.pos);
	}

	@Override
	public void Visit(VarDefStmtNode it) {
		it.varList.forEach(x -> x.Accept(this));
	}

	@Override
	public void Visit(SuiteStmtNode it) {
		it.stmtList.forEach(x -> {
			if(x instanceof suiteStmtNode) {
				nowScope = new Scope(nowScope);
				x.Accept(this);
				nowScope = nowScope.pScope;
			}
			else {
				x.Accept(this);
			}
		});
	}

	@Override
	public void Visit(IfStmtNode it) {
		it.expr.Accept(this);
		if(!it.expr.type.type != Bool) {
			throw new semanticError("IfContidion Type Error", it.pos);
		}

		++ depth;
		nowScope = new Scope(nowScope);

		it.stmt.Accept(this);

		-- depth;
		nowScope = nowScope.pScope;
	}

	@Override
	public void Visit(ForStmtNode it) {
		if(it.init != null) {
			it.init.Accept(this);
		}
		if(it.cond != null) {
			it.cond.Accept(this);
			if(it.cond.type.type != Bool) {
				throw new semanticError("ForCondition Type Error", it.pos);
			}
		}
		if(it.incr != null) {
			it.incr.Accept(this);
		}
		
		++ depth;
		nowScope = new Scope(nowScope);

		it.stmt.Accept(this);

		-- depth;
		nowScope = nowScope.pScope;
	}

	@Override
	public void Visit(ReturnStmtNode it) {
		hasReturn = true;
		if(it.expr != null) {
			if(lambdaReturn) {
				it.expr.Accept(this);
				lambdaType = it.expr.type;
			}
			else {
				it.expr.Accept(this);
				if(it.expr.type.type != returnType.type)
					throw new semanticError("Return Type not Equal", it.pos);
			}
		}
		else {
			if(lambdaReturn) {
				lambaType = new Type(Void, 0);
			}
			else {
				if(returnType.type != Void) {
					throw new semanticError("Return Type not Equal", it.pos);
				}
			}
		}
	}

	@Override
	public void Visit(BreakStmtNode it) {
		if(depth < 1)
			throw new semanticError("Invlid Break", it.pos);
	}

	@Override
	public void Visit(ContinueStmtNode it) {
		if(depth < 1)
			throw new semanticError("Invlid Continue", it.pos);
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
		it.type = new Type(Int, 0);
	}

	@Override
	public void Visit(BoolExprNode it) {
		it.type = new Type(Bool, 0);
	}

	@Override
	public void Visit(StringExprNode it) {
		it.type = new Type(String, 0);
	}

	@Override
	public void Visit(NullExprNode it) {
		it.type = new Type(Null, 0);
	}

	@Override
	public void Visit(ThisExprNode it) {
		if(nowClass == null)
			throw new semanticError("Invalid This", it.pos);
		it.type = nowClass;
	}

	@Override
	public void Visit(IdExprNode it) {
		it.type = nowScope.VarGet(it.id, true, it.pos);
	}

	@Override
	public void Visit(LambdaExprNode it) {
		nowScope = new Scope(nowScope);

		if(it.functionParameters.size() != it.exprList.exprList.size()) {
			throw new semanticError("Number of parameters in lambda expr not match", it.pos);
		}

		if(it.exprList.exprList.size() != 0) {
			it.exprList.exprList.forEach(x -> x.Accept(this));
		}

		if(it.functionParameters != null) {
			it.functionParameters.forEach(x -> nowScope.NewVar(x.id, new Type(globalScope.TypeGet(x.type), x.id), x.pos));
		}

		if(it.functionParameters != null) {
			for(int i = 0; i < it.functionParameters.size(); ++ i) {
				if(!globalScope.TypeGet(it.functionParameters.get(i).type).type != it.exprList.exprList.get(i).type.type) {
					throw new semanticError("Type of parameters in lambda not match", it.pos);
				}
			}
		}

		hasReturn = false;
		lambdaReturn = true;

		it.suite.Accept(this);

		if(!hasReturn) {
			it.type = new Type(Void, 0);
		}
		else {
			it.type = lambdaType;
		}

		hasReturn = false;
		lambdaReturn = false;
		nowScope = nowScope.pScope;
	}

	@Override
	public void Visit(FuncExprNode it) {
		if(it.id instanceof IdExprNode) {
			it.id.type = nowScope.FuncGet(((IdExprNode)it.id).id, true, it.pos);
		}
		else {
			it.id.Accept(this);
		}

		if(it.id.type.type != Function) {
			throw new semanticError("Function Undefined", it.pos);
		}

		it.exprList.Accept(this);
		
		Type tmp = it.id.type;
		if(it.exprList.exprList.size() != tmp.functionParameters.size()) {
			throw new semanticError("Number of parameters in function not match", it.pos);
		}

		for(int i = 0; i < tmp.functionParameters.size(); ++ i) {
			if(it.exprList.exprList.get(i).type.type != tmp.functionParameters.get(i).type) {
				throw new semanticError("Type of parameters in function not match", it.pos);
			}
		}

		it.type.type = tmp.type;
	}

	@Override
	public void Visit(IndexExprNode it) {
		it.bas.Accept(this);
		it.off.Accept(this);

		if(it.bas.type.dim == 0) {
			throw new semanticError("Undefined Array", it.pos);
		}

		if(it.off.type.type != Int) {
			throw new semanticError("Invalid offset", it.pos);
		}

		Type tmp = it.bas.type;
		if(tmp.dim == 1)
			it.type.type = tmp.type;
		else
			it.type = new Type(tmp, tmp.dim - 1);
	}

	@Override
	public void Visit(ClassExprType it) {
		it.name.Accept(this);

		if(it.name.type.dim != 0 && it.isFunc && it.id.equals("size")) {
			Type res = new Type("size");
			res.type = new Type(Int, 0);
			it.type = res;
			return;
		}

		if(it.name.type.type == String && it.isFunc && it.id.equals("length")) {
			Type res = new Type("length");
			res.type = new Type(Int, 0);
			it.type = res;
			return;
		}

		if(it.name.type.type == String && it.isFunc && it.id.equals("substring")) {
			Type res = new Type("substring");
			res.type = new Type(String, 0);
			res.functionParameters.add(new Type(Int, "left"));
			res.functionParameters.add(new Type(Int, "right"));
			it.type = res;
			return;
		}

		if(it.name.type.type == String && it.isFunc && it.id.equals("parseInt")) {
			Type res = new Type("parseInt");
			res.type = new Type(Int, 0);;
			it.type = res;
			return;
		}

		if(it.name.type.type == String && it.isFunc && it.id.equals("ord")) {
			Type res = new Type("ord");
			res.type = new Type(Int, 0);
			res.functionParameters.add(New Type(New Type(Int, 0), "pos"));
			return;
		}

		if(it.name.type.type != Class) {
			throw new semanticError("Class Undefined", it.pos);
		}
		
		Type preClass = it.name.type;
		if(it.isFunc) {
			if(!preClass.funcMap.containsKey(it.id)) {
				throw new semanticError("Undefined function in Class", it.pos);
			}
			it.type = preClass.funcMap.get(it.id);
		}
		else {
			if(!preClass.varMap.containsKey(it.id)) {
				throw new semanticError("Undefined variable in Class", it.pos);
			}
			it.type = preClass.varMap.get(it.id);
		}
	}

	@Override
	public void Visit(SufExprNode it) {
		it.expr.Accept(this);
		
		if(it.expr.type.type != Int) {
			throw new semanticError("Error Type of Suffix Expression", it.pos);
		}

		if(!it.expr.assign) {
			throw new semanticError("Suffix Assign Error", it.pos);
		}
		it.type = it.expr.type;
	}

	@Override
	public void Visit(PreExprNode it) {
		it.expr.Accept(this);
		switch(it.op) {
			case "++", "--" -> {
				if(it.expr.type.type != Int) {
					throw new semanticError("Error Type of Prefix Expression", it.pos);
				}
				if(!it.expr.assign) {
					throw new semanticError("Prefix Assign Error", it.pos);
				}
				it.assign = true;
				it.type = new Type(Int, 0);
			}
			case "+", "-", "~" -> {
				if(it.expr.type.type != Int) {
					throw new semanticError("Error Type of Prefix Expression", it.pos);
				}
				it.type = new Type(Int, 0);
			}
			case "!" -> {
				if(it.expr.type.type != Bool) {
					throw new semanticError("Error Type of Prefix Expression", it.pos);
				}
				it.type = new Type(Bool, 0);
			}
			default -> {
				throw new semanticError("Error Prefix Expression", it.pos);
			}
		}
	}

	@Override
	public void Visit(NewExprNode it) {
		if(it.exprList != null) {
			it.exprList.forEach(x -> {
				x.Accept(this);
				if(x.type.type != Int) {
					throw new semanticError("New Array's Parameter Error", it.pos);
				}
			});
		}
		it.type = globalScope.TypeGet(it.typeNode);
	}

	@Override
	public void Visit(BinaryExprNode it) {
		it.expr1.Accept(this);
		it.expr2.Accept(this);

		if(it.expr1.type.type == Null) {
			throw new semanticError("Binary Expression Expr1's Type Error", it.pos);
		}

		switch(it.op) {
			case "-", "*", "/", "%", "<<", ">>", "&", "|", "^" -> {
				if(it.expr1.type.type != Int || it.expr2.type.type != Int) {
					throw new semanticError("Error Type of Binary Expression", it.pos);
				}
				it.type = new Type(Int, 0);
				break;
			}
			case "+" -> {
				if(it.expr1.type.type == Int && it.expr2.type.type == Int) {
					it.type = new Type(Int, 0);
				}
				else if(it.expr1.type.type == String && it.expr2.type.type == String) {
					it.type = new Type(String, 0);
				}
				else {
					throw new semanticError("Error Type of Binary Expression", it.pos);
				}
				break;
			}
			case "<", ">", "<=", ">=" -> {
				if((it.expr1.type.type == Int && it.expr2.type.type == Int) || (it.expr1.type.type == String && it.expr2.type.type == String)) {
					it.type = new Type(Bool, 0);
				}
				else {
					throw new semanticError("Error Type of Binary Expression", it.pos);
				}
				break;
			}
			case "&&", "||" -> {
				if(it.expr1.type.type == Bool && it.expr2.type.type == Bool) {
					it.type = new Type(Bool, 0);
				}
				else {
					throw new semanticError("Error Type of Binary Expression", it.pos);
				}
				break;
			}
			case "==", "!=" -> {
				if(it.expr1.type.type == it.expr2.type.type) {
					it.type = new Type(Bool, 0);
				}
				else {
					throw new semanticError("Error Type of Binary Expression", it.pos);
				}
				break;
			}
			case "=" -> {
				if(it.expr1.type.type != it.expr2.type.type) {
					throw new semanticError("Error Type of Binary Expression", it.pos);
				}
				if(!it.expr1.assign) {
					throw new semanticError("Expression wasn't assigned", it.pos);
				}
				it.assign = true;
				it.type = it.expr1.type;
				break;
			}
			default -> {
				throw new semanticError("Invalid Binary Operator", it.pos);
			}
		}
	}

	@Override
	public void Visit(ExprListNode it) {
		it.exprList.forEach(x -> x.Accept(this));
	}
}