package stickman.model;

/**
 * Idiomatic implementation of memento originator
 */

public interface Originator {
    /**
     * Construct a snapshot of the current state
     * @return A new Memento
     */
    Memento makeSnapshot();

    /**
     * Restore the originator to the state described by:
     * @param snapshot - Some formerly constructed snapshot.
     */
    void restoreSnapshot(Memento snapshot);
}

