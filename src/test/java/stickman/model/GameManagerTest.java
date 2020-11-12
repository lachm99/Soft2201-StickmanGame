package stickman.model;


import org.junit.*;

import static org.junit.Assert.*;

public class GameManagerTest {
    private static GameManager testGameManager;

    @Before
    public void setup() {
        testGameManager = new GameManager("levels/levels.json");
    }

    @Test
    public void testBasicGameplay() {
        testGameManager.jump();
        testGameManager.tick();
        assertTrue(testGameManager.getCurrentLevel().getHeroY() < testGameManager.getCurrentLevel().getFloorHeight());

        double heroXPos = testGameManager.getCurrentLevel().getHeroX();
        testGameManager.moveLeft();
        testGameManager.tick();
        assertTrue(heroXPos > testGameManager.getCurrentLevel().getHeroX());

        testGameManager.stopMoving();
        heroXPos = testGameManager.getCurrentLevel().getHeroX();
        testGameManager.tick();
        assertTrue(heroXPos == testGameManager.getCurrentLevel().getHeroX());

        heroXPos = testGameManager.getCurrentLevel().getHeroX();
        testGameManager.moveRight();
        testGameManager.tick();
        assertTrue(heroXPos < testGameManager.getCurrentLevel().getHeroX());

    }


    @Test
    public void testGameStopsAfterWinning() {
        testGameManager.getCurrentLevel().win();

        double heroXPos = testGameManager.getCurrentLevel().getHeroX();
        double heroYPos = testGameManager.getCurrentLevel().getHeroY();
        int numberOfEntities = testGameManager.getCurrentLevel().getEntities().size();

        testGameManager.jump();
        testGameManager.shoot();
        testGameManager.moveRight();

        testGameManager.tick();

        assertEquals(heroXPos, testGameManager.getCurrentLevel().getHeroX(), 0.1);
        assertEquals(heroYPos, testGameManager.getCurrentLevel().getHeroY(), 0.1);
        assertEquals(numberOfEntities, testGameManager.getCurrentLevel().getEntities().size());

        testGameManager.moveLeft();
        testGameManager.tick();
        assertEquals(heroXPos, testGameManager.getCurrentLevel().getHeroX(), 0.1);

        assertFalse(testGameManager.stopMoving());
    }

}