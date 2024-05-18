package com.tripleS.window;

import com.tripleS.sheet.*;
import javax.swing.*;
import javax.swing.event.*;

public class TableSheetSynchronizer
		implements TableModelListener, ListSelectionListener, TableColumnModelListener {
	private Sheet s;
	private JTable table;
	private boolean mutex;
	private int row, col;
	public TableSheetSynchronizer() {
		s = new Sheet();
		mutex = false;
		row = -1;
		col = -1;
	}
	public void addTable(JTable table) {
		this.table = table;
		table.getModel().addTableModelListener(this);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(this);
		table.getColumnModel().addColumnModelListener(this);
	}
	public void clearTable() {
		s = new Sheet();
		for (int i = 0; i < Main.TABLE_HEIGHT; i++)
			for (int j = 0; j < Main.TABLE_WIDTH; j++)
				table.setValueAt("", i, j);
	}
	public void tableChanged(TableModelEvent e) {
		if (e.getType() != TableModelEvent.UPDATE)
			return;
		if (mutex)
			return;
		String displayVal = s.setCell(
				e.getFirstRow(), e.getColumn(),
				(String)table.getValueAt(e.getFirstRow(), e.getColumn())
		).getCellDisplay(e.getFirstRow(), e.getColumn());

		// CRITICAL SECTION!
		mutex = true;
		table.setValueAt(displayVal, e.getFirstRow(), e.getColumn());
		mutex = false;
	}
	public void valueChanged(ListSelectionEvent e) {
		mutex = true;
		if (row != -1 && col != -1)
			table.setValueAt(s.getCellDisplay(row, col), row, col);
		row = e.getFirstIndex();
		cellIsSelected();
		mutex = false;
	}
	public void columnSelectionChanged(ListSelectionEvent e) {
		mutex = true;
		if (row != -1 && col != -1)
			table.setValueAt(s.getCellDisplay(row, col), row, col);
		col = e.getFirstIndex();
		cellIsSelected();
		mutex = false;
	}
	private void cellIsSelected() {
		if (row == -1 || col == -1)
			return;
		String insideVal = s.getCellInsideDisplay(row, col);
		table.setValueAt(insideVal, row, col);
	}
	public void columnAdded(TableColumnModelEvent e) {

	}
	public void columnRemoved(TableColumnModelEvent e) {

	}
	public void columnMoved(TableColumnModelEvent e) {

	}
	public void columnMarginChanged(ChangeEvent e) {

	}
}
