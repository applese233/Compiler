// Generated from MxLite.g4 by ANTLR 4.7.2
package Parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MxLiteParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, Int=2, Bool=3, String=4, Null=5, Void=6, If=7, Else=8, For=9, 
		While=10, Break=11, Continue=12, Return=13, New=14, Class=15, This=16, 
		Dot=17, LeftParen=18, RightParen=19, LeftBracket=20, RightBracket=21, 
		LeftBrace=22, RightBrace=23, Less=24, LessEqual=25, Greater=26, GreaterEqual=27, 
		LeftShift=28, RightShift=29, Plus=30, SelfPlus=31, Minus=32, SelfMinus=33, 
		Mul=34, Div=35, Mod=36, And=37, Or=38, AndAnd=39, OrOr=40, Caret=41, Not=42, 
		Tilde=43, Question=44, Colon=45, Semi=46, Comma=47, Assign=48, Equal=49, 
		NotEqual=50, LambdaStart=51, WhiteSpace=52, NewLine=53, LineComment=54, 
		BlockComment=55, BoolConst=56, IntConst=57, StringConst=58, Identifier=59;
	public static final int
		RULE_constant = 0, RULE_basType = 1, RULE_varType = 2, RULE_returnType = 3, 
		RULE_program = 4, RULE_programBlock = 5, RULE_varDef = 6, RULE_varDeclaration = 7, 
		RULE_funcDef = 8, RULE_parameterList = 9, RULE_parameter = 10, RULE_suite = 11, 
		RULE_classDef = 12, RULE_classStruct = 13, RULE_statement = 14, RULE_expression = 15, 
		RULE_expressionList = 16, RULE_creator = 17, RULE_lambda = 18;
	private static String[] makeRuleNames() {
		return new String[] {
			"constant", "basType", "varType", "returnType", "program", "programBlock", 
			"varDef", "varDeclaration", "funcDef", "parameterList", "parameter", 
			"suite", "classDef", "classStruct", "statement", "expression", "expressionList", 
			"creator", "lambda"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'->'", "'int'", "'bool'", "'string'", "'null'", "'void'", "'if'", 
			"'else'", "'for'", "'while'", "'break'", "'continue'", "'return'", "'new'", 
			"'class'", "'this'", "'.'", "'('", "')'", "'['", "']'", "'{'", "'}'", 
			"'<'", "'<='", "'>'", "'>='", "'<<'", "'>>'", "'+'", "'++'", "'-'", "'--'", 
			"'*'", "'/'", "'%'", "'&'", "'|'", "'&&'", "'||'", "'^'", "'!'", "'~'", 
			"'?'", "':'", "';'", "','", "'='", "'=='", "'!='", "'[&]'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, "Int", "Bool", "String", "Null", "Void", "If", "Else", "For", 
			"While", "Break", "Continue", "Return", "New", "Class", "This", "Dot", 
			"LeftParen", "RightParen", "LeftBracket", "RightBracket", "LeftBrace", 
			"RightBrace", "Less", "LessEqual", "Greater", "GreaterEqual", "LeftShift", 
			"RightShift", "Plus", "SelfPlus", "Minus", "SelfMinus", "Mul", "Div", 
			"Mod", "And", "Or", "AndAnd", "OrOr", "Caret", "Not", "Tilde", "Question", 
			"Colon", "Semi", "Comma", "Assign", "Equal", "NotEqual", "LambdaStart", 
			"WhiteSpace", "NewLine", "LineComment", "BlockComment", "BoolConst", 
			"IntConst", "StringConst", "Identifier"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "MxLite.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MxLiteParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ConstantContext extends ParserRuleContext {
		public TerminalNode BoolConst() { return getToken(MxLiteParser.BoolConst, 0); }
		public TerminalNode IntConst() { return getToken(MxLiteParser.IntConst, 0); }
		public TerminalNode StringConst() { return getToken(MxLiteParser.StringConst, 0); }
		public TerminalNode Null() { return getToken(MxLiteParser.Null, 0); }
		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).enterConstant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).exitConstant(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxLiteVisitor ) return ((MxLiteVisitor<? extends T>)visitor).visitConstant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_constant);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Null) | (1L << BoolConst) | (1L << IntConst) | (1L << StringConst))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BasTypeContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxLiteParser.Identifier, 0); }
		public TerminalNode Int() { return getToken(MxLiteParser.Int, 0); }
		public TerminalNode Bool() { return getToken(MxLiteParser.Bool, 0); }
		public TerminalNode String() { return getToken(MxLiteParser.String, 0); }
		public BasTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_basType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).enterBasType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).exitBasType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxLiteVisitor ) return ((MxLiteVisitor<? extends T>)visitor).visitBasType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BasTypeContext basType() throws RecognitionException {
		BasTypeContext _localctx = new BasTypeContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_basType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Bool) | (1L << String) | (1L << Identifier))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarTypeContext extends ParserRuleContext {
		public BasTypeContext basType() {
			return getRuleContext(BasTypeContext.class,0);
		}
		public List<TerminalNode> LeftBracket() { return getTokens(MxLiteParser.LeftBracket); }
		public TerminalNode LeftBracket(int i) {
			return getToken(MxLiteParser.LeftBracket, i);
		}
		public List<TerminalNode> RightBracket() { return getTokens(MxLiteParser.RightBracket); }
		public TerminalNode RightBracket(int i) {
			return getToken(MxLiteParser.RightBracket, i);
		}
		public VarTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).enterVarType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).exitVarType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxLiteVisitor ) return ((MxLiteVisitor<? extends T>)visitor).visitVarType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarTypeContext varType() throws RecognitionException {
		VarTypeContext _localctx = new VarTypeContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_varType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			basType();
			setState(47);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LeftBracket) {
				{
				{
				setState(43);
				match(LeftBracket);
				setState(44);
				match(RightBracket);
				}
				}
				setState(49);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnTypeContext extends ParserRuleContext {
		public VarTypeContext varType() {
			return getRuleContext(VarTypeContext.class,0);
		}
		public TerminalNode Void() { return getToken(MxLiteParser.Void, 0); }
		public ReturnTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).enterReturnType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).exitReturnType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxLiteVisitor ) return ((MxLiteVisitor<? extends T>)visitor).visitReturnType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnTypeContext returnType() throws RecognitionException {
		ReturnTypeContext _localctx = new ReturnTypeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_returnType);
		try {
			setState(52);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Int:
			case Bool:
			case String:
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(50);
				varType();
				}
				break;
			case Void:
				enterOuterAlt(_localctx, 2);
				{
				setState(51);
				match(Void);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProgramContext extends ParserRuleContext {
		public List<ProgramBlockContext> programBlock() {
			return getRuleContexts(ProgramBlockContext.class);
		}
		public ProgramBlockContext programBlock(int i) {
			return getRuleContext(ProgramBlockContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxLiteVisitor ) return ((MxLiteVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Bool) | (1L << String) | (1L << Void) | (1L << Class) | (1L << Identifier))) != 0)) {
				{
				{
				setState(54);
				programBlock();
				}
				}
				setState(59);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProgramBlockContext extends ParserRuleContext {
		public VarDefContext varDef() {
			return getRuleContext(VarDefContext.class,0);
		}
		public FuncDefContext funcDef() {
			return getRuleContext(FuncDefContext.class,0);
		}
		public ClassDefContext classDef() {
			return getRuleContext(ClassDefContext.class,0);
		}
		public ProgramBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_programBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).enterProgramBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).exitProgramBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxLiteVisitor ) return ((MxLiteVisitor<? extends T>)visitor).visitProgramBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramBlockContext programBlock() throws RecognitionException {
		ProgramBlockContext _localctx = new ProgramBlockContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_programBlock);
		try {
			setState(63);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(60);
				varDef();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(61);
				funcDef();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(62);
				classDef();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarDefContext extends ParserRuleContext {
		public VarTypeContext varType() {
			return getRuleContext(VarTypeContext.class,0);
		}
		public List<VarDeclarationContext> varDeclaration() {
			return getRuleContexts(VarDeclarationContext.class);
		}
		public VarDeclarationContext varDeclaration(int i) {
			return getRuleContext(VarDeclarationContext.class,i);
		}
		public TerminalNode Semi() { return getToken(MxLiteParser.Semi, 0); }
		public List<TerminalNode> Comma() { return getTokens(MxLiteParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(MxLiteParser.Comma, i);
		}
		public VarDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).enterVarDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).exitVarDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxLiteVisitor ) return ((MxLiteVisitor<? extends T>)visitor).visitVarDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDefContext varDef() throws RecognitionException {
		VarDefContext _localctx = new VarDefContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_varDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			varType();
			setState(66);
			varDeclaration();
			setState(71);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(67);
				match(Comma);
				setState(68);
				varDeclaration();
				}
				}
				setState(73);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(74);
			match(Semi);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarDeclarationContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxLiteParser.Identifier, 0); }
		public TerminalNode Assign() { return getToken(MxLiteParser.Assign, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public VarDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).enterVarDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).exitVarDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxLiteVisitor ) return ((MxLiteVisitor<? extends T>)visitor).visitVarDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDeclarationContext varDeclaration() throws RecognitionException {
		VarDeclarationContext _localctx = new VarDeclarationContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_varDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			match(Identifier);
			setState(79);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Assign) {
				{
				setState(77);
				match(Assign);
				setState(78);
				expression(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncDefContext extends ParserRuleContext {
		public ReturnTypeContext returnType() {
			return getRuleContext(ReturnTypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(MxLiteParser.Identifier, 0); }
		public TerminalNode LeftParen() { return getToken(MxLiteParser.LeftParen, 0); }
		public TerminalNode RightParen() { return getToken(MxLiteParser.RightParen, 0); }
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public FuncDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).enterFuncDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).exitFuncDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxLiteVisitor ) return ((MxLiteVisitor<? extends T>)visitor).visitFuncDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncDefContext funcDef() throws RecognitionException {
		FuncDefContext _localctx = new FuncDefContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_funcDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			returnType();
			setState(82);
			match(Identifier);
			setState(83);
			match(LeftParen);
			setState(85);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Bool) | (1L << String) | (1L << Identifier))) != 0)) {
				{
				setState(84);
				parameterList();
				}
			}

			setState(87);
			match(RightParen);
			setState(88);
			suite();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterListContext extends ParserRuleContext {
		public List<ParameterContext> parameter() {
			return getRuleContexts(ParameterContext.class);
		}
		public ParameterContext parameter(int i) {
			return getRuleContext(ParameterContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(MxLiteParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(MxLiteParser.Comma, i);
		}
		public ParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).enterParameterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).exitParameterList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxLiteVisitor ) return ((MxLiteVisitor<? extends T>)visitor).visitParameterList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterListContext parameterList() throws RecognitionException {
		ParameterListContext _localctx = new ParameterListContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_parameterList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			parameter();
			setState(95);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(91);
				match(Comma);
				setState(92);
				parameter();
				}
				}
				setState(97);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterContext extends ParserRuleContext {
		public VarTypeContext varType() {
			return getRuleContext(VarTypeContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(MxLiteParser.Identifier, 0); }
		public ParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).enterParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).exitParameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxLiteVisitor ) return ((MxLiteVisitor<? extends T>)visitor).visitParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterContext parameter() throws RecognitionException {
		ParameterContext _localctx = new ParameterContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			varType();
			setState(99);
			match(Identifier);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SuiteContext extends ParserRuleContext {
		public TerminalNode LeftBrace() { return getToken(MxLiteParser.LeftBrace, 0); }
		public TerminalNode RightBrace() { return getToken(MxLiteParser.RightBrace, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public SuiteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_suite; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).enterSuite(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).exitSuite(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxLiteVisitor ) return ((MxLiteVisitor<? extends T>)visitor).visitSuite(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SuiteContext suite() throws RecognitionException {
		SuiteContext _localctx = new SuiteContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_suite);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101);
			match(LeftBrace);
			setState(105);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Bool) | (1L << String) | (1L << Null) | (1L << If) | (1L << For) | (1L << While) | (1L << Break) | (1L << Continue) | (1L << Return) | (1L << New) | (1L << This) | (1L << LeftParen) | (1L << LeftBrace) | (1L << Plus) | (1L << SelfPlus) | (1L << Minus) | (1L << SelfMinus) | (1L << Not) | (1L << Tilde) | (1L << Semi) | (1L << LambdaStart) | (1L << BoolConst) | (1L << IntConst) | (1L << StringConst) | (1L << Identifier))) != 0)) {
				{
				{
				setState(102);
				statement();
				}
				}
				setState(107);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(108);
			match(RightBrace);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassDefContext extends ParserRuleContext {
		public TerminalNode Class() { return getToken(MxLiteParser.Class, 0); }
		public TerminalNode Identifier() { return getToken(MxLiteParser.Identifier, 0); }
		public TerminalNode LeftBrace() { return getToken(MxLiteParser.LeftBrace, 0); }
		public TerminalNode RightBrace() { return getToken(MxLiteParser.RightBrace, 0); }
		public TerminalNode Semi() { return getToken(MxLiteParser.Semi, 0); }
		public List<VarDefContext> varDef() {
			return getRuleContexts(VarDefContext.class);
		}
		public VarDefContext varDef(int i) {
			return getRuleContext(VarDefContext.class,i);
		}
		public List<FuncDefContext> funcDef() {
			return getRuleContexts(FuncDefContext.class);
		}
		public FuncDefContext funcDef(int i) {
			return getRuleContext(FuncDefContext.class,i);
		}
		public List<ClassStructContext> classStruct() {
			return getRuleContexts(ClassStructContext.class);
		}
		public ClassStructContext classStruct(int i) {
			return getRuleContext(ClassStructContext.class,i);
		}
		public ClassDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).enterClassDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).exitClassDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxLiteVisitor ) return ((MxLiteVisitor<? extends T>)visitor).visitClassDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassDefContext classDef() throws RecognitionException {
		ClassDefContext _localctx = new ClassDefContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_classDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			match(Class);
			setState(111);
			match(Identifier);
			setState(112);
			match(LeftBrace);
			setState(118);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Bool) | (1L << String) | (1L << Void) | (1L << Identifier))) != 0)) {
				{
				setState(116);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
				case 1:
					{
					setState(113);
					varDef();
					}
					break;
				case 2:
					{
					setState(114);
					funcDef();
					}
					break;
				case 3:
					{
					setState(115);
					classStruct();
					}
					break;
				}
				}
				setState(120);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(121);
			match(RightBrace);
			setState(122);
			match(Semi);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassStructContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(MxLiteParser.Identifier, 0); }
		public TerminalNode LeftParen() { return getToken(MxLiteParser.LeftParen, 0); }
		public TerminalNode RightParen() { return getToken(MxLiteParser.RightParen, 0); }
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public ClassStructContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classStruct; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).enterClassStruct(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).exitClassStruct(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxLiteVisitor ) return ((MxLiteVisitor<? extends T>)visitor).visitClassStruct(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassStructContext classStruct() throws RecognitionException {
		ClassStructContext _localctx = new ClassStructContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_classStruct);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			match(Identifier);
			setState(125);
			match(LeftParen);
			setState(126);
			match(RightParen);
			setState(127);
			suite();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	 
		public StatementContext() { }
		public void copyFrom(StatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class VarDefStmtContext extends StatementContext {
		public VarDefContext varDef() {
			return getRuleContext(VarDefContext.class,0);
		}
		public VarDefStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).enterVarDefStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).exitVarDefStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxLiteVisitor ) return ((MxLiteVisitor<? extends T>)visitor).visitVarDefStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ForStmtContext extends StatementContext {
		public ExpressionContext expr1;
		public ExpressionContext expr2;
		public ExpressionContext expr3;
		public TerminalNode For() { return getToken(MxLiteParser.For, 0); }
		public TerminalNode LeftParen() { return getToken(MxLiteParser.LeftParen, 0); }
		public List<TerminalNode> Semi() { return getTokens(MxLiteParser.Semi); }
		public TerminalNode Semi(int i) {
			return getToken(MxLiteParser.Semi, i);
		}
		public TerminalNode RightParen() { return getToken(MxLiteParser.RightParen, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ForStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).enterForStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).exitForStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxLiteVisitor ) return ((MxLiteVisitor<? extends T>)visitor).visitForStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class WhileStmtContext extends StatementContext {
		public TerminalNode While() { return getToken(MxLiteParser.While, 0); }
		public TerminalNode LeftParen() { return getToken(MxLiteParser.LeftParen, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(MxLiteParser.RightParen, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public WhileStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).enterWhileStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).exitWhileStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxLiteVisitor ) return ((MxLiteVisitor<? extends T>)visitor).visitWhileStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfStmtContext extends StatementContext {
		public StatementContext trueStmt;
		public StatementContext falseStmt;
		public TerminalNode If() { return getToken(MxLiteParser.If, 0); }
		public TerminalNode LeftParen() { return getToken(MxLiteParser.LeftParen, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(MxLiteParser.RightParen, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode Else() { return getToken(MxLiteParser.Else, 0); }
		public IfStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).enterIfStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).exitIfStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxLiteVisitor ) return ((MxLiteVisitor<? extends T>)visitor).visitIfStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BreakStmtContext extends StatementContext {
		public TerminalNode Break() { return getToken(MxLiteParser.Break, 0); }
		public TerminalNode Semi() { return getToken(MxLiteParser.Semi, 0); }
		public BreakStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).enterBreakStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).exitBreakStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxLiteVisitor ) return ((MxLiteVisitor<? extends T>)visitor).visitBreakStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EmptyStmtContext extends StatementContext {
		public TerminalNode Semi() { return getToken(MxLiteParser.Semi, 0); }
		public EmptyStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).enterEmptyStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).exitEmptyStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxLiteVisitor ) return ((MxLiteVisitor<? extends T>)visitor).visitEmptyStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ReturnStmtContext extends StatementContext {
		public TerminalNode Return() { return getToken(MxLiteParser.Return, 0); }
		public TerminalNode Semi() { return getToken(MxLiteParser.Semi, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).enterReturnStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).exitReturnStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxLiteVisitor ) return ((MxLiteVisitor<? extends T>)visitor).visitReturnStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ContinueStmtContext extends StatementContext {
		public TerminalNode Continue() { return getToken(MxLiteParser.Continue, 0); }
		public TerminalNode Semi() { return getToken(MxLiteParser.Semi, 0); }
		public ContinueStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).enterContinueStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).exitContinueStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxLiteVisitor ) return ((MxLiteVisitor<? extends T>)visitor).visitContinueStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SuiteStmtContext extends StatementContext {
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public SuiteStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).enterSuiteStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).exitSuiteStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxLiteVisitor ) return ((MxLiteVisitor<? extends T>)visitor).visitSuiteStmt(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PureExprStmtContext extends StatementContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode Semi() { return getToken(MxLiteParser.Semi, 0); }
		public PureExprStmtContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).enterPureExprStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).exitPureExprStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxLiteVisitor ) return ((MxLiteVisitor<? extends T>)visitor).visitPureExprStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_statement);
		int _la;
		try {
			setState(174);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				_localctx = new SuiteStmtContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(129);
				suite();
				}
				break;
			case 2:
				_localctx = new VarDefStmtContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(130);
				varDef();
				}
				break;
			case 3:
				_localctx = new IfStmtContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(131);
				match(If);
				setState(132);
				match(LeftParen);
				setState(133);
				expression(0);
				setState(134);
				match(RightParen);
				setState(135);
				((IfStmtContext)_localctx).trueStmt = statement();
				setState(138);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
				case 1:
					{
					setState(136);
					match(Else);
					setState(137);
					((IfStmtContext)_localctx).falseStmt = statement();
					}
					break;
				}
				}
				break;
			case 4:
				_localctx = new WhileStmtContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(140);
				match(While);
				setState(141);
				match(LeftParen);
				setState(142);
				expression(0);
				setState(143);
				match(RightParen);
				setState(144);
				statement();
				}
				break;
			case 5:
				_localctx = new ForStmtContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(146);
				match(For);
				setState(147);
				match(LeftParen);
				setState(149);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Null) | (1L << New) | (1L << This) | (1L << LeftParen) | (1L << Plus) | (1L << SelfPlus) | (1L << Minus) | (1L << SelfMinus) | (1L << Not) | (1L << Tilde) | (1L << LambdaStart) | (1L << BoolConst) | (1L << IntConst) | (1L << StringConst) | (1L << Identifier))) != 0)) {
					{
					setState(148);
					((ForStmtContext)_localctx).expr1 = expression(0);
					}
				}

				setState(151);
				match(Semi);
				setState(153);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Null) | (1L << New) | (1L << This) | (1L << LeftParen) | (1L << Plus) | (1L << SelfPlus) | (1L << Minus) | (1L << SelfMinus) | (1L << Not) | (1L << Tilde) | (1L << LambdaStart) | (1L << BoolConst) | (1L << IntConst) | (1L << StringConst) | (1L << Identifier))) != 0)) {
					{
					setState(152);
					((ForStmtContext)_localctx).expr2 = expression(0);
					}
				}

				setState(155);
				match(Semi);
				setState(157);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Null) | (1L << New) | (1L << This) | (1L << LeftParen) | (1L << Plus) | (1L << SelfPlus) | (1L << Minus) | (1L << SelfMinus) | (1L << Not) | (1L << Tilde) | (1L << LambdaStart) | (1L << BoolConst) | (1L << IntConst) | (1L << StringConst) | (1L << Identifier))) != 0)) {
					{
					setState(156);
					((ForStmtContext)_localctx).expr3 = expression(0);
					}
				}

				setState(159);
				match(RightParen);
				setState(160);
				statement();
				}
				break;
			case 6:
				_localctx = new ReturnStmtContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(161);
				match(Return);
				setState(163);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Null) | (1L << New) | (1L << This) | (1L << LeftParen) | (1L << Plus) | (1L << SelfPlus) | (1L << Minus) | (1L << SelfMinus) | (1L << Not) | (1L << Tilde) | (1L << LambdaStart) | (1L << BoolConst) | (1L << IntConst) | (1L << StringConst) | (1L << Identifier))) != 0)) {
					{
					setState(162);
					expression(0);
					}
				}

				setState(165);
				match(Semi);
				}
				break;
			case 7:
				_localctx = new BreakStmtContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(166);
				match(Break);
				setState(167);
				match(Semi);
				}
				break;
			case 8:
				_localctx = new ContinueStmtContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(168);
				match(Continue);
				setState(169);
				match(Semi);
				}
				break;
			case 9:
				_localctx = new PureExprStmtContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(170);
				expression(0);
				setState(171);
				match(Semi);
				}
				break;
			case 10:
				_localctx = new EmptyStmtContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(173);
				match(Semi);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NewExprContext extends ExpressionContext {
		public TerminalNode New() { return getToken(MxLiteParser.New, 0); }
		public CreatorContext creator() {
			return getRuleContext(CreatorContext.class,0);
		}
		public NewExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).enterNewExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).exitNewExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxLiteVisitor ) return ((MxLiteVisitor<? extends T>)visitor).visitNewExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ThisExprContext extends ExpressionContext {
		public TerminalNode This() { return getToken(MxLiteParser.This, 0); }
		public ThisExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).enterThisExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).exitThisExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxLiteVisitor ) return ((MxLiteVisitor<? extends T>)visitor).visitThisExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FuncExprContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode LeftParen() { return getToken(MxLiteParser.LeftParen, 0); }
		public TerminalNode RightParen() { return getToken(MxLiteParser.RightParen, 0); }
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public FuncExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).enterFuncExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).exitFuncExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxLiteVisitor ) return ((MxLiteVisitor<? extends T>)visitor).visitFuncExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ClassExprContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode Dot() { return getToken(MxLiteParser.Dot, 0); }
		public TerminalNode Identifier() { return getToken(MxLiteParser.Identifier, 0); }
		public ClassExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).enterClassExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).exitClassExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxLiteVisitor ) return ((MxLiteVisitor<? extends T>)visitor).visitClassExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BinaryExprContext extends ExpressionContext {
		public ExpressionContext src1;
		public Token op;
		public ExpressionContext src2;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode Mul() { return getToken(MxLiteParser.Mul, 0); }
		public TerminalNode Div() { return getToken(MxLiteParser.Div, 0); }
		public TerminalNode Mod() { return getToken(MxLiteParser.Mod, 0); }
		public TerminalNode Plus() { return getToken(MxLiteParser.Plus, 0); }
		public TerminalNode Minus() { return getToken(MxLiteParser.Minus, 0); }
		public TerminalNode LeftShift() { return getToken(MxLiteParser.LeftShift, 0); }
		public TerminalNode RightShift() { return getToken(MxLiteParser.RightShift, 0); }
		public TerminalNode Less() { return getToken(MxLiteParser.Less, 0); }
		public TerminalNode LessEqual() { return getToken(MxLiteParser.LessEqual, 0); }
		public TerminalNode Greater() { return getToken(MxLiteParser.Greater, 0); }
		public TerminalNode GreaterEqual() { return getToken(MxLiteParser.GreaterEqual, 0); }
		public TerminalNode NotEqual() { return getToken(MxLiteParser.NotEqual, 0); }
		public TerminalNode Equal() { return getToken(MxLiteParser.Equal, 0); }
		public TerminalNode And() { return getToken(MxLiteParser.And, 0); }
		public TerminalNode Caret() { return getToken(MxLiteParser.Caret, 0); }
		public TerminalNode Or() { return getToken(MxLiteParser.Or, 0); }
		public TerminalNode AndAnd() { return getToken(MxLiteParser.AndAnd, 0); }
		public TerminalNode OrOr() { return getToken(MxLiteParser.OrOr, 0); }
		public TerminalNode Assign() { return getToken(MxLiteParser.Assign, 0); }
		public BinaryExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).enterBinaryExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).exitBinaryExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxLiteVisitor ) return ((MxLiteVisitor<? extends T>)visitor).visitBinaryExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PreExprContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SelfPlus() { return getToken(MxLiteParser.SelfPlus, 0); }
		public TerminalNode SelfMinus() { return getToken(MxLiteParser.SelfMinus, 0); }
		public TerminalNode Plus() { return getToken(MxLiteParser.Plus, 0); }
		public TerminalNode Minus() { return getToken(MxLiteParser.Minus, 0); }
		public TerminalNode Not() { return getToken(MxLiteParser.Not, 0); }
		public TerminalNode Tilde() { return getToken(MxLiteParser.Tilde, 0); }
		public PreExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).enterPreExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).exitPreExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxLiteVisitor ) return ((MxLiteVisitor<? extends T>)visitor).visitPreExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SufExprContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode SelfPlus() { return getToken(MxLiteParser.SelfPlus, 0); }
		public TerminalNode SelfMinus() { return getToken(MxLiteParser.SelfMinus, 0); }
		public SufExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).enterSufExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).exitSufExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxLiteVisitor ) return ((MxLiteVisitor<? extends T>)visitor).visitSufExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SubExprContext extends ExpressionContext {
		public TerminalNode LeftParen() { return getToken(MxLiteParser.LeftParen, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RightParen() { return getToken(MxLiteParser.RightParen, 0); }
		public SubExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).enterSubExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).exitSubExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxLiteVisitor ) return ((MxLiteVisitor<? extends T>)visitor).visitSubExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IndexExprContext extends ExpressionContext {
		public ExpressionContext expr1;
		public ExpressionContext expr2;
		public TerminalNode LeftBracket() { return getToken(MxLiteParser.LeftBracket, 0); }
		public TerminalNode RightBracket() { return getToken(MxLiteParser.RightBracket, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public IndexExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).enterIndexExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).exitIndexExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxLiteVisitor ) return ((MxLiteVisitor<? extends T>)visitor).visitIndexExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LambdaExprContext extends ExpressionContext {
		public LambdaContext lambda() {
			return getRuleContext(LambdaContext.class,0);
		}
		public LambdaExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).enterLambdaExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).exitLambdaExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxLiteVisitor ) return ((MxLiteVisitor<? extends T>)visitor).visitLambdaExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ClassthisExprContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode Dot() { return getToken(MxLiteParser.Dot, 0); }
		public TerminalNode This() { return getToken(MxLiteParser.This, 0); }
		public ClassthisExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).enterClassthisExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).exitClassthisExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxLiteVisitor ) return ((MxLiteVisitor<? extends T>)visitor).visitClassthisExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ConstExprContext extends ExpressionContext {
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public ConstExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).enterConstExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).exitConstExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxLiteVisitor ) return ((MxLiteVisitor<? extends T>)visitor).visitConstExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IdExprContext extends ExpressionContext {
		public TerminalNode Identifier() { return getToken(MxLiteParser.Identifier, 0); }
		public IdExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).enterIdExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).exitIdExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxLiteVisitor ) return ((MxLiteVisitor<? extends T>)visitor).visitIdExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 30;
		enterRecursionRule(_localctx, 30, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(193);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LeftParen:
				{
				_localctx = new SubExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(177);
				match(LeftParen);
				setState(178);
				expression(0);
				setState(179);
				match(RightParen);
				}
				break;
			case Null:
			case BoolConst:
			case IntConst:
			case StringConst:
				{
				_localctx = new ConstExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(181);
				constant();
				}
				break;
			case LambdaStart:
				{
				_localctx = new LambdaExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(182);
				lambda();
				}
				break;
			case This:
				{
				_localctx = new ThisExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(183);
				match(This);
				}
				break;
			case Identifier:
				{
				_localctx = new IdExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(184);
				match(Identifier);
				}
				break;
			case SelfPlus:
			case SelfMinus:
				{
				_localctx = new PreExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(185);
				((PreExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==SelfPlus || _la==SelfMinus) ) {
					((PreExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(186);
				expression(15);
				}
				break;
			case Plus:
			case Minus:
				{
				_localctx = new PreExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(187);
				((PreExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==Plus || _la==Minus) ) {
					((PreExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(188);
				expression(14);
				}
				break;
			case Not:
			case Tilde:
				{
				_localctx = new PreExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(189);
				((PreExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==Not || _la==Tilde) ) {
					((PreExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(190);
				expression(13);
				}
				break;
			case New:
				{
				_localctx = new NewExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(191);
				match(New);
				setState(192);
				creator();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(249);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(247);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
					case 1:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).src1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(195);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(196);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Mul) | (1L << Div) | (1L << Mod))) != 0)) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(197);
						((BinaryExprContext)_localctx).src2 = expression(12);
						}
						break;
					case 2:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).src1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(198);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(199);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==Plus || _la==Minus) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(200);
						((BinaryExprContext)_localctx).src2 = expression(11);
						}
						break;
					case 3:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).src1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(201);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(202);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==LeftShift || _la==RightShift) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(203);
						((BinaryExprContext)_localctx).src2 = expression(10);
						}
						break;
					case 4:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).src1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(204);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(205);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Less) | (1L << LessEqual) | (1L << Greater) | (1L << GreaterEqual))) != 0)) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(206);
						((BinaryExprContext)_localctx).src2 = expression(9);
						}
						break;
					case 5:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).src1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(207);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(208);
						((BinaryExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==Equal || _la==NotEqual) ) {
							((BinaryExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(209);
						((BinaryExprContext)_localctx).src2 = expression(8);
						}
						break;
					case 6:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).src1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(210);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(211);
						((BinaryExprContext)_localctx).op = match(And);
						setState(212);
						((BinaryExprContext)_localctx).src2 = expression(7);
						}
						break;
					case 7:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).src1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(213);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(214);
						((BinaryExprContext)_localctx).op = match(Caret);
						setState(215);
						((BinaryExprContext)_localctx).src2 = expression(6);
						}
						break;
					case 8:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).src1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(216);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(217);
						((BinaryExprContext)_localctx).op = match(Or);
						setState(218);
						((BinaryExprContext)_localctx).src2 = expression(5);
						}
						break;
					case 9:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).src1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(219);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(220);
						((BinaryExprContext)_localctx).op = match(AndAnd);
						setState(221);
						((BinaryExprContext)_localctx).src2 = expression(4);
						}
						break;
					case 10:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).src1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(222);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(223);
						((BinaryExprContext)_localctx).op = match(OrOr);
						setState(224);
						((BinaryExprContext)_localctx).src2 = expression(3);
						}
						break;
					case 11:
						{
						_localctx = new BinaryExprContext(new ExpressionContext(_parentctx, _parentState));
						((BinaryExprContext)_localctx).src1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(225);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(226);
						((BinaryExprContext)_localctx).op = match(Assign);
						setState(227);
						((BinaryExprContext)_localctx).src2 = expression(1);
						}
						break;
					case 12:
						{
						_localctx = new ClassthisExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(228);
						if (!(precpred(_ctx, 20))) throw new FailedPredicateException(this, "precpred(_ctx, 20)");
						setState(229);
						match(Dot);
						setState(230);
						match(This);
						}
						break;
					case 13:
						{
						_localctx = new FuncExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(231);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(232);
						match(LeftParen);
						setState(234);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Null) | (1L << New) | (1L << This) | (1L << LeftParen) | (1L << Plus) | (1L << SelfPlus) | (1L << Minus) | (1L << SelfMinus) | (1L << Not) | (1L << Tilde) | (1L << LambdaStart) | (1L << BoolConst) | (1L << IntConst) | (1L << StringConst) | (1L << Identifier))) != 0)) {
							{
							setState(233);
							expressionList();
							}
						}

						setState(236);
						match(RightParen);
						}
						break;
					case 14:
						{
						_localctx = new IndexExprContext(new ExpressionContext(_parentctx, _parentState));
						((IndexExprContext)_localctx).expr1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(237);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(238);
						match(LeftBracket);
						setState(239);
						((IndexExprContext)_localctx).expr2 = expression(0);
						setState(240);
						match(RightBracket);
						}
						break;
					case 15:
						{
						_localctx = new ClassExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(242);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(243);
						match(Dot);
						setState(244);
						match(Identifier);
						}
						break;
					case 16:
						{
						_localctx = new SufExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(245);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(246);
						((SufExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==SelfPlus || _la==SelfMinus) ) {
							((SufExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					}
					} 
				}
				setState(251);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ExpressionListContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(MxLiteParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(MxLiteParser.Comma, i);
		}
		public ExpressionListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).enterExpressionList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).exitExpressionList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxLiteVisitor ) return ((MxLiteVisitor<? extends T>)visitor).visitExpressionList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionListContext expressionList() throws RecognitionException {
		ExpressionListContext _localctx = new ExpressionListContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_expressionList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(252);
			expression(0);
			setState(257);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(253);
				match(Comma);
				setState(254);
				expression(0);
				}
				}
				setState(259);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CreatorContext extends ParserRuleContext {
		public CreatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_creator; }
	 
		public CreatorContext() { }
		public void copyFrom(CreatorContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ClassCreaContext extends CreatorContext {
		public BasTypeContext basType() {
			return getRuleContext(BasTypeContext.class,0);
		}
		public TerminalNode LeftParen() { return getToken(MxLiteParser.LeftParen, 0); }
		public TerminalNode RightParen() { return getToken(MxLiteParser.RightParen, 0); }
		public ClassCreaContext(CreatorContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).enterClassCrea(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).exitClassCrea(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxLiteVisitor ) return ((MxLiteVisitor<? extends T>)visitor).visitClassCrea(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BasicCreaContext extends CreatorContext {
		public BasTypeContext basType() {
			return getRuleContext(BasTypeContext.class,0);
		}
		public BasicCreaContext(CreatorContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).enterBasicCrea(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).exitBasicCrea(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxLiteVisitor ) return ((MxLiteVisitor<? extends T>)visitor).visitBasicCrea(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrayCreaContext extends CreatorContext {
		public BasTypeContext basType() {
			return getRuleContext(BasTypeContext.class,0);
		}
		public List<TerminalNode> LeftBracket() { return getTokens(MxLiteParser.LeftBracket); }
		public TerminalNode LeftBracket(int i) {
			return getToken(MxLiteParser.LeftBracket, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> RightBracket() { return getTokens(MxLiteParser.RightBracket); }
		public TerminalNode RightBracket(int i) {
			return getToken(MxLiteParser.RightBracket, i);
		}
		public ArrayCreaContext(CreatorContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).enterArrayCrea(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).exitArrayCrea(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxLiteVisitor ) return ((MxLiteVisitor<? extends T>)visitor).visitArrayCrea(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ErrorCreaContext extends CreatorContext {
		public BasTypeContext basType() {
			return getRuleContext(BasTypeContext.class,0);
		}
		public List<TerminalNode> LeftBracket() { return getTokens(MxLiteParser.LeftBracket); }
		public TerminalNode LeftBracket(int i) {
			return getToken(MxLiteParser.LeftBracket, i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> RightBracket() { return getTokens(MxLiteParser.RightBracket); }
		public TerminalNode RightBracket(int i) {
			return getToken(MxLiteParser.RightBracket, i);
		}
		public ErrorCreaContext(CreatorContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).enterErrorCrea(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).exitErrorCrea(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxLiteVisitor ) return ((MxLiteVisitor<? extends T>)visitor).visitErrorCrea(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CreatorContext creator() throws RecognitionException {
		CreatorContext _localctx = new CreatorContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_creator);
		try {
			int _alt;
			setState(304);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				_localctx = new ErrorCreaContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(260);
				basType();
				setState(265); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(261);
						match(LeftBracket);
						setState(262);
						expression(0);
						setState(263);
						match(RightBracket);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(267); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(271); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(269);
						match(LeftBracket);
						setState(270);
						match(RightBracket);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(273); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(279); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(275);
						match(LeftBracket);
						setState(276);
						expression(0);
						setState(277);
						match(RightBracket);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(281); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			case 2:
				_localctx = new ArrayCreaContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(283);
				basType();
				setState(288); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(284);
						match(LeftBracket);
						setState(285);
						expression(0);
						setState(286);
						match(RightBracket);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(290); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(296);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(292);
						match(LeftBracket);
						setState(293);
						match(RightBracket);
						}
						} 
					}
					setState(298);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
				}
				}
				break;
			case 3:
				_localctx = new ClassCreaContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(299);
				basType();
				setState(300);
				match(LeftParen);
				setState(301);
				match(RightParen);
				}
				break;
			case 4:
				_localctx = new BasicCreaContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(303);
				basType();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LambdaContext extends ParserRuleContext {
		public TerminalNode LambdaStart() { return getToken(MxLiteParser.LambdaStart, 0); }
		public SuiteContext suite() {
			return getRuleContext(SuiteContext.class,0);
		}
		public List<TerminalNode> LeftParen() { return getTokens(MxLiteParser.LeftParen); }
		public TerminalNode LeftParen(int i) {
			return getToken(MxLiteParser.LeftParen, i);
		}
		public List<TerminalNode> RightParen() { return getTokens(MxLiteParser.RightParen); }
		public TerminalNode RightParen(int i) {
			return getToken(MxLiteParser.RightParen, i);
		}
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public LambdaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambda; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).enterLambda(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MxLiteListener ) ((MxLiteListener)listener).exitLambda(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MxLiteVisitor ) return ((MxLiteVisitor<? extends T>)visitor).visitLambda(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LambdaContext lambda() throws RecognitionException {
		LambdaContext _localctx = new LambdaContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_lambda);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(306);
			match(LambdaStart);
			setState(312);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LeftParen) {
				{
				setState(307);
				match(LeftParen);
				setState(309);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Int) | (1L << Bool) | (1L << String) | (1L << Identifier))) != 0)) {
					{
					setState(308);
					parameterList();
					}
				}

				setState(311);
				match(RightParen);
				}
			}

			setState(314);
			match(T__0);
			setState(315);
			suite();
			setState(316);
			match(LeftParen);
			setState(318);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Null) | (1L << New) | (1L << This) | (1L << LeftParen) | (1L << Plus) | (1L << SelfPlus) | (1L << Minus) | (1L << SelfMinus) | (1L << Not) | (1L << Tilde) | (1L << LambdaStart) | (1L << BoolConst) | (1L << IntConst) | (1L << StringConst) | (1L << Identifier))) != 0)) {
				{
				setState(317);
				expressionList();
				}
			}

			setState(320);
			match(RightParen);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 15:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 11);
		case 1:
			return precpred(_ctx, 10);
		case 2:
			return precpred(_ctx, 9);
		case 3:
			return precpred(_ctx, 8);
		case 4:
			return precpred(_ctx, 7);
		case 5:
			return precpred(_ctx, 6);
		case 6:
			return precpred(_ctx, 5);
		case 7:
			return precpred(_ctx, 4);
		case 8:
			return precpred(_ctx, 3);
		case 9:
			return precpred(_ctx, 2);
		case 10:
			return precpred(_ctx, 1);
		case 11:
			return precpred(_ctx, 20);
		case 12:
			return precpred(_ctx, 19);
		case 13:
			return precpred(_ctx, 18);
		case 14:
			return precpred(_ctx, 17);
		case 15:
			return precpred(_ctx, 16);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3=\u0145\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\3\2\3\2\3\3\3\3\3\4\3\4\3\4\7\4\60\n\4\f\4\16\4\63"+
		"\13\4\3\5\3\5\5\5\67\n\5\3\6\7\6:\n\6\f\6\16\6=\13\6\3\7\3\7\3\7\5\7B"+
		"\n\7\3\b\3\b\3\b\3\b\7\bH\n\b\f\b\16\bK\13\b\3\b\3\b\3\t\3\t\3\t\5\tR"+
		"\n\t\3\n\3\n\3\n\3\n\5\nX\n\n\3\n\3\n\3\n\3\13\3\13\3\13\7\13`\n\13\f"+
		"\13\16\13c\13\13\3\f\3\f\3\f\3\r\3\r\7\rj\n\r\f\r\16\rm\13\r\3\r\3\r\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\7\16w\n\16\f\16\16\16z\13\16\3\16\3\16\3"+
		"\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3"+
		"\20\5\20\u008d\n\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20"+
		"\u0098\n\20\3\20\3\20\5\20\u009c\n\20\3\20\3\20\5\20\u00a0\n\20\3\20\3"+
		"\20\3\20\3\20\5\20\u00a6\n\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\5\20\u00b1\n\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u00c4\n\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u00ed\n\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\7\21\u00fa\n\21\f\21\16\21\u00fd\13"+
		"\21\3\22\3\22\3\22\7\22\u0102\n\22\f\22\16\22\u0105\13\22\3\23\3\23\3"+
		"\23\3\23\3\23\6\23\u010c\n\23\r\23\16\23\u010d\3\23\3\23\6\23\u0112\n"+
		"\23\r\23\16\23\u0113\3\23\3\23\3\23\3\23\6\23\u011a\n\23\r\23\16\23\u011b"+
		"\3\23\3\23\3\23\3\23\3\23\6\23\u0123\n\23\r\23\16\23\u0124\3\23\3\23\7"+
		"\23\u0129\n\23\f\23\16\23\u012c\13\23\3\23\3\23\3\23\3\23\3\23\5\23\u0133"+
		"\n\23\3\24\3\24\3\24\5\24\u0138\n\24\3\24\5\24\u013b\n\24\3\24\3\24\3"+
		"\24\3\24\5\24\u0141\n\24\3\24\3\24\3\24\2\3 \25\2\4\6\b\n\f\16\20\22\24"+
		"\26\30\32\34\36 \"$&\2\13\4\2\7\7:<\4\2\4\6==\4\2!!##\4\2  \"\"\3\2,-"+
		"\3\2$&\3\2\36\37\3\2\32\35\3\2\63\64\2\u0171\2(\3\2\2\2\4*\3\2\2\2\6,"+
		"\3\2\2\2\b\66\3\2\2\2\n;\3\2\2\2\fA\3\2\2\2\16C\3\2\2\2\20N\3\2\2\2\22"+
		"S\3\2\2\2\24\\\3\2\2\2\26d\3\2\2\2\30g\3\2\2\2\32p\3\2\2\2\34~\3\2\2\2"+
		"\36\u00b0\3\2\2\2 \u00c3\3\2\2\2\"\u00fe\3\2\2\2$\u0132\3\2\2\2&\u0134"+
		"\3\2\2\2()\t\2\2\2)\3\3\2\2\2*+\t\3\2\2+\5\3\2\2\2,\61\5\4\3\2-.\7\26"+
		"\2\2.\60\7\27\2\2/-\3\2\2\2\60\63\3\2\2\2\61/\3\2\2\2\61\62\3\2\2\2\62"+
		"\7\3\2\2\2\63\61\3\2\2\2\64\67\5\6\4\2\65\67\7\b\2\2\66\64\3\2\2\2\66"+
		"\65\3\2\2\2\67\t\3\2\2\28:\5\f\7\298\3\2\2\2:=\3\2\2\2;9\3\2\2\2;<\3\2"+
		"\2\2<\13\3\2\2\2=;\3\2\2\2>B\5\16\b\2?B\5\22\n\2@B\5\32\16\2A>\3\2\2\2"+
		"A?\3\2\2\2A@\3\2\2\2B\r\3\2\2\2CD\5\6\4\2DI\5\20\t\2EF\7\61\2\2FH\5\20"+
		"\t\2GE\3\2\2\2HK\3\2\2\2IG\3\2\2\2IJ\3\2\2\2JL\3\2\2\2KI\3\2\2\2LM\7\60"+
		"\2\2M\17\3\2\2\2NQ\7=\2\2OP\7\62\2\2PR\5 \21\2QO\3\2\2\2QR\3\2\2\2R\21"+
		"\3\2\2\2ST\5\b\5\2TU\7=\2\2UW\7\24\2\2VX\5\24\13\2WV\3\2\2\2WX\3\2\2\2"+
		"XY\3\2\2\2YZ\7\25\2\2Z[\5\30\r\2[\23\3\2\2\2\\a\5\26\f\2]^\7\61\2\2^`"+
		"\5\26\f\2_]\3\2\2\2`c\3\2\2\2a_\3\2\2\2ab\3\2\2\2b\25\3\2\2\2ca\3\2\2"+
		"\2de\5\6\4\2ef\7=\2\2f\27\3\2\2\2gk\7\30\2\2hj\5\36\20\2ih\3\2\2\2jm\3"+
		"\2\2\2ki\3\2\2\2kl\3\2\2\2ln\3\2\2\2mk\3\2\2\2no\7\31\2\2o\31\3\2\2\2"+
		"pq\7\21\2\2qr\7=\2\2rx\7\30\2\2sw\5\16\b\2tw\5\22\n\2uw\5\34\17\2vs\3"+
		"\2\2\2vt\3\2\2\2vu\3\2\2\2wz\3\2\2\2xv\3\2\2\2xy\3\2\2\2y{\3\2\2\2zx\3"+
		"\2\2\2{|\7\31\2\2|}\7\60\2\2}\33\3\2\2\2~\177\7=\2\2\177\u0080\7\24\2"+
		"\2\u0080\u0081\7\25\2\2\u0081\u0082\5\30\r\2\u0082\35\3\2\2\2\u0083\u00b1"+
		"\5\30\r\2\u0084\u00b1\5\16\b\2\u0085\u0086\7\t\2\2\u0086\u0087\7\24\2"+
		"\2\u0087\u0088\5 \21\2\u0088\u0089\7\25\2\2\u0089\u008c\5\36\20\2\u008a"+
		"\u008b\7\n\2\2\u008b\u008d\5\36\20\2\u008c\u008a\3\2\2\2\u008c\u008d\3"+
		"\2\2\2\u008d\u00b1\3\2\2\2\u008e\u008f\7\f\2\2\u008f\u0090\7\24\2\2\u0090"+
		"\u0091\5 \21\2\u0091\u0092\7\25\2\2\u0092\u0093\5\36\20\2\u0093\u00b1"+
		"\3\2\2\2\u0094\u0095\7\13\2\2\u0095\u0097\7\24\2\2\u0096\u0098\5 \21\2"+
		"\u0097\u0096\3\2\2\2\u0097\u0098\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u009b"+
		"\7\60\2\2\u009a\u009c\5 \21\2\u009b\u009a\3\2\2\2\u009b\u009c\3\2\2\2"+
		"\u009c\u009d\3\2\2\2\u009d\u009f\7\60\2\2\u009e\u00a0\5 \21\2\u009f\u009e"+
		"\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a2\7\25\2\2"+
		"\u00a2\u00b1\5\36\20\2\u00a3\u00a5\7\17\2\2\u00a4\u00a6\5 \21\2\u00a5"+
		"\u00a4\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00b1\7\60"+
		"\2\2\u00a8\u00a9\7\r\2\2\u00a9\u00b1\7\60\2\2\u00aa\u00ab\7\16\2\2\u00ab"+
		"\u00b1\7\60\2\2\u00ac\u00ad\5 \21\2\u00ad\u00ae\7\60\2\2\u00ae\u00b1\3"+
		"\2\2\2\u00af\u00b1\7\60\2\2\u00b0\u0083\3\2\2\2\u00b0\u0084\3\2\2\2\u00b0"+
		"\u0085\3\2\2\2\u00b0\u008e\3\2\2\2\u00b0\u0094\3\2\2\2\u00b0\u00a3\3\2"+
		"\2\2\u00b0\u00a8\3\2\2\2\u00b0\u00aa\3\2\2\2\u00b0\u00ac\3\2\2\2\u00b0"+
		"\u00af\3\2\2\2\u00b1\37\3\2\2\2\u00b2\u00b3\b\21\1\2\u00b3\u00b4\7\24"+
		"\2\2\u00b4\u00b5\5 \21\2\u00b5\u00b6\7\25\2\2\u00b6\u00c4\3\2\2\2\u00b7"+
		"\u00c4\5\2\2\2\u00b8\u00c4\5&\24\2\u00b9\u00c4\7\22\2\2\u00ba\u00c4\7"+
		"=\2\2\u00bb\u00bc\t\4\2\2\u00bc\u00c4\5 \21\21\u00bd\u00be\t\5\2\2\u00be"+
		"\u00c4\5 \21\20\u00bf\u00c0\t\6\2\2\u00c0\u00c4\5 \21\17\u00c1\u00c2\7"+
		"\20\2\2\u00c2\u00c4\5$\23\2\u00c3\u00b2\3\2\2\2\u00c3\u00b7\3\2\2\2\u00c3"+
		"\u00b8\3\2\2\2\u00c3\u00b9\3\2\2\2\u00c3\u00ba\3\2\2\2\u00c3\u00bb\3\2"+
		"\2\2\u00c3\u00bd\3\2\2\2\u00c3\u00bf\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c4"+
		"\u00fb\3\2\2\2\u00c5\u00c6\f\r\2\2\u00c6\u00c7\t\7\2\2\u00c7\u00fa\5 "+
		"\21\16\u00c8\u00c9\f\f\2\2\u00c9\u00ca\t\5\2\2\u00ca\u00fa\5 \21\r\u00cb"+
		"\u00cc\f\13\2\2\u00cc\u00cd\t\b\2\2\u00cd\u00fa\5 \21\f\u00ce\u00cf\f"+
		"\n\2\2\u00cf\u00d0\t\t\2\2\u00d0\u00fa\5 \21\13\u00d1\u00d2\f\t\2\2\u00d2"+
		"\u00d3\t\n\2\2\u00d3\u00fa\5 \21\n\u00d4\u00d5\f\b\2\2\u00d5\u00d6\7\'"+
		"\2\2\u00d6\u00fa\5 \21\t\u00d7\u00d8\f\7\2\2\u00d8\u00d9\7+\2\2\u00d9"+
		"\u00fa\5 \21\b\u00da\u00db\f\6\2\2\u00db\u00dc\7(\2\2\u00dc\u00fa\5 \21"+
		"\7\u00dd\u00de\f\5\2\2\u00de\u00df\7)\2\2\u00df\u00fa\5 \21\6\u00e0\u00e1"+
		"\f\4\2\2\u00e1\u00e2\7*\2\2\u00e2\u00fa\5 \21\5\u00e3\u00e4\f\3\2\2\u00e4"+
		"\u00e5\7\62\2\2\u00e5\u00fa\5 \21\3\u00e6\u00e7\f\26\2\2\u00e7\u00e8\7"+
		"\23\2\2\u00e8\u00fa\7\22\2\2\u00e9\u00ea\f\25\2\2\u00ea\u00ec\7\24\2\2"+
		"\u00eb\u00ed\5\"\22\2\u00ec\u00eb\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed\u00ee"+
		"\3\2\2\2\u00ee\u00fa\7\25\2\2\u00ef\u00f0\f\24\2\2\u00f0\u00f1\7\26\2"+
		"\2\u00f1\u00f2\5 \21\2\u00f2\u00f3\7\27\2\2\u00f3\u00fa\3\2\2\2\u00f4"+
		"\u00f5\f\23\2\2\u00f5\u00f6\7\23\2\2\u00f6\u00fa\7=\2\2\u00f7\u00f8\f"+
		"\22\2\2\u00f8\u00fa\t\4\2\2\u00f9\u00c5\3\2\2\2\u00f9\u00c8\3\2\2\2\u00f9"+
		"\u00cb\3\2\2\2\u00f9\u00ce\3\2\2\2\u00f9\u00d1\3\2\2\2\u00f9\u00d4\3\2"+
		"\2\2\u00f9\u00d7\3\2\2\2\u00f9\u00da\3\2\2\2\u00f9\u00dd\3\2\2\2\u00f9"+
		"\u00e0\3\2\2\2\u00f9\u00e3\3\2\2\2\u00f9\u00e6\3\2\2\2\u00f9\u00e9\3\2"+
		"\2\2\u00f9\u00ef\3\2\2\2\u00f9\u00f4\3\2\2\2\u00f9\u00f7\3\2\2\2\u00fa"+
		"\u00fd\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fc!\3\2\2\2"+
		"\u00fd\u00fb\3\2\2\2\u00fe\u0103\5 \21\2\u00ff\u0100\7\61\2\2\u0100\u0102"+
		"\5 \21\2\u0101\u00ff\3\2\2\2\u0102\u0105\3\2\2\2\u0103\u0101\3\2\2\2\u0103"+
		"\u0104\3\2\2\2\u0104#\3\2\2\2\u0105\u0103\3\2\2\2\u0106\u010b\5\4\3\2"+
		"\u0107\u0108\7\26\2\2\u0108\u0109\5 \21\2\u0109\u010a\7\27\2\2\u010a\u010c"+
		"\3\2\2\2\u010b\u0107\3\2\2\2\u010c\u010d\3\2\2\2\u010d\u010b\3\2\2\2\u010d"+
		"\u010e\3\2\2\2\u010e\u0111\3\2\2\2\u010f\u0110\7\26\2\2\u0110\u0112\7"+
		"\27\2\2\u0111\u010f\3\2\2\2\u0112\u0113\3\2\2\2\u0113\u0111\3\2\2\2\u0113"+
		"\u0114\3\2\2\2\u0114\u0119\3\2\2\2\u0115\u0116\7\26\2\2\u0116\u0117\5"+
		" \21\2\u0117\u0118\7\27\2\2\u0118\u011a\3\2\2\2\u0119\u0115\3\2\2\2\u011a"+
		"\u011b\3\2\2\2\u011b\u0119\3\2\2\2\u011b\u011c\3\2\2\2\u011c\u0133\3\2"+
		"\2\2\u011d\u0122\5\4\3\2\u011e\u011f\7\26\2\2\u011f\u0120\5 \21\2\u0120"+
		"\u0121\7\27\2\2\u0121\u0123\3\2\2\2\u0122\u011e\3\2\2\2\u0123\u0124\3"+
		"\2\2\2\u0124\u0122\3\2\2\2\u0124\u0125\3\2\2\2\u0125\u012a\3\2\2\2\u0126"+
		"\u0127\7\26\2\2\u0127\u0129\7\27\2\2\u0128\u0126\3\2\2\2\u0129\u012c\3"+
		"\2\2\2\u012a\u0128\3\2\2\2\u012a\u012b\3\2\2\2\u012b\u0133\3\2\2\2\u012c"+
		"\u012a\3\2\2\2\u012d\u012e\5\4\3\2\u012e\u012f\7\24\2\2\u012f\u0130\7"+
		"\25\2\2\u0130\u0133\3\2\2\2\u0131\u0133\5\4\3\2\u0132\u0106\3\2\2\2\u0132"+
		"\u011d\3\2\2\2\u0132\u012d\3\2\2\2\u0132\u0131\3\2\2\2\u0133%\3\2\2\2"+
		"\u0134\u013a\7\65\2\2\u0135\u0137\7\24\2\2\u0136\u0138\5\24\13\2\u0137"+
		"\u0136\3\2\2\2\u0137\u0138\3\2\2\2\u0138\u0139\3\2\2\2\u0139\u013b\7\25"+
		"\2\2\u013a\u0135\3\2\2\2\u013a\u013b\3\2\2\2\u013b\u013c\3\2\2\2\u013c"+
		"\u013d\7\3\2\2\u013d\u013e\5\30\r\2\u013e\u0140\7\24\2\2\u013f\u0141\5"+
		"\"\22\2\u0140\u013f\3\2\2\2\u0140\u0141\3\2\2\2\u0141\u0142\3\2\2\2\u0142"+
		"\u0143\7\25\2\2\u0143\'\3\2\2\2!\61\66;AIQWakvx\u008c\u0097\u009b\u009f"+
		"\u00a5\u00b0\u00c3\u00ec\u00f9\u00fb\u0103\u010d\u0113\u011b\u0124\u012a"+
		"\u0132\u0137\u013a\u0140";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}