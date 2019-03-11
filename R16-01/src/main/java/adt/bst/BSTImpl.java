package adt.bst;

import java.util.ArrayList;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

   protected BSTNode<T> root;

   public BSTImpl() {
      root = new BSTNode<T>();
   }

   public BSTNode<T> getRoot() {
      return this.root;
   }

   @Override
   public boolean isEmpty() {
      return root.isEmpty();
   }

   @Override
   public int height() {
      if (isEmpty())
         return -1;
      return height(root);
   }

   protected int height(BSTNode<T> node) {
      if (node.isEmpty())
         return -1;
      return 1 + Math.max(height((BSTNode<T>) node.getLeft()), height((BSTNode<T>) node.getRight()));
   }

   @Override
   public BSTNode<T> search(T element) {
      return search(element, root);
   }

   private BSTNode<T> search(T element, BSTNode<T> node) {
      if (node.isEmpty())
         return new BSTNode<T>();
      if (node.getData().equals(element))
         return node;
      else {
         if (node.getData().compareTo(element) > 0) {
            return search(element, (BSTNode<T>) node.getLeft());
         } else {
            return search(element, (BSTNode<T>) node.getRight());
         }
      }
   }

   @Override
   public void insert(T element) {
      insert(element, root);
   }

   private void insert(T element, BSTNode<T> node) {
      if (node.isEmpty()) {
         node.setData(element);
         node.setLeft(new BSTNode<T>());
         node.getLeft().setParent(node);
         node.setRight(new BSTNode<T>());
         node.getRight().setParent(node);
      } else {
         if (node.getData().compareTo(element) > 0) {
            insert(element, (BSTNode<T>) node.getLeft());
         } else {
            insert(element, (BSTNode<T>) node.getRight());
         }
      }
   }

   @Override
   public BSTNode<T> maximum() {
      if (isEmpty())
         return null;
      return maximum(root);
   }

   private BSTNode<T> maximum(BSTNode<T> node) {
      while (!node.getRight().isEmpty())
         node = (BSTNode<T>) node.getRight();
      return node;
   }

   @Override
   public BSTNode<T> minimum() {
      if (isEmpty())
         return null;
      return minimum(root);
   }

   private BSTNode<T> minimum(BSTNode<T> node) {
      while (!node.getLeft().isEmpty())
         node = (BSTNode<T>) node.getLeft();
      return node;
   }

   @Override
   public BSTNode<T> sucessor(T element) {
      BSTNode<T> aux = search(element);
      if (aux.isEmpty())
         return null;
      if (!aux.getRight().isEmpty())
         return minimum((BSTNode<T>) aux.getRight());
      BSTNode<T> y = (BSTNode<T>) aux.getParent();
      while (!y.isEmpty() && aux == y.getRight()) {
         aux = y;
         y = (BSTNode<T>) y.getParent();
         if (y == null)
            return null;
      }
      return y;

   }

   @Override
   public BSTNode<T> predecessor(T element) {
      BSTNode<T> aux = search(element);
      if (aux.isEmpty())
         return null;
      if (!aux.getLeft().isEmpty())
         return maximum((BSTNode<T>) aux.getLeft());
      BSTNode<T> y = (BSTNode<T>) aux.getParent();
      while (!y.isEmpty() && aux == y.getLeft()) {
         aux = y;
         y = (BSTNode<T>) y.getParent();
         if (y == null)
            return null;
      }
      return y;
   }

   public void remove(T element) {
      BSTNode<T> aux = search(element);
      if (!aux.isEmpty())
         remove(element, aux);
   }

   private void remove(T element, BSTNode<T> aux) {
      if (aux.isLeaf()) {

         if (root.isLeaf()) {
            root = new BSTNode<>();
         }else {
         if (isRight((BSTNode<T>) aux.getParent(), element))
            aux.getParent().setRight(new BSTNode<>());
         else
            aux.getParent().setLeft(new BSTNode<>());
         }
      } else if (oneFilho(aux)) {

         BSTNode<T> node;
         if (aux.getRight().isEmpty())
            node = (BSTNode<T>) aux.getLeft();
         else
            node = (BSTNode<T>) aux.getRight();

         BSTNode<T> parent = (BSTNode<T>) aux.getParent();

         if (isRight((BSTNode<T>) aux.getParent(), element)) {
            parent.setRight(node);
            node.setParent(parent);
         } else {
            if (root.getRight().isEmpty()) {
               root = (BSTNode<T>) aux.getLeft();
               root.setParent(new BSTNode<>());
            } else {
               parent.setLeft(node);
               node.setParent(parent);
            }
         }
      } else {
         BSTNode<T> suc = sucessor(aux.getData());
         aux.setData(suc.getData());
         remove(suc.getData(), suc);
      }

   }

   private boolean oneFilho(BSTNode<T> node) {
      return (!node.getLeft().isEmpty() && node.getRight().isEmpty())
            || (node.getLeft().isEmpty() && !node.getRight().isEmpty());
   }

   private boolean isRight(BSTNode<T> node, T element) {
      if (node == null || node.isEmpty())
         return false;
      return !node.getRight().isEmpty() && node.getRight().getData().equals(element);
   }

   @Override
   public T[] preOrder() {
      ArrayList<T> result = new ArrayList<T>();
      T[] array = (T[]) new Comparable[result.size()];
      if (isEmpty())
         return array;
      preOrder(root, result);
      array = (T[]) new Comparable[result.size()];
      for (int i = 0; i < array.length; i++)
         array[i] = result.get(i);
      return array;
   }

   private void preOrder(BSTNode<T> node, ArrayList<T> result) {
      result.add(node.getData());
      if (!node.getLeft().isEmpty())
         preOrder((BSTNode<T>) node.getLeft(), result);
      if (!node.getRight().isEmpty())
         preOrder((BSTNode<T>) node.getRight(), result);
   }

   @Override
   public T[] order() {
      ArrayList<T> result = new ArrayList<T>();
      T[] array = (T[]) new Comparable[result.size()];
      if (isEmpty())
         return array;
      order(root, result);
      array = (T[]) new Comparable[result.size()];
      for (int i = 0; i < array.length; i++)
         array[i] = result.get(i);
      return array;
   }

   private void order(BSTNode<T> node, ArrayList<T> result) {
      if (!node.getLeft().isEmpty())
         order((BSTNode<T>) node.getLeft(), result);
      result.add(node.getData());
      if (!node.getRight().isEmpty())
         order((BSTNode<T>) node.getRight(), result);

   }

   @Override
   public T[] postOrder() {
      ArrayList<T> result = new ArrayList<T>();
      T[] array = (T[]) new Comparable[result.size()];
      if (isEmpty())
         return array;
      postOrder(root, result);
      array = (T[]) new Comparable[result.size()];
      for (int i = 0; i < array.length; i++)
         array[i] = result.get(i);
      return array;
   }

   private void postOrder(BSTNode<T> node, ArrayList<T> result) {
      if (!node.getLeft().isEmpty())
         postOrder((BSTNode<T>) node.getLeft(), result);
      if (!node.getRight().isEmpty())
         postOrder((BSTNode<T>) node.getRight(), result);
      result.add(node.getData());
   }

   /**
    * This method is already implemented using recursion. You must understand
    * how it work and use similar idea with the other methods.
    */
   @Override
   public int size() {
      return size(root);
   }

   private int size(BSTNode<T> node) {
      int result = 0;
      // base case means doing nothing (return 0)
      if (!node.isEmpty()) { // indusctive case
         result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
      }
      return result;
   }

}
