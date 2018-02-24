package hw8.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import hw5.test.CheckAsserts;
import hw8.Building;
import hw8.Point;

public class PointTest {
	
	Point<Double> p1 = new Point<Double>(0.0, 0.0);
	Point<Double> p2 = new Point<Double>(8.475, 3.9);
	Point<Double> p3 = new Point<Double>(-1.0, 2.2);
	Point<String> p4 = new Point<String>("test", "point");
	Point<Double> p5 = new Point<Double>(-1.0, 2.2);
	Point<Double> p6 = new Point<Double>(2.2, -1.0);
	
	
	/**
	 * checks that Java asserts are enabled, and exits if not
	 */
	@Before
	public void testAssertsEnabled() {
		CheckAsserts.checkAssertsEnabled();
	}
	
	/**
	 * This method tests constructors with valid arguments
	 */
	@Test
	public void testConstructor() {
		new Point<Double>(8.0, 13.9);
		new Point<String>("are", "you");
		new Point<Double>(new Point<Double>(3.5, 4.5));		
	}
	
	@Test
	public void testAccessors() {
		assertTrue(0.0 == p1.getX() && 0.0 == p1.getY());
		assertTrue(p2.getX() == 8.475);
		assertEquals(p4.getX(), "test");
		
	}
	
	@Test
	public void testEqualsAndHashCode() {
		assertEquals(p3, new Point<Double>(-1.000, 2.2000));
		assertEquals(p3.hashCode(), p5.hashCode());
		assertFalse(p3.hashCode() == p6.hashCode());
	}

}
