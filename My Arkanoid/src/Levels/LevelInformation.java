//Erez Rotbart 316080589
package Levels;
import Geometry.Velocity;
import Sprite.Block;
import Sprite.Sprite;
import java.util.List;

/**
 * The interface Level information.
 * task 6
 * <p>
 * the interface of level in a game.
 * </p>
 */
public interface LevelInformation {
    /**
     * Number of balls int.
     * <p>
     * this method is returning the number of balls at the level
     * </p>
     * @return the int
     */
    int numberOfBalls();

    /**
     * Initial ball velocities list.
     * <p>
     * This method is returning a list of initial velocity of each ball
     * </p>
     * @return the list
     */
    List<Velocity> initialBallVelocities();

    /**
     * Paddle speed int.
     * <p>
     * this method is returning the speed of the paddle
     * </p>
     * @return the int
     */
    int paddleSpeed();

    /**
     * Paddle width int.
     * <p>
     * this method is returning the paddle width
     * </p>
     * @return the int
     */
    int paddleWidth();

    /**
     * Level name string.
     * <p>
     * this method is returning the level name
     * </p>
     * @return the string
     */
// the level name will be displayed at the top of the screen.
    String levelName();

    /**
     * Gets background.
     * <p>
     * Returns a sprite with the background of the level
     * </p>
     * @return the background
     */
    Sprite getBackground();

    /**
     * Blocks list.
     * <p>
     * this method is returning The Blocks that make up this level, each block
     * contains its size, color and location.
     * </p>
     * @return the list
     */
    List<Block> blocks();

    /**
     * Number of blocks to remove int.
     * <p>
     * this method is returning the Number of blocks that should be removed.
     * </p>
     * @return the int
     */
    int numberOfBlocksToRemove();
// end of LevelInformation
}
