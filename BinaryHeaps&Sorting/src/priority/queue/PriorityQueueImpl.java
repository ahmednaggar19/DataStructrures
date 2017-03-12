package priority.queue;

import heap.MaxHeap;

public class PriorityQueueImpl<E, T extends Comparable<T>> implements PriorityQueue<E,T>{

	private MaxHeap<E, T> maxHeap;

	public PriorityQueueImpl() {
		maxHeap = new MaxHeap<>();
	}

	@Override
	public E extractMax() {
		return maxHeap.extract();
	}

	@Override
	public void insert(E object, T key) {
		maxHeap.insert(object, key);
	}

}
