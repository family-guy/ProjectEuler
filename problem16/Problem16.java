import java.util.*;
/* Problem 16
 * multiplication: takes a dynamic array of numbers, multiplies by an integer and returns the result in a new dynamic array e.g. multiplication([1, 2, 3], 10) returns [1, 2, 3, 0]
 * reverseInPlace: reverses a dynamic array
 * sumOfArray: returns the sum of the numbers in a dynamic array
*/

public class Problem16 {
	public static void main(String[] arg) {
		ArrayList<Integer> dynArray = new ArrayList<Integer>(); dynArray.add(1);
		for (int i = 0; i < 1000; i++) {
			dynArray = multiplication(dynArray, 2);
		}
		System.out.println(sumOfArrayList(dynArray));	
	}

	public static ArrayList<Integer> multiplication(ArrayList<Integer> dynArray, int n) {
		ArrayList<Integer> result = new ArrayList<Integer>(); int carryOver = 0; int p = 0;
		for (int i = 0; i < dynArray.size() - 1; i++) { // there is a -1 as the first element will be treated as a separate case
			p = dynArray.get(dynArray.size() - 1 - i) * n + carryOver; result.add(p % 10); carryOver = p / 10;
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
	
	public static int sumOfArrayList(ArrayList<Integer> dynArray) {
		int sum = 0;
		for (int i = 0; i < dynArray.size(); i++) {
			sum += dynArray.get(i);
		}
		return sum;
	}
}