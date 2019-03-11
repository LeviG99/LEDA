package adt.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import util.Util;

/**
 * O comportamento de qualquer heap é definido pelo heapify. Neste caso o
 * heapify dessa heap deve comparar os elementos e colocar o maior sempre no
 * topo. Ou seja, admitindo um comparador normal (responde corretamente 3 > 2),
 * essa heap deixa os elementos maiores no topo. Essa comparação não é feita 
 * diretamente com os elementos armazenados, mas sim usando um comparator. 
 * Dessa forma, dependendo do comparator, a heap pode funcionar como uma max-heap 
 * ou min-heap.
 */
public class HeapImpl<T extends Comparable<T>> implements Heap<T> {

   protected T[] heap;
   protected int index = -1;
   /**
    * O comparador é utilizado para fazer as comparações da heap. O ideal é
    * mudar apenas o comparator e mandar reordenar a heap usando esse
    * comparator. Assim os metodos da heap não precisam saber se vai funcionar
    * como max-heap ou min-heap.
    */
   protected Comparator<T> comparator;

   private static final int INITIAL_SIZE = 20;
   private static final int INCREASING_FACTOR = 10;

   /**
    * Construtor da classe. Note que de inicio a heap funciona como uma
    * min-heap.
    */
   @SuppressWarnings("unchecked")
   public HeapImpl(Comparator<T> comparator) {
      this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
      this.comparator = comparator;
   }

   // /////////////////// METODOS IMPLEMENTADOS
   private int parent(int i) {
      return (i - 1) / 2;
   }

   /**
    * Deve retornar o indice que representa o filho a esquerda do elemento
    * indexado pela posicao i no vetor
    */
   private int left(int i) {
      return (i * 2 + 1);
   }

   /**
    * Deve retornar o indice que representa o filho a direita do elemento
    * indexado pela posicao i no vetor
    */
   private int right(int i) {
      return (i * 2 + 1) + 1;
   }

   @Override
   public boolean isEmpty() {
      return (index == -1);
   }

   @Override
   public T[] toArray() {
      ArrayList<T> resp = new ArrayList<T>();
      for (T elem : this.heap) {
         if (elem != null) {
            resp.add(elem);
         }
      }
      return (T[]) resp.toArray(new Comparable[0]);
   }

   // ///////////// METODOS A IMPLEMENTAR
   /**
    * Valida o invariante de uma heap a partir de determinada posicao, que pode
    * ser a raiz da heap ou de uma sub-heap. O heapify deve colocar os maiores
    * (comparados usando o comparator) elementos na parte de cima da heap.
    */
   private void heapify(int position) {
      int leftIndex = left(position);
      int rightIndex = right(position);
      int max = position;
      if(leftIndex <= index && heap[leftIndex] != null && comparator.compare(heap[leftIndex], heap[position]) > 0) {
    	 max = leftIndex;
      }if(rightIndex <= index &&  heap[rightIndex] != null && comparator.compare(heap[rightIndex], heap[max]) > 0) {
    	 max = rightIndex;
      }
      if(max!=position) {
    	  Util.swap(heap, position, max);
    	  heapify(max);
      }
   }

   @Override
   public void insert(T element) {
	    if (index == heap.length - 1) {
	          heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
	      }
	   if(element != null) {
	   index++;
	   heap[index] = element;
	   int aux = index;
	   while(comparator.compare(heap[parent(aux)],heap[aux]) < 0) {
		   Util.swap(heap, aux, parent(aux));
		   aux = parent(aux);
	   }
	   }
   }

   @Override
   public void buildHeap(T[] array) {
	   heap = array;
	   index = heap.length-1;
	   for(int i = index/2; i >= 0;i--)
		   heapify(i);
   }

   @Override
   public T extractRootElement() {
	 if(isEmpty())
		 return null;
     T oldRoot = heap[0];
     heap[0] = heap[index];
     heap[index] = null;
     index--;
     heapify(0);
	 System.out.println(oldRoot);
     return oldRoot;
   }

   @Override
   public T rootElement() {
      return heap[0];
   }

   @Override
   public T[] heapsort(T[] array) {
	   HeapImpl<T> heap = new HeapImpl<>((o1, o2) -> o1.compareTo(o2));
		heap.buildHeap(array);
		T[] aux = (T[]) new Comparable[array.length];
		for (int i = heap.index; i >= 0; i--) {
			aux[i] = heap.extractRootElement();
		}
		return aux;
   }

   @Override
   public int size() {
	   return index+1;
   }

   public Comparator<T> getComparator() {
      return comparator;
   }

   public void setComparator(Comparator<T> comparator) {
      this.comparator = comparator;
   }

   public T[] getHeap() {
      return heap;
   }

}
