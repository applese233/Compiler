package frontend;

import AST.*;
import AST.DefNode.*;
import AST.StmtNode.*;
import AST.ExprNode.*;
import org.antlr.v4.runtime.Parser;
import Util.position;
import Util.error.syntaxError;
import org.antlr.v4.runtime.ParserRuleContext;
import Parser.MxLiteBaseVisitor;
import Parser.MxLiteParser;

import java.util.ArrayList;

public class ASTBuilder {
	@Override
	public ASTNode VisitConstant(MxLiteParser.ConstantContext ctx) {
		
	}
}
