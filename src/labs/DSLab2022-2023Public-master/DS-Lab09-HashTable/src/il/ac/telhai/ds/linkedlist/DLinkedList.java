public class DLinkedList<T> implements List<T> {
	private DNode head;
	private DNode tail;
	private DNode current;

	public DLinkedList() {
		super();
		clear();
	}

	@Override
	public T remove(T element) {
		DNode cur = head;
		while (cur != null) {
			if (cur.getElement().equals(element))
				break;
			cur = cur.getNext();
		}
		if (cur == null)
			return null;
		current = cur;
		return remove();
	}

	@Override
	public T remove() {
		if (isEmpty())
			return null;

		T returnVal = current.getElement();
		DNode next = current.getNext();
		DNode prev = current.getPrev();

		current = next;
		if (prev == null) {
			head = next;
		}
		if (next == null) {
			tail = prev;
			current = head;
		}
		if (prev != null)
			prev.setNext(next);
		if (next != null)
			next.setPrev(prev);

		return returnVal;
	}

	@Override
	public void clear() {
		head = null;
		tail = null;
		current = null;
	}

	@Override
	public boolean isEmpty() {
		return head == null;
	}

	@Override
	public boolean goToBeginning() {
		return goTo(head);
	}

	@Override
	public boolean goToEnd() {
		return goTo(tail);
	}

	private boolean goTo(DNode goToNode) {
		boolean res = !isEmpty();
		if (res) {
			current = goToNode;
		}
		return res;
	}

	@Override
	public boolean hasNext() {
		return (current != tail);
	}

	@Override
	public boolean hasPrev() {
		return (current != head);
	}

	@Override
	public T getCursor() {
		if (current != null)
			return current.getElement();
		return null;
	}

	@Override
	public T getNext() {
		if ((current != null) && hasNext()) {
			current = current.getNext();
			return getCursor();
		}
		return null;
	}

	@Override
	public T getPrev() {
		if ((current != null) && hasPrev()) {
			current = current.getPrev();
			return getCursor();
		}
		return null;
	}

	@Override
	public void insert(T newElement) {
		if (newElement == null)
			throw new IllegalArgumentException("New element is null");
		DNode newNode = new DNode(newElement);
		if (isEmpty()) {
			head = newNode;
		} else {
			newNode.setPrev(tail);
			tail.setNext(newNode);
		}
		tail = newNode;
		current = newNode;
	}

	@Override
	public void replace(T newElement) {
		if ((newElement == null)) {
			throw new IllegalArgumentException("New element is null");
		}
		if ((isEmpty())) {
			throw new IllegalStateException("List is empty");
		}

		DNode newNode = new DNode(newElement);
		DNode next = current.getNext();
		DNode prev = current.getPrev();
		if ((next!=null))
		{
			prev = current.getPrev();
			next.setPrev(newNode);
		}
		
		newNode.setNext(next);
		if (prev != null) {
			newNode.setPrev(prev);
			prev.setNext(newNode);
		}
		
		current = newNode;
	}

	private class DNode {
		private T element; // element in the list
		private DNode next; // reference to the next element
		private DNode prev; // reference to the previous element

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
