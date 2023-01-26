package ASM.Operand;

public class PhysicalRegister extends ASMRegister {
	public PhysicalRegister(String _name) {
		super(_name);
	}

	@Override
	public String toString() {
		return name;
	}
}
