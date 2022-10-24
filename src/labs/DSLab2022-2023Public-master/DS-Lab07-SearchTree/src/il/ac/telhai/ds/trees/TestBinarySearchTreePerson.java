import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import Person;

public class TestBinarySearchTreePerson {

	private Person p1;
	private Person p3;
	private Person p5;
	private Person p7;
	private Person p10;
	private Person p15;
	private BinarySearchTree<Person> treePerson;

	@Before
	public void setUp() throws Exception {
		p10 = new Person("10", "10firstName", "10lastName");
		p5 = new Person("05", "05firstName", "05lastName");
		p1 = new Person("01", "01firstName", "01lastName");
		p3 = new Person("03", "03firstName", "03lastName");
		p7 = new Person("07", "07firstName", "07lastName");
		p15 = new Person("15", "15firstName", "15lastName");
		treePerson = new BinarySearchTree<Person>(p10);
		treePerson.add(p5);
		treePerson.add(p1);
		treePerson.add(p3);
		treePerson.add(p7);
		treePerson.add(p15);
	}

	@Test
	public void testSet() {
		//TODO may we want to allow setRight to the same <key,val>? - add a test for it.
		try {//TODO may we want to allow setValue
			treePerson.setValue(p1);
			fail("Shouldn't work");
		}
		catch (Exception e) {
		}
		
		try {
			treePerson.setLeft(new BinaryTree<Person>(p1));
			fail("Shouldn't work");
		}
		catch (Exception e) {
		}
		
		try {
			treePerson.setRight(new BinaryTree<Person>(p15));
			fail("Shouldn't work");
		}
		catch (Exception e) {
		}
		
		try {
			treePerson.setLeft( new BinarySearchTree<Person>(p15));
			fail("Shouldn't work");
		}
		catch (Exception e) {
		}
		
		try {
			treePerson.setRight( new BinarySearchTree<Person>(p1));
			fail("Shouldn't work");
		}
		catch (Exception e) {
		}
		
		treePerson.setLeft(null);
		treePerson.setRight(null);
	}
	
	@Test
	public void testAdd1() {
		BinaryTreeI<String> n3 = new BinaryTree<String>("03");
		BinaryTreeI<String> n1 = new BinaryTree<String>("01", null, n3);
		BinaryTreeI<String> n7 = new BinaryTree<String>("07");
		BinaryTreeI<String> n5 = new BinaryTree<String>("05", n1, n7);
		BinaryTreeI<String> n15 = new BinaryTree<String>("15");
		BinaryTreeI<String> n10 = new BinaryTree<String>("10", n5, n15);
		assertEquals(n10.getValue(), treePerson.getValue().getId());
		TestAllTreeVals(n10, treePerson);

	}

	@SuppressWarnings("unchecked")
	private void TestAllTreeVals(BinaryTreeI<String> binaryTree, BinarySearchTree<Person> searchTree) {
		if (binaryTree == null) {
			assertFalse(searchTree != null);
			return;
		}
		if (searchTree == null) {
			assertFalse(binaryTree != null);
			return;
		}
		assertEquals(binaryTree.getValue(), searchTree.getValue().getId());
		
		BinaryTreeI<Person> left=searchTree.getLeft();
		BinarySearchTree<Person> leftSearch=null;
		if(left!=null)
			leftSearch = (BinarySearchTree<Person>) left;
		
		BinaryTreeI<Person> right=searchTree.getRight();
		BinarySearchTree<Person> rightSearch=null;
		if(right!=null)
			rightSearch = (BinarySearchTree<Person>)right;
		
		TestAllTreeVals(binaryTree.getLeft(), leftSearch);
		TestAllTreeVals(binaryTree.getRight(), rightSearch);
	}

	@Test
	public void testEmptyTree() {
		treePerson.clear();
		assertEquals(1, treePerson.size());
		assertEquals(0, treePerson.height());
	}

	@Test
	public void testAdd2() {
		assertEquals(6, treePerson.size());
		try {
			treePerson.add(p10);
			fail("shouldn't work");
		} catch (Exception e) {
		}
		assertEquals(6, treePerson.size());
		try {
			treePerson.add(p10);
		} catch (Exception e) {
		}

		assertEquals(6, treePerson.size());
		Person p = new Person("02", "02firstName", "02lastName");
		treePerson.add(p);
		assertEquals(7, treePerson.size());
	}

	@Test
	public void testRemoveExistingLeaf() {
		assertTrue(treePerson.remove(new Person("03", "03firstName", "03lastName")));
		assertEquals(5, treePerson.size());
		assertFalse(treePerson.remove(p3));
		assertFalse(treePerson.contains(p3));
	}

	@Test
	public void testRemoveExistingMiddleItemWithEmptyLeftChild() {

		Person p8 = new Person("08", "08firstName", "08lastName");
		treePerson.add(p8);
		assertEquals(treePerson.getLeft().getRight().getRight().getValue(), p8);
		assertTrue(treePerson.remove(p7));
		assertEquals(treePerson.getLeft().getRight().getValue(), p8);
	}

	@Test
	public void testRemoveExistingMiddleItemWithEmptyRightChild() {
		Person p6 = new Person("06", "06firstName", "06lastName");
		treePerson.add(p6);
		assertEquals(treePerson.getLeft().getRight().getLeft().getValue(), p6);
		assertTrue(treePerson.remove(p7));
		assertEquals(treePerson.getLeft().getRight().getValue(), p6);
	}

	@Test
	public void testRemoveExistingMiddleItemWithTwoChildren1() {
		Person p8 = new Person("08", "08firstName", "08lastName");
		Person p6 = new Person("06", "06firstName", "06lastName");
		Person p2 = new Person("02", "02firstName", "02lastName");
		
		treePerson.add(p8);
		treePerson.add(p6);
		treePerson.add(p2);
		isSearchTree(treePerson);
		assertEquals(treePerson.size(),9);
	
		assertEquals(treePerson.getLeft().getRight().getRight().getValue(), p8);
		assertEquals(treePerson.getLeft().getRight().getLeft().getValue(), p6);
		assertEquals(treePerson.getLeft().getRight().getLeft().getValue(), p6);
		
		assertTrue(treePerson.remove(p7));
		assertEquals(treePerson.size(),8);
		isSearchTree(treePerson);
		
		treePerson.add(p7);
		assertEquals(treePerson.size(),9);
		treePerson.remove(p5);
		assertEquals(treePerson.size(),8);
		isSearchTree(treePerson);
		
		
	}

	private <T extends Comparable<T>> boolean isSearchTree(BinarySearchTree<T> searchTree) {
		if (searchTree == null)
			return false;
		if (searchTree.getLeft() != null) {
			int comparingLeft = searchTree.getValue().compareTo(searchTree.getLeft().getValue());
			if (comparingLeft > 0)
				return false;
			if (isSearchTree(searchTree.getLeft()) == false)
				return false;
		}
		if (searchTree.getRight() != null) {
			int comparingRight = searchTree.getValue().compareTo(searchTree.getRight().getValue());
			if (comparingRight < 0)
				return false;
			if (isSearchTree(searchTree.getRight()) == false)
				return false;
		}
		return true;

	}

}