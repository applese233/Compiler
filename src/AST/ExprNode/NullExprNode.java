package AST.ExprNode;

import AST.*;
import Util.position;

public class NullExprNode extends ExprNode {
	public NullExprNode(position _pos) {
		super(_pos);
	}

	@Override
	public void Accept(ASTVisitor visitor) {
		visitor.Visit(this);
	}
}
