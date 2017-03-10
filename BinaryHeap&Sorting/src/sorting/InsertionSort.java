package sorting;

import java.util.ArrayList;

public class InsertionSort <T extends Comparable<T>> extends SortStrategy<T> {

	public static void main(String[] args) {
		InsertionSort<Integer> sort = new InsertionSort<>();
		ArrayList<Integer> nums = new ArrayList<>();
		nums.add(14);
		nums.add(7);
		nums.add(29);
		nums.add(3);
		nums.add(15);
		System.out.println(sort.sort(nums));;
	}

	public InsertionSort() {
		
	}

	@Override
	public ArrayList<T> sort(ArrayList<T> unsortedList) {
		for (int i = 1; i < unsortedList.size(); i++) {
			T cur = unsortedList.get(i);
			int j;
			for (j = i - 1; j >= 0 && cur.compareTo(unsortedList.get(j)) < 0; j--) {
				unsortedList.set(j + 1, unsortedList.get(j));
			}
			unsortedList.set(j + 1, cur);
		}
		return unsortedList;
	}

}
