package Optimize;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;

import ASM.*;
import ASM.Inst.*;
import ASM.Operand.*;

public class RegisterAllocator {
	public class Pair {
		ASMRegister fi, se;
		public Pair() {

		}
		public Pair(ASMRegister _fi, ASMRegister _se) {
			fi = _fi;
			se = _se;
		}

		@Override
		public boolean equals(Object o) {
			if(this == o) {
				return true;
			}
			if(o == null || getClass() != o.getClass()) {
				return false;
			}
			Pair p = (Pair) o;
			return fi.equals(p.fi) && se.equals(p.se);
		}

		@Override
		public int hashCode() {
			return Objects.hash(fi, se);
		}
	}
	public ASMModule module;
	public ASMFunction nowFunction;
	public PhysicalRegister zero, s0;
	public VirtualRegister EX;

	private int K = 27;

	public HashMap<ASMBlock, HashSet<ASMRegister>> blockdef = new HashMap<>();
	public HashMap<ASMBlock, HashSet<ASMRegister>> blockuse = new HashMap<>();
	public HashMap<ASMBlock, HashSet<ASMRegister>> blocklivein = new HashMap<>();
	public HashMap<ASMBlock, HashSet<ASMRegister>> blockliveout = new HashMap<>();

	public HashSet<ASMRegister> precolored = new HashSet<>();
	public HashSet<ASMRegister> initialed = new HashSet<>();
	public HashSet<ASMRegister> simplifyWorklist = new HashSet<>();
	public HashSet<ASMRegister> freezeWorklist = new HashSet<>();
	public HashSet<ASMRegister> spillWorklist = new HashSet<>();
	public HashSet<ASMRegister> spilledNodes = new HashSet<>();
	public HashSet<ASMRegister> coalescedNodes = new HashSet<>();
	public HashSet<ASMRegister> coloredNodes = new HashSet<>();
	public Stack<ASMRegister> selectstack = new Stack<>();

	public HashSet<Mv> coalescedMoves = new HashSet<>();
	public HashSet<Mv> constrainedMoves = new HashSet<>();
	public HashSet<Mv> frozenMoves = new HashSet<>();
	public HashSet<Mv> worklistMoves = new HashSet<>();
	public HashSet<Mv> activeMoves = new HashSet<>();

	public HashSet<Pair> adjSet = new HashSet<>();
	public HashMap<ASMRegister, HashSet<ASMRegister>> adjList = new HashMap<>();
	public HashMap<ASMRegister, Integer> degree = new HashMap<>();
	public HashMap<ASMRegister, HashSet<Mv>> moveList = new HashMap<>();
	public HashMap<ASMRegister, ASMRegister> alias = new HashMap<>();
	public HashMap<ASMRegister, Integer> color = new HashMap<>();

	public HashSet<ASMRegister> spilled = new HashSet<>();

	public RegisterAllocator(ASMModule _module) {
		module = _module;
		zero = module.phyRegList.get(0);
		s0 = module.phyRegList.get(8);
	}

	public void FreezeMoves(ASMRegister u) {

	}

	public void Freeze() {
		ASMRegister u = freezeWorklist.iterator().next();
		freezeWorklist.remove(u);
		simplifyWorklist.add(u);
		FreezeMoves(u);
	}

	public void Combine(ASMRegister u, ASMRegister v) {

	}

	public boolean Conservative(HashSet<ASMRegister> tmp) {

	}

	public boolean OK(ASMRegister x, ASMRegister y) {

	}

	public void AddWorklist(ASMRegister u) {

	}

	public ASMRegister GetAlias(ASMRegister x) {
		if(coalescedNodes.contains(x)) {
			alias.replace(x, GetAlias(alias.get(x)));
			return alias.get(x);
		}
		else {
			return x;
		}
	}

	public HashSet<ASMRegister> Adjacent(ASMRegister nowReg) {

	}

	public void DecrementDegree(ASMRegister x) {

	}

	public void Simplify() {
		ASMRegister nowReg = simplifyWorklist.iterator().next();
		simplifyWorklist.remove(nowReg);
		selectstack.push(nowReg);
		for(ASMRegister x : Adjacent(nowReg)) {
			DecrementDegree(x);
		}
	}

