package tree.avl.tests;

import static org.junit.Assert.*;

import java.util.Random;
import org.junit.Before;
import org.junit.Test;
import tree.avl.AVLTree;

public class AVLTests {

	private AVLTree<Integer> tree;
	private final int MAX_INSERTIONS = 1000000;
	private Random random;
	private int deletedNum;

	@Before
	public void intialize() {
		tree = new AVLTree<>();
		random = new Random();
		for (int i = 1; i < MAX_INSERTIONS; i++) {
			tree.insert(i);
		}
	}

	@Test
	public void test01SearchForExistingKey() {
		int randomInt = random.nextInt(MAX_INSERTIONS - 1);
		assertTrue(tree.search((randomInt)));
		deletedNum = randomInt;
	}

	@Test
	public void test02SearchForNonExistingKey() {
		assertFalse(tree.search(-10));
	}

	@Test
	public void test03DeleteExistingKey() {
		assertTrue(tree.delete(random.nextInt(MAX_INSERTIONS - 1)));
	}

	@Test
	public void test04DeleteNonExistingKey() {
		assertFalse(tree.delete(-10));
	}
	
	@Test
	public void test05CheckTreeHeight() {
		System.out.println(tree.height() + " " + Math.log(MAX_INSERTIONS));
	}

	@Test
	public void test06SearchAfterDeletion() {
		tree.delete(deletedNum);
		assertFalse(tree.search(deletedNum));
	}
}