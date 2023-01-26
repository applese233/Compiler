package ASM.Inst;

import ASM.ASMVisitor;
import ASM.Operand.ASMRegister;
import ASM.Operand.Immediate;

public class Lw extends Inst {
	public String global;

	public Lw(ASMRegister _rd, ASMRegister _rs1, Immediate _imm, String _global) {
		super(_rd, _rs1, null, _imm);
		global = _global;
	}

	@Override
	public String toString() {
		if(global == null) {
			return "lw\t" + rd + ", " + imm + "(" + rs1 + ")";
		}
		else {
			return "lw\t" + rd + ", " + global;
		}
	}

	@Override
	public void Accept(ASMVisitor visitor) {
		visitor.Visit(this);
	}
}
