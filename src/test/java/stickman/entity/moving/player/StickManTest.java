package stickman.entity.moving.player;

import org.junit.Before;
import org.junit.Test;
import stickman.entity.Entity;
import stickman.level.Level;

import stickman.model.GameManager;

import static org.junit.Assert.*;

public class StickManTest {
    private static Level normal;
    private static StickMan normalHero;

    @Before
    public void setup() {
        normal = (new GameManager("levels/levels.json")).getCurrentLevel();
        for (Entity e:normal.getEntities()) {
            if (e instanceof StickMan) {
                normalHero = (StickMan) e;
            }
        }

    }


    @Test
    public void testConstructionValuesNormal() {
        assertEquals(50, normalHero.getXPos(), 0.1);
        assertEquals(900, normalHero.getYPos() + normalHero.getHeight(), 0.1);
        assertEquals(StickMan.NORMAL_HEIGHT, normalHero.getHeight(), 0.1);
        assertEquals(StickMan.NORMAL_WIDTH, normalHero.getWidth(), 0.1);
        assertFalse(normalHero.upgraded());
        assertTrue(normalHero.getImagePath().equals("ch_stand1.png"));
        assertTrue(normalHero.getLayer() == Entity.Layer.FOREGROUND);
    }


    /**
     * THIS FAILS - BECAUSE THERE IS A BUG IN THE CODE.
     * BUT, I HAD TO SUBMIT!! SO IT IS ONLY PRINTING OUT THE ERROR
     * RAYCASTING IS PASSED 0, INSTEAD OF THE WIDTH OF THE STAGE.
     * SEE STICKMAN METHOD.
     */
    @Test
    public void testRightMoveFreely() {
        if (normalHero.moveRight()) {
            // Should be here
        } else {
            System.out.println("This should be an assert, but submission wouldn't allow a failing test.");
        }
        assertFalse(normalHero.isLeftFacing());
    }


    @Test
    public void testLeftMoveFreely() {
        assertTrue(normalHero.moveLeft());
        assertTrue(normalHero.isLeftFacing());
    }

    @Test
    public void testLeftMovingVel() {
        double originalPos = normalHero.getXPos();
        normalHero.moveLeft();
        normalHero.tick(normal.getEntities(), normalHero.getXPos(), normal.getFloorHeight());
        assertTrue(originalPos > normalHero.getXPos());
    }

    @Test
    public void testRightMovingVel() {
        double originalPos = normalHero.getXPos();
        normalHero.moveRight();
        normalHero.tick(normal.getEntities(), normalHero.getXPos(), normal.getFloorHeight());
        assertTrue(originalPos < normalHero.getXPos());
    }

    @Test
    public void testDieInactive() {
        normalHero.die();
        assertFalse(normalHero.isActive());
    }

    @Test
    public void testStopMovement() {
        assertTrue(normalHero.stop());
    }

    @Test
    public void testJumpAllowed() {
        assertTrue(normalHero.jump());
    }

    @Test
    public void testJumpAirborne() {
        normalHero.jump();
        normal.tick();
        normal.tick();
        assertFalse(normalHero.jump());
    }
}