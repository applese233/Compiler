package IR;

import IR.inst.Global;

import java.util.ArrayList;

public class Module {
	public ArrayList<Global> globalList;
	public ArrayList<Function> funcList;

	public Module() {
		globalList = new ArrayList<>();
		funcList = new ArrayList<>();
	}

	public void Accept(IRVisitor visitor) {
		visitor.Visit(this);
	}
}
