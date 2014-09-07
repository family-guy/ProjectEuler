import java.util.*;
/*
 * This class contains functions that perform simple integer arithmetic
 * sieveEratosthenes: can be used to determine whether a number is prime
 * isPrime: same as above
 * sumOfPropDiv: returns the sum of a number's proper divisors
 * gcd: returns the greatest common divisor of a number
*/

public class IntegerArithmetic {
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
	
	public static boolean isPrime(long n) { 
		if (n <= 1) return false;
		else {
			for (int i = 2; i * i <= n; i++) {
				if (n % i == 0) return false;
			}
		}
		return true;
	}
	
	public static int sumOfPropDiv(int n) { // function d(n) ie returns sum of proper divisors of n
		int sum = 0;
		for (int i = 2; i * i <= n; i++) {
			if (n % i == 0) {
				if (i != n / i) sum += i + n / i;
				else sum += i;
			}
		}
		return sum + 1;
	}
	
	public static int gcd(int a, int b) { // a <= b
		if (b % a == 0) return a;
		return gcd(b % a, a);
	}	
}