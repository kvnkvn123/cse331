package hw8.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import hw5.test.CheckAsserts;
import hw8.Building;
import hw8.Point;

public class BuildingTest {
	
	private Building suz = new Building("suz", "suzallo", 0.0, 1.1);
	private Building suz2 = new Building("sud", "suzallo", 0.0, 1.1);
	private Building suz3 = new Building("suz", "suzallp", 0.0, 1.1);
	private Building al = new Building("al", "allen library", 8.5, 1.1);
	private Building ode = new Building("ode", "odegaard", -4.5, -3);
	
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
		new Building("test", "testBuilding", 0.0, 1.1);
	}
	
	@Test
	public void testAccessors() {
		assertEquals("suz", suz.getShortName());
		assertEquals("allen library", al.getLongName());
		Point<Double> p = new Point<>(8.500, 1.100);
		assertEquals(p, al.getLocation());		
	}
	
	@Test
	public void testCompareTO() {
		assertTrue(suz.compareTo(al) > 0);
		assertFalse(al.compareTo(suz) >= 0);
		assertTrue(suz.compareTo(suz2) > 0);
		assertFalse(suz2.compareTo(suz) >= 0);
		assertTrue(suz.compareTo(suz3) == 0);		
	}
	
	public void testEqualsAndHashCode() {
		assertTrue(suz.equals(suz3));
		assertFalse(suz.equals(suz2));
		assertFalse(al.equals(ode));
		assertTrue(suz.hashCode() == suz3.hashCode());
		assertFalse(suz.hashCode() == suz2.hashCode());
		assertFalse(ode.hashCode() == al.hashCode());
	}
}