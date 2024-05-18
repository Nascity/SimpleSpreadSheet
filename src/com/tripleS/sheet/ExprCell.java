package com.tripleS.sheet;

import com.tripleS.expr.*;
import com.tripleS.parse.*;

import java.util.*;

public class ExprCell extends Cell {
	private LinkedList<Node> exprTree;
	final private Stack<Integer> stack;
	final private String expr;
	public ExprCell(Sheet s, String expr) {
		super(s);
		this.expr = expr;
		stack = new Stack<>();
		try {
			exprTree = new ExprParser(expr).parse();
		}
		catch (ParsingException pe) {
			super.errorType = pe.getErrorType();
			exprTree = null;
			data = 0;
			return;
		}
		this.data = eval();
	}
	public int eval() {
		for (Node n : exprTree) {
			if (n.getType() == NodeType.Int)
				stack.push(((IntNode)n).getData());
			else if (n.getType() == NodeType.Operator) {
				int n1, n2;

				n2 = stack.pop();
				n1 = stack.pop();
				try {
					stack.push(((OpNode) n).calc(n1, n2));
				}
				catch (EvalException ee) {
					super.errorType = ee.getErrorType();
					return 0;
				}
			}
			else if (n.getType() == NodeType.Cell) {
				stack.push(((CellNode)n).getCell(super.sheet).data);
			}
		}
		try {
			return data = stack.pop();
		}
		catch (Exception e) {
			super.errorType = CellErrorType.Unknown;
			return 0;
		}
	}
	public String display() {
		return String.valueOf(data);
	}
	public String insideDisplay() {
		return expr;
	}
	public String fileDisplay() {
		return expr;
	}
	public void update() {
		data = eval();
	}
}
