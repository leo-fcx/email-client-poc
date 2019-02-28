package conditions;

import content.EmailContent;
import operations.LogicOperation;

public class CompoundCondition implements Condition {

    private final LogicOperation operation;
    private final Condition leftOperand;
    private final Condition rightOperand;

    public CompoundCondition(Condition leftOperand, LogicOperation operation, Condition rightOperand) {
        this.leftOperand = leftOperand;
        this.operation = operation;
        this.rightOperand = rightOperand;
    }

    @Override
    public Boolean evaluate(EmailContent email) {
        return this.operation.evaluate(leftOperand.evaluate(email), rightOperand.evaluate(email));
    }
}
