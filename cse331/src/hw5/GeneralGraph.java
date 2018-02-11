package hw5;

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
	 * @param label the label of the node to be added
	 * @modifies this
	 * @throws IllegalArgumentException if label == null
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
	 * @param label the label associated with the node whose existence
	 * 	is being tested for
	 * @throws IllegalArgumentException if label == null
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
	 * @param node1 the node from which the new edge extends
	 * @param node1 the node which the new edge leads to
	 * @param label the label associated with the new edge
	 * @throws IllegalArgumentException if node1 == null ||
	 * 	node2 == null || label == null
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
	 * @param node1 node from which edge might extend
	 * @param node2 the node to which edge might lead
	 * @throws IllegalArgumentException if !isNode(node1) || 
	 * 	!isNode(node2)
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
	 * @param node1 node from which edge might extend
	 * @param node2 the node to which edge might lead
	 * @param label the label of the edge whose existence is
	 * 	being determined
	 * @throws IllegalArgumentException if !isNode(node1) || 
	 * 	!isNode(node2)
	 * @return Returns true if there is an edge between node1 and 
	 * node2 with the given label. Returns false otherwise. 
	 * If the instance is a directed 
	 * graph, returns true only if the edge with given label is 
	 * directed from node1 to node2.
	 */
	public boolean isEdgeBetween(String node1, String node2, 
			String label);
	
	/**
	 * Returns a set of nodes in the 
	 * graph which are connected to the given node. In other words
	 * the nodes in the returned list have edges connecting the 
	 * given node directly to them. The list's iterator returns the nodes
	 * in ascending alphabetic order
	 * 
	 * @param node the node from which we are getting
	 * 	connected nodes
	 * @throw IllegalArgumentException if !isNode(node)
	 * @return An list of nodes in the graph that are
	 * 	children of the given node
	 */
	public Set<String> getConnectedNodes(String node);
	
	/**
	 * Returns a set of strings representing nodes in the graph. 
	 * 
	 * @return A set of strings representing nodes in the graph
	 */
	public Set<String> getNodes();
	
}
