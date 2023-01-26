package ASM.Operand;

public abstract class ASMRegister extends ASMOperand {
	public String name;

	public ASMRegister(String _name) {
		name = _name;
	}
}
