package hw5;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * <b>Graph</> represents the concept of a directed, labeled multi-graph.
 * 
 * A graph consists of a collection of nodes that are connected by edges 
 * that go from one node to another. Each edge has a label containing
 * information, and pairs of nodes can have multiple edges between them.
 * 
 *
 * Abstract Invariant:
 * 	No two nodes in the graph can be equal (no to nodes can have
 *  the same label)
 *  No two edges in the graph can be equal, that is, no two 
 *  edges can originate from node A to node B and have 
 *  identical labels.	
 *
 */
public class Graph {
	
	/** A map storing each node in the graph as a key and sets of
	 *  edges	originating from each node as values */
	private final Map<String, Set<Edge>> adjacencyList;
	
	// Abstraction Function:
	//  A graph consists of nodes and directed edges between nodes such 
	//	that r.adjacencyList.keySet() is the set of strings which represent 
	//	nodes in the graph (with the string being the node's label),
	//	and if a node in the graph is represented by string n
	//	r.adjacencyList.get(n) is the list of Edge objects which represent
	//	edges directed away from node n to other nodes in the graph.

	// Representation invariant for every RatNum r:
	//   r.adjacencyList != null &&
	//	 for all n in r.adjacencyList.keySet():
	//		r.get(n) != null && r.get(n) cannot contain
	//		any null objects
	
	/** Checks the representation invariant */
	private void checkRep() {
		assert (adjacencyList != null) : "adjacencyList cannot be null";
		for (String n : adjacencyList.keySet()) {
			assert (n != null) : "node cannot be null";
			for (Edge e : adjacencyList.get(n)) {
				assert (e != null) : "edge in node " + n +
															" cannot be null";
			}
		}
	}
	
	/**
	 * Constructs an empty graph.
	 * @effects Constructs a new empty graph
	 */
	public Graph() {
		adjacencyList = new TreeMap<String, Set<Edge>>();
		checkRep();
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
		checkRep();
		adjacencyList.put(label, new TreeSet<Edge>());
		checkRep();
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
		checkRep();
		return adjacencyList.containsKey(label);
	}
	
	/**
	 * Adds an edge to the graph with the given from-node, to-node,
	 * and label, provided that such an edge does not already
	 * exist within the graph. Does nothing if such an edge exists.
	 * If the given fromNode or toNode does not previously exist,
	 * adds those nodes to the graph before adding the edge.
	 * 
	 * @requires fromNode != null && toNode != null && label != null
	 * @param fromNode the node from which the new edge extends
	 * @param toNode the node which the new edge leads to
	 * @param label the label associated with the new edge
	 * @modifies this
	 * @effects If the given fromNode or toNode to not previously exist,
	 * adds those nodes to the graph. if given edge does not already 
	 * exist within the graph, adds such an edge to the graph. 
	 */
	public void addEdge(String fromNode, String toNode, 
											String label) {
		if (!isNode(fromNode)) {
			adjacencyList.put(fromNode, new TreeSet<Edge>());
		}
		if (!isNode(toNode)) {
			adjacencyList.put(toNode, new TreeSet<Edge>());
		}
		// TODO
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
		if (!isNode(fromNode) || !isNode(toNode)) {
			return false;
		}
		for (Edge e : adjacencyList.get(fromNode)) {
			if (e.getToNode().equals(toNode)) {
				return true;
			}
		}
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
	 * @requires fromNode != null && isNode(fromNode)
	 * @param fromNode the node from which the edge(s) extend(s)
	 * @return Returns a list of strings representing edges 
	 * 	originating from the given fromNode
	 */
	public SortedSet<String> getEdgesFrom(String fromNode) {
		if (!isNode(fromNode)) {
			throw new IllegalArgumentException();
		}
		SortedSet<String> result = new TreeSet<String>();
		for (Edge e : adjacencyList.get(fromNode)) {
			
		}
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
	 * Returns a set of strings representing nodes in the graph 
	 * sorted according to the lexicographic order of their labels.
	 * 
	 * @return A sorted set of strings representing nodes in the graph
	 */
	public SortedSet<String> getNodes() {
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
	 * @requires node != null && isNode(node)
	 * @return An unmodifiable sorted set of nodes in the graph are
	 * 	children of the given node
	 */
	public SortedSet<String> getChildren(String node) {
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
	private final class Node implements Comparable<Node>{
		
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

	/**
	 * <b>Edge</b> is an immutable representation of an edge
	 * in a directed, labeled graph that originates from a particular 
	 * node and leads to another particular node, 
	 * creating a parent child relationship between these nodes.
	 * 
	 * Specified fields: 
	 * @specfield from-node : string	// node from which the edge extends
	 * @specfield to-node : string // node to which the edge extends
	 * @specfield label : strings // labels of the edge
	 * 
	 * Abstract Invariant:
	 * 	An edge's from-node must be different from its to-node. Also, no
	 * edge from the same from-Node to to-Node can have the same label.  
	 *
	 */
	private class Edge {
		
		/** Node from which the edge extends */
	  private final String fromNode;

	  /** Node to which the edge extends */
	  private final String toNode;

	  /** The information associated with this edge */
	  private final String label;

	  // Representation Invariant:
	  //  this.fromNode != null && this.toNode != null &&
	  //	this.label != null &&
	  //	!this.fromNode.equals(this.toNode)
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
	   * @requires fromNode != null && toNode != null && label != null
	   * @effects Constructs a new Edge from the fromNode
	   *  to the toNode with the given label
	   */
		public Edge(String toNode, String fromNode, String label) {
			if (label == null || toNode == null ||
					fromNode == null) {
				throw new IllegalArgumentException();
			}
			this.toNode = toNode;
			this.fromNode = fromNode;
			this.label = label;
			checkRep();
		}
		
		/**
		 * @requires this != null
		 * @return The node from which this edge originates
		 * 
		 */
		public String getFromNode() {
			checkRep();
			return fromNode;
		}
		
		/**
		 * @requires this != null
		 * @return The node to which this edge extends
		 * 
		 */
		public String getToNode() {
			checkRep();
			return toNode;
		}
		
		/**
		 * Returns the label of this edge
		 * 
		 * @return The label associated with this edge
		 * 
		 */
		public String getLabel() {
			checkRep();
			return label;
		}
		
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
	  	if (obj instanceof Edge) {
	      Edge edge = (Edge) obj;

	      // Edges are equal if fromNode and toNode correspond
	      return fromNode.equals(edge.fromNode) && 
	      		toNode.equals(edge.fromNode) && 
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
	  	return 171 * fromNode.hashCode() + 13 *toNode.hashCode() + 
	  			label.hashCode();
	  }
	}
}
