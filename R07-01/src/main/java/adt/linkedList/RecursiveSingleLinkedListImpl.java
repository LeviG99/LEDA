package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;
	public RecursiveSingleLinkedListImpl() {
		this.data = null;
		this.next = null;
	}

	public RecursiveSingleLinkedListImpl(T data,
			RecursiveSingleLinkedListImpl<T> next) {
		this.data = data;
		this.next = next;
	}

	@Override
	public boolean isEmpty() {
		if(data == null) {
			return true;
		}return false;
	}

	@Override
	public int size() {
		if(getData() == null) {
			return 0;
		}
		if(getNext().getData() == null) {
			return 1;
		}else {
			return getNext().size() + 1;
		}
	}

	@Override
	public T search(T element) {
		if(getData() == null) {
			return null;
		}if(getData() == element) {
			return element;
		}if(getNext().getData() == null) {
			return null;
		}
		return getNext().search(element);
	}

	@Override
	public void insert(T element) {
		if(getData() == null) {
			setData(element);
			setNext(new RecursiveSingleLinkedListImpl<T>());
			return;
		}else if(getNext().getData() == null) {
			setNext(new RecursiveSingleLinkedListImpl<T>());
			getNext().setData(element);
			getNext().setNext(new RecursiveSingleLinkedListImpl<T>());
			return;
			}else {
			getNext().insert(element);
		}
	}

	@Override
	public void remove(T element) {
		if(getData() == null) {
			return;
		}
		if(getData() == element) {
			setData(null);
			return;
		}
		else if(next.getData() == null) {
			return;
		}if(next.getData() == element) {
			setNext(next.getNext());
			return;
		}else {
			getNext().remove(element);
		}
	}

	@Override
	public T[] toArray() {
		T[] array = (T[]) new Comparable[size()];
		if(getData() == null) {
			return array;
		}
		return toArray(array,0,this);
	}

	private T[] toArray(T[] array, int i,RecursiveSingleLinkedListImpl node) {
		array[i] = (T) node.getData();
		if(i == size()-1) {
			return array;
		}
		return toArray(array,i+1,node.getNext());
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
