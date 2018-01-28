package hw5;
/**
 * This immutable class represents the concept of an edge in a directed, 
 * labeled graph
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
	
	/** The parent node of the edge */
  private Node fromNode;

  /** The child node of the edge */
  private Node toNode;

  /** The information associated with this edge */
  private String label;

  // Representation Invariant:
  //  ! (startX == endX && startY == endY)
  //
  // Abstraction Function:
  //  AF(r) = a line, l, such that
  //   l.start-point = ⟨r.startX, r.startY⟩
  //   l.end-point = ⟨r.endX, r.endY⟩
  
  /**
   * @param fromNode The parent node of the new edge
   * @param toNode The child node of the new edge
   * @param label The information associated with the edge
   * @requires fromNode != null && toNode != null
   * @effects Constructs a new Edge from the node fromNode
   *  to the node toNode with the given label
   */
	public Edge() {
		// TODO Auto-generated constructor stub
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

}
