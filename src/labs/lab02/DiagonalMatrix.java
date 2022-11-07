package labs.lab02;

public class DiagonalMatrix implements Matrix {

    private final int n;
    private final int size;
    private final double[] data;
    private boolean isTranspose;

    public DiagonalMatrix() {
        this(MAX_SIZE);
    }

    public DiagonalMatrix(int size) {
        n = size;
        this.size = 2 * size - 1;
        data = new double[this.size];
        isTranspose = false;
    }

    @Override
    public double get(int i, int j) {
        if (i <= 0 || j <= 0 || i > n || j > n) {
            throw new IllegalArgumentException("Illegal arguments!");
        }
        return data[getDiagonalIndex(i, j)];
    }

    @Override
    public void set(int i, int j, double x) {
        if (i <= 0 || j <= 0 || i > n || j > n) {
            throw new IllegalArgumentException("Illegal arguments!");
        }
        data[getDiagonalIndex(i, j)] = x;
    }

    @Override
    public void transpose() {
        isTranspose = !isTranspose;
    }

    @Override
    public Matrix getTranspose() {
        Matrix m = new DiagonalMatrix(n);
        for (int i = 1; i <= n ; i++) { //Copy first row in reverse order
            m.set(1, i, this.get(n, n - i + 1));
        }

        for (int i = 1; i <= n ; i++) { //Copy last row in reverse order
            m.set(n, i, this.get(1, n - i + 1));
        }
        return m;
    }

    private int getDiagonalIndex(int i, int j) {
        int index = isTranspose ? j - i : i - j;
        return index >= 0 ? index : index + size;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                s.append(get(i, j));
                if(j != n) {
                    s.append("\t");
                }
            }
            s.append("\n");
        }
        return s.toString();
    }
}
