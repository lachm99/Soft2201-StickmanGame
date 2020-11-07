package stickman.entity.still;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import stickman.level.Level;
import stickman.level.LevelBuilderImpl;


public class FlagTest  {
    private static Flag f;

    @BeforeAll
    public static void setup() {
        f = new Flag(0,0);
    }

    @Test
    public void testConstructor() {
        Flag f = new Flag(0, 0);
        assertNotNull(f);
    }

    @Test
    public void testCoords() {
        assertEquals(0, f.getXPos());
        assertEquals(0, f.getYPos());
    }


    @Test
    public void testSolidity() {
        assertFalse(f.isSolid());
    }

    @Test
    public void testInteractionFlag() {
        Level l = LevelBuilderImpl.generateFromFile("levels/test/flagtest.json", null);
        l.tick();
        assertTrue(l.getEntities().stream().anyMatch(Win.class::isInstance));
    }

}
