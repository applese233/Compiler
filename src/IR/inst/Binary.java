package IR.inst;

import IR.BasicBlock;
import IR.IRVisitor;
import IR.operand.Operand;
import IR.operand.Register;
import IR.type.IRType;

public class Binary extends Inst {
	public String inst;
	public Operand op1, op2;
	public IRType type;

	public Binary(BasicBlock _inBlock, Register _result, String _inst, IRType _type, Operand _op1, Operand _op2) {
		super(_inBlock, _result);
		inst = _inst;
		type = _type;
		op1 = _op1;
		op2 = _op2;
	}

	@Override
	public void Accept(IRVisitor visitor) {
		visitor.Visit(this);
	}

	@Override
	public String toString() {
		return result.toString() + " = " + inst + " " + type.toString() + " " + op1.toString() + ", " + op2.toString();
	}
}
