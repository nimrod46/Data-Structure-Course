public interface IGraph<V extends Comparable<V>, E> {

	/**
	 * Add a new vertex if none exists.
	 */
	public void add(V v);

	/**
	 * @param u
	 * @param v
	 * @return the label of the edge (u,v)
	 */
	public E getEdge(V u, V v);

	/**
	 * Add a new edge if none exists between the two vertices Otherwise retain the
	 * existing edge and replace the item with the given one. If the vertices u or v
	 * do not exist, add them to the graph.
	 */
	public E putEdge(V u, V v, E edgeLabel);

	/**
	 * @return If the graph contains the vertex v.
	 */
	public boolean containsVertex(V v);

	/**
	 * Remove the vertex and its edges from the graph, and return its incidents. If
	 * the vertex dosn't exit return null.
	 */
	public void removeVertex(V v);

	/**
	 * @return The label of the edge between the two vertices. Null if the edge does
	 *         not exist Throws an exception if one of the vertices does not exist.
	 */
	public E removeEdge(V u, V v);

	/**
	 * @return The weight of edge (u,v), if it exists. Otherwise return 0.
	 */
	public double getWeight(V u, V v);

	/**
	 * @return The concatenation of the vertices separated by commas.
	 */
	public String toString();

	/**
	 * @returns The concatenation of the vertices separated by newlines Every vertex
	 *          is printed with a comma separated list of its incident edges. The
	 *          list is separated from the vertex with a colon.
	 */
	public String toStringExtended();

	/**
	 * @return If the the edge (u,v) exists.
	 */
	public boolean areAdjacent(V u, V v);

}