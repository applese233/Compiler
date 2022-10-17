package AST;

import AST.ProgNode;
import AST.TypeNode;
import AST.DefNode.*;

public interface ASTVisitor {
	void Visit(ProgNode it);

	void Visit(TypeNode it);

	void Visit(ClassDefNode it);

	void Visit(FuncDefNode it);
}