package hw5;

import java.util.Comparator;

/**
 * This class compares Edge objects with
 * comparable data types according to the natural
 * ordering of its data. 
 *
 */
public class EdgeComparator<T1 extends Comparable<T1>,T2 extends Comparable<T2>> 
			implements Comparator<Edge<T1, T2>> {
	
	/**
	 * Compares this Edge to other according to the natural ordering
	 * of its fields; first the from-Node, then the to-Node, then 
	 * the label. Returns a negative integer if
	 * the data of this edge precedes the other edge's data
	 * returns a positive integer if the label
	 * of this edge follows the other edge's data
	 * and returns zero if the this edge's data is
	 * equal to the other edge's data
	 * 
	 * @throws IllegalArgumentException if other == null
	 * @return Returns a negative integer if the data of this 
	 * 	edge precedes the other edge's data. Returns a positive 
	 * 	integer if the label of this edge follows the other edge's 
	 * 	data and returns zero if the this edge's data is
	 * 	equal to the other edge's data
	 */
	public int compare(Edge<T1, T2> e1, Edge<T1, T2> e2) {
		if (e1 == null || e2 == null) {
			throw new IllegalArgumentException();
		}
		if (!e1.getFromNode().equals(e2.getFromNode())) {
			return e1.getFromNode().compareTo(e2.getFromNode());
		} else if (!e1.getToNode().equals(e2.getToNode())) {
			return e1.getToNode().compareTo(e2.getToNode());
		} else {
			return e1.getLabel().compareTo(e2.getLabel());
		}
	}

}
