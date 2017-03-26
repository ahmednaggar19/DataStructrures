package tree.avl;

import tree.avl.util.IAVLTree;
import tree.avl.util.INode;

public class AVLTree<T extends Comparable<T>> implements IAVLTree<T> {

	/** Pointer to the root of the AVL Tree. */
	private INode<T> root;
	/**
	 * Number denoting the tree height. as the longest path from. the root to a
	 * leaf-node.
	 */
//	private int treeHeight;

	/**
	 * Number denoting the number. of nodes currently stored.
	 */
	private int noOfNodes;

	/** Maximum Allowed imbalance in height for any node. */
	private static final int ALLOWED_IMBALANCE = 1;

	private boolean isRemoved;
	
	public AVLTree() {
		root = null;
		noOfNodes = 0;
	}

	@Override
	public void insert(T key) {
		//Node<T> newNode = new Node<>(key);
		root = insert(key, root);
		noOfNodes++;
	}

	/**
	 * Inserts the node and returns the new root of the tree.
	 * using recursion.
	 * @param key the element to be inserted
	 * @param node the root of the subtree
	 * to which the element should be inserted
	 * @return the new root of the subtree 
	 * */
	private INode<T> insert(T key, INode<T> node) {
		if (node == null) {
			return new Node<T>(key);
		}
		if (node.getValue().compareTo(key) < 0) {
			node.setRightChild(insert(key, node.getRightChild()));
		} else if (node.getValue().compareTo(key) > 0) {
			node.setLeftChild(insert(key, node.getLeftChild()));
		}

		return balance(node);
	}

	/**
	 * balances the given node performing.
	 * the sufficient rotations.
	 * @param node the node to be balanced
	 * @return the new root of the subtree 
	 * */
	private INode<T> balance(INode<T> node) {
		if (node == null) {
			return node;
		}
		if (node.getLeftChild() != null && node.getRightChild() != null) {
			if (node.getLeftChild().getHeight() - node.getRightChild().getHeight() > ALLOWED_IMBALANCE) {
				if (node.getLeftChild().getLeftChild() != null && node.getLeftChild().getRightChild() != null)
				if (node.getLeftChild().getLeftChild().getHeight()
						- node.getLeftChild().getRightChild().getHeight() > ALLOWED_IMBALANCE) {
					// perform double rotation with right child
					node = rotateWithRightChild(node);
				} else {
					// perform double rotation with left child
					node = doubleRotateLeftChild(node);
				}
			} else if (node.getRightChild().getHeight() - node.getLeftChild().getHeight() > ALLOWED_IMBALANCE) {
				if (node.getRightChild().getRightChild() != null && node.getRightChild().getLeftChild() != null)
				if (node.getRightChild().getRightChild().getHeight()
						- node.getRightChild().getLeftChild().getHeight() > ALLOWED_IMBALANCE) {
					// perform single rotation with right child
					node = rotateWithRightChild(node);
				} else {
					// perform double rotation with left child
					node = doubleRotateRightChild(node);
				}

			}
		} else if (node.getLeftChild() != null) {
			if (node.getLeftChild().getHeight() > ALLOWED_IMBALANCE) {
				node = rotateWithLeftChild(node);
			}
		} else if (node.getRightChild() != null) {
			if (node.getRightChild().getHeight() > ALLOWED_IMBALANCE) {
				node = rotateWithRightChild(node);
			}
		} else {
			; // leaf node
		}
		return node;
	}

	/**
	 * Rotates the given node with it left child.
	 * @param node the root of the subtree
	 * upon which the operation should be done
	 * @return the new root of the subtree 
	 * */
	private INode<T> rotateWithLeftChild(INode<T> node) {
		INode<T> leftChild = node.getLeftChild();
		if (leftChild != null) {
			node.setLeftChild(leftChild.getRightChild());
			leftChild.setRightChild(node);
		}
		return leftChild;
	}

