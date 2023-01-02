package IR.type;

public class IntType extends IRType {
	public int wide;

	public IntType(int _wide) {
		wide = _wide;
	}

	@Override
	public String toString() {
		return "i" + Integer.toString(wide);
	}

	@Override
	public int getSize() {
		return 4;
	}
}
