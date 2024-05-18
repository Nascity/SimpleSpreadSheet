package com.tripleS.sheet;

import java.util.LinkedList;

public class Cell {
	protected CellErrorType errorType = CellErrorType.No;
	protected LinkedList<ExprCell> referencingCells;
	protected int data;
	protected Sheet sheet;
	public Cell(Sheet s) {
		sheet = s;
		referencingCells = new LinkedList<>();
	}
	public int eval() {
		return 0;
	}
	public void setErrorType(CellErrorType cet) {
		errorType = cet;
	}
	public String display() {
		return switch (errorType) {
			case CellErrorType.SyntaxError -> "!SYN";
			case CellErrorType.OverUnderFlow -> "!FLOW";
			case CellErrorType.Loop -> "!LOOP";
			case CellErrorType.DivisionByZero -> "!DIV0";
			case CellErrorType.Ref -> "!REF";
			case CellErrorType.No -> "";
			default -> "!UKN";
		};
	}
	public String insideDisplay() {
		return "";
	}
	public String fileDisplay() {
		return "";
	}
	public void updateReferencingCells() {
		for (ExprCell ec : referencingCells)
			ec.update();
	}
}
