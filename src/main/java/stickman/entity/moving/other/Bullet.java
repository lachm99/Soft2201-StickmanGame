package stickman.entity.moving.other;

import stickman.entity.Entity;
import stickman.entity.moving.MovingEntity;
import stickman.entity.moving.MovingObject;
import stickman.entity.moving.player.Controllable;
import stickman.entity.moving.player.StickMan;

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
     * The Controllable entity that shot the bullet.
     */
    private final Controllable originator;

    /**
     * Constructs a bullet object.
     * @param shooter the originator of the shot.
     */
    public Bullet(Controllable shooter) {
        super("bullet.png", shooter.getXPos(), shooter.getYPos() + shooter.getHeight()/3, BULLET_HEIGHT, BULLET_WIDTH, Layer.FOREGROUND);
        this.xVelocity = shooter.isLeftFacing() ? -BULLET_SPEED : BULLET_SPEED;
        this.xPos = shooter.isLeftFacing() ? this.xPos : this.xPos + shooter.getWidth();
        this.yVelocity = 0;
        this.originator = shooter;
    }

    @Override
    public void tick(List<Entity> entities, double heroX, double floorHeight) {
        this.xPos += this.xVelocity;
        this.yPos += this.yVelocity;
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
    public Controllable getOriginator() {
        return this.originator;
    }
}
