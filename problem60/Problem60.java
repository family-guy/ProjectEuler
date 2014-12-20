import java.util.*;
/**
 * Problem 60
 */

public class Problem60 {
	public static void main(String[] arg) {
		int n = 100000000;
		boolean[] isPrime = IntegerArithmetic.sieveEratosthenes(n);
		int count = 0;
		int m = 10000; // assumes that there exists a set of five primes with the desired property such that each prime in the set is less than 10000
		for (int i = 0; i < m; i++) {
			if (isPrime[i]) count++;
		}
		int[] primes = new int[count]; int j = 0;
		for (int i = 0; i < m; i++) {
			if (isPrime[i]) {
				primes[j] = i;
				j++;
			} 
		}
		int minSum = m * 5;
		ArrayList<ArrayList<Integer>> currentSets = primePairs(isPrime, primes); // initialise the 2D dynamic array. Needs to be iterated on 3 times
		for (int i = 0; i < 3; i++) {
			ArrayList<ArrayList<Integer>> setsOfPrimes = setsOfPrimes(currentSets, isPrime, primes);
			currentSets.clear(); // clear the array before doing a deep copy
			for (int k = 0; k < setsOfPrimes.size(); k++) {
				currentSets.add(setsOfPrimes.get(k));
			}
		}
		// Get the minimum sum from currentSets
		for (int i = 0; i < currentSets.size(); i++) {
			int sum = sumElts(currentSets.get(i));
			if (sum < minSum) minSum = sum;
		}
		System.out.println("The solution is " + minSum);
	}
	
	/**
	* Takes a set of primes and returns all the prime pairs that can be made from it
	*/
	public static ArrayList<ArrayList<Integer>> primePairs(boolean[] isPrime, int[] primes) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < primes.length; i++) {
			for (int j = i + 1; j < primes.length; j++) {
				if (isPrimePair(primes[i], primes[j], isPrime)) {
					ArrayList<Integer> pair = new ArrayList<Integer>();
					pair.add(primes[i]); pair.add(primes[j]);
					result.add(pair);
				}
			}
		}
		return result;
	}
	
	/**
	* Returns the sum of the elements of a dynamic array
	*/
	public static int sumElts(ArrayList<Integer> T) {
		int sum = 0;
		for (int i = 0; i < T.size(); i++) {
			sum += T.get(i);
		}
		return sum;
	}
	
	/**
	* Takes a set of prime pair sets of cardinality n and returns a set of prime pair sets of cardinality n + 1
	*/
	public static ArrayList<ArrayList<Integer>> setsOfPrimes(ArrayList<ArrayList<Integer>> currentSets, boolean[] isPrime, int[] primes) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> sums = new ArrayList<Integer>(); // used to reduce number of duplicates
		for (int t = 0; t < primes.length; t++) {
			for (int i = 0; i < currentSets.size(); i++) {
				ArrayList<Integer> currentSet = currentSets.get(i);
				int sum = sumElts(currentSet);
				if (hasPrimeSetProp(currentSet, primes[t], isPrime) && sums.indexOf(sum) == -1) {
					sums.add(sum);
					ArrayList<Integer> resultSet = new ArrayList<Integer>();
					for (int j = 0; j < currentSet.size(); j++) {
						resultSet.add(currentSet.get(j));
					}
					resultSet.add(primes[t]);
					result.add(resultSet);
				}
			}
		}
		return result;
	}
	
	/**
	* Takes a set of primes and a prime p and returns whether each pair that includes p is a prime pair
	*/
	public static boolean hasPrimeSetProp(ArrayList<Integer> primeSet, int p, boolean[] isPrime) {
		for (int i = 0; i < primeSet.size(); i++) {
			if (!isPrimePair(primeSet.get(i), p, isPrime)) return false;
		}
		return true;
	}
	
	/**
	* Returns whether a pair of primes is a prime pair
	*/
	public static boolean isPrimePair(int a, int b, boolean[] isPrime) {
		String aAsStr = Integer.toString(a);
		String bAsStr = Integer.toString(b);
		return isPrime[Integer.parseInt(aAsStr + bAsStr)] && isPrime[Integer.parseInt(bAsStr + aAsStr)];
	} 
}