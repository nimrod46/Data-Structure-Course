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
        if (newElement == null) {
            throw new IllegalArgumentException("newElement cannot be null!");
        }
        DNode newNode = new DNode(newElement);

        if (!isEmpty()) {
            newNode.setNext(cursor.next);
            newNode.prev = cursor;
            cursor.next = newNode;
            if (newNode.next == null) {
                last = newNode;
            } else {
                newNode.next.prev = newNode;
            }
        } else {
            head = newNode;
            last = newNode;
        }
        cursor = newNode;
    }

    @Override
    public T remove() {
        DNode node = cursor;
        if (isEmpty()) {
            return null;
        }

        if (hasPrev()) {
            cursor.prev.next = cursor.next;
        } else {
            head = cursor.next;
        }

        if (hasNext()) {
            cursor.next.prev = cursor.prev;
            cursor = cursor.next;
        } else {
            last = cursor.prev;
            cursor = head;
        }
        return node.element;
    }

    @Override
    public T remove(T element) {
        DNode current = head;
        while (current != null && !current.element.equals(element)) {
            current = current.next;
        }
        if (current == null) {
            return null;
        }
        cursor = current;
        return remove();
    }

    @Override
    public void clear() {
        head = null;
        last = null;
        cursor = null;
    }

    @Override
    public void replace(T newElement) {
        if (newElement == null) {
            throw new IllegalArgumentException("newElement cannot be null!");
        }

        if (isEmpty()) {
            throw new IllegalArgumentException("List in empty!");
        }

        DNode prevNode = cursor.getPrev();
        remove();
        if (prevNode != null) {
            cursor = prevNode;
            insert(newElement);
        } else {
            DNode node = new DNode(newElement);
            node.setNext(head);
            head.setPrev(node);
            head = node;
            cursor = node;
        }
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
        return cursor.getElement();
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
