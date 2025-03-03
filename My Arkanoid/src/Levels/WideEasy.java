//Erez Rotbart 316080589
package Levels;
import Geometry.Point;
import Geometry.Velocity;
import Sprite.Sprite;
import Sprite.Block;
import biuoop.DrawSurface;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Wide easy.
 * task 6
 * <p>
 * this class is responsible for WideEasy level.
 * </p>
 */
public class WideEasy implements LevelInformation {
    //constants for the level
    static final String LEVEL_NAME = "Wide Easy";
    static final int BALLS = 10;
    static final int SPEED = 1;
    static final int PADDLE_WIDTH = 600;
    static final int BLOCKS = 15;
    @Override
    public int numberOfBalls() {
        return BALLS;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<Velocity>();
        Velocity v;
        for (int i = 0; i < numberOfBalls() / 2; i++) {
            v = new Velocity(-5 + i, -5);
            velocities.add(v);
        }
        for (int i = 0; i < numberOfBalls() / 2; i++) {
            v = new Velocity(1 + i, -5);
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
                d.setColor(Color.WHITE);
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
    }

    @Override
    public List<Block> blocks() {
        Point upperLeft;
        Point p = new Point(30, 250);
        double width = 740 / (double) numberOfBlocksToRemove();
        List<Block> blocks = new ArrayList<Block>();
        Block block;
        for (int i = 0; i < numberOfBlocksToRemove(); i++) {
            upperLeft = new Point(p.getX() + i * width, p.getY());
            block = new Block(upperLeft, width, 20, colors(i));
            blocks.add(block);
        }
        return blocks;
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
        if (i < 2) {
            return Color.red;
        }
        if (i < 4) {
            return Color.orange;
        }
        if (i < 6) {
            return Color.yellow;
        }
        if (i < 9) {
            return Color.green;
        }
        if (i < 11) {
            return Color.blue;
        }
        if (i < 13) {
            return Color.pink;
        }
        return Color.CYAN;
        //end of colors(int i)
    }
//end of WideEasy
}
