package IR.inst;

import IR.BasicBlock;
import IR.IRVisitor;
import IR.operand.Operand;
import IR.operand.Register;
import IR.type.IRType;

public class Icmp extends Inst {
	public Operand op1, op2;
	public String cond;
	public IRType type;

	public Icmp(BasicBlock _inBlock, Register _result, String _cond, IRType _type, Operand _op1, Operand _op2) {
		super(_inBlock, _result);
		cond = _cond;
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
		return result.toString() + " = icmp " + cond + " " + type.toString() + " " + op1.toString() + ", " + op2.toString();
	}
}
