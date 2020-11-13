package stickman.model;

import stickman.level.Level;

/**
 * Interface for the GameEngine. Describes the necessary behaviour
 * for running the game.
 */
public interface GameEngine extends Originator {

    /**
     * Gets the current running level.
     * @return The current level
     */
    Level getCurrentLevel();


    /**
     * Gets the current cumulative score for all previous levels
     * @return Cumulative score.
     */
    int getCumulativeScore();

    /**
     * Adjusts the cumulative score by some int
     * @param amount
     */
    void adjustCumulativeScore(int amount);

    /**
     * Find the number of lives still remaining before the game is lost entirely.
     * @return
     */
    int getExtraLivesRemaining();

    /**
     * Handle the consequences of a life being lost.
     */
    int loseALife();

    /** Handle the consequences of a single stage being won
     *
     */
    void winLevel();

    /**
     * End the game -
     * @param gameWon determines whether game is over due to all stages completed or all lives spent.
     */
    void finishGame(boolean gameWon);

    int getGameState();

    /**
     * Makes the player jump.
     * @return Whether the input had an effect
     */
    boolean jump();

    /**
     * Moves the player left.
     * @return Whether the input had an effect
     */
    boolean moveLeft();

    /**
     * Moves the player right.
     * @return Whether the input had an effect
     */
    boolean moveRight();

    /**
     * Stops player movement.
     * @return Whether the input had an effect
     */
    boolean stopMoving();

    /**
     * Updates the scene every frame, returns the gamestate.
     */
    void tick();

    /**
     * Makes the player shoot.
     */
    void shoot();

    void startLevel(int index);

    /**
     * Restarts the level.
     */
    void reset();


    /**
     * Requests a save - will call makeSnapshot and store the snapshot somewhere.
     */
    void save();


    /**
     * Requests a load - will pass in a saved snapshot to restoreSnapshot.
     */
    void load();



    /**
     *
     * @return
     */
    Memento makeSnapshot();

    void restoreSnapshot(Memento m);

}
