package Util;

import java.util.ArrayList;
import java.util.HashMap;

import IR.type.ClassType;
import IR.type.IRType;
import IR.type.IntType;
import IR.type.PointerType;
import IR.type.VoidType;

public class Type {
	public enum basicType {
		Int, Bool, String, Void, Class, Function, This, Null
	}
	public basicType type;
	public String identifier;
	public int dim;
	public boolean isLeftValue;
	public Type functionReturnType;
	public ArrayList<Type> functionParameters;
	public HashMap<String, Type> varMap;
	public HashMap<String, Type> funcMap;
	public Type struct = null;
	public IRType irType = null;

	public Type(String _identifier) {
		identifier = _identifier;
	}

	public Type(basicType _type, int _dim) {
		type = _type;
		dim = _dim;
		if(type == basicType.Class)
			irType = new ClassType(identifier);
	}

	public Type(basicType _type, String _identifier) {
		type = _type;
		identifier = _identifier;
		if(type == basicType.Class)
			irType = new ClassType(identifier);
	}

	public Type(basicType _type, int _dim, String _identifier) {
		type = _type;
		dim = _dim;
		identifier = _identifier;
		if(type == basicType.Class)
			irType = new ClassType(identifier);
	}

	public Type(basicType _type, int _dim, boolean _isLeftValue) {
		type = _type;
		dim = _dim;
		isLeftValue = _isLeftValue;
		if(type == basicType.Class)
			irType = new ClassType(identifier);
	}

	public Type(String _identifier, int _dim, boolean _isLeftValue) {
		type = basicType.Class;
		identifier = _identifier;
		dim = _dim;
		isLeftValue = _isLeftValue;
		varMap = new HashMap<>();
		funcMap = new HashMap<>();
		irType = new ClassType(identifier);
	}

	public Type(String _identifier, Type returnType, ArrayList<Type> parameters) {
		type = basicType.Function;
		identifier = _identifier;
		functionReturnType = returnType;
		functionParameters = parameters;
		dim = 0;
		isLeftValue = false;
	}

	public Type(Type _type) {
		type = _type.type;
		irType = _type.irType;
		identifier = _type.identifier;
		dim = _type.dim;
		isLeftValue = _type.isLeftValue;
		if(_type.functionReturnType != null)
			functionReturnType = new Type(_type.functionReturnType);
		else
			functionReturnType = null;
		if(_type.functionParameters != null)
			functionParameters = new ArrayList<>(_type.functionParameters);
		else
			functionParameters = null;
		if(_type.varMap != null)
			varMap = new HashMap<>(_type.varMap);
		else
			varMap = null;
		if(_type.funcMap != null)
			funcMap = new HashMap<>(_type.funcMap);
		else
			funcMap = null;
		if(_type.struct != null)
			struct = new Type(_type.struct);
		else
			struct = null;
	}

	public Type(Type _type, int _dim) {
		type = _type.type;
		irType = _type.irType;
		identifier = _type.identifier;
		dim = _dim;
		isLeftValue = _type.isLeftValue;
		if(_type.functionReturnType != null)
			functionReturnType = new Type(_type.functionReturnType);
		else
			functionReturnType = null;
		if(_type.functionParameters != null)
			functionParameters = new ArrayList<>(_type.functionParameters);
		else
			functionParameters = null;
		if(_type.varMap != null)
			varMap = new HashMap<>(_type.varMap);
		else
			varMap = null;
		if(_type.funcMap != null)
			funcMap = new HashMap<>(_type.funcMap);
		else
			funcMap = null;
		if(_type.struct != null)
			struct = new Type(_type.struct);
		else
			struct = null;
	}

	public Type(Type _type, String _identifier) {
		type = _type.type;
		irType = _type.irType;
		identifier = _identifier;
		dim = _type.dim;
		isLeftValue = _type.isLeftValue;
		if(_type.functionReturnType != null)
			functionReturnType = new Type(_type.functionReturnType);
		else
			functionReturnType = null;
		if(_type.functionParameters != null)
			functionParameters = new ArrayList<>(_type.functionParameters);
		else
			functionParameters = null;
		if(_type.varMap != null)
			varMap = new HashMap<>(_type.varMap);
		else
			varMap = null;
		if(_type.funcMap != null)
			funcMap = new HashMap<>(_type.funcMap);
		else
			funcMap = null;
		if(_type.struct != null)
			struct = new Type(_type.struct);
		else
			struct = null;
	}

	public boolean TypeEqual(Type _type) {
		System.out.println(type + " " + dim + " " + identifier + " " + _type.type + " " + _type.dim + " " + _type.identifier);
		if(type == basicType.Function && _type.type == basicType.Function)
			return functionReturnType.TypeEqual(_type.functionReturnType);
		else if(type == basicType.Function)
			return functionReturnType.TypeEqual(_type);
		else if(_type.type == basicType.Function)
			return TypeEqual(_type.functionReturnType);
		else {
			System.out.println("What?");
			if(type == basicType.Class)
				return (_type.type == basicType.Null) || (_type.type == basicType.Class && dim == _type.dim);
			if(dim > 0)
				return (_type.type == basicType.Null) || (type == _type.type && dim == _type.dim);
			System.out.println("Here." + (type == _type.type) + " " + type + " " + _type.type);
			if(type == _type.type)
				return true;
			return false;
		}
	}

	public boolean Equal(basicType _type) {
		if(type == basicType.Function)
			return functionReturnType.type == _type;
		return type == _type;
	}

	public IRType GetIRType() {
		System.out.println("GetIR: " + identifier + " " + type + " " + irType);
		if(type == basicType.Int)
			return new IntType(32);
		else if(type == basicType.Bool)
			return new IntType(1);
		else if(type == basicType.String)
			return new PointerType(new IntType(8));
		else if(type == basicType.Void)
			return new VoidType();
		else if(type == basicType.Function)
			return functionReturnType.GetIRType();
		else if(type == basicType.Class) {
			if(irType == null)
				irType = new ClassType(identifier);
			return irType;
		}
		else {
			System.out.println(dim);
			if(dim > 0) {
				if(type == basicType.Int)
					return new IntType(32);
				else if(type == basicType.Bool)
					return new IntType(1);
				else if(type == basicType.String)
					return new PointerType(new IntType(8));
				else if(type == basicType.Void)
					return new VoidType();
			}
			return irType;
		}
	}
}