//erez rotbart 316080589
package Sprite;
import Collision.Collidable;
import Game.GameLevel;
import Geometry.Point;
import Geometry.Velocity;
import biuoop.DrawSurface;
import java.awt.*;
import java.util.ArrayList;
import Geometry.Rectangle;
import Geometry.Line;

/**
 * The type Paddle.
 * <p>
 * the class of the Paddle in the game.
 * </p>
 */
public class Paddle implements Sprite, Collidable {
    //variables for the paddle constructor
    private biuoop.KeyboardSensor keyboard;
    //variables for the paddle
    private Rectangle paddle;
    private Velocity leftV;
    private Velocity rightV;
    /**
     * constant for hitting Region 1 angles.
     */
    static final double ANGLE1_UP = -150;
    /**
     * constant for hitting Region 2 angles.
     */
    static final double ANGLE2_UP = -120;
    /**
     * constant for hitting Region 3 angles.
     */
    static final double ANGLE3_UP = -90;
    /**
     * constant for hitting Region 4 angles.
     */
    static final double ANGLE4_UP = -60;
    /**
     * constant for hitting Region 5 angles.
     */
    static final double ANGLE5_UP = -30;
    /**
     * The Partition that represent the part of any region from the top of
     * the paddle.
     */
    static final double PARTITION = 0.2;
    /**
     * The Color of the paddle.
     */
    static final java.awt.Color COLOR = Color.YELLOW;
    //the variables that responsible for calculating tha paddles regions.
    /**
     * The Left border of the moving range.
     */
    static final double LEFT_BORDER = 30;
    /**
     * The Right border of the moving range.
     */
    static final double RIGHT_BORDER = 770;

    /**
     * Instantiates a new Paddle.
     * <p>
     * the constructor of the paddle.
     * </p>
     *
     * @param paddle   the paddle
     * @param keyboard the keyboard
     * @param velocity the velocity
     */
    public Paddle(Rectangle paddle, biuoop.KeyboardSensor keyboard,
                  int velocity) {
        this.paddle = paddle;
        this.keyboard = keyboard;
        //left and right velocities
        this.leftV = new Velocity((double) -1 * velocity, 0);
        this.rightV = new Velocity((double) velocity, 0);
    }

    /**
     * Regions java . util . list.
     * <p>
     * this method is making a list of all the 5 regions the paddle have. the
     * first region is leftest and etc...
     * </p>
     *
     * @return the java . util . list
     */
    public java.util.List<Line> regions() {
        //variables for the method
        java.util.List<Line> regions = new ArrayList<Line>();
        double x = this.paddle.getUpperLeft().getX();
        double y = this.paddle.getUpperLeft().getY();
        //the size of any region at the paddle
        double regionSize = paddle.getWidth() * PARTITION;
        //calculating the points borders of each region.
        Point p1 = new Point(x, y);
        Point p2 = new Point(x + regionSize, y);
        Point p3 = new Point(x + (2 * regionSize), y);
        Point p4 = new Point(x + (3 * regionSize), y);
        Point p5 = new Point(x + (4 * regionSize), y);
        Point p6 = new Point(x + (5 * regionSize), y);
        //each line in the list is a region in the paddle.
        regions.add(0, new Line(p1, p2));
        regions.add(1, new Line(p2, p3));
        regions.add(2, new Line(p3, p4));
        regions.add(3, new Line(p4, p5));
        regions.add(4, new Line(p5, p6));
        return regions;
    //public java.util.List<Line> regions()
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }

    /**
     * Move left.
     * <p>
     * This method move the paddle to the left.
     * </p>
     */
    public void moveLeft() {
        //checking the paddle isn't crossing the left border.
        if (leftV.applyToPoint(this.paddle.getUpperLeft()).getX()
                >= LEFT_BORDER) {
            //moving the ball left according to its speed.
            this.paddle =
                    new Rectangle(leftV.applyToPoint(this.paddle.
                            getUpperLeft()), this.paddle.getWidth(), this.paddle
                            .getHeight());
        }
    }

