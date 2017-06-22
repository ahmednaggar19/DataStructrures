package hashing.hash.function;

import java.util.ArrayList;
import hashing.hash.function.utils.AbstractHashFunction;

public class UniversalLinearHashFunction extends AbstractHashFunction {

	private int aFactor;
	private int bFactor;
	private int hashTableSize;
	private int primeNunber;
	
	public UniversalLinearHashFunction(ArrayList<Integer> parameters) {
		super(parameters);
		FIXED_PARAMETERS_SIZE = 4;
		if (parameters.size() != FIXED_PARAMETERS_SIZE) {
			throw new RuntimeException();
		}
		aFactor = parameters.get(0);
		bFactor = parameters.get(1);
		hashTableSize = parameters.get(2);
		primeNunber = parameters.get(3);
	}

	@Override
	public int getHashIndex(int key) {
		return ((aFactor * key + bFactor) % primeNunber) % hashTableSize;
	}

	public static void main(String[] args) {
		ArrayList<Integer> parameters = new ArrayList<>();
		parameters.add(3); //a
		parameters.add(3); //b
		parameters.add(81); //m
		parameters.add(101); //p
		UniversalLinearHashFunction function = new UniversalLinearHashFunction(parameters);
		System.out.println(function.getHashIndex(1));
		System.out.println(function.getHashIndex(2));
		System.out.println(function.getHashIndex(5));
		System.out.println(function.getHashIndex(100));
		System.out.println(function.getHashIndex(98));
		System.out.println(function.getHashIndex(8));
		System.out.println(function.getHashIndex(40));
		System.out.println(function.getHashIndex(50));
		System.out.println(function.getHashIndex(44));
	}

}
