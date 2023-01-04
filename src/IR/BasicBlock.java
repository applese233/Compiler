package IR;

import java.util.ArrayList;

import IR.inst.Alloca;
import IR.inst.Br;
import IR.inst.Inst;
import IR.inst.Ret;

public class BasicBlock {
	public String name;
	public ArrayList<Inst> allocaList, instList;
	public Inst terminator = null;
	public Function inFunction;

	public BasicBlock(String _name, Function _inFunction) {
		name = _name;
		inFunction = _inFunction;
		allocaList = new ArrayList<>();
		instList = new ArrayList<>();
	}

	public void AddInst(Inst _inst) {
		if(terminator != null) {
			return;
		}
		if(_inst instanceof Br || _inst instanceof Ret) {
			terminator = _inst;
		}
		else if(_inst instanceof Alloca) {
			allocaList.add(_inst);
		}
		else {
			instList.add(_inst);
		}
	}

	public void Accept(IRVisitor visitor) {
		visitor.Visit(this);
	}
}
