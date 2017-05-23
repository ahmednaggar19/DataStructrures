package hashing.hash.tables;

import hashing.hash.function.utils.AbstractHashFunction;

public class HashTable implements HashTableIF {
	
	private int[] hashTable;
	private AbstractHashFunction hashFunction;
	
	public HashTable(int maxSize, AbstractHashFunction function) {
		hashTable = new int[maxSize];
		hashFunction = function;
	}

	@Override
	public int getKey(int key) {
		int index = hashFunction.getHashIndex(key);
		return hashTable[index];
	}

	@Override
	public void addKey(int key) {
		int index = hashFunction.getHashIndex(key);
		hashTable[index] = key;
	}

	@Override
	public boolean search(int key) {
		
		int index = hashFunction.getHashIndex(key);
		return hashTable[index] == key;
	}

	
}
