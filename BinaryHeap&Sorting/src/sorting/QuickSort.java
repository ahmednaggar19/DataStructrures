package sorting;

import java.util.ArrayList;
import java.util.Collections;

public class QuickSort <T extends Comparable<T>> extends SortStrategy<T> {

	public static void main(String[] args) {
		QuickSort<Integer> sort = new QuickSort<>();
		ArrayList<Integer> nums = new ArrayList<>();
		nums.add(14);
		nums.add(7);
		nums.add(29);
		nums.add(3);
		nums.add(15);
		System.out.println(sort.sort(nums));;
	}

	public QuickSort() {
		
	}

	@Override
	public ArrayList<T> sort(ArrayList<T> unsortedList) {
		quickSort(unsortedList, 0, unsortedList.size() - 1);
		return unsortedList;
	}

	private void quickSort(ArrayList<T> unsortedList, int low, int high) {
		if (low < high) {
			int pivot = partition(unsortedList, low, high);
			quickSort(unsortedList, low, pivot - 1);
			quickSort(unsortedList, pivot + 1, high);
		}
	}

	private int partition(ArrayList<T> unsortedList, int low, int high) {
		T pivot = unsortedList.get(high);
		int i = low - 1;
		for (int j = low; j <= high - 1; j++) {
			if (unsortedList.get(j).compareTo(pivot) <= 0) {
				i++;
				Collections.swap(unsortedList, i, j);
			}
		}
		Collections.swap(unsortedList, i + 1, high);
		return i + 1;
	}
}
