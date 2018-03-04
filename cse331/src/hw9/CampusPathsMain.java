package hw9;

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import hw6.MarvelParser.MalformedDataException;
import hw8.CampusPathModel;

public class CampusPathsMain {
	
	// this class is not an ADT and
	// therefore has not rep invariant
	
	public static void main(String[] args) throws IOException, MalformedDataException {
		
	    // Create new window and set it to exit from application when closed
	    JFrame frame = new JFrame("Campus Paths");
	    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    
	    // create a new model and
	    // initialize panel with map search application
	    JPanel main = new CampusMapControl(new CampusPathModel());
	    main.setPreferredSize(new Dimension(960, 600));
	    frame.add(main);
	    
	    // pack layout to natural sizes of components, then display
	    frame.pack();
	    
	    // display
	    frame.setVisible(true);
	}
}
