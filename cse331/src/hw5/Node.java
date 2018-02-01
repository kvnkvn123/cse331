package hw5;

/**
 * <b>Node</b> is an immutable representation of a node in a 
 * directed, labeled graph. A node is connected to other nodes
 * through edges, and can have parent-child relationships
 * with other nodes.
 *
 *  @specfield label : string // the label associated with this node
 *
 */
public final class Node implements Comparable<Node>{
	
	/** Data associated with this mode */
	private final String label;
	
	// Representation Invariant:
	//  this.label != null
	//
	// Abstraction Function:
	//  AF(r) = a node, such that
	//	r.label = label of the node
	
	/** Checks the representation invariant */
	private void checkRep() {
		assert (this.label != null) : "Label in a Node cannot equal null";
	}
	
	/**
	 * @param label the label of the node
	 * @requires label != null
	 * @effects Constructs a new node with the given label
	 */
	public Node(String label) {
		if (label == null) {
			throw new IllegalArgumentException();
		}
		this.label = label;
		checkRep();
	}
	
	/**
	 * Returns the label of this node
	 * 
	 * @return the label of this node
	 */
	public String getLabel() {
		checkRep();
		return label;
	}
	
	/**
	 * Compares this node to other according to the lexicographic
	 * order of the data they store. Returns a negative integer if
	 * the label of this node precedes the other node's label
	 * lexicographically, returns a negative integer if the label
	 * of this node follows the other node's label lexicographically,
	 * and returns zero if the this node's label is lexicographically
	 * equal to the other node's label.
	 * 
	 * @requires other != null
	 * @return Returns a negative integer if
	 * the label of this node precedes the other node's label
	 * lexicographically, returns a negative integer if the label
	 * of this node follows the other node's label lexicographically,
	 * and returns zero if the this node's label is lexicographically
	 * equal to the other node's label.
	 */
	public int compareTo(Node other) {
		checkRep();
		return label.compareTo(other.label);
	}
	
	/**
   * Standard equality operation.
   *
   * @param obj The object to be compared for equality.
   * @return true iff 'obj' is an instance of a Node and 'this' and 'obj'
   *         represent Nodes containing the same label.
   */
  @Override
  public boolean equals(Object obj) {
  	checkRep();
  	if (obj instanceof Node) {
      Node test = (Node) obj;
      return (this.label.equals(test.label));
    } else {
      return false;
    }
  }
  
  /** Standard hashCode function.
  @return an int that all objects equal to this will also
  return.
   */
  @Override
  public int hashCode() {
  	checkRep();
  	return 137 + this.label.hashCode();
  }
}