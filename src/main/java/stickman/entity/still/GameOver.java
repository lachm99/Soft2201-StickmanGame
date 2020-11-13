package stickman.entity.still;

import stickman.entity.GameObject;

/**
 * The GameOver Message displayed after Losing all lives.
 */
public class GameOver extends GameObject {

    /**
     * Constructs a new GameOver object.
     * @param x The x-coordinate
     * @param y The y-coordinate
     */
    public GameOver(double x, double y) {
        super("defeat.png", x, y, 400, 400, Layer.EFFECT);
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}
