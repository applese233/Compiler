package AST.StmtNode;

import AST.*;
import AST.ExprNode.*;
import Util.position;

public class IfStmtNode extends StmtNode {
	public ExprNode expr;
	public StmtNode trueStmt, falseStmt;

	public IfStmtNode(position _pos, ExprNode _expr, StmtNode _trueStmt, StmtNode _falseStmt) {
		super(_pos);
		expr = _expr;
		trueStmt = _trueStmt;
		falseStmt = _falseStmt;
	}

	@Override
	public void Accept(ASTVisitor visitor) {
		visitor.Visit(this);
	}
}
