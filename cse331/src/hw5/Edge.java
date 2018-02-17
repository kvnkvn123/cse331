package hw5;

/**
 * <b>Edge</b> is an immutable representation of an edge
 * in a directed, labeled graph that originates from a particular 
 * node and leads to another particular node, 
 * creating a parent child relationship between these nodes.
 * 
 * Specified fields: 
 * @specfield from-node : T1	// node from which the edge extends
 * @specfield to-node : T1 // node to which the edge extends
 * @specfield label : T2 // labels of the edge
 * 
 * Abstract Invariant:
 * 	No edge from the same from-Node to to-Node can have the same label.  
 *
 */
public final class Edge<T1,T2> {
																			
	/** Node from which the edge extends */
	private final T1 fromNode;

	/** Node to which the edge extends */
	private final T1 toNode;

	/** The information associated with this edge */
	private final T2 label;

	// Representation Invariant:
	//  this.fromNode != null && this.toNode != null &&
	//	this.label != null
	//
	// Abstraction Function:
	//	AF(r) = an in a graph, such that
	//	r.toNode = node to which the edge extends
	//	r.fromNode = node from which the edge originates
	//	r.label = label of the edge
  
	/** Checks the representation invariant */
	private void checkRep() {
		assert (this.toNode != null) : "toNode cannot be null";
		assert (this.fromNode != null) : "fromNode cannot be null";
		assert (this.label != null) : "label cannot be null";
	}
  
	/**
	 * @param fromNode The node from which the new edge extends
	 * @param toNode The node to which the new edge extends
	 * @param label The label associated with the new edge
	 * @throws IllegalArgumentException if 
	 * 	fromNode == null || toNode == null || label == null
	 * @effects Constructs a new Edge from the fromNode
	 *  to the toNode with the given label
	 */
	public Edge(T1 fromNode, T1 toNode, T2 label) {
		if (label == null || toNode == null ||
				fromNode == null) {
			throw new IllegalArgumentException();
		}
		this.fromNode = fromNode;
		this.toNode = toNode;
		this.label = label;
		checkRep();
	}
	
	/**
	 * @requires this != null
	 * @return The node from which this edge originates
	 * 
	 */
	public T1 getFromNode() {
		checkRep();
		return fromNode;
	}
	
	/**
	 * @requires this != null
	 * @return The node to which this edge extends
	 * 
	 */
	public T1 getToNode() {
		checkRep();
		return toNode;
	}
	
	/**
	 * Returns the label of this edge
	 * 
	 * @return The label associated with this edge
	 * 
	 */
	public T2 getLabel() {
		checkRep();
		return label;
	}
	
/*		*//**
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
	 * 	edge precedes the other edge's data returns a positive 
	 * 	integer if the label of this edge follows the other edge's 
	 * 	data and returns zero if the this edge's data is
	 * 	equal to the other edge's data
	 *//*
	public int compareTo(Edge<T1, T2> other) {
		checkRep();
		if (other == null) {
			throw new IllegalArgumentException();
		}
		if (!fromNode.equals(other.fromNode)) {
			return fromNode.compareTo(other.fromNode);
		} else if (!toNode.equals(other.toNode)) {
			return toNode.compareTo(other.toNode);
		} else {
			return label.compareTo(other.label);
		}
	}*/
	
	/**
	 * Standard equality operation.
   	 *
   	 * @param obj The object to be compared for equality.
   	 * @return true iff 'obj' is an instance of an Edge and 
   	 * 	'this' and 'obj' have the same from-Node and to-Node.
   	 */
	@Override
	public boolean equals(Object obj) {
		checkRep();
		if (obj instanceof Edge<?,?>) {
			Edge<?,?> edge = (Edge<?,?>) obj;

			// Edges are equal if fromNode and toNode correspond
			// and label correspond
			return fromNode.equals(edge.fromNode) && 
					toNode.equals(edge.toNode) && 
					label.equals(edge.label);
		} else {
			return false;
		}
	}
  
	/** 
	 * Standard hashCode function.
	 * @return an int that all objects equal to this will also
	 * 	return.
	 */
	@Override
	public int hashCode() {
		checkRep();
		return (171 * fromNode.hashCode()) + (19 * toNode.hashCode()) + 
  			label.hashCode();
	}
}
