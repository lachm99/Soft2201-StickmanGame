package stickman.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import stickman.entity.still.GameOver;
import stickman.entity.still.Win;
import stickman.level.*;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Implementation of GameEngine. Manages the running of the game.
 */
public class GameManager implements GameEngine {

    /**
     * The current level Index
     */
    private int levelIndex;

    /**
     * The current level Object
     */
    private Level currentLevel;

    /**
     * List of all level file names.
     */
    private List<String> levelFileNames;

    /**
     * Cumulative score - from all previous levels.
     * Current Level score is maintained by the Level itself.
     */
    private int cumulativeScore;

    /**
     * Number of Spare Lives remaining. 0 is okay.
     * If player loses life with 0 remaining, game over.
     */
    private int extraLivesRemaining;


    /**
     * Current saved state of the game engine.
     */
    private Memento savedState;

    /**
     * Describes the state of the game: 1=WON, -1=GAMEOVER, 0=PLAYING.
     */
    private int gameState;

    /**
     * Creates a GameManager object.
     *
     * @param configFile The config file containing the names of all the levels and the number of stickman Lives.
     *
     * Generates all levels, and selects the first one as the current level.
     */
    public GameManager(String configFile) {
        this.gameState = 0;
        this.extraLivesRemaining = this.readConfigFileLives(configFile);

        this.levelFileNames = this.readConfigFileLevels(configFile);
        startLevel(0);
    }

    @Override
    public Level getCurrentLevel() {
        return this.currentLevel;
    }

    @Override
    public int getCumulativeScore() {
        return this.cumulativeScore;
    }

    @Override
    public void adjustCumulativeScore(int amount) {
        this.cumulativeScore += amount;
    }

    @Override
    public int getExtraLivesRemaining() {
        return this.extraLivesRemaining;
    }

    @Override
    public int loseALife() {
        return this.extraLivesRemaining--;
    }

    @Override
    public void winLevel() {
        this.adjustCumulativeScore(this.getCurrentLevel().getCurrentScore());
        if (++levelIndex < this.levelFileNames.size()) {
            this.reset();
        } else {
            this.getCurrentLevel().getEntities().add(new Win(this.getCurrentLevel().getHeroX() - 270, this.getCurrentLevel().getHeroY() - 100));
        }
    }

    @Override
    public void loseLevel() {
        if (--this.extraLivesRemaining > 0) {
            this.reset();
        } else {
            this.getCurrentLevel().getEntities().add(new GameOver(this.getCurrentLevel().getHeroX() - 270, this.getCurrentLevel().getHeroY() - 100));
        }
    }

    @Override
    public boolean jump() {
        return this.getCurrentLevel().jump();
    }

    @Override
    public boolean moveLeft() {
        return this.getCurrentLevel().moveLeft();
    }

    @Override
    public boolean moveRight() {
        return this.getCurrentLevel().moveRight();
    }

    @Override
    public boolean stopMoving() {
        return this.getCurrentLevel().stopMoving();
    }

    @Override
    public void tick() {
        this.getCurrentLevel().tick();
    }

    @Override
    public void shoot() {
        this.getCurrentLevel().shoot();
    }

    @Override
    public void startLevel(int index) {
        this.currentLevel = LevelBuilderImpl.generateFromFile(this.levelFileNames.get(levelIndex), this);
    }


    @Override
    public void reset() {
        startLevel(this.levelIndex);
    }

    /**
     * Retrieves the list of level filenames from a config file
     *
     * @param config The config file
     * @return The list of level names
     */
    @SuppressWarnings("unchecked")
    private List<String> readConfigFileLevels(String config) {

        List<String> res = new ArrayList<String>();

        JSONParser parser = new JSONParser();

        try {

            Reader reader = new FileReader(config);

            JSONObject object = (JSONObject) parser.parse(reader);

            JSONArray levelFiles = (JSONArray) object.get("levelFiles");

            Iterator<String> iterator = (Iterator<String>) levelFiles.iterator();

            // Get level file names
            while (iterator.hasNext()) {
                String file = iterator.next();
                res.add("levels/" + file);
            }

        } catch (IOException e) {
            System.exit(10);
            return null;
        } catch (ParseException e) {
            return null;
        }

        return res;
    }

    private int readConfigFileLives(String config) {
        JSONParser parser = new JSONParser();
        try {

            Reader reader = new FileReader(config);

            JSONObject object = (JSONObject) parser.parse(reader);

            return  Integer.parseInt(String.valueOf(object.get("lives")));

        } catch (IOException e) {
            System.exit(10);
            return 0;
        } catch (ParseException e) {
            return 0;
        }
    }


    @Override
    public Memento makeSnapshot() {
        return new Memento(this);
    }

    @Override
    public void restoreSnapshot(Memento m) {
        // this.state = m.state.
    }


    @Override
    public void save() {
        this.savedState = this.makeSnapshot();
    }

    @Override
    public void load() {
        restoreSnapshot(this.savedState);
    }
}

