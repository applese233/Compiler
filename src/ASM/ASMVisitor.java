package ASM;

import ASM.Inst.*;

public interface ASMVisitor {
	void Visit(Mv it);

	void Visit(Arithmetic it);

	void Visit(Li it);

	void Visit(J it);

	void Visit(Branch it);

	void Visit(Call it);

	void Visit(Compare it);

	void Visit(La it);

	void Visit(Ret it);

	void Visit(ASMModule it);

	void Visit(ASMFunction it);

	void Visit(ASMBlock it);

	void Visit(Sw it);

	void Visit(Lw it);
}
