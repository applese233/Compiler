package AST.ExprNode;

import AST.*;
import Util.position;

public class BinaryExprNode extends ExprNode {
	public ExprNode expr1, expr2;
	public String op;

	public BinaryExprNode(position _pos, ExprNode _expr1, ExprNode _expr2, String _op) {
		super(_pos);
		System.out.println("Binary: " + _expr1 + " " + _expr2 + " " + _op);
		expr1 = _expr1;
		expr2 = _expr2;
		op = _op;
	}

	@Override
	public void Accept(ASTVisitor visitor) {
		visitor.Visit(this);
	}
}
