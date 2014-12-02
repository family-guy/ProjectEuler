import java.util.*;
/**
 * Problem 57
 */

public class Problem57 {
	public static void main(String[] arg) {
		String a = "1"; String b = "1";
		int count = 0;
		for (int i = 0; i < 1000; i++) {
			String temp = a;
			a = add(add(a, b), b);
			b = add(temp, b);
			if (a.length() > b.length()) count++;
		}
		System.out.println("The solution is " + count);
	}
	
	/**
	* Takes two integers a and b, where a >= b, represented by strings, and returns a string representing their sum. 
	*/
	public static String add(String a, String b) {
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
}