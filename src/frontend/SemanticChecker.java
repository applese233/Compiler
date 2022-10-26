package frontend;

import AST.*;
import AST.DefNode.*;
import AST.ExprNode.*;
import AST.StmtNode.*;
import Util.*;
import Util.error.semanticError;
import Util.Scope;

import java.util.ArrayList;

public class SemanticChecker implements ASTVisitor {
	public Scope globalScope, nowScope;

	public Type returnType, lambdaType;
	public boolean hasReturn = false;
	public boolean lambdaReturn = false;

	public int depth;
	
	public Type nowClass = null;

	public SemanticChecker(Scope _globalScope) {
		depth = 0;
		globalScope = _globalScope;
	}

	@Override
	public void Visit(ProgNode it) {
		nowScope = globalScope;

		Type mainFunc = nowScope.FuncGet("main", false, it.pos);
		System.out.println("mainFunc.type = " + mainFunc.type + " " + mainFunc.functionReturnType.type);
		if(mainFunc.functionReturnType.type != Type.basicType.Int) {
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
		System.out.println("FuncDef: " + it.type);
		nowScope = new Scope(nowScope);
		
		if(it.paralist != null) {
			it.paralist.forEach(x -> nowScope.NewVar(x.id, new Type(globalScope.TypeGet(x.type, x.pos), x.id), x.pos));
		}
		
		hasReturn = false;
		if(it.type != null) {
			returnType = globalScope.TypeGet(it.type, it.pos);
		}
		else {
			returnType = new Type(Type.basicType.Void, -1);
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
		nowScope = new Scope(nowScope);

		System.out.println("ClassDef: " + it.id);
		nowClass = globalScope.typeMap.get(it.id);
		System.out.println(nowClass.varMap);
		System.out.println(nowClass.funcMap);
		System.out.println(nowClass.type);
		nowClass.varMap.forEach((x, y) -> nowScope.NewVar(x, y, it.pos));
		nowClass.funcMap.forEach((x, y) -> nowScope.NewFunc(x, y, it.pos));

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
		System.out.println("VarDec: " + it.id + " " + it.expr + " " + it.type.type);
		Type tmp = globalScope.TypeGet(it.type, it.pos);
		System.out.println("What: " + tmp.type + " " + tmp.dim + " " + tmp.funcMap);

		if(tmp.type == Type.basicType.Void) {
			throw new semanticError("Variable Type Error", it.pos);
		}
		
		if(it.expr != null) {
			it.expr.Accept(this);
			System.out.println("Checker Visit VarDec: " + it.expr.assign);
			System.out.println(it.expr.type.type);
			if(!tmp.TypeEqual(it.expr.type)) {
				throw new semanticError("Variable Type not equal to Expr Type", it.pos);
			}
		}
		System.out.println(tmp.type + " " + tmp.dim);

		nowScope.NewVar(it.id, new Type(tmp, it.id), it.pos);
	}

	@Override
	public void Visit(VarDefStmtNode it) {
		System.out.println("VarDef: " + it.pos);
		it.varList.forEach(x -> x.Accept(this));
	}

	@Override
	public void Visit(SuiteStmtNode it) {
		it.stmtList.forEach(x -> {
			if(x instanceof SuiteStmtNode) {
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
		if(!it.expr.type.Equal(Type.basicType.Bool)) {
			throw new semanticError("If Contidion Type Error", it.pos);
		}

		nowScope = new Scope(nowScope);
		it.trueStmt.Accept(this);
		nowScope = nowScope.pScope;

		if(it.falseStmt != null) {
			nowScope = new Scope(nowScope);
			it.falseStmt.Accept(this);
			nowScope = nowScope.pScope;
		}
	}

	@Override
	public void Visit(ForStmtNode it) {
		if(it.init != null) {
			it.init.Accept(this);
		}
		if(it.cond != null) {
			it.cond.Accept(this);
			if(!it.cond.type.Equal(Type.basicType.Bool)) {
				throw new semanticError("For Condition Type Error", it.pos);
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
	public void Visit(WhileStmtNode it) {
		it.expr.Accept(this);
		if(!it.expr.type.Equal(Type.basicType.Bool)) {
			throw new semanticError("While Condition Type Error", it.pos);
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
				System.out.println(returnType.type + " " + it.expr.type.type);
				if(!it.expr.type.Equal(returnType.type)) {
					throw new semanticError("Return Type not Equal", it.pos);
				}
			}
		}
		else {
			if(lambdaReturn) {
				lambdaType = new Type(Type.basicType.Void, -1);
			}
			else {
				if(!returnType.Equal(Type.basicType.Void)) {
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
		it.type = new Type(Type.basicType.Int, -1);
	}

	@Override
	public void Visit(BoolExprNode it) {
		it.type = new Type(Type.basicType.Bool, -1);
	}

	@Override
	public void Visit(StringExprNode it) {
		it.type = new Type(Type.basicType.String, -1);
	}

	@Override
	public void Visit(NullExprNode it) {
		it.type = new Type(Type.basicType.Null, -1);
	}

	@Override
	public void Visit(ThisExprNode it) {
		if(nowClass == null)
			throw new semanticError("Invalid This", it.pos);
		it.type = nowClass;
	}

	@Override
	public void Visit(IdExprNode it) {
		System.out.println("IdExpr: " + it.id);
		it.type = nowScope.VarGet(it.id, true, it.pos);
		System.out.println(it.type.type + " " + it.type.dim);
	}

	@Override
	public void Visit(LambdaExprNode it) {
		nowScope = new Scope(nowScope);

		if(it.paralist.size() != it.exprList.exprList.size()) {
			throw new semanticError("Number of parameters in lambda expr not match", it.pos);
		}

		if(it.exprList.exprList.size() != 0) {
			it.exprList.exprList.forEach(x -> x.Accept(this));
		}

		if(it.paralist != null) {
			it.paralist.forEach(x -> nowScope.NewVar(x.id, new Type(globalScope.TypeGet(x.type, x.pos), x.id), x.pos));
		}

		if(it.paralist != null) {
			for(int i = 0; i < it.paralist.size(); ++ i) {
				if(globalScope.TypeGet(it.paralist.get(i).type, it.paralist.get(i).pos).type != it.exprList.exprList.get(i).type.type) {
					throw new semanticError("Type of parameters in lambda not match", it.pos);
				}
			}
		}

		hasReturn = false;
		lambdaReturn = true;

		it.suite.Accept(this);

		if(!hasReturn) {
			it.type = new Type(Type.basicType.Void, -1);
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
		System.out.println("FuncExprNode: " + it.id + " " + it.id.pos + " " + it.id.type + " " + (it.id instanceof IdExprNode));
		if(it.id instanceof IdExprNode) {
			System.out.println("ExprHere.");
			it.id.type = nowScope.FuncGet(((IdExprNode)it.id).id, true, it.pos);
		}
		else {
			it.id.Accept(this);
		}

		System.out.println(it.id.type.type + " " + it.exprList);
		if(it.id.type.type != Type.basicType.Function) {
			throw new semanticError("Function Undefined", it.pos);
		}

		it.exprList.Accept(this);
		
		Type tmp = it.id.type;
		if(it.exprList.exprList.size() != tmp.functionParameters.size()) {
			throw new semanticError("Number of parameters in function not match", it.pos);
		}

		System.out.println(it);
		System.out.println(it.exprList + " " + it.exprList.exprList);
		System.out.println(tmp.functionParameters);
		System.out.println(it.exprList.exprList.size() + " " + tmp.functionParameters.size());
		for(int i = 0; i < tmp.functionParameters.size(); ++ i) {
			System.out.println(it.exprList.exprList.get(i).type.type);
			System.out.println(it.exprList.exprList.get(i).type.identifier);
			System.out.println(tmp.functionParameters.get(i).type);
			if(!it.exprList.exprList.get(i).type.TypeEqual(tmp.functionParameters.get(i))) {
				throw new semanticError("Type of parameters in function not match", it.pos);
			}
		}

		it.type = tmp;
	}

	@Override
	public void Visit(IndexExprNode it) {
		System.out.println("IndexExprNode: " + it.type);
		System.out.println(it.bas);
		System.out.println(it.off);
		it.bas.Accept(this);
		it.off.Accept(this);
		System.out.println(it.bas.type.type + " " + it.bas.type.dim + " " + it.bas.pos);
		System.out.println(it.off.type.type + " " + it.off.type.dim + " " + it.off.pos);

		if(it.bas.type.dim < 0) {
			throw new semanticError("Undefined Array", it.pos);
		}

		if(!it.off.type.Equal(Type.basicType.Int)) {
			throw new semanticError("Invalid offset", it.pos);
		}

		Type tmp = it.bas.type;
		System.out.println("Now: " + tmp.dim + " " + it.type);
		if(tmp.dim == 1)
			it.type = tmp;
		else
			it.type = new Type(tmp, tmp.dim - 1);
	}

	@Override
	public void Visit(ClassExprNode it) {
		it.name.Accept(this);
		System.out.println("ClassExpr: " + it.name.type.identifier + " " + it.pos + " " + it.isFunc + " " + it.name.type.type);
		System.out.println(globalScope.typeMap);
		// System.out.println(nowScope.typeMap.get(it.name.type.identifier).identifier);
		// System.out.println(nowScope.typeMap.get(it.name.type.identifier).type);

		if(it.name.type.dim != 0 && it.isFunc && it.id.equals("size")) {
			it.type = new Type("size", new Type(Type.basicType.Int, -1), new ArrayList<>());
			return;
		}

		if(it.name.type.Equal(Type.basicType.String) && it.isFunc && it.id.equals("length")) {
			it.type = new Type("length", new Type(Type.basicType.Int, -1), new ArrayList<>());
			return;
		}

		if(it.name.type.Equal(Type.basicType.String) && it.isFunc && it.id.equals("substring")) {
			ArrayList<Type> tmp = new ArrayList<>();
			tmp.add(new Type(new Type(Type.basicType.Int, -1), "left"));
			tmp.add(new Type(new Type(Type.basicType.Int, -1), "right"));
			it.type = new Type("substring", new Type(Type.basicType.String, -1), tmp);
			return;
		}

		if(it.name.type.Equal(Type.basicType.String) && it.isFunc && it.id.equals("parseInt")) {
			it.type = new Type("parseInt", new Type(Type.basicType.Int, -1), new ArrayList<>());
			return;
		}

		if(it.name.type.Equal(Type.basicType.String) && it.isFunc && it.id.equals("ord")) {
			ArrayList<Type> tmp = new ArrayList<>();
			tmp.add(new Type(new Type(Type.basicType.Int, -1), "pos"));
			it.type = new Type("ord", new Type(Type.basicType.Int, -1), tmp);
			return;
		}

		System.out.println(it.name.type.identifier + " " + it.name.type.type);
		if(!it.name.type.Equal(Type.basicType.Class)) {
			throw new semanticError("Class Undefined", it.pos);
		}
		
		Type preClass = it.name.type;
		System.out.println(it.name.type.identifier);
		System.out.println(it.name.type.type);
		System.out.println(it.name.type.dim);
		System.out.println(it.name.type.funcMap);
		System.out.println(it.pos);
		System.out.println(it.type);
		System.out.println(it.id);
		System.out.println(it.isFunc);
		System.out.println(preClass.funcMap);
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
		
		if(it.expr.type.type != Type.basicType.Int) {
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
			case "++":
			case "--": {
				if(it.expr.type.type != Type.basicType.Int) {
					throw new semanticError("Error Type of Prefix Expression", it.pos);
				}
				if(!it.expr.assign) {
					throw new semanticError("Prefix Assign Error", it.pos);
				}
				it.assign = true;
				it.type = new Type(Type.basicType.Int, -1);
				break;
			}
			case "+":
			case "-":
			case "~": {
				if(!it.expr.type.Equal(Type.basicType.Int)) {
					throw new semanticError("Error Type of Prefix Expression", it.pos);
				}
				it.type = new Type(Type.basicType.Int, -1);
				break;
			}
			case "!": {
				if(!it.expr.type.Equal(Type.basicType.Bool)) {
					throw new semanticError("Error Type of Prefix Expression", it.pos);
				}
				it.type = new Type(Type.basicType.Bool, -1);
				break;
			}
			default: {
				throw new semanticError("Error Prefix Expression", it.pos);
			}
		}
	}

	@Override
	public void Visit(NewExprNode it) {
		if(it.exprList != null) {
			it.exprList.forEach(x -> {
				x.Accept(this);
				if(!x.type.Equal(Type.basicType.Int)) {
					throw new semanticError("New Array's Parameter Error", it.pos);
				}
			});
		}
		it.type = globalScope.TypeGet(it.typeNode, it.pos);
	}

	@Override
	public void Visit(BinaryExprNode it) {
		System.out.println("Here.");
		System.out.println(it.expr1);
		System.out.println(it.expr2);

		it.expr1.Accept(this);
		it.expr2.Accept(this);

		if(it.expr1.type.type == Type.basicType.Null) {
			throw new semanticError("Binary Expression Expr1's Type Error", it.pos);
		}

		switch(it.op) {
			case "-":
			case "*":
			case "/":
			case "%":
			case "<<":
			case ">>":
			case "&":
			case "|":
			case "^": {
				if(!it.expr1.type.Equal(Type.basicType.Int) || !it.expr2.type.Equal(Type.basicType.Int)) {
					throw new semanticError("Error Type of Binary Expression", it.pos);
				}
				it.type = new Type(Type.basicType.Int, -1);
				break;
			}
			case "+": {
				System.out.println(it.expr1.type.type);
				System.out.println(it.expr2.type.type);
				if(it.expr1.type.Equal(Type.basicType.Int) && it.expr2.type.Equal(Type.basicType.Int)) {
					// System.out.println("Get Int: " + it.type);
					it.type = new Type(Type.basicType.Int, -1);
				}
				else if(it.expr1.type.Equal(Type.basicType.String) && it.expr2.type.Equal(Type.basicType.String)) {
					it.type = new Type(Type.basicType.String, -1);
				}
				else {
					throw new semanticError("Error Type of Binary Expression", it.pos);
				}
				break;
			}
			case "<":
			case ">":
			case "<=":
			case ">=": {
				if((it.expr1.type.Equal(Type.basicType.Int) && it.expr2.type.Equal(Type.basicType.Int)) || (it.expr1.type.Equal(Type.basicType.String) && it.expr2.type.Equal(Type.basicType.String))) {
					it.type = new Type(Type.basicType.Bool, -1);
				}
				else {
					throw new semanticError("Error Type of Binary Expression", it.pos);
				}
				break;
			}
			case "&&":
			case "||": {
				System.out.println(it.expr1.type.type + " " + it.expr2.type.type);
				System.out.println(it.expr1.type.identifier + " " + it.expr2.type.identifier);
				if(it.expr1.type.Equal(Type.basicType.Bool) && it.expr2.type.Equal(Type.basicType.Bool)) {
					it.type = new Type(Type.basicType.Bool, -1);
				}
				else {
					throw new semanticError("Error Type of Binary Expression", it.pos);
				}
				break;
			}
			case "==":
			case "!=": {
				if(it.expr1.type.TypeEqual(it.expr2.type)) {
					it.type = new Type(Type.basicType.Bool, -1);
				}
				else {
					throw new semanticError("Error Type of Binary Expression", it.pos);
				}
				break;
			}
			case "=": {
				System.out.println(it.expr1.type.type + " " + it.expr1.type.dim);
				System.out.println(it.expr2.type.type);
				if(!it.expr1.type.TypeEqual(it.expr2.type)) {
					throw new semanticError("Error Type of Binary Expression", it.pos);
				}
				System.out.println(it.expr1 + " " + it.expr1.type.identifier + " " + it.expr1.assign);
				if(!it.expr1.assign) {
					throw new semanticError("Type of value Error", it.pos);
				}
				it.assign = true;
				it.type = it.expr1.type;
				break;
			}
			default: {
				throw new semanticError("Invalid Binary Operator", it.pos);
			}
		}
	}

	@Override
	public void Visit(ExprListNode it) {
		it.exprList.forEach(x -> x.Accept(this));
	}
}