	public void MakeWorklist() {

	}

	public void MoveRelated(ASMRegister x) {

	}

	public HashSet<Mv> NodeMoves(ASMRegister x) {

	}

	public void Build() {

	}

	public void AddEdge(ASMRegister u, ASMRegister v) {

	}

	public void Coalesce() {
		
	}

	public void AssignColors() {
		while(!selectstack.isEmpty()) {
			ASMRegister n = selectstack.pop();
			ArrayList<PhysicalRegister> nowCol = new ArrayList<>(module.icolors);
			for(ASMRegister x : adjList.get(n)) {
				HashSet<ASMRegister> tmp = new HashSet<>(coloredNodes);
				tmp.addAll(precolored);
				if(tmp.contains(GetAlias(x))) {
					nowCol.remove(module.phyRegList.get(color.get(GetAlias(x))));
				}
			}
			if(nowCol.isEmpty()) {
				spilledNodes.add(n);
			}
			else {
				coloredNodes.add(n);
				color.replace(n, module.phyRegId.get(nowCol.get(0)));
			}
		}
		for(ASMRegister x : coalescedNodes) {
			color.replace(x, color.get(GetAlias(x)));
		}
	}

	public void Analysis() {
		for(ASMBlock x : nowFunction.blockList) {
			nowFunction.blockMap.put(x.name, x);
		}
		for(ASMBlock x : nowFunction.blockList) {
			for(Inst y : x.instList) {
				if(y instanceof Branch) {
					x.succ.add(nowFunction.blockMap.get(((Branch) y).dest));
				}
				else {
					x.succ.add(nowFunction.blockMap.get(((J) y).dest));
				}
			}
		}

		for(ASMBlock x : nowFunction.blockList) {
			HashSet<ASMRegister> nowdef = new HashSet<>();
			HashSet<ASMRegister> nowuse = new HashSet<>();
			for(Inst y : x.instList) {
				y.use.forEach(z -> {
					if(!nowdef.contains(z)) {
						nowuse.add(z);
					}
				});
				nowdef.addAll(y.def);
			}
			blocklivein.put(x, nowuse);
			blockliveout.put(x, new HashSet<>());
			blockdef.put(x, nowdef);
		}
		while(true) {
			boolean fixed = true;
			for(ASMBlock x : nowFunction.blockList) {
				HashSet<ASMRegister> in = blocklivein.get(x);
				HashSet<ASMRegister> out = blockliveout.get(x);
				int inSize = in.size(), outSize = out.size();
				out.removeAll(blockdef.get(x));
				in.addAll(out);
				x.succ.forEach(y -> {
					out.addAll(blocklivein.get(y));
				});
				if(inSize != in.size() || outSize != out.size()) {
					fixed = false;
				}
			}
			if(fixed) {
				break;
			}
		}
	}
	
