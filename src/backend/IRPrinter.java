package backend;

import IR.*;
import IR.inst.*;

import java.io.*;

public class IRPrinter implements IRVisitor {
	public PrintWriter file_print;
	private final String tab = "    ";

	public IRPrinter(String path) throws FileNotFoundException {
		file_print = new PrintWriter(new FileOutputStream(path));
		file_print.println("declare dso_local void @print(i8* %0)");
		file_print.println("declare dso_local void @println(i8* %0)");
		file_print.println("declare dso_local void @printInt(i32 %0)");
		file_print.println("declare dso_local void @printlnInt(i32 %0)");
		file_print.println("declare dso_local i32 @getInt()");
		file_print.println("declare dso_local i8* @toString(i32 %0)");
		file_print.println("declare dso_local i8* @getString()");
		file_print.println("declare dso_local i32 @_str_ord(i8* %0, i32 %1)");
		file_print.println("declare dso_local zeroext i1 @_str_eq(i8* %0, i8* %1)");
		file_print.println("declare dso_local zeroext i1 @_str_ne(i8* %0, i8* %1)");
		file_print.println("declare dso_local zeroext i1 @_str_lt(i8* %0, i8* %1)");
		file_print.println("declare dso_local zeroext i1 @_str_le(i8* %0, i8* %1)");
		file_print.println("declare dso_local zeroext i1 @_str_gt(i8* %0, i8* %1)");
		file_print.println("declare dso_local zeroext i1 @_str_ge(i8* %0, i8* %1)");
		file_print.println("declare dso_local i8* @_str_concatenate(i8* %0, i8* %1)");
		file_print.println("declare dso_local i8* @_f_malloc(i32 %0)");
		file_print.println("declare dso_local i32 @_str_length(i8* %0)");
		file_print.println("declare dso_local i8* @_str_substring(i8* %0, i32 %1, i32 %2)");
		file_print.println("declare dso_local i32 @_str_parseInt(i8* %0)");
		file_print.println("");
	}

	@Override
	public void Visit(Module_ it) {
		it.globalList.forEach(x -> x.Accept(this));
		file_print.println("");
		it.funcList.forEach(x -> x.Accept(this));
		file_print.close();
	}

	@Override
	public void Visit(Function it) {
		Visit(it.funcDefine);
		for(BasicBlock x : it.blockList) {
			for(Inst y : x.allocaList) {
				it.allocaBlock.AddInst(y);
			}
			x.allocaList.clear();
		}
		it.allocaBlock.AddInst(new Br(it.allocaBlock, it.blockList.get(0)));
		it.allocaBlock.Accept(this);
		it.blockList.forEach(x -> x.Accept(this));
		file_print.println("}");
		file_print.println("");
	}

	@Override
	public void Visit(BasicBlock it) {
		file_print.println(it.name + ":");
		it.allocaList.forEach(x -> x.Accept(this));
		it.instList.forEach(x -> x.Accept(this));
		it.terminator.Accept(this);
		file_print.println("");
	}

	@Override
	public void Visit(Alloca it) {
		file_print.println(tab + it.toString());
	}

	@Override
	public void Visit(Binary it) {
		file_print.println(tab + it.toString());
	}

	@Override
	public void Visit(Br it) {
		file_print.println(tab + it.toString());
	}

	@Override
	public void Visit(Define it) {
		file_print.println(tab + it.toString());
	}
	
	@Override
	public void Visit(Global it) {
		file_print.println(tab + it.toString());
	}
	
	@Override
	public void Visit(Icmp it) {
		file_print.println(tab + it.toString());
	}
	
	@Override
	public void Visit(Load it) {
		file_print.println(tab + it.toString());
	}
	
	@Override
	public void Visit(Ret it) {
		file_print.println(tab + it.toString());
	}
	
	@Override
	public void Visit(Store it) {
		file_print.println(tab + it.toString());
	}
	
	@Override
	public void Visit(Call it) {
		file_print.println(tab + it.toString());
	}
	
	@Override
	public void Visit(Bitcast it) {
		file_print.println(tab + it.toString());
	}
	
	@Override
	public void Visit(Getelementptr it) {
		file_print.println(tab + it.toString());
	}
}
