package hashing.hash.function;

import java.util.ArrayList;
import java.util.Random;

import hashing.hash.function.utils.AbstractHashFunction;

public class MatrixHashFunction extends AbstractHashFunction {

	private int hashTableSize;
	private int keyLength;
	private int[][] matrix;

	public static void main(String[] args) {
		ArrayList<Integer> parameters = new ArrayList<>();
		parameters.add(3);
		parameters.add(4);
		MatrixHashFunction function = new MatrixHashFunction(parameters);
		System.out.println(function.getHashIndex(5));
		System.out.println(function.getHashIndex(5));
	}
	
	public MatrixHashFunction(ArrayList<Integer> parameters) {
		super(parameters);
		FIXED_PARAMETERS_SIZE = 2;
		if (parameters.size() != FIXED_PARAMETERS_SIZE) {
			throw new RuntimeException();
		}
		hashTableSize = parameters.get(0);
		keyLength = parameters.get(1);
		matrix = new int[hashTableSize][keyLength];
		generateRandomMatrix();
	}

	private void generateRandomMatrix() {
		Random random = new Random();
		for (int outerIndex = 0; outerIndex < matrix.length; outerIndex++) {
		    for (int innerIndex = 0; innerIndex < matrix[outerIndex].length; innerIndex++) {
		        if (random.nextDouble() < 0.5) {
		            matrix[outerIndex][innerIndex] = 1;
		        } else {
		            matrix[outerIndex][innerIndex] = 0;
		        }
		        System.out.print(matrix[outerIndex][innerIndex] + " ");
		    }
		    System.out.println();
		}
		System.out.println("-----");
	}
	
	private int[] getKeyMatrix(int key) {
		int[] keyMatrix = new int[keyLength];
		for (int i = 0; i < keyLength; i++) {
			keyMatrix[i] = key & (1 << i);
			if (keyMatrix[i] > 0) {
				keyMatrix[i] = 1;
			}
			System.out.print(keyMatrix[i] + " ");
			
		}
		System.out.println("------");
		return keyMatrix;
	}

	private int[] multiplyMatrices(int[][] matrixA, int[] matrixB) {
		if (matrixA[0].length != matrixB.length) {
			return null;
		}
		int[] resultMatrix = new int[keyLength];
		for (int i = 0; i < matrixA.length; i++) {
			int accumulator = 0;
			for (int j = 0; j < matrixA[i].length; j++) {
				accumulator += matrixA[i][j] * matrixB[j];
			}
			if (accumulator > 0) {
				accumulator = 1;
			}
			resultMatrix[i] = accumulator;
			System.out.print(resultMatrix[i] + " ");
		}
		return resultMatrix;
	}

	private int convertMatrixToNumber(int[] matrix) {
		int number = 0;
		for (int i = 0; i < matrix.length; i++) {
			number += 1 << matrix[i];
		}
		return number;
	}
	
	@Override
	public int getHashIndex(int key) {
		int[] keyMatrix = getKeyMatrix(key);
		int[] resultMatrix = multiplyMatrices(matrix, keyMatrix);
		return convertMatrixToNumber(resultMatrix);
	}

}
