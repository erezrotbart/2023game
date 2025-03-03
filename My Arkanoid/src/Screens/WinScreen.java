package Screens;
//erez rotbart 316080589
import Game.Animation;
import biuoop.DrawSurface;

import java.awt.*;

/**
 * The type Win screen.
 */
public class WinScreen implements Animation {
    private biuoop.KeyboardSensor keyboard;
    private boolean stop;
    private String finalScore;

    /**
     * Instantiates a new Pause screen.
     *
     * @param finalScore the final score
     */
    public WinScreen(int finalScore) {
        this.stop = false;
        this.finalScore = String.valueOf(finalScore);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.GREEN);
        d.drawText(10, d.getHeight() / 2, "You Win! Your score is "
                + finalScore, 32);
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
