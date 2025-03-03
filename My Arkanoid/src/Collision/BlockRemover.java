//Erez Rotbart 316080589.
package Collision;
import Game.GameLevel;
import Sprite.Ball;
import Sprite.Block;
import Game.Counter;

/**
 * The type Block remover.
 * task 5.
 */
public class BlockRemover implements HitListener {
    //BlockRemover settings
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Instantiates a new Block remover.
     * <p>
     * the constructor
     * </p>
     * @param gameLevel          the gameLevel
     * @param removedBlocks the removed blocks
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Hit event.
     * <p>
     * Blocks that are hit should be removed
     * </p>
     * @param beingHit the being hit
     * @param hitter   the hitter
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        gameLevel.removeSprite(beingHit);
        gameLevel.removeCollidable(beingHit);
        remainingBlocks.decrease(1);
        //in charge that the block under the gaming area won't disapear.
        if (remainingBlocks.getValue() == 0) {
            beingHit.removeHitListener(this);
        }
    }
//end of BlockRemover
}
