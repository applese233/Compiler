package ASM;

import java.util.ArrayList;
import java.util.HashMap;

import ASM.Operand.ASMOperand;
import ASM.Operand.PhysicalRegister;

public class ASMModule {
	public ArrayList<ASMOperand> globalList;
	public ArrayList<ASMFunction> funcList;
	public ArrayList<PhysicalRegister> phyRegList;
	public ArrayList<PhysicalRegister> caller;
	public ArrayList<PhysicalRegister> callee;
	public ArrayList<PhysicalRegister> icolors;
	public HashMap<PhysicalRegister, Integer> phyRegId;

	public ASMModule() {
		globalList = new ArrayList<>();
		funcList = new ArrayList<>();
		phyRegList = new ArrayList<>();
		caller = new ArrayList<>();
		callee = new ArrayList<>();
		icolors = new ArrayList<>();
		phyRegId = new HashMap<>();

		PhysicalRegister now;
		phyRegList.add(new PhysicalRegister("zero"));
		phyRegList.add(new PhysicalRegister("ra"));
		phyRegList.add(new PhysicalRegister("sp"));
		phyRegList.add(new PhysicalRegister("gp"));
		phyRegList.add(new PhysicalRegister("tp"));

		for(int i = 0; i <= 2; ++ i) {
			now = new PhysicalRegister("t" + i);
			phyRegList.add(now);
			caller.add(now);
			icolors.add(now);
		}

		for(int i = 0; i <= 1; ++ i) {
			now = new PhysicalRegister("s" + i);
			phyRegList.add(now);
			callee.add(now);
			icolors.add(now);
		}

		for(int i = 0; i <= 7; ++ i) {
			now = new PhysicalRegister("a" + i);
			phyRegList.add(now);
			caller.add(now);
			icolors.add(now);
		}

		for(int i = 2; i <= 11; ++ i) {
			now = new PhysicalRegister("s" + i);
			phyRegList.add(now);
			callee.add(now);
			icolors.add(now);
		}

		for(int i = 3; i <= 6; ++ i) {
			now = new PhysicalRegister("t" + i);
			phyRegList.add(now);
			caller.add(now);
			icolors.add(now);
		}

		for(int i = 0; i < 32; ++ i) {
			phyRegId.put(phyRegList.get(i), i);
		}
	}

	public void Accept(ASMVisitor visitor) {
		visitor.Visit(this);
	}
}
