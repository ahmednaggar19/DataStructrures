package tree.avl.dictionary.tests;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import tree.avl.dictionary.Dictionary;

public class DictionaryTest {

	private Dictionary dictionary;
	
	@Before
	public void intialize() {
		dictionary = new Dictionary();
		dictionary.load(new File("words.txt"));
	}

	@Test
	public void test01SearchForExistingWord() {
		assertTrue(dictionary.exists("to"));
	}

	@Test
	public void test02SearchForNonExistingWord() {
		assertFalse(dictionary.exists("nonExistingWord"));
	}

	@Test
	public void test03InsertExistingWord() {
		assertFalse(dictionary.insert("dictionary"));
	}

	@Test
	public void test04InsertNonExistingWord() {
		assertTrue(dictionary.insert("newWord"));
		assertTrue(dictionary.exists("newWord"));
	}

	@Test
	public void test05DeleteExistingWord() {
		assertTrue(dictionary.delete("dictionary"));
	}

	@Test
	public void test06DeleteNonExistingWord() {
		assertFalse(dictionary.delete("nonExistingWord"));
	}

	@Test
	public void test07CheckSize() {
		assertEquals(6 ,dictionary.size());
	}

}
