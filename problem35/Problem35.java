import java.util.*;
/* Problem 35
 * convertIntToArray: returns a given integer as a dynamic array e.g. convertIntToArray(123) = [1, 2, 3]
 * isPrimeCircular: takes a prime and returns whether it is circular
*/

public class Problem35 {
	public static void main(String[] str) {
		int N = 1000000;
		/* create an array of all primes less than N */
		boolean[] P = IntegerArithmetic.sieveEratosthenes(N);
		ArrayList<Integer> allPrimesUnderN = new ArrayList<Integer>();
		for (int i = 0; i < P.length; i++) {
			if (P[i]) allPrimesUnderN.add(i);
		} // done
		int count = 0;
		for (int i = 0; i < allPrimesUnderN.size(); i++) {
			if (isPrimeCircular(allPrimesUnderN.get(i), P)) count++;
		}
		System.out.println("The solution is " + count);
	}
	
	public static boolean isPrimeCircular(int p, boolean[] P) { 
		ArrayList<Integer> pAsArray = convertIntToArray(p);
		if (pAsArray.size() == 1) return true;
		else {
			int pNbDigits = pAsArray.size(); int[] pRotated = new int[pNbDigits];
			for (int nbRotations = 1; nbRotations < pNbDigits; nbRotations++) { 
				for (int i = 0; i < pNbDigits; i++) {
					pRotated[i] = pAsArray.get((i + nbRotations) % pNbDigits);
				}
				/* convert pRotated to an integer and check if it is prime */
				int sum = 0;
				for (int j = pNbDigits - 1; j >= 0; j--) { 
					sum += (int)Math.pow(10, pNbDigits - 1 - j) * pRotated[j];
				}
				if (!P[sum]) return false;
			}
			return true;
		}
	}

	public static ArrayList<Integer> convertIntToArray(int n) {
		ArrayList<Integer> result = new ArrayList<Integer>();	
		int divisor = 1; int dividend = n;
		while (true) {
			if (divisor * 10 > n) break;
			divisor *= 10;
		}
		while (divisor > 0) {
			result.add(dividend / divisor);
			dividend %= divisor;
			divisor /= 10;
		}
		return result;
	}
}