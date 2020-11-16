package stickman.model;

public interface Caretaker {
    /**
     * Called on keypress "l" - will tell originator to make a
     * snapshot and save it to the caretaker.
     */
    void save();

    /**
     * Called on keypress "l" - will tell the originator to restore a
     * snapshot saved in the caretaker.
     */
    void load();
}
