package labs.lab10;

public class MinHeap<T extends Comparable<T>> {


    private final T[] data;
    private int lastLeafIndex;

    @SuppressWarnings({"unchecked", "rawtypes"})
    public MinHeap(int length) {
        data = (T[]) new Comparable[length + 1];
        lastLeafIndex = 0;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public MinHeap(T[] arr) {
        data = (T[]) new Comparable[arr.length + 1];
        for (int i = 1; i < arr.length + 1; i++) {
            data[i] = arr[i - 1];
        }
        lastLeafIndex = arr.length;
        for (int i = arr.length+1; i > 0; i--) {
            shiftDown(i);
        }

    }

    private void shiftDown(int index) {
        while (index * 2 <= lastLeafIndex) {
            int chosenIndex;
            if (index * 2 + 1 > lastLeafIndex || data[index * 2].compareTo(data[index * 2 + 1]) < 0) {
                if (data[index * 2].compareTo(data[index]) < 0) {
                    chosenIndex = index * 2;
                    swapByIndexes(index, chosenIndex);
                } else {
                    return;
                }
            } else {
                if (data[index * 2 + 1].compareTo(data[index]) < 0) {
                    chosenIndex = index * 2 + 1;
                    swapByIndexes(index, chosenIndex);
                } else {
                    return;
                }
            }
            index = chosenIndex;
        }
    }

    private void shiftUp(int index) {
        while (index > 1) {
            if (data[index / 2].compareTo(data[index]) > 0) {
                swapByIndexes(index, index / 2);
            } else {
                return;
            }
            index = index / 2;
        }
    }

    public boolean isFull() {
        return lastLeafIndex == data.length - 1;
    }

    public boolean isEmpty() {
        return lastLeafIndex == 0;
    }

    public void insert(T element) {
        if (isFull()) {
            throw new IllegalStateException("Cannot insert, heap is full");
        }
        lastLeafIndex++;
        data[lastLeafIndex] = element;
        shiftUp(lastLeafIndex);

    }

    public T getMin() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty!");
        }
        return (T) data[1];
    }

    public T deleteMin() {
        T temp = data[1];
        data[1] = data[lastLeafIndex];
        data[lastLeafIndex] = null;
        lastLeafIndex--;
        shiftDown(1);
        return temp;
    }

    /**
     * return a string represents the heap. The order of the elements are according
     * to The string starts with "[", ends with "]", and the values are seperated
     * with a comma
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 1; i < lastLeafIndex + 1; i++) {
            stringBuilder.append(data[i]).append(",");
        }

        if(!isEmpty()) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        stringBuilder.append(']');

        return stringBuilder.toString();
    }

    private void swapByIndexes(int index1, int index2) {
        T temp = data[index2];
        data[index2] = data[index1];
        data[index1] = temp;
    }
}
