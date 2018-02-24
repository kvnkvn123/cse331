package hw8.test;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import hw5.test.CheckAsserts;
import hw6.MarvelParser.MalformedDataException;
import hw8.CampusPathModel;

public class CampusPathModelTest {
	
	private CampusPathModel model;
	
	@Before
	public void testConstructor() throws MalformedDataException {
		model = new CampusPathModel();
	}
	
	/**
	 * checks that Java asserts are enabled, and exits if not
	 */
	@Before
	public void testAssertsEnabled() {
		CheckAsserts.checkAssertsEnabled();
	}
	
	@Test
	public void testContainsBuilding() {
		String[] buildings = {"MOR", "MLR", "MGH (E)", "KNE (SE)"};
		String[] notBuildings = {"er", "MOr", "", "MUS (W)", "KNE "};
		for (String b : buildings) {
			assertTrue(model.containsBuilding(b));
		}
		for (String b : notBuildings) {
			assertFalse(model.containsBuilding(b));
		}
	}
	
	@Test
	public void testLongName() {
		assertEquals(model.getLongName("KNE (S)"), "Kane Hall (South Entrance)");
		assertEquals(model.getLongName("EEB"), "Electrical Engineering Building (North Entrance)");
		assertEquals(model.getLongName("CHL (NE)"), "Chemistry Library (Northeast Entrance)");
		assertFalse(model.getLongName("CHL (SE)").equals("Chemistry Library (Northeast Entrance)"));
	}
	

}
