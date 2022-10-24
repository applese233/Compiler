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

public class ASTBuilder {
	@Override
	public ASTNode VisitProgram(MxLiteParser.ProgramContext ctx) {
		ProgNode res = new ProgNode(new position(ctx));
		for(ParserRuleContext x : ctx.ProgramBlockContext)
			res.List.add(Visit(x));
		return res;
	}

	@Override
	public ASTNode VisitProgramBlock(MxLiteParser.ProgramBlockContext ctx) {
		if(ctx.varDef() != null)
			return Visit(ctx.varDef());
		else if(ctx.funcDef() != null)
			return Visit(ctx.funcDef());
		else
			return Visit(ctx.classDef());
	}

	@Override
	public ASTNode VisitVarDef(MxLiteParser.VarDefContext ctx) {
		VarDefStmtNode res = new VarDefStmtNode(new position(ctx));
		TypeNode type = (TypeNode)Visit(ctx.varType);
		for(ParserRuleContext x : ctx.varDeclaration()) {
			VarDecStmtNode tmp = (VarDecStmtNode)Visit(x);
			tmp.type = type;
			res.varList.add(tmp);
		}
		return res;
	}

	@Override
	public ASTNode VisitVarDec(MxLiteParser.VarDeclarationContext ctx) {
		VarDecStmtNode res = new VarDecStmtNode(new position(ctx), ctx.Identifier().getText(), ctx.expression() == null ? null : (ExprNode)Visit(ctx.expression()));
	}

	@Override
	public ASTNode VisitFuncDef(MxLiteParser.FuncDefContext ctx) {
		return new FuncDefNode(new position(ctx), ctx.returnType() == null ? null : (TypeNode)Visit(ctx.returnType()), ctx.Identifier().getText(), ctx.parameterList() == null ? new ArrayList<>() : ((VarDefStmtNode)Visit(ctx.parameterList())).varList, (SuiteStmtNode)Visit(ctx.suite()));
	}

	@Override
	public ASTNode VisitParaList(MxLiteParser.ParameterListContext ctx) {
		VarDefStmtNode res = new VarDefStmtNode(new position(ctx));
		for(ParserRuleContext x : ctx.parameter())
			res.varList.add((VarDecStmtNode)Visit(x));
		return res;
	}

	@Override
	public ASTNode VisitPara(MxLiteParser.ParameterContext ctx) {
		VarDecStmtNode res = new VarDecStmtNode(new position(ctx), ctx.Identifier().getText(), null);
		res.type = (TypeNode)Visit(ctx.varType());
		return res;
	}

	@Override
	public ASTNode VisitSuite(MxLiteParser.SuiteContext ctx) {
		SuiteStmtNode res = new SuiteStmtNode(new position(ctx));
		if(ctx.statement() != null)
			for(ParserRuleContext x : ctx.statement())
				res.stmtList.Add((StmtNode)Visit(x));
		return res;
	}

	@Override
	public ASTNode VisitClassDef(MxLiteParser.ClassDefContext ctx) {
		ClassDefNode res = new ClassDefNode(new position(ctx), ctx.Identifier().getText());
		if(ctx.varDef() != null)
			for(ParserRuleContext x : ctx.varDef())
				res.varlist.addAll(((VarDefStmtNode)Visit(x)).varList);
		if(ctx.funcDef() != null)
			for(ParserRuleContext x : ctx.funcDef())
				res.funclist.add((FuncDefNode)Visit(x));
		if(ctx.classStruct().size() != 0)
			res.struct = (FuncDefNode)Visit(ctx.classStruct().get(0));
		return res;
	}

	@Override
	public ASTNode VisitClassStruct(MxLiteParser.ClassStructContext ctx) {
		return new FuncDefNode(new position(ctx), null, ctx.Identifier().getText(), null, (SuiteStmtNode)Visit(ctx.suite()));
	}

