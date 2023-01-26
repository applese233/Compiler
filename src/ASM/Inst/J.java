package ASM.Inst;

import ASM.ASMVisitor;

public class J extends Inst {
	public String dest;

	public J(String _dest) {
		super(null, null, null, null);
		dest = _dest;
	}

	@Override
	public String toString() {
		return "j\t" + dest;
	}

	@Override
	public void Accept(ASMVisitor visitor) {
		visitor.Visit(this);
	}
}
