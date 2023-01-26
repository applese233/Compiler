package ASM.Inst;

import ASM.ASMVisitor;
import ASM.Operand.ASMRegister;
import ASM.Operand.Immediate;

public class Arithmetic extends Inst {
	public String op;
	public boolean immArith = false;

	public Arithmetic(String _op, ASMRegister _rd, ASMRegister _rs1, ASMRegister _rs2, Immediate _imm) {
		super(_rd, _rs1, _rs2, _imm);
		op = _op;
		if(_imm != null) {
			immArith = true;
		}
	}
	
	@Override
	public String toString() {
		if(immArith) {
			return op + "i\t" + rd + ", " + rs1 + ", " + imm;
		}
		else {
			return op + "\t" + rd + ", " + rs1 + ", " + rs2;
		}
	}
	
	@Override
	public void Accept(ASMVisitor visitor) {
		visitor.Visit(this);
	}
}
