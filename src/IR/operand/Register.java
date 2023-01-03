package IR.operand;

import IR.type.IRType;

public class Register extends Operand {
	public String name;

	public Register(IRType _type, String _name) {
		super(_type);
		name = _name;
	}

	@Override
	public String toString() {
		return "%R" + name;
	}
}
