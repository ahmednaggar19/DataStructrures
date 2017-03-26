package tree.avl;

import tree.avl.util.INode;

public class Node<T extends Comparable<T>> implements INode<T> {

	/** Value stored in the node of type T, initially null.*/
	private T value;
	/**Pointer to the left child.*/
	private INode<T> leftChild;
	/**Pointer to the right child.*/
	private INode<T> rightChild;
	/**
	 * A number denoting the difference between the heights of.
	 * the right subtree and the left subtree.
	 * */
	private int heightDifference;

	/**
	 * A number denoting the maximum.
	 * height of the node to a leaf node.
	 */
	private int nodeHeight;

	public Node() {
		leftChild = null;
		rightChild = null;
		value = null;
		initializeHeight();
	}

	public Node(T initialValue) {
		this.value = initialValue;
		initializeHeight();
	}
	
	public Node(INode<T> rightChild, INode<T> leftChild) {
		this.rightChild = rightChild;
		this.leftChild = leftChild;
		heightDifference = rightChild.getHeight() - leftChild.getHeight();
	}

	private void initializeHeight() {
		heightDifference = 0;
		nodeHeight = 1;
	}
	
	@Override
	public INode<T> getLeftChild() {
		return leftChild;
	}

	@Override
	public INode<T> getRightChild() {
		return rightChild;
	}

	@Override
	public T getValue() {
		return value;
	}

	@Override
	public void setValue(T value) {
		this.value = value;
	}

	@Override
	public void setLeftChild(INode<T> leftChild) {
		this.leftChild = leftChild;
		updateHeight();
	}

	@Override
	public void setRightChild(INode<T> rightChild) {
		this.rightChild = rightChild;
		updateHeight();
	}

	@Override
	public int getHeight() {
		return nodeHeight;
	}

	public int getHeightDifference() {
		return value == null ? -1 : heightDifference;
	}
	
	public void updateHeight() {
		// check first for null children
		heightDifference = (rightChild == null) ? 0 : rightChild.getHeightDifference()
				- ((leftChild == null) ? 0 : leftChild.getHeightDifference());
		nodeHeight = 1 + Math.max((rightChild == null) ? 0 : rightChild.getHeight(),
				(leftChild == null) ? 0 : leftChild.getHeight());
	}
}
