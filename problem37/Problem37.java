import java.util.*;
/* Problem 37
 * isTruncLeftToRight: takes a positive integer and a boolean array and returns whether the integer is 'truncatable' from left to right
 * isTruncRightToLeft: takes a positive integer and a boolean array and returns whether the integer is 'truncatable' from right to left
*/

public class Problem37 {
	public static void main(String[] arg) {
		int n = 1000000; int sum = 0; int count = 0;
		boolean[] primes = IntegerArithmetic.sieveEratosthenes(n);
		for (int i = 10; i <= n; i++) {
			if (isTruncLeftToRight(i, primes) && isTruncRightToLeft(i, primes)) {
				count++;
				sum += i;
			}
		}
		System.out.println("We can confirm that there are " + count + " such truncatable primes. The solution is " + sum);
	}
	
	public static boolean isTruncLeftToRight(int n, boolean[] p) {
		String nAsStr = Integer.toString(n);
		for (int i = 0; i < nAsStr.length(); i++) {
			if (!p[Integer.parseInt(nAsStr.substring(i))]) return false;
		}
		return true;
	}
	
	public static boolean isTruncRightToLeft(int n, boolean[] p) {
		String nAsStr = Integer.toString(n);
		for (int i = 0; i < nAsStr.length(); i++) {
			if (!p[Integer.parseInt(nAsStr.substring(0, nAsStr.length() - i))]) return false;
		}
		return true;
	}
}