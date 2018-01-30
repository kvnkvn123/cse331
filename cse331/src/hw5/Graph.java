package hw5;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * <b>Graph</> represents the concept of a directed, labeled multi-graph.
 * 
 * A graph consists of a collection of nodes that are connected by edges 
 * that go from one node to another. Each edge has a label containing
 * information, and pairs of nodes can have multiple edges between them.
 * 
 *
 * Abstract Invariant:
 * 	No two nodes in the graph can be equal (no two nodes can 
 * 	exist that are associated with identical data)
 *  No two edges in the graph can be equal, that is, no two 
 *  edges can originate from node A to node b and have 
 *  identical labels.	
 *
 */
public class Graph {
	
	/** A map storing each node in the graph as a key and sets of
	 *  edges	originating from each node as values */
	private final Map<Node, List<Edge>> adjacencyList;
	
	// Abstraction Function:
	//  A graph consists of nodes and directed edges between nodes such 
	//	that r.adjacencyList.keySet() is the set of Nodes which represent 
	//	nodes in the graph, and if n is a node in the graph, 
	//	r.adjacencyList.get(n) is the list of Edge objects which represent
	//	edges directed away from node n to other nodes in the graph.

	// Representation invariant for every RatNum r:
	//   r.adjacencyList != null &&
	//	 for all Nodes n is r.adjacencyList.keySey()
	//	 
	//   (r.denom > 0 ==> there does not exist integer i > 1 such that
	//                    r.numer mod i = 0 and r.denom mod i = 0;)
	//   In other words,
	//     * r.denom is always non-negative.
	//     * r.numer/r.denom is in reduced form (assuming r.denom is not zero).
	// (A representation invariant tells us something that is true for all
	// instances of a RatNum)
	/**
	 * Constructs an empty graph.
	 * @effects Constructs a new empty graph
	 */
	public Graph() {
		adjacencyList = new TreeMap<Node, List<Edge>>();
	}
	
	/**
	 * Adds a node to the graph with the given label, provided
	 * a node with the given label does not already exist within
	 * the graph. Does nothing if such a node exists.
	 * 
	 * @requires label != null
	 * @param label the label of the node to be added
	 * @modifies this
	 * @effects if a node with the given label does not
	 *  already exist within the graph, adds a new node with 
	 *  the given label to this
	 * 
	 */
	public void addNode(String label) {
	}
	
	/**
	 * Returns true if a node with the given label exists
	 * within the graph
	 * 
	 * @requires label != null
	 * @param label the label associated with the node whose existence
	 * 	is being tested for
	 * @return true if a node with the given label exists
	 * within the graph, returns false otherwise
	 * 
	 */
	public boolean isNode(String label) {
		return false;
	}
	
	/**
	 * Adds an edge to the graph with the given from-node, to-node,
	 * and label, provided that such an edge does not already
	 * exist within the graph. Does nothing if such an edge exists.
	 * 
	 * @requires fromNode != null && toNode != null && label != null
	 * @param fromNode the node from which the new edge extends
	 * @param toNode the node which the new edge leads to
	 * @param label the label associated with the new edge
	 * @modifies this
	 * @effects if given edge does not already exist within the graph,
	 *  adds such an edge to the graph. 
	 */
	public void addEdge(String fromNode, String toNode, 
											String label) {
		
	}
	
	/**
	 * Returns true if there is an edge that extends from 
	 * fromNode to toNode. Returns false otherwise
	 * 
	 * 
	 * @requires fromNode != null && toNode != null &&
	 * 	isNode(fromNode) && isNode(toNode)
	 * @param fromNode node from which edge might extend
	 * @param toNode the node to which edge might lead
	 * @return Returns true if there is an edge that extends from 
	 * 	fromNode to toNode. Returns false otherwise
	 */
	public boolean isEdgeBetween(String fromNode, String toNode) {
		return false;
	}
	
	/**
	 * Returns a list of strings representing edges originating 
	 * from the given fromNode, sorted alphabetically first
	 * according to the nodes they extend to, and then according
	 * to the labels of the edges (for multiple edges between the
	 * nodes). Strings are of the form 
	 * "<i>toNode</i>(<i>edgeLabel</i>)"
	 * where toNode represents the node to which the edge extends
	 * and edgeLabel represents the label of the edge
	 * 
	 * @requires fromNode != null
	 * @param fromNode the node from which the edge(s) extend(s)
	 * @return Returns a list of strings representing edges 
	 * 	originating from the given fromNode
	 */
	public List<String> listEdgesFrom(String fromNode) {
		return null;
	}
	
	/**
	 * Returns true if there is an edge that extends from 
	 * fromNode to toNode with the given label. Returns false 
	 * otherwise.
	 * 
	 * 
	 * @requires fromNode != null && toNode != null &&
	 * 	label != null &&	isNode(fromNode) && isNode(toNode)
	 * @param fromNode the node from which the possible edge extends
	 * @param toNode the node to which the possible edge leads to
	 * @param label the label of the edge whose existence is
	 * 	being determined
	 * @return Returns true if there is an edge that extends from 
	 * 	fromNode to toNode with the given label. Returns 
	 * 	false otherwise
	 */
	private boolean isEdgeBetween(String fromNode, String toNode, 
														String label) {
		return false;		
	}
	
	/**
	 * Returns a list of strings representing nodes in the graph 
	 * sorted according to the lexicographic order of their labels.
	 * 
	 * @return A list of strings representing nodes in the graph
	 */
	public List<String> getNodes() {
		return null;		
	}
	
