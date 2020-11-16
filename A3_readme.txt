#StickMan

##Sprites
Mushroom: https://www.deviantart.com/adventuresmg64gaming/art/Super-Mario-World-Modern-1-UP-Mushroom-Sprite-666837189

##Run
Run the code with `gradle run`

##Features
* Level Transition (See `Levels` section)
* Score and Time (See `Score` section)
* Save and Load (See `Save and Load` section)

##Design Patterns
* Memento design pattern
  * stickman.model: Caretaker, Originator, Memento.
  * GameEngine extends Caretaker and Originator, so is responsible for generating mementos, and also storing them.
* Prototype design pattern
  * stickman.entity: EntityProtoyper - and all entities implement it.
  * Provides a `copy()` method that can be used to create duplicates of prototype entities.
  * Really important for saving and loading levels, creating duplicate entities.


##JSON Format
* "stickmanSize": The size of the StickMan, either "normal" or "large"
* "stickmanPos": A JSON object storing the starting x-coordinate of the StickMan (he starts on the floor)
* "cloudVelocity": The horizontal velocity of clouds (NOT CURRENTLY USED)
* "levelDimensions": A JSON object storing width, height and floorHeight of the level
* "platforms": A JSON array of x,y coordinates representing locations of platforms
* "mushrooms": A JSON array of x,y coordinates representing locations of mushrooms
* "enemies": A JSON array of enemy objects
    * Each enemy is represented with a JSON object storing x, y coordinates, the sprite path, whether the enemy starts by
      moving left and the strategy used by the enemy (either "dumb", which just goes backwards and forwards, or "follow",
      which moves towards the player's location).
    * In the current set of levels, yellow, red and pink slimes are set to "follow", while blue and green are set to "dumb"
* "flag": A JSON object storing the x,y coordinates of the final flag

##Levels
Level files are stored in `levels/`.
The `ConfigFile.json` file describes the list of levels (in order) to be completed for the player to win.
After completing the first level, the player will spawn into the second, the overall score will be updated, the timer
will be reset, and lives will persist. This continues until the last level in the list - where completion will
mean displaying a Win message, and making the game inactive.

##Lives
The number of lives the player gets is stored in `levels/ConfigFile.json`.
The player will have that many lives to complete all the levels - they do not reset between levels.

##Controls
* Move left: Left Arrow Key
* Move right: Right Arrow Key
* Jump: Up Arrow Key
* Shoot: Space Key
* QuickSave: q Key
* QuickLoad: l Key

##Collisions
Movement is configured to use a raycasting algorithm. Raycasting is where a line (ray) is projected
from one object in a direction, and determines the distance to the nearest object in its path. This
ensures that regardless of speed, objects will not pass through each other instead of colliding.

##Documentation
All classes have been documented, and there are comments on every method.
Classes and patterns are split into packages.