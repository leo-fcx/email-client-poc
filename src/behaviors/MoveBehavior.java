package behaviors;

import content.Content;
import content.FolderContent;

public class MoveBehavior implements Behavior {

    private final FolderContent targetFolder;

    public MoveBehavior(FolderContent folder) {
        this.targetFolder = folder;
    }

    @Override
    public void apply(Content email) {
        this.targetFolder.addChild(email);
    }
}
