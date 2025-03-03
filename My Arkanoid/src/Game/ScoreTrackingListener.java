//Erez Rotbart 316080589.
package Game;
import Collision.HitListener;
import Sprite.Ball;
import Sprite.Block;

/**
 * The type Score tracking listener.
 * <p>
 * this class is a HitListener that updated the score wen the ball hits a block
 * task 5
 * </p>
 */
public class ScoreTrackingListener implements HitListener {
    //ScoreTrackingListener setting.
    private Counter currentScore;

    /**
     * Instantiates a new Score tracking listener.
     * <p>
     * the constructor of ScoreTrackingListener.
     * </p>
     *
     * @param scoreCounter the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
//class ScoreTrackingListener
}
