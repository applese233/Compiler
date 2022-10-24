package AST.ExprNode;

import AST.*;
import Util.position;

public class IntExprNode extends ExprNode {
	public int value;

	public IntExprNode(position _pos, int _value) {
		super(_pos);
		value = _value;
	}

	@Override
	public void Accept(ASTVisitor visitor) {
		visitor.Visit(this);
	}
}
