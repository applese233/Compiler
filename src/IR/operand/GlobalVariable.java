package IR.operand;

import IR.type.IRType;

public class GlobalVariable extends Operand {
	public String name;

	public GlobalVariable(String _name, IRType _type) {
		super(_type);
		name = _name;
	}

	@Override
	public String toString() {
		return "@" + name;
	}
}
