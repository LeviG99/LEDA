package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {
	
	protected DoubleLinkedListNode<T> last;
	public DoubleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
		this.last = new DoubleLinkedListNode<T>();
	}
	public void insert(T element) {
		DoubleLinkedListNode<T> aux = new DoubleLinkedListNode<T>(element,new DoubleLinkedListNode<T>(),last);
		last.setNext(aux);
		last = aux;
	}
	@Override
	public void insertFirst(T element) {
		DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<>();
		newHead.setData(element);
		newHead.setNext(head);
		head = newHead;
	}

	@Override
	public void removeFirst() {
		if(isEmpty())
			return;
		else
			head = head.getNext();
	}

	@Override
	public void removeLast() {
		if(isEmpty()) 
			return;
		else {
			SingleLinkedListNode<T> node = head;
			while(!node.next.isNIL()) {
				node = node.next;
			}node.setData(null);
		}
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}
