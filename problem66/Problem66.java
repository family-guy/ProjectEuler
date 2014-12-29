import java.util.*;
/*
 * Problem 66
 * See http://en.wikipedia.org/wiki/Pell%27s_equation#Concise_representation_and_faster_algorithms, section 3 'Example'
 */

public class Problem66 {
	public static void main(String[] arg) {
		String currentMax = "0";
		int currentMaxIndex = 0;
		for (int i = 0; i <= 1000; i++) {
			if (!isSquare(i)) {
				String str = minSolInX(i);
				if (greaterThan(str, currentMax)) {
					currentMax = str;
					currentMaxIndex = i;
				}
			} 		
		}
		System.out.println("The solution is: D equals " + currentMaxIndex + " for which the value of x is " + currentMax);
	}
	
	/*
	* Returns whether a positive integer is a square
	*/
	public static boolean isSquare(int n) {
		int a = (int)Math.sqrt(n);
		return a * a == n;
	}
	
	/*
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
	
	/*
	* Returns the minimal solution in x for a given D
	*/
	public static String minSolInX (int D) {
		int i = 0;
		while (true) {
			String x = kthTermInfContFrac(i, D)[0];
			String y = kthTermInfContFrac(i, D)[1];
			if (mult(x, x).equals(add(mult(Integer.toString(D), mult(y, y)), "1"))) return x;
			i++;
		}
	}
	
	/*
	* Takes an integer and returns the k-th term in the sequence of partial values of its infinite continued fraction
	*/
	public static String[] kthTermInfContFrac(int k, int n) {
		String[] result = new String[2];
		ArrayList<ArrayList<Integer>> infContFracNotation = infContFrac(n);
		if (k == 0) {
			result[0] = Integer.toString(infContFracNotation.get(0).get(0));
			result[1] = "1";
			return result;
		}
		String[] values = repeatBlock(infContFracNotation.get(1), k);
		String[] currentFrac = new String[2];
		currentFrac[0] = values[values.length - 1];
		currentFrac[1] = "1";
		for (int i = values.length - 2; i > -1; i--) {
			String currentInt = values[i];
			String[] next = f(currentInt, currentFrac[0], currentFrac[1]);
			currentFrac[0] = next[0];
			currentFrac[1] = next[1];
		}
		result[0] = add(mult(Integer.toString(infContFracNotation.get(0).get(0)), currentFrac[0]), currentFrac[1]);
		result[1] = currentFrac[0];
		return result;
	}
	
	/*
	* Takes an array of integers and returns an array of length k by repeating the values in the array given e.g. repeatBlock([1, 1, 5], 7) returns [1, 1, 5, 1, 1, 5, 1]
	*/
	public static String[] repeatBlock(ArrayList<Integer> block, int k) {
		String[] values = new String[k];
		for (int i = 0; i < values.length; i++) {
			values[i] = Integer.toString(block.get(i % block.size()));
		}
		return values;
	}
	
	/**
	* Takes a triple (a, b_1, b_2) and returns a + 1 / b where b = b_1 / b_2
	*/
	public static String[] f(String a, String b_1, String b_2) {
		String[] result = new String[2];
		result[1] = b_1; // denominator
		result[0] = add(mult(a, b_1), b_2); // numerator
		return result;
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
	
	/**
	* Returns the product a * b of two non-negative integers where b is a digit and a is represented as a string
	*/
	public static String multAux(String a, int b) {
		if (b == 0) return "0";
		String result = "";
		int carryOver = 0; int p = 0;
		for (int i = 0; i < a.length() - 1; i++) {
			p = Integer.parseInt(a.substring(a.length() - 1 - i, a.length() - i)) * b + carryOver;
			result = Integer.toString(p % 10) + result;
			carryOver = p / 10;
		}
		p = Integer.parseInt(a.substring(0, 1)) * b + carryOver;
		while (true) {
			result = (p % 10) + result;
			if (p / 10 == 0) break;
			p /= 10;
		}
		return result;
	}
	
	/**
	* Returns the product of two non-negative integers represented as strings
	*/
	public static String mult(String a, String b) {
		if (a == "0" || b == "0") return "0";
		String sum = "0";
		for (int i = 0; i < a.length(); i++) {
			String zeros = "";
			for (int j = 0; j < a.length() - 1 - i; j++) {
				zeros += "0";
			}
			// if/else because in sum(a, b), a >= b
			if (i == 0) {
				sum = add(multAux(b, Integer.parseInt(a.substring(i, i + 1))) + zeros, sum); 
			}
			else {
				sum = add(sum, multAux(b, Integer.parseInt(a.substring(i, i + 1))) + zeros); 
			}
		}
		return sum;
	}
	
	/*
	* Takes a non-square positive integer and returns its infinite contiuned fraction e.g. infContFrac(23) returns [[4], [1, 3, 1, 8]]
	*/
	public static ArrayList<ArrayList<Integer>> infContFrac(int n) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> firstElt = new ArrayList<Integer>();
		firstElt.add((int)Math.sqrt(n));
		result.add(firstElt);
		ArrayList<Integer> secondElt = new ArrayList<Integer>();
		int[] firstTriple = {1, n, (-1) * firstElt.get(0)};
		int[] currentTriple = {1, n, (-1) * firstElt.get(0)};
		while (true) {
			int intermedFracDen = currentTriple[1] - currentTriple[2] * currentTriple[2];
			secondElt.add((int)(currentTriple[0] * (Math.sqrt(currentTriple[1]) - currentTriple[2]) / intermedFracDen));
			int[] next = nextTerm(currentTriple);
			if (equals(next, firstTriple)) break;
			for (int i = 0; i < currentTriple.length; i++) {
				currentTriple[i] = next[i];
			}
		}
		result.add(secondElt);
		return result;
	}
	
	/*
	* Takes a triple (a, b, c) representing a fraction of the form a / (sqrt(b) + c) and returns the next fraction (of the same form) in the sequence
	*/
	public static int[] nextTerm(int[] T) {
		int[] result = new int[3];
		result[1] = T[1];
		int intermedFracDen = T[1] - T[2] * T[2];
		int intermedFracWholePart = (int)(T[0] * (Math.sqrt(T[1]) - T[2]) / intermedFracDen);
		result[0] = intermedFracDen / T[0];
		result[2] = (-1) * T[2] - intermedFracWholePart * result[0];
		return result;
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