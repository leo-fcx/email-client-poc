package conditions;

import content.EmailContent;

/**
 * This Condition interface and the classes that implement it are part of a "Composite" Design Pattern implementation.
 *
 * Note that CompoundCondition is compound of other two (2) Condition objects which could be either simpleCondition or
 * CompoundCondition objects.
 */
public interface Condition {

    Boolean evaluate(EmailContent emailContent);
}
