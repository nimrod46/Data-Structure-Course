package labs.lab01;

import labs.DSLab20222023Publicmaster.DSLab01ArrayPointList.PointList;

import java.awt.*;

public class ArrayPointList implements PointList {
    private final Point[] points;
    private final int size;
    int cursor;
    private int lastElementIndex;

    public ArrayPointList() {
        this(MAX_SIZE);
    }

    public ArrayPointList(int size) {
        this.size = size;
        points = new Point[size];
        cursor = -1;
        lastElementIndex = -1;
    }

    @Override
    public void append(Point newPoint) {
        points[++cursor] = newPoint;
        lastElementIndex++;
    }

    @Override
    public void clear() {
        for (int i = 0; i < cursor; i++) {
            points[cursor] = null;
        }
        cursor = -1;
        lastElementIndex = -1;
    }

    @Override
    public boolean isEmpty() {
        return cursor == -1;
    }

    @Override
    public boolean isFull() {
        return cursor == size - 1;
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
        if(lastElementIndex == -1) {
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
