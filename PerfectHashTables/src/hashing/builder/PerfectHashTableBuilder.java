package hashing.builder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import hashing.PerfectHashTable;
import hashing.builder.utils.ArraysUtils;
import hashing.builder.utils.PrimeNumberGenerator;
import hashing.hash.function.UniversalLinearHashFunction;

public class PerfectHashTableBuilder {

	private final static int MAX_ITERATIONS = 10;
	private final static int MAX_HASH_TABLE_SIZE_FACTOR = 4;
	private final static int PRIME_LOWER_BOUND = 100000;

	private static ArrayList<Integer> getHashFunctionParameters(int m, int p) {
		Random random = new Random();
		int a = random.nextInt(p - 1);
		int b = random.nextInt(p);
		ArrayList<Integer> parameters = new ArrayList<>();
		parameters.add(a);
		parameters.add(b);
		parameters.add(m);
		parameters.add(p);
		return parameters;
	}
	public static PerfectHashTable buildSquareSpaceHashTable(int[] keys) {
		PerfectHashTable table = new PerfectHashTable();
		int squaredSpace = keys.length * keys.length;
		for (int i = 0; i < MAX_ITERATIONS; i++) {
			UniversalLinearHashFunction hashFunction = new UniversalLinearHashFunction(
					getHashFunctionParameters(squaredSpace, PrimeNumberGenerator.getPrimeNumber(PRIME_LOWER_BOUND)));
			int[] hashedValues = new int[squaredSpace];
			boolean collisionOccured = false;
			for (int key : keys) {
				int hashedIndex = hashFunction.getHashIndex(key);
				if (hashedValues[hashedIndex] != 0 && hashedValues[hashedIndex] != key) {
					collisionOccured = true;
					break;
				}
				hashedValues[hashedIndex] = key;
			}
			if (!collisionOccured) {
				table.setHashFunction(hashFunction);
				table.setValues(hashedValues);
				PerfectHashTable[] hashTables = new PerfectHashTable[squaredSpace];
				table.setHashTables(hashTables);
				return table;
			}

		}
		return table;
	}

	public static PerfectHashTable buildLinearSpaceHashTable(int[] keys) {
		PerfectHashTable table = new PerfectHashTable();
		int linearSpace = keys.length;
		for (int i = 0; i < MAX_ITERATIONS; i++) {
			UniversalLinearHashFunction hashFunction = new UniversalLinearHashFunction(
					getHashFunctionParameters(linearSpace, PrimeNumberGenerator.getPrimeNumber(PRIME_LOWER_BOUND)));
			ArrayList<ArrayList<Integer>> values = ArraysUtils.initializeArray(linearSpace);
			Set<Integer> distinctKeys = new HashSet<Integer>();
			int totalSpace = 0;
			for (int key : keys) {
				if (distinctKeys.contains(key)) {
					continue;
				}
				distinctKeys.add(key);
				int index = hashFunction.getHashIndex(key);
				values.get(index).add(key);
				int curSize = values.get(index).size();
				if (curSize != 0)
					totalSpace -= ((curSize - 1) * (curSize - 1));
				totalSpace += (curSize * curSize);
			}
			totalSpace = 0;
			for (ArrayList<Integer> arrayList : values) {
				totalSpace += (arrayList.size() * arrayList.size());
			}
			System.out.println(totalSpace);
			int[] valuesWithNoCollisions = new int[linearSpace];
			if (totalSpace <= MAX_HASH_TABLE_SIZE_FACTOR * linearSpace) {
				PerfectHashTable[] perfectHashTables = new PerfectHashTable[linearSpace];
				for (int j = 0; j < linearSpace; j++) {
					int size = values.get(j).size();
					Integer[] curKeys = new Integer[size];
					valuesWithNoCollisions[j] = 0;
					if (size > 1) {
						perfectHashTables[j] = buildSquareSpaceHashTable(
								ArraysUtils.getPrimitiveArray(values.get(j).toArray(curKeys)));
					} else {
						perfectHashTables[j] = null;
						if (size == 1) {
							valuesWithNoCollisions[j] = values.get(j).get(0);
						}
					}
				}
				table.setHashFunction(hashFunction);
				table.setHashTables(perfectHashTables);
				table.setValues(valuesWithNoCollisions);
				break;
			}
		}
		return table;
	}
}
