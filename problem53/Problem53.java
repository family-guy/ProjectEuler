import java.util.*;
/**
 * Problem 53
 */

public class Problem53 {
	public static void main(String[] arg) {
		int limit = 1000000; int sum = 0;
		for (int n = 1; n <= 100; n++) {
			sum += nbExceedingVal(n, limit);
		}
		System.out.println("The solution is " + sum);
	}
	
	/**
	* Returns the number of values of k, k in [0, ..., n], such that C^k_n > l
	*/
	public static int nbExceedingVal(int n, int l) {
		int k = 0; int m = n + 1;
		while (k <= n / 2) {
			if (Combinatorics.nChooseK(k, n) > l) break;
			k++;
		}
		return Math.max(m - 2 * k, 0);
	}
}
