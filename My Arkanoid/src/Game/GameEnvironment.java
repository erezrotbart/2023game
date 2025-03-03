package Game;
//Erez Rotbart 316080589
import Collision.Collidable;
import Collision.CollisionInfo;
import Geometry.Line;
import Geometry.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * The type GameLevel environment.
 * task 3
 */
public class GameEnvironment {
    //variable for the gameenvironment
    private List<Collidable> collidables;

    /**
     * Instantiates a new GameLevel environment.
     * <p>
     * the constructor of the GameEnvironment.
     * </p>
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    /**
     * Gets collidables.
     * <p>
     * the method is returning the callable list.
     * </p>
     *
     * @return the collidables
     */
    public List<Collidable> getCollidables() {
        return this.collidables;
    }

    /**
     * Add collidable.
     * <p>
     * the method is adding a Collectable to the list.
     * </p>
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * delete collidable.
     *
     * @param c the c
     */
    public void deleteCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * Gets closest collision.
     * <p>
     * the method is getting a line of a movment object and returning the
     * collision info of the first object it bumped into
     * </p>
     *
     * @param trajectory the trajectory
     * @return the closest collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        //list of points to find the closest one to the start of trajectory
        List<Point> points = new ArrayList<Point>();
        //the collisionInfo the method will return if existing.
        CollisionInfo found = null;
        //loop to create list of optional collision points
        for (Collidable a : this.getCollidables()) {
            Point p = trajectory.closestIntersectionToStartOfLine(a
                    .getCollisionRectangle());
            //add the point to the list if existing
            if (p != null) {
                points.add(p);
            }
        }
        //if there are no collision points the method will return null.
        if (points.isEmpty()) {
            return null;
        } else {
            //method to find the closest point
            Point closestP = findClosestP(points, trajectory);
            //method to find the matching Collidiable to the point.
            Collidable closestC = findClosestC(closestP, trajectory);
            found = new CollisionInfo(closestP, closestC);
        }
        return found;
        // end of getClosestCollision
    }

    /**
     * Find closest p point.
     * <p>
     * the methos is getting a list of points, a line returning the closest
     * one to the start of the line.
     * </p>
     *
     * @param points     the points
     * @param trajectory the trajectory
     * @return the point
     */
    public Point findClosestP(java.util.List<Point> points, Line trajectory) {
        //set min distance at a starting value
        double minD = points.get(0).distance(trajectory.start());
        Point closestP = points.get(0);
        //loop that finds the closest point to the start of trajectory.
        for (Point p : points) {
            if (minD > p.distance(trajectory.start())) {
                //updating the values
                minD = p.distance(trajectory.start());
                closestP = p;
            }
        }
        return closestP;
        //end of findClosestP.
    }

    /**
     * Count objects int.
     * <p>
     * the method is getting a line and count the amount of objects that share
     * the collision point.
     * </p>
     *
     * @param trajectory the trajectory
     * @return the int
     */
    public int countObjects(Line trajectory) {
        //list of points to find the closest one to the start of trajectory
        List<Point> points = new ArrayList<Point>();
        //the collisionInfo the method will return if existing.
        CollisionInfo found = null;
        //loop to create list of optional collision points
        for (Collidable a : this.getCollidables()) {
            Point p = trajectory.closestIntersectionToStartOfLine(a
                    .getCollisionRectangle());
            //add the point to the list if existing
            if (p != null) {
                points.add(p);
            }
        }
        return points.size();
        // end of countObjects
    }

    /**
     * Find closest c collidable.
     * <p>
     * the method id getting a point and a line and finds the matching
     * collidable to the point.
     * </p>
     *
     * @param closestP   the closest p
     * @param trajectory the trajectory
     * @return the collidable
     */
    public Collidable findClosestC(Point closestP, Line trajectory) {
        Collidable closestC = this.getCollidables().get(0);
        //loop that finding the matching collidable to the closestP
        for (Collidable a : this.collidables) {
            Point p = trajectory.closestIntersectionToStartOfLine(a
                    .getCollisionRectangle());
            //checking if collidable matching according to the point.
            if (p != null) {
                if (closestP.equals(p)) {
                    closestC = a;
                    break;
                }
            }
        }
        return closestC;
    // end of findClosestC
    }
// end of GameEnvironment
}