package hw5.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import hw5.Edge;
import hw5.Graph;
import hw5.WeightedPath;

public class WeightedPathTest {
	
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
	  WeightedPath<String, Double> test = 
			  new WeightedPath<>(new Edge<String, Double>("a", "b", 2.0));
	  assertEquals("a", test.getStart());
	  assertEquals("b", test.getDest());
	  assertTrue(test.getCost() == 2.0);
  }
  
  /**
   * Test iterator and adding edges
   */
  @Test
  public void testIterator() {
	  Edge<String, Double> edge1 = new Edge<String, Double>("a", "b", 1.0);
	  Edge<String, Double> edge2 = new Edge<String, Double>("b", "c", 2.0);
	  Edge<String, Double> edge3 = new Edge<String, Double>("c", "d", 1.0);
	  WeightedPath<String, Double> test = 
			  new WeightedPath<>(edge1);
	  test.addEdge(edge2);
	  test.addEdge(edge3);
	  Iterator<Edge<String, Double>> itr = test.iterator();
	  assertEquals(edge1, itr.next());
	  assertEquals(edge2, itr.next());
	  assertEquals(edge3, itr.next());
  }
  
  /**
   * Tests equals
   */
  @Test
  public void testEquals() {
	  Edge<String, Double> edge1 = new Edge<String, Double>("a", "b", 1.0);
	  Edge<String, Double> edge2 = new Edge<String, Double>("b", "c", 2.0);
	  Edge<String, Double> edge3 = new Edge<String, Double>("c", "d", 1.0);
	  WeightedPath<String, Double> test = 
			  new WeightedPath<>(edge1);
	  WeightedPath<String, Double> test2 = 
			  new WeightedPath<>(edge1);
	  assertTrue(test.equals(test2));
	  test.addEdge(edge2);
	  test.addEdge(edge3);
	  assertFalse(test.equals(test2));
	  assertFalse(test.equals(new Double(2.0)));
	  assertFalse(test.equals(null));
	  test2.addEdge(edge2);
	  test2.addEdge(edge3);
	  assertTrue(test.equals(test2));
  }
  
  /**
   * Test clone
   */
  @Test
  public void testClone() {
	  Edge<String, Double> edge1 = new Edge<String, Double>("a", "b", 1.0);
	  Edge<String, Double> edge2 = new Edge<String, Double>("b", "c", 2.0);
	  Edge<String, Double> edge3 = new Edge<String, Double>("c", "d", 1.0);
	  WeightedPath<String, Double> test = 
			  new WeightedPath<>(edge1);
	  test.addEdge(edge2);
	  test.addEdge(edge3);
	  WeightedPath<String, Double> test2 = test.clone();
	  Iterator<Edge<String, Double>> itr = test.iterator();
	  Iterator<Edge<String, Double>> itr2 = test2.iterator();
	  assertEquals(itr.next(), itr2.next());
	  assertEquals(itr.next(), itr2.next());
	  assertEquals(itr.next(), itr2.next());
	  assertEquals(test, test2);
  }
  
  /**
   * Test compareTo
   */
  public void testCompareTo() {
	  Edge<String, Double> edge1 = new Edge<String, Double>("a", "b", 1.0);
	  Edge<String, Double> edge2 = new Edge<String, Double>("b", "c", 2.0);
	  Edge<String, Double> edge3 = new Edge<String, Double>("c", "d", 1.0);
	  WeightedPath<String, Double> test = 
			  new WeightedPath<>(edge1);
	  test.addEdge(edge2);
	  test.addEdge(edge3);
	  WeightedPath<String, Double> test2 = test.clone();
	  assertTrue(test.compareTo(test2) == 0);
	  test.addEdge(new Edge<String, Double>("d", "e", 3.0));
	  test.addEdge(new Edge<String, Double>("d", "e", 4.0));
	  assertTrue(test.compareTo(test2) < 0);
	  assertTrue(test2.compareTo(test) > 0);	  
  }
  
}
