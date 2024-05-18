package com.tripleS.sheet;

import com.tripleS.window.Main;

public class Sheet {
	Cell[][] sheet;
	public Sheet() {
		int i, j;

		sheet = new Cell[Main.TABLE_HEIGHT][Main.TABLE_WIDTH];
		for (i = 0; i < Main.TABLE_HEIGHT; i++)
			for (j = 0; j < Main.TABLE_WIDTH; j++)
				sheet[i][j] = new Cell(this);
	}
	public Cell getCell(int row, int col) {
		return sheet[row][col];
	}
	public Sheet setCell(int row, int col, String input) {
		Cell newCell;
		int data;

		// DEBUG
		// JOptionPane.showMessageDialog(null, input);
		try {
			if (input.isEmpty()) {
				newCell = new Cell(this);
			}
			else {
				data = Integer.parseInt(input);
				newCell = new IntegerCell(this, data);
			}
		}
		catch (NumberFormatException nfe) {
			if (input.charAt(0) == '=')
				newCell = new ExprCell(this, input);
			else
				newCell = new StringCell(this, input);
		}
		catch (StackOverflowError soe) {
			newCell = new IntegerCell(this, 0);
			newCell.setErrorType(CellErrorType.OverUnderFlow);
		}
		sheet[row][col] = newCell;
		return this;
	}
	public String getCellDisplay(int row, int col) {
		return sheet[row][col].display();
	}
	public String getCellInsideDisplay(int row, int col) {
		return sheet[row][col].insideDisplay();
	}
	public String getCellFileDisplay(int row, int col) {
		return sheet[row][col].fileDisplay();
	}
}
