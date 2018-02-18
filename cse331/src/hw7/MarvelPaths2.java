package hw7;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
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
	 * This main method constructs a graph using the marvel universe
	 * information provided in marvel.tsv. It then allows a user
	 * to input pairs of characters and then prints to the console the path 
	 * between those two pairs with least cost, if any
	 */
	public static void main(String[] args) throws MalformedDataException {
		Scanner console = new Scanner(System.in);
		Graph<String, Double> marvelGraph = loadGraph("src/hw7/data/marvel.tsv");
		
		String again = "yes";
		while (!again.equals("n")) {
			System.out.println("Find connections between characters");
			System.out.println("in the marvel universe!");
			System.out.println("Enter the names of any two marvel");
			System.out.println("comic characters and see if they are");
			System.out.println("connected by comic books.");
			System.out.println("Type the name of the first character: ");
			String char1 = console.next().toUpperCase().replace("_", " ");
			System.out.println("Type the name of the second character: ");
			String char2 = console.next().toUpperCase().replace("_", " ");
			System.out.println();
			System.out.println(char1 + " and " + char2);
			System.out.println();
			
			try {
				WeightedPath<String, Double> path = MarvelPaths2.findPath(marvelGraph, char1, char2);
				System.out.println("path from " + char1 + " to " + char2 + ":");
				if (path == null) {
					System.out.println("no path found");
				} else {
					Iterator<Edge<String, Double>> itr = path.iterator();
					// remove first empty path
					itr.next();
					while (itr.hasNext()) {
						Edge<String, Double> e = itr.next();
						String weight = String.format("%.3f", e.getLabel());
						System.out.println(e.getFromNode() + " to " + e.getToNode() + 
										" with weight " + weight);
					}
					String totalWeight = String.format("%.3f", path.getCost());
					System.out.println("total cost: " + totalWeight);
				}
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage() + " is not a marvel character");
			} finally {
				System.out.println();
				System.out.println("Enter any letter to try again, " 
								+ "enter \"n\" directly to quit");
				again = console.next();
			}
			
		}
		
		console.close();		
	}
	
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
		
		// create a dataset of books connecting each pair of 
		// characters
		Map<String, Map<String, Integer>> weightMap = new HashMap<>();
		for (String book : books.keySet()) {
			for (String char1 : books.get(book)) {
				for (String char2 : books.get(book)) {
					if (!weightMap.containsKey(char1)) {
						weightMap.put(char1,  new HashMap<>());
					}
					Map<String, Integer> char1Map = weightMap.get(char1);
					if (!char1Map.containsKey(char2)) {
						char1Map.put(char2, 1);
					} else {
						char1Map.put(char2, char1Map.get(char2) + 1);
					}
				}
			}
		}
		
		// add edges between characters based on numer
		// of books. also adds an edge leading from each
		// node to itself, with weight 0.0
		for (String char1 : weightMap.keySet()) {
			Map<String, Integer> char1Map = weightMap.get(char1);
			for (String char2 : char1Map.keySet()) {
				int bookCount = char1Map.get(char2);
				if (bookCount > 0 && !result.isEdgeBetween(char1, char2)) {
					if (char1.equals(char2)) {
						result.addEdge(char1, char2, 0.0);
						result.addEdge(char2, char1, 0.0);						
					} else {
						double weight = 1 / (double) bookCount;
						result.addEdge(char1, char2, weight);
						result.addEdge(char2, char1, weight);
					}
				}
			}
		}
		
		// return resulting graph
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
		if (!graph.isNode(start)) {
			throw new IllegalArgumentException(start.toString());
		}
		if (!graph.isNode(dest)) {
			throw new IllegalArgumentException(dest.toString());
		}
		
		// a priority queue representing paths to nodes to
		// which the shorted path is not known yet
		PriorityQueue<WeightedPath<T1, T2>> active = new PriorityQueue<>();
		
		// A map of nodes to paths, with keys being nodes to which the
		// shorted path is known, and values being the shorted path
		Map<T1, WeightedPath<T1, T2>> finished = new HashMap<>();
		
		// add a path from start to start to active
		active.add(new WeightedPath<T1, T2>(graph.getEdgeBetween(start, start)));
		
		while (!active.isEmpty()) {
			
			// lowest cost path in active
			WeightedPath<T1, T2> minPath = active.remove();
			
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
