package Sprite;
//Erez Rotbart 316080589.
import Collision.Collidable;
import Collision.HitListener;
import Collision.HitNotifier;
import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Velocity;
import biuoop.DrawSurface;
import java.awt.Color;
import Geometry.Line;
import Game.GameLevel;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Block.
 * task 3.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    //variables for the Block
    private Rectangle block;
    private java.awt.Color color;
    /**
     * The Hit listeners.
     */
    private List<HitListener> hitListeners;

    /**
     * Instantiates a new Block.
     * <p>
     * the constructor of Block.
     * </p>
     *
     * @param block the block
     * @param color the color
     */
    public Block(Rectangle block, java.awt.Color color) {
        this.block = block;
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * Instantiates a new Block.
     * <p>
     * the Constructor of Block.
     * </p>
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     * @param color     the color
     */
    public Block(Point upperLeft, double width, double height,
                 java.awt.Color color) {
        this.block = new Rectangle(upperLeft, width, height);
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.block;
    }
    /**
     * Draw on.
     * <p>
     * drawing the block.
     * </p>
     * @param d the surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        //fill the blocks with their color.
        d.setColor(this.color);
        d.fillRectangle((int) this.block.getUpperLeft().getX(),
                (int) this.block.getUpperLeft().getY(),
                (int) this.block.getWidth(), (int) this.block.getHeight());
        //to identify game border blocks and regular blocks.
        if (this.block.getHeight() <= 550 && this.block.getWidth() < 800) {
            d.setColor(Color.black);
            //drawing the blocks borders .
            d.drawRectangle((int) this.block.getUpperLeft().getX(),
                    (int) this.block.getUpperLeft().getY(),
                    (int) this.block.getWidth(), (int) this.block.getHeight());
        }
        //end of drawOn.
    }

    /**
     * Notify hit.
     * <p>
     * this metod is getting a ball and making all the game updates that
     * should accord because the of the ball hitting the block like making the
     * block disapiring or count points and etc..
     * </p>
     * @param hitter the hitter
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //converting the collision point to a line to find in which border it was
        Line cPoint = new Line(collisionPoint, collisionPoint);
        //boolean values to indicate where the colision was on the block
        boolean upBorder, downBorder, leftBorder, rightBorder;
        //checking in which border of the block was the hit.
        upBorder = this.block.upperBorder().isIntersecting(cPoint);
        rightBorder = this.block.rightBorder().isIntersecting(cPoint);
        downBorder = this.block.downBorder().isIntersecting(cPoint);
        leftBorder = this.block.leftBorder().isIntersecting(cPoint);
        this.notifyHit(hitter);
        //returning the new velocity according to the point oof the hit.
        return newVelocity(upBorder, rightBorder, downBorder, leftBorder,
                currentVelocity);
    //end of hit
    }
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * New velocity - velocity.
     * <p>
     * the method is getting answers about where to collision point was and
     * calculating a new velocity according to it.
     * </p>
     *
     * @param up              the up
     * @param right           the right
     * @param down            the down
     * @param left            the left
     * @param currentVelocity the current velocity
     * @return the velocity
     */
    public Velocity newVelocity(boolean up, boolean right, boolean down,
                                boolean left, Velocity currentVelocity) {
        //variables for dx and dy.
        double dx = currentVelocity.getDx(), dy = currentVelocity.getDy();
        //checking if the hit was on the top or down of the block.
        if (up || down) {
            //checking if also on left or right blocks
            if (!right && !left) {
                return new Velocity(dx, -dy);
            } else {
                return new Velocity(-dx, -dy);
            }
        }
        //in case the hit was only on one of the sides.
        return new Velocity(-dx, dy);
        // end of newVelocity.
    }
    @Override
    public void timePassed() { }

    /**
     * Add to game.
     * <p>
     * adding the blocks to the game.
     * </p>
     *
     * @param g the g
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Instantiates a new Remove from gameLevel.
     * <p>
     * this method is removing the current block from the gameLevel when it called.
     * </p>
     *
     * @param gameLevel the gameLevel
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

//end of Block class.
}