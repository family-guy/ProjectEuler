import java.util.*;
/*
 * Problem 77
 */

public class Problem77 {
	public static void main(String[] arg) {
		int n = 1000000;
		boolean[] prime = IntegerArithmetic.sieveEratosthenes(n);
		int i = 10;
		int k = 5000;
		while (true) {
			int[] primes = primesLessThanN(i, prime);
			if (nbOfWays(i, primes) > k) {
				System.out.println("The solution is " + i);
				break;
			}
			i++;
		}
	}
	
	/*
	* Returns the primes less than a given integer
	*/
	public static int[] primesLessThanN(int n, boolean[] prime) {
		int nbPrimes = 0;
		for (int i = 0; i < n; i++) {
			if (prime[i]) nbPrimes++;
		}
		int[] result = new int[nbPrimes];
		int j = 0;
		for (int i = 0; i < n; i++) {
			if (prime[i]) {
				result[j] = i;
				j++;
			}
		}
		return result;
	}
	
	/*
	* Returns the number of ways in which an integer can be written as the sum of some given integers
	*/
	public static int nbOfWays(int t, int[] c) {
		if (c.length == 1) {
			if (t % c[0] == 0 && t / c[0] > 0) return 1;
			else return 0;
		}
		int sum = 0; int[] new_c = new int[c.length - 1];
		for (int i = 0; i < new_c.length; i++) {
			new_c[i] = c[i + 1];
		}
		if (t % c[0] == 0 && t / c[0] > 0) sum++;
		while (t > 0) {
			sum += nbOfWays(t, new_c);
			t -= c[0];
		}
		return sum; 
	}
}