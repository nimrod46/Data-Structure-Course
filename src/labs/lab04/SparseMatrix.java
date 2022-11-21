package labs.lab04;

import labs.lab03.DLinkedList;

public class SparseMatrix<T> implements Matrix<T> {

    DLinkedList<SparseMatrixEntry> data;
    private final int size;
    private final T defaultValue;
    private boolean isTranspose;

    public SparseMatrix(T defaultValue) {
        this(MAX_SIZE, defaultValue);
    }

    public SparseMatrix(int size, T defaultValue) {
        this.size = size;
        this.defaultValue = defaultValue;
        data = new DLinkedList<>();
        isTranspose = false;
    }


    @Override
    public T get(int row, int col) {
        validateInput(row, col);

        SparseMatrixEntry entry = getElementAt(row, col);
        if (entry != null) {
            return entry.getValue();
        }
        return defaultValue;
    }

    @Override
    public void set(int row, int col, T element) {
        validateInput(row, col);

        SparseMatrixEntry entry = getElementAt(row, col);
        if (entry != null) {
            entry.setValue(element);
            return;
        }
        SparseMatrixEntry entryToAdd = new SparseMatrixEntry(row, col, element);
        data.insert(entryToAdd);
    }

    @Override
    public void transpose() {
        isTranspose = !isTranspose;
    }

    private SparseMatrixEntry getElementAt(int row, int col) {
        data.goToBeginning();
        SparseMatrixEntry entry = data.getCursor();
        while (entry != null) {
            if (matchRowAndColumn(row, col, entry)) {
                return entry;
            }
            entry = data.getNext();
        }
        return null;
    }

    private boolean matchRowAndColumn(int row, int col, SparseMatrixEntry entry) {
        return isTranspose ? entry.row == col && entry.col == row : entry.row == row && entry.col == col;
    }

    private void validateInput(int row, int col) {
        if (row < 1 || col < 1 || row > size || col > size) {
            throw new IllegalArgumentException("Row or column does not match matrix size!");
        }
    }

    private class SparseMatrixEntry {
        private T value;
        private int row;
        private int col;

        public SparseMatrixEntry(int row, int col, T value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getCol() {
            return col;
        }

        public void setCol(int col) {
            this.col = col;
        }
        public T getValue() {
            return value;
        }

        public void setValue(T newVal) {
            this.value = newVal;
        }
    }
}
