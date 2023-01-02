package IR.type;

public class PointerType extends IRType {
	public IRType type;

	public PointerType(IRType _type) {
		super(_type.dim + 1);
		type = _type;
	}

	@Override
	public String toString() {
		return type.toString() + "*";
	}

	@Override
	public int getSize() {
		return 4;
	}
}
