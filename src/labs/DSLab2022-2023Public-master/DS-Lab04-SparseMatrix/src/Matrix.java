public interface Matrix<T> {
	
	int MAX_SIZE = 100;

	
	/**
	 * @param row - number of row
	 * @param col - number of column.
	 * @return the value in (row,col) entry.
	 * @throws IllegalArgumentException if row<1 or col<1 or row>size or col>size.
	 */
	T get(int row, int col);

	/**
	 * @param row - number of row
	 * @param col - number of column.
	 * @param element - element to update
	 * Update the value in (row,col) entry.
	 * @throws IllegalArgumentException if row<1 or col<1 or row>size or col>size.
	 */
	void set(int row, int col, T element);

	/**
	 * Transpose the matrix. 
	 * Bonus of 5 points will be given for implementation in O(1).
	 */
	void transpose();

	/**
	 * @return the a new matrix which is a transpose of the original.
	 */
	Matrix<T> getTranspose();
}