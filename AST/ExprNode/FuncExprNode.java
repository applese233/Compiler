package AST.ExprNode;

import AST.*;
import Util.position;

public class FuncExprNode extends ExprNode {
	public ExprNode id;
	public ExprListNode exprList;

	public FuncExprNode(position _pos, ExprNode _id, ExprListNode _exprList) {
		super(_pos);
		id = _id;
		exprList = _exprList;
	}

	@Override
	public void Accept(ASTVisitor visitor) {
		visitor.Visit(this);
	}
}
