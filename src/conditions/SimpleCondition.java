package conditions;

import content.EmailContent;
import operations.CompareOperation;

public class SimpleCondition implements Condition {

    private final CompareOperation operation;
    private final String fieldName;
    private final String value;

    public SimpleCondition(String fieldName, CompareOperation operation, String value) {
        this.fieldName = fieldName;
        this.operation = operation;
        this.value = value;
    }

    @Override
    public Boolean evaluate(EmailContent email) {
        return this.operation.evaluate(email.getField(fieldName), value);
    }
}