	@Override
	public ASTNode VisitConstant(MxLiteParser.ConstantContext ctx) {
		if(ctx.IntConst() != null)
			return new IntExprNode(new position(ctx), Integer.parseInt(ctx.IntConst().getText()));
		if(ctx.BoolConst() != null)
			return new BoolExprNode(new position(ctx), Boolean.parseBoolean(ctx.BoolConst().getText()));
		if(ctx.StringConst() != null)
			return new StringExprNode(new position(ctx), ctx.StringConst().getText());
		return new NullExprNode(new position(ctx));
	}

	@Override
	public ASTNode VisitBasType(MxLiteParser.BasTypeContext ctx) {
		return new TypeNode(new position(ctx), ctx.getText(), 0);
	}

	@Override
	public ASTNode VisitVarType(MxLiteParser.VarTypeContext ctx) {
		return new TypeNode(new position(ctx), ctx.basType().getText(), (ctx.getChildCount() - 1) / 2);
	}

	@Override
	public ASTNode VisitReturnType(MxLiteParser.ReturnTypeContext ctx) {
		if(ctx.varType() != null)
			return Visit(ctx.varType());
		return new TypeNode(new position(ctx), ctx.Void().getText(), 0);
	}

	@Override
	public ASTNode VisitSuiteStmt(MxLiteParser.SuiteStmtContext ctx) {
		SuiteStmtNode res = new SuiteStmtNode(new position(ctx));
		if(ctx.suite().statement() != null)
			for(ParserRuleContext x : ctx.suite().statement())
				res.stmtList.add((StmtNode)Visit(x));
		return res;
	}

	@Override
	public ASTNode VisitVarDefStmt(MxLiteParser.VarDefStmtContext ctx) {
		return Visit(ctx.varDef());
	}

	@Override
	public ASTNode VisitIfStmt(MxLiteParser.IfStmtContext ctx) {
		return new IfStmtNode(new position(ctx), (ExprNode)Visit(ctx.expression()), (StmtNode)Visit(ctx.trueStmt), ctx.falseStmt == null ? null : (StmtNode)ctx.falseStmt);
	}

	@Override
	public ASTNode VisitWhileStmt(MxLiteParser.WhileStmtContext ctx) {
		return new WhileStmtNode(new position(ctx), (ExprNode)Visit(ctx.expression()), (StmtNode)Visit(ctx.statement()));
	}

	@Override
	public ASTNode VisitForStmt(MxLiteParser.ForStmtContext ctx) {
		return new ForStmtNode(new position(ctx), ctx.expr1 == null ? null : (ExprNode)Visit(ctx.expr1), ctx.expr2 == null ? null : (ExprNode)Visit(ctx.expr2), ctx.expr3 == null ? null : (ExprNode)Visit(ctx.expr3), (StmtNode)Visit(ctx.statement()));
	}

	@Override
	public ASTNode VisitReturnStmt(MxLiteParser.ReturnStmtContext ctx) {
		return new ReturnStmtNode(new position(ctx), ctx.expression() == null ? null : (ExprNode)Visit(ctx.expression()));
	}

	@Override
	public ASTNode VisitBreakStmt(MxLiteParser.BreakStmtContext ctx) {
		return new BreakStmtNode(new position(ctx));
	}

	@Override
	public ASTNode VisitContinueStmt(MxLiteParser.ContinueStmtContext ctx) {
		return new ContinueStmtNode(new position(ctx));
	}

	@Override
	public ASTNode VisitPureExprStmt(MxLiteParser.PureExprStmtContext ctx) {
		return new PureExprStmtNode(new position(ctx), (ExprNode)Visit(ctx.expression()));
	}

	@Override
	public ASTNode VisitEmptyStmt(MxLiteParser.EmptyStmtContext ctx) {
		return new EmptyStmtNode(new position(ctx));
	}

	@Override
	public ASTNode VisitNewExpr(MxLireParser.NewExprContext ctx) {
		return Visit(ctx.creator());
	}

	@Override
	public ASTNode VisitIndexExpr(MxLiteParser.IndexExprContext ctx) {
		return new IndexExprNode(new position(ctx), (ExprNode)Visit(ctx.expr1), (ExprNode)Visit(ctx.expr2));
	}

	@Override
	public ASTNode VisitThisExpr(MxLiteParser.ThisExprContext ctx) {
		return new ThisExprNode(new position(ctx));
	}

