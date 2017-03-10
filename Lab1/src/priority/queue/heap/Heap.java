package priority.queue.heap;

import java.util.ArrayList;
import priority.queue.heap.util.Node;

public abstract class Heap <E, T extends Comparable<T>>  {

	/**The array that represents the heap binary tree.*/
	protected ArrayList<Node<E, T>> heapElements;

	/**The total length of the heap array.*/
	protected int heapLength;

	/**The total Size of the heap array.*/
	protected int heapSize;

	/**The total length of the heap array.*/
	private final int MAX_SIZE = 1000000;

	public Heap() {
		heapElements = new ArrayList<>(MAX_SIZE);
		initializeAttributes();
	}

	public Heap(int maxSize) {
		heapElements = new ArrayList<>(maxSize);
		initializeAttributes();
	}

	public void initializeAttributes() {
		heapSize = -1;
		heapLength = 0;
	}

	public abstract ArrayList<Node<E, T>> heapSort(ArrayList<Node<E,T>> unsortedList);

	abstract protected void build(ArrayList<Node<E,T>> list);
	
	abstract public void insert(E object, T key);
	
	abstract public E extract();

	abstract public void heapify(int index);

	abstract protected void popNodeUp(int index);
	
	abstract protected void swapNodes(int firstIndex, int secondIndex);

	protected int left(int index) {
		//if (index == 0) return 1;
		return 2 * index;
	}

	protected int right(int index) {
		//if (index == 0) return 2;
		return 2 * index + 1;
	}
}
