package content;

import java.util.ArrayList;
import java.util.List;

public class FolderContent implements Content {

    private String name;
    private List<Content> content;

    public FolderContent(String name) {
        this.name = name;
        this.content = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Content> getContent() {
        return content;
    }

    public void addChild(Content content) {
        this.content.add(content);
    }

    @Override
    public void print() {
        System.out.print((char) 27 + "[32m");
        System.out.println("            + " + this.getName() + " (" + this.getContent().size() + ")");

        for (Content content : this.content) {
            content.print();
        }
    }
}
