package rules;

import content.EmailContent;

/**
 * INFO: This rule interface implements the "Chain Of Responsibility" Design Pattern. Note the .setNext() method which
 * allows the chaining of different Rules.
 */
public interface Rule {
    void setNext(Rule next);

    void check(EmailContent email);
}
