package IR.operand;

import IR.type.IRType;

public class Temporary extends Operand {
	public String name;
	
	public Temporary(IRType _type, String _name) {
		super(_type);
		name = _name;
	}

	@Override
	public String toString() {
		return "%" + name;
	}
}
