import java.util.*;
/*
 * Problem 63
 */

public class Problem63 {
	public static void main(String[] arg) {
		int sum = 0;
		for (int i = 1; i < 10; i++) {
			sum += nbNDigitIntsNthPow(i);
		}
		System.out.println("The solution is " + sum);
	}
	
	/*
	* Returns the powerful digit count for a given base
	*/
	public static int nbNDigitIntsNthPow(int base) {
		int result = 0;
		int exponent = 1;
		while (true) {
			String str = power(base, exponent);
			if (str.length() == exponent) result++;
			if (str.length() < exponent) break;
			exponent++;
		}
		return result;
	}
	
	/**
	* Takes two integers, one represented as a string, and returns their product represented as a string
	*/
	public static String multiply(String a, int b) {
		String result = "";
		int carryOver = 0; int x = 0; int i = 0;
		for (i = a.length() - 1; i > 0; i--) {
			x = Integer.parseInt(a.substring(i, i + 1));
			int value = (x * b + carryOver) % 10;
			result = Integer.toString(value) + result;
			carryOver = (x * b + carryOver) / 10;
		}
		x = Integer.parseInt(a.substring(i, i + 1));
		return Integer.toString(x * b + carryOver) + result;
	}
	
	/**
	* Calculates a^b
	*/
	public static String power(int a, int b) {
		String result = "1";
		for (int i = 0; i < b; i++) {
			result = multiply(result, a);
		}
		return result;
	}
}