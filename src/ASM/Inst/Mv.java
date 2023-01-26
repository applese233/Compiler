package ASM.Inst;

import ASM.ASMVisitor;
import ASM.Operand.ASMRegister;

public class Mv extends Inst {
	public Mv(ASMRegister _rd, ASMRegister _rs1) {
		super(_rd, _rs1, null, null);
	}

	@Override
	public String toString() {
		return "mv\t" + rd + ", " + rs1;
	}

	@Override
	public void Accept(ASMVisitor visitor) {
		visitor.Visit(this);
	}
}
