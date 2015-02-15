import java.util.*;
/*
 * Problem 80
 */

public class Problem80 {
	public static void main(String[] arg) {
		ArrayList<ArrayList<Integer>> contFracReps = new ArrayList<ArrayList<Integer>>();
		for (int i = 1; i <= 100; i++) {
			if (!isSquare(i)) {
				ArrayList<Integer> row = new ArrayList<Integer>();
				getContFracExpInPlace(i, row);
				contFracReps.add(row);
			} 
		}
		int sum = 0;
		for (int i = 1; i <= 100; i++) {
			if (!isSquare(i)) sum += (int)Math.sqrt(i);
		}
		for (int i = 0; i < contFracReps.size(); i++) {
			String currentVal = "";
			int k = 2;
			while (true) {
				String nextVal = divide(contFrac(contFracReps.get(i), k)[1], contFrac(contFracReps.get(i), k)[0]);
				if (currentVal.equals(nextVal)) break;
				currentVal = nextVal;
				k++;
			}
			sum += sumDigits(currentVal);
		}
		System.out.println("The solution is: " + sum);
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
	* Performs division of two integers represented as strings to 99 decimal places
	*/
	public static String divide(String a, String b) {
		String result = "";
		String remainder = "0";
		String quotient = "0";
		String dividend = a;
		for (int i = 0; i < 99; i++) {
			dividend += "0";
			String[] quotRem = IntegerArithmetic.quotientRemainder(dividend, b);
			quotient = quotRem[0];
			result += quotient;
			remainder = quotRem[1];
			if (remainder.equals("0")) {
				while (result.length() < 100) {
					result += "0";
				}
				break;
			}
			dividend = remainder;
		}
		return result;
	}
	
	/**
	* Continued fraction expansion to n iterations, n greater than or equal to 2
	*/
	public static String[] contFrac(ArrayList<Integer> contFracRep, int n) {
		String[] currentFrac = new String[2];
		currentFrac[0] = Integer.toString(contFracRep.get((n - 1) % contFracRep.size()));
		currentFrac[1] = "1";
		for (int i = n - 2; i > -1; i--) {
			String[] next = f(Integer.toString(contFracRep.get(i % contFracRep.size())), currentFrac[0], currentFrac[1]);
			currentFrac[0] = next[0];
			currentFrac[1] = next[1];
		}
		return currentFrac;	
	}
	
	public static String[] f(String a, String b_1, String b_2) {
		String[] result = new String[2];
		result[1] = b_1;
		result[0] = IntegerArithmetic.sum(IntegerArithmetic.product(a, b_1), b_2);
		return result;
	}
	
	/*
	* Returns whether a positive integer is a square
	*/
	public static boolean isSquare(int n) {
		int a = (int)Math.sqrt(n);
		return a * a == n;
	}

	/*
	* Takes a triple (a, b, c) representing a fraction of the form a / (sqrt(b) + c) and returns the next fraction (of the same form) in the sequence
	*/
	public static int[] nextTerm(int[] T, ArrayList<Integer> values) {
		int[] result = new int[3];
		result[1] = T[1];
		int intermedFracDen = T[1] - T[2] * T[2];
		int intermedFracWholePart = (int)(T[0] * (Math.sqrt(T[1]) - T[2]) / intermedFracDen);
		values.add(intermedFracWholePart);
		result[0] = intermedFracDen / T[0]; //System.out.println(intermedFracWholePart);
		result[2] = (-1) * T[2] - intermedFracWholePart * result[0];
		return result;
	}
	
	/*
	* Takes a positive non-square integer and returns its continued fraction expansion in array form
	*/
	public static void getContFracExpInPlace(int n, ArrayList<Integer> values) {
		int a_0 = (int)Math.sqrt(n);
		int[] firstTriple = {1, n, (-1) * a_0};
		int[] currentTriple = {1, n, (-1) * a_0};
		while (true) {
			int[] next = nextTerm(currentTriple, values);
			if (equals(next, firstTriple)) break;
			for (int i = 0; i < currentTriple.length; i++) {
				currentTriple[i] = next[i];
			}
		}
	}

	/*
	* Returns if two arrays of integers are equal
	*/
	public static boolean equals(int[] T, int[] U) {
		if (T.length != U.length) return false;
		for (int i = 0; i < T.length; i++) {
			if (T[i] != U[i]) return false;
		}
		return true;
	}
}

