package Geometry;
//Erez Rotbart 316080589
import java.util.List;
/**
 * The type Line. structure and his functions.
 * task 1.2
 */
public class Line {
    /**
     * The Line arr size.
     * <p> part 2 </p>
     */
// basic data types do to represent the line
    static final int LINE_ARR_SIZE = 2;
    private Point[] lineArr;
    /**
     * The constant EPSILON - threshold method to help compare double values.
     */
    static final double EPSILON = 0.0001;

    /**
     * Instantiates a new Line.
     * <p>
     * a constructor for the line, gettig to point and creating a line
     * </p>
     *
     * @param start the start
     * @param end   the end
     */
    public Line(Point start, Point end) {
        //creating the line
        this.lineArr = new Point[LINE_ARR_SIZE];
        this.lineArr[0] = start;
        this.lineArr[1] = end;
    }

    /**
     * Instantiates a new Line.
     * <p>
     * another constructor for a line, this constructor getting x1 y1 x2 and y2
     * and creating a line.
     * </p>
     *
     * @param x1 the x 1
     * @param y1 the y 1
     * @param x2 the x 2
     * @param y2 the y 2
     */
    public Line(double x1, double y1, double x2, double y2) {
        //creating the line.
        this.lineArr = new Point[LINE_ARR_SIZE];
        this.lineArr[0] = new Point(x1, y1);
        this.lineArr[1] = new Point(x2, y2);
    }

    /**
     * Length double.
     * <p>
     * the method is givinig back the length of the line.
     * </p>
     *
     * @return the double
     */
    public double length() {
        return this.lineArr[0].distance(this.lineArr[1]);
    }

    /**
     * Middle point.
     * <p>
     * this method returning the middle point of the line
     * </p>
     *
     * @return the point
     */
    public Point middle() {
        return new Point((lineArr[0].getX() + lineArr[1].getX()) / 2,
                (lineArr[0].getY() + lineArr[1].getY()) / 2);
    }

    /**
     * Start point.
     * <p>
     * the method returning the starting point of the line as the line created.
     * </p>
     *
     * @return the point
     */
    public Point start() {
        return this.lineArr[0];
    }

    /**
     * End point.
     * <p>
     * the method returning the ending point of the line as the line created.
     * </p>
     *
     * @return the point
     */
    public Point end() {
        return this.lineArr[1];
    }

    /**
     * Is intersecting boolean.
     * <p>
     * Returns true if the lines intersect or if one line or part of it
     * contained in the other, false.
     * otherwise
     * </p>
     *
     * @param other the other
     * @return the boolean
     */
    public boolean isIntersecting(Line other) {
        //if the lines are equal they're intersecting
        if (equals(other)) {
            return true;
        }
        //if the point isn't null = intersecting
        if (intersectionWith(other) != null) {
            return true;
        } else {
            //in case of both lines vertical to x and are at the same x.
            if (compareX(other.start(), other.end()) && compareX(lineArr[0],
                    lineArr[1]) && compareX(other.start(), lineArr[0])) {
                double maxY, minY;
                //finding max y of both the lines
                maxY = Math.max(Math.max(other.start().getY(),
                        other.end().getY()), Math.max(lineArr[0].getY(),
                        lineArr[1].getY()));
                //finding min y of both of the lines
                minY = Math.min(Math.min(other.start().getY(),
                        other.end().getY()), Math.min(lineArr[0].getY(),
                        lineArr[1].getY()));
                //checking if one line is in another line.
                if (maxY - minY < other.length() + lineArr[0].
                        distance(lineArr[1])) {
                    return true;
                }
                //end of if (compareX(other.start()...
            }
            //in case of both lines vertical to y and are at the same y.
            if (compareY(other.start(), other.end()) && compareY(lineArr[1],
                    lineArr[0]) && compareY(other.start(), lineArr[0])) {
                double maxX, minX;
                //finding max x of both the lines
                maxX = Math.max(Math.max(other.start().getX(),
                        other.end().getX()), Math.max(lineArr[0].getX(),
                        lineArr[1].getX()));
                //find min x of both the lines
                minX = Math.min(Math.min(other.start().getX(),
                        other.end().getX()), Math.min(lineArr[0].getX(),
                        lineArr[1].getX()));
                //checking if one line is in another line.
                if (maxX - minX <= other.length() + lineArr[0].
                        distance(lineArr[1])) {
                    return true;
                }
                ////end of if (compareX(other.start()...
            }
            double m1, m2, b1, b2;
            //calculating m1 and b1 for the equation y1= m1*x1+b1 - "this" line.
            m1 = incline(lineArr[0].getX(), lineArr[1].getX(),
                    lineArr[0].getY(), lineArr[1].getY());
            b1 = findB(m1, lineArr[0].getX(), lineArr[0].getY());
            //calculating m2 and b2 for the equation y2= m2*x2+b2 - "other line"
            m2 = incline(other.start().getX(), other.end().getX(),
                    other.start().getY(), other.end().getY());
            b2 = findB(m2, other.start().getX(), other.start().getY());
            //checking if the 2 lines are the same
            if (compareDouble(m1, m2) && compareDouble(b1, b2)) {
                double maxX, minX;
                Point leftest = null, rightest = null;
                Point[] arr = {lineArr[0], lineArr[1], other.start(),
                        other.end()};
                //finding max x of both the lines
                maxX = Math.max(Math.max(other.start().getX(),
                        other.end().getX()), Math.max(lineArr[0].getX(),
                        lineArr[1].getX()));
                //find min x of both the lines
                minX = Math.min(Math.min(other.start().getX(),
                        other.end().getX()), Math.min(lineArr[0].getX(),
                        lineArr[1].getX()));
                //finding the leftest X of both of the lines
                for (Point point : arr) {
                    //in case we found it we don't need the loop anymore
                    if (minX == point.getX()) {
                        leftest = point;
                        break;
                    }
                }
                //finding the rightest X of both of the lines
                for (Point point : arr) {
                    //in case we found it we don't need the loop anymore
                    if (maxX == point.getX()) {
                        rightest = point;
                        break;
                    }
                }
                //checking if one of the lines is inside the other
                if (leftest.distance(rightest) < other.length()
                        + lineArr[0].distance(lineArr[1])) {
                    return true;
                }
                //end of if (compareDouble(m1, m2)
            }
        }
        return false;
        //end of isIntersecting
    }

