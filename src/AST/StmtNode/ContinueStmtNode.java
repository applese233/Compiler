package AST.StmtNode;

import AST.*;
import Util.position;

public class ContinueStmtNode extends StmtNode {
	public ContinueStmtNode(position _pos) {
		super(_pos);
	}

	@Override
	public void Accept(ASTVisitor visitor) {
		visitor.Visit(this);
	}
}
