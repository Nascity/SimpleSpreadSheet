package com.tripleS.expr;

import com.tripleS.sheet.*;

public class CellNode extends Node {
	int row, col;
	public CellNode(int row, int col) {
		super.type = NodeType.Cell;
		this.row = row;
		this.col = col;
	}
	public Cell getCell(Sheet s) {
		return s.getCell(row, col);
	}
}
