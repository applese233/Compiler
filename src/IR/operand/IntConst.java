package IR.operand;

import IR.type.IntType;

public class IntConst extends Operand {
	public int val;

	public IntConst(int _val) {
		super(new IntType(32));
		val = _val;
	}

	@Override
	public String toString() {
		return Integer.toString(val);
	}
}