	/**
	 * Rotates the given node with it right child.
	 * @param node the root of the subtree
	 * upon which the operation should be done
	 * @return the new root of the subtree 
	 * */
	private INode<T> rotateWithRightChild(INode<T> node) {
		INode<T> rightChild = node.getRightChild();
		if (rightChild != null) {
			node.setRightChild(rightChild.getLeftChild());
			rightChild.setLeftChild(node);
		}
		return rightChild;
	}

	/**
	 * Performs double rotation to the given.
	 * node with it left child.
	 * @param node the root of the subtree
	 * upon which the operation should be done
	 * @return the new root of the subtree 
	 * */
	private INode<T> doubleRotateLeftChild(INode<T> node) {
		node.setLeftChild(rotateWithRightChild(node.getLeftChild()));
		return rotateWithLeftChild(node);
	}

	/**
	 * Performs double rotation to the given.
	 * node with it right child.
	 * @param node the root of the subtree
	 * upon which the operation should be done
	 * @return the new root of the subtree 
	 * */
	private INode<T> doubleRotateRightChild(INode<T> node) {
		node.setRightChild(rotateWithLeftChild(node.getRightChild()));
		return rotateWithRightChild(node);
	}

	@Override
	public boolean delete(T key) {
		isRemoved = false;
		root = remove(key, root);
		if (isRemoved) {
			noOfNodes--;
			return true;
		}
		return false;
	}

	/**
	 * Deletes the node with the given.
	 * key and re balance the tree.
	 * @param key the element to be deleted
	 * @param node the root of the subtree
	 * upon which the operation should be done
	 * @return the new root of the subtree 
	 * */
	private INode<T> remove(T key, INode<T> node) {
		if (node == null) {
			return null;
		}
		if (node.getValue().compareTo(key) < 0) {
			node.setRightChild(remove(key, node.getRightChild()));
		} else if (node.getValue().compareTo(key) > 0) {
			node.setLeftChild(remove(key, node.getLeftChild()));
		} else if  (node.getValue().compareTo(key) == 0) {
			if (node.getLeftChild() != null && node.getRightChild() != null) {
				isRemoved = true;
				
				INode<T> newNode = findMinimum(node.getRightChild());
				newNode.setLeftChild(node.getLeftChild());
				node = newNode;
				node.setRightChild(remove(node.getValue(), node.getRightChild()));
			} else {
				isRemoved = true;
				node = (node.getLeftChild() != null) ? node.getLeftChild() : node.getRightChild();
			}
		} 
		return balance(node);
	}

	/**
	 * Finds the node with the minimum 
	 * key in the tree with the given root
	 * @param node the root of the subtree
	 * upon which the operation should be done
	 * @return the new root of the subtree 
	 * */
	private INode<T> findMinimum(INode<T> node) {
		if (node == null) {
			return null;
		} else if (node.getLeftChild() == null) {
			return node;
		}
		return findMinimum(node.getLeftChild());
	}

	@Override
	public boolean search(T key) {
		INode<T> currentNode = root;
		while (currentNode != null) {
			if (currentNode.getValue().compareTo(key) < 0) {
				currentNode = currentNode.getRightChild();
			} else if (currentNode.getValue().compareTo(key) > 0) {
				currentNode = currentNode.getLeftChild();
			} else {
				return true;
			}
		}
		return false;
	}

	@Override
	public int height() {
		return root.getHeight();
	}

	@Override
	public INode<T> getTree() {
		return root;
	}

	public void traverseInOrder() {
		System.out.println("ROOT " + root.getValue());
		inorder(root);
		System.out.println();
	}

	private void inorder(INode<T> node) {
		if (node != null) {
			inorder(node.getLeftChild());
			System.out.print(node.getValue() + " ");
			inorder(node.getRightChild());
		}
	}
	
	/**
	 * Returns the number of nodes in the tree.
	 * @return number of nodes in the tree
	 */
	public int getSize() {
		return noOfNodes;
	}
}
