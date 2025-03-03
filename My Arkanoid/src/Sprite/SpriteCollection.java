package Sprite;
// Erez Rotbart 316080589
import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Sprite collection.
 * task 3.
 */
public class SpriteCollection {

    //variable for SpriteCollection object
    private List<Sprite> sprites;

    /**
     * Instantiates a new Sprite collection.
     * <p>
     * the constructor of SSpriteCollection.
     * </p>
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<Sprite>();
    }

    /**
     * Add sprite.
     * <p>
     * this method is getting a Sprite value and add it to the collection.
     * </p>
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * Remove sprite.
     * <p>
     * this method is getting an index and remove the matching object.
     * </p>
     *
     * @param s the s
     */
    public void deleteSprite(Sprite s) {
        this.sprites.remove(s);
    }

    /**
     * Gets sprites.
     * <p>
     * the method is returning the list of sprites.
     * </p>
     *
     * @return the sprites
     */
    public List<Sprite> getSprites() {
        return this.sprites;
    }

    /**
     * Notify all time passed.
     * <p>
     * this method is making all the sprites to move using their timePassed
     * method.
     * </p>
     */
    public void notifyAllTimePassed() {
        List<Sprite> sprites = new ArrayList<Sprite>(this.sprites);
        for (Sprite s: sprites) {
            s.timePassed();
        }
    }

    /**
     * Draw all on.
     * <p>
     * drawing all sprites using their drawOn method.
     * </p>
     *
     * @param d the d
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s: getSprites()) {
            s.drawOn(d);
        }
    }
// end of SpriteCollection class.
}