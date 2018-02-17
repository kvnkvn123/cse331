package hw5;

import java.util.Set;

/**
 * <b>General Graph</> represents the concept a graph.
 * 
 * A graph consists of a collection of nodes that are connected by edges.
 * Each node and edge contains data; nodes consists of data of type T1 and are
 * represented by this data, and edge data consists of type T2
 * 
 *
 * Abstract Invariant:
 * 	No two nodes in the graph can be equal (no to nodes can have
 *  the same data)
 *
 */
public interface GeneralGraph<T1, T2> {
	
	/**
	 * Adds a node to the graph with the given data, provided
	 * a node with the given data does not already exist within
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
	public void addNode(T1 label);
	
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
	public boolean isNode(T1 label);
	
	/**
	 * Adds an edge with data edgeData to the graph between the 
	 * nodes node1 and node2. Behavior is undetermined 
	 * if such an edge already exists within the graph, or if 
	 * the given nodes do not exist.
	 * 
	 * @param node1 node from which the new edge extends
	 * @param node1 node which the new edge leads to
	 * @param edgeData the data associated with the new edge
	 * @throws IllegalArgumentException if node1 == null ||
	 * 	node2 == null || label == null
	 * @modifies this
	 * @effects Adds adds an edge to the graph, provided given nodes 
	 * 	exist, and the edge does not already exist.
	 */
	public void addEdge(T1 node1, T1 node2, 
			T2 edgeData);
	
	/**
	 * Returns true if there is an edge between nodes
	 * node1 and node2. 
	 * Returns false otherwise. If the instance is a directed 
	 * graph, returns true only if the edge is directed from
	 * node1 to node with data node2.
	 * 
	 * 
	 * @param node1 the node from which edge might extend
	 * @param node2 the node to which edge might lead
	 * @throws IllegalArgumentException if !isNode(node1) || 
	 * 	!isNode(node2)
	 * @return Returns true if there is an edge between 
	 * 	node1 and node2. Returns false otherwise. If 
	 * 	the instance is a directed graph, returns true only if 
	 * 	the edge is directed from node1 to node2
	 */
	public boolean isEdgeBetween(T1 node1, T1 node2);
	
	/**
	 * Returns true if there is an edge between
	 * node1 and node2 with the given label.
	 * Returns false otherwise. If the instance is a directed 
	 * graph, returns true only if the edge with given label is 
	 * directed from node1 to node2.
	 * 
	 * 
	 * @param node1 the node from which edge might extend
	 * @param node2 the node to which edge might lead
	 * @param edgeData the data of the edge whose existence is
	 * 	being determined
	 * @throws IllegalArgumentException if !isNode(node1) || 
	 * 	!isNode(node2)
	 * @return Returns true if there is an edge between
	 * 	node1 and node2 with given lable. Returns false
	 *  otherwise. If the instance is a directed graph, returns 
	 *  true only if the edge with given label is directed from
	 *  node1 to node2.
	 */
	public boolean isEdgeBetween(T1 node1, T1 node2, 
			T2 edgeData);
	
	/**
	 * Returns a set of nodes in the 
	 * graph which are connected to the given node. In other words
	 * the nodes in the returned list have edges connecting the 
	 * given node directly to them. 
	 * 
	 * @param node the node from which we are getting
	 * 	connected nodes
	 * @throw IllegalArgumentException if !isNode(node)
	 * @return A set of nodes in the graph that are
	 * 	children of the given node
	 */
	public Set<T1> getConnectedNodes(T1 node);
	
	/**
	 * Returns a set of nodes in the graph. 
	 * 
	 * @return A set of nodes in the graph
	 */
	public Set<T1> getNodes();
	
}
