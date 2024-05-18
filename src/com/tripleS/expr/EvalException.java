package com.tripleS.expr;

import com.tripleS.sheet.CellErrorType;

public class EvalException extends Exception {
	final private CellErrorType errorType;
	public EvalException() {
		this(CellErrorType.Unknown);
	}
	public EvalException(CellErrorType errorType) {
		super();
		this.errorType = errorType;
	}
	public CellErrorType getErrorType() {
		return errorType;
	}
}
