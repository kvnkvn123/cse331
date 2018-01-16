/**
 * This is part of HW0: Environment Setup and Java Introduction for CSE 331.
 */
package hw3;

import java.util.comparator;

/**
 * This is a simple object that has a volume.
 */
// You may not make Ball implement the Comparable interface.
public class Ball implements Comparator<Ball> {

    private double volume;

    /**
     * Constructor that creates a new ball object with the specified volume.
     * @param volume Volume of the new object.
     */
    public Ball(double volume) {
        this.volume = volume;
    }

    /**
     * Returns the volume of the Ball.
     * @return the volume of the Ball.
     */
    public double getVolume() {
        return this.volume;
    }
    
    public int compare(Ball b1, Ball b2) {
    	double diff = b1.volume - b2.volume;
    	if (diff < 0) {
    		return -1;
    	} else if (diff > 0) {
    		return 1;
    	} else {
    		return 0;
    	}
    }

}
