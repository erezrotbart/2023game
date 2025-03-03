package Collision;
//Erez Rotbart 316080589
import Geometry.Point;
/**
 * The type Collision info.
 * task 3.
 */
public class CollisionInfo {
    //variables for collisionInfo
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Instantiates a new Collision info.
     * <p>
     * constructor of CollisionInfo.
     * </p>
     * @param collisionPoint  the collision point
     * @param collisionObject the collision object
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * Collision point Point.
     * <p>
     * returning the collision Point.
     * </p>
     * @return the point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Collision object collidable.
     * <p>
     * returning the object of the hit.
     * </p>
     * @return the collidable
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}