package hw5;

import java.util.TreeMap;
import java.util.Map;
import java.util.Set;

/**
 * This class represents the concept of a directed, labeled multi-graph.
 * 
 * A graph consists of a collection of nodes that are connected by edges 
 * that go from one node to another. Each edge has a label containing
 * information, and pairs of nodes can have multiple edges between them.
 * 
 * Specification fields:
 * 	TODO
 *  @specfield start-point : point // The starting point of the line
 *  @specfield end-point   : point // The ending point of the line
 *
 * Derived specification fields:
 * 	TODO
 *  @derivedfield length : real // length = sqrt((start-point.x - end-point.x)^2 + (start-point.y - end-point.y)^2)
 *                              // The length of the line
 *
 * Abstract Invariant:
 * 	No two nodes in the graph can be equal (no two nodes can 
 * 	exist that are associated with identical data)
 *  No two edges in the graph can be equal, that is, no two 
 *  edges can originate from node A to node b and have 
 *  identical labels.
 * 	
 *
 */
public class Graph {
	
	/** A map storing each node in the graph as a key and sets of
	 *  edges	originating from each node as values */
	private final Map<Node, Set<Edge>> adjacencyList;
	
	/**
	 * Constructs an empty graph.
	 * @effects Constructs a new empty graph
	 */
	public Graph() {
		adjacencyList = new TreeMap<Node, Set<Edge>>();
	}
	
	/**
	 * Adds a node to the graph with the given data, provided
	 * a node with the given data does not already exist within
	 * the graph. Does nothing if such a node exists.
	 * 
	 * @requires data != null
	 * @param data the data associated with the node to be added
	 * @modifies this
	 * @effects if a node with the given data does not
	 *  already exist within the graph, adds a new node with 
	 *  the given data to this
	 * 
	 */
	public void addNode(String data) {
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
	 * @effects if given from-node, to-node, and label does not
	 *  already exist within the graph, adds such an edge to the
	 *  graph. 
	 */
	public void addEdge(Node fromNode, Node toNode, String label) {
		
	}
	
	/**
	 * Returns an unmodifiable or read-only set of nodes in the 
	 * graph sorted according to the lexicographic order of the data
	 * stored in the nodes. Any attempts to modify this set will result 
	 * in an UnsupportedOperationException.
	 * 
	 * @return An unmodifiable sorted set of nodes in the graph
	 */
	public Set<Node> getNodes() {
		return null;		
	}
	
	/**
	 * Returns an unmodifiable or read-only set of edges in the 
	 * graph which originate from the given node. Any attempts to
	 * modify this set will result in an UnsupportedOperationException.
	 * 
	 * @return An unmodifiable set of edges in the graph which
	 * 	originate from the given node
	 */
	public Set<Edge> getEdges() {
		return null;
	}
	
	/**
	 * Returns an unmodifiable or read-only sorted set of nodes in the 
	 * graph which are children of the given node. In other words
	 * the nodes in the returned set have edges extending from the 
	 * given node to them.Any attempts to modify this set will result
	 * in an UnsupportedOperationException.
	 * 
	 * @return An unmodifiable sorted set of nodes in the graph are
	 * 	children of the given node
	 */
	public Set<Node> getChildren() {
		return null;
	}

}
