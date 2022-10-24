import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class TestBinarySearchTreeInteger {

	private BinarySearchTree<Integer> searchTree;
	BinarySearchTree<Integer> small;
	BinarySearchTree<Integer> big;

	@Before
	public void setUp() throws Exception {
		small = new BinarySearchTree<Integer>(10);
		big = new BinarySearchTree<Integer>(20);
		searchTree = new BinarySearchTree<Integer>(15, small, big);
	}

	@Test
	public void testCopyInConstructor() {
		small.setRight(new BinarySearchTree<Integer>(30));
		assertEquals(small.getRight().getValue(), Integer.valueOf(30));
		BinarySearchTree<Integer> left2 = searchTree.getLeft();
		assertNull(left2.getRight());

		big.setLeft(new BinarySearchTree<Integer>(0));
		assertEquals(big.getLeft().getValue(), Integer.valueOf(0));
		BinarySearchTree<Integer> right2 = searchTree.getRight();
		assertNull(right2.getLeft());
	}

	@Test
	public void testIllegalChangeThrouGetterAndSetters() {
		BinarySearchTree<Integer> left = searchTree.getLeft();
		left.setRight(new BinarySearchTree<Integer>(30));
		assertEquals(left.getRight().getValue(), Integer.valueOf(30));
		BinarySearchTree<Integer> left2 = searchTree.getLeft();
		assertNull(left2.getRight());

		BinarySearchTree<Integer> right = searchTree.getRight();
		right.setLeft(new BinarySearchTree<Integer>(0));
		assertEquals(right.getLeft().getValue(), Integer.valueOf(0));
		BinarySearchTree<Integer> right2 = searchTree.getRight();
		assertNull(right2.getLeft());
	}

	@Test
	public void testIllegalSet() {
		try {
			searchTree.setLeft(big);
			fail("Shouldn't work");
		} catch (Exception e) {
		}
		
		try {
			searchTree.setLeft(new BinaryTree<Integer>(3));
			fail("Shouldn't work");
		} catch (Exception e) {
		}
	}

	@Test
	public void testConstructor() {
		try {
			searchTree = new BinarySearchTree<Integer>(0, small, big);
			fail("Shouldn't work");
		} catch (Exception e) {
		}
		try {
			searchTree = new BinarySearchTree<Integer>(30, small, big);
			fail("Shouldn't work");
		} catch (Exception e) {
		}
		try {
			searchTree = new BinarySearchTree<Integer>(15, big, small);
			fail("Shouldn't work");
		} catch (Exception e) {
		}
		searchTree = new BinarySearchTree<Integer>(15, small, big);
	}

	
	@Test
	public void testTree() {
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>(5);
		tree.add(4);
		tree.add(2);
		tree.add(3);
		tree.add(6);
		tree.add(1);

		assertEquals(6, tree.size());
		assertTrue(tree.contains(3));
		tree.remove(3);
		assertFalse(tree.contains(3));
		
		
		assertEquals(tree.findLE(3), Integer.valueOf(2));
		assertEquals(tree.findGE(3), Integer.valueOf(4));
		
		tree.remove(4);
		tree.remove(5);
		tree.add(3);
		assertEquals(tree.findLE(4), Integer.valueOf(3));
		assertEquals(tree.findGE(4), Integer.valueOf(6));
		
		
		tree.add(4);
		assertEquals(tree.findLE(4), Integer.valueOf(4));
		assertEquals(tree.findGE(4), Integer.valueOf(4));
		assertEquals(tree.findLE(5), Integer.valueOf(4));
		assertEquals(tree.findGE(5), Integer.valueOf(6));
		
		assertEquals(tree.findGE(0), Integer.valueOf(1));
		
		assertNull(tree.findLE(0));
		assertNull(tree.findGE(7));
	}
}