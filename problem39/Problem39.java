import java.util.*;
/* Problem 39
 * isPythag: returns whether three positive integers make up a Pythagorean triple
*/

public class Problem39 {
	public static void main(String[] arg) {
		int[] P = new int[1001];
		for (int i = 1; i < 998; i++) {
			for (int j = i + 1; j < 999; j++) {
				for (int k = j + 1; k < 1000; k++) {
					int p = i + j + k;
					if (p <= 1000 && isPythag(i, j, k)) {
						P[p]++;
					}
				}
			}
		}
		int maxIndex = 0;
		for (int i = 0; i < P.length; i++) {
			if (P[maxIndex] < P[i]) maxIndex = i;
		}
		System.out.println("The value of p for which the number of solutions is maximised is " + maxIndex + " which has " + P[maxIndex] + " solutions");
	}
	
	public static boolean isPythag(int a, int b, int c) {
		return (a * a + b * b == c * c);
	}
}