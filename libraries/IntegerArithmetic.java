import java.util.*;
/*
 * This class contains functions that perform simple integer arithmetic
 */

public class IntegerArithmetic {
	/**
	* Returns the prime decomposition of an integer (overloaded function)
	*/
	public static ArrayList<ArrayList<Integer>> primeDecomp(int n, int[] primes) {
		ArrayList<ArrayList<Integer>> solution = new ArrayList<ArrayList<Integer>>();
		return primeDecomp(n, primes, solution, 0);
	}
	
	public static ArrayList<ArrayList<Integer>> primeDecomp(int n, int[] primes, ArrayList<ArrayList<Integer>> solution, int lastIndex) {
		ArrayList<Integer> primeFact = new ArrayList<Integer>();
		for (int i = lastIndex; i < primes.length; i++) {
			int p = primes[i];
			if (n % p == 0) {
				primeFact.add(p);
				int expPrimeFact = 1;
				n /= p;
				while (true) {
					if (n % p == 0) {
						expPrimeFact++;
						n /= p;
					} 
					else break;
				}
				primeFact.add(expPrimeFact);
				solution.add(primeFact);
				if (n == 1) return solution;
				return primeDecomp(n, primes, solution, i + 1);
			}
		}
		primeFact.add(n);
		primeFact.add(1);
		solution.add(primeFact);
		return solution;
	}
	
	/**
	* Can be used to determine whether a number is prime
	*/
	public static boolean[] sieveEratosthenes(int n) { // returns all primes less than or equal to n
		boolean[] result = new boolean[n + 1];
		for (int i = 0; i < result.length; i++) { // starting case, all values are true
			result[i] = true;
		}
		result[0] = false; result[1] = false;
		for (int i = 0; i * i < n; i++) { // the exit condition comes from "(for all i in {1, ..., [sqrt n]} s.t i is prime, n is not divisible by i) => n is prime"
			if (result[i] == true) {
				for (int j = 2; i * j <= n; j++) {
					result[i * j] = false;
				}
			} 
		}
		return result;
	}
	
	/**
	* Can be used to determine whether a number is prime
	*/
	public static boolean isPrime(long n) { 
		if (n <= 1) return false;
		else {
			for (int i = 2; i * i <= n; i++) {
				if (n % i == 0) return false;
			}
		}
		return true;
	}
	
	/**
	* Returns the sum of a number's proper divisors
	*/
	public static int sumOfPropDiv(int n) {
		int sum = 0;
		for (int i = 2; i * i <= n; i++) {
			if (n % i == 0) {
				if (i != n / i) sum += i + n / i;
				else sum += i;
			}
		}
		return sum + 1;
	}
	
	/**
	* Returns the greatest common divisor of two integers a and b where a is less than or equal to b
	*/
	public static int gcd(int a, int b) { 
		if (b % a == 0) return a;
		return gcd(b % a, a);
	}	
}