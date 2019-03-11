package adt.avltree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import adt.bst.BSTNode;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends AVLTreeImpl<T> implements AVLCountAndFill<T> {

   private int LLcounter;
   private int LRcounter;
   private int RRcounter;
   private int RLcounter;

   public AVLCountAndFillImpl() {

   }

   @Override
   public int LLcount() {
      return LLcounter;
   }

   @Override
   public int LRcount() {
      return LRcounter;
   }

   @Override
   public int RRcount() {
      return RRcounter;
   }

   @Override
   public int RLcount() {
      return RLcounter;
   }

   @Override
   protected void rebalance(BSTNode<T> node) {
      int balance = calculateBalance(node);
      if (balance > 1) {
         if (calculateBalance((BSTNode<T>) node.getRight()) < 0) {
            rightRotation((BSTNode<T>) node.getRight());
            leftRotation(node);
            RLcounter++;
         } else {
            leftRotation(node);
            RRcounter++;
         }
      } else if (balance < -1) {
         if (calculateBalance((BSTNode<T>) node.getLeft()) > 0) {
            leftRotation((BSTNode<T>) node.getLeft());
            rightRotation(node);
            LRcounter++;
         } else {
            rightRotation(node);
            LLcounter++;
         }
      }
   }

   @SuppressWarnings("unchecked")
   @Override
   public void fillWithoutRebalance(T[] array) {
      ArrayList<T> arrayList = new ArrayList<T>();
      for (T t : array)
         arrayList.add(t);
      for (T t : preOrder())
         arrayList.add(t);
      Collections.sort(arrayList);
      removeRepeated(arrayList);
      root = new BSTNode<T>();
      array = getArrayCerto((T[]) arrayList.toArray(new Comparable[arrayList.size()]));
      for (T t : array) {
         insert(t);
      }
   }

   @SuppressWarnings("unchecked")
   private T[] getArrayCerto(T[] array) {
      T[] newArray = (T[]) new Comparable[array.length];
      ArrayList<ArrayList<T>> aux = new ArrayList<ArrayList<T>>();
      aux.add(new ArrayList<T>(Arrays.asList(array)));
      for (int i = 0; i < array.length; i++) {
         int middle = aux.get(i).size() / 2;
         newArray[i] = aux.get(i).get(middle);
         aux.add(newArrayList(aux.get(i), 0, middle));
         aux.add(newArrayList(aux.get(i), middle + 1, aux.get(i).size()));
      }
      return newArray;
   }

   private ArrayList<T> newArrayList(ArrayList<T> array, int a, int f) {
      ArrayList<T> aux = new ArrayList<T>();
      for (int i = a; i < f; i++) {
         aux.add(array.get(i));
      }
      return aux;
   }

   private void removeRepeated(ArrayList<T> Arraylist) {
      for (int i = 0; i < Arraylist.size(); i++) {
         for (int j = i + 1; j < Arraylist.size() && Arraylist.get(i).equals(Arraylist.get(j)); j++) {
            Arraylist.remove(Arraylist.get(j));
         }
      }
   }

}
