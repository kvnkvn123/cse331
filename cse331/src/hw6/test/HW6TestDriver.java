package hw6.test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

import hw5.Graph;
import hw5.test.HW5TestDriver;


/**
 * This class implements a testing driver which reads test scripts
 * from files for testing Graph, the Marvel parser, and your BFS
 * algorithm.
 **/
public class HW6TestDriver extends HW5TestDriver {


	public static void main(String args[]) {
        try {
            if (args.length > 1) {
                printUsage();
                return;
            }

            HW6TestDriver td;

            if (args.length == 0) {
                td = new HW6TestDriver(new InputStreamReader(System.in),
                                       new OutputStreamWriter(System.out));
            } else {

                String fileName = args[0];
                File tests = new File (fileName);

                if (tests.exists() || tests.canRead()) {
                    td = new HW6TestDriver(new FileReader(tests),
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

	public HW6TestDriver(Reader r, Writer w) {
		super(r, w);
	}
	
	@Override
	// This might be an unusual way to override the method, but it
	// allows us to keep a number of the methods in the HW5Driver
	// class private
	protected void executeCommand(String command, List<String> arguments) {
		if (command.equals("CreateGraph") || command.equals("AddNode") ||
			command.equals("AddEdge") || command.equals("ListNodes") ||
			command.equals("ListChildren")) {
			super.executeCommand(command, arguments);
		} else {
	        try {
	            if (command.equals("LoadGraph")) {
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
    }
	
	// TODO
	private void loadGraph(List<String> arguments) {
        if (arguments.size() != 2) {
            throw new CommandException("Bad arguments to LoadGraph: " + arguments);
        }

        String graphName = arguments.get(0);
        String fileName = arguments.get(1);
        loadGraph(graphName, fileName);
    }

	// TODO
    private void loadGraph(String graphName, String fileName) {
    }
    
    // TODO
    private void findPath(List<String> arguments) {
        if (arguments.size() != 3) {
            throw new CommandException("Bad arguments to FindPath: " + arguments);
        }

        String graphName = arguments.get(0);
        String fromNode = arguments.get(1);
        String toNode = arguments.get(2);
        findPath(graphName, fromNode, toNode);
    }
    
    // TODO
    private void findPath(String graphName, String fromNode, String toNode) {
    }
}
