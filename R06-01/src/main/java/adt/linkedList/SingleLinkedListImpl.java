package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return this.head.isNIL();
	}

	@Override
	public int size() {
		int cont = 0;
		if(isEmpty()) {
			cont = 0;
		}
		else{
		SingleLinkedListNode<T> aux = head;
		while(!aux.isNIL()) {
			cont++;
			aux = aux.getNext();
		}
	}
		return cont;	
	}

	@Override
	public T search(T element) {
		T retorno = null;
		SingleLinkedListNode<T> aux = head;
		while(!aux.isNIL()) {
			if(aux.getData().equals(element)) {
				retorno = element;
				break;
			}
			aux = aux.getNext();
		}return retorno;
	}

	@Override
	public void insert(T element) {
		SingleLinkedListNode<T> aux = head;
		if(size()==0) {
			head = new SingleLinkedListNode<T>(element,new SingleLinkedListNode<T>());
		}else {
		while(!aux.next.isNIL()) {
			aux = aux.getNext();
		}
		aux.setNext(new SingleLinkedListNode<T>(element,new SingleLinkedListNode<T>() ));
		}
	}
	@Override
	public void remove(T element) {
		SingleLinkedListNode<T> aux = head;
		while(!aux.next.isNIL()) {
			if(aux.next.getData().equals(element)) {
				aux.setNext(aux.getNext().getNext());
				return;
			}
		}
	}

	@Override
	public T[] toArray() {
		SingleLinkedListNode<T> aux = head;
		T[] array = (T[]) new Comparable[size()];
		for(int i = 0;i<size();i++) {
			array[i] = aux.getData();
			aux = aux.getNext();
		}
		return array;
		
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
