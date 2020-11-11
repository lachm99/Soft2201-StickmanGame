package stickman.entity.still;



import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import stickman.entity.moving.player.StickMan;

/**
 * Mushroom object that the player can pick up to get the ability to shoot.
 */
public class MushroomTest {
    private static Mushroom m;

    @Before
    public void setup() {
        m = new Mushroom(0,0);
    }

    @Test
    public void testConstructor() {
        Mushroom m = new Mushroom(0, 0);
        assertNotNull(m);
    }

    @Test
    public void testCoords() {
        assertEquals(0, m.getXPos(), 0.1);
        assertEquals(0, m.getYPos(), 0.1);
    }

    @Test
    public void testSolidity() {
        assertFalse(m.isSolid());
    }

    @Test
    public void testActive() {
        assertTrue(m.isActive());
    }

    @Test
    public void testFirstInteractionMushroom() {
        StickMan h = new StickMan(0, 0, "large", null);
        Mushroom interact = new Mushroom(0,0);
        interact.interact(h);
        assertFalse(interact.isActive());
    }

    @Test
    public void testSecondInteractionMushroom() {
        StickMan h = new StickMan(0, 0, "large", null);
        Mushroom interact = new Mushroom(0,0);
        interact.interact(h);
        assertFalse(interact.isActive());
        interact.interact(h);
        assertFalse(interact.isActive());
    }



}
