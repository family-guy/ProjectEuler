import java.util.*;
/**
 * Problem 56
 */

public class Problem56 {
	public static void main(String[] arg) {
		int max = 0;
		for (int a = 1; a < 100; a++) {
			for (int b = 1; b < 100; b++) {
				String exp = exponent(a, b);
				int value = sumDigits(exp);
				if (value > max) max = value;
			}
		}
		System.out.println("The solution is " + max);
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
	public static String exponent(int a, int b) {
		String result = "1";
		for (int i = 0; i < b; i++) {
			result = multiply(result, a);
		}
		return result;
	}
	
	/**
	* Takes an integer represented as a string and returns the sum of its digits
	*/
	public static int sumDigits(String n) {
		int sum = 0;
		for (int i = 0; i < n.length(); i++) {
			sum += Integer.parseInt(n.substring(i, i + 1));
		}
		return sum;
	} 
}