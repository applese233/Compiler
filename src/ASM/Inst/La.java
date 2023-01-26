package ASM.Inst;

import ASM.ASMVisitor;
import ASM.Operand.ASMRegister;

public class La extends Inst {
	public String addr;

	public La(ASMRegister _rd, String _addr) {
		super(_rd, null, null, null);
		addr = _addr;
	}

	@Override
	public String toString() {
		return "la\t" + rd + ", " + addr;
	}

	@Override
	public void Accept(ASMVisitor visitor) {
		visitor.Visit(this);
	}
}
