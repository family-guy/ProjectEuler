import java.util.*;
/**
 * Combinatorics methods for strings and integers
 */

public class Combinatorics {
	/**
	* Inserts a character into a string
	*/
	private static String insertCharAt(String str, String ch, int i) {		
		if (i == 0) return ch + str;
		if (i == str.length()) return str + ch;
		else return str.substring(0, i) + ch + str.substring(i, str.length());	
	}
	
	/**
	* Removes a character from a string
	*/
	private static String removeCharAt(String str, int i) {
		return str.substring(0, i) + str.substring(i + 1);
	}
	
   	/**
   	* Gets new strings formed by adding one character to an existing string
   	*/
	private static String[] allNewStrings(String str, String ch) {
		String[] result = new String[str.length() + 1];
		for (int i = 0; i < str.length() + 1; i++) {
			result[i] = insertCharAt(str, ch, i);
		}
		return result;
	}
	
	/**
	* Gets new strings formed by adding one character to an existing array of strings
	*/
	private static String[] allNewStrings(String[] T, String ch) {
		int p = T.length; int q = T[0].length() + 1;
		String[] result = new String[p * q]; int j = -1;
		for (int i = 0; i < p * q; i++) {
			if (i % q == 0) j++;
			result[i] = allNewStrings(T[j], ch)[i % q];			
		}
		return result;
	}
	
	/**
	* Suppose that we enumerate all k-combinations of a set S of n elements in lexicographic order e.g. if S = {1, 2, 3, 4, 5}, k = 3, then we have {1, 2, 3}, {1, 	2, 4}, {1, 2, 5}, {1, 3, 4}, {1, 3, 5}, {1, 4, 5}, {2, 3, 4}, {2, 3, 5}, {2, 4, 5}, {3, 4, 5}. Given one of these k-combinations, returns the next k-combination 	e.g. next({1, 4, 5}, 5) returns {2, 3, 4}
	*/
	public static int[] next(int[] T, int n) { 
		int[] result = new int[T.length];
		for (int i = 0; i < T.length; i++) {
			result[i] = T[i];
		}
		int i = 0;
		while (T.length - 1 - i > -1) {
			if (T[T.length - 1 - i] < n - i) {
				result[result.length - 1 - i]++;
				for (int j = result.length - i; j < T.length; j++) {
					result[j] = result[result.length - 1 - i] + 1 + (j - (result.length - i));
				}
				break;
			}
			i++;
		}
		return result;
	}
	
	/**
	* Enumerates all k-combinations of a set containing n elements in lexicographic order e.g. enum_n_choose_k(3, 5) returns {1, 2, 3}, {1, 	2, 4}, {1, 2, 5}, 	{1, 3, 4}, {1, 3, 5}, {1, 4, 5}, {2, 3, 4}, {2, 3, 5}, {2, 4, 5}, {3, 4, 5}
	*/
	public static int[][] enumNchooseK(int k, int n) {
		int[][] result = new int[nChooseK(k, n)][k];
		for (int j = 0; j < result[0].length; j++) {
			result[0][j] = j + 1;
		}
		for (int i = 1; i < result.length; i++) {
			int[] nextEnum = next(result[i - 1], n);
			for (int j = 0; j < result[i].length; j++) {
				result[i][j] = nextEnum[j];
			}
		}
		return result;
	}
	
	/**
	* Calculates the factorial of a number
	*/
	public static int fact(int n) {
		if (n == 0) return 1;
		int result = 1;
		for (int i = 1; i <= n; i++) {
			result *= i;
		}
		return result;
	}
	
	/**
	* Calculates n choose k
	*/
	public static int nChooseK(int k, int n) {
		if (k == 0) return 1;
		int result = 1;
		for (int i = 1; i < k + 1; i++) {
			result *= n - (k - i);
			result /= i;
		}
		return result;
	}
	
	/**
	* Checks if two numbers are permutations of one another
	*/
	public static boolean isPerm(int a, int b) {
		String aAsStr = Integer.toString(a); String bAsStr = Integer.toString(b);
		if (aAsStr.length() != bAsStr.length()) return false;
		int[] A = new int[10]; int[] B = new int[10];
		for (int i = 0; i < aAsStr.length(); i++) {
			A[Integer.parseInt(aAsStr.substring(i, i + 1))]++;
			B[Integer.parseInt(bAsStr.substring(i, i + 1))]++;
		}
		for (int i = 0; i < A.length; i++) {
			if (A[i] != B[i]) return false;
		}
		return true;
	}
	
	/**
	* Gets all permutations of a string
	*/
	public static String[] permutationsOfAString(String str) {
		if (str.length() == 2) {
			String[] result = new String[2];
			result[0] = str;
			result[1] = str.substring(1) + str.substring(0, 1);
			return result;
		}
		return allNewStrings(permutationsOfAString(removeCharAt(str, str.length() - 1)), str.substring(str.length() - 1)); 
	}
	
	/**
	* Prints an array of strings
	*/
	public static void showStrings(String[] T) {
		for (int i = 0; i < T.length; i++) {
			System.out.println(T[i]);
		}
	}
}

	