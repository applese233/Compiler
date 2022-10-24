package AST.ExprNode;

import AST.*;
import Util.position;

public class PreExprNode extends ExprNode {
	public String op;
	public ExprNode expr;

	public PreExprNode(position _pos, String _op, ExprNode _expr) {
		super(_pos);
		op = _op;
		expr = _expr;
	}

	@Override
	public void Accept(ASTVisitor visitor) {
		visitor.Visit(this);
	}
}
