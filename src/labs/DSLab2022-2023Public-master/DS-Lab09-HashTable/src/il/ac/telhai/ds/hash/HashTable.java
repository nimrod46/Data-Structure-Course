public class HashTable<V> {
	public static final int DEF_MAX_HASH_SIZE = 10;

	/**
	 * constructor
	 * constructs a hash-table of max-size "DEF_MAX_HASH_SIZE".
	 */
	public HashTable() {
		// TODO add your implementation.
	}
	
	/**
	 * constructs a hash-table of size 'hashSize'.
	 * @param hashSize, the size of the constructed hash-table.
	 */
	public HashTable (int hashSize) {
		// TODO add your implementation.
	}
	
	/**
	 * @param val
	 * @return true if the hash-table contains val, otherwise return false
	 */
	public boolean contains(V val) {
		// TODO add your implementation.
	}

	/**
	 * Add val to the hash-table.
	 * 
	 * @param val
	 * @return If the val has already existed in the the hash-table, it will not be
	 *         added again. Return true if the val was added successfully. Otherwise
	 *         return false.
	 */
	public boolean add(V val) {
		// TODO add your implementation.
	}

	/**
	 * Remove val from the hash-table.
	 * 
	 * @param val
	 * @return Return true if the val was removed successfully. Otherwise return
	 *         false.
	 */
	public boolean remove(V val) {
		// TODO add your implementation.
	}

	/**
	 * clear all the data in the hash-table
	 */
	public void clear() {
		// TODO add your implementation.
	}

	/**
	 * @return true if the hash-table is empty, otherwise return false.
	 */
	public boolean isEmpty() {
		// TODO add your implementation.
	}
}