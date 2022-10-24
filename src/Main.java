import AST.*;
import frontend.*;
import Util.MxLiteErrorListener;
import Util.error.semanticError;
import Util.position;
import Util.Scope;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import Parser.MxLiteLexer;
import Parser.MxLiteParser;

import java.io.FileInputStream;
import java.io.InputStream;

public class Main {
	public static void main(String[] args) throws Exception {
		String name = "test.mx";
		InputStream input = System.in;
		try {
			ProgNode ASTRoot;
			Scope globalScope = new Scope(null);
			MxLiteLexer lexer = new MxLiteLexer(CharStreams.fromStream(input));
			lexer.removeErrorListeners();
			lexer.addErrorListener(new MxLiteErrorListener());

			MxLiteParser parser = new MxLiteParser(new CommonTokenStream(lexer));
			parser.removeErrorListeners();
			parser.addErrorListener(new MxLiteErrorListener());

			ParseTree parseTreeRoot = parser.program();
			ASTBuilder astBuilder = new ASTBuilder();
			ASTRoot = (ProgNode)astBuilder.visit(parseTreeRoot);

			new SymbolCollector(globalScope).Visit(ASTRoot);
			globalScope.varMap.clear();
			new SemanticChecker(globalScope).Visit(ASTRoot);

		}
		catch (Error error) {
			System.err.println(error.toString());
			throw new RuntimeException();
		}
	}
}
