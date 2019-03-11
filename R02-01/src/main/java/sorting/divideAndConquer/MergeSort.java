package sorting.divideAndConquer;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {
    public void sort(T array[], int leftIndex, int rightIndex){ 
        if (leftIndex < rightIndex) 
        { 
            int middle = (leftIndex+rightIndex)/2;  
            sort(array, leftIndex, middle); 
            sort(array, middle+1, rightIndex);  
            merge(array, leftIndex, middle, rightIndex); 
        } 
    }
    void merge(T[] array, int leftIndex, int middle, int rightIndex){ 
        int size1 = middle - leftIndex + 1; 
        int size2 = rightIndex - middle; 
        T leftArray[] = array.clone(); 
        T rightArray[] = array.clone(); 
        for (int i=0; i<size1; ++i) 
            leftArray[i] = array[leftIndex + i]; 
        for (int j=0; j<size2; ++j) 
            rightArray[j] = array[middle + 1+ j]; 
        int aux1 = 0, aux2 = 0; 
        int k = leftIndex; 
        while (aux1 < size1 && aux2 < size2){ 
            if (leftArray[aux1].compareTo(rightArray[aux2])<= 0) { 
                array[k] = leftArray[aux1]; 
                aux1++; 
            }else{ 
                array[k] = rightArray[aux2]; 
                aux2++; 
            } 
            k++; 
        } 
        while (aux1 < size1){ 
            array[k] = leftArray[aux1]; 
            aux1++; 
            k++; 
        } 
        while (aux2 < size2){ 
            array[k] = rightArray[aux2]; 
            aux2++; 
            k++; 
        } 
    } 
  
}






