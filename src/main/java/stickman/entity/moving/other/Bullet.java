package stickman.entity.moving.other;

import stickman.entity.Entity;
import stickman.entity.moving.MovingObject;
import stickman.level.Level;

import java.util.List;

/**
 * Bullet object that the player can shoot to kill slimes.
 */
public class Bullet extends MovingObject implements Projectile {

    /**
     * The x-velocity of all bullets.
     */
    public static final double BULLET_SPEED = 2;

    /**
     * The width of all bullets. (Static to help testing)
     */
    public static final double BULLET_WIDTH = 10;

    /**
     * The height of all bullets. (Static to help testing)
     */
    public static final double BULLET_HEIGHT = 10;

    /**
     * The Level that this bullet belongs to - important so that entities it kills can have their
     * points added to the level score..
     */
    private final Level level;

    /**
     * Construct a new bullet
     * @param x - Coordinate to start the bullet
     * @param y - Coordinate to start the bullet
     * @param left - Is the bullet travelling leftward?
     * @param l - The level to attribute any scored points to.
     */
    public Bullet(double x, double y, boolean left, Level l) {
        super("bullet.png", x, y, BULLET_HEIGHT, BULLET_WIDTH, Layer.FOREGROUND);
        this.xVelocity = left ? -BULLET_SPEED : BULLET_SPEED;
        this.yVelocity = 0;
        this.level = l;
    }

    @Override
    public void tick(List<Entity> entities, double heroX, double floorHeight) {
        this.xPos += this.xVelocity;
        this.yPos += this.yVelocity;
    }


    /**
     * Typical overridden entity copy method
     * @return ONLY a shallow copy of this projectile - which shares the same level of origin.
     */
    @Override
    public Entity copy() {
        return this.copyToLevel(this.level);
    }

    /**
     * A DEEP copy of this Projectile, provided:
     * @param copyOwner - The Level that the new projectile belongs to (Important for point scoring)
     * @return The deep copy belonging to the copyOwner.
     */
    @Override
    public Projectile copyToLevel(Level copyOwner) {
        Bullet b = new Bullet(this.getXPos(), this.getYPos(), (this.xVelocity < 0), copyOwner);
        return b;
    }

    @Override
    public boolean isSolid() {
        return false;
    }

    @Override
    public void stop() {
        this.active = false;
    }


    @Override
    public Level getLevel() {
        return this.level;
    }
}
