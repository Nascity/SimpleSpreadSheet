package com.tripleS.expr;

import com.tripleS.sheet.CellErrorType;

class AddOpNode extends OpNode {
    public int calc(int n1, int n2) throws EvalException {
        int ret = n1 + n2;

        if (ret - n2 != n1)
            throw new EvalException(CellErrorType.OverUnderFlow);
        return ret;
    }
}

class SubOpNode extends OpNode {
    public int calc(int n1, int n2) throws EvalException {
        int ret = n1 - n2;

        if (ret + n2 != n1)
            throw new EvalException(CellErrorType.OverUnderFlow);
        return ret;
    }
}

class MulOpNode extends OpNode {
    public int calc(int n1, int n2) throws EvalException {
        int ret = n1 * n2;

        if (ret / n2 != n1)
            throw new EvalException(CellErrorType.OverUnderFlow);
        return ret;
    }
}

public class OpNodeBuilder {
    public static OpNode build(char ch) {
        return switch (ch) {
            case '+' -> new AddOpNode();
            case '-' -> new SubOpNode();
            case '*' -> new MulOpNode();
            default -> null;
        };
    }
}
