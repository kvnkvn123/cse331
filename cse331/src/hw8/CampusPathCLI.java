package hw8;

import java.util.Iterator;
import java.util.Scanner;

import hw5.Edge;
import hw5.WeightedPath;
import hw6.MarvelParser.MalformedDataException;

/**
 * This static class runs a command line interface
 * for a campus path finder application; it helps
 * users find the shortest path between different
 * buildings on the University of Washington campus
 * 
 *
 */
public class CampusPathCLI {
	
	// This class is not an ADT; it contains only static methods
	// and does not have a representation invariant
	
	/**
	 * This main method interacts with the user through a command
	 * line interface and allows them to find the shortest path
	 * between two buildings on the UW campus. Itallows a user
	 * to input pairs of buildings and then prints to the console the path 
	 * between those two pairs with th least distance, if any
	 */
	public static void main(String[] args) throws MalformedDataException {
		Scanner console = new Scanner(System.in);
		CampusPathModel model = new CampusPathModel();
		
		// print the prompt options
		printMenu();
		
		String again = "yes";
		while (!again.equals("q")) {
			
			// prompt
			System.out.println();
			System.out.print("Enter an option ('m' to see the menu): ");
			String next = console.nextLine();
			
			// echo blank lines or comments
			while (next.trim().equals("") || next.startsWith("#")) {
				System.out.println(next);
				next = console.nextLine();
			}
			
			// check for options
			if (next.toLowerCase().equals("q")) {
				again = "q";
			} else if (next.toLowerCase().equals("r")) {
				System.out.print("Abbreviated name of starting building: "); 
				String start = console.nextLine().replace("_", " ");
				System.out.print("Abbreviated name of ending building: ");
				String dest = console.nextLine().replace("_", " ");
				printPath(model, start, dest);
			} else if (next.toLowerCase().equals("b")) {
				printBuildings(model);
			} else if (next.toLowerCase().equals("m")) {
				printMenu();
			} else {
				System.out.println("Unknown option");
			}
		}
		
		console.close();
	}	
	
	/**
	 * Prints the menu of options for the CLI
	 */
	private static void printMenu() {
		System.out.println("Menu:");
		System.out.println("\tr to find a route");
		System.out.println("\tb to see a list of all buildings");
		System.out.println("\tq to quit");
	}
	
	/**
	 * Prints out the buildings in the campus model, in the 
	 * alphabetical order of their short name, one per line
	 * in the format 'shortName: longName'
	 * 
	 * @param model the model from which to retrieve buildings
	 */
	private static void printBuildings(CampusPathModel model) {
		System.out.println("Buildings:");
		for (Building b : model.getBuildings()) {
			System.out.println("\t" + b.getShortName() + 
					": " + b.getLongName());
		}
	}
	
	/**
	 * Prints the shortest path between two buildings on
	 * the UW campus.
	 * 
	 * @param model the model used to calculate shortest paths
	 * @param start the building the path starts at
	 * @param dest the building the path ends at
	 */
	private static void printPath(CampusPathModel model, 
			String start, String dest) {
		
		boolean containsStart = model.containsBuilding(start);
		boolean containsDest = model.containsBuilding(dest);
		
		if (containsStart && containsDest) {
			
			// get shortest path
			WeightedPath<Point<Double>, Double> path = 
					model.findPath(start, dest);
			
			// throw IllegalArgumentException if no path exists
			// between buildings, which is a model error
			if (path == null) {
				throw new IllegalArgumentException(
						"no path between these buildings");
			}
			
			// remove first empty path
			Iterator<Edge<Point<Double>, Double>> itr = path.iterator();
			itr.next();
			
			// tally total distance
			double total = 0.0;
			
			// print path and total distance
			System.out.println("Path from " + model.getLongName(start) + 
					" to " + model.getLongName(dest) + ":");
			while (itr.hasNext()) {
				Edge<Point<Double>, Double> e = itr.next();
				total += e.getLabel();
				String dist = String.format("%.0f", e.getLabel());
				String x = String.format("%.0f", e.getToNode().getX());
				String y = String.format("%.0f", e.getToNode().getY());
				String direction = getDirection(e.getFromNode(), e.getToNode());
				System.out.println("\tWalk " + dist + " feet " + 
							direction + " to (" + x + ", " + y + ")");
			}
			
			String distance = String.format("%.0f", total);
			System.out.println("Total distance: " + distance + " feet");
		} else {
			if (!containsStart) {
				System.out.println("Unknown building: " + start);
			}
			if (!containsDest) {
				System.out.println("Unknown building: " + dest);
			}
		}
	}
	
	/**
	 * determines the direction of the path between 
	 * two given points
	 * 
	 * @param from the point from which the path starts
	 * @param to the point to which the path goes
	 * @return a string abbreviation of the cardinal direction
	 * 	of the path between the two points
	 */
	private static String getDirection(Point<Double> from, 
				Point<Double> to) {
		
		// convert given points to equivalent points in a standard
		// coordinate system
		Point<Double> newFrom = new Point<>(from.getX(), -from.getY());
		Point<Double> newTo = new Point<>(to.getX(), -to.getY());
		
		// compute polar angle from newFrom to newTo
		double deltaY = newTo.getY() - newFrom.getY();
		double deltaX = newTo.getX() - newFrom.getX();
		double angle = Math.atan2(deltaY, deltaX);
		
		// determine direction based on the angle
		if (angle <= (Math.PI / 8) && angle >= (-Math.PI / 8)) {
			return "E";
		} else if (angle > (Math.PI / 8) && angle < (3 * Math.PI / 8)) {
			return "NE";
		} else if (angle >= (3 * Math.PI / 8) && angle <= (5 * Math.PI / 8)) {
			return "N";
		} else if (angle > (5 * Math.PI / 8) && angle < (7 * Math.PI / 8)) {
			return "NW";
		} else if (angle < (-Math.PI / 8) && angle > (-3 * Math.PI / 8)) {
			return "SE";
		} else if (angle <= (-3 * Math.PI / 8) && angle >= (-5 * Math.PI / 8)) {
			return "S";
		} else if (angle < (-5 * Math.PI / 8) && angle > (-7 * Math.PI / 8)) {
			return "SW";
		} else if ((angle <= (-7 * Math.PI / 8) && angle >= (-2 * Math.PI)) ||
				(angle >= (7 * Math.PI / 8) && angle <= (2 * Math.PI))) {
			return "W";
		} else {
			// for error checking
			return "error";
		}
	}
}