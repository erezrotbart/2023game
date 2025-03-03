//Erez Rotbart 316080589
package Levels;
import Geometry.Velocity;
import Sprite.Sprite;
import Sprite.Block;
import biuoop.DrawSurface;
import Geometry.Point;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Direct hit Level. task 6
 * <p>
 * this is the class of the "DirectHit level"
 * </p>
 */
public class DirectHit implements LevelInformation {
    //constants for the level
    static final String LEVEL_NAME = "Direct Hit";
    static final int BALLS = 1;
    static final int SPEED = 5;
    static final int PADDLE_WIDTH = 80;
    static final int BLOCKS = 1;
    @Override
    public int numberOfBalls() {
        return BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<Velocity>();
        Velocity v = new Velocity(0, 5.5);
        velocities.add(v);
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return SPEED;
    }

    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    @Override
    public String levelName() {
        return LEVEL_NAME;
    }

    @Override
    public Sprite getBackground() {
        //setting a background for the level
        Sprite backGround = new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.BLACK);
                d.fillRectangle(0, 30, 800, 600);
                d.drawText(700, 20, levelName(), 20);
            }

            @Override
            public void timePassed() {

            }
        //end of background
        };
        return backGround;
    //end of get Background
    }

    @Override
    public List<Block> blocks() {
        Point p = new Point(380, 200);
        Block b = new Block(p, 40, 40, Color.RED);
        List<Block> blocks = new ArrayList<Block>();
        blocks.add(b);
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return BLOCKS;
    }
//end of directHit
}
