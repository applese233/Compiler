package ASM;

import java.util.ArrayList;
import java.util.LinkedList;

import ASM.Inst.*;

public class ASMBlock {
	public String name;
	public LinkedList<Inst> instList;
	public ArrayList<ASMBlock> succ;

	public ASMBlock(String _name) {
		name = _name;
		instList = new LinkedList<>();
		succ = new ArrayList<>();
	}

	public void AddInst(Inst x) {
		instList.add(x);
	}

	public void Accept(ASMVisitor visitor) {
		visitor.Visit(this);
	}
}
