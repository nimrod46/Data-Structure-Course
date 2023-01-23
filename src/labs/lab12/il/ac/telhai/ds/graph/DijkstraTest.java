package labs.lab12.il.ac.telhai.ds.graph;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DijkstraTest {

	@Test
	public void testSingleton () {
		Graph<String, Double> g = new Graph<String, Double>();
		g.add("A");
		assertEquals("{A=0.0}", g.distancesFrom("A").toString());    
	}

	@Test
	public void test2K1 () {
		Graph<String, Double> g = new Graph<String, Double>();
		g.add("A");
		g.add("B");
		assertEquals("{A=0.0}", g.distancesFrom("A").toString());
		assertEquals("{B=0.0}", g.distancesFrom("B").toString());
	}

	@Test
	public void testK2 () {
		Graph<String, Double> g = new Graph<String, Double>();
		g.add("A");
		g.add("B");
		g.putEdge("A", "B", 5.5);
		assertEquals("{A=0.0, B=5.5}", g.distancesFrom("A").toString());
		assertEquals("{A=5.5, B=0.0}", g.distancesFrom("B").toString());
	}

	@Test
	public void testP4P1() {
		Graph<String, Double> p1 = new Graph<String, Double>();
		p1.add("A");
		p1.add("B");
		p1.add("C");
		p1.add("D");
		p1.add("E");
		p1.putEdge("A", "B", 2.5);
		p1.putEdge("B", "C", 3.0);
		p1.putEdge("C", "D", 4.5);
		assertEquals("{A=0.0, B=2.5, C=5.5, D=10.0}", p1.distancesFrom("A").toString());
		assertEquals("{A=2.5, B=0.0, C=3.0, D=7.5}", p1.distancesFrom("B").toString());
		assertEquals("{E=0.0}", p1.distancesFrom("E").toString());
	}

	@Test
	public void testLabGraph() {
		Graph<String, Double> g = new Graph<String, Double>();
		g.add("A");
		g.add("B");
		g.add("C");
		g.add("D");
		g.add("E");
		g.add("F");
		g.add("G");
		g.add("H");
		g.add("I");
		g.putEdge("A", "B", 1.0);
		g.putEdge("A", "C", 1.0);
		g.putEdge("A", "I", 6.0);
		g.putEdge("B", "C", 3.0);
		g.putEdge("B", "D", 4.5);
		g.putEdge("B", "G", 5.0);
		g.putEdge("C", "D", 1.0);
		g.putEdge("C", "H", 1.0);
		g.putEdge("C", "I", 2.5);
		g.putEdge("E", "F", 1.0);
		g.putEdge("G", "H", 1.0);
		g.putEdge("H", "I", 1.0);
		assertEquals("{A=1.0, B=0.0, C=2.0, D=3.0, G=4.0, H=3.0, I=4.0}", g.distancesFrom("B").toString());
		assertEquals("{E=0.0, F=1.0}", g.distancesFrom("E").toString());
	}

	

}