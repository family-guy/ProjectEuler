import java.util.*;
/**
 * Problem 69
 */

public class Problem69 {
	public static void main(String[] arg) {
		int n = (int)Math.sqrt(1000000);
		boolean[] prime = IntegerArithmetic.sieveEratosthenes(n);
		int nbPrimes = 0;
		for (int i = 0; i < prime.length; i++) {
			if (prime[i]) nbPrimes++;
		}
		int[] primes = new int[nbPrimes];
		int j = 0;
		for (int i = 0; i < prime.length; i++) {
			if (prime[i]) {
				primes[j] = i;
				j++;
			}
		}
		float maxRatio = 0;
		int index = 0;
		for (int i = 2; i <= 1000000; i++) {
			float ratio = (float)i / (float)phi(i, primes);
			if (ratio > maxRatio) {
				maxRatio = ratio;
				index = i;
			} 
		}
		System.out.println("The solution is " + index);
	}

	/**
	* Returns phi n
	*/
	public static int phi(int n, int[] primes) {
		ArrayList<ArrayList<Integer>> primeDec = IntegerArithmetic.primeDecomp(n, primes);
		int result = 1;
		for (int i = 0; i < primeDec.size(); i++) {
			result *= (primeDec.get(i).get(0) - 1) * (int)Math.pow(primeDec.get(i).get(0), primeDec.get(i).get(1) - 1);
		}
		return result;
	}
}