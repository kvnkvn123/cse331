package hw7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import hw5.Edge;
import hw5.Graph;
import hw5.WeightedPath;
import hw6.MarvelParser;
import hw6.MarvelParser.MalformedDataException;

/**
 * This class contains static methods that operate on
 * graphs. It contains methods to construct a weighted graph
 * from data in a .tsv file, and a method to find paths 
 * between nodes in a graph using Dikstra's algorithm
 *
 */
public class MarvelPaths2 {

	// This class does not require an abstraction function
	// or rep invariant since it does not represent an ADT
	
	/**
	 * Reads a .tsv file and constructs a weighted graph with the
	 * information contained in the graph. For the marvel comics data
	 * constructs a weighted graph with edges between characters that
	 * appear in books together, and weights representing the inverse
	 * of the number of books they appear in together.
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
	public static Graph<String, Double> loadGraph(String file) throws MalformedDataException {
		Graph<String, Double> result = new Graph<>();
		Set<String> characters = new HashSet<>();
		Map<String, List<String>> books = new HashMap<>();
		MarvelParser.parseData(file, characters, books);
		
		// add a node to the graph for every character 
		// in the dataset
		for (String character : characters) {
			result.addNode(character);
		}
		
		// for each pair of character, find number of books
		// the appear in together, calculate weight and
		// add edge between them
		for (String character1 : characters) {
			for (String character2 : characters) {
				if (!result.isEdgeBetween(character1, character2)) {
					if (character1.equals(character2)) {
						result.addEdge(character1, character2, 0.0);
						result.addEdge(character2, character1, 0.0);
					} else {
						int bookCount = 0;
						for (String book : books.keySet()) {
							List<String> appearances = books.get(book);
							if (appearances.contains(character1) && 
									appearances.contains(character2)) {
								bookCount++;
							}
						}
						if (bookCount > 0) {
							double weight = 1 / (double) bookCount;
							result.addEdge(character1, character2, weight);
							result.addEdge(character2, character1, weight);
						}
					}
				}
			}
		}

		return result;
	}
	
	/**
	 * Uses Dikstra's algorithm to search for the path of least cost
	 * between start and dest in the given graph. Returns a list of
	 * Edges representing the shortest path. If there are
	 * multiple paths of the same cost, returns one such path.
	 * 
	 * @requires all edge weights must be non-negative
	 * @param graph the graph to be searched for the shortest path
	 * @param start the node from which the path should start
	 * @param dest the node to which the must should lead
	 * @return A WeightedPath representing the path of least cost between
	 * start and dest. Returns null if no path exists. If there are
	 *  multiple paths of the same size, returns one such path
	 * @throws IllegalArgumentException if !(graph.isNode(start) && graph.isNode(dest))
	 * @throws NullPointerException if graph == null
	 * 
	 */
	public static <T1, T2 extends Number> WeightedPath<T1, T2> findPath(Graph<T1, 
			T2> graph, T1 start, T1 dest) {
		if (graph == null) {
			throw new NullPointerException();
		}
		//System.out.println("Start: " + graph.isNode(start));
		if (!graph.isNode(start)) {
			throw new IllegalArgumentException(start.toString());
		}
		//System.out.println("dest: " + graph.isNode(dest));
		if (!graph.isNode(dest)) {
			throw new IllegalArgumentException(dest.toString());
		}
		
		// a priority queue representing paths to nodes to
		// which the shorted path is not known yet
		PriorityQueue<WeightedPath<T1, T2>> active = new PriorityQueue<>();
		//System.out.println("fine");
		
		// A map of nodes to paths, with keys being nodes to which the
		// shorted path is known, and values being the shorted path
		Map<T1, WeightedPath<T1, T2>> finished = new HashMap<>();
		//System.out.println("fine2");
		
		// add a path from start to start to active
		Edge<T1, T2> e1 = graph.getEdgeBetween(start, start);
		System.out.println("fine2.5");
		System.out.println("edge toNode " + e1.getToNode() + " edge fromNode " + e1.getFromNode());
		active.add(new WeightedPath<T1, T2>(graph.getEdgeBetween(start, start)));
		System.out.println("fine3");
		
		while (!active.isEmpty()) {
			
			// lowest cost path in active
			WeightedPath<T1, T2> minPath = active.remove();
			System.out.println("minPathStart:" + minPath.getStart());
			System.out.println("minPathDest:" + minPath.getDest());
			
			// destination of lowest cost path
			T1 minDest = minPath.getDest();
			
			if (dest.equals(minDest))
				return minPath;
			
			// if minDest is not in finished
			if (!finished.containsKey(minDest)) {
				// for each edge from minDest
				for (Edge<T1, T2> edge : graph.getEdgesFrom(minDest)) {
					// if minimum path is not known, 
					if (!finished.containsKey(edge.getToNode()) && 
							!(edge.getToNode().equals(edge.getFromNode()))) {
						WeightedPath<T1, T2> newPath = minPath.clone();
						newPath.addEdge(edge);
						active.add(newPath);
					}
				}
				
				finished.put(minDest, minPath);
				
			}
		}
		
		// if no path found, return null
		return null;
	}
}
