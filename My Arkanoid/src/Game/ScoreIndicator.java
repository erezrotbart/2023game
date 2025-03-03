//Erez Rotbart 316080589.
package Game;
import Sprite.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The type Score indicator.
 * task 5.
 */
public class ScoreIndicator implements Sprite {

    // the color of the ball
    private java.awt.Color color;
    private int x;
    private int y;
    private String score;
    private GameLevel gameLevel;

    /**
     * The Middle x of the gameLevel.
     */
    static final int MIDDLE = 400;
    /**
     * The Above the gameLevel to show the score.
     */
    static final int ABOVE_THE_GAME = 20;
    /**
     * constant for the sentence above the gamescreen.
     */
    static final String SENTENCE = "Score: ";

    /**
     * Instantiates a new Score indicator.
     * <p>
     * the constructor
     * </p>
     * @param score the score
     * @param gameLevel  the gameLevel
     */
    public ScoreIndicator(Counter score, GameLevel gameLevel) {
        this.color = Color.BLACK;
        this.x = MIDDLE;
        this.y = ABOVE_THE_GAME;
        this.score = SENTENCE + String.valueOf(score.getValue());
        this.gameLevel = gameLevel;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.drawText(x, y, score, 20);
    }

    @Override
    public void timePassed() {
        //updating score
        this.score = SENTENCE + String.valueOf(gameLevel.getScore().getValue());
    }

    /**
     * Add to gameLevel.
     * <p>
     * this method is adding the score indicator to the gameLevel.
     * </p>
     * @param g the g
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
// end of score indicator
}