	@Override
	public ASTNode VisitFuncExpr(MxLiteParser.FuncExprContext ctx) {
		ExprNode res = (ExprNode)Visit(ctx.expression());
		if(res instanceof ClassExprNode)
			res.assign = false, ((ClassExprNode)res).isFunc = true;
		return new FuncExprNode(new position(ctx), res, ctx.expressionList() == null ? new ExprListNode(new position(ctx)) : (ExprListNode)Visit(ctx.expressionList()));
	}

	@Override
	public ASTNode VisitLambdaExpr(MxLiteParser.LambdaExprContext ctx) {
		return Visit(ctx.lambda());
	}

	@Override
	public ASTNode VisitClassExpr(MxLiteParser.ClassExprContext ctx) {
		return new ClassExprNode(new position(ctx), (ExprNode)Visit(ctx.expression()), ctx.Identifier().getText());
	}

	@Override
	public ASTNode VisitBinaryExpr(MxLiteParser.BinaryExprContext ctx) {
		return new BinaryExprNode(new position(ctx), (ExprNode)Visit(ctx.src1), (ExprNode)Visit(ctx.src2), ctx.op.getText());
	}

	@Override
	public ASTNode VisitPreExpr(MxLiteParser.PreExprContext ctx) {
		return new PreExprNode(new position(ctx), ctx.op.getText(), (ExprNode)Visit(ctx.expression()));
	}

	@Override
	public ASTNode VisitSufExpr(MxLiteParser.SufExprContext ctx) {
		return new SufExprNode(new position(ctx), (ExprNode)Visit(ctx.expression()), ctx.op.getText());
	}

	@Override
	public ASTNode VisitSubExpr(MxLiteParser.SubExprContext ctx) {
		return Visit(ctx.expression());
	}

	@Override
	public ASTNode VisitConstExpr(MxLiteParser.ConstExprContext ctx) {
		return Visit(ctx.constant());
	}

	@Override
	public ASTNode VisitIdExpr(MxLiteParser.IdExprContext ctx) {
		return IdExprNode(new position(ctx), ctx.Identifier().getText());
	}

	@Override
	public ASTNode VisitExprList(MxLiteParser.ExpressionListContext ctx) {
		ExprListNode res = new ExprListNode(new position(ctx));
		for(ParserRuleContext x : ctx.expression())
			res.exprList.add((ExprNode)Visit(x));
		return res;
	}

	@Override
	public ASTNode VisitErrorCrea(MxLiteParser.ErrorCreaContext ctx) {
		throw new syntaxError("ErrorCreator", new position(ctx));
	}

	@Override
	public ASTNode VisitArrayCrea(MxLiteParser.ArrayCreaContext ctx) {
		TypeNode type = (TypeNode)Visit(ctx.basType());
		ArrayList<ExprNode> _exprList = new ArrayList<>();
		for(ParserRuleContext x : ctx.expression())
			_exprList.add((ExprNode)Visit(x));
		return new NewExprNode(new position(ctx), type, (ctx.getChildCount() - ctx.expression().size() - 1) / 2, _exprList);
	}

	@Override
	public ASTNode VisitClassCrea(MxLiteParser.ClassCreaContext ctx) {
		return new NewExprNode(new position(ctx), (TypeNode)Visit(ctx.basType()), 0, null);
	}

	@Override
	public ASTNode VisitBasicCrea(MxLiteParser.BasicCreaContext ctx) {
		return new NewExprNode(new position(ctx), (TypeNode)Visit(ctx.basType()), 0, null);
	}

	@Override
	public ASTNode VisitLambda(MxLiteParser.LambdaContext ctx) {
		LambdaExprNode res = new LambdaExprNode(new position(ctx), (SuiteStmtNode)Visit(ctx.suite()), ctx.expressionList() == null ? new ExprListNode(new position(ctx)) : (ExprListNode)Visit(ctx.expressionList()));
		if(ctx.parameterList() != null)
			res.paralist.addAll(((VarDefStmtNode)Visit(ctx.parameterList())).varList);
		return res;
	}
}
