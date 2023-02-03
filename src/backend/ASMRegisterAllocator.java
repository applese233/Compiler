package backend;

import java.util.ListIterator;

import ASM.ASMBlock;
import ASM.ASMFunction;
import ASM.ASMModule;
import ASM.Inst.Arithmetic;
import ASM.Inst.Inst;
import ASM.Inst.Li;
import ASM.Inst.Lw;
import ASM.Inst.Sw;
import ASM.Operand.Immediate;
import ASM.Operand.PhysicalRegister;
import ASM.Operand.VirtualRegister;

public class ASMRegisterAllocator {
	public ASMModule module;
	public ASMFunction nowFunction;
	public ASMBlock nowBlock;
	public PhysicalRegister zero, sp, s0, t3, t4, t5, t6;
	public ListIterator<Inst> it;

	public ASMRegisterAllocator(ASMModule _module) {
		module = _module;
		zero = _module.phyRegList.get(0);
		sp = _module.phyRegList.get(2);
		s0 = _module.phyRegList.get(8);
		t3 = _module.phyRegList.get(28);
		t4 = _module.phyRegList.get(29);
		t5 = _module.phyRegList.get(30);
		t6 = _module.phyRegList.get(31);
	}

	public PhysicalRegister Allocate(VirtualRegister vReg, PhysicalRegister pReg, boolean op) {
		if(!nowFunction.offMap.containsKey(vReg.name)) {
			nowFunction.Alloca(vReg);
		}

		int off = -nowFunction.offMap.get(vReg.name);
		if(-2048 <= off && off <= 2047) {
			if(op) {
				it.previous();
				it.add(new Lw(pReg, s0, new Immediate(off), null));
				it.next();
			}
			else {
				it.add(new Sw(s0, pReg, new Immediate(off)));
			}
		}
		else {
			if(op) {
				it.previous();
				it.add(new Li(t6, new Immediate(off)));
				it.add(new Arithmetic("add", t6, t6, s0, null));
				it.add(new Lw(pReg, t6, new Immediate(0), null));
				it.next();
			}
			else {
				it.add(new Li(t6, new Immediate(off)));
				it.add(new Arithmetic("add", t6, t6, s0, null));
				it.add(new Sw(t6, pReg, new Immediate(0)));
			}
		}
		return pReg;
	}

	public void RegisterAllocate() {
		for(ASMFunction f : module.funcList) {
			nowFunction = f;
			for(ASMBlock b : nowFunction.blockList) {
				nowBlock = b;
				it = b.instList.listIterator(0);
				while(it.hasNext()) {
					Inst i = it.next();
					if(i.rs1 instanceof VirtualRegister) {
						i.rs1 = Allocate((VirtualRegister) i.rs1, t3, true);
					}
					if(i.rs2 instanceof VirtualRegister) {
						i.rs2 = Allocate((VirtualRegister) i.rs2, t4, true);
					}
					if(i.rd instanceof VirtualRegister) {
						i.rd = Allocate((VirtualRegister) i.rd, t5, false);
					}
				}
			}
		}
	}
}
