import java.util.*;
/*
 * Problem 65
 */

public class Problem65 {
	public static void main(String[] arg) {
		String[] values = new String[99];
		for (int i = 0; i < values.length; i++) {
			if (i % 3 == 0 || i % 3 == 2) values[i] = "1";
			else {
				int x = (i / 3 + 1) * 2;
				values[i] = Integer.toString(x);
			}
		}
		String[] currentFrac = new String[2];
		currentFrac[0] = values[values.length - 1];
		currentFrac[1] = "1";
		for (int i = values.length - 2; i > -1; i--) {
			String currentInt = values[i];
			String[] next = f(currentInt, currentFrac[0], currentFrac[1]);
			currentFrac[0] = next[0];
			currentFrac[1] = next[1];
		}
		System.out.println("The solution is " + sumDigits(add(mult(2, currentFrac[0]), currentFrac[1])));
	}
	
	/**
	* Returns the sum of digits of an integer represented as a string
	*/
	public static int sumDigits(String str) {
		int result = 0;
		for (int i = 0; i < str.length(); i++) {
			result += Integer.parseInt(str.substring(i, i + 1));
		}
		return result;
	}
	
	/**
	* Takes a triple (a, b_1, b_2) and returns a + 1 / b where b = b_1 / b_2
	*/
	public static String[] f(String a, String b_1, String b_2) {
		String[] result = new String[2];
		result[1] = b_1; // denominator 
		int aAsInt = Integer.parseInt(a);
		result[0] = add(mult(aAsInt, b_1), b_2); // numerator
		return result;
	}
	
	/**
	* Returns the product of two integers, one of which is represented by a string
	*/
	public static String mult(int a, String b) {
		if (a == 1) return b;
		return add(mult(a - 1, b), b);
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