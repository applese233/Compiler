package AST;

import Util.position;

import java.util.ArrayList;

public class ProgNode extends ASTNode {
	public ArrayList<ASTNode> list = new ArrayList<>();

	public ProgNode(position _pos) {
		super(_pos);
	}

	@Override
	public void Accept(ASTVisitor visitor) {
		visitor.Visit(this);
	}
}