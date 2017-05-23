package hashing.builder.utils;

public class PrimeNumberGenerator {

	public static void main(String[] args) {
		System.out.println(getPrimeNumber(120));;
	}

	public static int getPrimeNumber(int lowerBound) {
		boolean[] values = new boolean[2 * lowerBound];
		for (int i = 2; i < Math.sqrt(2 * lowerBound); i++) {
			if (values[i] == false) {
				for (int j = i * i; j < 2 * lowerBound; j += i) {
					values[j] = true;
				}
			}
		}
		for (int i = 2 * lowerBound - 1; i >= 2; i--) {
			if (values[i] == false) {
				return i;
			}
		}
		return 2;
	}
}
