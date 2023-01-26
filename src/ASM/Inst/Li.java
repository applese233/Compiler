package ASM.Inst;

import ASM.ASMVisitor;
import ASM.Operand.ASMRegister;
import ASM.Operand.Immediate;

public class Li extends Inst {
	public Li(ASMRegister _rd, Immediate _imm) {
		super(_rd, null, null, _imm);
	}

	@Override
	public String toString() {
		return "li\t" + rd + ", " + imm;
	}

	@Override
	public void Accept(ASMVisitor visitor) {
		visitor.Visit(this);
	}
}
