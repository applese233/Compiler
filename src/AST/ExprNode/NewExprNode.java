package AST.ExprNode;

import AST.*;
import Util.position;

import java.util.ArrayList;

public class NewExprNode extends ExprNode {
	public TypeNode typeNode;
	public ArrayList<ExprNode> exprList = new ArrayList<>();

	public NewExprNode(position _pos, TypeNode _typeNode, int _dim, ArrayList<ExprNode> _exprList) {
		super(_pos);
		typeNode = _typeNode;
		typeNode.dim = _dim;
		exprList = _exprList;
	}

	@Override
	public void Accept(ASTVisitor visitor) {
		visitor.Visit(this);
	}
}
