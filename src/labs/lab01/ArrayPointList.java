package labs.lab01;

import java.awt.*;

public class ArrayPointList implements PointList {
    private final Point[] points;
    private final int maxSize;
    private int cursor;
    private int lastElementIndex;

    public ArrayPointList() {
        this(MAX_SIZE);
    }

    public ArrayPointList(int maxSize) {
        this.maxSize = maxSize;
        points = new Point[maxSize];
        cursor = -1;
        lastElementIndex = -1;
    }

    @Override
    public void append(Point newPoint) {
        if(isFull()) {
            throw new RuntimeException("Array point is full!");
        }
        points[++lastElementIndex] = newPoint;
        cursor = lastElementIndex;
    }

    @Override
    public void clear() {
        cursor = -1;
        lastElementIndex = -1;
    }

    @Override
    public boolean isEmpty() {
        return cursor == -1;
    }

    @Override
    public boolean isFull() {
        return lastElementIndex == maxSize - 1;
    }

    @Override
    public boolean goToBeginning() {
        if(cursor == -1) {
            return false;
        }
        cursor = 0;
        return true;
    }

    @Override
    public boolean goToEnd() {
        if(isEmpty()) {
            return false;
        }
        cursor = lastElementIndex;
        return true;
    }

    @Override
    public boolean goToNext() {
        if(cursor == lastElementIndex) {
            return false;
        }
        cursor++;
        return true;
    }

    @Override
    public boolean goToPrior() {
        if(cursor == 0) {
            return false;
        }
        cursor--;
        return true;
    }

    @Override
    public Point getCursor() {
        if(isEmpty()) {
            return null;
        }
        return (Point) points[cursor].clone();
    }
}
