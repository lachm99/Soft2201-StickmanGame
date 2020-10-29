package stickman.entity.still;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class WinTest {

    private static Win w;

    @BeforeAll
    public static void setup() {
        w = new Win(0,0);
    }

    @Test
    public void testConstructor() {
        Win w = new Win(0, 0);
        assertNotNull(w);
    }

    @Test
    public void testCoords() {
        assertEquals(0, w.getXPos());
        assertEquals(0, w.getYPos());
    }

    @Test
    public void testSolidity() {
        assertFalse(w.isSolid());
    }

}
