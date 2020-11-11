package stickman.entity.moving.player;

import org.junit.Before;
import org.junit.Test;
import stickman.entity.Entity;
import stickman.level.Level;

import stickman.level.LevelBuilderImpl;

import static org.junit.Assert.*;

public class StickManTest {
    private static Level normal;
    private static StickMan normalHero;
    private static Level large;
    private static StickMan largeHero;
    private static Level obstructed;
    private static StickMan obstructedHero;


    @Before
    public void setup() {
        normal = LevelBuilderImpl.generateFromFile("levels/test/normalstickman.json", null);
        for (Entity e:normal.getEntities()) {
            if (e instanceof StickMan) {
                normalHero = (StickMan) e;
            }
        }
        large = LevelBuilderImpl.generateFromFile("levels/test/largestickman.json", null);
        for (Entity e:large.getEntities()) {
            if (e instanceof StickMan) {
                largeHero = (StickMan) e;
            }
        }
        obstructed = LevelBuilderImpl.generateFromFile("levels/test/obstructed.json", null);
        for (Entity e:obstructed.getEntities()) {
            if (e instanceof StickMan) {
                obstructedHero = (StickMan) e;
            }
        }
    }


    @Test
    public void testConstructionValuesNormal() {
        assertEquals(50, normalHero.getXPos(), 0.1);
        assertEquals(350, normalHero.getYPos() + normalHero.getHeight(), 0.1);
        assertEquals(StickMan.NORMAL_HEIGHT, normalHero.getHeight(), 0.1);
        assertEquals(StickMan.NORMAL_WIDTH, normalHero.getWidth(), 0.1);
        assertFalse(normalHero.upgraded());
        assertTrue(normalHero.getImagePath().equals("ch_stand1.png"));
        assertTrue(normalHero.getLayer() == Entity.Layer.FOREGROUND);
    }

    @Test
    public void testConstructionValuesLarge() {
        assertEquals(50, largeHero.getXPos(), 0.1);
        assertEquals(350, largeHero.getYPos() + largeHero.getHeight(), 0.1);
        assertEquals(StickMan.LARGE_HEIGHT, largeHero.getHeight(), 0.1);
        assertFalse(largeHero.upgraded());
    }
    @Test
    public void testConstructionValuesInvalidSize() {
        Level invalid = LevelBuilderImpl.generateFromFile("levels/test/invalidsize.json", null);
        StickMan inv;
        for (Entity e:invalid.getEntities()) {
            if (e instanceof StickMan) {
                 inv = (StickMan) e;
                assertEquals(0, inv.getHeight(), 0.1);
                assertEquals(0, inv.getWidth(), 0.1);
            }
        }
    }


    @Test
    public void testRightMoveFreely() {
        assertTrue(normalHero.moveRight());
        assertFalse(normalHero.isLeftFacing());
    }


    @Test
    public void testLeftMoveFreely() {
        assertTrue(normalHero.moveLeft());
        assertTrue(normalHero.isLeftFacing());
    }

    @Test
    public void testRightMoveObstructed() {
        assertFalse(obstructedHero.moveRight());
        assertFalse(obstructedHero.isLeftFacing());
    }


    @Test
    public void testLeftMoveObstructed() {
        assertFalse(obstructedHero.moveLeft());
        assertTrue(obstructedHero.isLeftFacing());
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
        largeHero.jump();
        large.tick();
        large.tick();
        assertFalse(largeHero.jump());
    }
}