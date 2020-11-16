#StickMan Submission

##NOTE:
Written in MarkDown. If you want to mark a prettier document, then change .txt to .md.

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
  * stickman.model: Caretaker, Originator, Memento, GameManager(Concrete Implementor)
  * A GameEngine extends Caretaker and Originator, so its implementors are responsible for generating mementos, and also storing them.
* Prototype design pattern
  * stickman.entity: EntityProtoyper - and all entities implement it.
  * Provides a `copy()` method that can be used to create duplicates of entities.
  * Really important for saving and loading levels, creating duplicate entities, using existing entities as prototypes for the savestate.



##Levels
Level files are stored in `gameFiles/levelFiles/`.
The `ConfigFile.json` in `gameFiles/` contains the list of levels (in order) to be completed for the player to win.
After completing the first level, the player will spawn into the second, the overall score will be updated, the timer
will be reset, and lives will persist. This continues until the last level in the list - where completion will
mean displaying a Win message, and making the game inactive.

###Level Json Format
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


##Lives
The number of lives the player gets is determined in `gameFiles/ConfigFile.json`.
The player will have that many lives to complete all the levels - they do not reset between levels.
Number of current remaining stock is in the upper left, overlaid. When the player dies with 0 left, the game ends.

##Score and Time
The score and time details are overlaid.
* Top left: Spare lives
* Top right: Game Score (Updated upon level completion)
* Bottom left: Level timer (Seconds - resets upon death and new level)
* Bottom right: Level score
  * Describes current point status for the level.
  * Game Score is updated by the level score upon completion of a level
  * Level score starts at 0.
  * When the target time is reached, the level score jumps up by the target time, but then decrements by one every second
    as a time penalty.
  * If the player wins the level before the target time is reached, they receive the difference as a bonus.
  * Killing slimes and collecting mushrooms updates the level score.
  * Dying imposes no penalty on the player, but resets their level score - since it is a fresh start at the level, with
    all slimes and mushrooms back in their original location, and the timer reset.

##Save and Load
The game has Quicksave and Quickload functionality.
* Quicksave:
  * Press `q` to save.
  * Captures entire game state - level, score, time, lives, location, entities.
* Quickload:
  * Press `l` to load.
  * Restores the most recent quicksave entirely, as above.
  * Overwrites any previous save.

##Controls
* Move left: Left Arrow Key
* Move right: Right Arrow Key
* Jump: Up Arrow Key
* Shoot: Space Key
* QuickSave: q Key
* QuickLoad: l Key
