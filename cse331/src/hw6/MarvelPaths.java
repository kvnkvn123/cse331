package hw6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import hw5.Graph;
import hw5.Graph.Edge;
import hw6.MarvelParser.MalformedDataException;

/**
 * This class contains static methods that operate on
 * graphs. It contains methods to construct a graph from
 * data in a.tsv file, and a method to find paths between
 * nodes in a graph using a breadth-first search algorithm
 *
 */
public class MarvelPaths {
	
	/**
	 * Reads a .tsv file and constructs a graph representing this information.
	 * 
	 * The .tsv file should be in well-formed: 
	 * 	each line must contain exactly two tokens separated by a tab,
	 *  or else must start with a # symbol to indicate a comment line.
	 * 
	 * 
	 * @param file the tsv file in the appropriate format to be read 
	 * 	into a graph.
	 * @return a graph representing the information contained in the
	 * 	.tsv file
	 * @throws MalformedDataException if the file is not well-formed:
	 *          each line contains exactly two tokens separated by a tab,
	 *          or else starting with a # symbol to indicate a comment line.
	 */
	public static Graph loadGraph(String file) throws MalformedDataException {
		Graph result = new Graph();
		Set<String> characters = new HashSet<>();
		Map<String, List<String>> books = new HashMap<>();
		MarvelParser.parseData(file, characters, books);
		for (String character : characters) {
			result.addNode(character);
		}
		for (String book : books.keySet()) {
			for (String character1 : books.get(book)) {
				for (String character2 : books.get(book)) {
					if (!character1.equals(character2)) 
						result.addEdge(character1, character2, book);
				}
			}
		}
		return result;
	}
	
	/**
	 * Uses a breadth-first search algorithm to search for the shorted
	 * path between start and dest in the given graph. Returns a list of
	 * Edges representing the shortest path. If there are
	 * multiple paths of the same size, returns the shorted path
	 * lexicographically: it picks the lexicographically first node
	 * at each step in the path, and if those nodes have several 
	 * edges between them,it returns the edge with the the lexicographically 
	 * lowest label.
	 * 
	 * @param graph the graph to be searched for the shortest path
	 * @param start the node from which the path should start
	 * @param dest the node to which the must should lead
	 * @return A list of edges representing the shortest path between
	 * start and dest. Returns an empty list if start.equals(dest), and 
	 * returns null if no path exists.
	 * 	 If there are multiple paths of the same size, returns 
	 * the shorted path lexicographically: it picks the lexicographically first 
	 * node at each step in the path, and if those nodes have several 
	 * edges between them, it returns the edge with the the lexicographically 
	 * lowest label. 
	 * @throws IllegalArgumentException if !(graph.isNode(start) && graph.isNode(dest))
	 * @throws NullPointerException if graph == null
	 * 
	 */
	public static List<Edge> findPath(Graph graph, String start, String dest) {
		if (graph == null) {
			throw new NullPointerException();
		}
		if (!graph.isNode(start)) {
			throw new IllegalArgumentException(start);
		}
		if (!graph.isNode(dest)) {
			throw new IllegalArgumentException(dest);
		}
		Queue<String> queue = new LinkedList<>();
		Map<String, List<Edge>> paths = new HashMap<>();
		
		queue.add(start);
		paths.put(start, new ArrayList<Edge>());
		
		while (!queue.isEmpty()) {
			String next = queue.remove();
			List<Edge> currentPath = paths.get(next);
			if (next.equals(dest)) {
				return currentPath;
			}
			for (Edge edge : graph.getEdgesFrom(next)) {
				String toNode = edge.getToNode();
				if (!paths.containsKey(toNode)) {
					List<Edge> newPath = new ArrayList<>();
					newPath.addAll(currentPath);
					newPath.add(edge);
					paths.put(toNode, newPath);
					queue.add(toNode);
				}
			}
		}
		
		// if no path exists
		return null;		
	}
	
}
