import java.util.*;
/* Problem 45
 * isIn: returns whether a given integer is in a given array where the array is sorted
*/

public class Problem45 {
	public static void main(String[] arg) {
		long[] hex = new long[50000001];
		for (int i = 0; i < hex.length; i++) {
			hex[i] = (long)i * (2 * (long)i - 1);
		}
		long l = 0; long k = 0; int i = 166;
		while (true) {
			l = (long)i;
			k = l * (3 * l - 1) / 2;
			if (isIn(hex, k)) break;
			i++;
		}
		System.out.println("The solution is " + k); // every hexagonal number is a triangle number
	}
	
	public static boolean isIn(long[] T, long t) {	
		int a =  Arrays.binarySearch(T, t);
		return a > 0;
	}
}