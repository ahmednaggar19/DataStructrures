package hashing;

import hashing.hash.function.utils.AbstractHashFunction;

public class PerfectHashTable {

	private AbstractHashFunction hashFunction;
	private PerfectHashTable[] hashTables;
	private int[] values;
	
	public PerfectHashTable() {
		
	}
	
	public void setHashFunction(AbstractHashFunction hashFunction) {
		this.hashFunction = hashFunction;
	}
	
	public void setValues(int[] values) {
		this.values = values;
	}
	
	public void setHashTables(PerfectHashTable[] hashTables) {
		this.hashTables = hashTables;
	}

	public boolean search(int key) {
		if(key <= 0){
			return false;
		}
		int index = hashFunction.getHashIndex(key);
		if (hashTables[index] == null) {
			return values[index] == key;
		}
		return hashTables[index].search(key);
	}
	
}
