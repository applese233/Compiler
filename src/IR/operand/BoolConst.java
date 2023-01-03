package IR.operand;

import IR.type.IntType;

public class BoolConst extends Operand {
	public boolean val;

	public BoolConst(boolean _val) {
		super(new IntType(1));
		val = _val;
	}

	@Override
	public String toString() {
		return val ? "true" : "false";
	}
}
