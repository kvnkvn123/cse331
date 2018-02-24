package hw8;

import java.util.Iterator;
import java.util.Scanner;

import hw6.MarvelParser.MalformedDataException;

public class CampusPathCLI {
	
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
		
		// print the introduction
		printIntro();
		
		// print the prompt options
		printMenu();
		
		String again = "yes";
		while (!again.equals("q")) {
			System.out.print("> ");
			String next = console.nextLine().trim().toLowerCase().replace("_", " ");
			if (next.equals("q")) {
				again = "q";
			} else if (next.equals("r")) {
				continue;
			} else if (next.equals("b")) {
				for (Building b : model.getBuildings()) {
					System.out.println(b.getShortName() + ": " + b.getLongName());
				}
			} else if (next.equals("m")) {
				printMenu();
			} else if (next.equals("") || next.startsWith("#")) {
				System.out.println(next);
			} else {
				System.out.println("Unknown option");
			}
		}
		
		console.close();
	}	
	
	/** 
	 *  Prints the introduction to for the command line
	 *  interface
	 */
	private static void printIntro() {
		System.out.println("Welcome to the campus paths application");
		System.out.println("You can use this application to find the");
		System.out.println("shortest path between any two buildings on");
		System.out.println("the UW campus");
		System.out.println();
	}
	
	private static void printMenu() {
		System.out.println("Enter 'b' to list all the buildings");
		System.out.println("Enter 'r' to find the shortest route");
		System.out.println("between two buildings");
		System.out.println("Enter 'q' to quit the program");
		System.out.println("Enter m to re-print this menu of options");
		System.out.println();
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
	public static String getDirection(Point<Double> from, 
				Point<Double> to) {
		
		// convert given points to equivalent points in a standard
		// coordinate system
		Point<Double> newFrom = new Point<>(from.getX(), -from.getY());
		Point<Double> newTo = new Point<>(to.getX(), -to.getY());
		
		// compute polar angle from newFrom to newTo
		double deltaY = newTo.getY() - newFrom.getY();
		double deltaX = newTo.getX() - newFrom.getY();
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
