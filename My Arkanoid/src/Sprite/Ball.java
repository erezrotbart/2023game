package Sprite;
//erez rotbart 316080589
import biuoop.DrawSurface;
import Geometry.Velocity;
import Geometry.Line;
import Geometry.Point;
import Game.GameLevel;
import Game.GameEnvironment;
import Collision.CollisionInfo;
import Collision.Collidable;
import java.awt.*;

/**
 * The type Ball. structure and his functions.
 * task 3
 */
public class Ball implements Sprite {
    //center of the ball
    private Point center;
    //radius of the ball
    private int r;
    // the color of the ball
    private java.awt.Color color;
    //the moving speed of the ball
    private Velocity velocity;
    //min x range of the ball movement
    private GameEnvironment gameEnvironment;
    /**
     * Instantiates a new Ball.
     * <p>
     * this method is the constructor of the ball structure. the method is
     * getting a point, radius of a ball and the color of the ball.
     * </p>
     * @param center          the center
     * @param r               the r
     * @param color           the color
     * @param gameEnvironment the game environment
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
        //ball setting
        this.center = center;
        this.r = r;
        this.color = color;
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Instantiates a new Ball.
     * <p>
     * this method is another constructor of the ball structure that getting
     * cordinate to a ball, his radius and color.
     * </p>
     * @param x     the x
     * @param y     the y
     * @param r     the r
     * @param color the color
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        //ball setting
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
    }
    /**
     * Gets x.
     * <p>
     * the method is returning the x value of the ball
     * </p>
     * @return the x
     */
    public int getX() {
        return  (int) this.center.getX();
    }

    /**
     * Gets y.
     * <p>
     * the method is returning the y value of the ball.
     * </p>
     *
     * @return the y
     */
    public int getY() {
        return  (int) this.center.getY();
    }

    /**
     * Gets size.
     * <p>
     * the method is returning the radius of the ball
     * </p>
     *
     * @return the size
     */
    public int getSize() {
        return this.r;
    }
    /**
     * Gets color.
     * <p>
     * the method is returning the color of the ball
     * </p>
     *
     * @return the color
     */
    public java.awt.Color getColor() {
        return this.color;
    }
    /**
     * Get center point.
     *
     * @return the point
     */
    public Point getCenter() {
        return this.center;
    }
    /**
     * Draw on.
     * <p>
     * the method is setting in the DrawSurface value the way of the ball
     * should be sown on the screen by his values.
     * </p>
     *
     * @param surface the surface
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(),
                this.r);
        surface.setColor(Color.black);
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(),
                this.r);
    }

    /**
     * Sets game environment.
     * <p>
     * this method sets a game Environment for the ball if needed to.
     * </p>
     * @param gameEnvironment the game environment
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Gets game environment.
     * <p>
     * this method is returning the game environment of the ball.
     * </p>
     * @return the game environment
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }

    /**
     * Sets velocity.
     * <p>
     * the method is getting a Velocity value. this value is in charge of the
     * ball moving in the screen.
     * </p>
     *
     * @param v the v
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets velocity.
     * <p>
     * the method is getting a two double values and creating a velocity for
     * the ball. this value is in charge of the
     * ball moving in the screen.
     * </p>
     *
     * @param dx the dx
     * @param dy the dy
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Gets velocity.
     * <p>
     * the method is returning the velocity of the ball.
     * </p>
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Move one step.
     * <p>
     * the method is moving the ball center according to its velocity and it's
     * movement range and make sure it says in his movement range.
     * </p>
     */
    public void moveOneStep() {
        //the line the ball moves.
        Line trajectory = new Line(this.center,
                this.getVelocity().applyToPoint(this.center));
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        /*
        a loop that notice every time the ball is going outside the gaming area
        and fix its movement to not do so.
         */
        while (this.gameEnvironment.getClosestCollision(trajectory) != null) {
            //getting information of the subject the ball collided to.
            CollisionInfo info =
                    this.gameEnvironment.getClosestCollision(trajectory);
            Point colision = info.collisionPoint();
            Collidable obj = info.collisionObject();
            //getting a new direction for the ball
            Velocity newV = obj.hit(this, colision, this.getVelocity());
            //velocity for the ball no to go outside the screen.
            Velocity almostNewV = new Velocity(newV.getDx() * 0.5,
                    newV.getDy() * 0.5);
            this.setVelocity(newV);
            //in case the new velocity will get the ball outside the screen.
            this.center = almostNewV.applyToPoint(this.center);
            //renew ending condition.
            trajectory = new Line(this.center,
                    this.getVelocity().applyToPoint(this.center));
            //to make the after movement of the ball smoother.
            sleeper.sleepFor(1);
        }
        //move the ball.
        this.center = this.getVelocity().applyToPoint(this.center);
        //end of moveOneStep()
    }
    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Add to game.
     *
     * @param g the g
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
//end public class ball
}