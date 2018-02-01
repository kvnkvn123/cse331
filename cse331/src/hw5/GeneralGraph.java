package hw5;

import java.util.Collections;
import java.util.Set;

/**
 * <b>General Graph</> represents the concept a graph.
 * 
 * A graph consists of a collection of nodes that are connected by edges.
 * Each node and edge contains data in the form of a string label.
 * 
 *
 * Abstract Invariant:
 * 	No two nodes in the graph can be equal (no to nodes can have
 *  the same label)
 *
 */
public interface GeneralGraph {
	
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
	public void addNode(String label);
	
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
	public boolean isNode(String label);
	
	/**
	 * Adds an edge to the graph between the given node1 and 
	 * node2, to-node. Behaviour is undetermined if such an edge
	 * already exists within the graph, or if the given nodes
	 * do not exist.
	 * 
	 * @requires node1 != null && node2 != null && label != null
	 * @param node1 the node from which the new edge extends
	 * @param node1 the node which the new edge leads to
	 * @param label the label associated with the new edge
	 * @modifies this
	 * @effects Adds adds an edge to the graph, provided the
	 * 	given nodes exist, and the edge does not already exist.
	 */
	public void addEdge(String node1, String node2, 
			String label);
	
	/**
	 * Returns true if there is an edge between node1 and node2. 
	 * Returns false otherwise. If the instance is a directed 
	 * graph, returns true only if the edge is directed from node1
	 * to node2.
	 * 
	 * 
	 * @requires node1 != null && node2 != null &&
	 * 	isNode(node1) && isNode(node2)
	 * @param node1 node from which edge might extend
	 * @param node2 the node to which edge might lead
	 * @return Returns true if there is an edge between node1 and node2. 
	 * Returns false otherwise. If the instance is a directed 
	 * graph, returns true only if the edge is directed from node1
	 * to node2.
	 */
	public boolean isEdgeBetween(String node1, String node2);
	
	/**
	 * Returns true if there is an edge between node1 and node2
	 * with the given label.
	 * Returns false otherwise. If the instance is a directed 
	 * graph, returns true only if the edge with given label is 
	 * directed from node1 to node2.
	 * 
	 * 
	 * @requires node1 != null && node2 != null &&
	 * 	isNode(node1) && isNode(node2) && label != null
	 * @param node1 node from which edge might extend
	 * @param node2 the node to which edge might lead
	 * @param label the label of the edge whose existence is
	 * 	being determined
	 * @return Returns true if there is an edge between node1 and 
	 * node2 with the given label. Returns false otherwise. 
	 * If the instance is a directed 
	 * graph, returns true only if the edge with given label is 
	 * directed from node1 to node2.
	 */
	public boolean isEdgeBetween(String node1, String node2, 
			String label);
	
	/**
	 * Returns a list of strings representing edges connected to
	 * the given fromNode. If the instance is a directed graph
	 * returns edges directed from fromNode. Strings are of the form 
	 * "<i>toNode</i>(<i>edgeLabel</i>)"
	 * where toNode represents the node to which the edge extends
	 * and edgeLabel represents the label of the edge
	 * 
	 * @requires fromNode != null && isNode(fromNode)
	 * @param fromNode the node from which the edge(s) extend(s)
	 * @return Returns a list of strings representing edges 
	 * 	originating from the given fromNode
	 */
	public Set<String> getEdgesFrom(String fromNode);
	
	/**
	 * Returns a set of strings representing nodes in the graph. 
	 * 
	 * @return A set of strings representing nodes in the graph
	 */
	public Set<String> getNodes();
	
}
