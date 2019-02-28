package content;

import java.util.Map;

public class EmailContent implements Content {

    private Map<String, String> fields;

    public EmailContent(Map fields) {
        this.fields = fields;
    }

    public String getField(String key) {
        return this.fields.get(key);
    }

    @Override
    public void print() {
        System.out.print((char) 27 + "[36m");
        System.out.println("                .........................................................................");

        for (Map.Entry<String, String> entry : this.fields.entrySet()) {
            System.out.println("                " + entry.getKey().toUpperCase() + ": " + entry.getValue());
        }

        System.out.println("");
    }
}
