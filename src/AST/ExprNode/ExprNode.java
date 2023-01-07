package AST.ExprNode;

import AST.*;
import IR.operand.Operand;
import Util.position;
import Util.Type;

public abstract class ExprNode extends ASTNode {
	public Type type;
	public boolean assign = false;
	public Operand operand;

	public ExprNode(position _pos) {
		super(_pos);
	}

	public ExprNode(position _pos, boolean _assign) {
		super(_pos);
		assign = _assign;
	}

	public ExprNode(position _pos, Type _type, boolean _assign) {
		super(_pos);
		type = _type;
		assign = _assign;
	}
}
