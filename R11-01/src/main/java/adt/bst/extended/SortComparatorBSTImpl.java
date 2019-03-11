package adt.bst.extended;

import java.util.ArrayList;
import java.util.Comparator;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Implementacao de SortComparatorBST, uma BST que usa um comparator interno em suas funcionalidades
 * e possui um metodo de ordenar um array dado como parametro, retornando o resultado do percurso
 * desejado que produz o array ordenado. 
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class SortComparatorBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements SortComparatorBST<T> {

   private Comparator<T> comparator;

   public SortComparatorBSTImpl(Comparator<T> comparator) {
      super();
      this.comparator = comparator;
   }

   public BSTNode<T> search(T element) {
      return search(element, root);
   }

   private BSTNode<T> search(T element, BSTNode<T> node) {
      if (node.isEmpty())
         return new BSTNode<T>();
      if (node.getData().equals(element))
         return node;
      else {
         if (comparator.compare(node.getData(), element) > 0) {
            return search(element, (BSTNode<T>) node.getLeft());
         } else {
            return search(element, (BSTNode<T>) node.getRight());
         }
      }
   }

   @Override
   public void insert(T element) {
      insert(element, getRoot());
   }

   private void insert(T element, BSTNode<T> node) {
      if (node.isEmpty()) {
         node.setData(element);
         node.setLeft(new BSTNode<T>());
         node.getLeft().setParent(node);
         node.setRight(new BSTNode<T>());
         node.getRight().setParent(node);
      } else {
         if (comparator.compare(node.getData(), element) > 0) {
            insert(element, (BSTNode<T>) node.getLeft());
         } else {
            insert(element, (BSTNode<T>) node.getRight());
         }
      }
   }

   public void emptyBST() {
      while (!isEmpty())
         remove(root.getData());
   }

   @Override
   public T[] sort(T[] array) {
      emptyBST();
      for (T element : array)
         insert(element);
      return order();

   }

   @SuppressWarnings("unchecked")
@Override
   public T[] reverseOrder() {
      ArrayList<T> result = new ArrayList<T>();
	T[] reverse = (T[]) new Comparable[result.size()];
      if (isEmpty())
         return reverse;
      reverseOrder(root, result);
      reverse = (T[]) new Comparable[result.size()];
      for (int i = 0; i < reverse.length; i++)
         reverse[i] = result.get(i);
      return reverse;
   }

   private void reverseOrder(BSTNode<T> node, ArrayList<T> result) {
      if (!node.getRight().isEmpty())
         reverseOrder((BSTNode<T>) node.getRight(), result);
      result.add(node.getData());
      if (!node.getLeft().isEmpty())
         reverseOrder((BSTNode<T>) node.getLeft(), result);
   }

   public Comparator<T> getComparator() {
      return comparator;
   }

   public void setComparator(Comparator<T> comparator) {
      this.comparator = comparator;
   }

}
