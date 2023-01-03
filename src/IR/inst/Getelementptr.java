package IR.inst;

import java.util.ArrayList;

import IR.BasicBlock;
import IR.IRVisitor;
import IR.operand.IntConst;
import IR.operand.Operand;
import IR.operand.Register;
import IR.type.PointerType;

public class Getelementptr extends Inst {
	public PointerType type;
	public Operand value;
	public ArrayList<Operand> idxList;

	public Getelementptr(BasicBlock _inBlock, Register _result, PointerType _type, Operand _value) {
		super(_inBlock, _result);
		type = _type;
		value = _value;
		idxList = new ArrayList<>();
		idxList.add(new IntConst(0));
		idxList.add(new IntConst(0));
	}

	public Getelementptr(BasicBlock _inBlock, Register _result, PointerType _type, Operand _value, ArrayList<Operand> _idxList) {
		super(_inBlock, _result);
		type = _type;
		value = _value;
		idxList = _idxList;
	}

	@Override
	public void Accept(IRVisitor visitor) {
		visitor.Visit(this);
	}

	@Override
	public String toString() {
		String res = result.toString() + " = getelementptr inbounds " + type.type.toString() + ", " + type.toString() + " " + value.toString();
		for(Operand x : idxList) {
			res = res + ", " + x.type.toString() + " " + x.toString();
		}
		return res;
	}
}
