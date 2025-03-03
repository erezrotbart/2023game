package Screens;

import Game.Animation;
import biuoop.DrawSurface;

import java.awt.*;

/**
 * The type End screen.
 */
public class LoseScreen implements Animation {
    private boolean stop;
    private String finalScore;

    /**
     * Instantiates a new Pause screen.
     *
     * @param finalScore the final score
     */
    public LoseScreen(int finalScore) {
        this.stop = false;
        this.finalScore = String.valueOf(finalScore);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.red);
        d.drawText(10, d.getHeight() / 2, "Game Over. Your score is "
                + finalScore, 32);
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
