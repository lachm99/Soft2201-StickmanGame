package stickman.model;

public interface Originator {
    Memento makeSnapshot();
    void restoreSnapshot(Memento m);
}

