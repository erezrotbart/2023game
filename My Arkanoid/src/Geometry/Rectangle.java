package Geometry;
//Erez Rotbart 316080589
import java.util.ArrayList;
/**
 * The type Rectangle.
 * task 3.
 */
public class Rectangle {
    // basic data types do to represent the Rectangle.
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Instantiates a new Rectangle.
     * <p>
     * constructor of Rectangle.
     * </p>
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }
    /**
     * Gets width.
     * <p>
     * returning width of rectangle
     * </p>
     * @return the width
     */
    public double getWidth() {
        return this.width;
    }
    /**
     * Gets height.
     * returning Height of rectangle
     * @return the height
     */
    public double getHeight() {
        return this.height;
    }
    /**
     * Gets upper left.
     * returning up Upper Left of rectangle
     * @return the upper left
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
    /**
     * Up right corner point.
     * <p>
     * returning up Right Corner of rectangle
     * </p>
     * @return the point
     */
    public Point upRightCorner() {
        return new Point(this.upperLeft.getX() + this.getWidth(),
                this.getUpperLeft().getY());
    }
    /**
     * Down left corner point.
     * <p>
     * returning down Left Corner of rectangle
     * </p>>
     * @return the point
     */
    public Point downLeftCorner() {
        return  new Point(this.getUpperLeft().getX(),
                this.getUpperLeft().getY() + this.getHeight());
    }
    /**
     * Down right corner point.
     * returning down right Corner of rectangle
     * @return the point
     */
    public Point downRightCorner() {
        return  new Point(this.upRightCorner().getX(),
                this.upRightCorner().getY() + this.getHeight());
    }
    /**
     * Upper borderline.
     * <p>
     * returning upper Border of rectangle
     * </p>>
     * @return the line
     */
    public Line upperBorder() {
        return new Line(this.getUpperLeft(), this.upRightCorner());
    }
    /**
     * Down borderline.
     * <p>
     * returning down Border of rectangle
     * </p>
     * @return the line
     */
    public Line downBorder() {
        return new Line(this.downLeftCorner(), this.downRightCorner());
    }
    /**
     * Left borderline.
     * <p>
     * returning left Border of rectangle
     * </p>
     * @return the line
     */
    public Line leftBorder() {
        return new Line(this.downLeftCorner(), this.getUpperLeft());
    }
    /**
     * Right borderline.
     * <p>
     * returning rightBorder of rectangle
     * </p>
     * @return the line
     */
    public Line rightBorder() {
        return new Line(this.upRightCorner(), this.downRightCorner());
    }
    /**
     * Intersection points java . util . list.
     * <p>
     * returning intersection Points of rectangle with a line.
     * </p>
     * @param line the line
     * @return the java . util . list
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        //setting valuables for the method
        java.util.List<Point> intersections = new ArrayList<Point>();
        Point startOfLine = line.start();
        //checking if line is intersecting the upperborder of the rectangle
        if (line.isIntersecting(this.upperBorder())) {
            //checking if line is in the border
            if (line.intersectionWith(upperBorder()) == null) {
                //if so make the intersection at the first point between them
                intersections.add(closestP(startOfLine, upperBorder().start(),
                        upperBorder().end()));
            } else {
                intersections.add(line.intersectionWith(upperBorder()));
            }
        }
        //checking if line is intersecting the downBorder of the rectangle
        if (line.isIntersecting(this.downBorder())) {
            //checking if line is in the border
            if (line.intersectionWith(downBorder()) == null) {
                //if so make the intesection at the first point between them
                intersections.add(closestP(startOfLine, downBorder().start(),
                        downBorder().end()));
            } else {
                intersections.add(line.intersectionWith(downBorder()));
            }
        }
        //checking if line is intersecting the leftBorder of the rectangle
        if (line.isIntersecting(this.leftBorder())) {
            //checking if line is in the border
            if (line.intersectionWith(leftBorder()) == null) {
                //if so make the intesection at the first point between them
                intersections.add(closestP(startOfLine, leftBorder().start(),
                        leftBorder().end()));
            } else {
                intersections.add(line.intersectionWith(leftBorder()));
            }
        }
        //checking if line is intersecting the rightBorder of the rectangle
        if (line.isIntersecting(this.rightBorder())) {
            //checking if line is in the border
            if (line.intersectionWith(rightBorder()) == null) {
                //if so make the intesection at the first point between them
                intersections.add(closestP(startOfLine, rightBorder().start(),
                        rightBorder().end()));
            } else {
                intersections.add(line.intersectionWith(rightBorder()));
            }
        }
        //if list is empty return null to notify the program.
        if (intersections.isEmpty()) {
            intersections = null;
        }
        return intersections;
        //end of intersectionPoints.
    }

    /**
     * Closest p point.
     * <p>
     * returning the closest point in case the same lines have are sharing
     * points.
     * </p>
     * @param trajectory the trajectory
     * @param p1         the p 1
     * @param p2         the p 2
     * @return the point
     */
    public Point closestP(Point trajectory, Point p1, Point p2) {
        if (trajectory.distance(p1) <= trajectory.distance(p2)) {
            return p1;
        }
        return p2;
    }

// end of Rectangle
}