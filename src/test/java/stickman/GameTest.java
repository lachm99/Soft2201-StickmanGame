package stickman;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import stickman.model.GameEngine;
import stickman.model.GameManager;
import stickman.view.GameWindow;


import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class GameTest extends ApplicationTest {
    private GameWindow testWindow;
    private GameEngine testEngine;

    private KeyEvent rightPress = new KeyEvent(null, null, KeyEvent.KEY_PRESSED, null, null, KeyCode.RIGHT, false, false, false, false);
    private KeyEvent rightRelease = new KeyEvent(null, null, KeyEvent.KEY_RELEASED, null, null, KeyCode.RIGHT, false, false, false, false);
    private KeyEvent leftPress = new KeyEvent(null, null, KeyEvent.KEY_PRESSED, null, null, KeyCode.LEFT, false, false, false, false);
    private KeyEvent leftRelease = new KeyEvent(null, null, KeyEvent.KEY_RELEASED, null, null, KeyCode.LEFT, false, false, false, false);
    private KeyEvent spacePress = new KeyEvent(null, null, KeyEvent.KEY_PRESSED, null, null, KeyCode.SPACE, false, false, false, false);
    private KeyEvent spaceRelease = new KeyEvent(null, null, KeyEvent.KEY_RELEASED, null, null, KeyCode.SPACE, false, false, false, false);
    private KeyEvent upPress = new KeyEvent(null, null, KeyEvent.KEY_PRESSED, null, null, KeyCode.UP, false, false, false, false);
    private KeyEvent upRelease = new KeyEvent(null, null, KeyEvent.KEY_RELEASED, null, null, KeyCode.UP, false, false, false, false);


    @Override
    public void start(Stage primaryStage) {
        this.testEngine = new GameManager("levels/levels.json");
        this.testWindow = new GameWindow(testEngine, 640, 400);
        testWindow.run();
    }

    private void testEvent(KeyEvent k) {
        this.testWindow.getScene().processKeyEvent(k);
    }

    @Test
    public void testMoveRight() {
        double initLocation = this.testEngine.getCurrentLevel().getHeroX();
        testEvent(rightPress);
        sleep(20);
        testEvent(rightRelease);
        assertTrue(initLocation < this.testEngine.getCurrentLevel().getHeroX());
    }

    @Test
    public void testMoveLeft() {
        double initLocation = this.testEngine.getCurrentLevel().getHeroX();
        testEvent(leftPress);
        sleep(20);
        testEvent(leftRelease);
        assertTrue(initLocation > this.testEngine.getCurrentLevel().getHeroX());
    }


    @Test
    public void testMoveJump() {
        double initLocation = this.testEngine.getCurrentLevel().getHeroY();
        testEvent(upPress);
        sleep(40);
        testEvent(upRelease);
        assertTrue(initLocation > this.testEngine.getCurrentLevel().getHeroY());
    }

    @Test
    public void testStickManDiesBySlimeAndResets() {
        double initXLocation = testEngine.getCurrentLevel().getHeroX();
        testEvent(rightPress);
        sleep(8, TimeUnit.SECONDS);
        testEvent(leftPress);
        testEvent(rightRelease);
        testEvent(upPress);
        testEvent(upRelease);
        sleep(10, TimeUnit.SECONDS);
        assertEquals(initXLocation, testEngine.getCurrentLevel().getHeroX(), 0.1);
    }

    @Test
    public void testStickManKillsSlime() {
        int initNumberOfEntities = testEngine.getCurrentLevel().getEntities().size();
        testEvent(spacePress);
        testEvent(spaceRelease);
        assertEquals(initNumberOfEntities, testEngine.getCurrentLevel().getEntities().size());
        testEvent(rightPress);
        sleep(8, TimeUnit.SECONDS);
        // Mushroom consumed
        assertEquals(initNumberOfEntities - 1, testEngine.getCurrentLevel().getEntities().size());

        testEvent(leftPress);
        testEvent(rightRelease);
        testEvent(upPress);
        testEvent(upRelease);
        sleep(2, TimeUnit.SECONDS);
        testEvent(leftRelease);

        testEvent(spacePress);
        testEvent(spaceRelease);
        // Bullet that will self destroy on tile
        assertEquals(initNumberOfEntities, testEngine.getCurrentLevel().getEntities().size());

        sleep(5, TimeUnit.SECONDS);
        assertEquals(initNumberOfEntities - 1, testEngine.getCurrentLevel().getEntities().size());

        testEvent(spacePress);
        testEvent(spaceRelease);
        // Bullet!
        assertEquals(initNumberOfEntities, testEngine.getCurrentLevel().getEntities().size());

        sleep(2, TimeUnit.SECONDS);
        // Mushroom and slime gone, bullet no longer exists.
        assertEquals(initNumberOfEntities - 2, testEngine.getCurrentLevel().getEntities().size());
    }

}
