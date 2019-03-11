package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
		RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {

	}

	public RecursiveDoubleLinkedListImpl(T data,
			RecursiveSingleLinkedListImpl<T> next,
			RecursiveDoubleLinkedListImpl<T> previous) {
		super(data, next);
		this.previous = previous;
	}
	
	public void insert(T element) {
		if(getData() == null) {
			setData(element);
			setNext(new RecursiveDoubleLinkedListImpl<T>());
			setPrevious(new RecursiveDoubleLinkedListImpl<T>());
			return;
		}else if(getNext().getData() == null) {
			setNext(new RecursiveDoubleLinkedListImpl<T>());
			getNext().setData(element);
			((RecursiveDoubleLinkedListImpl<T>) getNext()).setPrevious(this);
			((RecursiveDoubleLinkedListImpl<T>) getNext()).setNext(new RecursiveDoubleLinkedListImpl<T>());
			return;
			}else {
			getNext().insert(element);
		}
	}
	@Override
	public void insertFirst(T element) {
		if(getData() == null) {
			this.data = element;
			setNext(new RecursiveDoubleLinkedListImpl<T>());
			setPrevious(new RecursiveDoubleLinkedListImpl<T>());
		}else {
			T aux = getData();
			this.setData(element);
			this.setNext(new RecursiveDoubleLinkedListImpl<T>(aux,this.getNext(),this));
		}
	}

	@Override
	public void removeFirst() {
		if(getNext().getData() == null) {
			this.setData(null);
		}else {
		this.setData(getNext().getData());
		this.setNext(getNext().getNext());
		}
	}

	@Override
	public void removeLast() {
		if(getNext().getData() != null) {
			((DoubleLinkedList<T>) next).removeLast();
		}else {
			if(getPrevious().getData() == null) {
				this.data = null;
				return;
				}
			getPrevious().setNext(new RecursiveDoubleLinkedListImpl<T>());
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
