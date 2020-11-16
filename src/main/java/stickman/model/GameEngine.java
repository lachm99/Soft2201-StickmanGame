package stickman.model;

import stickman.level.Level;

/**
 * Interface for the GameEngine. Describes the necessary behaviour
 * for running the game.
 */
public interface GameEngine extends Originator, Caretaker{

    /**
     * Gets the current running level object
     * @return The current level
     */
    Level getCurrentLevel();


    /**
     * Gets the current running level INDEX - (The stage number)
     * @return the stage index.
     */
    int getLevelIndex();

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
     * Find the number of lives the player has.
     * @return
     */
    int getLivesRemaining();

    /**
     * Win the current level. Will check for overall victory
     */
    void winLevel();

    /**
     * Lose the current level. Will check for overall loss.
     */
    void loseLevel();

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


    /**
     * Restarts the current level.
     */
    void reset();

    /**
     * Starts the level with index
     * @param index; from the filename with the specified index in the config file.
     */
    void startLevel(int index);
}
