package priority.queue.heap;

import java.util.ArrayList;

import priority.queue.heap.util.Node;

public class MaxHeap <E, T extends Comparable<T>> extends Heap<E, T> {
	
	public static void main(String[] args) {
		MaxHeap<Integer, Integer> heap = new MaxHeap<Integer, Integer>();
		ArrayList<Node<Integer, Integer>> nodes = new ArrayList<>();
		nodes.add(new Node<Integer, Integer>(new Integer(16), 16));
		nodes.add(new Node<Integer, Integer>(new Integer(4), 4));
		nodes.add(new Node<Integer, Integer>(new Integer(7), 7));
		nodes.add(new Node<Integer, Integer>(new Integer(15), 15));
		heap.build(nodes);
		// 16 4 7 15
		System.out.println(heap.extract());
		System.out.println(heap.extract());
	}

	public MaxHeap() {
		super();
	}

	public MaxHeap(int maxSize) {
		super(maxSize);
	}
	
	@Override
	public E extract() {
		if (heapSize < 0) {
			throw new RuntimeException();
		}
		Node<E,T> maxNode = (Node<E,T>) heapElements.get(0);
		heapElements.set(0, heapElements.remove(heapSize));
		heapSize--;
		heapify(0);
		heapLength = heapElements.size();
		return (E)maxNode.getValue();
	}

	@Override
	public void heapify(int index) {
		int leftChildIndex = left(index) + 1;
		int rightChildIndex = right(index) + 1;
		int largestIndex = index;
		Node<E,T> currentNode = (Node<E,T>) heapElements.get(index);
		if (leftChildIndex <= heapSize) {
			Node<E,T> leftChild = (Node<E,T>) heapElements.get(leftChildIndex);
			if (leftChild.getKey().compareTo(currentNode.getKey()) > 0) {
				largestIndex = leftChildIndex;
			} else {
				largestIndex = index;
			}
		}
		Node<E,T> largestNode = (Node<E,T>) heapElements.get(largestIndex);
		if (rightChildIndex <= heapSize) {
			Node<E,T> rightChild = (Node<E,T>) heapElements.get(rightChildIndex);
			if (rightChild.getKey().compareTo(largestNode.getKey()) > 0) {
				largestIndex = rightChildIndex;
			}
		}
		if (largestIndex != index) {
			swapNodes(index, largestIndex);
			heapify(largestIndex);
		}
		return;
	}

	public void setArray(ArrayList<Node<E,T>> unsortedList) {
		heapElements.addAll(unsortedList);
		return;
	}

	@Override
	protected void build(ArrayList<Node<E,T>> list) {
		heapElements.addAll(list);
		heapSize += list.size();
		for (int i = (heapSize + 1)/ 2; i >= 0; i--) {
			heapify(i);
		}
	}

	@Override
	protected void popNodeUp(int nodeIndex) {
		if (nodeIndex == 0) {
			return;
		}
		int parentIndex = nodeIndex / 2;
		if (!(parentIndex < heapSize && parentIndex >= 0)) {
			return;
		}
		if (((Node<E,T>) heapElements.get(parentIndex)).getKey().compareTo(((Node<E,T>) heapElements.get(nodeIndex)).getKey()) > 0) {
			return;
		}
		swapNodes(nodeIndex, parentIndex);
		popNodeUp(parentIndex);
	}

	private void printNodes(ArrayList<Node<E, T>> array) {
		for (Node<E, T> node : array) {
			System.out.print(node.getValue() + " ");
		}
		System.out.println();
	}

	@Override
	protected void swapNodes(int firstIndex, int secondIndex) {
		Node<E,T> tempNode = (Node<E,T>) heapElements.get(firstIndex);
		heapElements.set(firstIndex, heapElements.get(secondIndex));
		heapElements.set(secondIndex, tempNode);
		return;
	}

	@Override
	public void insert(E object, T key) {
		heapSize++;
		heapElements.add(new Node<E,T>(object, key));
		popNodeUp(heapSize);
		heapLength = heapElements.size();
		
	}

	private ArrayList<Node<E, T>> heapSort() {
		heapLength = heapSize;
		for (int i = heapLength; i >= 1; i--) {
			swapNodes(0, i);
			heapSize--;
			heapify(0);
			printNodes(heapElements);
		}
		return heapElements;
	}

	@Override
	public ArrayList<Node<E, T>> heapSort(ArrayList<Node<E,T>> unsortedList) {
		MaxHeap<E, T> maxHeap = new MaxHeap<>();
		maxHeap.build(unsortedList);
		return maxHeap.heapSort();
	}


}
