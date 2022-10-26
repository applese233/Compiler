package AST.ExprNode;

import AST.*;
import Util.position;

import java.util.ArrayList;

public class ExprListNode extends ExprNode {
	public ArrayList<ExprNode> exprList = new ArrayList<>();

	public ExprListNode(position _pos) {
		super(_pos);
	}

	@Override
	public void Accept(ASTVisitor visitor) {
		visitor.Visit(this);
	}
}
