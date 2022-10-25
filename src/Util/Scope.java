package Util;

import AST.TypeNode;
import Util.error.semanticError;

import java.util.HashMap;

public class Scope {
	public Scope pScope;
	public HashMap<String, Type> typeMap = new HashMap<>();
	public HashMap<String, Type> varMap = new HashMap<>();
	public HashMap<String, Type> funcMap = new HashMap<>();

	public Scope(Scope _pScope) {
		pScope = _pScope;
	}

	public boolean Exist(String id, boolean up, position pos) {
		if(typeMap.containsKey(id))
			return true;
		if(up && pScope != null)
			return pScope.Exist(id, true, pos);
		return false;
	}

	public void NewVar(String id, Type type, position pos) {
		System.out.println(id + " " + type.type + " " + type.dim);
		if(Exist(id, true, pos))
			throw new semanticError("Variable Conflict", pos);
		if(varMap.containsKey(id))
			throw new semanticError("Variable Duplicate Definition", pos);
		varMap.put(id, type);
	}

	public void NewFunc(String id, Type type, position pos) {
		System.out.println(pScope);
		System.out.println("New Func: " + id + " " + type.type + " " + type.functionParameters);
		if(Exist(id, true, pos))
			throw new semanticError("Function Conflict", pos);
		if(funcMap.containsKey(id))
			throw new semanticError("Function Duplicate Definition", pos);
		funcMap.put(id, type);
	}

	public void NewType(String id, Type type, position pos) {
		System.out.println("NewType: " + id + " " + type.type + " " + pos);
		if(type.funcMap != null)
			System.out.println(type.funcMap);
		if(typeMap.containsKey(id))
			throw new semanticError("Type Conflict", pos);
		typeMap.put(id, type);
	}

	public Type VarGet(String id, boolean up, position pos) {
		if(varMap.containsKey(id))
			return varMap.get(id);
		if(up && pScope != null)
			return pScope.VarGet(id, true, pos);
		throw new semanticError("Undefined Variable", pos);
	}

	public Type FuncGet(String id, boolean up, position pos) {
		System.out.println(id);
		System.out.println(up);
		System.out.println(pos);
		if(funcMap.containsKey(id))
			return funcMap.get(id);
		if(pScope != null)
			return pScope.FuncGet(id, true, pos);
		throw new semanticError("Undefined Function", pos);
	}

	public Type TypeGet(TypeNode type, position pos) {
		System.out.println(type.dim + " " + type.type + " " + pos);
		if(type.dim == 0) {
			return typeMap.get(type.type);
		}
		else {
			return new Type((typeMap.get(type.type)), type.dim);
		}
	}
}