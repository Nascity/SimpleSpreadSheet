package com.tripleS.parse;

import com.tripleS.sheet.*;

public class ParsingException extends Exception {
	final private CellErrorType errorType;
	final private int charIndex;
	public ParsingException() {
		this(CellErrorType.Unknown, 0);
	}
	public ParsingException(CellErrorType errorType, int charIndex) {
		super();
		this.errorType = errorType;
		this.charIndex = charIndex;
	}
	public CellErrorType getErrorType() {
		return errorType;
	}
	public int getErrorIndex() {
		return charIndex;
	}
}
