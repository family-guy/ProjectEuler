import java.util.*;
/* Problem 30
 * sumOfPowersOfDigits: returns the sum described e.g. sumOfPowersOfDigits(123, 2) returns 1^2 + 2^2 + 3^2
*/

public class Problem30 {
	public static void main(String[] arg) {
		int sum = 0;
		for (int i = 2; i < 1000000; i++) {
			if (sumOfPowersOfDigits(i, 5) == i) sum += i;
		}
		System.out.println("The solution is " + sum);
	}
	
	public static int sumOfPowersOfDigits(int n, int exp) {
		int sum = 0;
		String nAsStr = Integer.toString(n);
		for (int i = 0; i < nAsStr.length(); i++) {
			int digit = Integer.parseInt(nAsStr.substring(i, i + 1));
			sum += Math.pow(digit, exp);
		}
		return sum;
	}
}