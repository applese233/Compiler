package IR;

import IR.inst.*;

public interface IRVisitor {
	void Visit(Module it);

    void Visit(Function it);

    void Visit(BasicBlock it);

    void Visit(Alloca it);

    void Visit(Binary it);

    void Visit(Br it);

    void Visit(Define it);

    void Visit(Global it);

    void Visit(Icmp it);

    void Visit(Load it);

    void Visit(Ret it);

    void Visit(Store it);

    void Visit(Call it);

    void Visit(Bitcast it);

    void Visit(Getelementptr it);
}
