package sorting;

import java.util.ArrayList;

import priority.queue.heap.MaxHeap;
import priority.queue.heap.util.Node;

public class HeapSort <T extends Comparable<T>> extends SortStrategy<T> {

	public HeapSort() {
		super();
	}
	public static void main(String[] args) {
		ArrayList<Integer> nums = new ArrayList<>();
		nums.add(14);
		nums.add(7);
		nums.add(4);
		nums.add(18);
		nums.add(5);
		HeapSort sort = new HeapSort();
		System.out.println(sort.sort(nums));
	}
	@Override
	public ArrayList<T> sort(ArrayList<T> unsortedList) {
		MaxHeap<T, T> maxHeap = new MaxHeap<>();
		ArrayList<Node<T, T>> nodesArray = new ArrayList<>();
		for (T element : unsortedList) {
			nodesArray.add(new Node<T,T>(element, element));
		}
		nodesArray = maxHeap.heapSort(nodesArray);
		unsortedList.clear();
		for (Node<T, T> cur : nodesArray) {
			unsortedList.add(cur.getValue());
		}
		return unsortedList;
	}
	
	
	
}
