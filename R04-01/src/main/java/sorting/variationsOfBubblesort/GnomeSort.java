package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * The implementation of the algorithm must be in-place!
 */
public class GnomeSort<T extends Comparable<T>> extends AbstractSorting<T> {
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if(array.length == 0) {
			return;
		}
		int end = rightIndex+1;
		int pivot = leftIndex;
		while (pivot<end) {
			if(pivot == 0) {
				pivot++;
			}
			if (array[pivot].compareTo(array[pivot-1])>= 0) {
				pivot++;
			}
			else {
				Util.swap(array, pivot, pivot-1);
				pivot--;
			}
		}
	}
}
