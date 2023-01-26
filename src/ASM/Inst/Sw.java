package ASM.Inst;

import ASM.ASMVisitor;
import ASM.Operand.ASMRegister;
import ASM.Operand.Immediate;

public class Sw extends Inst {
	public Sw(ASMRegister _rs1, ASMRegister _rs2, Immediate _imm) {
		super(null, _rs1, _rs2, _imm);
	}

	@Override
	public String toString() {
		return "sw\t" + rs2 + ", " + imm + "(" + rs1 + ")";
	}

	@Override
	public void Accept(ASMVisitor visitor) {
		visitor.Visit(this);
	}
}