    /**
     * Intersection with point.
     *
     * @param other the other
     * @return the point
     */
    public Point intersectionWith(Line other) {
        Point intersection = null;
        //in case one of the lines is vertical to x-axis
        if (compareX(other.start(), other.end()) || compareX(lineArr[0],
                lineArr[1])) {
            //method that returning the intersection point between the lines.
            return verticalToX(other);
        }
        //checking if both lines vertical to y-axis
        if (compareY(other.start(), other.end()) && compareY(lineArr[0],
                lineArr[1])) {
            //checking if both lines vertical to y-axis but in different y
            if (!compareY(other.start(), this.lineArr[0])) {
                return null;
            } else {
                double maxXLinearr, maxXOther, minXLinearr, minXOther, maxX,
                        minX;
                //calculating the max x value of both of the lines
                maxXLinearr = Math.max(lineArr[0].getX(), lineArr[1].getX());
                maxXOther = Math.max(other.start().getX(), other.end().getX());
                maxX = Math.max(maxXLinearr, maxXOther);
                //calculating the min y value of both of the lines
                minXLinearr = Math.min(lineArr[0].getY(), lineArr[1].getY());
                minXOther = Math.min(other.start().getY(), other.end().getY());
                minX = Math.min(minXLinearr, minXOther);
                //checking if there's intersection point between the lines
                if (compareDouble(maxX - minX, other.length()
                        + lineArr[0].distance(lineArr[1]))) {
                    double y = other.start().getY();
                /*
                calculating x according the start of one line and the end of the
                second, the max of two min point should be the
                intersection point.
                 */
                    double x = Math.max(minXOther, minXLinearr);
                    return new Point(x, y);
                }
                //end of else
            }
            // end of if (compareY(other.start()....
        }
        //in case none of the lines are vertical to one of the axes
        double m1, m2, b1, b2, intersectX, intersectY, maxXLinearr, maxXOther,
                minXLinearr, minXOther, maxYLinearr, maxYOther, minYLinearr,
                minYOther;
        //calculating m1 and b1 for the equation y1= m1*x1+b1 - "this" line.
        m1 = incline(lineArr[0].getX(), lineArr[1].getX(),
                lineArr[0].getY(), lineArr[1].getY());
        b1 = findB(m1, lineArr[0].getX(), lineArr[0].getY());
        //calculating m2 and b2 for the equation y2= m2*x2+b2 - "other line"
        m2 = incline(other.start().getX(), other.end().getX(),
                other.start().getY(), other.end().getY());
        b2 = findB(m2, other.start().getX(), other.start().getY());
        //in case the inclines are equal
        if (compareDouble(m1, m2)) {
            if (!compareDouble(b1, b2)) {
                //if the b's are different there isn't intersection point
                return null;
                //in case both of the lines are equal
            } else {
                //method that returning intersection if existing
                return sameLine(other);
            }
        }
        //calculating the intersection
        intersectX = (b2 - b1) / (m1 - m2);
        intersectY = m1 * intersectX + b1;
        /*
        checking if the intersection point is in the lines using the points we
        found above. if the point is in the range of the two lines there is
        intersection between them.
         */
        //min and max for "this" line
        maxXLinearr = Math.max(lineArr[0].getX(), lineArr[1].getX());
        maxYLinearr = Math.max(lineArr[0].getY(), lineArr[1].getY());
        minXLinearr = Math.min(lineArr[0].getX(), lineArr[1].getX());
        minYLinearr = Math.min(lineArr[0].getY(), lineArr[1].getY());
        //min and max for the "other" line
        maxXOther = Math.max(other.start().getX(), other.end().getX());
        maxYOther = Math.max(other.start().getY(), other.end().getY());
        minXOther = Math.min(other.start().getX(), other.end().getX());
        minYOther = Math.min(other.start().getY(), other.end().getY());
        /*
        checking if the point is within the x range. and making the epsilon
        bigger for the intersections point on the screen
         */
        if (intersectX - minXLinearr >= -EPSILON && intersectX - minXOther
                >= -EPSILON && maxXLinearr - intersectX >= -EPSILON
                && maxXOther - intersectX >= -EPSILON) {
            //checking if the point is within the y range.
            if (intersectY - minYLinearr >= -EPSILON && intersectY - minYOther
                    >= -EPSILON && maxYLinearr - intersectY >= -EPSILON
                    && maxYOther - intersectY >= -EPSILON) {
                intersection = new Point(intersectX, intersectY);
            }
        }
        return intersection;
        //end of intersetionWith
    }

