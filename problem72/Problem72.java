import java.util.*;
/**
 * Problem 72
 */

public class Problem72 {
	public static void main(String[] arg) {
		int upperBound = (int)Math.sqrt(1000000);
		boolean[] prime = IntegerArithmetic.sieveEratosthenes(upperBound);
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
		/*int upperBound = 1000000;
		int k = (int)Math.sqrt(upperBound);
		boolean[] prime = IntegerArithmetic.sieveEratosthenes(k);*/
		long sum = 0;
		for (int d = 2; d <= 1000000; d++) {
			sum += (long)phi(d, primes);
		}
		System.out.println("The solution is " + sum);
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