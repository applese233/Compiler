package backend;

import java.util.ArrayList;
import java.util.HashMap;

import ASM.*;
import ASM.Inst.*;
import ASM.Operand.*;
import IR.*;
import IR.inst.*;
import IR.operand.*;
import IR.type.*;

public class ASMBuilder implements IRVisitor {
	public ASMModule module;
	public ASMFunction nowFunction;
	public ASMBlock nowBlock;
	public PhysicalRegister zero, ra, sp, s0, a0;
	public HashMap<String, ASMRegister> regMap;
	public ArrayList<VirtualRegister> calleeList;
	int extraNum = -1;

	public ASMBuilder(ASMModule _module) {
		module = _module;
		zero = _module.phyRegList.get(0);
		ra = _module.phyRegList.get(1);
		sp = _module.phyRegList.get(2);
		s0 = _module.phyRegList.get(8);
		a0 = _module.phyRegList.get(10);
		regMap = new HashMap<>();
		calleeList = new ArrayList<>();
	}

	public ASMRegister getReg(Operand operand) {
		VirtualRegister res = null;
		if(operand instanceof Register) {
			if(regMap.containsKey(nowFunction.name + "_" + ((Register) operand).name)) {
				return regMap.get(nowFunction.name + "_" + ((Register) operand).name);
			}
			else {
				res = new VirtualRegister(((Register) operand).name, 4);
				regMap.put(nowFunction.name + "_" + ((Register) operand).name, res);
			}
		}
		else if(operand instanceof Temporary) {
			if(regMap.containsKey(nowFunction.name + "_" + ((Temporary) operand).name)) {
				return regMap.get(nowFunction.name + "_" + ((Temporary) operand).name);
			}
			else {
				res = new VirtualRegister(((Temporary) operand).name, 4);
				regMap.put(nowFunction.name + "_" + ((Temporary) operand).name, res);
			}
		}
		else if(operand instanceof GlobalVariable) {
			extraNum ++;
			res = new VirtualRegister("_EX_" + extraNum, 4);
			nowBlock.AddInst(new La(res, ((GlobalVariable) operand).name));
			return res;
		}
		else {
			extraNum ++;
			res = new VirtualRegister("_EX_" + extraNum, 4);
			int value = 0;
			if(operand instanceof IntConst) {
				value = ((IntConst) operand).val;
			}
			else if(operand instanceof BoolConst) {
				value = ((BoolConst) operand).val ? 1 : 0;
			}
			else if(operand instanceof NullOperand) {
				value = 0;
			}
			if(value == 0) {
				return zero;
			}
			Immediate imm = new Immediate(value);
			nowBlock.AddInst(new Li(res, imm));
		}
		return res;
	}

	@Override
	public void Visit(Module_ it) {
		it.globalList.forEach(x -> x.Accept(this));
		it.funcList.forEach(x -> x.Accept(this));
	}

	@Override
	public void Visit(Function it) {
		nowFunction = new ASMFunction(it.funcDefine.name);
		nowBlock = new ASMBlock("." + it.funcDefine.name + "_INIT");
		it.funcDefine.Accept(this);
		nowBlock.AddInst(new J("." + it.funcDefine.name + "_L0"));
		nowFunction.blockList.add(nowBlock);
		it.allocaBlock.allocaList.forEach(x -> x.Accept(this));
		it.blockList.forEach(x -> x.Accept(this));
		nowBlock = new ASMBlock("." + it.funcDefine.name + "_RETURN");
		nowFunction.blockList.add(nowBlock);
		module.funcList.add(nowFunction);
	}

	@Override
	public void Visit(BasicBlock it) {
		nowBlock = new ASMBlock("." + nowFunction.name + "_" + it.name);
		it.instList.forEach(x -> x.Accept(this));
		it.terminator.Accept(this);
		nowFunction.blockList.add(nowBlock);
	}

	@Override
	public void Visit(Alloca it) {
		VirtualRegister allReg = new VirtualRegister(it.result.name, 4);
		regMap.put(nowFunction.name + "_" + it.result.name, allReg);
		nowFunction.Alloca(allReg);
	}

	@Override
	public void Visit(Binary it) {
		String alop = it.inst;
		if(alop.equals("sdiv")) {
			alop = "div";
		}
		if(alop.equals("srem")) {
			alop = "rem";
		}
		if(alop.equals("shl")) {
			alop = "sll";
		}
		if(alop.equals("ashr")) {
			alop = "sra";
		}
		ASMRegister rd = getReg(it.result);
		ASMRegister rs1 = getReg(it.op1);
		ASMRegister rs2 = getReg(it.op2);
		nowBlock.AddInst(new Arithmetic(alop, rd, rs1, rs2, null));
	}