	/**
	 * Returns a list of strings representing edges of the graph, 
	 * sorted alphabetically unmodifiable or read-only set of edges in the 
	 * graph which originate from the given node. Any attempts to
	 * modify this set will result in an UnsupportedOperationException.
	 * 
	 * @return An unmodifiable set of edges in the graph which
	 * 	originate from the given node
	 *//*
	public Set<Edge> getEdges() {
		return null;
	}*/
	
	/**
	 * Returns an unmodifiable or read-only sorted set of nodes in the 
	 * graph which are children of the given node. In other words
	 * the nodes in the returned set have edges extending from the 
	 * given node to them.Any attempts to modify this set will result
	 * in an UnsupportedOperationException.
	 * 
	 * @requires given node is already contained in the graph
	 * @return An unmodifiable sorted set of nodes in the graph are
	 * 	children of the given node
	 */
	public List<String> getChildren(Node node) {
		return null;
	}
	
	/**
	 * <b>Node</b> is an immutable representation of a node in a 
	 * directed, labeled graph. A node is connected to other nodes
	 * through edges, and can have parent-child relationships
	 * with other nodes.
	 *
	 *  @specfield label : string // the label associated with this node
	 *
	 */
	private class Node implements Comparable<Node>{
		
		/** Data associated with this mode */
		private final String label;
		
		// Representation Invariant:
		//  this.label != null
		//
		// Abstraction Function:
		//  AF(r) = a node, such that
		//	r.label = label of the node
		
		/**
		 * @param label the label of the node
		 * @requires label != null
		 * @effects Constructs a new node with the given label
		 */
		public Node(String label) {
			this.label = label;
		}
		
		/**
		 * Returns the label of this node
		 * 
		 * @return the label of this node
		 */
		public String getLabel() {
			return null;
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
			return 0;
		}
		
		/**
	   * Standard equality operation.
	   *
	   * @param obj The object to be compared for equality.
	   * @return true iff 'obj' is an instance of a Node and 'this' and 'obj'
	   *         represent Nodes containing the same label.
	   */
	  @Override
	  public boolean equals(/*@Nullable**/ Object obj) {
	  	return false;
	  }
	/*  if (obj instanceof Node) {
	      Node test = (Node) obj;
	      return (this.data.equals(test.data));
	    } else {
	      return false;
	    }
	  }
	*/
	  
	  /** Standard hashCode function.
	  @return an int that all objects equal to this will also
	  return.
	   */
	  @Override
	  public int hashCode() {
	  	return 0;
	  }/*
	  	// all instances that are NaN must return the same hashcode;
	  	if (this.isNaN()) {
	  		return 0;
	  	}
	  	return (this.numer*2) + (this.denom*3);
	  }*/
	}

	/**
	 * <b>Edge</b> is a representation of one or more edges
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
	private class Edge {
		
		/** Node from which the edge extends */
	  private final Node fromNode;

	  /** Node to which the edge extends */
	  private final Node toNode;

	  /** The information associated with this edge */
	  private final Set<String> labels;

	  // Representation Invariant:
	  //  this.fromNode != null && this.toNode != null &&
	  //	this.labels != null && this.labels.size() > 0 &&
	  //	!this.fromNode.getlabel().equals(this.toNode.getlabel()) 
	  //
	  // Abstraction Function:
	  //	AF(r) = one or more edges in a graph, such that
	  //	r.toNode = node to which the edge(s) extend
	  //	r.fromNode = node from which the edge(s) originate
	  //	r.labels.size() = number of edge(s) from from-node
	  //									 to the to-node
	  // 	each string in r.labels represents the label of an
	  //	edge between fromNode and toNode
	  
	  /**
	   * @param fromNode The parent node of the new edge
	   * @param toNode The child node of the new edge
	   * @param label The information associated with the edge
	   * @requires fromNode != null && toNode != null
	   * @effects Constructs a new Edge from the node fromNode
	   *  to the node toNode with the given label
	   */
		public Edge(Node toNode, Node fromNode, Set<String> labels) {
			this.toNode = toNode;
			this.fromNode = fromNode;
			this.labels = labels;
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
		 * Returns an unmodifiable or read-only set of strings
		 * which are the labels of the edges this Edge represents.
		 * The set is sorted according to the lexicographic ordering
		 * of the labels. Any attempts to modify this set will result
		 * in an UnsupportedOperationException.
		 * 
		 * @return Returns an unmodifiable or read-only set of strings
		 * which are the labels of the edges this Edge represents.
		 * The set is sorted according to the lexicographic ordering
		 * of the labels.
		 * 
		 */
		public String getLabels() {
			return null;
		}
		
		/**
	   * Standard equality operation.
	   *
	   * @param obj The object to be compared for equality.
	   * @return true iff 'obj' is an instance of an Edge and 
	   * 	'this' and 'obj' have the same from-Node and to-Node.
	   */
	  @Override
	  public boolean equals(/*@Nullable**/ Object obj) {
	  	return false;
	  }
	/*  if (obj instanceof Node) {
	      Node test = (Node) obj;
	      return (this.data.equals(test.data));
	    } else {
	      return false;
	    }
	  }
	*/
	  
	  /** 
	   * Standard hashCode function.
	   * @return an int that all objects equal to this will also
	   * 	return.
	   */
	  @Override
	  public int hashCode() {
	  	return 0;
	  }/*
	  	// all instances that are NaN must return the same hashcode;
	  	if (this.isNaN()) {
	  		return 0;
	  	}
	  	return (this.numer*2) + (this.denom*3);
	  }*/

	}
}
