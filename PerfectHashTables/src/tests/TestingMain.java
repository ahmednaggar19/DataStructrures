package tests;

import java.io.File;
import java.util.Arrays;

import file.handlers.readers.FileReader;
import hashing.PerfectHashTable;
import hashing.builder.PerfectHashTableBuilder;

public class TestingMain {

	public static void main(String[] args) {
		FileReader reader = new FileReader();
		int[] keys = reader.readKeys(new File("keys.txt"));
		PerfectHashTable table = PerfectHashTableBuilder.buildLinearSpaceHashTable(keys);
		for (int i = 1; i < 100; i++) {
			System.out.println(i + " " + table.search(i));
		}
		Arrays.sort(keys);
		for (int key : keys) {
			System.out.print(key + " " );
		}
	}

}
