package AST.StmtNode;

import AST.*;
import Util.position;

public class EmptyStmtNode extends StmtNode {
	public EmptyStmtNode(position _pos) {
		super(_pos);
	}

	@Override
	public void Accept(ASTVisitor visitor) {
		visitor.Visit(this);
	}
}
