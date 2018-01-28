package hw5;
/**
 * <b>Edge</b> is an immutable representation of an edge in a directed, 
 * labeled graph. An edge originates from a node and leads to
 * another node, creating a parent child relationship between nodes.
 * 
 * Specified fields: 
 * @specfield from-node : node	// node from which the edge extends
 * @specfield to-node : node // node to which the edge extends
 * @specfield label : string // information associated with the edge
 * 
 * Abstract Invariant:
 * 	An edge's from-node must be different from its to-node
 *
 */
public class Edge {
	
	/** Node from which the edge extends */
  private final Node fromNode;

  /** Node to which the edge extends */
  private final Node toNode;

  /** The information associated with this edge */
  private final String label;

  // Representation Invariant:
  //  this.fromNode != null && this.toNode != null &&
  //	this.label != null &&
  //	!this.fromNode.getData().equals(this.toNode.getData()) 
  //
  // Abstraction Function:
  //	AF(r) = an edge, e such such that
  //	r.toNode = e.to-Node
  //	r.fromNode = e.from-Node
  //	r.label = e.label
  
  /**
   * @param fromNode The parent node of the new edge
   * @param toNode The child node of the new edge
   * @param label The information associated with the edge
   * @requires fromNode != null && toNode != null
   * @effects Constructs a new Edge from the node fromNode
   *  to the node toNode with the given label
   */
	public Edge(Node toNode, Node fromNode, String label) {
		this.toNode = toNode;
		this.fromNode = fromNode;
		this.label = label;
	}
	
	/**
	 * @requires this != null
	 * @return The node from which this edge originates
	 * 
	 */
	public Node getFromNode() {
		return null;
	}
	
	/**
	 * @requires this != null
	 * @return The node to which this edge extends
	 * 
	 */
	public Node getToNode() {
		return null;
	}
	
	/**
	 * @requires this != null
	 * @return The label associated with this edge 
	 * 
	 */
	public String getLabel() {
		return null;
	}
	
	/**
	 * Returns a string representation of this edge. Returns
	 * a string of the form:
	 * "(<i>from-Node.data</i>, <i>label</i>, <i>to-Node.data</i>)"
	 * (String does not contain quotation marks)
	 * 
	 * @returns Returns a string representation of this edge
	 */
	public String toString() {
		return null;
	}

}
