package AST.StmtNode;

import AST.*;
import AST.ExprNode.*;
import Util.position;

public class WhileStmtNode extends StmtNode {
	public ExprNode expr;
	public StmtNode stmt;

	public WhileStmtNode(position _pos, ExprNode _expr, StmtNode _stmt) {
		super(_pos);
		expr = _expr;
		stmt = _stmt;
	}

	@Override
	public void Accept(ASTVisitor visitor) {
		visitor.Visit(this);
	}
}
