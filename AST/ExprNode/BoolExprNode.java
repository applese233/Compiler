package AST.ExprNode;

import AST.*;
import Util.position;

public class BoolExprNode extends ExprNode {
	public boolean value;

	public BoolExprNode(position _pos, boolean _value) {
		super(_pos);
		value = _value;
	}

	@Override
	public void Accept(ASTVisitor visitor) {
		visitor.Visit(this);
	}
}
