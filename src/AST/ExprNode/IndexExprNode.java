package AST.ExprNode;

import AST.*;
import Util.position;

public class IndexExprNode extends ExprNode {
	public ExprNode bas;
	public ExprNode off;

	public IndexExprNode(position _pos, ExprNode _bas, ExprNode _off) {
		super(_pos, true);
		bas = _bas;
		off = _off;
	}

	@Override
	public void Accept(ASTVisitor visitor) {
		visitor.Visit(this);
	}
}
