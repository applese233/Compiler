package IR;

import java.util.HashMap;

import IR.operand.Register;

public class IRScope {
	public HashMap<String, Register> regMap;
	public IRScope pIRScope;

	public IRScope(IRScope _pIRScope) {
		pIRScope = _pIRScope;
		regMap = new HashMap<>();
	}
	
	public boolean containsKey(String id) {
		return regMap.containsKey(id);
	}

	public Register Get(String id) {
		return regMap.get(id);
	}

	public void Put(String id, Register reg) {
		regMap.put(id, reg);
	}
}