	@Override
	public void Visit(Br it) {
		if(it.cond == null) {
			nowBlock.AddInst(new J("." + it.iftrue.inFunction.funcDefine.name + "_" + it.iftrue.name));
		}
		else {
			ASMRegister rs1 = getReg(it.cond);
			nowBlock.AddInst(new Branch("beqz", rs1, "." + it.iffalse.inFunction.funcDefine.name + "_" + it.iffalse.name));
			nowBlock.AddInst(new J("." + it.iftrue.inFunction.funcDefine.name + "_" + it.iftrue.name));
		}
	}

	@Override
	public void Visit(Define it) {
		calleeList.clear();
		for(int i = 0; i < module.callee.size(); ++ i) {
			PhysicalRegister x = module.callee.get(i);
			extraNum ++;
			VirtualRegister EX = new VirtualRegister("_EX_" + extraNum, 4);
			calleeList.add(EX);
			nowBlock.AddInst(new Mv(EX, x));
		}

		for(int i = 0; i < Integer.min(it.regList.size(), 8); ++ i) {
			ASMRegister tmpReg = getReg(it.regList.get(i));
			nowBlock.AddInst(new Mv(tmpReg, module.phyRegList.get(10 + i)));
		}

		for(int i = 8; i < it.regList.size(); ++ i) {
			ASMRegister tmpReg = getReg(it.regList.get(i));
			nowBlock.AddInst(new Lw(tmpReg, s0, new Immediate((i - 8) * 4), null));
		}
	}

	@Override
	public void Visit(Global it) {
		if(it.type instanceof ClassType) {
			return;
		}
		if(it.value instanceof StringConst) {
			module.globalList.add(new String_(it.var.name, ((StringConst) it.value).str));
		}
		else {
			module.globalList.add(new GlobalRegister(it.var.name, 4, 0));
		}
	}

	@Override
	public void Visit(Icmp it) {
		ASMRegister rd = getReg(it.result), rs1 = getReg(it.op1), rs2 = getReg(it.op2);

		switch(it.cond) {
			case "eq": {
				extraNum ++;
				VirtualRegister EX = new VirtualRegister("_EX_" + extraNum, 4);
				nowBlock.AddInst(new Arithmetic("xor", EX, rs1, rs2, null));
				nowBlock.AddInst(new Compare("seqz", rd, EX, null));
				break;
			}
			case "ne": {
				extraNum ++;
				VirtualRegister EX = new VirtualRegister("_EX_" + extraNum, 4);
				nowBlock.AddInst(new Arithmetic("xor", EX, rs1, rs2, null));
				nowBlock.AddInst(new Compare("snez", rd, EX, null));
				break;
			}
			case "slt": {
				nowBlock.AddInst(new Compare("slt", rd, rs1, rs2));
				break;
			}
			case "sle": {
				nowBlock.AddInst(new Compare("sgt", rd, rs1, rs2));
				nowBlock.AddInst(new Arithmetic("xor", rd, rd, null, new Immediate(1)));
				break;
			}
			case "sgt": {
				nowBlock.AddInst(new Compare("sgt", rd, rs1, rs2));
				break;
			}
			case "sge": {
				nowBlock.AddInst(new Compare("slt", rd, rs1, rs2));
				nowBlock.AddInst(new Arithmetic("xor", rd, rd, null, new Immediate(1)));
				break;
			}
		}
	}

	@Override
	public void Visit(Load it) {
		VirtualRegister rd = new VirtualRegister(it.result.name, 4);
		regMap.put(nowFunction.name + "_" + it.result.name, rd);
		if(it.pointer instanceof GlobalVariable) {
			nowBlock.AddInst(new Lw(rd, null, null, ((GlobalVariable) it.pointer).name));
		}
		else {
			ASMRegister rs1 = getReg(it.pointer);
			if(nowFunction.offMap.containsKey(rs1.name)) {
				int offset = -nowFunction.offMap.get(rs1.name);
				if(-2048 <= offset && offset <= 2047) {
					nowBlock.AddInst(new Lw(rd, s0, new Immediate(offset), null));
				}
				else {
					extraNum ++;
					VirtualRegister EX = new VirtualRegister("_EX_" + extraNum, 4);
					nowBlock.AddInst(new Li(EX, new Immediate(offset)));
					nowBlock.AddInst(new Arithmetic("add", EX, EX, s0, null));
					nowBlock.AddInst(new Sw(EX, rd, new Immediate(0)));
				}
			}
			else {
				nowBlock.AddInst(new Lw(rd, rs1, new Immediate(0), null));
			}
		}
	}

