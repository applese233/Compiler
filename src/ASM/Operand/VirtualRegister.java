package ASM.Operand;

public class VirtualRegister extends ASMRegister {
	public int size;

	public VirtualRegister(String _name, int _size) {
		super(_name);
		size = _size;
	}

	@Override
	public String toString() {
		return "VR" + name;
	}
}
