package AST.StmtNode;

import AST.*;
import AST.ExprNode.*;
import Util.position;

public class ReturnStmtNode extends StmtNode {
	public ExprNode expr;

	public ReturnStmtNode(position _pos, ExprNode _expr) {
		super(_pos);
		expr = _expr;
	}

	@Override
	public void Accept(ASTVisitor visitor) {
		visitor.Visit(this);
	}
}
