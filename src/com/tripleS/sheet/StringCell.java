package com.tripleS.sheet;

public class StringCell extends Cell {
	private final String str;
	public StringCell(Sheet s, String str) {
		super(s);
		super.data = 0;
		this.str = str;
	}
	public String display() {
		return str;
	}
	public String insideDisplay() {
		return str;
	}
	public String fileDisplay() {
		return '"' + str + '"';
	}
}
