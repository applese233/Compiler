package AST.StmtNode;

import AST.*;
import Util.position;

import java.util.ArrayList;

public class SuiteStmtNode extends StmtNode {
	public ArrayList<StmtNode> stmtList = new ArrayList<>();

	public SuiteStmtNode(position _pos) {
		super(_pos);
	}

	@Override
	public void Accept(ASTVisitor visitor) {
		visitor.Visit(this);
	}
}
