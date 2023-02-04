package ASM.Inst;

import ASM.ASMVisitor;
import ASM.Operand.ASMRegister;

public class Compare extends Inst {
	public String op;

	public Compare(String _op, ASMRegister _rd, ASMRegister _rs1, ASMRegister _rs2) {
		super(_rd, _rs1, _rs2, null);
		op = _op;
	}

	@Override
	public String toString() {
		if(op.equals("seqz") || op.equals("snez")) {
			return op + "\t" + rd + ", " + rs1;
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
