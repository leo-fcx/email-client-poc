import behaviors.Behavior;
import behaviors.MoveBehavior;
import com.google.common.base.Strings;
import content.EmailContent;
import content.FolderContent;
import rules.EmailRule;

import java.util.ArrayList;
import java.util.List;

public class EmailClient {

    private List<FolderContent> content;
    private FolderContent inbox;
    private FolderContent sent;
    private FolderContent trash;
    private List<EmailRule> rules;
    private List<EmailRule> chainedRules;
    private EmailRule defaultRule;

    public EmailClient() {
        this.inbox = new FolderContent("Inbox");
        this.sent = new FolderContent("Sent");
        this.trash = new FolderContent("Trash");
        this.content = new ArrayList<>();
        this.rules = new ArrayList<>();
        this.chainedRules = new ArrayList<>();

        this.initDefaultRule();
    }

    public List<FolderContent> getContent() {
        return content;
    }

    public FolderContent getInbox() {
        return inbox;
    }

    public void addRule(EmailRule rule) {
        this.rules.add(rule);

        this.chainRules();
    }

    public void addFolder(FolderContent folder) {
        this.content.add(folder);
    }

    public void receive(EmailContent email) {
        this.chainedRules.get(0).check(email);
    }

    public void print(String description) {
        this.printTitle(description);

        System.out.print((char) 27 + "[34m");
        System.out.println("    EMAIL CLIENT STATE:");
        System.out.println("");
        System.out.println("        RULES:");

        for (EmailRule rule : this.chainedRules) {
            rule.print();
        }

        System.out.print((char) 27 + "[34m");
        System.out.println("");
        System.out.println("        DEFAULT FOLDERS:");
        this.inbox.print();
        this.sent.print();
        this.trash.print();

        System.out.print((char) 27 + "[34m");
        System.out.println("");
        System.out.println("        CUSTOM FOLDERS:");

        for (FolderContent content : this.content) {
            content.print();
        }

        System.out.print((char) 27 + "[30m");
        System.out.println("");
        System.out.println("=========================================================================================");
        System.out.println("");
    }

    public void printTitle(String description) {
        System.out.print((char) 27 + "[30m");
        System.out.println("");
        System.out.println(description);
        System.out.println(Strings.repeat("-", description.length()));
        System.out.println("");
    }

    private void initDefaultRule() {
        Behavior moveToInbox = new MoveBehavior(this.inbox);
        this.defaultRule = new EmailRule("All emails got to Inbox folder by default.", moveToInbox);

        this.chainRules();
    }

    /**
     * Chains all rules that are going to be evaluated for each received email.
     *
     * INFO: This is the place where "Chain Of Responsibility" Design Pattern is used. See the implementation for Rule
     * Interface and EmailRule Class which implements it.
     *
     * TODO: Currently, the order in chaining is being applied according how items/riles were added to rules list. Need
     * to do some additional work to make it to consider some "priority" criteria.
     */
    private void chainRules() {
        //
        //
        this.chainedRules.clear();
        this.chainedRules.addAll(this.rules);
        this.chainedRules.add(this.defaultRule);

        int i;
        int limit = this.chainedRules.size() - 2; // 2 because: 1 index starts in 0 and last item is the default-rule

        for (i = 0; i < limit; i++) {
            this.chainedRules.get(i).setNext(this.rules.get(i + 1));
        }

        this.chainedRules.get(i).setNext(this.defaultRule);
    }
}
