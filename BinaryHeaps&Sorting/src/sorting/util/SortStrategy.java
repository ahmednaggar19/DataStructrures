package sorting.util;

import java.util.ArrayList;

public abstract class SortStrategy <T extends Comparable<T>> {

	public SortStrategy() {
		
	}
	abstract public ArrayList<T> sort(ArrayList<T> unsortedList);

}
