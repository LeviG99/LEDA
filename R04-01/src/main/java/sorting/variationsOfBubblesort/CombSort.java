package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * The combsort algoritm.
 */
public class CombSort<T extends Comparable<T>> extends AbstractSorting<T> {
	int fator = 125;
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		int gap = rightIndex+1;
		boolean troca = true;
		while (gap !=1 || troca == true) {
			gap = gap*100/fator;
			if(gap < 1) {
				gap = 1;
			}
			troca = false;
			for (int i = 0; i < rightIndex+1-gap;i++) {
				if (array[i].compareTo(array[i+gap]) > 0){
					Util.swap(array, i,i+gap);
					troca = true;
				}
			}
		}
	}
}
