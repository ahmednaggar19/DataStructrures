package tree.avl.util;

public interface INode<T extends Comparable<T>> {
	/**
	 * Returns the left child of the current element/node in the heap tree
	 * 
	 * @return INode wrapper to the left child of the current element/node
	 */
	INode<T> getLeftChild();

	void setLeftChild(INode<T> leftChild);
	/**
	 * Returns the right child of the current element/node in the heap tree
	 * 
	 * @return INode wrapper to the right child of the current element/node
	 */
	INode<T> getRightChild();

	void setRightChild(INode<T> rightChild);
	/**
	 * Set/Get the value of the current node
	 * 
	 * @return Value of the current node
	 */
	T getValue();

	void setValue(T value);

	/**
	 * Gets the height of the node
	 * 
	 * @return Height of the node
	 */
	int getHeight();

	/**
	 * Get the height difference of the node
	 * 
	 * @return Height difference of the node
	 */
	public int getHeightDifference();
}
