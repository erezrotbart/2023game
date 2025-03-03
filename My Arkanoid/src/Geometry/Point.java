package Geometry;
// 316080589 Erez Rotbart
/**
 * The Point. structure and his functions
 * task 3
 */
public class Point {
    /**
     * The Point arr.
     */
    static final int POINT_ARR_SIZE = 2;
    /**
     * The constant EPSILON - threshold method to help compare double values.
     */
    static final double EPSILON = 0.0001;
    // basic data types do to represent the point
    private final double[] xyArr;

    /**
     * Instantiates a new Point.
     * <p> constructor of th point </p>
     * @param x the x
     * @param y the y
     */
    public Point(double x, double y) {
        //creating the point
        this.xyArr = new double[POINT_ARR_SIZE];
        this.xyArr[0] = x;
        this.xyArr[1] = y;
    }
    /**
     * Distance double.
     * <p> the method calculating the distance between this point and another
     * point </p>
     * @param other the other
     * @return the double
     */
    public double distance(Point other) {
        //returning the distance according to distance equation
        return Math.sqrt((other.getX() - xyArr[0]) * (other.getX() - xyArr[0])
                + (other.getY() - xyArr[1]) * (other.getY() - xyArr[1]));
    }

    /**
     * Equals boolean.
     * <p> the method is returning true if the points are the same or false
     * others </p>
     * @param other the other
     * @return the boolean
     */
    public boolean equals(Point other) {
        boolean checkX, checkY;
        //checking if the points has the same x and y values.
        checkX = Math.abs(other.getX() - this.xyArr[0]) <= EPSILON;
        checkY = Math.abs(other.getY() - this.xyArr[1]) <= EPSILON;
        return checkX && checkY;
    }
    /**
     * Gets x.
     * <p> return the y value of the point </p>
     * @return the x
     */
    public double getX() {
        return this.xyArr[0];
    }

    /**
     * Gets y.
     * <p> return the y value of the point </p>
     * @return the y
     */
    public double getY() {
        return this.xyArr[1];
    }
// end of public class Point.
}