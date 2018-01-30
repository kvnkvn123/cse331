package hw5;

import java.util.Set;
import java.util.TreeSet;

/**
 * <b>Edge</b> is an immutable representation of one or more edges
 * in a directed, labeled graph that originate from a particular 
 * node and leads to another particular node, 
 * creating a parent child relationship between these nodes.
 * 
 * Specified fields: 
 * @specfield from-node : node	// node from which the edge extends
 * @specfield to-node : node // node to which the edge extends
 * @specfield labels : list of strings // list of labels of each of 
 * 																		// the edges this object represents
 * 
 * Abstract Invariant:
 * 	An edge's from-node must be different from its to-node. Also, the
 * labels list cannot be empty; in other words, an edge object must
 * represent at least one edge in the graph
 *
 */
public class Edge {
	
	/** Node from which the edge extends */
  private final Node fromNode;

  /** Node to which the edge extends */
  private final Node toNode;

  /** The information associated with this edge */
  private final Set<String> label;

  // Representation Invariant:
  //  this.fromNode != null && this.toNode != null &&
  //	this.label != null && this.label.size() > 0 &&
  //	!this.fromNode.getData().equals(this.toNode.getData()) 
  //
  // Abstraction Function:
  //	AF(r) = one or more edges in a graph, such that
  //	r.toNode = node to which the edge(s) extend
  //	r.fromNode = node from which the edge(s) originate
  //	r.label.size() = number of edge(s) from from-node
  //									 to the to-node
  
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
