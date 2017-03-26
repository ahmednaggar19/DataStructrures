package tree.avl.tests;

import tree.avl.AVLTree;

public class AVLTest {

	public static void main(String[] args) {
		AVLTree<Integer> tree = new AVLTree<>();
		
		System.out.println("Insert 5");
		tree.insert(5);
		tree.traverseInOrder();
		System.out.println(tree.height());
		System.out.println();
		
		System.out.println("Insert 4");
		tree.insert(4);
		tree.traverseInOrder();
		System.out.println(tree.height());
		System.out.println();
		
		System.out.println("Insert 3");
		tree.insert(3);
		tree.traverseInOrder();
		System.out.println(tree.height());
		System.out.println();
		
		System.out.println("Delete 4");
		tree.delete(4);
		tree.traverseInOrder();
		System.out.println(tree.height());
		System.out.println();
		
		System.out.println("Does 4 exist ?");
		System.out.println(tree.search(4));
		System.out.println();
		
		System.out.println("Does 3 exist ?");
		System.out.println(tree.search(3));
		System.out.println();
		
		System.out.println("Does 5 exist ?");
		System.out.println(tree.search(5));
		System.out.println();
		
	}

}
