import java.util.*;
/* Problem 40
 * digit: takes a positive integer n and returns the n-th digit of the fractional part of the irrational decimal fraction
*/

public class Problem40 {
	public static void main(String[] arg) {
		int product = 1;
		for (int i = 0; i < 7; i++) {
			product *= digit((int)Math.pow(10, i));
		}
		System.out.println("The solution is " + product);
	}
	
	public static int digit(int n) {
		int i = 1; int count = 0;
		String iAsStr = ""; int j = 0;
		while (true) {
			iAsStr = Integer.toString(i);
			for (j = 0; j < iAsStr.length(); j++) {
				count++;
				if (count == n) break;
			}
			if (count == n) break;
			i++;
		}
		return Character.digit(iAsStr.charAt(j), 10);
	}
}