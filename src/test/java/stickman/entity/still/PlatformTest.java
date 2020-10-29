package stickman.entity.still;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlatformTest {
    private static Platform p;

    @BeforeAll
    public static void setup() {
        p = new Platform(0,0);
    }

    @Test
    public void testConstructor() {
        Platform p = new Platform(0, 0);
        assertNotNull(p);
    }

    @Test
    public void testCoords() {
        assertEquals(0, p.getXPos());
        assertEquals(0, p.getYPos());
    }

    @Test
    public void testSolidity() {
        assertTrue(p.isSolid());
    }

}
