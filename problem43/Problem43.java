import java.util.*;
/* Problem 43
 * hasProp: takes a 0 to 9 pandigital number and returns whether it has the property given in the question
*/

public class Problem43 {
	public static void main(String[] arg) {
		String[] permutations = Combinatorics.permutationsOfAString("0123456789");
		long sum = 0;
		for (int i = 0; i < permutations.length; i++) {
			if (hasProp(permutations[i])) {
				long n = Long.parseLong(permutations[i]);
				System.out.println(n);
				sum += n;
			}
		}
		System.out.println("The solution is " + sum);
	}
	
	public static boolean hasProp(String s, int[] T) {
		for (int i = 0; i < T.length; i++) {
			String subStr = s.substring(i + 1, i + 4);
			int subStrAsInt = Integer.parseInt(subStr);
			if (subStrAsInt % T[i] != 0) return false;
		}
		return true;
	}
	
	public static boolean hasProp(String s) {
		int[] T = {2, 3, 5, 7, 11, 13, 17};
		return hasProp(s, T);
	}
}