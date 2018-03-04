package hw8;

/**
 * <tt>Building</tt> represents a building with
 * 	an abbreviated name, full name, and coordinate
 * 	location
 * 
 * Specification fields:
 *  @specfield shortName : string // abbreviated name of the building
 *  @specfield longName : string // full name of the building
 *  @specfield location : point // coordinate location of building
 *
 */
public final class Building implements Comparable<Building> {
	
	/** abbreviated name of the building */
	private final String shortName;
	
	/** Full name of the building */
	private final String longName;
	
	/** Location of the building */
	private final Point<Double> location;
	
	// Representation Invariant:
	//  this.shortName != null &&
	//	this.longName != null
	//
	// Abstraction Function:
	//	AF(r) = a Building b, such that
	//  b.shortName = r.shortName
	//  b.longName = r.longName
	//  b.location = r.location
	
	/** checks the representation invariant of this */
	private void checkRep() {
		assert (shortName != null) : "shortName cannot be null";
		assert (longName != null) : "longName cannot be null";
	}
	
	/**
	 * Constructs a building with the given
	 * shortName, longName, and x and y coordinates
	 * 
	 * @param shortName the shortName of the building
	 * @param longName the longName of the building
	 * @param x the x coordinate of location
	 * @param y the y coordinate of location
	 * @effects constructs a new building with the given information
	 * @throws IllegalArgumentException if shortName = null || longName = null
	 */
	public Building(String shortName, String longName, double x, double y) {
		if (shortName == null || longName == null) {
			throw new IllegalArgumentException();
		}
		this.shortName = shortName;
		this.longName = longName;
		this.location = new Point<Double>(x, y);
	}
	
	/**
	 * Returns the shortName of this building
	 * @return shortName of this building
	 */
	public String getShortName() {
		return shortName;
	}
	
	/**
	 * Returns the longName of this building
	 * @return longName of this building
	 */
	public String getLongName() {
		return longName;
	}
	
	/**
	 * Returns the location of this building
	 * @return location of this building
	 */
	public Point<Double> getLocation() {
		return location;
	}
	
	/**
	 * Compares two buildings according to the lexicographic 
	 * order of their shortNames.
	 * 
	 * @param other the building to compare this to
	 * @return the value 0 if the this building's shortname is
	 *  equal to others shortName; a value less than 0 if this
	 *  building's shortName is lexicographically less than others
	 *  shortName argument; and a value greater than 0 if this
	 *  buildings shortName is lexicographically greater than4
	 *  other's shortName.
	 */
	public int compareTo(Building other) {
		return this.shortName.compareTo((other.shortName));
	}
	
	/**
	 * Standard equality operation.
  	 *
  	 * @param obj The object to be compared for equality.
  	 * @return true iff 'obj' is an instance of a building and 
  	 * 	'this' and 'obj' have equal .
  	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Building) {
			Building b = (Building) obj;

			// Buildings are equal if shortName and longName correspond
			// and location corresponds
			return shortName.equals(b.shortName); 
		} else {
			return false;
		}
	}
	
	/** 
	 * Standard hashCode function.
	 * @return an int that all objects equal to this will also
	 * 	return.
	 */
	@Override
	public int hashCode() {
		checkRep();
		return shortName.hashCode();
	}
	
	/**
	 * Returns the string representation of the 
	 * buildings's shortName
	 */
	@Override
	public String toString() {
		return shortName;
	}
}
