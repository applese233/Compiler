// Generated from MxLite.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MxLiteParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MxLiteVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MxLiteParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(MxLiteParser.ConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxLiteParser#basType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBasType(MxLiteParser.BasTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxLiteParser#varType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarType(MxLiteParser.VarTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxLiteParser#returnType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnType(MxLiteParser.ReturnTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxLiteParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(MxLiteParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxLiteParser#programBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgramBlock(MxLiteParser.ProgramBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxLiteParser#varDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDef(MxLiteParser.VarDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxLiteParser#varDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDeclaration(MxLiteParser.VarDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxLiteParser#funcDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncDef(MxLiteParser.FuncDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxLiteParser#parameterList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterList(MxLiteParser.ParameterListContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxLiteParser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter(MxLiteParser.ParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxLiteParser#suite}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSuite(MxLiteParser.SuiteContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxLiteParser#classDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassDef(MxLiteParser.ClassDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxLiteParser#classStruct}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassStruct(MxLiteParser.ClassStructContext ctx);
	/**
	 * Visit a parse tree produced by the {@code suiteStmt}
	 * labeled alternative in {@link MxLiteParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSuiteStmt(MxLiteParser.SuiteStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varDefStmt}
	 * labeled alternative in {@link MxLiteParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDefStmt(MxLiteParser.VarDefStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifStmt}
	 * labeled alternative in {@link MxLiteParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStmt(MxLiteParser.IfStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code whileStmt}
	 * labeled alternative in {@link MxLiteParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStmt(MxLiteParser.WhileStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code forStmt}
	 * labeled alternative in {@link MxLiteParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStmt(MxLiteParser.ForStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code returnStmt}
	 * labeled alternative in {@link MxLiteParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnStmt(MxLiteParser.ReturnStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code breakStmt}
	 * labeled alternative in {@link MxLiteParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakStmt(MxLiteParser.BreakStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code continueStmt}
	 * labeled alternative in {@link MxLiteParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinueStmt(MxLiteParser.ContinueStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pureExprStmt}
	 * labeled alternative in {@link MxLiteParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPureExprStmt(MxLiteParser.PureExprStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code emptyStmt}
	 * labeled alternative in {@link MxLiteParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptyStmt(MxLiteParser.EmptyStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code newExpr}
	 * labeled alternative in {@link MxLiteParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewExpr(MxLiteParser.NewExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code indexExpr}
	 * labeled alternative in {@link MxLiteParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexExpr(MxLiteParser.IndexExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code thisExpr}
	 * labeled alternative in {@link MxLiteParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitThisExpr(MxLiteParser.ThisExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funcExpr}
	 * labeled alternative in {@link MxLiteParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncExpr(MxLiteParser.FuncExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lambdaExpr}
	 * labeled alternative in {@link MxLiteParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambdaExpr(MxLiteParser.LambdaExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code classExpr}
	 * labeled alternative in {@link MxLiteParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassExpr(MxLiteParser.ClassExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binaryExpr}
	 * labeled alternative in {@link MxLiteParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryExpr(MxLiteParser.BinaryExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code preExpr}
	 * labeled alternative in {@link MxLiteParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPreExpr(MxLiteParser.PreExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sufExpr}
	 * labeled alternative in {@link MxLiteParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSufExpr(MxLiteParser.SufExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code subExpr}
	 * labeled alternative in {@link MxLiteParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubExpr(MxLiteParser.SubExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code constExpr}
	 * labeled alternative in {@link MxLiteParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstExpr(MxLiteParser.ConstExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code idExpr}
	 * labeled alternative in {@link MxLiteParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdExpr(MxLiteParser.IdExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxLiteParser#expressionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionList(MxLiteParser.ExpressionListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code errorCrea}
	 * labeled alternative in {@link MxLiteParser#creator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitErrorCrea(MxLiteParser.ErrorCreaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arrayCrea}
	 * labeled alternative in {@link MxLiteParser#creator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayCrea(MxLiteParser.ArrayCreaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code classCrea}
	 * labeled alternative in {@link MxLiteParser#creator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClassCrea(MxLiteParser.ClassCreaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code basicCrea}
	 * labeled alternative in {@link MxLiteParser#creator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBasicCrea(MxLiteParser.BasicCreaContext ctx);
	/**
	 * Visit a parse tree produced by {@link MxLiteParser#lambda}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambda(MxLiteParser.LambdaContext ctx);
}