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
        level = LevelBuilderImpl.generateFromFile("levels/vertical.json", null);
        for (Entity e : level.getEntities()) {
            if (e instanceof Slime) {
                dumbSlime = (Slime) e;
            }
        }
    }

    @Test
    public void testDumbMoves() {
        double originalXPos = dumbSlime.getXPos();
        level.tick();
        assertTrue(dumbSlime.getXPos() != originalXPos);
    }


    @Test
    public void testFollow() {
        Level slimeLevel = LevelBuilderImpl.generateFromFile("levels/default.json", null);
        Slime followingSlime = null;
        for (Entity e : slimeLevel.getEntities()) {
            if (e instanceof Slime) {
                if (e.getImagePath().equals("slimeYa.png")) {
                    followingSlime = (Slime) e;
                    break;
                }
            }
        }
        double originalXPos = followingSlime.getXPos();
        slimeLevel.tick();
        assertTrue(followingSlime.getXPos() < originalXPos);
    }

}