/**
 * This is part of HW0: Environment Setup and Java Introduction for CSE 331.
 */
package hw3;

import java.util.Comparator;

/**
 * This is a simple object compares Ball objects
 * according to volume
 */
public class VolumeComparator implements Comparator<Ball>{
    
	/**
     * Compares the volume of the given Ball objects
     * @param b1, b2 two Ball objects to be compared
     * @requires b!= null
     * @return -1 if volume of b1 is less than volume of b2
     * 1 if the volume of b1 is greater than volume of b2
     * 0 if volumes are equal
     * 
     */
	public int compare(Ball b1, Ball b2) {
    	double diff = b1.getVolume() - b2.getVolume();
    	if (diff < 0) {
    		return -1;
    	} else if (diff > 0) {
    		return 1;
    	} else {
    		return 0;
    	}
    }
}

    
