package com.tripleS.expr;

public class IntNode extends Node {
	int data;
	public IntNode(int data) {
		super.type = NodeType.Int;
		this.data = data;
	}
	public int getData() {
		return data;
	}
}
