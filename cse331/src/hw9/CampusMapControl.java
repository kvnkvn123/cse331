package hw9;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import hw5.WeightedPath;
import hw8.Building;
import hw8.CampusPathModel;
import hw8.Point;

/**
 *  CampusMapControl manages to user interface 
 *  of the campus map application, dsiplaying a map
 *  and menu for user input, and displaying the appropriate
 *  map display based on user input
 *
 */
public class CampusMapControl extends JPanel {
	
	// Rep invariant
	// model != null) $$
	// map != null &&
	// startMenu != null &&
	// destMenu != null &&
	// display != null
	
	// abstract invariant : represents the model
	// and controller of the application, with
	// map representing the map view
	// and startMenu and DestMenu representing
	// recording user selections
	
	/**
	 *  Checks the representation invariant
	 */
	private void checkRep() {
		assert (model != null) : "model is null";
		assert (map != null) : "map is null";
		assert (startMenu != null) : "startMenu is null";
		assert (destMenu != null) : "destMenu is null";
		assert (display != null) : "display is null";
	}
	
	/** reference to the model used for this application */
	private final CampusPathModel model;
	
	/** panel used to display the map */
	private final MapView map;
	
	/** start building selected by user */
	private Building start;
	
	/** destination building selected by user */
	private Building dest;
	
	/** drop down menu for start building selection */
	private JComboBox<Building> startMenu;
	
	/** drop down menu for destination building selection */
	private JComboBox<Building> destMenu;
	
	/** label used to display path distance */
	private DisplayLabel display;
	
	/**
	 *  Constructs the panel to create a map, menu for 
	 *  user selection and a distance display. 
	 *  
	 * @param model the model used to calculate path data
	 */
	public CampusMapControl(CampusPathModel model) {
		
		// store a reference to the model
		this.model = model;
		
		// set initial start and dest to null
		this.start = null;
		this.dest = null;
		
		// set layout
		setLayout(new BorderLayout());
		
		// add map to panel
		map = new MapView();
		map.setPreferredSize(new Dimension(map.getWidth(), map.getHeight()));
		add(map, BorderLayout.CENTER);
		
		// create the menu and add to panel
		JPanel menu = createMenu();
		add(menu, BorderLayout.SOUTH);
		
		checkRep();
		
	}
	
	/**
	 *  Creates a menu bar for the path
	 *  search application with drop down menus, find path 
	 *  and reset button, and a distance display
	 * 
	 * @return menu created
	 */
	private JPanel createMenu() {
		
		// create menu and set layout
		JPanel menu = new JPanel();
		FlowLayout menuLayout = (FlowLayout) menu.getLayout();
		menuLayout.setHgap(20);
			
		// create array of all buildings
		Set<Building> buildings = model.getBuildings();
		Building[] buildingArray = (Building[]) 
				buildings.toArray(new Building[buildings.size()]);
		
		// create drop down menus for building selection
		startMenu = new JComboBox<>(buildingArray);
		startMenu.setSelectedIndex(-1);
		destMenu = new JComboBox<>(buildingArray);
		destMenu.setSelectedIndex(-1);
		
		// create start selection subcomponent
		JPanel startPanel = new JPanel();
		FlowLayout startLayout = (FlowLayout) startPanel.getLayout();
		startLayout.setHgap(10);
		JLabel startText = new JLabel("Start");
		startPanel.add(startText);
		startPanel.add(startMenu);
		
		//create destination selection subcomponent
		JPanel destPanel = new JPanel();
		FlowLayout destLayout = (FlowLayout) destPanel.getLayout();
		destLayout.setHgap(10);
		JLabel destText = new JLabel("Destination");
		destPanel.add(destText);
		destPanel.add(destMenu);
		
		// initialize display
		display = new DisplayLabel("Distance:", "choose path");
		
		// create buttons
		JButton findPath = new JButton("Find Path");
		JButton reset = new JButton("Reset");
		
		// add action listener for start selection
		MenuListener menuListener = new MenuListener();
		startMenu.addActionListener(menuListener);
		destMenu.addActionListener(menuListener);
				
		// add action listener for findPath button
		ButtonListener buttonlistener = new ButtonListener();
		findPath.addActionListener(buttonlistener);
		reset.addActionListener(buttonlistener);

		// add components to menu
		menu.add(startPanel);
		menu.add(destPanel);
		menu.add(display);
		menu.add(findPath);
		menu.add(reset);
		
		// return menu
		return menu;
	}
	
	/**
	 *  If user has selected start and destination building
	 *  communicated with model to determine shortest path between
	 *  buildings and displays on the map and distance display
	 */
	private void findPath() {
		checkRep();
		if (start != null && dest != null) {
			WeightedPath<Point<Double>, Double> path = 
					model.findPath(start.getShortName(), dest.getShortName());
			String distance = String.format("%.0f", path.getCost());
			display.setDisplayText(distance + " ft");
			map.setMap(path);
			map.notifyMapView();
		}
		checkRep();
	}
	
	/**
	 * Resets the view to initial conditions
	 */
	private void reset() {
		checkRep();
		map.reset();
		display.reset();
		startMenu.setSelectedIndex(-1);
		destMenu.setSelectedIndex(-1);
		checkRep();
	}
	
	/**
	 * Handles button clicks for the application
	 */
	class ButtonListener implements ActionListener {
	    
	    /**
	     * Process button clicks by turning the simulation on and off
	     * @param e the event created by the button click
	     */
	    public void actionPerformed(ActionEvent e) {
	    	if (e.getActionCommand().equals("Find Path")) {
	    		findPath();
	    	} else if (e.getActionCommand().equals("Reset")) {
	    		reset();
	    	}
	    }
	}
	
	/**
	 * Handles JComboBox selections
	 */
	class MenuListener implements ActionListener {
		
		/**
	     * Process menu selections by turning the simulation on and off
	     * @param e the event created by the button click
	     */
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			if (source instanceof JComboBox<?>) {
				JComboBox<?> cb = (JComboBox<?>) source;
				Building b = (Building) cb.getSelectedItem();
				if (source == startMenu) {
					start = b;
					map.setStart(b);
				} else if (source == destMenu) {
					dest = b;
					map.setDest(b);
				}
			}
			display.reset();
		}
	}
}
