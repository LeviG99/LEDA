package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable> extends AbstractHashtableOpenAddress<T> {

   public HashtableOpenAddressQuadraticProbingImpl(int size, HashFunctionClosedAddressMethod method, int c1, int c2) {
      super(size);
      hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
      this.initiateInternalTable(size);
   }

   @Override
   public void insert(T element) {
      if (isFull()) {
         throw new HashtableOverflowException();
      }
      for (int i = 0; i < table.length; i++) {
         int posicao = ((HashFunctionQuadraticProbing<T>) this.getHashFunction()).hash(element, i);
         if (i != 0)
            COLLISIONS++;
         if (table[posicao] == null || table[posicao] == deletedElement) {
            table[posicao] = element;
            elements++;
            break;
         }
         if (table[posicao].equals(element)) {
            COLLISIONS--;
            break;
         }
      }
   }

   @Override
   public void remove(T element) {
      for (int i = 0; i < table.length; i++) {
         int posicao = ((HashFunctionQuadraticProbing<T>) this.getHashFunction()).hash(element, i);
         if (table[posicao] != null)
            if (table[posicao].equals(element)) {
               table[posicao] = deletedElement;
               elements--;
               break;
            }
      }
   }

   @Override
   public T search(T element) {
      for (int i = 0; i < table.length; i++) {
         int posicao = ((HashFunctionQuadraticProbing<T>) this.getHashFunction()).hash(element, i);
         if (table[posicao] != null)
            if (table[posicao].equals(element))
               return element;
      }
      return null;
   }

   @Override
   public int indexOf(T element) {
      for (int i = 0; i < table.length; i++) {
         int posicao = ((HashFunctionQuadraticProbing<T>) this.getHashFunction()).hash(element, i);
         if (table[posicao].equals(element))
            return posicao;
         if (table[posicao] == null) {
            return -1;
         }
      }
      return -1;
   }
}