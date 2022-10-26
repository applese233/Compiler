package AST.StmtNode;

import AST.*;
import Util.position;

public class BreakStmtNode extends StmtNode {
	public BreakStmtNode(position _pos) {
		super(_pos);
	}

	@Override
	public void Accept(ASTVisitor visitor) {
		visitor.Visit(this);
	}
}
