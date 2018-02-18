package hw7.test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import hw5.Edge;
import hw5.EdgeComparator;
import hw5.Graph;
import hw5.WeightedPath;
import hw6.MarvelParser.MalformedDataException;
import hw6.test.HW6TestDriver;
import hw7.MarvelPaths2;


/**
 * This class implements a testing driver which reads test scripts
 * from files for your graph ADT and improved MarvelPaths application
 * using Dijkstra's algorithm.
 **/
public class HW7TestDriver extends HW6TestDriver {

	protected final Map<String, Graph<String,Double>> graphs = new HashMap<>();

	public static void main(String args[]) {
        try {
            if (args.length > 1) {
                printUsage();
                return;
            }

            HW7TestDriver td;

            if (args.length == 0) {
                td = new HW7TestDriver(new InputStreamReader(System.in),
                                       new OutputStreamWriter(System.out));
            } else {

                String fileName = args[0];
                File tests = new File (fileName);

                if (tests.exists() || tests.canRead()) {
                    td = new HW7TestDriver(new FileReader(tests),
                                           new OutputStreamWriter(System.out));
                } else {
                    System.err.println("Cannot read from " + tests.toString());
                    printUsage();
                    return;
                }
            }

            td.runTests();

        } catch (IOException e) {
            System.err.println(e.toString());
            e.printStackTrace(System.err);
        }
    }

    public HW7TestDriver(Reader r, Writer w) {
    	super(r, w);
    }
    
    protected void executeCommand(String command, List<String> arguments) {
        try {
            if (command.equals("CreateGraph")) {
                createGraph(arguments);
            } else if (command.equals("AddNode")) {
                addNode(arguments);
            } else if (command.equals("AddEdge")) {
                addEdge(arguments);
            } else if (command.equals("ListNodes")) {
                listNodes(arguments);
            } else if (command.equals("ListChildren")) {
                listChildren(arguments);
            } else if (command.equals("LoadGraph")) {
                loadGraph(arguments);
            } else if (command.equals("FindPath")) {
                findPath(arguments);
            } else {
                output.println("Unrecognized command: " + command);
            }
        } catch (Exception e) {
            output.println("Exception: " + e.toString());
        }
    }
    
    private void createGraph(List<String> arguments) {
        if (arguments.size() != 1) {
            throw new CommandException("Bad arguments to CreateGraph: " + arguments);
        }

        String graphName = arguments.get(0);
        createGraph(graphName);
    }

    private void createGraph(String graphName) {
    	if (graphs.containsKey(graphName)) {
    		output.println(graphName + " already exists");
    	} else {
    		graphs.put(graphName, new Graph<String, Double>());
    		output.println("created graph " + graphName);
    	}
    }

    private void addNode(List<String> arguments) {
        if (arguments.size() != 2) {
            throw new CommandException("Bad arguments to addNode: " + arguments);
        }

        String graphName = arguments.get(0);
        String nodeName = arguments.get(1);

        addNode(graphName, nodeName);
    }

    private void addNode(String graphName, String nodeName) {
    	if (!graphs.containsKey(graphName)) {
    		throw new IllegalArgumentException();
    	}
    	graphs.get(graphName).addNode(nodeName);
        output.println("added node " + nodeName + " to " + graphName);
    }

    private void addEdge(List<String> arguments) {
        if (arguments.size() != 4) {
            throw new CommandException("Bad arguments to addEdge: " + arguments);
        }

        String graphName = arguments.get(0);
        String parentName = arguments.get(1);
        String childName = arguments.get(2);
        Double edgeLabel = Double.valueOf(arguments.get(3));

        addEdge(graphName, parentName, childName, edgeLabel);
    }

    private void addEdge(String graphName, String parentName, String childName,
            Double edgeLabel) {
    	if (!graphs.containsKey(graphName)) {
  			throw new IllegalArgumentException();
  		}
    	graphs.get(graphName).addEdge(parentName, childName, edgeLabel);
    	String weight = String.format("%.3f", edgeLabel);
        output.println("added edge " + weight + " from " + parentName + 
        				" to " + childName + " in " + graphName);
    }

