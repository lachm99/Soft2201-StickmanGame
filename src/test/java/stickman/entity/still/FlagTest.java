package stickman.entity.still;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import stickman.level.Level;
import stickman.level.LevelBuilderImpl;



public class FlagTest  {
    private static Flag f;

    @Before
    public void setup() {
        f = new Flag(0,0);
    }

    @Test
    public void testConstructor() {
        Flag f = new Flag(0, 0);
        assertNotNull(f);
    }

    @Test
    public void testCoords() {
        assertEquals(0, f.getXPos(), 0.1);
        assertEquals(0, f.getYPos(), 0.1);
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
