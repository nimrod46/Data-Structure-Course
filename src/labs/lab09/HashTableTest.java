package labs.lab09;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HashTableTest {

	private Person p1;
	private Person p3;
	private Person p5;
	private Person p7;
	private Person p10;
	private Person p15;
	private HashTable<Person> hashPerson;

	@Before
	public void setUp() throws Exception {
		p10 = new Person("10", "10firstName", "10lastName");
		p5 = new Person("05", "05firstName", "05lastName");
		p1 = new Person("01", "01firstName", "01lastName");
		p3 = new Person("03", "03firstName", "03lastName");
		p7 = new Person("07", "07firstName", "07lastName");
		p15 = new Person("15", "15firstName", "15lastName");
		hashPerson = new HashTable<Person>();
	}
	
	@Test
	public void testAddContains() {
		assertTrue(hashPerson.add(p5));
		assertFalse(hashPerson.add(p5));
		assertTrue(hashPerson.contains(p5));
		assertTrue(hashPerson.contains(new Person("05", "05firstName", "05lastName")));
		assertFalse(hashPerson.contains(new Person("05", "", "")));
		assertFalse(hashPerson.contains(p1));
		assertTrue(hashPerson.add(p1));
		assertTrue(hashPerson.contains(p1));
		assertTrue(hashPerson.add(p3));
		assertTrue(hashPerson.add(p7));
		assertTrue(hashPerson.add(p15));
		assertTrue(hashPerson.contains(p3));
		assertTrue(hashPerson.contains(p7));
		assertTrue(hashPerson.contains(p15));
		assertFalse(hashPerson.contains(p10));
		
		assertFalse(hashPerson.add(p1));
		assertFalse(hashPerson.add(p3));
		assertFalse(hashPerson.add(p7));
		assertFalse(hashPerson.add(p15));
	}

	@Test
	public void testRemove() {
		hashPerson.add(p5);
		hashPerson.add(p1);
		hashPerson.add(p3);
		hashPerson.add(p7);
		hashPerson.add(p15);
		assertTrue(hashPerson.remove(p15));
		assertFalse(hashPerson.remove(p10));
		assertTrue(hashPerson.remove(p1));
		assertTrue(hashPerson.remove(p5));
		assertFalse(hashPerson.remove(p5));
		assertFalse(hashPerson.remove(p1));
	}

	@Test
	public void testClear() {
		hashPerson.add(p5);
		hashPerson.add(p1);
		hashPerson.add(p3);
		hashPerson.add(p7);
		hashPerson.add(p15);
		assertFalse(hashPerson.isEmpty());
		hashPerson.clear();
		assertTrue(hashPerson.isEmpty());
	}

	@Test
	public void testIsEmpty() {
		assertTrue(hashPerson.isEmpty());
		hashPerson.add(p1);
		assertFalse(hashPerson.isEmpty());
		hashPerson.remove(p1);
		assertTrue(hashPerson.isEmpty());
	}

}