package hw8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import hw5.Edge;
import hw5.Graph;
import hw5.WeightedPath;
import hw6.MarvelParser.MalformedDataException;
import hw7.MarvelPaths2;

public class CampusPathModel {
	
	/** Stores campus path data*/
	private Graph<Point<Double>, Double> campusPaths;
	
	/** Stores campus building information */
	private Set<Building> buildings;
	
	/** determine whether to perform extensive repInv checks */
	private static final boolean deepCheck = true;
	
	/** the name of the file of campus buildings */
	private static final String buildingFile = "campus_buildings.dat";
	
	/** the name of the file of campus paths */
	private static final String pathFile = "campus_paths.dat";
	
	// Representation Invariant:
	//	this.campusPaths != null && 
	//	this.buildings != null &&
	//	for each Building b in buildings:
	//		b.getLocation() is a node in campusPaths
	// && for any pair of nodes a and b in campusPaths, if
	//  there is an edge from a to b, there is only one 
	//  such edge, and there is also an edge with the same
	//	label from b to a
	//
	// Abstraction Function:
	//	AF(r) models a campus, such that
	//	each Building in buildings represents
	//	a building on campus, and each node in campusPaths
	//	represents a location on campus, and each edge between
	//	nodes represents the path between those two locations
	// 	which the edge label measuring the distance between the
	//	two points
	
	private void checkRep() {
		assert (buildings != null) : "buildings is null";
		assert (campusPaths != null) : "campusPaths is null";
		if (deepCheck) {
			// check that the location of each building
			// is in the graph
			for (Building b : buildings) {
				assert (campusPaths.isNode(b.getLocation())) :
					"buildning " + b.getLongName() + " is not in graph";
			}
			// check that each point in the graph has at most 
			// one edge to any other point
			for (Point<Double> p : campusPaths.getNodes()) {
				Set<Point<Double>> children = new HashSet<>();
				Set<Edge<Point<Double>, Double>> edges = new HashSet<>();
				for (Edge<Point<Double>, Double> e : campusPaths.getEdgesFrom(p)) {
					Point<Double> toPoint = e.getToNode();
					assert (!children.contains(toPoint)) : 
						"two edges between " + toPoint + 
							" and " + e.getFromNode();
					children.add(toPoint);
					edges.add(e);
				}
				// check that for each
				// edge between two points, there is an edge
				// between those points in opposite direction
				for (Edge<Point<Double>, Double> e : edges) {
					assert (campusPaths.isEdgeBetween(e.getToNode(), 
							e.getFromNode(), e.getLabel())) : "edge " 
								+ e + "does not have opposite";
				}
			}
		}
	}
	
	/**
	 *  Constructs a CampusPathModel from information in the
	 *  given files. 
	 *  
	 *  Both files must be well formed .dat files:
	 * 	each line  of buildingFile must contain exactly four 
	 * 	tokens separated by tabs, two strings followed by 
	 * 	two doubles
	 * 	each line of pathFile must contain exactly two 
	 * 	doubles separated by a comma,
	 *  or else must start with a tab and two doubles separated 
	 *  by a comma, followed by a ": " and another double
	 *  
	 * @param buildingFile file with building data
	 * @param pathFile file with path data
	 * @throws MalformedDataException if either file is not
	 * 	well formed.
	 */
	public CampusPathModel()
			throws MalformedDataException{
		buildings = parseBuildings(buildingFile);
		campusPaths = parsePaths(pathFile);
		checkRep();
	}
	
