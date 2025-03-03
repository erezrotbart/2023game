package Game;

/**
 * The type Counter.
 * task 5.
 */
public class Counter {
    private int counter;

    /**
     * Instantiates a new Counter.
     * <p>
     * constructor
     * </p>
     * @param number the number
     */
    public Counter(int number) {
        this.counter = number;
    }
    /**
     * Increase.
     * <p>
     * this method increasing the number in this.counter
     * </p>
     * @param number the number
     */
    public void increase(int number) {
        this.counter += number;
    }

    /**
     * Decrease.
     * <p>
     * this method decreasing the number in this.counter
     * </p>
     * @param number the number
     */
    public void decrease(int number) {
        this.counter -= number;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public int getValue() {
        return this.counter;
    }
// end of Counter
}
