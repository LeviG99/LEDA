package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class StackDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if(isFull())
			throw new StackOverflowException();
		if(element != null) 
			top.insertFirst(element);
		
	}

	@Override
	public T pop() throws StackUnderflowException {
		if(isEmpty())
			throw new StackUnderflowException();
		T retorno = top.toArray()[top.toArray().length-1];
		top.removeLast();
		return retorno;
	}

	@Override
	public T top() {
		T retorno;
		if(isEmpty())
			retorno = null;
		else {
			retorno = top.toArray()[top.toArray().length-1];
		}
		return retorno;
	}

	@Override
	public boolean isEmpty() {
		return top.isEmpty();
	}

	@Override
	public boolean isFull() {
		return size == top.toArray().length;
	}

}
