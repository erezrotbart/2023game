//erez rotbart 316080589
package Game;
import biuoop.DrawSurface;
/**
 * The interface Animation.
 * task6
 */
public interface Animation {
    /**
     * Do one frame.
     * <p>
     * this method is responsible in every animation to draw the animation ti
     * gets on the screen.
     * </p>
     * @param d the d
     */
    void doOneFrame(DrawSurface d);

    /**
     * Should stop boolean.
     * <p>
     * this method is responsible to send the stop command to to the class
     * that use the animation to make it stop in the correct time
     * </p>
     * @return the boolean
     */
    boolean shouldStop();
//end of animation
}
