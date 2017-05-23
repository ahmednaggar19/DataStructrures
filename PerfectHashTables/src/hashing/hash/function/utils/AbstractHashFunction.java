package hashing.hash.function.utils;

import java.util.ArrayList;

public abstract class AbstractHashFunction {

	protected ArrayList<Integer> parameters;
	protected static int FIXED_PARAMETERS_SIZE;

	public AbstractHashFunction(ArrayList<Integer> parameters) {
		this.parameters = parameters;
	}

	public abstract int getHashIndex(int key);
}
