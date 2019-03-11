package adt.linkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import adt.stack.StackOverflowException;
import adt.stack.StackRecursiveDoubleLinkedListImpl;
import adt.stack.StackUnderflowException;

public class StudentDoubleLinkedListTest extends StudentLinkedListTest {

	private DoubleLinkedList<Integer> lista3;
	private StackRecursiveDoubleLinkedListImpl<Integer> stack;

	@Before
	public void setUp() throws Exception {

		getImplementations();

		// Lista com 3 elementos.
		lista1.insert(3);
		lista1.insert(2);
		lista1.insert(1);
		stack.push(3);
		stack.push(2);
		stack.push(1);
		// Lista com 1 elemento.
		lista3.insert(1);
	}

	private void getImplementations() {
		// TODO O aluno deve ajustar aqui para instanciar sua implementação
		lista1 = new RecursiveDoubleLinkedListImpl<Integer>();
		lista2 = new RecursiveDoubleLinkedListImpl<Integer>();
		lista3 = new RecursiveDoubleLinkedListImpl<Integer>();
		stack = new StackRecursiveDoubleLinkedListImpl<Integer>(3);
	}

	// Métodos de DoubleLinkedList

	@Test
	public void testInsertFirst() throws StackOverflowException, StackUnderflowException {
		((DoubleLinkedList<Integer>) lista1).insertFirst(4);
		Assert.assertArrayEquals(new Integer[] { 4, 3, 2, 1 }, lista1.toArray());
		((DoubleLinkedList<Integer>) lista1).removeFirst();
		Assert.assertArrayEquals(new Integer[] {3, 2, 1 }, lista1.toArray());
		Assert.assertTrue(stack.isFull());
		Assert.assertFalse(stack.isEmpty());
		Assert.assertTrue(stack.pop() == 1);
		Assert.assertTrue(stack.top() == 2);
		Assert.assertTrue(stack.pop() == 2);
		Assert.assertTrue(stack.top() == 3);
		Assert.assertTrue(stack.pop() == 3);
		Assert.assertTrue(stack.isEmpty());
	}
	@Test
	public void testInsertLast() {
		((DoubleLinkedList<Integer>) lista1).insert(4);
		Assert.assertArrayEquals(new Integer[] {3, 2, 1 ,4 }, lista1.toArray());
		((DoubleLinkedList<Integer>) lista1).removeFirst();
		Assert.assertArrayEquals(new Integer[] {2, 1, 4 }, lista1.toArray());
	}

	@Test
	public void testRemoveFirst() {
		((DoubleLinkedList<Integer>) lista1).removeFirst();
		Assert.assertArrayEquals(new Integer[] { 2, 1 }, lista1.toArray());
		((DoubleLinkedList<Integer>) lista1).removeFirst();
		Assert.assertArrayEquals(new Integer[] { 1 }, lista1.toArray());
		((DoubleLinkedList<Integer>) lista1).removeFirst();
		Assert.assertArrayEquals(new Integer[] {}, lista1.toArray());
		((DoubleLinkedList<Integer>) lista1).removeFirst();
	}

	@Test
	public void testRemoveLast() {
		((DoubleLinkedList<Integer>) lista1).removeLast();
		Assert.assertArrayEquals(new Integer[] { 3, 2 }, lista1.toArray());
		((DoubleLinkedList<Integer>) lista1).removeLast();
		Assert.assertArrayEquals(new Integer[] {3}, lista1.toArray());
		((DoubleLinkedList<Integer>) lista1).removeLast();
		Assert.assertArrayEquals(new Integer[] {}, lista1.toArray());
		((DoubleLinkedList<Integer>) lista1).removeLast();
	}
}