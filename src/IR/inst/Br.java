package IR.inst;

import IR.*;
import IR.operand.*;

public class Br extends Inst {
	public Operand cond = null;
	public BasicBlock iftrue, iffalse;

	public Br(BasicBlock _inBlock, BasicBlock _dest) {
		super(_inBlock, null);
		iftrue = _dest;
	}

	public Br(BasicBlock _inBlock, Operand _cond, BasicBlock _iftrue, BasicBlock _iffalse) {
		super(_inBlock, null);
		cond = _cond;
		iftrue = _iftrue;
		iffalse = _iffalse;
	}

	@Override
	public void Accept(IRVisitor visitor) {
		visitor.Visit(this);
	}

	@Override
	public String toString() {
		if(cond == null)
			return "br label %" + iftrue.name;
		else
			return "br i1 " + cond.toString() + ", label %" + iftrue.name + ", label %" + iffalse.name;
	}
}
