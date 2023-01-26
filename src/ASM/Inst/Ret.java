package ASM.Inst;

import ASM.ASMVisitor;

public class Ret extends Inst {
	public Ret() {
		super(null, null, null, null);
	}

	@Override
	public String toString() {
		return "ret";
	}

	@Override
	public void Accept(ASMVisitor visitor) {
		visitor.Visit(this);
	}
}
