package stickman.entity.still;



import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlatformTest {
    private static Platform p;

    @Before
    public void setup() {
        p = new Platform(0,0);
    }

    @Test
    public void testConstructor() {
        Platform p = new Platform(0, 0);
        assertNotNull(p);
    }

    @Test
    public void testCoords() {
        assertEquals(0, p.getXPos(), 0.1);
        assertEquals(0, p.getYPos(), 0.1);
    }

    @Test
    public void testSolidity() {
        assertTrue(p.isSolid());
    }

}
