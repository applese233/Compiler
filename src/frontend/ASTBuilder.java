package frontend;

import AST.*;
import AST.DefNode.*;
import AST.StmtNode.*;
import AST.ExprNode.*;
import org.antlr.v4.runtime.Parser;

import Util.MxLiteErrorListener;
import Util.position;
import Util.error.syntaxError;
import org.antlr.v4.runtime.ParserRuleContext;
import Parser.MxLiteBaseVisitor;
import Parser.MxLiteParser;

import java.util.ArrayList;

public class ASTBuilder extends MxLiteBaseVisitor<ASTNode> {
	@Override
	public ASTNode visitProgram(MxLiteParser.ProgramContext ctx) {
		ProgNode res = new ProgNode(new position(ctx));
		for(ParserRuleContext x : ctx.programBlock())
			res.list.add(visit(x));
		return res;
	}

	@Override
	public ASTNode visitProgramBlock(MxLiteParser.ProgramBlockContext ctx) {
		if(ctx.varDef() != null)
			return visit(ctx.varDef());
		else if(ctx.funcDef() != null)
			return visit(ctx.funcDef());
		else
			return visit(ctx.classDef());
	}

	@Override
	public ASTNode visitVarDef(MxLiteParser.VarDefContext ctx) {
		VarDefStmtNode res = new VarDefStmtNode(new position(ctx));
		TypeNode type = (TypeNode)visit(ctx.varType());
		for(ParserRuleContext x : ctx.varDeclaration()) {
			VarDecStmtNode tmp = (VarDecStmtNode)visit(x);
			tmp.type = type;
			res.varList.add(tmp);
		}
		return res;
	}

	@Override
	public ASTNode visitVarDeclaration(MxLiteParser.VarDeclarationContext ctx) {
		return new VarDecStmtNode(new position(ctx), ctx.Identifier().getText(), ctx.expression() == null ? null : (ExprNode)visit(ctx.expression()));
	}

	@Override
	public ASTNode visitFuncDef(MxLiteParser.FuncDefContext ctx) {
		return new FuncDefNode(new position(ctx), ctx.returnType() == null ? null : (TypeNode)visit(ctx.returnType()), ctx.Identifier().getText(), ctx.parameterList() == null ? new ArrayList<>() : ((VarDefStmtNode)visit(ctx.parameterList())).varList, (SuiteStmtNode)visit(ctx.suite()));
	}

	@Override
	public ASTNode visitParameterList(MxLiteParser.ParameterListContext ctx) {
		VarDefStmtNode res = new VarDefStmtNode(new position(ctx));
		for(ParserRuleContext x : ctx.parameter())
			res.varList.add((VarDecStmtNode)visit(x));
		return res;
	}

	@Override
	public ASTNode visitParameter(MxLiteParser.ParameterContext ctx) {
		VarDecStmtNode res = new VarDecStmtNode(new position(ctx), ctx.Identifier().getText(), null);
		res.type = (TypeNode)visit(ctx.varType());
		return res;
	}

	@Override
	public ASTNode visitSuite(MxLiteParser.SuiteContext ctx) {
		SuiteStmtNode res = new SuiteStmtNode(new position(ctx));
		if(ctx.statement() != null)
			for(ParserRuleContext x : ctx.statement())
				res.stmtList.add((StmtNode)visit(x));
		return res;
	}

	@Override
	public ASTNode visitClassDef(MxLiteParser.ClassDefContext ctx) {
		ClassDefNode res = new ClassDefNode(new position(ctx), ctx.Identifier().getText());
		if(ctx.varDef() != null)
			for(ParserRuleContext x : ctx.varDef())
				res.varlist.addAll(((VarDefStmtNode)visit(x)).varList);
		if(ctx.funcDef() != null)
			for(ParserRuleContext x : ctx.funcDef())
				res.funclist.add((FuncDefNode)visit(x));
		if(ctx.classStruct().size() != 0)
			res.struct = (FuncDefNode)visit(ctx.classStruct().get(0));
		return res;
	}

	@Override
	public ASTNode visitClassStruct(MxLiteParser.ClassStructContext ctx) {
		return new FuncDefNode(new position(ctx), null, ctx.Identifier().getText(), null, (SuiteStmtNode)visit(ctx.suite()));
	}