    /**
     * Move right.
     * <p>
     * This method move the paddle to the left.
     * </p>
     */
    public void moveRight() {
        //checking the paddle isn't crossing the right border.
        if (rightV.applyToPoint(this.paddle.getUpperLeft()).getX() + this.
                paddle.getWidth() <= RIGHT_BORDER) {
            //moving the ball right according to its speed.
            this.paddle =
                    new Rectangle(rightV.applyToPoint(this.paddle.
                            getUpperLeft()), this.paddle.getWidth(), this.paddle
                            .getHeight());
        }
    }
    /**
     * timePassed()
     * <p>
     * the method is moving the paddle according to the user's orders.
     * </p>
     */
    @Override
    public void timePassed() {
        //checking what keyboard the user pressed and implements it.
        if (keyboard.isPressed(keyboard.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(keyboard.RIGHT_KEY)) {
            moveRight();
        }
    }
    /**
     * Draw on.
     * <p>
     * this method is painting the paddle.
     * </p>
     * @param d the d
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(COLOR);
        d.fillRectangle((int) this.paddle.getUpperLeft().getX(), (int) this.
                        paddle.getUpperLeft().
                        getY(), (int) this.paddle.getWidth(),
                (int) this.paddle.getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) this.paddle.getUpperLeft().getX(),
                (int) this.paddle.getUpperLeft().getY(), (int) this.paddle.
                        getWidth(), (int) this.paddle.getHeight());
    }
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //converting the collision point to a line to find in which border it was
        Line cPoint = new Line(collisionPoint, collisionPoint);
        //checking if the hit was on top of the paddle use method from Block().
        boolean upBorder = this.paddle.upperBorder().isIntersecting(cPoint);
        Velocity newV = currentVelocity;
        if (!upBorder) {
            return new Velocity(-1 * newV.getDx(), newV.getDy());
        } else {
            newV = newVelocity(currentVelocity, cPoint);
        }
        //returning the new velocity according to the point oof the hit.
        return newV;
    }

    /**
     * New velocity - velocity.
     * <p>
     * this method is getting an answer if the ball hits the padlle on its
     * top to calculate correct velocity according to the region of the it.
     * if the ball didn't the top return a velocity like a normal block.
     * </p>
     *
     * @param currentVelocity the current velocity
     * @param collisionPoint  the collision point
     * @return the velocity
     */
    public Velocity newVelocity(Velocity currentVelocity,
                                Line collisionPoint) {
        //variables for the method
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        double speed = Math.sqrt((dx * dx) + (dy * dy));
        //the list og the regions of the paddle
        java.util.List<Line> partition = regions();
        //checking the region of the hit in every if to set the right angle.
        if (collisionPoint.isIntersecting(partition.get(0))) {
            //return the new velocity according to the angle.
            return Velocity.fromAngleAndSpeed(ANGLE1_UP, speed);
        }
        if (collisionPoint.isIntersecting(partition.get(1))) {
            //return the new velocity according to the angle.
            return Velocity.fromAngleAndSpeed(ANGLE2_UP, speed);
        }
        if (collisionPoint.isIntersecting(partition.get(2))) {
            //return the new velocity according to the angle.
            return Velocity.fromAngleAndSpeed(ANGLE3_UP, speed);
        }
        if (collisionPoint.isIntersecting(partition.get(3))) {
            //return the new velocity according to the angle.
            return Velocity.fromAngleAndSpeed(ANGLE4_UP, speed);
        }
        //return the new velocity according to the angle.
        return Velocity.fromAngleAndSpeed(ANGLE5_UP, speed);
    // end of new velocity
    }

    /**
     * Add to game.
     * <p>
     * this method is adding the paddle to the game.
     * </p>
     *
     * @param g the g
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
//end of paddle class.
}