    /**
     * Same line point.
     *
     * @param other the other
     * @return the point
     */
    public Point sameLine(Line other) {
        double  minXLinearr, minXOther, maxXLinearr, maxOther, minX, maxX;
        Point leftest = null;
        Point rightest = null;
        Point intersection = null;
        Point[] arr = {lineArr[0], lineArr[1], other.start(), other.end() };
        //finding the smallest x of both lines.
        minXLinearr = Math.min(lineArr[0].getX(), lineArr[1].getX());
        minXOther = Math.min(other.start().getX(), other.end().getX());
        minX = Math.min(minXLinearr, minXOther);
        //finding the leftest X of both of the lines
        for (Point point : arr) {
            if (minX == point.getX()) {
                leftest = point;
                break;
            }
        }
        //finding possible intersection point
        if (leftest.equals(lineArr[0])) {
            intersection = lineArr[1];
        } else if (leftest.equals(lineArr[1])) {
            intersection = lineArr[0];
        } else if (leftest.equals(other.start())) {
            intersection = other.end();
        } else if (leftest.equals(other.end())) {
            intersection = other.start();
        }
        //finding the biggest x of both lines
        maxXLinearr = Math.max(lineArr[0].getX(), lineArr[1].getX());
        maxOther = Math.max(other.start().getX(), other.end().getX());
        maxX = Math.max(maxOther, maxXLinearr);
        //finding the rightest X of both of the lines
        for (Point point : arr) {
            if (maxX == point.getX()) {
                rightest = point;
                break;
            }
        }
        //checking if there is intersection
        if (compareDouble(lineArr[0].distance(lineArr[1]) + other.length(),
                leftest.distance(rightest))) {
            return intersection;
        }
        return null;
        //end of sameLine
    }

