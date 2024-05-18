package com.tripleS.expr;

abstract public class OpNode extends Node {
	public OpNode() {
		super.type = NodeType.Operator;
	}
	abstract public int calc(int n1, int n2) throws EvalException;
}