	public void RewritePrograms() {
		for(ASMBlock x : nowFunction.blockList) {
			ListIterator<Inst> it = x.instList.listIterator(0);
			while(it.hasNext()) {
				Inst y = it.next();
				VirtualRegister extra = null;
				for(ASMRegister z : y.def) {
					if(spilledNodes.contains(z)) {
						EX = new VirtualRegister("_EX_def", 4);
						spilled.add(EX);
						if(!nowFunction.offMap.containsKey(z.name)) {
							nowFunction.Alloca((VirtualRegister) z);
						}
						int off = -nowFunction.offMap.get(z.name);
						if(-2048 <= off && off <= 2047) {
							it.add(new Sw(s0, EX, new Immediate(off)));
						}
						else {
							VirtualRegister EX_li = new VirtualRegister("_EX_def_li", 4);
							spilled.add(EX_li);
							it.add(new Li(EX_li, new Immediate(off)));
							it.add(new Arithmetic("add", EX_li, s0, EX_li, null));
							it.add(new Sw(EX_li, EX, new Immediate(0)));
						}
						extra = EX;
						y.rd = EX;
					}
				}
				if(extra != null) {
					y.def.clear();
					y.def.add(extra);
				}
				HashMap<ASMRegister, ASMRegister> regMap = new HashMap<>();
				for(ASMRegister z : y.use) {
					if(spilledNodes.contains(z)) {
						EX = new VirtualRegister("_EX_use", 4);
						spilled.add(EX);
						if(!nowFunction.offMap.containsKey(z.name)) {
							nowFunction.Alloca((VirtualRegister) z);
						}
						int off = -nowFunction.offMap.get(z.name);
						it.previous();
						if(-2048 <= off && off <= 2047) {
							it.add(new Lw(EX, s0, new Immediate(off), null));
						}
						else {
							VirtualRegister EX_li = new VirtualRegister("_EX_use_li", 4);
							spilled.add(EX_li);
							it.add(new Li(EX_li, new Immediate(off)));
							it.add(new Arithmetic("add", EX_li, s0, EX_li, null));
							it.add(new Lw(EX, EX_li, new Immediate(0), null));
						}
						it.next();
						regMap.put(z, EX);
					}
				}
				for(Map.Entry<ASMRegister, ASMRegister> entry : regMap.entrySet()) {
					if(y.rs1 == entry.getKey()) {
						y.rs1 = entry.getValue();
					}
					if(y.rs2 == entry.getKey()) {
						y.rs2 = entry.getValue();
					}
					y.use.remove(entry.getKey());
					y.use.add(entry.getValue());
				}
			}
		}
	}

	public void SelectSpill() {
		ASMRegister nowReg = null;
		int nowd = -1;
		for(ASMRegister x : spillWorklist) {
			if((!spilled.contains(x)) && degree.get(x) > nowd) {
				nowd = degree.get(x);
				nowReg = x;
			}
			if(nowReg == null) {
				nowReg = x;
			}
		}
		spillWorklist.remove(nowReg);
		simplifyWorklist.add(nowReg);
		FreezeMoves(nowReg);
	}

	public void VisitModule() {

	}

	public void VisitFunction() {
		precolored = new HashSet<>();
		initialed = new HashSet<>();
		simplifyWorklist = new HashSet<>();
		freezeWorklist = new HashSet<>();
		spillWorklist = new HashSet<>();
		spilledNodes = new HashSet<>();
		coalescedNodes = new HashSet<>();
		coloredNodes = new HashSet<>();
		selectstack = new Stack<>();
		coalescedMoves = new HashSet<>();
		constrainedMoves = new HashSet<>();
		frozenMoves = new HashSet<>();
		worklistMoves = new HashSet<>();
		adjSet = new HashSet<>();
		adjList = new HashMap<>();
		degree = new HashMap<>();
		moveList = new HashMap<>();
		alias = new HashMap<>();
		color = new HashMap<>();
		for(ASMBlock x : nowFunction.blockList) {
			for(Inst y : x.instList) {
				initialed.addAll(y.def);
				initialed.addAll(y.use);
			}
		}
		for(int i = 0; i < 32; ++ i) {
			PhysicalRegister x = module.phyRegList.get(i);
			precolored.add(x);
			adjList.put(x, new HashSet<>());
			degree.put(x, Integer.MAX_VALUE);
			moveList.put(x, new HashSet<>());
			alias.put(x, null);
			color.put(x, i);
		}
		initialed.removeAll(precolored);
		for(ASMRegister x : initialed) {
			adjList.put(x, new HashSet<>());
			degree.put(x, 0);
			moveList.put(x, new HashSet<>());
			alias.put(x, null);
			color.put(x, null);
		}

		Analysis();
		Build();
		MakeWorklist();
		while(true) {
			if(!simplifyWorklist.isEmpty()) {
				Simplify();
			}
			else if(!worklistMoves.isEmpty()) {
				Coalesce();
			}
			else if(!freezeWorklist.isEmpty()) {
				Freeze();
			}
			else if(!spillWorklist.isEmpty()) {
				SelectSpill();
			}
			else {
				break;
			}
		}
		AssignColors();
		if(!spilledNodes.isEmpty()) {
			RewritePrograms();
			VisitFunction();
		}
	}
}