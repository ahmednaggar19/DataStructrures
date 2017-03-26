package tree.avl.dictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import tree.avl.AVLTree;
import tree.avl.util.IDictionary;

public class Dictionary implements IDictionary {

	/**The tree used for storing the words.*/
	private AVLTree<String> tree;

	public Dictionary() {
		tree = new AVLTree<>();
	}

	@Override
	public void load(File file) {
		try {
			BufferedReader reader  = new BufferedReader(new FileReader(file));
			String word;
			// Checking for EOF
			while ((word = reader.readLine()) != null) {
			       tree.insert(word);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean insert(String word) {
		if (tree.search(word)) {
			return false;
		}
		tree.insert(word);
		return true;
	}

	@Override
	public boolean exists(String word) {
		return tree.search(word);
	}

	@Override
	public boolean delete(String word) {
		return tree.delete(word);
	}

	@Override
	public int size() {
		return tree.getSize();
	}

	@Override
	public int height() {
		return tree.height();
	}

}
