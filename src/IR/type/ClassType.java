package IR.type;

import java.util.ArrayList;

public class ClassType extends IRType {
	public String name;
	public ArrayList<IRType> typeList;
	public ArrayList<String> nameList;
	public boolean hasStruct = false;

	public ClassType(String _name) {
		name = _name;
		typeList = new ArrayList<>();
		nameList = new ArrayList<>();
	}

	@Override
	public String toString() {
		return "%class." + name;
	}

	@Override
	public int getSize() {
		int res = 0;
		for(IRType x : typeList) {
			res += x.getSize();
		}
		return res;
	}
}
