package AST.StmtNode;

import AST.*;
import AST.ExprNode.*;
import Util.position;

public class VarDecStmtNode extends StmtNode {
	public TypeNode type;
	public String id;
	public ExprNode expr;

	public VarDecStmtNode(position _pos, String _id, ExprNode _expr) {
		super(_pos);
		id = _id;
		expr = _expr;
		if(_expr != null) {
			expr.assign = true;
			System.out.println(_pos + " " + id + " " + expr + " " + expr.assign);
		}
	}

	@Override
	public void Accept(ASTVisitor visitor) {
		visitor.Visit(this);
	}
}
