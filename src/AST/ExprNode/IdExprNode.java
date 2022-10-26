package AST.ExprNode;

import AST.*;
import Util.position;

public class IdExprNode extends ExprNode {
	public String id;
	
	public IdExprNode(position _pos, String _id) {
		super(_pos, true);
		id = _id;
	}

	@Override
	public void Accept(ASTVisitor visitor) {
		visitor.Visit(this);
	}
}
