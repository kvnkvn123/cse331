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
import hw6.MarvelParser.MalformedDataException;


public class MarvelPaths {
	private final Graph marvelGraph = new Graph();
	
	public MarvelPaths(String file) throws MalformedDataException {
		Set<String> characters = new HashSet<>();
		Map<String, List<String>> books = new HashMap<>();
		MarvelParser.parseData(file, characters, books);
		for (String character : characters) {
			marvelGraph.addNode(character);
		}
		for (String book : books.keySet()) {
			for (String character1 : books.get(book)) {
				for (String character2 : books.get(book)) {
					marvelGraph.addEdge(character1, character2, book);
				}
			}
		}
	}
	
	public List<String> findPath(String start, String dest) {
		Queue<String> queue = new LinkedList<>();
		Map<String, List<String>> paths = new HashMap<>();
		
		queue.add(start);
		paths.put(start, new ArrayList<String>());
		
		while (!queue.isEmpty()) {
			String next = queue.remove();
			List<String> currentPath = paths.get(next);
			if (next.equals(dest)) {
				return currentPath;
			}
			for (String toNode : marvelGraph.getChildren(next)) {
				List<String> newPath = new ArrayList<String>();
				newPath.addAll(currentPath);
				newPath.add(toNode);
				paths.put(toNode, currentPath);
				queue.add(toNode);
			}
		}
		
		// if no path exists
		return null;		
	}
	
}
