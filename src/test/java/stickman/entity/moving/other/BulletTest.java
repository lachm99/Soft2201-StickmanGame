package stickman.entity.moving.other;

import org.junit.jupiter.api.*;
import stickman.entity.Entity;
import stickman.entity.moving.player.StickMan;
import stickman.level.Level;
import stickman.level.LevelBuilderImpl;

import static org.junit.jupiter.api.Assertions.*;

public class BulletTest {
    private static Level l;
    private static StickMan hero;


    @BeforeAll
    public static void setup() {
        l = LevelBuilderImpl.generateFromFile("levels/test/normalstickman.json", null);
        for (Entity e:l.getEntities()) {
            if (e instanceof StickMan) {
                hero = (StickMan) e;
                hero.upgrade();
            }
        }
    }


    @Test
    public void testConstruction() {
        Bullet newBullet = new Bullet(0, 0, false);
        assertEquals(0, newBullet.getXPos());
        assertEquals(0, newBullet.getXPos());
    }

    @Test
    public void testDirectionOfTravel() {
        Bullet currentBullet = null;
        hero.moveRight();
        l.shoot();
        for (Entity e:l.getEntities()) {
            if (e instanceof Bullet) {
                currentBullet = (Bullet) e;
            }
        }
        double xLocationInitialRight = currentBullet.getXPos();
        l.tick();
        assertTrue(xLocationInitialRight < currentBullet.getXPos());

        l.getEntities().remove(currentBullet);


        hero.moveLeft();
        l.shoot();
        for (Entity e:l.getEntities()) {
            if (e instanceof Bullet) {
                currentBullet = (Bullet) e;
            }
        }
        double xLocationInitialLeft = currentBullet.getXPos();
        l.tick();
        assertTrue(xLocationInitialLeft > currentBullet.getXPos());

    }
}