	/**
	 * Reads a .dat file of buildings and their locations and
	 * constructs a set based on this data.
	 * 
	 * The .dat file should be well-formed: 
	 * 	each line must contain exactly four tokens separated by tabs,
	 *  two strings followed by two doubles
	 *  
	 * @param filename the .dat file in the appropriate format to be
	 * 	read
	 * @return a set of buildings read from the file
	 * @throws MalformedDataException if fill is not well-formed: if
	 * 	each line doesn't contain exactly four tokens separated by tabs,
	 *  two strings followed by two doubles
	 */
	private static Set<Building> parseBuildings(String filename)
			throws MalformedDataException {
		Set<Building> result = new TreeSet<Building>();
		BufferedReader reader = null;
		
		try {
			reader = new BufferedReader(new 
					FileReader("src/hw8/data/" + filename));
			
			String inputLine;
	        while ((inputLine = reader.readLine()) != null) {
	        	
	        	// Ignore comment lines.
	            if (inputLine.startsWith("#")) {
	                continue;
	            }
	            
	            // Parse the data, throwing
	            // an exception for malformed lines.
	            String[] tokens = inputLine.split("\t");
	            if (tokens.length != 4) {
	                throw new MalformedDataException("Line should contain exactly one tab: "
	                                                 + inputLine);
	            }
	            
	            String shortName = tokens[0];
	            String longName = tokens[1];
	            double x = Double.parseDouble(tokens[2]);
	            double y = Double.parseDouble(tokens[3]);
	            result.add(new Building(shortName, longName, x, y));
	        }	        
        } catch (IOException e) {
            System.err.println(e.toString());
            e.printStackTrace(System.err);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.err.println(e.toString());
                    e.printStackTrace(System.err);
                }
            }
        }
		
		return result;
	}
	
	/**
	 * Reads a .dat file of points and distances between then, and
	 * constructs a graph based on this data with points as nodes
	 * and distances between points as edges.
	 * 
	 * The .dat file should be well-formed: 
	 * 	each line must contain exactly two doubles separated by a comma,
	 *  or else must start with a tab and two doubles separaed by a comma,
	 *  followed by a ": " and another double
	 * 
	 * 
	 * @param fileName the .dat file in the appropriate format to be read 
	 * 	 into a graph.
	 * @return a graph representing the information contained in the
	 * 	 .tsv file
	 * @throws MalformedDataException if the file is not well-formed:
	 *	 each line must contain exactly two doubles separated by a comma,
	 *   or else must start with a tab and two doubles separaed by a comma,
	 *   followed by a ": " and another double
	 */
	private static Graph<Point<Double>, Double> parsePaths(String filename) 
			throws MalformedDataException {
		Graph<Point<Double>, Double> result = new Graph<>();
		BufferedReader reader = null;
		
		try {
			reader = new BufferedReader(new 
					FileReader("src/hw8/data/" + filename));
			
			String inputLine;
			Point<Double> start = null;
	        while ((inputLine = reader.readLine()) != null) {
	        	
	        	// Ignore comment lines.
	            if (inputLine.startsWith("#")) {
	                continue;
	            }
	            
	            if (inputLine.startsWith("\t")) {
	            	
	            	// Parse the data, splitting by ": " and throwing
		            // an exception for malformed lines.
	            	String[] tokens = inputLine.trim().split(": ");
	            	if (tokens.length != 2) {
		                throw new MalformedDataException(
		                		"Line should contain points and distance "
		                                                 + inputLine);
		            }
	            	
	            	// Parse the data, splitting by ": " and throwing
		            // an exception for malformed lines.
	            	String[] points = tokens[0].split(",");
	            	if (points.length != 2) {
		                throw new MalformedDataException(
		                		"Line should contain two points before distance "
		                                                 + inputLine);
		            }
	            	
	            	double x = Double.parseDouble(points[0]);
		            double y = Double.parseDouble(points[1]);
		            double distance = Double.parseDouble(tokens[1]);
		            Point<Double> dest = new Point<Double>(x, y);
		            result.addEdge(start, dest, distance);
	            	
	            } else {
	            
		            // Parse the data, splitting by commas and throwing
		            // an exception for malformed lines.
		            String[] tokens = inputLine.split(",");
		            if (tokens.length != 2) {
		                throw new MalformedDataException(
		                		"Line should contain two points "
		                                                 + inputLine);
		            }
		            
		            // add location to graph
		            double x = Double.parseDouble(tokens[0]);
		            double y = Double.parseDouble(tokens[1]);
		            start = new Point<Double>(x, y);
		            if (!result.isNode(start)) {
		            	result.addNode(start);
		            }
	            }
	        }	        
		} catch (IOException e) {
	        System.err.println(e.toString());
	        e.printStackTrace(System.err);
	    } finally {
	        if (reader != null) {
	            try {
	                reader.close();
	            } catch (IOException e) {
	                System.err.println(e.toString());
	                e.printStackTrace(System.err);
	            }
	        }
	    }
		
		return result;
	}
	
	/**
	 * Returns a read only set of the buildings  in this model. The
	 * iterator of this set returns the buildings in alphabetic order
	 * 
	 * @return a read only set of the buildings  in this model. The
	 * iterator of this set returns the buildings in alphabetic order
	 */
	public Set<Building> getBuildings() {
		checkRep();
		return Collections.unmodifiableSet(buildings);
	}
	
	/**
	 *  Returns a weighted path representing the shortest path
	 *  between the two buildings given (represented by their
	 *  abbreviated names)
	 *  
	 * @param start the abbreviated name of the start building
	 * @param dest the abbreviated name of the destination building
	 * @return a weighted path representing the shortest path
	 *  between the two buildings given (represented by their
	 *  abbreviated names)
	 */
	public WeightedPath<Point<Double>, Double> 
			findShortestPath(String start, String dest) {
		
		checkRep();
		
		// find building locations
		Point<Double> startLocation = getLocation(start);
		Point<Double> destLocation = getLocation(dest);
		
		return MarvelPaths2.findPath(campusPaths, 
				startLocation, destLocation);
	}
	
	/**
	 *  Returns the location of the building with the
	 *  given shortName, returns null if such a building
	 *  does not exist
	 *  
	 * @param shortName the abbreviated name of the building to
	 *  whose location is to be determined
	 * @return the location of the building with the
	 *  given shortName, null if such a building
	 *  does not exist
	 */
	private Point<Double> getLocation(String shortName) {
		checkRep();
		for (Building b : buildings) {
			if (b.getShortName().equals(shortName)) {
				return b.getLocation();
			}
		}
		return null;
	}
}
