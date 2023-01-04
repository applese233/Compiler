package IR;

import java.util.ArrayList;

import IR.inst.Define;

public class Function {
	public Define funcDefine;
	public BasicBlock allocaBlock;
	public ArrayList<BasicBlock> blockList;

	public Function() {
		allocaBlock = new BasicBlock("ALLOCA", this);
		blockList = new ArrayList<>();
	}

	public void Accept(IRVisitor visitor) {
		visitor.Visit(this);
	}
}
