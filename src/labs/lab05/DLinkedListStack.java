package labs.lab05;

import labs.lab03.DLinkedList;

public class DLinkedListStack<T> implements Stack<T>{

    private final DLinkedList<T> linkedList;

    public DLinkedListStack() {
        this.linkedList = new DLinkedList<>();
    }

    @Override
    public void push(T t) {
        linkedList.insert(t);
    }

    @Override
    public T pop() {
        T removed = linkedList.remove();
        linkedList.goToEnd();
        return removed;
    }

    @Override
    public T top() {
        return linkedList.getCursor();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[");
        T t = linkedList.getCursor();
        while (t != null) {
            str.append(", ");
            str.append(t);
            t = linkedList.getPrev();
        }
        if(str.length() != 1) {
            str.delete(1, 3);
        }
        str.append("]");
        linkedList.goToEnd();
        return str.toString();
    }
}
