package stickman.level;

import org.junit.Before;
import org.junit.Test;
import stickman.entity.Entity;
import stickman.entity.moving.enemy.Slime;
import stickman.entity.moving.player.StickMan;
import stickman.model.GameEngine;
import stickman.model.GameManager;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class LevelTest  {
    private static GameManager testGameManager;
    private static Level testLevel;
    private static Slime testEnemy;
    private static StickMan testHero;

    @Before
    public void setup() {
        testGameManager = new GameManager("levels/test/levels.json");
        testLevel = testGameManager.getCurrentLevel();
        for (Entity e : testLevel.getEntities()) {
            if (e instanceof Slime) {
                testEnemy = (Slime) e;
            }
            if (e instanceof StickMan) {
                testHero = (StickMan) e;
            }
        }
    }

    @Test
    public void testStickmanShooting() {
        int numberOfEntities = testLevel.getEntities().size();
        testLevel.shoot();
        assertFalse(testLevel.getEntities().size() > numberOfEntities);

        testHero.upgrade();
        testLevel.shoot();
        assertTrue(testLevel.getEntities().size() > numberOfEntities);

        testHero.die();
        numberOfEntities = testGameManager.getCurrentLevel().getEntities().size();
        testGameManager.getCurrentLevel().shoot();
        assertFalse(testGameManager.getCurrentLevel().getEntities().size() > numberOfEntities);
    }
}
