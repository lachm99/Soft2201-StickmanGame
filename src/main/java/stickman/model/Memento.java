package stickman.model;

import stickman.level.Level;

public class Memento {

    private Level savedLevel;
    private int savedLevelIndex;
    private int savedLivesRemaining;
    private int savedCumulativeScore;

    public Memento(GameEngine originator) {
        this.savedLevel = originator.getCurrentLevel().deepCopy();
        this.savedLevelIndex = originator.getLevelIndex();
        this.savedLivesRemaining = originator.getLivesRemaining();
        this.savedCumulativeScore = originator.getCumulativeScore();
    }


    public Level retrieveSavedLevel() {
        return savedLevel;
    }

    public int retrieveSavedLevelIndex() {
        return this.savedLevelIndex;
    }

    public int retrieveSavedLivesRemaining() {
        return this.savedLivesRemaining;
    }

    public int retrieveSavedCumulativeScore() {
        return this.savedCumulativeScore;
    }
}
