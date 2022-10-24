package AST;

import AST.DefNode.*;
import AST.ExprNode.*;
import AST.StmtNode.*;
import AST.ProgNode;
import AST.TypeNode;

public interface ASTVisitor {
	void Visit(ProgNode it);

	void Visit(TypeNode it);

	void Visit(ClassDefNode it);

	void Visit(FuncDefNode it);

	void Visit(NullExprNode it);

	void Visit(BinaryExprNode it);

	void Visit(BoolExprNode it);

	void Visit(ClassExprNode it);

	void Visit(ExprListNode it);

	void Visit(FuncExprNode it);

	void Visit(IdExprNode it);

	void Visit(IndexExprNode it);

	void Visit(IntExprNode it);

	void Visit(LambdaExprNode it);

	void Visit(NewExprNode it);

	void Visit(PreExprNode it);

	void Visit(PureExprStmtNode it);

	void Visit(StringExprNode it);

	void Visit(SufExprNode it);

	void Visit(ThisExprNode it);

	void Visit(BreakStmtNode it);

	void Visit(ContinueStmtNode it);

	void Visit(EmptyStmtNode it);
	
	void Visit(ForStmtNode it);

	void Visit(IfStmtNode it);

	void Visit(ReturnStmtNode it);

	void Visit(SuiteStmtNode it);

	void Visit(VarDecStmtNode it);

	void Visit(VarDefStmtNode it);

	void Visit(WhileStmtNode it);
}