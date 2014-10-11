import java.util.*;
import java.math.*;
/* Problem 36
 * reverse: takes a string and returns its reverse
 * isPalindrome: takes an integer (or BigInteger) and returns whether it is a palindrome
 * convertToBaseTwo: takes a positive integer and returns its value in base two
*/

public class Problem36 {
	public static void main(String[] arg) {
		int N = 1000000;
		int sum = 0;
		for (int i = 1; i < N; i++) {
			if (isPalindrome(i) && isPalindrome(convertToBaseTwo(i))) sum += i;
		}
		System.out.println("The solution is " + sum);
	}
	
	public static String reverse(String str) {
		String result = "";
		for (int i = 0; i < str.length(); i++) {
			result += str.charAt(str.length() - 1 - i);
		}
		return result;
	}
	
	public static boolean isPalindrome(int n) {
		String nAsStr = Integer.toString(n);
		return nAsStr.equals(reverse(nAsStr));
	}
	
	public static boolean isPalindrome(BigInteger n) {
		String nAsStr = n.toString();
		return nAsStr.equals(reverse(nAsStr));
	}
	
	public static BigInteger convertToBaseTwo(int n) {
		int exp = 0; int product = 1; BigInteger sum = new BigInteger("0");
		while (true) {
			while (true) {
				product *= 2;
				if (product > n) break;
				exp++;
			}
			BigInteger big = new BigInteger("10"); 
			sum = sum.add(big.pow(exp));
			if (product / 2 == n) break;
			n -= product / 2; product = 1; exp = 0;
		}
		return sum;
	}
}

