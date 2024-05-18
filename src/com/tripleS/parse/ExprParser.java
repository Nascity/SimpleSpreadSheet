package com.tripleS.parse;

import com.tripleS.expr.*;
import com.tripleS.sheet.CellErrorType;
import com.tripleS.window.Main;

import java.util.*;

public class ExprParser {
	private final String expr;
	private char token;
	private int ptr;
	private final LinkedList<Node> exprTree;
	public ExprParser(String expr) {
		this.expr = expr;
		token = 1;
		ptr = 0;
		exprTree = new LinkedList<>();
	}
	public LinkedList<Node> parse() throws ParsingException {
		E();
		return exprTree;
	}
	private void throwError() throws ParsingException {
		throw new ParsingException(CellErrorType.SyntaxError, ptr);
	}
	private void nextToken() throws ParsingException {
		if (token == 0)
			throwError();
		if (ptr >= expr.length()) {
			token = 0;
			return;
		}
		token = expr.charAt(ptr++);
	}
	// E -> TE'
	private void E() throws ParsingException {
		nextToken();
		if (token != '=')
			throwError();
		nextToken();
		T();
		Ep();
	}
	// E' -> (* | / | %)TE' | e
	private void Ep() throws ParsingException {
		OpNode op;

		if (token == '*' || token == '/' || token == '%') {
			op = OpNodeBuilder.build(token);
			nextToken();
			T();
			exprTree.add(op);
			Ep();
			return;
		}
		else if (token == 0)
			return;
		throwError();
	}
	// T -> ST'
	private void T() throws ParsingException {
		S();
		Tp();
	}
	// T' -> (+ | -)ST' | e
	private void Tp() throws ParsingException {
		OpNode op;

		if (token == '+' || token == '-') {
			op = OpNodeBuilder.build(token);
			nextToken();
			S();
			exprTree.add(op);
			Tp();
			return;
		}
		else if (token == 0)
			return;
		throwError();
	}
	// S -> (E) | Cell | Integer
	private void S() throws ParsingException {
		if (token == '(') {
			nextToken();
			E();
			if (token != ')')
				throwError();
			return;
		}
		else if (token >= 'A' && token <= 'Z') {
			exprTree.add(Cell());
			return;
		}
		else if (token >= '0' && token <= '9') {
			exprTree.add(new IntNode(Number()));
			return;
		}
		throwError();
	}
	private CellNode Cell() throws ParsingException {
		int row, col;

		col = token - 'A';
		nextToken();
		row = Number() - 1;
		if (row > Main.TABLE_HEIGHT)
			throwError();
		return new CellNode(row, col);
	}
	private int Number() throws ParsingException {
		int acc = 0;
		int overflowChecker;

		while (token >= '0' && token <= '9') {
			overflowChecker = acc;
			acc *= 10;
			if (acc / 10 != overflowChecker)
				throwError();
			acc += token - '0';
			nextToken();
		}
		return acc;
	}
}
