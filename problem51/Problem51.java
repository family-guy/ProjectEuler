import java.util.*;
/* Problem 51
 * next: Suppose that we enumerate all k-combinations of a set S of n elements in lexicographic order e.g. if S = {1, 2, 3, 4, 5}, k = 3, then we have {1, 2, 3}, {1, 2, 4}, {1, 2, 5}, {1, 3, 4}, {1, 3, 5}, {1, 4, 5}, {2, 3, 4}, {2, 3, 5}, {2, 4, 5}, {3, 4, 5}. Given one of these k-combinations in the form of an array and an integer representing n, this function returns the next k-combination e.g. next([1, 4, 5], 5) returns [2, 3, 4]
* isIn: takes an integer and an array of integers and returns whether that integer is in the array
* isPrimeFamAux: takes a prime, a positive integer m and a k-combination and returns if that k-combination yields a m prime value family 
* isPrimeFam: takes a prime and an integer m and returns whether that prime is part of a m prime value family
*/

public class Problem51 {
	public static void main(String[] arg) {
		int n = 5000000; // value of n is set arbitrarily
		boolean[] isPrime = IntegerArithmetic.sieveEratosthenes(n);
		// get the number of primes between 10 and n
		int nbPrimes = 0;
		for (int i = 10; i < isPrime.length; i++) {
			if (isPrime[i]) nbPrimes++;
		}
		// array of all primes between 10 and n
		int[] primes = new int[nbPrimes]; int j = 0;
		for (int i = 10; i < isPrime.length; i++) {
			if (isPrime[i]) {
				primes[j] = i;
				j++;
			}
		}
   	 	for (int i = 0; i < primes.length; i++) {
   			if (isPrimeFam(primes[i], 8, isPrime)) {
   				System.out.println("The solution is " + primes[i]);
   				break;
   			}
   		}
	}
		
	public static int[] next(int[] T, int n) { 
		int[] result = new int[T.length];
		for (int i = 0; i < T.length; i++) {
			result[i] = T[i];
		}
		int i = 0;
		while (T.length - 1 - i > -1) {
			if (T[T.length - 1 - i] < n - i) {
				result[result.length - 1 - i]++;
				for (int j = result.length - i; j < T.length; j++) {
					result[j] = result[result.length - 1 - i] + 1 + (j - (result.length - i));
				}
				break;
			}
			i++;
		}
		return result;
	}
	
	public static boolean isIn(int n, int[] T) {
		for (int i = 0; i < T.length; i++) {
			if (T[i] == n) return true;
		}
		return false;
	}
	
	public static boolean isPrimeFamAux(int n, int m, int[] T, boolean[] P) { 
		String nAsStr = Integer.toString(n);
		int count = 0; // counts the number of primes in the family generated
		if (T.length > 1) { // if the length of the array T is one no need to bother with the check below
			for (int i = 1; i < T.length; i++) { // checks to see if n has the same digits in the positions proposed by the combination i.e. the array T
				if (!nAsStr.substring(T[i], T[i] + 1).equals(nAsStr.substring(T[0], T[0] + 1))) return false;
			}
		}
		if (T[0] == 0) {
			for (int i = 1; i <= 9; i++) {
				String generatedNb = "";
				String iAsStr = Integer.toString(i);
				for (int j = 0; j < nAsStr.length(); j++) {
					if (isIn(j, T)) generatedNb += iAsStr;
					else generatedNb += nAsStr.substring(j, j + 1);
				}
				int generatedNbAsInt = Integer.parseInt(generatedNb);
				if (P[generatedNbAsInt]) count++;
			}
			if (count == m) return true;
		}
		else { // do the same as above except start from 0 rather than 1
			for (int i = 0; i <= 9; i++) {
				String generatedNb = "";
				String iAsStr = Integer.toString(i);
				for (int j = 0; j < nAsStr.length(); j++) {
					if (isIn(j, T)) generatedNb += iAsStr;
					else generatedNb += nAsStr.substring(j, j + 1);
				}
				int generatedNbAsInt = Integer.parseInt(generatedNb);
				if (P[generatedNbAsInt]) count++;
			}
			if (count == m) return true;
		}
		return false;
	}
	
	public static boolean isPrimeFam(int n, int m, boolean[] P) {
		String nAsStr = Integer.toString(n);
		for (int i = 1; i < nAsStr.length(); i++) {
			int[] startingValue = new int[i];
			for (int j = 0; j < startingValue.length; j++) {
				startingValue[j] = j;
			}
			int upperLimit = Combinatorics.nChooseK(startingValue.length, nAsStr.length());
			for (int k = 0; k < upperLimit; k++) {
				if (isPrimeFamAux(n, m, startingValue, P)) return true;
				startingValue = next(startingValue, nAsStr.length() - 1);
			}
		}
		return false;
	}
}
