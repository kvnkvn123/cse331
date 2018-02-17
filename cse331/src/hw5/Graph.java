package hw5;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

/**
 * <b>Graph</> represents the concept of a directed, labeled multi-graph.
 * 
 * A graph consists of a collection of nodes that are connected by edges 
 * that go from one node to another. Each edge has a label containing
 * information, and pairs of nodes can have multiple edges between them.
 * 
 * Nodes are represented by their data (or labels). Nodes have labels 
 * of type T1
 * 
 * Edges have labels of type T2
 *
 * Abstract Invariant:
 * 	No two nodes in the graph can be equal (no to nodes can have
 *  the same label)
 *  No two edges in the graph can be equal, that is, no two 
 *  edges can originate from node A to node B and have 
 *  identical labels.	
 *
 */
public class Graph<T1,T2> implements GeneralGraph<T1, T2> {
	
	/** A map storing each node in the graph as a key and sets of
	 *  edges originating from each node as values */
	private final Map<T1, Set<Edge<T1, T2>>> adjacencyList;
	
	/** Determines whether to perform a thorough check of the
	 * representation invariant: set to true when debugging */
	private static final boolean deepCheck = false;
	
	// Abstraction Function:
	//  A graph consists of nodes and directed edges between nodes such 
	//	that r.adjacencyList.keySet() is the set of T1s which represent 
	//	nodes in the graph (with the T1 being the node's label),
	//	and if a node in the graph is represented by T1 n
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
		if (deepCheck) {
			for (T1 n : adjacencyList.keySet()) {
				assert (n != null) : "node cannot be null";
				for (Edge<T1, T2> e : adjacencyList.get(n)) {
					assert (e != null) : "edge in node " + n +
								" cannot be null";
				}
			}
		}
	}
	
	/**
	 * Constructs an empty graph.
	 * @effects Constructs a new empty graph
	 */
	public Graph() {
		adjacencyList = new HashMap<T1, Set<Edge<T1, T2>>>();
		checkRep();
	}
	
	/**
	 * Adds a node to the graph with the given label, provided
	 * a node with the given label does not already exist within
	 * the graph. Does nothing if such a node exists.
	 * 
	 * @param label the label of the node to be added
	 * @modifies this
	 * @throws IllegalArgumentException if label == null
	 * @effects if a node with the given label does not
	 *  already exist within the graph, adds a new node with 
	 *  the given label to this
	 * 
	 */
	@Override
	public void addNode(T1 label) {
		checkRep();
		if (label == null) {
			throw new IllegalArgumentException();
		}
		if (!adjacencyList.containsKey(label))
			adjacencyList.put(label, new HashSet<Edge<T1, T2>>());
		checkRep();
	}
	
	/**
	 * Returns true if a node with the given label exists
	 * within the graph
	 * 
	 * @param label the label associated with the node whose existence
	 * 	is being tested for
	 * @throws IllegalArgumentException if label == null
	 * @return true if a node with the given label exists
	 * within the graph, returns false otherwise
	 * 
	 */
	@Override
	public boolean isNode(T1 label) {
		checkRep();
		if (label == null) {
			throw new IllegalArgumentException();
		}
		return adjacencyList.containsKey(label);
	}
	
	/**
	 * Adds an edge to the graph with the given from-node, to-node,
	 * and label, provided that such an edge does not already
	 * exist within the graph. Does nothing if such an edge exists.
	 * If the given fromNode or toNode does not previously exist,
	 * adds those nodes to the graph before adding the edge.
	 * 
	 * @param fromNode the node from which the new edge extends
	 * @param toNode the node which the new edge leads to
	 * @param label the label associated with the new edge
	 * @throws IllegalArgumentException if fromNode == null ||
	 * 	toNode == null || label == null
	 * @modifies this
	 * @effects If the given fromNode or toNode to not previously exist,
	 * adds those nodes to the graph. if given edge does not already 
	 * exist within the graph, adds such an edge to the graph. 
	 */
	@Override
	public void addEdge(T1 fromNode, T1 toNode, 
						T2 label) {
		checkRep();
		if (!isNode(fromNode)) {
			addNode(fromNode);
		}
		if (!isNode(toNode)) {
			addNode(toNode);
		}
		adjacencyList.get(fromNode).add(new Edge<T1, T2>(fromNode, toNode, label));
		checkRep();
	}
	
	/**
	 * Returns true if there is an edge that extends from 
	 * fromNode to toNode with the given label. Returns false 
	 * otherwise.
	 * 
	 * 
	 * @param fromNode the node from which the possible edge extends
	 * @param toNode the node to which the possible edge leads to
	 * @param label the label of the edge whose existence is
	 * 	being determined
	 * @throws IllegalArgumentException if !isNode(fromNode) || 
	 * 	!isNode(toNode)
	 * @return Returns true if there is an edge that extends from 
	 * 	fromNode to toNode with the given label. Returns 
	 * 	false otherwise
	 */
	@Override
	public boolean isEdgeBetween(T1 fromNode, T1 toNode, 
								T2 label) {
		checkRep();
		if (!isNode(fromNode) || !isNode(toNode)) {
			return false;
		}
		for (Edge<T1, T2> e : adjacencyList.get(fromNode)) {
			if (e.getToNode().equals(toNode) && 
					(label == null || e.getLabel().equals(label))) {
				return true;
			}
		}
		return false;	
	}
	
	/**
	 * Returns true if there is an edge that extends from 
	 * fromNode to toNode. Returns false otherwise
	 * 
	 * 
	 * @param fromNode node from which edge might extend
	 * @param toNode the node to which edge might lead
	 * @throws IllegalArgumentException if !isNode(fromNode) || 
	 * 	!isNode(toNode)
	 * @return Returns true if there is an edge that extends from 
	 * 	fromNode to toNode. Returns false otherwise
	 */
	@Override
	public boolean isEdgeBetween(T1 fromNode, T1 toNode) {
		return isEdgeBetween(fromNode, toNode, null);
	}
	
	/**
	 * Returns a list of Edges representing edges originating 
	 * from the given fromNode.  
	 * 
	 * @param fromNode the node from which the edge(s) extend(s)
	 * @throws IllegalArgumentException if !isNode(fromNode)
	 * @return Returns a list of Edges representing edges 
	 * 	originating from the given fromNode
	 */
	public List<Edge<T1, T2>> getEdgesFrom(T1 fromNode) {
		checkRep();
		if (!isNode(fromNode)) {
			throw new IllegalArgumentException();
		}
		return new ArrayList<>(adjacencyList.get(fromNode));
	}
	
	/**
	 * Returns an edge between fromNode and toNode, provided the
	 * given edge exists. Returns null otherwise
	 * @param fromNode the node from which the edge extends
	 * @param toNode the node to which the edge extends
	 * @throws IllegalArgumentException if fromNode == null ||
	 * 	toNode == null || !isNode(fromNode)
	 * @return Returns edge between fromNode and toNode, if the
	 * given edge exists. Returns null otherwise
	 */
	public Edge<T1, T2> getEdgeBetween(T1 fromNode, T1 toNode) {
		checkRep();
		if (fromNode == null || toNode == null) {
			throw new IllegalArgumentException();
		}
		for (Edge<T1, T2> edge : getEdgesFrom(fromNode)) {
			if (toNode.equals(edge.getToNode())) {
				return edge;
			}
		}
		return null;
	}
	
	/**
	 * Returns an unmodifiable set of nodes in the graph. 
	 * Any attempts to modify this set will result 
	 * in an UnsupportedOperationException.
	 * 
	 * @return A set of T1s representing nodes in the graph
	 */
	public Set<T1> getNodes() {
		checkRep();
		return Collections.unmodifiableSet(adjacencyList.keySet());
	}
	
	/**
	 * Returns a set of nodes in the 
	 * graph which are children of the given node. In other words
	 * the nodes in the returned list have edges extending from the 
	 * given node directly to them. 
	 * 
	 * @param node the parent node form which we are getting
	 * 	child nodes
	 * @throw IllegalArgumentException if !isNode(node)
	 * @return A set of nodes in the graph that are
	 * 	children of the given node
	 */
	@Override
	public Set<T1> getConnectedNodes(T1 node) {
		checkRep();
		if (!isNode(node)) {
			throw new IllegalArgumentException();
		}
		Set<T1> result = new HashSet<>();
		for (Edge<T1, T2> e : adjacencyList.get(node)) {
			result.add(e.getToNode());
		}
		return result;
	} 
}