	@Override
	public ASTNode visitConstant(MxLiteParser.ConstantContext ctx) {
		if(ctx.IntConst() != null)
			return new IntExprNode(new position(ctx), Integer.parseInt(ctx.IntConst().getText()));
		if(ctx.BoolConst() != null)
			return new BoolExprNode(new position(ctx), Boolean.parseBoolean(ctx.BoolConst().getText()));
		if(ctx.StringConst() != null)
			return new StringExprNode(new position(ctx), ctx.StringConst().getText());
		return new NullExprNode(new position(ctx));
	}

	@Override
	public ASTNode visitBasType(MxLiteParser.BasTypeContext ctx) {
		return new TypeNode(new position(ctx), ctx.getText(), 0);
	}

	@Override
	public ASTNode visitVarType(MxLiteParser.VarTypeContext ctx) {
		return new TypeNode(new position(ctx), ctx.basType().getText(), (ctx.getChildCount() - 1) / 2);
	}

	@Override
	public ASTNode visitReturnType(MxLiteParser.ReturnTypeContext ctx) {
		if(ctx.varType() != null)
			return visit(ctx.varType());
		return new TypeNode(new position(ctx), ctx.Void().getText(), 0);
	}

	@Override
	public ASTNode visitSuiteStmt(MxLiteParser.SuiteStmtContext ctx) {
		SuiteStmtNode res = new SuiteStmtNode(new position(ctx));
		if(ctx.suite().statement() != null)
			for(ParserRuleContext x : ctx.suite().statement())
				res.stmtList.add((StmtNode)visit(x));
		return res;
	}

	@Override
	public ASTNode visitVarDefStmt(MxLiteParser.VarDefStmtContext ctx) {
		return visit(ctx.varDef());
	}

	@Override
	public ASTNode visitIfStmt(MxLiteParser.IfStmtContext ctx) {
		return new IfStmtNode(new position(ctx), (ExprNode)visit(ctx.expression()), (StmtNode)visit(ctx.trueStmt), ctx.falseStmt == null ? null : (StmtNode)visit(ctx.falseStmt));
	}

	@Override
	public ASTNode visitWhileStmt(MxLiteParser.WhileStmtContext ctx) {
		return new WhileStmtNode(new position(ctx), (ExprNode)visit(ctx.expression()), (StmtNode)visit(ctx.statement()));
	}

	@Override
	public ASTNode visitForStmt(MxLiteParser.ForStmtContext ctx) {
		return new ForStmtNode(new position(ctx), ctx.expr1 == null ? null : (ExprNode)visit(ctx.expr1), ctx.expr2 == null ? null : (ExprNode)visit(ctx.expr2), ctx.expr3 == null ? null : (ExprNode)visit(ctx.expr3), (StmtNode)visit(ctx.statement()));
	}

	@Override
	public ASTNode visitReturnStmt(MxLiteParser.ReturnStmtContext ctx) {
		return new ReturnStmtNode(new position(ctx), ctx.expression() == null ? null : (ExprNode)visit(ctx.expression()));
	}

	@Override
	public ASTNode visitBreakStmt(MxLiteParser.BreakStmtContext ctx) {
		return new BreakStmtNode(new position(ctx));
	}

	@Override
	public ASTNode visitContinueStmt(MxLiteParser.ContinueStmtContext ctx) {
		return new ContinueStmtNode(new position(ctx));
	}

	@Override
	public ASTNode visitPureExprStmt(MxLiteParser.PureExprStmtContext ctx) {
		return new PureExprStmtNode(new position(ctx), (ExprNode)visit(ctx.expression()));
	}

	@Override
	public ASTNode visitEmptyStmt(MxLiteParser.EmptyStmtContext ctx) {
		return new EmptyStmtNode(new position(ctx));
	}

	@Override
	public ASTNode visitNewExpr(MxLiteParser.NewExprContext ctx) {
		return visit(ctx.creator());
	}

	@Override
	public ASTNode visitIndexExpr(MxLiteParser.IndexExprContext ctx) {
		return new IndexExprNode(new position(ctx), (ExprNode)visit(ctx.expr1), (ExprNode)visit(ctx.expr2));
	}

