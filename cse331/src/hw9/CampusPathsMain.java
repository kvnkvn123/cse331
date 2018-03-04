package hw9;

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import hw6.MarvelParser.MalformedDataException;
import hw8.CampusPathModel;

public class CampusPathsMain {
	
	public static void main(String[] args) throws IOException, MalformedDataException {
	    // Create new window and set it to exit from application when closed
	    JFrame frame = new JFrame("Campus Paths");
	    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    
	    /*BufferedImage myPicture = ImageIO.read(new File("src/hw8/data/campus_map.jpg"));
	    JLabel picLabel = new JLabel(new ImageIcon(myPicture));
	    picLabel.setPreferredSize(new Dimension(300, 300));
	    frame.add(picLabel);*/
	    /*JPanel panel = new JPanel();
	    //panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
	    
	    String[] petStrings = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };
	    JComboBox<String> test = new JComboBox<>(petStrings);
	    panel.add(test);
	    
	    panel.add(new JButton("Start"));
	    panel.add(new JButton("Dest"));
	    panel.add(new JButton("Reset"));
	    
	    
	    JPanel f = new MapView();
	    //f.setPreferredSize(new Dimension(200,200));
	    f.setBackground(Color.cyan);
	    frame.add(f, BorderLayout.CENTER);
	    //JLabel jlbl = new JLabel("Don't worry, be happy!", SwingConstants.CENTER);
	    //frame.add(jlbl, BorderLayout.SOUTH);
	    frame.add(panel, BorderLayout.EAST);*/
	    
	    JPanel main = new CampusMapControl(new CampusPathModel());
	    main.setPreferredSize(new Dimension(960, 600));
	    frame.add(main);
	    
	    // pack layout to natural sizes of components, then display
	    frame.pack();
	    
	    frame.setVisible(true);
	}
}
