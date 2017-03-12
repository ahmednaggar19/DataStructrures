package priority.queue;

public interface PriorityQueue<E, T extends Comparable<T>> {

	public E extractMax();

	public void insert(E object, T key);
}
