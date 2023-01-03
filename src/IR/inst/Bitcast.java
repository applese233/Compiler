package IR.inst;

import IR.IRVisitor;
import IR.BasicBlock;
import IR.operand.Operand;
import IR.operand.Register;
import IR.type.IRType;

public class Bitcast extends Inst {
	public IRType oldtype, newtype;
	public Operand value;

	public Bitcast(BasicBlock _inBlock, Register _result, IRType _oldtype, Operand _value, IRType _newtype) {
		super(_inBlock, _result);
		oldtype = _oldtype;
		value = _value;
		newtype = _newtype;
	}

	@Override
	public void Accept(IRVisitor visitor) {
		visitor.Visit(this);
	}

	@Override
	public String toString() {
		return result.toString() + " = bitcast " + oldtype.toString() + " " + value.toString() + " to " + newtype.toString();
	}
}
