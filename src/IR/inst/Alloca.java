package IR.inst;

import IR.BasicBlock;
import IR.IRVisitor;
import IR.operand.Register;
import IR.type.IRType;

public class Alloca extends Inst {
	public IRType type;
	
	public Alloca(BasicBlock _inBlock, Register _result, IRType _type) {
		super(_inBlock, _result);
		type = _type;
	}

	@Override
	public void Accept(IRVisitor visitor) {
		visitor.Visit(this);
	}

	@Override
	public String toString() {
		return result.toString() + " = alloca" + type.toString();
	}
}
