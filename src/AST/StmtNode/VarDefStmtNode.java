package AST.StmtNode;

import AST.*;
import Util.position;

import java.util.ArrayList;

public class VarDefStmtNode extends StmtNode {
	public ArrayList<VarDecStmtNode> varList = new ArrayList<>();

	public VarDefStmtNode(position _pos) {
		super(_pos);
	}

	@Override
	public void Accept(ASTVisitor visitor) {
		visitor.Visit(this);
	}
}
