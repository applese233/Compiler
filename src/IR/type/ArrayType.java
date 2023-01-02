package IR.type;

public class ArrayType extends IRType {
	public int arraydim;
	public IRType type;

	public ArrayType(int _arraydim, IRType _type) {
		super();
		arraydim = _arraydim;
		type = _type;
	}

	@Override
	public String toString() {
		return "[" + Integer.toString(arraydim) + "x" + type.toString() + "]";
	}

	@Override
	public int getSize() {
		return arraydim * type.getSize();
	}
}
