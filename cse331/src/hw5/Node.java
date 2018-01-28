package hw5;

import hw4.RatTerm;

/**
 * <b>Node</b> is an immutable representation of a node in a 
 * directed, labeled graph. A node is connected to other nodes
 * through edges, and can have parent-child relationships
 * with other nodes.
 *
 * Specification fields:
 *  @specfield data : string // the data associated with this node
 *
 */
public class Node implements Comparable<Node>{
	
	/** Data associated with this mode */
	private final String data;
	
	// Representation Invariant:
	//  this.data != null
	//
	// Abstraction Function:
	//  AF(r) = a node, such that
	//	r.data = data associated with the node
	
	/**
	 * @param data the data associated with this node
	 * @requires data != null
	 * @effects Constructs a new node containing the given
	 *		data
	 */
	public Node(String data) {
		this.data = data;
	}
	
	/**
	 * Returns the data associated with this node
	 * @return the data associated with this node
	 */
	public String getData() {
		return null;
	}
	
	/**
	 * Compares this node to other according to the lexicographic
	 * order of the data they store. Returns an integer based on
	 * this comparison. 
	 * 
	 * @requires other != null
	 * @return Returns a negative integer if
	 * the data stored in this node precedes the other node's data 
	 * lexicographically, returns a negative integer if the data
	 * the data stored in this node follows the other node's data,
	 * and returns zero if the this node's data is lexicographically
	 * equal to the other node's data.
	 */
	public int compareTo(Node other) {
		return 0;
	}
	
	/**
   * Standard equality operation.
   *
   * @param obj The object to be compared for equality.
   * @return true iff 'obj' is an instance of a Node and 'this' and 'obj'
   *         represent Nodes containing the same data.
   */
  @Override
  public boolean equals(/*@Nullable*/ Object obj) {
    if (obj instanceof Node) {
      Node test = (Node) obj;
      return (this.data.equals(test.data));
    } else {
      return false;
    }
  }

}
