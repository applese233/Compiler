package IR.inst;

import IR.*;
import IR.operand.*;

public abstract class Inst {
	public BasicBlock inBlock;
	public Register result;

	public Inst() {

	}

	public Inst(BasicBlock _inBlock, Register _result) {
		inBlock = _inBlock;
		result = _result;
	}

	public abstract void Accept(IRVisitor visitor);

	public abstract String toString();
}
