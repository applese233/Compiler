package IR.inst;

import IR.IRVisitor;
import IR.operand.GlobalVariable;
import IR.operand.Operand;
import IR.operand.StringConst;
import IR.type.ClassType;
import IR.type.IRType;

public class Global extends Inst {
	public GlobalVariable var;
	public IRType type;
	public Operand value;

	public Global(IRType _type) {
		type = _type;
	}

	public Global(GlobalVariable _var, IRType _type, Operand _value) {
		var = _var;
		type = _type;
		value = _value;
	}

	@Override
	public void Accept(IRVisitor visitor) {
		visitor.Visit(this);
	}

	@Override
	public String toString() {
		if(type instanceof ClassType) {
			String res = type.toString() + " = type {";
			if(((ClassType)type).typeList.size() > 0) {
				res = res + ((ClassType)type).typeList.get(0).toString();
				for(int i = 1; i < ((ClassType)type).typeList.size(); ++ i) {
					res = res + ", " + ((ClassType)type).typeList.get(i).toString();
				}
			}
			res = res + "}";
			return res;
		}
		else if(value instanceof StringConst) {
			return var.toString() + " = constant " + type.toString() + " c\"" + value.toString() + "\"";
		}
		else {
			return var.toString() + " = global " + type.toString() + " " + value.toString();
		}
	}
}
