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
 * The type Green 3.
 * task 6
 * <p>
 * this class id responsible for green 3 level
 * </p>
 */
public class Green3 implements LevelInformation {
    //constants for the level
    static final String LEVEL_NAME = "Green 3";
    static final int BALLS = 2;
    static final int SPEED = 8;
    static final int PADDLE_WIDTH = 80;
    static final int BLOCKS = 50;
    @Override
    public int numberOfBalls() {
        return BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<Velocity>();
        Velocity v;
        for (int i = 0; i < numberOfBalls(); i++) {
            v = new Velocity(-2.5 + i * 5, -5);
            velocities.add(v);
        }
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
        Sprite backGround = new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                //drawing the background of the level
                d.setColor(new Color(0x0B6D0B));
                d.fillRectangle(0, 30, 800, 600);
                //drawing level name on the screen
                d.setColor(Color.BLACK);
                d.drawText(700, 20, levelName(), 20);
            }
            @Override
            public void timePassed() {
            }
        };
        return backGround;
    //end of getBackground
    }

    @Override
    public List<Block> blocks() {
        Point upperLeft;
        Point p = new Geometry.Point(270, 150);
        List<Block> blocks = new ArrayList<Block>();
        Block block;
        //loop that adds 10 blocks so the top blocks will have 2 life.
        for (int i = 0; i < 10; i++) {
            upperLeft = new Point(p.getX() + i * 50, p.getY());
            block = new Block(upperLeft, 50, 20, colors(i));
            blocks.add(block);
        }
        //loop that add the rest of the blocks that required in the task.
        for (int i = 0; i < 5; i++) {
            //first iteration is the highest blocks on the screen.
            for (int j = i; j < 10; j++) {
                upperLeft = new Point(p.getX() + j * 50, p.getY() + i * 20);
                block = new Block(upperLeft, 50, 20, colors(i));
                blocks.add(block);
            }
        }
        return blocks;
    //end of blocks
    }

    @Override
    public int numberOfBlocksToRemove() {
        return BLOCKS;
    }
    /**
     * Colors java . awt . color.
     * <p>
     * a method that paint the game blocks on the screen depends on which
     * number it gets.
     * </p>
     * @param i the
     * @return the java . awt . color
     */
    public java.awt.Color colors(int i) {
        //returning a color depends on the input.
        if (i == 0) {
            return Color.red;
        }
        if (i == 1) {
            return Color.orange;
        }
        if (i == 2) {
            return Color.yellow;
        }
        if (i == 3) {
            return Color.white;
        }
        return Color.CYAN;
        //end of colors(int i)
    }
//end og Green3
}
