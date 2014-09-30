import java.util.*;
/* Problem 34
 * convertIntToArray: returns a given integer as a dynamic array e.g. convertIntToArray(123) = [1, 2, 3]
 * fact: returns the factorial of a number
 * sumFact: takes a dynamic array of positive integers and returns the sum of their factorials
*/

public class Problem34 {
	public static void main(String[] arg) {
		int maxIndex = 10000000; // numbers with 8 or more digits cannot be equal to the sum of the factorial of their digits
		int sum = 0;
		for (int i = 10; i < maxIndex; i++) {
			if (i == sumFact(convertIntToArray(i))) sum += i;
		}
		System.out.println("The solution is " + sum);
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
	
	public static int fact(int n) {
		if (n == 0) return 1;
		int result = 1;
		for (int i = 1; i <= n; i++) {
			result *= i;
		}
		return result;
	}
	
	public static int sumFact(ArrayList<Integer> T) {
		int sum = 0;
		for (int i = 0; i < T.size(); i++) {
			sum += fact(T.get(i));
		}
		return sum;
	}
}