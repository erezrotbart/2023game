//Erez Rotbart 316080589
package Screens;
import Game.Animation;
import Sprite.SpriteCollection;
import biuoop.DrawSurface;
import java.awt.*;

/**
 * The type Countdown animation.
 * task 6
 * <p>
 * this class is making a countdown cefore a start of every level
 * </p>
 */
public class CountdownAnimation implements Animation {
    private SpriteCollection gameScreen;
    private double numOfSeconds;
    private int countFrom;
    private boolean stop;
    private long secondsLeft;
    private biuoop.Sleeper sleeper;
    /**
     * Instantiates a new Countdown animation.
     * <p>
     * the builder of CountdownAnimation
     * </p>
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom,
                              SpriteCollection gameScreen) {
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.numOfSeconds = numOfSeconds;
        this.stop = false;
        //the seconds a number needs to appear on the screen
        this.secondsLeft = 1000 * (long) numOfSeconds / (long) countFrom;
        this.sleeper = new biuoop.Sleeper();
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        //showing the countdown at red.
        d.setColor(Color.RED);
        d.drawText(400, 400, String.valueOf(countFrom), 40);
        countFrom--;
        //the animation ends when the countFrom is zero.
        if (this.countFrom == 0) {
            this.stop = true;
        }
    //end doOneFrame
    }
    @Override
    public boolean shouldStop() {
        /*
        this if exist in order to show the screen first and then wait for the
         seconds we need to wait.
         */
        if (countFrom < 3) {
            this.sleeper.sleepFor(secondsLeft);
        }
        return this.stop;
    }
//end of CountdownAnimation.
}
