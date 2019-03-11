package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.RecursiveDoubleLinkedListImpl;

public class StackRecursiveDoubleLinkedListImpl<T> implements Stack<T> {

   protected DoubleLinkedList<T> top;
   protected int size;

   public StackRecursiveDoubleLinkedListImpl(int size) {
      this.size = size;
      this.top = new RecursiveDoubleLinkedListImpl<T>();
   }

   @Override
   public void push(T element) throws StackOverflowException {
      if (isFull()) {
         throw new StackOverflowException();
      }
      this.top.insertFirst(element);
   }

   @Override
   public T pop() throws StackUnderflowException {
      if (isEmpty()) {
         throw new StackUnderflowException();
      }
      T aux = ((RecursiveDoubleLinkedListImpl<T>) top).getData();
      this.top.removeFirst();
      return aux;
   }

   @Override
   public T top() {
      if (isEmpty()) {
         return null;
      }
      return ((RecursiveDoubleLinkedListImpl<T>) top).getData();
   }

   @Override
   public boolean isEmpty() {
      return ((RecursiveDoubleLinkedListImpl<T>) top).getData() == null;
   }

   @Override
   public boolean isFull() {
      return size == top.size();
   }

}
