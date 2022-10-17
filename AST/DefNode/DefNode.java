package AST.DefNode;

import Util.position;
import AST.*;
import AST.ExprNode.*;
import AST.StmtNode.*;

public abstract class DefNode extends ASTNode {
	public DefNode(position _pos) {
		super(_pos);
	}
}