	@Override
	public ASTNode visitThisExpr(MxLiteParser.ThisExprContext ctx) {
		return new ThisExprNode(new position(ctx));
	}

	@Override
	public ASTNode visitFuncExpr(MxLiteParser.FuncExprContext ctx) {
		ExprNode res = (ExprNode)visit(ctx.expression());
		if(res instanceof ClassExprNode) {
			res.assign = false;
			((ClassExprNode)res).isFunc = true;
		}
		return new FuncExprNode(new position(ctx), res, ctx.expressionList() == null ? new ExprListNode(new position(ctx)) : (ExprListNode)visit(ctx.expressionList()));
	}

	@Override
	public ASTNode visitLambdaExpr(MxLiteParser.LambdaExprContext ctx) {
		return visit(ctx.lambda());
	}

	@Override
	public ASTNode visitClassExpr(MxLiteParser.ClassExprContext ctx) {
		return new ClassExprNode(new position(ctx), (ExprNode)visit(ctx.expression()), ctx.Identifier().getText());
	}

	@Override
	public ASTNode visitBinaryExpr(MxLiteParser.BinaryExprContext ctx) {
		return new BinaryExprNode(new position(ctx), (ExprNode)visit(ctx.src1), (ExprNode)visit(ctx.src2), ctx.op.getText());
	}

	@Override
	public ASTNode visitPreExpr(MxLiteParser.PreExprContext ctx) {
		return new PreExprNode(new position(ctx), ctx.op.getText(), (ExprNode)visit(ctx.expression()));
	}

	@Override
	public ASTNode visitSufExpr(MxLiteParser.SufExprContext ctx) {
		return new SufExprNode(new position(ctx), (ExprNode)visit(ctx.expression()), ctx.op.getText());
	}

	@Override
	public ASTNode visitSubExpr(MxLiteParser.SubExprContext ctx) {
		return visit(ctx.expression());
	}

	@Override
	public ASTNode visitConstExpr(MxLiteParser.ConstExprContext ctx) {
		return visit(ctx.constant());
	}

	@Override
	public ASTNode visitIdExpr(MxLiteParser.IdExprContext ctx) {
		return new IdExprNode(new position(ctx), ctx.Identifier().getText());
	}

	@Override
	public ASTNode visitExpressionList(MxLiteParser.ExpressionListContext ctx) {
		ExprListNode res = new ExprListNode(new position(ctx));
		for(ParserRuleContext x : ctx.expression())
			res.exprList.add((ExprNode)visit(x));
		return res;
	}

	@Override
	public ASTNode visitErrorCrea(MxLiteParser.ErrorCreaContext ctx) {
		throw new syntaxError("ErrorCreator", new position(ctx));
	}

	@Override
	public ASTNode visitArrayCrea(MxLiteParser.ArrayCreaContext ctx) {
		TypeNode type = (TypeNode)visit(ctx.basType());
		ArrayList<ExprNode> _exprList = new ArrayList<>();
		for(ParserRuleContext x : ctx.expression())
			_exprList.add((ExprNode)visit(x));
		return new NewExprNode(new position(ctx), type, (ctx.getChildCount() - ctx.expression().size() - 1) / 2, _exprList);
	}

	@Override
	public ASTNode visitClassCrea(MxLiteParser.ClassCreaContext ctx) {
		return new NewExprNode(new position(ctx), (TypeNode)visit(ctx.basType()), 0, null);
	}

	@Override
	public ASTNode visitBasicCrea(MxLiteParser.BasicCreaContext ctx) {
		return new NewExprNode(new position(ctx), (TypeNode)visit(ctx.basType()), 0, null);
	}

	@Override
	public ASTNode visitLambda(MxLiteParser.LambdaContext ctx) {
		LambdaExprNode res = new LambdaExprNode(new position(ctx), (SuiteStmtNode)visit(ctx.suite()), ctx.expressionList() == null ? new ExprListNode(new position(ctx)) : (ExprListNode)visit(ctx.expressionList()));
		if(ctx.parameterList() != null)
			res.paralist.addAll(((VarDefStmtNode)visit(ctx.parameterList())).varList);
		return res;
	}
}
