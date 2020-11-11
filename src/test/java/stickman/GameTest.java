package stickman;

import javafx.application.Application;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import stickman.model.GameEngine;
import stickman.model.GameManager;
import stickman.view.GameWindow;



public class GameTest extends Application {
    private GameWindow testWindow;
    private GameEngine testEngine;

    @Override
    @Before
    public void start(Stage primaryStage) throws Exception {
        this.testEngine = new GameManager("levels/levels.json");
        this.testWindow = new GameWindow(testEngine, 640, 400);
        testWindow.run();
    }

    @Test
    public void appTest() {
        App a = new App();
        a.launch(null);
    }
//    @Test
//    public void testGameLoad() {
//        App a = new App();
//        GameEngine model = new GameManager("levels/levels.json");
//        GameWindow window = new GameWindow(model, 640, 400);
//
//        window.run();
//    }


}
