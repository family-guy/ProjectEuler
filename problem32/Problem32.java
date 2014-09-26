import java.util.*;
/* Problem 32
 * convertIntToArray: returns a given integer as a dynamic array e.g. convertIntToArray(123) = [1, 2, 3]
 * isPandigital: returns whether a dynamic array of integers, where each integer is in {0, 1, ..., 9}, is a permutation of {1, ..., 9}
 * concat: concatenates the second dynamic array to the first dynamic array
 * sumSolutions: returns the sum of products made from a n digit multiplier and a m digit multiplicand
*/

public class Problem32 {
	public static void main(String[] arg) {
		System.out.println("The solution is " + (sumSolutions(2, 3) + sumSolutions(1, 4)));
	}
	
	public static int sumSolutions(int n, int m) {
		ArrayList<Integer> solutions = new ArrayList<Integer>(); 
		int sum = 0; 
		for (int i = (int)Math.pow(10, n - 1); i < (int)Math.pow(10, n); i++) {
			for (int j = (int)Math.pow(10, m - 1); j < (int)Math.pow(10, m); j++) {
				ArrayList<Integer> multiplier = convertIntToArray(i); ArrayList<Integer> multiplicand = convertIntToArray(j);
				ArrayList<Integer> product = convertIntToArray(i * j); concat(product, multiplier); concat(product, multiplicand);
				if (isPandigital(product) && solutions.indexOf(i * j) == -1) {
					solutions.add(i * j); sum += i * j;
				}
			}
		}
		return sum;
	}

	public static boolean isPandigital(ArrayList<Integer> T) {
		if (T.size() != 9 || T.indexOf(0) > -1) return false;
		for (int i = 1; i < 10; i++) {
			if (T.indexOf(i) != T.lastIndexOf(i)) return false;
		}
		return true;
	}
	
	public static ArrayList<Integer> convertIntToArray(int n) {
		ArrayList<Integer> result = new ArrayList<Integer>();	
		int divisor = 1; int dividend = n;
		while (true) {
			if (divisor * 10 > n) break;
			divisor *= 10;
		}
		while (divisor > 0) {
			result.add(dividend / divisor);
			dividend %= divisor;
			divisor /= 10;
		}
		return result;
	}
	
	public static void concat(ArrayList<Integer> T, ArrayList<Integer> U) {
		for (int i = 0; i < U.size(); i++) {
			T.add(U.get(i));
		}
	}
}

