//Erez Rotbart 316080589.
package Collision;
/**
 * The interface Hit notifier.
 * task 5.
 */
public interface HitNotifier {
    /**
     * Add hit listener.
     * <p>
     * this method is responsible for adding HitListener in every class that
     * implements HitNotifier.
     * </p>
     * @param hl the hl
     */
    void addHitListener(HitListener hl);
    /**
     * Remove hit listener.
     * <p>
     * this method is responsible for removing HitListener in every class that
     * implements HitNotifier.
     * </p>
     * @param hl the hl
     */
    void removeHitListener(HitListener hl);
}
