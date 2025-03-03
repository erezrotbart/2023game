//erez rotbart 316080589
package Game;
import biuoop.GUI;
import biuoop.DrawSurface;

/**
 * The type Animation runner.
 * task6
 */
public class AnimationRunner {
    //variables for AnimationRunner
    private GUI gui;
    private int framesPerSecond;
    private biuoop.Sleeper sleeper;

    /**
     * Instantiates a new Animation runner.
     * <p>
     * the builder of the class
     * </p>
     * @param gui the gui
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
        this.framesPerSecond = 60;
        this.sleeper = new biuoop.Sleeper();
    }

    /**
     * Run.
     * <p>
     * this method id responsible to run the animation of a class that
     * implements Animation on the screen
     * </p>
     * @param animation the animation
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        //run the animation on the screen until should-stop condition happened
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            //calling the drawing method of the animation and show on screen
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
// end AnimationRunner.
}
