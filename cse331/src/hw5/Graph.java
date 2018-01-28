package hw5;

/**
 * This class represents the concept of a directed, labeled multi-graph.
 * 
 * A graph consists of a collection of nodes that are connected by edges 
 * that go from one node to another. Each edge has a label containing
 * information, and pairs of nodes can have multiple edges between them.
 * 
 * Specification fields:
 *  @specfield start-point : point // The starting point of the line
 *  @specfield end-point   : point // The ending point of the line
 *
 * Derived specification fields:
 *  @derivedfield length : real // length = sqrt((start-point.x - end-point.x)^2 + (start-point.y - end-point.y)^2)
 *                              // The length of the line
 *
 * Abstract Invariant:
 * 	No two nodes in the graph can be equal
 * 	
 *  A line's start-point must be different from its end-point.
 * @author tushar
 *
 */
public class Graph {

	public Graph() {
		// TODO Auto-generated constructor stub
	}

}
