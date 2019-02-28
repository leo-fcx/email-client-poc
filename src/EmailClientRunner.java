import behaviors.Behavior;
import behaviors.MoveBehavior;
import conditions.CompoundCondition;
import conditions.Condition;
import conditions.SimpleCondition;
import content.EmailContent;
import content.FolderContent;
import operations.CompareOperation;
import operations.LogicOperation;
import operators.CompareOperator;
import operators.LogicOperator;
import rules.EmailRule;

import java.util.HashMap;
import java.util.Map;

public class EmailClientRunner {

    public static void main(String[] args) {

        EmailClient emailClient;

        System.out.println("#########################################################################################");
        System.out.println("#                             EMAIL CLIENT - DESIGN PATTERNS                            #");
        System.out.println("#########################################################################################");

        emailClient = new EmailClient();

        emailClient.print("Starting App:");

        // Creating Folder
        FolderContent spam = new FolderContent("Spam");

        emailClient.addFolder(spam);
        emailClient.print("Creating SPAM folder:");

        // Creating FIRST Email
        Map<String, String> firstEmailData = new HashMap<>();
        firstEmailData.put("from", "sombody@email.com");
        firstEmailData.put("subject", "Hello world!");
        EmailContent firstEmail = new EmailContent(firstEmailData);

        emailClient.printTitle("FIRST email created:");
        firstEmail.print();

        emailClient.receive(firstEmail);
        emailClient.print("Receiving FIRST email:");

        // Creating Operators
        CompareOperation equalOperator = new CompareOperation(CompareOperator.EQUAL);
        LogicOperation andOperator = new LogicOperation(LogicOperator.AND);

        // Creating moveToSpam Behavior
        Behavior moveToSpam = new MoveBehavior(spam);

        // Creating FIRST Rule
        Condition condition = new SimpleCondition("from", equalOperator, "unknown@email.com");
        EmailRule firstSpamRule = new EmailRule("Move emails from 'unknown@email.com' to Spam folder.", moveToSpam, condition);

        emailClient.addRule(firstSpamRule);
        emailClient.print("Adding FIRST rule for SPAM folder:");

        // Creating SECOND Rule
        Condition condition1 = new SimpleCondition("from", equalOperator, "no-reply@email.com");
        Condition condition2 = new SimpleCondition("subject", equalOperator, "Black Friday Offer");
        Condition compoundCondition = new CompoundCondition(condition1, andOperator, condition2);
        EmailRule secondSpamRule = new EmailRule("Move Black Friday emails from no-reply@email.com to Spam folder.", moveToSpam, compoundCondition);

        emailClient.addRule(secondSpamRule);
        emailClient.print("Adding SECOND rule for SPAM folder:");

        // Creating SECOND Email
        Map<String, String> secondEmailData = new HashMap<>();
        secondEmailData.put("from", "unknown@email.com");
        secondEmailData.put("subject", "[IMPORTANT] Please reply");
        EmailContent secondEmail = new EmailContent(secondEmailData);

        emailClient.printTitle("SECOND email created:");
        secondEmail.print();

        emailClient.receive(secondEmail);
        emailClient.print("Receiving SECOND email:");

        // Creating THIRD Email
        Map<String, String> thirdEmailData = new HashMap<>();
        thirdEmailData.put("from", "no-reply@email.com");
        thirdEmailData.put("subject", "Black Friday Offer");
        EmailContent thirdEmail = new EmailContent(thirdEmailData);

        emailClient.printTitle("THIRD email created:");
        thirdEmail.print();

        emailClient.receive(thirdEmail);
        emailClient.print("Receiving THIRD email:");
    }
}
