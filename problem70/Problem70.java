import java.util.*;
/**
 * Problem 70
 */

public class Problem70 {
	public static void main(String[] str) {
		int upperBound = (int)Math.sqrt(10000000);
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
		int index = 87109;
		float minRatio = (float)index / (float)phi(index, primes);
		for (int n = 2; n < 10000000; n++) {
			int phiOfn = phi(n, primes);
			if (Combinatorics.isPerm(phiOfn, n)) {
				float ratio = (float)n / (float)phiOfn;
				if (ratio < minRatio) {
					minRatio = ratio;
					index = n;
				} 
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