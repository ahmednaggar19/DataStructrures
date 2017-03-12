package sorting.util;

import java.util.ArrayList;

public class Sort<T extends Comparable<T>> {

	private SortStrategy<T> sortingDelegate;

	public Sort(SortStrategy<T> sortingDelegate) {
		this.sortingDelegate = sortingDelegate;
	}

	public ArrayList<T> sort(ArrayList<T> unsortedList) {
		return sortingDelegate.sort(unsortedList);
	}
	
}
