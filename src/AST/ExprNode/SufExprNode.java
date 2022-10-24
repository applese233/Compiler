package AST.ExprNode;

import AST.*;
import Util.position;

public class SufExprNode extends ExprNode {
	public ExprNode expr;
	public String op;

	public SufExprNode(position _pos, ExprNode _expr, String _op) {
		super(_pos);
		expr = _expr;
		op = _op;
	}

	@Override
	public void Accept(ASTVisitor visitor) {
		visitor.Visit(this);
	}
}
