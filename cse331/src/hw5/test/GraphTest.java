package hw5.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import hw5.Edge;
import hw5.EdgeComparator;
import hw5.Graph;

public class GraphTest {
	
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
  	new Graph<String, String>();
  }
  
  /**
   * Test adding nodes
   */
  @Test
  public void testAddingNodes() {
	Graph<String, String> graph1 = new Graph<>();
  	String[] nodes = {"a", "b", "d", "c", ""};
  	for (String label : nodes) {
  		graph1.addNode(label);
  	}
  }
  
  /**
   * Tests the isNode method
   */
  @Test
  public void testIsNode() {
	Graph<String, String> graph1 = new Graph<>();
	Graph<String, String> graph2 = new Graph<>();
  	String[] nodes = {"a", "b", "d", "c", ""};
  	for (String label : nodes) {
  		graph1.addNode(label);
  	}
  	assertTrue("a is in graph", graph1.isNode("a"));
  	assertTrue(graph1.isNode("b"));
  	assertFalse(graph1.isNode("e"));
  	assertTrue(graph1.isNode(""));
  	assertFalse(graph2.isNode("n1"));
  	assertFalse(graph2.isNode(""));
  }
  
  /**
   * Test that the getNodes() method works
   */
  @Test
  public void testGetNodes() {
	Graph<String, String> graph1 = new Graph<>();
  	String[] nodes = {"", "a", "b", "c", "d"};
  	for (String label : nodes) {
  		graph1.addNode(label);
  	}
  	Iterator<String> itr = graph1.getNodes().iterator();
  	for (String label: nodes) {
  		assertEquals(itr.next(), label);
  	}
  }
  
  
  /**
   * Test adding edges to the graph
   */
  @Test
  public void testAddingOneEdge() {
	Graph<String, String> graph1 = new Graph<>();
  	graph1.addEdge("a",  "b", "e1");
  	graph1.addEdge("b",  "c", "e1"); 	
  }
  
  /**
   * Test the isEdgeBetween method
   */
  @Test
  public void testIsEdgeBetween() {
	Graph<String, String> graph5 = new Graph<>();
  	graph5.addEdge("a", "b", "e1");
  	graph5.addEdge("b", "c", "e1"); 
  	graph5.addEdge("a", "a", "e1");
  	assertTrue(graph5.isEdgeBetween("a", "b"));
  	assertFalse(graph5.isEdgeBetween("b", "a"));
  	assertFalse(graph5.isEdgeBetween("c", "a"));
  	assertTrue(graph5.isEdgeBetween("b", "c"));
  	assertTrue(graph5.isEdgeBetween("a", "a"));
  	assertTrue(graph5.isEdgeBetween("a", "b", "e1"));
  	assertTrue(graph5.isEdgeBetween("b", "c", "e1"));
  	assertTrue(graph5.isEdgeBetween("a", "a", "e1"));
  	assertFalse(graph5.isEdgeBetween("a", "a", "e2"));
  	assertFalse(graph5.isEdgeBetween("a", "b", "e2"));
  }
  
  /**
   * Test getting edges in the graph
   */
  @Test
  public void testGetEdgesFrom() {
	Graph<String, String> graph6 = new Graph<>();
  	graph6.addEdge("a",  "a", "e1");
  	graph6.addEdge("a",  "a", "e2");
  	graph6.addEdge("a",  "b", "e1");
  	graph6.addEdge("a",  "c", "e1");
  	graph6.addEdge("a",  "c", "e2");
  	graph6.addEdge("a",  "d", "e1");
  	graph6.addEdge("b",  "c", "e1"); 
  	String[] edgesA = {"a(e1)", "a(e2)", "b(e1)", "c(e1)", "c(e2)", "d(e1)"};
  	Iterator<String> itr = getStringEdgesFrom(graph6, "a").iterator();
  	Iterator<String> itr2 = getStringEdgesFrom(graph6, "b").iterator();
  	for (String s : edgesA) {
  		assertEquals(itr.next(), s);
  	}
  	assertEquals(itr2.next(), "c(e1)");
  	assertEquals("should be empty", 0, getStringEdgesFrom(graph6, "c").size());
  }
  
  private List<String> getStringEdgesFrom(Graph<String, String> graph, String node) {
	List<Edge<String, String>> edges = graph.getEdgesFrom(node);
  	Collections.sort(edges, new EdgeComparator<String, String>());
  	List<String> result = new ArrayList<String>();
  	for (Edge<String, String> edge : edges) {
  		result.add(edge.getToNode() + "(" + edge.getLabel() + ")");
  	}
  	return result;
  }
  
  /**
   * Test adding multiple edges to same node
   */
  @Test
  public void testAddingMultipleEdges() {
	Graph<String, String> graph7 = new Graph<>();
  	graph7.addEdge("a",  "b", "e2");
  	graph7.addEdge("a",  "b", "e3");
  	graph7.addEdge("b",  "a", "e1");
  	graph7.addEdge("a",  "c", "e2");
  	graph7.addEdge("a",  "d", "e3");
  	String[] edgesA = {"b(e2)", "b(e3)", "c(e2)", "d(e3)"};
  	Iterator<String> itr = getStringEdgesFrom(graph7, "a").iterator();
  	for (String s : edgesA) {
  		assertEquals(itr.next(), s);
  	}
  }
  
  /**
   * Test adding edges to the graph that
   * already exist
   */
  @Test
  public void testAddingExistingEdges() {
	Graph<String, String> graph8 = new Graph<>();
  	graph8.addEdge("a",  "b", "e2");
  	graph8.addEdge("a",  "b", "e2");
  	graph8.addEdge("b",  "a", "e1");
  	graph8.addEdge("a",  "c", "e2");
  	graph8.addEdge("a",  "c", "e2");
  	String[] edgesA = {"b(e2)", "c(e2)"};
  	Iterator<String> itr = getStringEdgesFrom(graph8, "a").iterator();
  	for (String s : edgesA) {
  		assertEquals(itr.next(), s);
  	}
  }
  
  /**
   * Test adding edges to the graph for nodes that
   * do not exist
   */
  @Test
  public void testAddingEdgesWithNewNodes() {
	Graph<String, String> graph9 = new Graph<>();
  	graph9.addEdge("f",  "g", "e1");
  	graph9.addEdge("f",  "h", "e1"); 
  	String[] nodes = {"f", "g", "h"};
  	for (String s : nodes) {
  		assertTrue(graph9.isNode(s));
  	}
  	assertTrue(graph9.isEdgeBetween("f",  "g", "e1"));
  	assertTrue(graph9.isEdgeBetween("f",  "h", "e1"));
  }
  
  /**
   * Test the getChildren() method
   */
  @Test
  public void testGetChildren() {
	Graph<String, String> graph10 = new Graph<>();
  	graph10.addEdge("a",  "b", "e2");
  	graph10.addEdge("a",  "b", "e3");
  	graph10.addEdge("b",  "a", "e1");
  	graph10.addEdge("a",  "c", "e2");
  	graph10.addEdge("a",  "d", "e3");
  	String[] children = {"b", "c", "d"};
  	Iterator<String> itr = graph10.getConnectedNodes("a").iterator();
  	for (String s : children) {
  		assertEquals(itr.next(), s);
  	}
  	assertEquals(graph10.getConnectedNodes("b").size(), 1);
  	assertEquals(graph10.getConnectedNodes("c").size(), 0);
  } 
}
