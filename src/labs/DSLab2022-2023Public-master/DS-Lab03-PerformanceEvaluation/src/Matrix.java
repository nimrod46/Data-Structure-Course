public interface Matrix {
    int MAX_SIZE = 100;
    double get(int i, int j);
    void set(int i, int j, double x);
    void transpose();
    Matrix getTranspose();
}