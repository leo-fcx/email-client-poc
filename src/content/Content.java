package content;

/**
 * This Content interface and the classes that implement it are part of a "Composite" Design Pattern implementation.
 * Note that FolderContent is compound of other Content objects which could be either EmailContent or FolderContent
 * objects.
 */
public interface Content {

    void print();
}
