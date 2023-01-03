package IR.operand;

import IR.type.IRType;

public class StringConst extends Operand {
	public String str;

	public StringConst(IRType _type, String _str) {
		super(_type);
		str = _str;
	}

	@Override
	public String toString() {
		return str;
	}
}
