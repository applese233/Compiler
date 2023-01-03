package IR.inst;

import IR.BasicBlock;
import IR.IRVisitor;
import IR.operand.Operand;
import IR.operand.Register;
import IR.type.IRType;

public class Load extends Inst {
	public Operand pointer;
	public IRType type;

	public Load(BasicBlock _inBlock, Register _result, IRType _type, Operand _pointer) {
		super(_inBlock, _result);
		type = _type;
		pointer = _pointer;
	}

	@Override
	public void Accept(IRVisitor visitor) {
		visitor.Visit(this);
	}

	@Override
	public String toString() {
		return result.toString() + " = load " + type.toString() + ", " + type.toString() + "* " + pointer.toString();
	}
}
