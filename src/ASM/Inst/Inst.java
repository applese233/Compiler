package ASM.Inst;

import java.util.HashSet;

import ASM.ASMVisitor;
import ASM.Operand.ASMRegister;
import ASM.Operand.Immediate;

public abstract class Inst {
	public ASMRegister rd, rs1, rs2;
	public Immediate imm;
	public HashSet<ASMRegister> use = new HashSet<>();
	public HashSet<ASMRegister> def = new HashSet<>();

	public Inst(ASMRegister _rd, ASMRegister _rs1, ASMRegister _rs2, Immediate _imm) {
		rd = _rd;
		rs1 = _rs1;
		rs2 = _rs2;
		imm = _imm;
		if(rd != null) {
			def.add(rd);
		}
		if(rs1 != null) {
			use.add(rs1);
		}
		if(rs2 != null) {
			use.add(rs2);
		}
	}

	public abstract String toString();

	public abstract void Accept(ASMVisitor visitor);
}
