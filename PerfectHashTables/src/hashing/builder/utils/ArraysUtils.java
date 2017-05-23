package hashing.builder.utils;

import java.util.ArrayList;

public class ArraysUtils {

	public static ArrayList<ArrayList<Integer>> initializeArray(int size) {
		ArrayList<ArrayList<Integer>> arrayList = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			arrayList.add(new ArrayList<>());
		}
		return arrayList;
	}

	public static int[] getPrimitiveArray(Integer[] integerArray) {
		int[] intArray = new int[integerArray.length];
		for (int i = 0; i < integerArray.length; i++) { 
			intArray[i] = integerArray[i];
		}
		return intArray;
	}
	
}
