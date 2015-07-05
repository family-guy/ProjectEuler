import java.util.*;
/**
 * Problem 6
 */

public class Problem6 {
	public static void main(String[] arg) {
		int n = 100;
		int sumSquares = 0;
		int sum = 0;
		for (int i = 0; i < n + 1; i++) {
			sumSquares += i * i;
			sum += i;
		}
		int squareSum = sum * sum;
		System.out.println("The solution is " + (squareSum - sumSquares));
	}
}