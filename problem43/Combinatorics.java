import java.util.*;
/*
 * This class contains combinatorics methods for strings and integers
 * fact: returns the factorial of a non-negative integer
 * permutationsOfAString: returns all permutations of a string
 * showStrings: prints an array of strings
*/

// private methods
// insertCharAt: takes a string and inserts a character (string of length 1) at the given index
// removeCharAt: takes a string and removes the character at the given index
// allNewStrings: takes a string (or an array of strings) and a character and returns all new strings that can be made by adding that character

public class Combinatorics {
	private static String insertCharAt(String str, String ch, int i) {		
		if (i == 0) return ch + str;
		if (i == str.length()) return str + ch;
		else return str.substring(0, i) + ch + str.substring(i, str.length());	
	}
	
	private static String removeCharAt(String str, int i) {
	      return str.substring(0, i) + str.substring(i + 1);
	   }
	
	private static String[] allNewStrings(String str, String ch) {
		String[] result = new String[str.length() + 1];
		for (int i = 0; i < str.length() + 1; i++) {
			result[i] = insertCharAt(str, ch, i);
		}
		return result;
	}
	
	private static String[] allNewStrings(String[] T, String ch) {
		int p = T.length; int q = T[0].length() + 1;
		String[] result = new String[p * q]; int j = -1;
		for (int i = 0; i < p * q; i++) {
			if (i % q == 0) j++;
			result[i] = allNewStrings(T[j], ch)[i % q];			
		}
		return result;
	}
	
	public static int fact(int n) {
		if (n == 0) return 1;
		int result = 1;
		for (int i = 1; i <= n; i++) {
			result *= i;
		}
		return result;
	}
	
	public static String[] permutationsOfAString(String str) {
		if (str.length() == 2) {
			String[] result = new String[2];
			result[0] = str;
			result[1] = str.substring(1) + str.substring(0, 1);
			return result;
		}
		return allNewStrings(permutationsOfAString(removeCharAt(str, str.length() - 1)), str.substring(str.length() - 1)); 
	}
	
	public static void showStrings(String[] T) {
		for (int i = 0; i < T.length; i++) {
			System.out.println(T[i]);
		}
	}
}

	