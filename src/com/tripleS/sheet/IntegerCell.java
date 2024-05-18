package com.tripleS.sheet;

public class IntegerCell extends Cell {
	public IntegerCell(Sheet s, int data) {
		super(s);
		super.data = data;
	}
	public int eval() {
		return super.data;
	}
	public String display() {
		return String.valueOf(super.data);
	}
	public String insideDisplay() {
		return String.valueOf(super.data);
	}
	public String fileDisplay() {
		return String.valueOf(super.data);
	}
}
