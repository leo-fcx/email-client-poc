package rules;

import behaviors.Behavior;
import conditions.Condition;
import content.EmailContent;

public class EmailRule implements Rule {

    private String name;
    private Rule next = null;
    private Condition condition = null;
    private Behavior action;

    public EmailRule(String name, Behavior action) {
        this.name = name;
        this.action = action;
    }

    public EmailRule(String name, Behavior action, Condition condition) {
        this.name = name;
        this.condition = condition;
        this.action = action;
    }

    public String getName() {
        return name;
    }

    @Override
    public void setNext(Rule next) {
        this.next = next;
    }

    /**
     * Checks whether the email received matches with the condition from the rule. If so, action is executed.
     *
     * INFO: This is the entry point where "Command" Design Pattern is being used by executing action.apply()
     * method. Note also that condition.evaluate() is another entry point to use implementation of this pattern to
     * evaluate specific condition.
     *
     * @param email
     */
    @Override
    public void check(EmailContent email) {
        if (this.condition == null || this.condition.evaluate(email)) {
            this.action.apply(email);
        } else if (next != null) {
            next.check(email);
        }
    }

    public void print() {
        System.out.print((char) 27 + "[31m");
        System.out.println("            + " + this.getName());
    }

}
