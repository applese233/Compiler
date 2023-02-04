package backend;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import ASM.Operand.*;
import ASM.*;
import ASM.Inst.*;

public class ASMPrinter implements ASMVisitor {
	public PrintWriter file_print;
	public PhysicalRegister ra, sp, s0, s2;
	private final String tab = "	";

	public ASMPrinter(String path) throws FileNotFoundException {
		file_print = new PrintWriter(new FileOutputStream(path));
	}

	@Override
	public void Visit(Mv it) {
		file_print.println(tab + it.toString());
	}

	@Override
	public void Visit(Arithmetic it) {
		file_print.println(tab + it.toString());
	}

	@Override
	public void Visit(Li it) {
		file_print.println(tab + it.toString());
	}

	@Override
	public void Visit(J it) {
		file_print.println(tab + it.toString());
	}

	@Override
	public void Visit(Branch it) {
		file_print.println(tab + it.toString());
	}

	@Override
	public void Visit(Call it) {
		file_print.println(tab + it.toString());
	}

	@Override
	public void Visit(Compare it) {
		file_print.println(tab + it.toString());
	}

	@Override
	public void Visit(La it) {
		file_print.println(tab + it.toString());
	}

	@Override
	public void Visit(Ret it) {
		file_print.println(tab + it.toString());
	}

	@Override
	public void Visit(Lw it) {
		file_print.println(tab + it.toString());
	}

	@Override
	public void Visit(Sw it) {
		file_print.println(tab + it.toString());
	}

	@Override
	public void Visit(ASMModule it) {
		ra = it.phyRegList.get(1);
		sp = it.phyRegList.get(2);
		s0 = it.phyRegList.get(8);
		s2 = it.phyRegList.get(18);

		file_print.println(tab + ".text");
		file_print.println("");

		it.funcList.forEach(x -> x.Accept(this));

		file_print.println("");
		it.globalList.forEach(x -> {
			file_print.println(x.toString());
			file_print.println("");
		});

		file_print.close();
	}

	@Override
	public void Visit(ASMFunction it) {
		file_print.println(tab + ".globl" + tab + it.name);
		file_print.println(tab + ".p2align" + tab + 2);
		file_print.println(tab + ".type" + tab + it.name + ",@function");
		file_print.println(it.name + ":");

		int off = it.offset + it.calloff;

		ASMBlock firstBlock = it.blockList.get(0);
		ASMBlock lastBlock = it.blockList.get(it.blockList.size() - 1);
		if(-2024 <= off && off <= 2023) {
			firstBlock.instList.addFirst(new Arithmetic("add", s0, sp, null, new Immediate(off + 12)));
			firstBlock.instList.addFirst(new Sw(sp, s0, new Immediate(off + 4)));
			firstBlock.instList.addFirst(new Sw(sp, ra, new Immediate(off + 8)));
			firstBlock.instList.addFirst(new Arithmetic("add", sp, sp, null, new Immediate(-off - 12)));

			lastBlock.AddInst(new Lw(s0, sp, new Immediate(off + 4), null));
			lastBlock.AddInst(new Lw(ra, sp, new Immediate(off + 8), null));
			lastBlock.AddInst(new Arithmetic("add", sp, sp, null, new Immediate(off + 12)));
		}
		else {
			firstBlock.instList.addFirst(new Arithmetic("add", s0, sp, s2, null));
			firstBlock.instList.addFirst(new Li(s2, new Immediate(off + 12)));
			firstBlock.instList.addFirst(new Sw(s2, s0, new Immediate(0)));
			firstBlock.instList.addFirst(new Arithmetic("add", s2, s2, sp, null));
			firstBlock.instList.addFirst(new Li(s2, new Immediate(off + 4)));
			firstBlock.instList.addFirst(new Sw(s2, ra, new Immediate(0)));
			firstBlock.instList.addFirst(new Arithmetic("add", s2, s2, sp, null));
			firstBlock.instList.addFirst(new Li(s2, new Immediate(off + 8)));
			firstBlock.instList.addFirst(new Arithmetic("add", sp, sp, s2, null));
			firstBlock.instList.addFirst(new Li(s2, new Immediate(-off - 12)));

			lastBlock.AddInst(new Li(s2, new Immediate(off + 4)));
			lastBlock.AddInst(new Arithmetic("add", s2, s2, sp, null));
			lastBlock.AddInst(new Lw(s0, s2, new Immediate(0), null));
			lastBlock.AddInst(new Li(s2, new Immediate(off + 8)));
			lastBlock.AddInst(new Arithmetic("add", s2, s2, sp, null));
			lastBlock.AddInst(new Lw(ra, s2, new Immediate(0), null));
			lastBlock.AddInst(new Li(s2, new Immediate(off + 12)));
			lastBlock.AddInst(new Arithmetic("add", sp, sp, s2, null));
		}

		lastBlock.AddInst(new Ret());

		it.blockList.forEach(x -> x.Accept(this));

		file_print.println("");
	}

	@Override
	public void Visit(ASMBlock it) {
		file_print.println(it.name + ":");
		it.instList.forEach(x -> x.Accept(this));
	}
}
