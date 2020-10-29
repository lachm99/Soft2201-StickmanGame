package stickman.entity.still;

import org.junit.jupiter.api.BeforeAll;

/**
 * Mushroom object that the player can pick up to get the ability to shoot.
 */
public class MushroomTest {
    private Mushroom m;

    @BeforeAll
    public void setup() {
        this.m = new Mushroom(0,0);
    }


}
