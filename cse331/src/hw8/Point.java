package hw8;

/**
 * <tt>Point<E></tt> represents a 2 dimensional point, or
 * an object with an x and y coordinate. Both x and y coordinates
 * are of type E.
 * 
 * Specification fields:
 *  @specfield x : E // The x-coordinate of the point
 *  @specfield y : E // The y-coordinate of the point
 *
 *
 */
public final class Point<E> {
	
	/** The x-coordinate of this point*/
	private final E x;
	
	/** The y-coordinate of this point*/
	private final E y;
	
	// Representation Invariant:
	//  true
	//  
	// Abstraction Function:
	//	AF(r) = a point p, such that
	//  p.x = r.x
	//  p.y = r.y
	
	// no checkRep since
	// repInvariant is true
	
	/**
	 * Constructs a new point with given coordinates
	 * 
	 * @param x the x coordinate of the new point
	 * @param y the y coordinate of the new point
	 * @effects constructs a new point with given coordinates
	 */
	public Point(E x, E y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Constructs a new point with coordinates equal
	 * to those of the given point
	 * 
	 * @param p a point which the given constructor constructs
	 *	an equivalent point 
	 * @effects constructs a new point equal to the given point
	 * @throws IllegalArgumentException if p == null
	 */
	public Point(Point<E> p) {
		if (p == null) {
			throw new IllegalArgumentException();
		}
		this.x = p.getX();
		this.y = p.getY();
	}
	
	/**
	 * returns the x coordinate of this Point
	 * 
	 * @return the x coordinate of this Point
	 */
	public E getX() {
		return x;
	}
	
	/**
	 * returns the y coordinate of this Point
	 * 
	 * @return the y coordinate of this Point
	 */
	public E getY() {
		return y;
	}
	
	/**
	 * Standard equality operation.
  	 *
  	 * @param obj The object to be compared for equality.
  	 * @return true iff 'obj' is an instance of an Point and 
  	 * 	'this' and 'obj' have equal x and y coordinates.
  	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Point<?>) {
			Point<?> point = (Point<?>) obj;

			// points are equal if x and y correspond
			return x.equals(point.x) && 
					y.equals(point.y);
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
		return (171 * x.hashCode()) + (19 * y.hashCode());
	}
}
