package Sprite;
//Erez Rotbart 316080590
import biuoop.DrawSurface;

/**
 * The interface Sprite.
 * task 3.
 */
public interface Sprite {
    /**
     * Draw on.
     * <p>
     * this method is in charge of painting the sprites.
     * </p>
     * @param d the d
     */
    void drawOn(DrawSurface d);

    /**
     * this method is in charge of making the sprites move on the screen.
     */
    void timePassed();
//end of Sprite.
}