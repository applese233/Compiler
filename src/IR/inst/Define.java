package IR.inst;

import java.util.ArrayList;

import IR.IRVisitor;
import IR.operand.Register;
import IR.type.IRType;

public class Define extends Inst {
	public IRType type;
	public String name;
	public ArrayList<IRType> typeList = new ArrayList<>();
	public ArrayList<Register> regList = new ArrayList<>();

	public Define(IRType _type, String _name) {
		type = _type;
		name = _name;
	}

	@Override
	public void Accept(IRVisitor visitor) {
		visitor.Visit(this);
	}

	@Override
	public String toString() {
		String res = "define " + type.toString() + " @" + name + "(";
		if(typeList.size() > 0) {
			res = res + typeList.get(0).toString() + " " + regList.get(0).toString();
			for(int i = 1; i < typeList.size(); ++ i) {
				res = res + ", " + typeList.get(i).toString() + " " + regList.get(i).toString();
			}
		}
		res = res + ") {";
		return res;
	}
}
