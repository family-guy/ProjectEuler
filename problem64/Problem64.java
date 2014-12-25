import java.util.*;
/* 
 * Problem 64
 */

public class Problem64 {
	public static void main(String[] arg) {
		int nbOddPeriods = 0;
		int N = 10000;
		for (int i = 0; i <= N; i++) {
			if (!isSquare(i)) {
				if (period(i) % 2 == 1) nbOddPeriods++;
			}
		}
		System.out.println("The solution is " + nbOddPeriods);
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
	* Takes a positive non-square integer and returns its period
	*/
	public static int period(int n) {
		int a_0 = (int)Math.sqrt(n);
		int[] firstTriple = {1, n, (-1) * a_0};
		int[] currentTriple = {1, n, (-1) * a_0};
		int result = 1;
		while (true) {
			int[] next = nextTerm(currentTriple);
			if (equals(next, firstTriple)) break;
			for (int i = 0; i < currentTriple.length; i++) {
				currentTriple[i] = next[i];
			}
			result++;
		}
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