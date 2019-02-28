package operations;

import operators.LogicOperator;
import operators.Operator;

public class LogicOperation implements Operation<Boolean> {

    private final Operator operator;

    public LogicOperation(Operator operator) {
        this.operator = operator;
    }

    @Override
    public Boolean evaluate(Boolean leftOperand, Boolean rightOperand) {
        if (this.operator.equals(LogicOperator.AND)) {
            return leftOperand && rightOperand;
        }
        // TODO: Implement the other compare cases

        // TODO: We may need to throw exception for unsupported cases. Returning false for now
        return false;
    }
}
