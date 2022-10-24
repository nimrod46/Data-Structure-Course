public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

	public BinarySearchTree(T value, BinarySearchTree<T> left, BinarySearchTree<T> right) {
		// TODO add your implementation
	}

	public BinarySearchTree(T value) {
		// TODO add your implementation
	};

	/**
	 * Adds the object value to the tree as a leaf according to the parameter. If
	 * the object was in the tree before, then a RuntimeException is thrown.
	 * 
	 * @param val
	 */
	public void add(T val) {
		// TODO add your implementation
	}

	/**
	 * Looks for an object which is equal to the parameter.
	 * 
	 * @param val: the object to be looked for in the tree
	 * @return true if the tree contains this object. Otherwise, return false.
	 */
	public boolean contains(T value) {
		// TODO add your implementation
	}

	/**
	 * Looks for the minimal object in the tree, which is greater than or equal to
	 * the parameter.
	 * 
	 * @param val: the object to be looked for in the tree
	 * @return a reference to the found object.
	 */
	public T findGE(T val) {
		// TODO add your implementation
	}

	/**
	 * Looks for the maximal object in the tree, which is smaller than or equal to
	 * the parameter.
	 * 
	 * @param val: the object to be looked for in the tree
	 * @return a reference to the found object.
	 */
	public T findLE(T val) {
		// TODO add your implementation
	}

	/**
	 * Removes the object in the tree which is equal to the parameter. If the object
	 * was found but the tree contains only one element, it want be removed and a
	 * run-time exception will be thrown. Nothing is done if not found
	 * 
	 * @param val: the object to be looked for in the tree
	 * @return True, if the element was removed. Otherwise false.
	 */
	public boolean remove(T val) {
		// TODO add your implementation
	}

	@Override
	public BinarySearchTree<T> getLeft() {
		// TODO add your implementation
	}

	@Override
	public BinarySearchTree<T> getRight() {
		// TODO add your implementation
	}

}
