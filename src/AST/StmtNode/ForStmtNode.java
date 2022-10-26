package AST.StmtNode;

import AST.*;
import AST.ExprNode.*;
import Util.position;

public class ForStmtNode extends StmtNode {
	public ExprNode init, cond, incr;
	public StmtNode stmt;

	public ForStmtNode(position _pos, ExprNode _init, ExprNode _cond, ExprNode _incr, StmtNode _stmt) {
		super(_pos);
		init = _init;
		cond = _cond;
		incr = _incr;
		stmt = _stmt;
	}

	@Override
	public void Accept(ASTVisitor visitor) {
		visitor.Visit(this);
	}
}
