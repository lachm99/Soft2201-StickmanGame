package stickman.entity.still;



import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class WinTest {

    private static Win w;

    @Before
    public void setup() {
        w = new Win(0,0);
    }

    @Test
    public void testConstructor() {
        Win w = new Win(0, 0);
        assertNotNull(w);
    }

    @Test
    public void testCoords() {
        assertEquals(0, w.getXPos(), 0.1);
        assertEquals(0, w.getYPos(), 0.1);
    }

    @Test
    public void testSolidity() {
        assertFalse(w.isSolid());
    }

}
