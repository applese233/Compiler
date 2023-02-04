import AST.*;
import frontend.*;
import backend.*;
import Util.*;
import IR.*;
import Optimize.RegisterAllocator;
import ASM.*;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import Parser.MxLiteLexer;
import Parser.MxLiteParser;

import java.io.FileInputStream;
import java.io.InputStream;

public class Compiler {
	public static void main(String[] args) throws Exception {
		// String name = "test.mx";
		// FileInputStream input = new FileInputStream(name);
		InputStream input = System.in;
		System.out.println("Here.");
        boolean tag = true;
        if (args.length > 0) {
            for (String arg : args) {
                switch (arg) {
                    case "-semantic": {
						tag = false;
						break;
					}
                    case "-codegen": {
						tag = true;
						break;
					}
                }
            }
        }
		try {
			BuiltinFunctionASMPrinter builtin_printer = new BuiltinFunctionASMPrinter("builtin.s");
			System.out.println("Here2.");
			ProgNode ASTRoot;
			Scope globalScope = new Scope(null);
			System.out.println("Here3.");
			MxLiteLexer lexer = new MxLiteLexer(CharStreams.fromStream(input));
			System.out.println("Here4.");
			lexer.removeErrorListeners();
			System.out.println("Here5.");
			lexer.addErrorListener(new MxLiteErrorListener());
			System.out.println("Lexer End.");

			MxLiteParser parser = new MxLiteParser(new CommonTokenStream(lexer));
			parser.removeErrorListeners();
			parser.addErrorListener(new MxLiteErrorListener());
			System.out.println("Parser End.");

			ParseTree parseTreeRoot = parser.program();
			ASTBuilder astBuilder = new ASTBuilder();
			ASTRoot = (ProgNode)astBuilder.visit(parseTreeRoot);
			System.out.println("Builder End.");

			new SymbolCollector(globalScope).Visit(ASTRoot);
			System.out.println("Symbol End.");
			new TypeCollector(globalScope).Visit(ASTRoot);
			System.out.println("Type End.");
			globalScope.varMap.clear();
			System.out.println(globalScope.typeMap.get("int").type);
			new SemanticChecker(globalScope).Visit(ASTRoot);
			System.out.println("Checker End.");

			if(!tag)
				return;

			Module_ module = new Module_();
			new IRBuilder(globalScope, module).Visit(ASTRoot);
			IRPrinter irPrinter = new IRPrinter("myllvm.ll");
			irPrinter.Visit(module);
			System.out.println("IR.");

			ASMModule ASM = new ASMModule();
			new ASMBuilder(ASM).Visit(module);

			System.out.println("debug.");
			ASMPrinter debug = new ASMPrinter("debug.s");
			debug.Visit(ASM);

			// new ASMRegisterAllocator(ASM).RegisterAllocate();
			new RegisterAllocator(ASM).VisitModule();

			ASMPrinter result = new ASMPrinter("output.s");
			result.Visit(ASM);
			System.out.println("gen.");
		}
		catch (Error error) {
			System.err.println(error.toString());
			throw new RuntimeException();
		}
	}
}
