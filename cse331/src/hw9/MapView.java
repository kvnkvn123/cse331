package hw9;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import hw5.Edge;
import hw5.WeightedPath;
import hw8.Building;
import hw8.Point;

public class MapView extends JPanel {
	
	private final String imageFile = "src/hw8/data/campus_map.jpg";
	private BufferedImage image;
	private Building start;
	private Building dest;
	private Building newStart;
	private Building newDest;
	private WeightedPath<Point<Double>, Double> path;
	
	public MapView() {
		start = null;
		dest = null;
		path = null;
		try {
			image = ImageIO.read(new File(imageFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setStart(Building newStart) {
		this.newStart = newStart;
		repaint();
	}
	
	public void setDest(Building newDest) {
		this.newDest = newDest;
		repaint();
	}
	
	public void notifyMapView() {
		start = newStart;
		dest = newDest;
		repaint();
	}
	
	public void setMap(WeightedPath<Point<Double>, Double> path) {
		this.path = path;
	}
	
	public void reset() {
		this.newStart = null;
		this.newDest = null;
		this.start = null;
		this.dest = null;
		this.path = null;
		repaint();
	}
	
	protected void paintComponent(Graphics g) {
    	Graphics2D g2 = (Graphics2D) g;
    	
        super.paintComponent(g2);
        
        BufferedImage temp = image.getSubimage(0, 0, image.getWidth(), image.getHeight());
    	BufferedImage copyOfImage = new BufferedImage(temp.getWidth(), temp.getHeight(), BufferedImage.TYPE_INT_RGB);
    	Graphics2D g1 = (Graphics2D) copyOfImage.createGraphics();
    	g1.drawImage(temp, 0, 0, null);
        
        if (start == null || dest == null || path == null) {
        	/*BufferedImage temp = image.getSubimage(0, 0, image.getWidth(), image.getHeight());
        	BufferedImage copyOfImage = new BufferedImage(temp.getWidth(), temp.getHeight(), BufferedImage.TYPE_INT_RGB);
        	Graphics2D g1 = (Graphics2D) copyOfImage.createGraphics();
        	g1.drawImage(temp, 0, 0, null);*/
        	if (newStart != null) {
        		drawRectangle(g1, newStart.getLocation(), 80, 20, Color.cyan);
        		/*g1.setColor(Color.cyan);
        		g1.setStroke(new BasicStroke(20));
        		int x = newStart.getLocation().getX().intValue();
        		int y = newStart.getLocation().getY().intValue();
        		g1.draw(new Rectangle(x - 40, y - 40, 80, 80));*/
        	}
        	if (newDest != null) {
        		drawRectangle(g1, newDest.getLocation(), 80, 20, Color.green);
        		/*g1.setColor(Color.green);
        		g1.setStroke(new BasicStroke(20));
        		int x = newDest.getLocation().getX().intValue();
        		int y = newDest.getLocation().getY().intValue();
        		g1.draw(new Rectangle(x - 40, y - 40, 80, 80));*/
        	}
        	//g1.setStroke(new BasicStroke(400));
        	//g1.fillRect(500, 500,  500,  500);
        	g2.drawImage(copyOfImage, 0, 0, getWidth(), getHeight(), this);
        	//g2.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        	/*g2.setColor(Color.black);
        	g2.setStroke(new BasicStroke(400));
        	g2.drawLine(20,  20,  100,  100);
        	g2.fillRect(500, 500,  500,  500);
        	if (newStart != null) {
        		g2.setColor(Color.cyan);
        		g2.setStroke(new BasicStroke(400));
        		int x = newStart.getLocation().getX().intValue();
        		int y = newStart.getLocation().getY().intValue();
        		g2.fillRect(x - 20, y - 20, 40, 40);
        		g2.draw(new Rectangle(x - 20, y - 20, 40, 40));
        		//g2.draw(x - 20, y - 20, 40, 40);
        	}
        	if (newDest != null) {
        		g2.setColor(Color.green);
        		int x = newDest.getLocation().getX().intValue();
        		int y = newDest.getLocation().getY().intValue();
        		g2.drawOval(x - 20, y - 20, 40, 40);
        	}*/
        	
        	
        } else {
        	int[] x = new int[path.size() + 1];
        	int[] y = new int[path.size() + 1];
        	x[0] = start.getLocation().getX().intValue();
        	y[0] = start.getLocation().getY().intValue();
        	int minY = y[0];
        	int maxY = y[0];
        	int minX = x[0];
        	int maxX = x[0];
        	int i = 1;
        	//TODO what if this path has zero edges
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
        	
        	minY = Math.max(0,  minY - 100);
        	maxY = Math.min(image.getHeight(), maxY + 100);
        	minX = Math.max(0,  minX - 100);
        	maxX = Math.min(image.getWidth(), maxX + 100);
        	
        	System.out.println("width: " + image.getWidth() + " height: " + image.getHeight());
        	
        	//BufferedImage temp = image.getSubimage(minX, minY, maxX - minX, maxY - minY);
        	//BufferedImage copyOfImage = new BufferedImage(temp.getWidth(), temp.getHeight(), BufferedImage.TYPE_INT_RGB);
        	//BufferedImage copyOfImage = image.getSubimage(0, 0, image.getWidth(), image.getHeight());
        	/*BufferedImage temp = image.getSubimage(0, 0, image.getWidth(), image.getHeight());
        	BufferedImage copyOfImage = new BufferedImage(temp.getWidth(), temp.getHeight(), BufferedImage.TYPE_INT_RGB);
        	Graphics2D g1 = (Graphics2D) copyOfImage.createGraphics();
        	g1.drawImage(temp, 0, 0, null);*/
        	//Graphics2D g1 = (Graphics2D) copyOfImage.createGraphics();
        	//Graphics2D g1 = image.createGraphics();
        	
        	// show start selection in cyan
        	drawRectangle(g1, start.getLocation(), 40, 10, Color.cyan);
        	/*g1.setColor(Color.cyan);
    		g1.setStroke(new BasicStroke(20));
    		int u = newStart.getLocation().getX().intValue();
    		int w = newStart.getLocation().getY().intValue();
    		g1.draw(new Rectangle(u - 40, w - 40, 80, 80));*/
        	
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
        	
        	//Experiment with zooming
        	BufferedImage zoom = copyOfImage.getSubimage(minX, minY, maxX - minX, maxY - minY);
        
        	//g2.setStroke(new BasicStroke(400));
        	System.out.println(Arrays.toString(x));
        	System.out.println(Arrays.toString(y));
        	System.out.println(x.length);
        	//g2.setColor(Color.black);
        	//g2.fillRect(20,  20,  300,  300);
        	//g2.drawLine(20, 20, 300, 300);
        	//g2.drawPolyline(x, y, x.length);
        	g2.drawImage(zoom, 0, 0, getWidth(), getHeight(), this);
        	//g2.drawImage(copyOfImage, minX, minY, maxX - minX, maxY - minY, this);
        	
        	
        	/*BufferedImage temp = image.getSubimage(minX, minY, maxX - minX, maxY - minY);
        	BufferedImage copyOfImage = new BufferedImage(temp.getWidth(), temp.getHeight(), BufferedImage.TYPE_INT_RGB);
        	Graphics2D g1 = (Graphics2D) copyOfImage.createGraphics();
        	g1.drawImage(temp, 0, 0, null);
        	
        	BufferedImage copyOfImage = image.getSubimage(minX, minY, maxX - minX, maxY - minY); //fill in the corners of the desired crop location here
            Graphics2D g1 = (Graphics2D) copyOfImage.createGraphics();
            g1.setColor(Color.BLACK);
            g1.setStroke(new BasicStroke(100));
            System.out.println(copyOfImage.getWidth());
            System.out.println(copyOfImage.getHeight());
            System.out.println(Arrays.toString(x));
            System.out.println(Arrays.toString(y));
            int[] x1 = {20, 100, 200};
     	   	int[] y1 = {20, 100, 150};
     	   	System.out.println(x[0] + " " + x[10] + " " + y[0] + " " + y[10]);
     	   	//g1.drawLine(x[0], y[0], x[10], y[10]);
            g1.drawPolyline(x, y, 3);
            //g1.drawPolyline(x1, y1, 3);
            g1.draw(new Line2D.Double(start.getLocation().getX(), start.getLocation().getY(), dest.getLocation().getY(), dest.getLocation().getY()));
            g2.drawImage(copyOfImage, 0, 0, getWidth(), getHeight(), this);
            g2.drawPolyline(x, y, x.length);
            g2.drawPolyline(x1, y1, 3);
            g2.drawLine(x[0], y[0], x[10], y[10]);*/
        	
        	//Graphics2D g1 = (Graphics2D) 
        	
        }
        
        /*BufferedImage copyOfImage = image.getSubimage(0, 0, 320, 200); //fill in the corners of the desired crop location here
        Graphics2D g1 = copyOfImage.createGraphics();
        g1.setColor(Color.BLACK);
 	   	int[] x = {20, 100, 200};
 	   	int[] y = {20, 100, 150};
 	    g1.setStroke(new BasicStroke(100));
 	   	g1.drawPolyline(x, y, 3);
        g2.drawImage(copyOfImage, 0, 0, getWidth(), getHeight(), this);
        //g2.drawImage(image, 0, 0, 300, 400, this);
*/        
       //g2.drawImage(img, 0, 0, null);
    }
    /*@Override
    public Dimension getPreferredSize() {
        return new Dimension(960, 800);
    }
	*/
	
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
