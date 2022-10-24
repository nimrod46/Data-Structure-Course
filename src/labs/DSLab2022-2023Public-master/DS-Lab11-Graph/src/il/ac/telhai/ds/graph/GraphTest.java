import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import Friendship;
import il.ac.telhai.ds.misc.Person;
import WeightedFriendship;

public class GraphTest {

	IGraph<String, Double> g;

	@Before
	public void setUp() throws Exception {
		g = new Graph<String, Double>();
	}

	@Test
	public void testGraph() {
		assertEquals("", g.toString());
	}

	@Test
	public void testAddV() {
		g.add("A");
		assertEquals("A", g.toString());
		g.add("B");
		assertEquals("A,B", g.toString());
	}

	@Test
	public void testRemoveNoEdges() {
		g.add("A");
		g.add("B");
		g.add("C");
		g.add("D");
		g.removeVertex("C");
		assertEquals("A,B,D", g.toString());
	}

	@Test
	public void testPutVVE() {
		g.add("A");
		g.add("B");
		g.add("C");
		g.add("D");
		g.putEdge("A", "B", 2.5);
		assertEquals("A,B,C,D", g.toString());
		assertEquals("A:{A,B}(2.5)\nB:{A,B}(2.5)\nC:\nD:", g.toStringExtended());
		g.putEdge("B", "C", 3.0);
		g.putEdge("C", "D", 4.5);
		assertEquals("A:{A,B}(2.5)\nB:{A,B}(2.5),{B,C}(3.0)\nC:{B,C}(3.0),{C,D}(4.5)\nD:{C,D}(4.5)",
				g.toStringExtended());
	}

	private IGraph<String, Double> createP4() {
		IGraph<String, Double> p4 = new Graph<String, Double>();
		p4.add("A");
		p4.add("B");
		p4.add("C");
		p4.add("D");
		p4.putEdge("A", "B", 2.5);
		p4.putEdge("B", "C", 3.0);
		p4.putEdge("C", "D", 4.5);
		return p4;
	}

	@Test
	public void testFriendship() {
		IGraph<Person, Friendship> fr = new Graph<Person, Friendship>();
		Person m = new Person("012960449", "M", "S");
		Person a = new Person("317919249", "A", "S");
		fr.add(m);
		fr.add(a);
		fr.putEdge(a, m, new Friendship((new GregorianCalendar(1995, 3, 15)).getTime()));
		assertEquals("Person [M S],Person [A S]", fr.toString());
		assertEquals("Person [M S]:{Person [A S],Person [M S]}\nPerson [A S]:{Person [A S],Person [M S]}",
				fr.toStringExtended());
		try {
			fr.getWeight(m, a);
			fail("Should throw exception");
		} catch (Exception e) {
		}
	}

	@Test
	public void testWeightedFriendship() {
		IGraph<Person, WeightedFriendship> fr = new Graph<Person, WeightedFriendship>();
		Person m = new Person("012960449", "M", "S");
		Person a = new Person("317919249", "A", "S");
		fr.add(m);
		fr.add(a);
		fr.putEdge(a, m, new WeightedFriendship((new GregorianCalendar(1995, 3, 15)).getTime(), 33));
		assertEquals(33.0, fr.getWeight(m, a), 0);
	}

	@Test
	public void testGet() {
		IGraph<String, Double> p4 = createP4();
		assertEquals(2.5, p4.getEdge("A", "B"), 0);
		assertNull(p4.getEdge("A", "C"));
	}

	@Test
	public void testGetWeightSimple() {
		IGraph<String, Double> p4 = createP4();
		assertEquals(2.5, p4.getWeight("A", "B"), 0);
		assertEquals(0, p4.getWeight("A", "C"), 0);
	}

	@Test
	public void testAreAdjacent() {
		IGraph<String, Double> p4 = createP4();
		assertTrue(p4.areAdjacent("A", "B"));
		assertTrue(p4.areAdjacent("B", "C"));
		assertTrue(p4.areAdjacent("C", "D"));
		assertTrue(p4.areAdjacent("D", "C"));
		assertFalse(p4.areAdjacent("A", "C"));
		assertFalse(p4.areAdjacent("E", "C"));
		assertFalse(p4.areAdjacent("A", "E"));
	}

	@Test
	public void testRemoveEdge() {
		IGraph<String, Double> p4 = createP4();
		p4.removeEdge("B", "C");
		assertEquals("A:{A,B}(2.5)\nB:{A,B}(2.5)\nC:{C,D}(4.5)\nD:{C,D}(4.5)", p4.toStringExtended());
		try {
			p4.removeEdge("A", "E");
			p4.removeEdge("E", "A");
			fail("Should throw exception");
		} catch (Exception e) {
		}
		assertNull(p4.removeEdge("B", "C"));
	}

	@Test
	public void testContains() {
		IGraph<String, Double> p = createP4();
		assertTrue(p.containsVertex("A"));
		assertTrue(p.containsVertex("B"));
		assertTrue(p.containsVertex("C"));
		assertTrue(p.containsVertex("D"));
		assertFalse(p.containsVertex("E"));
		assertFalse(p.containsVertex("F"));

		IGraph<Person, WeightedFriendship> fr = new Graph<Person, WeightedFriendship>();
		Person m = new Person("012960449", "M", "S");
		Person a = new Person("317919249", "A", "S");
		fr.add(m);
		fr.add(a);
		assertTrue(fr.containsVertex(m));
		assertTrue(fr.containsVertex(new Person("317919249", "A", "S")));
		assertFalse(fr.containsVertex(new Person("327919249", "A", "S")));
	}

}