package hw9;

import javax.swing.JLabel;

/**
 * locationLabel is used to 
 * display the users selected choices
 * for start and destination points on the map
 *
 */
public class DisplayLabel extends JLabel {
	
	// this class has no checkRep because
	// its representation invariant is true
	
	/** prompt the label begins with */
	private String prompt;
	
	/** text the label initializes with */
	private String startText;
	
	/**
	 * Constructs a DisplayLabel with initial text
	 * 
	 * @param prompt Fixed text in the label
	 * @param startText initial text which varies
	 *  as user selects items
	 */
	public DisplayLabel(String prompt, String startText) {
		super(prompt + " " + startText);
		this.prompt = prompt;
		this.startText = startText;
	}
	
	/**
	 * Resets the labels text to initial conditions
	 * @modifies this
	 * @effects changes the text in the label
	 */
	public void reset() {
		setText(prompt + " " + startText);
	}
	
	/**
	 * Sets the variable text to the given 
	 * @param displayText the text to be added to
	 * 	the label
	 */
	public void setDisplayText(String displayText) {
		setText(prompt + " " + displayText);
	}
}
