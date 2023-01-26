package ASM.Inst;

import ASM.ASMVisitor;

public class Call extends Inst {
	public String funcId;

	public Call(String _funcId) {
		super(null, null, null, null);
		funcId = _funcId;
	}

	@Override
	public String toString() {
		return "call\t" + funcId;
	}

	@Override
	public void Accept(ASMVisitor visitor) {
		visitor.Visit(this);
	}
}
