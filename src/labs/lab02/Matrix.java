package labs.lab02;

public interface Matrix {


    int MAX_SIZE = 100;

    /***
     * Precondition: 1 ≤ i,j ≤ n
     * Post-condition: returns the value of the element at position (i,j).
     */
    double get(int i, int j);


    /***
     * Precondition: 1 ≤ i,j ≤ n.
     * Post-condition: update the value of the element at position (i,j) to x.
     */
    void set(int i, int j, double x);


    /***
     * Post-condition: replace the current matrix wıth its transpose.
     */
    void transpose();

    /***
     * Post-condition: returns a new matrix which equals to the transpose of the current matrix.
     */
    Matrix getTranspose();


}
