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
        return linkedList.remove();
    }

    @Override
    public T top() {
        return linkedList.getCursor();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }
}
