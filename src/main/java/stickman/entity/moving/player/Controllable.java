package stickman.entity.moving.player;

import stickman.entity.moving.MovingEntity;
import stickman.entity.moving.other.Projectile;
import stickman.level.Level;
import stickman.level.LevelManager;

/**
 * Interface describing the behaviours of a Player controlled character.
 */
public interface Controllable extends MovingEntity {

    /**
     * Moves the entity left.
     * @return Whether entity moves left
     */
    boolean moveLeft();

    /**
     * Moves the entity right.
     * @return Whether entity moves right
     */
    boolean moveRight();

    /**
     * Makes the entity jump.
     * @return Whether entity jumps
     */
    boolean jump();

    /**
     * Stops all horizontal movement.
     * @return Whether entity stops
     */
    boolean stop();

    /**
     * Upgrades the hero's ability (e.g. with a mushroom).
     */
    void upgrade();


    /**
     * Awards the hero with
     * @param amount of points
     */
    void awardPoints(int amount);

    /**
     * Returns true if the player is facing left.
     * @return Whether player is left facing
     */
    boolean isLeftFacing();

    /**
     * Returns true if the player has collected a mushroom.
     * @return Whether player has upgraded
     */
    boolean upgraded();

    /**
     * Called when the player reaches the flag.
     */
    void win();

    Controllable copyToLevel(Level level);
}
