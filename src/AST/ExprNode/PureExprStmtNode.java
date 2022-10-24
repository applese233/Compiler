package AST.ExprNode;

import AST.*;
import Util.position;

public class PureExprStmtNode extends ExprNode {
	public ExprNode expr;

	public PureExprStmtNode(position _pos, ExprNode _expr) {
		super(_pos);
		expr = _expr;
	}

	@Override
	public void Accept(ASTVisitor visitor) {
		visitor.Visit(this);
	}
}