    private void listNodes(List<String> arguments) {
        if (arguments.size() != 1) {
            throw new CommandException("Bad arguments to listNodes: " + arguments);
        }

        String graphName = arguments.get(0);
        listNodes(graphName);
    }

    private void listNodes(String graphName) {
    	if (!graphs.containsKey(graphName)) {
  			throw new IllegalArgumentException();
  		}
    	// store nodes in sorted order
    	Set<String> sortedNodes = new TreeSet<String>(graphs.get(graphName).getNodes());
    		
    	String result = graphName + " contains:";
    	for (String node : sortedNodes) {
    		result += " " + node;
    	}
    	output.println(result);
    }

    private void listChildren(List<String> arguments) {
        if (arguments.size() != 2) {
            throw new CommandException("Bad arguments to listChildren: " + arguments);
        }

        String graphName = arguments.get(0);
        String parentName = arguments.get(1);
        listChildren(graphName, parentName);
    }

    private void listChildren(String graphName, String parentName) {
    	if (!graphs.containsKey(graphName)) {
  			throw new IllegalArgumentException();
  		}
    	List<Edge<String, Double>> edges = graphs.get(graphName).getEdgesFrom(parentName);
    	Collections.sort(edges, new EdgeComparator<String, Double>());
    	String result = "the children of " + parentName + 
    					" in " + graphName + " are:";
    	// round doubles to three digits
    	for (Edge<String, Double> edge : edges) {
    		String weight = String.format("%.3f", edge.getLabel());
    		result += " " + edge.getToNode() + "(" + weight + ")";
    	}
    	output.println(result);
    }
    
    private void loadGraph(List<String> arguments) 
			throws MalformedDataException {
        if (arguments.size() != 2) {
            throw new CommandException("Bad arguments to LoadGraph: " 
            		+ arguments);
        }

        String graphName = arguments.get(0);
        String fileName = arguments.get(1);
        loadGraph(graphName, fileName);
    }
    
    private void loadGraph(String graphName, String fileName)
    		throws MalformedDataException {
    	try {
	    	if (graphs.containsKey(graphName)) {
	    		output.println(graphName + " already exists");
	    	} else {
	    		Graph<String, Double> result = MarvelPaths2.loadGraph("src/hw7/data/" + 
	    												fileName);
	    		graphs.put(graphName, result);
	    		output.println("loaded graph " + graphName);
	    	}
    	} catch (MalformedDataException e) {
    		output.println(fileName + " malformed");
    	}
    }
    
    private void findPath(List<String> arguments) {
        if (arguments.size() != 3) {
            throw new CommandException("Bad arguments to FindPath: " + 
            		arguments);
        }

        String graphName = arguments.get(0);
        String fromNode = arguments.get(1);
        String toNode = arguments.get(2);
        findPath(graphName, fromNode, toNode);
    }
    
    private void findPath(String graphName, String fromNode, String toNode) {
    	if (!graphs.containsKey(graphName)) {
    		throw new IllegalArgumentException();
    	}
    	try {
    		// replace underscores with spaces
	    	String start = fromNode.replace("_", " ");
	    	String dest = toNode.replace("_", " ");
			Graph<String, Double> graph = graphs.get(graphName);
			
			// use marvelpaths to find path
			WeightedPath<String, Double> path = MarvelPaths2.findPath(graph, start, dest);
			output.println("path from " + start + " to " + dest + ":");
			if (path == null) {
				output.println("no path found");
			} else {
				Iterator<Edge<String, Double>> itr = path.iterator();
				// remove first empty path
				itr.next();
				while (itr.hasNext()) {
					Edge<String, Double> e = itr.next();
					String weight = String.format("%.3f", e.getLabel());
					output.println(e.getFromNode() + " to " + e.getToNode() + 
									" with weight " + weight);
				}
				String totalWeight = String.format("%.3f", path.getCost());
				output.println("total cost: " + totalWeight);
			}
    	} catch (IllegalArgumentException e) {
    		output.println("unknown character " + e.getMessage());
    	}
    }
}
