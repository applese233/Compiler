package ASM.Operand;

public class GlobalRegister extends ASMRegister {
	public int size, value;
	
	public GlobalRegister(String _name, int _size, int _value) {
		super(_name);
		size = _size;
		value = _value;
	}

	@Override
	public String toString() {
		return "\t.type\t" + name + ",@object\n" + "\t.section\t.sbss\n" + "\t.globl\t" + name + "\n" + "\t.p2align\t2\n" + name + ":\n" + "\t.word\t" + value + "\n" + "\t.size\t" + name + ", " + size;
	}
}
