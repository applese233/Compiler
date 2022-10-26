package AST.ExprNode;

import AST.*;
import Util.position;

public class ClassExprNode extends ExprNode {
	public ExprNode name;
	public String id;
	public boolean isFunc = false;

	public ClassExprNode(position _pos, ExprNode _name, String _id) {
		super(_pos, true);
		name = _name;
		id = _id;
	}

	@Override
	public void Accept(ASTVisitor visitor) {
		visitor.Visit(this);
	}
}
