package ASM;

import java.util.ArrayList;
import java.util.HashMap;

import ASM.Operand.VirtualRegister;

public class ASMFunction {
	public String name;
	public int offset, calloff;
	public ArrayList<ASMBlock> blockList;
	public HashMap<String, ASMBlock> blockMap;
	public HashMap<String, Integer> offMap;

	public ASMFunction(String _name) {
		name = _name;
		offset = 8;
		calloff = 0;
		blockList = new ArrayList<>();
		blockMap = new HashMap<>();
		offMap = new HashMap<>();
	}

	public void Alloca(VirtualRegister reg) {
		offset += reg.size;
		offMap.put(reg.name, offset);
	}

	public void Accept(ASMVisitor visitor) {
		visitor.Visit(this);
	}
}
