package AST.ExprNode;

import AST.*;
import AST.StmtNode.*;
import Util.position;

import java.util.ArrayList;

public class LambdaExprNode extends ExprNode {
	public ArrayList<VarDecStmtNode> paralist = new ArrayList<>();
	public SuiteStmtNode suite;
	public ExprListNode exprList;

	public LambdaExprNode(position _pos, SuiteStmtNode _suite, ExprListNode _exprList) {
		super(_pos);
		suite = _suite;
		exprList = _exprList;
	}

	@Override
	public void Accept(ASTVisitor visitor) {
		visitor.Visit(this);
	}

}
