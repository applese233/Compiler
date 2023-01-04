package IR.inst;

import IR.BasicBlock;
import IR.IRVisitor;
import IR.operand.Operand;
import IR.type.IRType;
import IR.type.VoidType;

public class Ret extends Inst {
	public IRType type = null;
	public Operand value = null;

	public Ret(BasicBlock _inBlock) {
		super(_inBlock, null);
	}

	public Ret(BasicBlock _inBlock, IRType _type, Operand _value) {
		super(_inBlock, null);
		type = _type;
		value = _value;
	}

	@Override
	public void Accept(IRVisitor visitor) {
		visitor.Visit(this);
	}

	@Override
	public String toString() {
		if(type instanceof VoidType || type == null) {
			return "ret void";
		}
		else {
			return "ret " + type.toString() + " " + value.toString();
		}
	}
}
