package labs.lab03;

public class DLinkedList<T> implements List<T> {
    private DNode head;
    private DNode last;
    private DNode cursor;

    public DLinkedList() {
        head = null;
        last = null;
        cursor = null;
    }

    @Override
    public void insert(T newElement) {
        if(newElement == null) {
            throw new IllegalArgumentException("newElement cannot be null!");
        }
        DNode newNode = new DNode(newElement);


        if (!isEmpty()) {
            newNode.next = cursor.next;
            newNode.prev = cursor;
            cursor.next = newNode;
            if(newNode.next == null) {
                last = newNode;
            }
        } else {
            head = newNode;
            last = newNode;
        }
        cursor = newNode;
    }

    @Override
    public T remove() {
//        if (isEmpty()) {
//            return null;
//        }
//        DNode tmp = cursor;
//        cursor.prev.next = cursor.next;
//        cursor = tmp.next;
//        if(tmp.next == null) {
//            last = tmp.prev;
//            cursor = head;
//        }
//        return tmp.element;

        return removeNode(cursor);
    }

    @Override
    public T remove(T element) {
        DNode current = head;
        while (current != null && !current.element.equals(element)) {
            current = current.next;
        }
        return removeNode(current);
    }

    private T removeNode(DNode node) {
        if (isEmpty() || node == null) {
            return null;
        }
        cursor = node.next;

        if(node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }

        if(node.next != null) {
            node.next.prev = node.prev;
        } else {
            cursor = head;
            last = node.prev;
        }
        return node.element;
    }

    @Override
    public void clear() {
        head = null;
        last = null;
        cursor = null;
    }

    @Override
    public void replace(T newElement) {
        if(newElement == null) {
            throw new IllegalArgumentException("newElement cannot be null!");
        }

        if(isEmpty()) {
            throw new IllegalArgumentException("newElement cannot be null!");
        }
        cursor.element = newElement;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public boolean goToBeginning() {
        if(isEmpty()) {
            return false;
        }
        cursor = head;
        return true;
    }

    @Override
    public boolean goToEnd() {
        if(isEmpty()) {
            return false;
        }
        cursor = last;
        return true;
    }

    @Override
    public T getNext() {
        if(isEmpty() || cursor.next == null) {
            return null;
        }
        cursor = cursor.next;
        return cursor.element;
    }

    @Override
    public T getPrev() {
        if(isEmpty() || cursor.prev == null) {
            return null;
        }
        cursor = cursor.prev;
        return cursor.element;
    }

    @Override
    public T getCursor() {
        if(isEmpty()) {
            return null;
        }
        return cursor.element;
    }

    @Override
    public boolean hasNext() {
        if(isEmpty()) {
            return false;
        }
        return cursor.next != null;
    }

    @Override
    public boolean hasPrev() {
        if(isEmpty()) {
            return false;
        }
        return cursor.prev != null;
    }

    private class DNode {
        private T element;
        private DNode next;
        private DNode prev;

        public DNode(T element) {
            this.element = element;
        }

        public T getElement() {
            return element;
        }

        public void setNext(DNode next) {
            this.next = next;
        }

        public DNode getNext() {
            return next;
        }

        public void setPrev(DNode prev) {
            this.prev = prev;
        }

        public DNode getPrev() {
            return prev;
        }
    }
}
