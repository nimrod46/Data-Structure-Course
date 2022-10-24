import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public abstract class ListTest<T> {

	private DListFactory<T> dListFactory = new DListFactory<T>();
	private List<T> dList;

	private T newInstance1;
	private T newInstance2;
	private T newInstance3;

	/**
	 * @return a new instance of parameter T. Two instances which are created by
	 *         this method should be different according to "equals" method. For
	 *         example for T=Object, the implementation can be "return new
	 *         Object();".
	 */

	public abstract T getParameterInstance();

	@Before
	public void setUp() throws Exception {
		dList = dListFactory.getDList();

		newInstance1 = getParameterInstance();
		newInstance2 = getParameterInstance();
		newInstance3 = getParameterInstance();
	}

	/**
	 * @return a new instance of parameter T. Two instances which are created by
	 *         this method should be different according to "equals" method. For
	 *         example for T=Object, the implementation can be "return new
	 *         Object();".
	 */


	@Test
	public void testGetParameterInstance() {
		if ((newInstance1.equals(newInstance2)) || (newInstance1.equals(newInstance3))
				|| (newInstance3.equals(newInstance2))) {
			fail("new instances should be different");
		}
	}

	@Test
	public void testIsEmpty() {
		assertTrue(dList.isEmpty());
		dList.insert(newInstance1);
		assertFalse(dList.isEmpty());
		dList.insert(newInstance2);
		assertFalse(dList.isEmpty());
		dList.clear();
		assertTrue(dList.isEmpty());
		dList.insert(newInstance2);
		assertFalse(dList.isEmpty());
		dList.remove(newInstance2);
		assertTrue(dList.isEmpty());
	}

	@Test
	public void testInsert1() {
		try {
			dList.insert(null);
			fail("Should not work");
		} catch (Exception e) {
		}
		dList.insert(newInstance1);
		assertEquals(newInstance1, dList.getCursor());
	}

	@Test
	public void testInsert2() {
		dList.insert(newInstance1);
		assertEquals(newInstance1, dList.getCursor());

		dList.insert(newInstance2);
		assertEquals(newInstance2, dList.getCursor());

		dList.goToBeginning();
		dList.insert(newInstance3);
		assertEquals(newInstance3, dList.getCursor());
		assertEquals(newInstance2, dList.getNext());
	}

	@Test
	public void testRemove() {
		testRemove(newInstance1);
		testRemove(newInstance1, newInstance2, newInstance3);
	}

	private void testRemove(T newInstance1) {
		T deleted = dList.remove();
		assertNull(deleted);

		deleted = dList.remove(newInstance1);
		assertNull(deleted);

		dList.insert(newInstance1);
		deleted = dList.remove();
		assertEquals(newInstance1, deleted);

		assertTrue(dList.isEmpty());
	}

	private void testRemove(T newInstance1, T newInstance2, T newInstance3) {
		T deleted;

		dList.insert(newInstance1);
		dList.insert(newInstance2);
		dList.insert(newInstance3);
		T prev = dList.getPrev();
		assertEquals(newInstance2, prev);
		deleted = dList.remove();
		assertEquals(newInstance3, dList.getCursor());
		assertEquals(newInstance2, deleted);
		deleted = dList.remove(newInstance2);
		assertNull(deleted);
		dList.insert(newInstance2);
		deleted = dList.remove(newInstance1);
		assertEquals(newInstance1, deleted);
		deleted = dList.remove();
		assertEquals(newInstance3, deleted);
		deleted = dList.remove();
		assertEquals(newInstance2, deleted);
		assertTrue(dList.isEmpty());
	}

	@Test
	public void testClear() {
		dList.clear();
		assertTrue(dList.isEmpty());

		dList.insert(newInstance1);
		dList.insert(newInstance2);
		dList.insert(newInstance3);
		assertFalse(dList.isEmpty());
		dList.clear();
		assertTrue(dList.isEmpty());

		dList.insert(newInstance3);
		assertFalse(dList.isEmpty());
		assertEquals(dList.getCursor(), newInstance3);
		dList.clear();
		assertTrue(dList.isEmpty());
		assertNull(dList.getCursor());
	}

	@Test
	public void testReplace() {
		try {
			dList.replace(newInstance1);
			fail("Should not work");
		} catch (Exception e) {
		}

		dList.insert(newInstance1);
		try {
			dList.replace(null);
			fail("Should not work");
		} catch (Exception e) {
		}
		dList.insert(newInstance2);
		dList.insert(newInstance1);

		assertEquals(dList.getCursor(), newInstance1);
		dList.replace(newInstance3);
		assertEquals(dList.getCursor(), newInstance3);

		dList.goToBeginning();
		assertEquals(dList.getCursor(), newInstance1);
		dList.replace(newInstance3);
		assertEquals(dList.getCursor(), newInstance3);

	}

	@Test
	public void testReplaceNextAndPrev() {

		dList.insert(newInstance1);
		dList.insert(newInstance1);
		dList.insert(newInstance3);

		dList.getPrev();
		dList.replace(newInstance2);
		assertEquals(dList.getCursor(), newInstance2);
		assertEquals(dList.getPrev(), newInstance1);
		assertEquals(dList.getNext(), newInstance2);
		assertEquals(dList.getNext(), newInstance3);
		assertEquals(dList.getPrev(), newInstance2);
	}

	@Test
	public void testGoToBeginning() {
		dList.insert(newInstance1);
		dList.insert(newInstance2);
		dList.insert(newInstance3);

		assertEquals(dList.getCursor(), newInstance3);
		assertEquals(dList.goToBeginning(), true);
		assertEquals(dList.getCursor(), newInstance1);
	}

	@Test
	public void testGoToEnd() {
		assertEquals(dList.goToBeginning(), false);
		assertEquals(dList.goToEnd(), false);

		dList.insert(newInstance1);
		dList.insert(newInstance2);
		dList.insert(newInstance3);

		assertEquals(dList.getCursor(), newInstance3);
		assertEquals(dList.goToBeginning(), true);
		assertEquals(dList.getCursor(), newInstance1);
		assertEquals(dList.goToEnd(), true);
		assertEquals(dList.getCursor(), newInstance3);
	}

	@Test
	public void testPrevAndNext() {

		assertFalse(dList.hasNext());
		assertFalse(dList.hasPrev());
		assertNull(dList.getNext());
		assertNull(dList.getPrev());

		dList.insert(newInstance1);
		assertFalse(dList.hasNext());
		assertFalse(dList.hasPrev());
		assertNull(dList.getNext());
		assertNull(dList.getPrev());

		dList.insert(newInstance2);
		assertFalse(dList.hasNext());
		assertTrue(dList.hasPrev());
		assertNull(dList.getNext());
		assertEquals(dList.getPrev(), newInstance1);
		assertEquals(dList.getCursor(), newInstance1);
		assertNull(dList.getPrev());
		assertEquals(dList.getCursor(), newInstance1);
		assertEquals(dList.getNext(), newInstance2);
		assertEquals(dList.getCursor(), newInstance2);

		dList.insert(newInstance3);

	}

	@Test
	public void testHasNextAndPrev() {
		//TODO Add tests for HasNext and HasPrev methods.
	}


}
