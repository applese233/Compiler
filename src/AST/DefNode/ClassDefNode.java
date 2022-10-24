package AST.DefNode;

import Util.position;
import AST.*;
import AST.StmtNode.*;

import java.util.ArrayList;

public class ClassDefNode extends ASTNode {
	public String id;
	public ArrayList<VarDecStmtNode> varlist = new ArrayList<>();
	public ArrayList<FuncDefNode> funclist = new ArrayList<>();
	public FuncDefNode struct = null;

	public ClassDefNode(position _pos, String _id) {
		super(_pos);
		id = _id;
	}

	@Override
	public void Accept(ASTVisitor visitor) {
		visitor.Visit(this);
	}
}
