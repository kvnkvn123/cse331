package hw9;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import hw5.WeightedPath;
import hw8.Building;
import hw8.CampusPathModel;
import hw8.Point;

public class CampusMapControl extends JPanel {
	
	private final CampusPathModel model;
	private final MapView map;
	private Building start;
	private Building dest;
	
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
		
		// create a panel with drop down menus and buttons
		
		// first create drop down menus
		/*Set<Building> buildings = model.getBuildings();
		String[] buildingArray = new String[buildings.size()];
		int i = 0;
		for (Building b : buildings) {
			buildingArray[i] = b.getShortName();
			i++;
		}*/
		
		Set<Building> buildings = model.getBuildings();
		Building[] buildingArray = (Building[]) 
				buildings.toArray(new Building[buildings.size()]);
		for (Building b : buildingArray) {
			System.out.println(b.getShortName());
		}
		
		
		// configure menu
		JPanel menu = new JPanel();
		JComboBox<Building> startMenu = new JComboBox<>(buildingArray);
		JComboBox<Building> destMenu = new JComboBox<>(buildingArray);
		JButton findPath = new JButton("Find Path");
		JButton reset = new JButton("Reset");
		menu.add(startMenu);
		menu.add(destMenu);
		menu.add(findPath);
		menu.add(reset);
		
		// add action listener for start selection
		startMenu.addActionListener(e -> {
			Object source = e.getSource();
			if (source instanceof JComboBox<?>) {
				JComboBox<?> cb = (JComboBox<?>) source;
				Building b = (Building) cb.getSelectedItem();
				start = b;
				map.setStart(b);
				System.out.println("Start: " + b.getShortName());
			}
	    });
		
		// add action listener for end selection
		destMenu.addActionListener(e -> {
			Object source = e.getSource();
			if (source instanceof JComboBox<?>) {
				JComboBox<?> cb = (JComboBox<?>) source;
				Building b = (Building) cb.getSelectedItem();
				dest = b;
				map.setDest(b);
				System.out.println("Dest: " + b.getShortName());
			}
        });
		
		// add action listener for findPath button
		ButtonListener buttonlistener = new ButtonListener();
		findPath.addActionListener(buttonlistener);
		reset.addActionListener(buttonlistener);
		
		add(menu, BorderLayout.SOUTH);
		
	}
	
	public void findPath() {
		if (start != null && dest != null) {
			System.out.println(start.getShortName());
			System.out.println(dest.getShortName());
			WeightedPath<Point<Double>, Double> path = 
					model.findPath(start.getShortName(), dest.getShortName());
			map.setMap(path);
			map.notifyMapView();
		}
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
	    		map.reset();
	    	}
	    }
	}

}
