import java.util.*;
/* Problem 29
 * multiplication: takes a dynamic array of numbers, multiplies by an integer and returns the result in a new dynamic array e.g. multiplication([1, 2, 3], 10) returns [1, 2, 3, 0]
 * reverseInPlace: reverses a dynamic array
 * exponent: exponent(a, b) returns a^b e.g. exponent(2, 9) returns [5, 1, 2]
 * isEqual: checks whether two dynamic arrays have the same values e.g. isEqual([5, 1, 2], [5, 1, 2]) returns true
 * isIn: returns whether a dynamic array of dynamic arrays contains a dynamic array given
*/

public class Problem29 {
	public static void main(String[] arg) {
		int lowerBound = 2; int upperBound = 100; int nbOfDistinctTerms = 0;
		ArrayList<ArrayList<Integer>> prevValues = new ArrayList<ArrayList<Integer>>();
		for (int p = lowerBound; p <= upperBound; p++) {
			for (int q = lowerBound; q <= upperBound; q++) {
				if (!isIn(prevValues, exponent(p, q))) {
					nbOfDistinctTerms++; prevValues.add(exponent(p, q));
				}
			}
		}
		System.out.println("The solution is " + nbOfDistinctTerms);
	}
	
	public static ArrayList<Integer> multiplication(ArrayList<Integer> dynArray, int n) {
		ArrayList<Integer> result = new ArrayList<Integer>(); int carryOver = 0; int p = 0;
		for (int i = 0; i < dynArray.size() - 1; i++) { // there is a -1 as the first element will be treated as a separate case
			p = dynArray.get(dynArray.size() - 1 - i) * n + carryOver;
			result.add(p % 10); carryOver = p / 10;
		}
		p = dynArray.get(0) * n + carryOver; // treat the case of the first element
		while (true) {
			result.add(p % 10);
			if (p / 10 == 0) break;
			else p /= 10;
		}	
		reverseInPlace(result); return result;
	}

	public static void reverseInPlace(ArrayList<Integer> dynArray) {
		ArrayList<Integer> copy = new ArrayList<Integer>();
		for (int i = 0; i < dynArray.size(); i++) {
			copy.add(dynArray.get(i));
		}
		dynArray.clear();
		for (int i = 0; i < copy.size(); i++) {
			dynArray.add(copy.get(copy.size() - 1 - i));
		}
	}
	
	public static ArrayList<Integer> exponent(int a, int b) {
		ArrayList<Integer> result = new ArrayList<Integer>(); result.add(1);
		for (int i = 0; i < b; i++) {
			result = multiplication(result, a);
		}
		return result;
	}
	
	public static boolean isEqual(ArrayList<Integer> d1, ArrayList<Integer> d2) {
		if (d1.size() != d2.size()) return false;
		for (int i = 0; i < d1.size(); i++) {
			if (d1.get(i) != d2.get(i)) return false;
		}
		return true;	
	}
	
	public static boolean isIn(ArrayList<ArrayList<Integer>> d1, ArrayList<Integer> d2) {
		for (int i = 0; i < d1.size(); i++) {
			if (isEqual(d1.get(i), d2)) return true;
		}
		return false;
	}
}