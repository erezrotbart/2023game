//Erez Rotbart 316080589
package Screens;
import Game.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The type Key press stoppable animation.
 * task 6
 * <p>
 * this class is a decorator for screens that can pop in a game.
 * </p>
 */
public class KeyPressStoppableAnimation implements Animation {
    // The object that is being decorated
    private Animation animation;
    private biuoop.KeyboardSensor keyboard;
    private String key;
    private boolean stop;
    private boolean isAlreadyPressed;
    /**
     * Instantiates a new Key press stoppable animation.
     * <p>
     * the builder of KeyPressStoppableAnimation.
     * </p>
     * @param keyboard    the sensor
     * @param key       the key
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor keyboard, String key,
                                      Animation animation) {
        this.keyboard = keyboard;
        this.animation = animation;
        this.key = key;
        this.stop = false;
        this.isAlreadyPressed = true;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        //presenting the correct screen on the game.
        animation.doOneFrame(d);
        //fixing a bug of skipping screens.
        if (!this.isAlreadyPressed && this.keyboard.isPressed(key)) {
            this.stop = true;
        }
        this.isAlreadyPressed = false;
    //end of doOneFrame
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
// end of KeyPressStoppableAnimation
}
