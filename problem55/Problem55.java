import java.util.*;
/**
 * Problem 55
 */

public class Problem55 {
	public static void main(String[] arg) {
		int nbLychrels = 0;
		for (int i = 1; i < 10000; i++) {
			if (nbIterations(i) == -1) nbLychrels++;
		}
		System.out.println("The solution is " + nbLychrels);		
	}
	
	/**
	* Returns the number of iterations required to produce a palindrome. If not less than 50, returns -1
	*/
	public static int nbIterations(int n) {
		String nAsStr = Integer.toString(n);
		for (int i = 1; i < 50; i++) {
			String sum = add(nAsStr, reverse(nAsStr));
			if (reverse(sum).equals(sum)) return i;
			nAsStr = sum;
		}
		return -1;
	}
	
	/**
	* Takes two integers that have the same number of digits, represented by strings, and returns a string representing their sum. 
	*/
	public static String add(String a, String b) {
		String result = "";
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
	* Takes a string and returns its reverse
	*/
	public static String reverse(String str) {
		String result = "";
		for (int i = str.length() - 1; i > -1; i--) {
			result += str.substring(i, i + 1);
		}
		return result;
	}
}