    /**
     * Vertical to x - returning point.
     * <p>
     * this method is calculating the insertion point of two lines in case at
     * least one of them is vertical to x-axis.
     * </p>
     *
     * @param other the other
     * @return the point
     */
    public Point verticalToX(Line other) {
        //variebels for m and b in y=mx+b.
        double m, b, y, x;
        Point xy = null;
        //in case "other line" is vertical to x and "this" line doesn't
        if (compareX(other.start(), other.end()) && !compareX(this.lineArr[0],
                this.lineArr[1])) {
            //getting the x-axis.
            x = other.start().getX();
            //checking if this line is crossing the x of "other line"
            if (Math.min(this.start().getX(), this.end().getX()) < x
                    && Math.max(this.start().getX(), this.end().getX()) > x) {
                // calculating the line equation y=mx+b
                m = incline(this.lineArr[0].getX(), this.lineArr[1].getX(),
                        this.lineArr[0].getY(), this.lineArr[1].getY());
                b = this.lineArr[0].getY() - m * this.lineArr[0].getX();
                y = m * x + b;
            /*
             checking if the point is within the y range. and making the epsilon
             bigger for the intersections point on the screen.
             */
                if (y - Math.min(other.start().getY(), other.end().getY())
                        >= -EPSILON && y - Math.max(other.start().getY(),
                        other.end().getY()) <= EPSILON) {
                    xy = new Point(x, y);
                }
            }
        }
        // in case "this.lineArr" is vertical to x-axis and other line don't.
        if (!compareX(other.start(), other.end()) && compareX(this.lineArr[0],
                this.lineArr[1])) {
            //getting the x-axis.
            x = this.start().getX();
            //checking if other line is crossing the x of "this line"
            if (Math.min(other.start().getX(), other.end().getX()) < x
                    && Math.max(other.start().getX(), other.end().getX()) > x) {
                // calculating the line equasion y=mx+b
                m = incline(other.start().getX(), other.end().getX(),
                        other.start().getY(), other.end().getY());
                b = other.start().getY() - m * other.start().getX();
                y = m * x + b;
            /*
             checking if the point is within the y range. and making the epsilon
             bigger for the intersections point on the screen.
             */
                if (y - Math.min(this.lineArr[0].getY(), this.lineArr[1].getY())
                        >= -EPSILON && y - Math.max(this.lineArr[0].getY(),
                        this.lineArr[1].getY()) <= EPSILON) {
                    xy = new Point(x, y);
                }
            }
        }
        //in case both of the lines are in the same x value
        if (compareX(other.start(), this.lineArr[0])) {
            double maxYLarr, maxYOther, minLarr, minOther, maxY, minY;
            //calculating the max Y value of both of the lines
            maxYLarr = Math.max(lineArr[0].getY(), lineArr[1].getY());
            maxYOther = Math.max(other.start().getY(), other.end().getY());
            maxY = Math.max(maxYLarr, maxYOther);
            //calculating the min Y value of both of the lines
            minLarr = Math.min(lineArr[0].getY(), lineArr[1].getY());
            minOther = Math.min(other.start().getY(), other.end().getY());
            minY = Math.min(minLarr, minOther);
            //checking if the lines have one intersection point.
            if (compareDouble(maxY - minY, other.length() + lineArr[0].
                    distance(lineArr[1]))) {
                x = other.start().getX();
                /*
                calculating y according the start of one line and the end of the
                second, the max of two min point should be the
                intersection point.
                 */
                y = Math.max(minOther, minLarr);
                xy = new Point(x, y);
            }
            //end of if (compareX(other.start(), this.lineArr[0]))
        }
        //returning the intersection value if existing or null
        return xy;
        //end of verticaltoX
    }

    /**
     * Equals boolean.
     * <p>
     * the method is checking if both of the lines are equal.
     * </p>
     *
     * @param other the other
     * @return the boolean
     */
    public boolean equals(Line other) {
        //returning true or false according to the answer
        return other.start().equals(lineArr[0]) && other.end().
                equals(lineArr[1]) || other.start().equals(lineArr[1]) && other.
                end().equals(lineArr[0]);
    }

    /**
     * Incline double.
     * <p>
     * find the incline (the m in y=mx+b)
     * </p>
     *
     * @param x1 the x 1
     * @param x2 the x 2
     * @param y1 the y 1
     * @param y2 the y 2
     * @return the double
     */
    public double incline(double x1, double x2, double y1, double y2) {
        // calculating the incline of the line
        return (y2 - y1) / (x2 - x1);
    }

    /**
     * Find b double.
     * <b>
     * find the b in y=mx+b
     * </b>
     *
     * @param m the m
     * @param x the x
     * @param y the y
     * @return the double
     */
    public double findB(double m, double x, double y) {
        return y - m * x;
    }

    /**
     * Compare x boolean.
     *
     * @param p1 the p 1
     * @param p2 the p 2
     * @return the boolean
     */
    public boolean compareX(Point p1, Point p2) {
        return Math.abs(p1.getX() - p2.getX()) <= EPSILON;
    }

    /**
     * Compare y boolean.
     *
     * @param p1 the p 1
     * @param p2 the p 2
     * @return the boolean
     */
    public boolean compareY(Point p1, Point p2) {
        return Math.abs(p1.getY() - p2.getY()) <= EPSILON;
    }

    /**
     * Compare double boolean.
     *
     * @param a the a
     * @param b the b
     * @return the boolean
     */
    public boolean compareDouble(double a, double b) {
        return Math.abs(a - b) <= EPSILON;
    }

    /**
     * Closest intersection to start of line point.
     * <p>
     * the method is getting a rectangle and returning the closest
     * intersection point to the stat of this line in case there is an
     * intersection.
     * </p>
     * @param rect the rect
     * @return the point
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        //getting a list of intersection points between the line and the rect
        List<Point> intersections = rect.intersectionPoints(this);
        //checking if the list is empty
        if (intersections != null) {
            //checking if there's only 1 intersection point and return it
            if (intersections.size() == 1) {
                return intersections.get(0);
            } else {
                //in case there's two, checking which is the closest.
                double dFrom0 = intersections.get(0).distance(this.start());
                double dFrom1 = intersections.get(1).distance(this.start());
                //returning the closet point
                if (dFrom0 <= dFrom1) {
                    return intersections.get(0);
                } else {
                    return intersections.get(1);
                }
            }
        // end of if (intersections != null).
        }

        return null;
    // end of public closestIntersectionToStartOfLine.
    }
//end of public class Line
}