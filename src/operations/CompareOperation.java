package operations;

import operators.Operator;

import static operators.CompareOperator.EQUAL;

public class CompareOperation implements Operation<String> {

    private final Operator operator;

    public CompareOperation(Operator operator) {
        this.operator = operator;
    }

    @Override
    public Boolean evaluate(String left, String right) {

        if (this.operator.equals(EQUAL)) {
            return left.equals(right);
        }
        // TODO: Implement the other compare cases

        // TODO: We may need to throw exception for unsupported cases. Returning false for now
        return false;
    }
}
