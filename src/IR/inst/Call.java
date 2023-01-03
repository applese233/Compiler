package IR.inst;

import java.util.ArrayList;

import IR.BasicBlock;
import IR.IRVisitor;
import IR.operand.Operand;
import IR.operand.Register;
import IR.type.IRType;
import IR.type.VoidType;

public class Call extends Inst {
	public IRType type;
	public String funcId;
	public ArrayList<Operand> paraList;

	public Call(BasicBlock _inBlock, Register _result, IRType _type, String _funcId, ArrayList<Operand> _paraList) {
		super(_inBlock, _result);
		type = _type;
		funcId = _funcId;
		paraList = _paraList;
	}

	@Override
	public void Accept(IRVisitor visitor) {
		visitor.Visit(this);
	}

	@Override
	public String toString() {
		if(type instanceof VoidType) {
			String res = "call void @" + funcId + "(";
			if(paraList.size() > 0) {
				res = res + paraList.get(0).type.toString() + " " + paraList.get(0).toString();
				for(int i = 1; i < paraList.size(); ++ i) {
					res = res + ", " + paraList.get(i).type.toString() + " " + paraList.get(i).toString();
				}
			}
			res = res + ")";
			return res;
		}
		else {
			String res = result.toString() + " = call " + type.toString() + " @" + funcId + "(";
			if(paraList.size() > 0) {
				res = res + paraList.get(0).type.toString() + " " + paraList.get(0).toString();
				for(int i = 1; i < paraList.size(); ++ i) {
					res = res + ", " + paraList.get(i).type.toString() + " " + paraList.get(i).toString();
				}
			}
			res = res + ")";
			return res;
		}
	}
}
