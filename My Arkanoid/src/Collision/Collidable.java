package Collision;
// Erez Rotbart 316080589.

import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Velocity;
import Sprite.Ball;

/**
 * The interface Collidable will be used by things that can be collided with.
 */
public interface Collidable {
    /**
     * Gets collision rectangle.
     * <p>
     * this method is returning the kind of rectangle that got hit.
     * </p>
     *
     * @return the collision rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * Hit velocity.
     * <p>
     * this method is getting the collision point in the rectangle and the
     * velocity of the ball who hit it and calculate the new velocity the ball
     * should bounce according to.
     * </p>
     *
     * @param hitter          the hitter
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return the velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
//end of Collidable
}