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
	
	/**
	* Takes two positive integers a and b represented as strings and returns the quotient a / b and the remainder
	*/
	public static String[] quotientRemainder(String a, String b) {
		String[] result = new String[2];
		String quotient = "0";
		while (true) {
			if (greaterThan(b, a)) break;
			a = difference(a, b);
			quotient = sum(quotient, "1");
		}
		result[0] = quotient;
		result[1] = a;
		return result;
	}
	
	/**
	* Takes two non-negative integers a and b represented as strings and returns whether a is greater than b
	*/
	public static boolean greaterThan(String a, String b) {
		if (a.length() < b.length()) return false;
		if (a.length() == b.length()) {
			for (int i = 0; i < a.length(); i++) {
				String aDigit = a.substring(i, i + 1);
				String bDigit = b.substring(i, i + 1);
				if (!aDigit.equals(bDigit)) return Integer.parseInt(aDigit) > Integer.parseInt(bDigit);
			}
			return false;
		}
		return true;
	}
	
	/**
	* Returns the sum of two arbitrarily large non-negative integers represented as strings where the first argument is greater than or equal to the second
	*/
	public static String sum(String a, String b) {
		String result = "";
		int difference = a.length() - b.length();
		for (int i = 0; i < difference; i++) {
			b = "0" + b;
		}
		int carryOver = 0;
		for (int i = a.length() - 1; i > -1; i--) {
			int x = Integer.parseInt(a.substring(i, i + 1));
			int y = Integer.parseInt(b.substring(i, i + 1));
			int value = (x + y + carryOver) % 10;
			result = Integer.toString(value) + result;
			carryOver = (x + y + carryOver) / 10;
		}
		if (carryOver == 0) return result;
		return Integer.toString(carryOver) + result;
	}
	
	/**
	* Returns the product of an arbitrarily large non-negative integer represented as a string and a digit
	*/
	public static String digitProduct(int digit, String b) {
		if (digit == 0) return "0";
		if (digit == 1) return b;
		return sum(digitProduct(digit - 1, b), b);
	}
	
	/**
	* Returns the product of two arbitrarily large non-negative integers represented as strings where the first argument is greater than or equal to the second
	*/
	public static String product(String a, String b) {
		String result = digitProduct(Integer.parseInt(b.substring(0, 1)), a);
		for (int i = 0; i < b.length() - 1; i++) {
			result += "0";
		}
		for (int i = 1; i < b.length(); i++) {
			String substr = b.substring(i, i + 1);
			String str = digitProduct(Integer.parseInt(substr), a);
			for (int j = i; j < b.length() - 1; j++) {
				str += "0";
			}
			result = sum(result, str);
		}
		return result;
	}
	
	/**
	* Returns the difference of two arbitrarily large non-negative integers represented as strings where the first argument is greater than or equal to the second
	*/
	public static String difference(String a, String b) {
		String result = "";
		int[] A = new int[a.length()];
		int[] B = new int[a.length()];
		for (int i = 0; i < A.length; i++) {
			A[i] = Integer.parseInt(a.substring(i, i + 1));
		}
		int diffLengths = a.length() - b.length();
		for (int i = diffLengths; i < A.length; i++) {
			B[i] = Integer.parseInt(b.substring(i - diffLengths, i - diffLengths + 1));
		}
		for (int i = A.length - 1; i > -1; i--) {
			if (A[i] < B[i]) {
				int k = 1;
				while (true) {
					if (A[i - k] != 0) {
						A[i - k]--;
						break;
					}
					A[i - k] = 9;
					k++;
				}
				result = Integer.toString(A[i] + 10 - B[i]) + result;
			}
			else result = Integer.toString(A[i] - B[i]) + result;
		}
		String resultWithoutLeadingZeros = "";
		int j = 0;
		for (j = 0; j < result.length() - 1; j++) {
			if (!(result.substring(j, j + 1).equals("0"))) break;
		}
		for (int l = j; l < result.length(); l++) {
			resultWithoutLeadingZeros += result.substring(l, l + 1);
		}
		return resultWithoutLeadingZeros;
	}
}