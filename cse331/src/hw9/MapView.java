package hw9;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import hw5.Edge;
import hw5.WeightedPath;
import hw8.Building;
import hw8.Point;

/**
 * Mapview displays the map of the UW campus,
 * marking building selections and paths and zooming into
 * paths when they are chosen.
 * 
 *
 */
public class MapView extends JPanel {
	
	// Rep invariant
	// image != null 
	
	// abstract invariant : represents the map 
	// view of the user interface, with newStart being
	// the building displayed as the starting point
	// new dest being building displayed as ending
	// point, and path being path displayed when
	// user asks for path display.
	
	/**
	 *  Checks the representation invariant
	 */
	private void checkRep() {
		assert (image != null) : "model is null";
	}
	
	/** the map image of UW campus */
	private final String imageFile = "src/hw8/data/campus_map.jpg";
	
	/** buffered image of given map */
	private BufferedImage image;
	
	/** start building selected be user at time of last
	 *  path search
	 */
	private Building start;
	
	/** destination building selected be user at time of last
	 *  path search
	 */
	private Building dest;
	
	/** current start building selected by user */
	private Building newStart;
	
	/** current destination building selected by user */
	private Building newDest;
	
	/** current path computed for the user */
	private WeightedPath<Point<Double>, Double> path;
	
	/**
	 * Constructs the map view, which initially displays just
	 * the original image
	 * 
	 * @effects constructs a new MapView
	 */
	public MapView() {
		start = null;
		dest = null;
		path = null;
		try {
			image = ImageIO.read(new File(imageFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		checkRep();
	}
	
	/** register the users latest start selection and display
	 * on map
	 * 
	 * @param newStart the latest start selection of the user
	 * @modifies this
	 * @effects shows the newest start selection on the map
	 */
	public void setStart(Building newStart) {
		checkRep();
		this.newStart = newStart;
		path = null;
		repaint();
		checkRep();
	}
	
	/** register the users latest destination selection and display
	 * on map
	 * 
	 * @param newStart the latest destination selection of the user
	 * @modifies this
	 * @effects shoes the latest destination selection on the map
	 */
	public void setDest(Building newDest) {
		checkRep();
		this.newDest = newDest;
		path = null;
		repaint();
		checkRep();
	}
	
	/**
	 *  Registers the latest path search request, and updates start
	 *  and dest
	 *  
	 *  @modifies this
	 *  @effects changes state variables
	 */
	public void notifyMapView() {
		checkRep();
		start = newStart;
		dest = newDest;
		repaint();
		checkRep();
	}
	
	/** records the latest path to display on the map 
	 * 
	 * @param the latest path determined 
	 * @modifies this
	 * @effects changes state of map
	 */
	public void setMap(WeightedPath<Point<Double>, Double> path) {
		checkRep();
		this.path = path;
		checkRep();
	}
	
	/** Reset the state of the map to initial conditions
	 * 
	 *  @modifies this
	 *  @effects changes state of map to initial conditions
	 */
	public void reset() {
		checkRep();
		this.newStart = null;
		this.newDest = null;
		this.start = null;
		this.dest = null;
		this.path = null;
		repaint();
		checkRep();
	}
	
	/**
	 *  Draws the map according to the latest state of the
	 *  map and the users choices, either displaying the inital
	 *  image, choosing building selections or showing a path
	 *  and zooming into the appropriate section of the path
	 *  
	 *  @param g the graphics object of this map
	 *  @modifies this
	 *  @effects redraws the map according to latest information
	 */
	protected void paintComponent(Graphics g) {
		
		checkRep();
		
		// cast to Graphics2D object
    	Graphics2D g2 = (Graphics2D) g;
    	
        super.paintComponent(g2);
        
        // create copy of image
        BufferedImage temp = image.getSubimage(0, 0, image.getWidth(), image.getHeight());
    	BufferedImage copyOfImage = new BufferedImage(temp.getWidth(), temp.getHeight(), BufferedImage.TYPE_INT_RGB);
    	Graphics2D g1 = (Graphics2D) copyOfImage.createGraphics();
    	g1.drawImage(temp, 0, 0, null);
        
    	// if a path has not been chosen, show zoomed out maps
    	// with user selection if any
        if (start == null || dest == null || path == null) {
        	if (newStart != null) {
        		drawRectangle(g1, newStart.getLocation(), 80, 20, Color.cyan);
        	}
        	if (newDest != null) {
        		drawRectangle(g1, newDest.getLocation(), 80, 20, Color.green);
        	}
        	
        	// draw map
        	g2.drawImage(copyOfImage, 0, 0, getWidth(), getHeight(), this);
        	
        } else {  // user has chosen a path
        	
        	// find set of points along the path, find max and min
        	// x and y dimensions to determine area to zoom in        	
        	int[] x = new int[path.size() + 1];
        	int[] y = new int[path.size() + 1];
        	x[0] = start.getLocation().getX().intValue();
        	y[0] = start.getLocation().getY().intValue();
        	int minY = y[0];
        	int maxY = y[0];
        	int minX = x[0];
        	int maxX = x[0];
        	int i = 1;
        	for (Edge<Point<Double>, Double> e : path) {
        		x[i] = e.getToNode().getX().intValue();
        		y[i] = e.getToNode().getY().intValue();
        		if (x[i] > maxX) {
        			maxX = x[i];
        		} else if (x[i] < minX) {
        			minX = x[i];
        		}
        		if (y[i] > maxY) {
        			maxY = y[i];
        		} else if (y[i] < minY) {
        			minY = y[i];
        		}
        		i++;
        	}
        	
        	// create zoom in dimensions based on path array
        	minY = Math.max(0,  minY - 100);
        	maxY = Math.min(image.getHeight(), maxY + 100);
        	minX = Math.max(0,  minX - 100);
        	maxX = Math.min(image.getWidth(), maxX + 100);
        	
        	// show start selection in cyan
        	drawRectangle(g1, start.getLocation(), 40, 10, Color.cyan);
        	
        	// show dest selection in green
        	drawRectangle(g1, dest.getLocation(), 40, 10, Color.green);
        	
        	// draw path
        	g1.setStroke(new BasicStroke(10));
        	g1.setColor(Color.red);
        	g1.drawPolyline(x, y, x.length);
        	
        	// mark points
        	g1.setColor(Color.yellow);
        	for (int j = 0; j < x.length; j++) {
        		g1.fillRect(x[j] - 5, y[j] - 5, 10, 10);
        	}
        	
        	// create zoomed in version of image
        	BufferedImage zoom = copyOfImage.getSubimage(minX, minY, maxX - minX, maxY - minY);
        	
        	// draw final image
        	g2.drawImage(zoom, 0, 0, getWidth(), getHeight(), this);;
        }
        
        checkRep();
    }
	
	/**
	 *  Draws a square of the given color, width and thickness
	 *  centered around the given location on the given graphic
	 *  
	 * @param g graphic to draw on
	 * @param location about which to draw
	 * @param width of square to draw
	 * @param thickness of square border to draw
	 * @param color of the rectangle to draw
	 */
	private void drawRectangle(Graphics2D g, Point<Double> location, 
			int width, int thickness, Color color) {
		g.setColor(color);
		g.setStroke(new BasicStroke(thickness));
		int x = location.getX().intValue();
		int y = location.getY().intValue();
		g.draw(new Rectangle(x - (width / 2), y - (width / 2), width, width));
	}
	

}
