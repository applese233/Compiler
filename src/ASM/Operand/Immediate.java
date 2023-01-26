package ASM.Operand;

public class Immediate extends ASMOperand {
	public int value;

	public Immediate(int _value) {
		value = _value;
	}

	@Override
	public String toString() {
		return Integer.toString(value);
	}
}
