//erez rotbart 316080589
package Screens;
import Game.Animation;
import biuoop.DrawSurface;

/**
 * The type Pause screen.
 * <p>
 * this class is responsible for the pause screen
 * task 6
 * </p>
 */
public class PauseScreen implements Animation {
    //variable for pausescreen
    private boolean stop;

    /**
     * Instantiates a new Pause screen.
     * <p>
     * builder for pausescreen.
     * </p>
     */
    public PauseScreen() {
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to"
                + "continue", 32);
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
//end of pause-screen
}
