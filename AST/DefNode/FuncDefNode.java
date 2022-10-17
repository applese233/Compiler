package AST.DefNode;

import AST.ASTNode;

import Util.position;
import AST.*;
import AST.ExprNode.*;
import AST.StmtNode.*;

import java.util.ArrayList;

public abstract class FuncDefNode extends ASTNode {
	public TypeNode type;
	public String id;
	public ArrayList<VarDecStmtNode> paralist = new ArrayList<>();
	public SuiteStmtNode suite;

	public FuncDefNode(position _pos, TypeNode _type, String _id, ArrayList<VarDecStmtNode> _paralist, SuiteStmtNode _suite) {
		super(_pos);
		type = _type;
		id = _id;
		paralist = _paralist;
		suite = _suite;
	}

	@Override
	public void Accept(ASTVisitor visitor) {
		visitor.Visit(this);
	}
}
