package hw5;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.lang.Iterable;

/**
 * <b>WeightedPath<T1, T2></> represents a path between two nodes in a graph
 * 	in which each edge has a weight. A path consists of a sequence
 * 	of edges in the graph between one node and another
 * 
 *  WeightedPath<T1, T2> is a path between nodes of type T1 and edges
 *  with labels of type T2
 * 
 * Specification fields:
 *  @specfield start : T1 // The node at the start of the path
 *  @specfield dest : T1 // The node at the dest of the path
 *  @specfield cost : double // The cost of the path, or the
 *  						// sum of weights of the path edges
 *
 * Abstract invariant:
 * 	A path cannot visit the same node twice, unless it starts
 * 	with a trivial path from a node to itself. A path must
 * 	be continuous: in other words, a path cannot jump
 * 	between nodes with no edge between them
 *
 */
public class WeightedPath<T1, T2 extends Number> implements Iterable<Edge<T1, T2>>,
							Comparable<WeightedPath<T1, T2>>, Cloneable {
	
	/** Represents the path, with the first element
	 * of the list being the starting edge of the path and
	 * the last element representing the desting edge of the
	 * path
	 */
	private final List<Edge<T1, T2>> path;
	
	/** Represents the starting node of the path*/
	private T1 start;
	
	/** Represents the desting node of the path*/
	private T1 dest;
	
	/** Represents the cost of traversing the paths*/
	private double cost;
	
	/** Number of edges in the path */
	private int size;
	
	/** Determines whether to perform a thorough check of the
	 * representation invariant: set to true when debugging */
	private static final boolean deepCheck = false;
	
	// Representation Invariant:
	//	start.equals(path.get(0).getFromNode()) &&
	//	dest.equals(path.get(path.size() - 1).getToNode()) &&
	//	 cost = sum of labels of each edge in path 
	//	 && size = number of edges in path &&
	//	 size > 0
	//
	//  &&
	//
	//  if s is the multiset of e.getToNode() for all
	//	edges e in path, s must contain no duplicates
	//	and s can only contain start if first edge is
	//	from start to start
	//
	//	&&
	//
	//  for all edges e_n corresponding to path.get(n)
	//	such that 0 < n < path.size()
	//	e_i-1.getToNode() must equal e_i.getFromNode()
	//		
	//
	// Abstraction Function:
	//  AF(r) = a path p such that
	//	 r.start = p.start
	//	 r.dest = p.dest
	//	 r.cost = p.cost
	//	 r.path represents the sequences of edges
	//		traversed by the path such that the ordered
	//		set of edges in the path is
	//		[r.path.get(0),...r.p.get(r.path.size() - 1]
	//
	
	/** Checks that the representation invariant of the class
	 * 	holds
	 */
	private void checkRep() {
		if (deepCheck) {
			
			// test that fields have correct values
			assert (path != null) : "path cannot be null";
			assert (size == path.size()) : "size = " + size + " path.size = " + path.size();
			assert (size > 0) : "path must have at least one edge";
			assert(start.equals(path.get(0).getFromNode())) 
					: "start must be first node in path";
			assert(dest.equals(path.get(path.size() - 1).getToNode()))
					: "dest must be last node in path";
			double checkCost = 0.0;
			for (Edge<T1, T2> e : path) {
				checkCost += (double) e.getLabel();
				assert (e != null) : "edges cannot be null";
			}
			assert (cost == checkCost) 
					: "cost must be sum of edge weights";
			
			// check that path is continuous
			for (int i = 0; i < path.size() - 1; i++) {
				T1 toNode = path.get(i).getToNode();
				T1 fromNode = path.get(i + 1).getFromNode();
				assert (toNode.equals(fromNode)) 
							: "Path must be continuous";
			}
			
			// check that nodes are not duplicated, apart
			// from start and the toNode of the first edge
			Set<T1> nodes = new HashSet<>();
			if (!start.equals(path.get(0).getToNode())) {
				nodes.add(start);
			}
			for (Edge<T1, T2> e : path) {
				T1 toNode = e.getToNode();
				assert (!nodes.contains(toNode))
					: "duplicate nodes in path";
				nodes.add(toNode);
			}
		}
	}
	
	/**
	 * Constucts a new path containing the given edge
	 * 
	 * @param edge the edge with which the path begins
	 * @throws IllegalArgumentException if edge == null
	 */
	public WeightedPath(Edge<T1, T2> edge) {
		if (edge == null) {
			throw new IllegalArgumentException();
		}
		path = new ArrayList<>();
		path.add(edge);
		start = edge.getFromNode();
		dest = edge.getToNode();
		cost = edge.getLabel().doubleValue();
		size = 1;
		checkRep();
	}
	
	/**
	 * Constucts a new path from the given edge list
	 * of edges
	 * 
	 * 
	 * @param path the list of edges in the path to
	 * 	be created
	 * @throws IllegalArgumentException if newPath == null ||
	 * 	newPath.size() < 1
	 */
	private WeightedPath(List<Edge<T1, T2>> path) {
		if (path == null || path.size() < 1) {
			throw new IllegalArgumentException();
		}

		// no need to create copy since this is 
		// a private method
		this.path = path;
		
		// determine fields
		start = path.get(0).getFromNode();
		dest = path.get(path.size() - 1).getToNode();
		size = this.path.size();
		cost = 0.0;
		for (Edge<T1, T2> e : path) {
			cost += e.getLabel().doubleValue();
		}
		checkRep();
	}
	
	/** Adds an edge to the path
	 * 
	 * @requires edge does not contain nodes already 
	 * 	in path
	 * @param edge the edge to be added to the path
	 * @throws IllegalArgumentException if edge == null
	 */
	public void addEdge(Edge<T1, T2> edge) {
		checkRep();
		path.add(edge);
		cost += (double) edge.getLabel();
		dest = edge.getToNode();	
		size++;
		checkRep();
	}
	
	/**
     * Returns a copy of this WeightedPath instance.   (The
     * elements themselves are not copied.)
     *
     * @return a clone of this WeightedPath instance
     */
	// The edges themselves are not copied
	@Override
    public WeightedPath<T1, T2> clone() {
		checkRep();
		List<Edge<T1, T2>> newPath = new ArrayList<>(path);
		return new WeightedPath<T1, T2>(newPath);
    }
	
	/**
	 *  Returns the node at the start of the path
	 *  
	 *  @returns the node at the start of the path
	 */
	public T1 getStart() {
		checkRep();
		return start;
	}
	
	/**
	 * 	Returns the node at the dest of the path
	 * 
	 * 	@returns the node at the dest of the path
	 */
	public T1 getDest() {
		checkRep();
		return dest;
	}
	
	/**
	 * Returns the cost of the path
	 * 
	 * @returns the cost of the path
	 */
	public double getCost() {
		checkRep();
		return cost;
	}
	
	/**
	 * Returns an iterator over the edges in
	 * this. Iterator returns edges in order, 
	 * from start to dest
	 * 
	 * @returns iterator over the edges in this path.
	 */
	public Iterator<Edge<T1, T2>> iterator() {
		return path.iterator();
	}
	
	/** Compares this path to other according to their costs
	 * Returns a negative integer if
	 * the cost of this path is less than the other paths cost.
	 * returns a positive integer if the cost
	 * of this path is greater than the other path's cost
	 * and returns zero if the this path's cost is
	 * equal to the other path's cost
	 * 
	 * @throws IllegalArgumentException if other == null
	 * @return Returns a negative integer if
	 * the cost of this path is less than the other paths cost.
	 * returns a positive integer if the cost
	 * of this path is greater than the other path's cost
	 * and returns zero if the this path's cost is
	 * equal to the other path's cost
	 */
	public int compareTo(WeightedPath<T1, T2> other) {
		checkRep();
		if (other == null) {
			throw new IllegalArgumentException();
		}
		return Double.compare(cost, other.cost);
	}
	
	/**
	 * Standard equality operation.
   	 *
   	 * @param obj The object to be compared for equality.
   	 * @return true iff 'obj' is an instance of an WeightedPath and 
   	 * 	'this' and 'obj' represent identical paths..
   	 */
	@Override
	public boolean equals(Object obj) {
		checkRep();
		if (obj instanceof WeightedPath<?,?>) {
			WeightedPath<?,?> wp = (WeightedPath<?,?>) obj;

			// Check if path size is same
			if (path.size() != wp.path.size()) {
				return false;
			}
			
			// Weighted paths are equal if they contain the same
			// edges in the same order
			for (int i = 0; i < path.size(); i++) {
				if (!path.get(i).equals(wp.path.get(i))) {
					return false;
				}
			}
			return true;
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
		int hashCode = 1;
		for (Edge<T1, T2> e : path) {
			hashCode = e.hashCode() * (13 + hashCode);
		}
		return hashCode;
	}
}
