package AST;

import Util.position;

public abstract class ASTNode {
	public position pos;

	public ASTNode(position _pos) {
		pos = _pos;
	}

	public abstract void Accept(ASTVisitor visitor);
}