	@Override
	public void Visit(IR.inst.Ret it) {
		for(int i = 0; i < module.callee.size(); ++ i) {
			PhysicalRegister x = module.callee.get(i);
			VirtualRegister EX = calleeList.get(i);
			nowBlock.AddInst(new Mv(x, EX));
		}

		if(it.type instanceof VoidType) {
			nowBlock.AddInst(new Mv(a0, zero));
		}
		else {
			nowBlock.AddInst(new Mv(a0, getReg(it.value)));
		}
		nowBlock.AddInst(new J("." + nowFunction.name + "_RETURN"));
	}

	@Override
	public void Visit(Store it) {
		if(it.pointer instanceof GlobalVariable) {
			extraNum ++;
			VirtualRegister EX = new VirtualRegister("_EX_" + extraNum, 4);
			nowBlock.AddInst(new La(EX, ((GlobalVariable) it.pointer).name));
			nowBlock.AddInst(new Sw(EX, getReg(it.value), new Immediate(0)));
		}
		else {
			if(it.pointer instanceof Temporary) {
				nowBlock.AddInst(new Mv(getReg(it.pointer), getReg(it.value)));
			}
			else {
				ASMRegister rs1 = getReg(it.pointer), rs2 = getReg(it.value);
				if(nowFunction.offMap.containsKey(rs1.name)) {
					int offset = -nowFunction.offMap.get(rs1.name);
					if(-2048 <= offset && offset <= 2047) {
						nowBlock.AddInst(new Sw(s0, rs2, new Immediate(offset)));
					}
					else {
						extraNum ++;
						VirtualRegister EX = new VirtualRegister("_EX_" + extraNum, 4);
						nowBlock.AddInst(new Li(EX, new Immediate(offset)));
						nowBlock.AddInst(new Arithmetic("add", EX, EX, s0, null));
						nowBlock.AddInst(new Sw(EX, rs1, new Immediate(0)));
					}
				}
				else {
					nowBlock.AddInst(new Sw(rs1, rs2, new Immediate(0)));
				}
			}
		}
	}

	@Override
	public void Visit(IR.inst.Call it) {
		int spilloff = 0;
		for(int i = 0; i < Integer.min(it.paraList.size(), 8); ++ i) {
			ASMRegister tmpReg = getReg(it.paraList.get(i));
			nowBlock.AddInst(new Mv(module.phyRegList.get(10 + i), tmpReg));
		}
		for(int i = 8; i < it.paraList.size(); ++ i) {
			ASMRegister tmpReg = getReg(it.paraList.get(i));
			spilloff += 4;
			nowBlock.AddInst(new Sw(sp, tmpReg, new Immediate((i - 8) * 4)));
		}
		nowFunction.calloff = Math.max(nowFunction.calloff, spilloff);
		ASM.Inst.Inst res = new ASM.Inst.Call(it.funcId);
		res.def.addAll(module.caller);
		nowBlock.AddInst(res);

		if(!(it.type instanceof VoidType)) {
			ASMRegister returnReg = getReg(it.result);
			nowBlock.AddInst(new Mv(returnReg, a0));
		}
	}

	@Override
	public void Visit(Bitcast it) {
		regMap.put(nowFunction.name + "_" + it.result.name, getReg(it.value));
	}

	@Override
	public void Visit(Getelementptr it) {
		if(it.value instanceof GlobalVariable) {
			regMap.put(nowFunction + "_" + it.result.name, getReg(it.value));
		}
		else {
			ASMRegister rd = getReg(it.result);

			if(it.idxList.size() > 1) {
				int off = 4 * ((IntConst) it.idxList.get(1)).val;
				nowBlock.AddInst(new Arithmetic("add", rd, getReg(it.value), null, new Immediate(off)));
			}
			else {
				extraNum ++;
				VirtualRegister EX = new VirtualRegister("_EX_" + extraNum, 4);
				nowBlock.AddInst(new Li(EX, new Immediate(4)));
				nowBlock.AddInst(new Arithmetic("mul", EX, getReg(it.idxList.get(0)), EX, null));
				nowBlock.AddInst(new Arithmetic("add", rd, getReg(it.value), EX, null));
			}
		}
	}
}
