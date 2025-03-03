//Erez Rotbart 316080589.
package Collision;
import Game.GameLevel;
import Sprite.Ball;
import Sprite.Block;
import Game.Counter;

/**
 * The type Ball remover.
 * <p>
 * this class is responsible for removing balls from the gameLevel
 * task 5
 * </p>
 */
public class BallRemover implements HitListener {
    //ball remover settings
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * Instantiates a new Block remover.
     * <p>
     * the constructor
     * </p>
     * @param gameLevel           the gameLevel
     * @param remainingBalls the remaining balls
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        //removing the ball and decreasing ball number by 1.
        gameLevel.removeSprite(hitter);
        remainingBalls.decrease(1);
        if (remainingBalls.getValue() == 0) {
            beingHit.removeHitListener(this);
        }
    }
//end of BallRemover
}
