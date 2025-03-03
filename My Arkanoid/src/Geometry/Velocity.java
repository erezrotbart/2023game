package Geometry;
//Erez Rotbart 316080589
/**
 * The type Velocity.
 * task 3
 */
// Velocity specifies the change in position on the `x` and the `y` axes.
public class Velocity {
    // basic data type do to represent the line
    private double[] velocityArr;
    /**
     * constant for the size of the array that represent the velocity.
     */
    static final int VELOCITY_ARR_SIZE = 2;
    /**
     * Instantiates a new Velocity.
     * <p>
     * the constructor of the velocity, inserting dx to place 0 of the array
     * and dy to place 1.
     * </p>
     *
     * @param dx the dx
     * @param dy the dy
     */
    public Velocity(double dx, double dy) {
        velocityArr = new double[VELOCITY_ARR_SIZE];
        this.velocityArr[0] = dx;
        this.velocityArr[1] = dy;
    }

    /**
     * Apply to point.
     * <p>
     * the method is getting the previous point before his movement, and
     * setting a new point values according to his velocity.
     * </p>
     *
     * @param p the p
     * @return the point
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + velocityArr[0], p.getY()
                + velocityArr[1]);
    }

    /**
     * From angle and speed velocity.
     * <p>
     * the method is getting an angle and speed and covert it to the ball
     * movement threw the screen. I use cos and sin to convert the angle to
     * the movement. cos for dx, because cos is about x and width and sin is
     * for dy because sin is about y and width in math
     * </p>
     * @param angle the angle
     * @param speed the speed
     * @return the velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx, dy, radians;
        //converting angle to radians for the Math.cos and sin functions.
        radians = Math.toRadians(angle);
        /*
        multiply speed with cos and sin for the ratio of speed to the different
        directions
        */
        dx = Math.cos(radians) * speed;
        dy = Math.sin(radians) * speed;
        return new Velocity(dx, dy);
        //end of fromAngleAndSpeed
    }
    /**
     * Gets dx.
     *
     * @return the dx
     */
    public double getDx() {
        return this.velocityArr[0];
    }

    /**
     * Gets dy.
     *
     * @return the dy
     */
    public double getDy() {
        return this.velocityArr[1];
    }
//end of public class velocity.
}