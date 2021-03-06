package stickman.entity.moving.enemy;

import org.junit.*;
import stickman.entity.Entity;
import stickman.level.Level;
import stickman.level.LevelBuilderImpl;

import static org.junit.Assert.assertTrue;

public class EnemiesTest {
    private static Level level;
    private static Slime dumbSlime;

    @Before
    public void setup() {
        level = LevelBuilderImpl.generateFromFile("levels/test/obstructed.json", null);
        for (Entity e : level.getEntities()) {
            if (e instanceof Slime) {
                dumbSlime = (Slime) e;
            }
        }
    }

    @Test
    public void testDumbTurnAroundBothWays() {
        double originalXPos = dumbSlime.getXPos();
        level.tick();
        assertTrue(dumbSlime.getXPos() > originalXPos);
        level.tick();
        level.tick();
        level.tick();
        assertTrue(dumbSlime.getXPos() < originalXPos);
        level.tick();
        level.tick();
        assertTrue(dumbSlime.getXPos() == originalXPos);
    }


    @Test
    public void testFollowFromRight() {
        Level slimeOnRightLevel = LevelBuilderImpl.generateFromFile("levels/test/slimeOnRight.json", null);
        Slime followFromRight = null;
        for (Entity e : slimeOnRightLevel.getEntities()) {
            if (e instanceof Slime) {
                followFromRight = (Slime) e;
            }
        }
        double originalXPos = followFromRight.getXPos();
        slimeOnRightLevel.tick();
        assertTrue(followFromRight.getXPos() < originalXPos);
    }

    @Test
    public void testFollowFromLeft() {
        Level slimeOnLeftLevel = LevelBuilderImpl.generateFromFile("levels/test/slimeOnLeft.json", null);
        Slime followFromLeft = null;
        for (Entity e : slimeOnLeftLevel.getEntities()) {
            if (e instanceof Slime) {
                followFromLeft = (Slime) e;
            }
        }
        double originalXPos = followFromLeft.getXPos();
        slimeOnLeftLevel.tick();
        assertTrue(followFromLeft.getXPos() > originalXPos);
    }

}