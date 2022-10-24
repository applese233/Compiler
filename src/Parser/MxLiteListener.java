// Generated from MxLite.g4 by ANTLR 4.7.2
package Parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MxLiteParser}.
 */
public interface MxLiteListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MxLiteParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterConstant(MxLiteParser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxLiteParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitConstant(MxLiteParser.ConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxLiteParser#basType}.
	 * @param ctx the parse tree
	 */
	void enterBasType(MxLiteParser.BasTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxLiteParser#basType}.
	 * @param ctx the parse tree
	 */
	void exitBasType(MxLiteParser.BasTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxLiteParser#varType}.
	 * @param ctx the parse tree
	 */
	void enterVarType(MxLiteParser.VarTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxLiteParser#varType}.
	 * @param ctx the parse tree
	 */
	void exitVarType(MxLiteParser.VarTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxLiteParser#returnType}.
	 * @param ctx the parse tree
	 */
	void enterReturnType(MxLiteParser.ReturnTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxLiteParser#returnType}.
	 * @param ctx the parse tree
	 */
	void exitReturnType(MxLiteParser.ReturnTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxLiteParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(MxLiteParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxLiteParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(MxLiteParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxLiteParser#programBlock}.
	 * @param ctx the parse tree
	 */
	void enterProgramBlock(MxLiteParser.ProgramBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxLiteParser#programBlock}.
	 * @param ctx the parse tree
	 */
	void exitProgramBlock(MxLiteParser.ProgramBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxLiteParser#varDef}.
	 * @param ctx the parse tree
	 */
	void enterVarDef(MxLiteParser.VarDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxLiteParser#varDef}.
	 * @param ctx the parse tree
	 */
	void exitVarDef(MxLiteParser.VarDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxLiteParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVarDeclaration(MxLiteParser.VarDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxLiteParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVarDeclaration(MxLiteParser.VarDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxLiteParser#funcDef}.
	 * @param ctx the parse tree
	 */
	void enterFuncDef(MxLiteParser.FuncDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxLiteParser#funcDef}.
	 * @param ctx the parse tree
	 */
	void exitFuncDef(MxLiteParser.FuncDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxLiteParser#parameterList}.
	 * @param ctx the parse tree
	 */
	void enterParameterList(MxLiteParser.ParameterListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxLiteParser#parameterList}.
	 * @param ctx the parse tree
	 */
	void exitParameterList(MxLiteParser.ParameterListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxLiteParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(MxLiteParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxLiteParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(MxLiteParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxLiteParser#suite}.
	 * @param ctx the parse tree
	 */
	void enterSuite(MxLiteParser.SuiteContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxLiteParser#suite}.
	 * @param ctx the parse tree
	 */
	void exitSuite(MxLiteParser.SuiteContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxLiteParser#classDef}.
	 * @param ctx the parse tree
	 */
	void enterClassDef(MxLiteParser.ClassDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxLiteParser#classDef}.
	 * @param ctx the parse tree
	 */
	void exitClassDef(MxLiteParser.ClassDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxLiteParser#classStruct}.
	 * @param ctx the parse tree
	 */
	void enterClassStruct(MxLiteParser.ClassStructContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxLiteParser#classStruct}.
	 * @param ctx the parse tree
	 */
	void exitClassStruct(MxLiteParser.ClassStructContext ctx);
	/**
	 * Enter a parse tree produced by the {@code suiteStmt}
	 * labeled alternative in {@link MxLiteParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterSuiteStmt(MxLiteParser.SuiteStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code suiteStmt}
	 * labeled alternative in {@link MxLiteParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitSuiteStmt(MxLiteParser.SuiteStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varDefStmt}
	 * labeled alternative in {@link MxLiteParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterVarDefStmt(MxLiteParser.VarDefStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varDefStmt}
	 * labeled alternative in {@link MxLiteParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitVarDefStmt(MxLiteParser.VarDefStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifStmt}
	 * labeled alternative in {@link MxLiteParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIfStmt(MxLiteParser.IfStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifStmt}
	 * labeled alternative in {@link MxLiteParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIfStmt(MxLiteParser.IfStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code whileStmt}
	 * labeled alternative in {@link MxLiteParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStmt(MxLiteParser.WhileStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code whileStmt}
	 * labeled alternative in {@link MxLiteParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStmt(MxLiteParser.WhileStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code forStmt}
	 * labeled alternative in {@link MxLiteParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterForStmt(MxLiteParser.ForStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code forStmt}
	 * labeled alternative in {@link MxLiteParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitForStmt(MxLiteParser.ForStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code returnStmt}
	 * labeled alternative in {@link MxLiteParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStmt(MxLiteParser.ReturnStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code returnStmt}
	 * labeled alternative in {@link MxLiteParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStmt(MxLiteParser.ReturnStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code breakStmt}
	 * labeled alternative in {@link MxLiteParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterBreakStmt(MxLiteParser.BreakStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code breakStmt}
	 * labeled alternative in {@link MxLiteParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitBreakStmt(MxLiteParser.BreakStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code continueStmt}
	 * labeled alternative in {@link MxLiteParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterContinueStmt(MxLiteParser.ContinueStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code continueStmt}
	 * labeled alternative in {@link MxLiteParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitContinueStmt(MxLiteParser.ContinueStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code pureExprStmt}
	 * labeled alternative in {@link MxLiteParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterPureExprStmt(MxLiteParser.PureExprStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code pureExprStmt}
	 * labeled alternative in {@link MxLiteParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitPureExprStmt(MxLiteParser.PureExprStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code emptyStmt}
	 * labeled alternative in {@link MxLiteParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterEmptyStmt(MxLiteParser.EmptyStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code emptyStmt}
	 * labeled alternative in {@link MxLiteParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitEmptyStmt(MxLiteParser.EmptyStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code newExpr}
	 * labeled alternative in {@link MxLiteParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNewExpr(MxLiteParser.NewExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code newExpr}
	 * labeled alternative in {@link MxLiteParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNewExpr(MxLiteParser.NewExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code indexExpr}
	 * labeled alternative in {@link MxLiteParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIndexExpr(MxLiteParser.IndexExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code indexExpr}
	 * labeled alternative in {@link MxLiteParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIndexExpr(MxLiteParser.IndexExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code thisExpr}
	 * labeled alternative in {@link MxLiteParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterThisExpr(MxLiteParser.ThisExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code thisExpr}
	 * labeled alternative in {@link MxLiteParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitThisExpr(MxLiteParser.ThisExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funcExpr}
	 * labeled alternative in {@link MxLiteParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFuncExpr(MxLiteParser.FuncExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funcExpr}
	 * labeled alternative in {@link MxLiteParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFuncExpr(MxLiteParser.FuncExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code lambdaExpr}
	 * labeled alternative in {@link MxLiteParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLambdaExpr(MxLiteParser.LambdaExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code lambdaExpr}
	 * labeled alternative in {@link MxLiteParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLambdaExpr(MxLiteParser.LambdaExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code classExpr}
	 * labeled alternative in {@link MxLiteParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterClassExpr(MxLiteParser.ClassExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code classExpr}
	 * labeled alternative in {@link MxLiteParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitClassExpr(MxLiteParser.ClassExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binaryExpr}
	 * labeled alternative in {@link MxLiteParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterBinaryExpr(MxLiteParser.BinaryExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binaryExpr}
	 * labeled alternative in {@link MxLiteParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitBinaryExpr(MxLiteParser.BinaryExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code preExpr}
	 * labeled alternative in {@link MxLiteParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPreExpr(MxLiteParser.PreExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code preExpr}
	 * labeled alternative in {@link MxLiteParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPreExpr(MxLiteParser.PreExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sufExpr}
	 * labeled alternative in {@link MxLiteParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSufExpr(MxLiteParser.SufExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sufExpr}
	 * labeled alternative in {@link MxLiteParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSufExpr(MxLiteParser.SufExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code subExpr}
	 * labeled alternative in {@link MxLiteParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSubExpr(MxLiteParser.SubExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code subExpr}
	 * labeled alternative in {@link MxLiteParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSubExpr(MxLiteParser.SubExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code constExpr}
	 * labeled alternative in {@link MxLiteParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterConstExpr(MxLiteParser.ConstExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code constExpr}
	 * labeled alternative in {@link MxLiteParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitConstExpr(MxLiteParser.ConstExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code idExpr}
	 * labeled alternative in {@link MxLiteParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIdExpr(MxLiteParser.IdExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code idExpr}
	 * labeled alternative in {@link MxLiteParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIdExpr(MxLiteParser.IdExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxLiteParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void enterExpressionList(MxLiteParser.ExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxLiteParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void exitExpressionList(MxLiteParser.ExpressionListContext ctx);
	/**
	 * Enter a parse tree produced by the {@code errorCrea}
	 * labeled alternative in {@link MxLiteParser#creator}.
	 * @param ctx the parse tree
	 */
	void enterErrorCrea(MxLiteParser.ErrorCreaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code errorCrea}
	 * labeled alternative in {@link MxLiteParser#creator}.
	 * @param ctx the parse tree
	 */
	void exitErrorCrea(MxLiteParser.ErrorCreaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arrayCrea}
	 * labeled alternative in {@link MxLiteParser#creator}.
	 * @param ctx the parse tree
	 */
	void enterArrayCrea(MxLiteParser.ArrayCreaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arrayCrea}
	 * labeled alternative in {@link MxLiteParser#creator}.
	 * @param ctx the parse tree
	 */
	void exitArrayCrea(MxLiteParser.ArrayCreaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code classCrea}
	 * labeled alternative in {@link MxLiteParser#creator}.
	 * @param ctx the parse tree
	 */
	void enterClassCrea(MxLiteParser.ClassCreaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code classCrea}
	 * labeled alternative in {@link MxLiteParser#creator}.
	 * @param ctx the parse tree
	 */
	void exitClassCrea(MxLiteParser.ClassCreaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code basicCrea}
	 * labeled alternative in {@link MxLiteParser#creator}.
	 * @param ctx the parse tree
	 */
	void enterBasicCrea(MxLiteParser.BasicCreaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code basicCrea}
	 * labeled alternative in {@link MxLiteParser#creator}.
	 * @param ctx the parse tree
	 */
	void exitBasicCrea(MxLiteParser.BasicCreaContext ctx);
	/**
	 * Enter a parse tree produced by {@link MxLiteParser#lambda}.
	 * @param ctx the parse tree
	 */
	void enterLambda(MxLiteParser.LambdaContext ctx);
	/**
	 * Exit a parse tree produced by {@link MxLiteParser#lambda}.
	 * @param ctx the parse tree
	 */
	void exitLambda(MxLiteParser.LambdaContext ctx);
}