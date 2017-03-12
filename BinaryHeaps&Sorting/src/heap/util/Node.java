package heap.util;

public class Node <E, T extends Comparable<T>> {

	private E value;
	private T key;

	public Node(E value, T key) {
		this.value = value;
		this.key = key;
	}

	public E getValue() {
		return value;
	}

	public T getKey() {
		return key;
	}
}
