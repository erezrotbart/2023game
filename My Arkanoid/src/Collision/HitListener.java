//Erez Rotbart 316080589.
package Collision;
import Sprite.Ball;
import Sprite.Block;

/**
 * The interface Hit listener.
 * task 5.
 */
public interface HitListener {
    /**
     * Hit event.
     * <p>
     * This method is called whenever the beingHit object is hit. The hitter
     * parameter is the Ball that's doing the hitting.
     * </p>
     * @param beingHit the being hit
     * @param hitter   the hitter
     */
    void hitEvent(Block beingHit, Ball hitter);
//HitListener
}
