import java.util.*;
/**
 * Problem 74
 */

public class Problem74 {
	public static Map<Integer, Integer> loops = new HashMap<Integer, Integer>();
	
	public static void main(String[] arg) {
		loops.put(1, 0);
		loops.put(2, 0);
		loops.put(145, 0);
		loops.put(40585, 0);
		loops.put(169, 2); loops.put(363601, 2); loops.put(1454, 2);
		loops.put(871, 1); loops.put(45361, 1);
		loops.put(872, 1); loops.put(45362, 1);
		int sum = 0;
		for (int i = 1; i < 1000000; i++) {
			if (nbNonRepeatTerms(i) == 60) sum++;
		}
		System.out.println("The solution is " + sum);
	}
	
	/**
	* Returns a given integer as a dynamic array e.g. convertIntToArray(123) = [1, 2, 3]
	*/
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
	
	/**
	* Takes a dynamic array of positive integers and returns the sum of their factorials
	*/
	public static int sumFact(ArrayList<Integer> T) {
		int sum = 0;
		for (int i = 0; i < T.size(); i++) {
			sum += Combinatorics.fact(T.get(i));
		}
		return sum;
	}
	
	/**
	* Returns the number of non-repeating terms for a given integer
	*/
	public static int nbNonRepeatTerms(int n) {
		int result = 1;
		int currentVal = n;
		while (true) {
			if (loops.get(currentVal) != null) return result + loops.get(currentVal);
			result++;
			currentVal = sumFact(convertIntToArray(currentVal));
		}
	}
}