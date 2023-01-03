package IR.operand;

import IR.type.PointerType;
import IR.type.VoidType;

public class NullOperand extends Operand {
	public NullOperand() {
		super(new PointerType(new VoidType()));
	}

	@Override
	public String toString() {
		return "null";
	}
}
