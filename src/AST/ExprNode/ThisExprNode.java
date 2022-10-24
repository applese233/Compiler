package AST.ExprNode;

import AST.*;
import Util.position;

public class ThisExprNode extends ExprNode {
	public ThisExprNode(position _pos) {
		super(_pos);
	}

	@Override
	public void Accept(ASTVisitor visitor) {
		visitor.Visit(this);
	}
}
