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
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import rules.EmailRule;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EmailClientTest {

    private EmailClient emailClient;

    @Before
    public void before() {
        emailClient = new EmailClient();
    }

    @After
    public void After() {
        emailClient = null;
    }

    @Test
    public void shouldCreateInstanceSuccessfully() {
        assertNotNull(emailClient);
    }

    @Test
    public void shouldHaveContent() {
        assertNotNull(emailClient.getContent());
    }

    @Test
    public void shouldReceiveEmailContentToInboxAsDefaultBehavior() {
        EmailContent email = new EmailContent(new HashMap());
        FolderContent inbox = emailClient.getInbox();

        assertEquals(0, inbox.getContent().size());

        emailClient.receive(email);

        assertEquals(1, inbox.getContent().size());
    }

    @Test
    public void shouldMoveEmailToSpamFolderAccordingToSimpleConditionInRuleDefinition() throws Exception {

        // Creating Email
        Map<String, String> emailData = new HashMap<>();
        emailData.put("from", "spam@email.com");
        EmailContent email = new EmailContent(emailData);

        // Creating Folder
        FolderContent spam = new FolderContent("Spam");

        emailClient.addFolder(spam);

        assertEquals(0, spam.getContent().size());

        // Creating Rule
        Behavior moveToSpam = new MoveBehavior(spam);
        CompareOperation equalOperator = new CompareOperation(CompareOperator.EQUAL);
        Condition condition = new SimpleCondition("from", equalOperator, "spam@email.com");
        EmailRule spamRule = new EmailRule("Move emails from 'spam@email.com' to Spam folder.", moveToSpam, condition);

        emailClient.addRule(spamRule);
        emailClient.receive(email);

        assertEquals(1, spam.getContent().size());
    }

    @Test
    public void shouldMoveEmailToSpamFolderAccordingToCompoundConditionInRuleDefinition() throws Exception {

        // Creating Email
        Map<String, String> emailData = new HashMap<>();
        emailData.put("from", "spam@email.com");
        emailData.put("subject", "Black Friday Offer");
        EmailContent email = new EmailContent(emailData);

        // Creating Folder
        FolderContent spam = new FolderContent("Spam");

        emailClient.addFolder(spam);

        assertEquals(0, spam.getContent().size());

        // Creating Operators
        CompareOperation equalOperator = new CompareOperation(CompareOperator.EQUAL);
        LogicOperation andOperator = new LogicOperation(LogicOperator.AND);

        // Creating Rule
        Behavior moveToSpam = new MoveBehavior(spam);
        Condition condition1 = new SimpleCondition("from", equalOperator, "spam@email.com");
        Condition condition2 = new SimpleCondition("subject", equalOperator, "Black Friday Offer");
        Condition compoundCondition = new CompoundCondition(condition1, andOperator, condition2);
        EmailRule spamRule = new EmailRule("Move Black Friday emails to Spam folder.", moveToSpam, compoundCondition);

        emailClient.addRule(spamRule);
        emailClient.receive(email);

        assertEquals(1, spam.getContent().size());
    }
}