package IR.inst;

import IR.BasicBlock;
import IR.IRVisitor;
import IR.operand.Operand;
import IR.type.IRType;

public class Store extends Inst {
	public IRType type;
	public Operand value, pointer;

	public Store(BasicBlock _inBlock, IRType _type, Operand _value, Operand _pointer) {
		super(_inBlock, null);
		type = _type;
		value = _value;
		pointer = _pointer;
	}

	@Override
	public void Accept(IRVisitor visitor) {
		visitor.Visit(this);
	}

	@Override
	public String toString() {
		return "store " + type.toString() + " " + value.toString() + ", " + type.toString() + "* " + pointer.toString();
	}
}
