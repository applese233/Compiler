package AST.ExprNode;

import AST.*;
import Util.position;

public class StringExprNode extends ExprNode {
	public String value;

	public StringExprNode(position _pos, String _value) {
		super(_pos);
		value = _value;
	}

	@Override
	public void Accept(ASTVisitor visitor) {
		visitor.Visit(this);
	}
}
