package IR.type;

public class VoidType extends IRType {
	public VoidType() {
		super();
	}

	@Override
	public String toString() {
		return "void";
	}

	@Override
	public int getSize() {
		return 0;
	}
}
