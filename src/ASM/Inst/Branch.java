package ASM.Inst;

import ASM.ASMVisitor;
import ASM.Operand.ASMRegister;

public class Branch extends Inst {
	public String op, dest;

	public Branch(String _op, ASMRegister _rs1, String _dest) {
		super(null, _rs1, null, null);
		op = _op;
		dest = _dest;
	}

	@Override
	public String toString() {
		return op + "\t" + rs1 + ",\t" + dest;
	}

	@Override
	public void Accept(ASMVisitor visitor) {
		visitor.Visit(this);
	}
}
