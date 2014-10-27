import java.util.*; 
/* Problem 52 */

public class Problem52 {
	public static void main(String[] arg) {
		int i = 1; int n = 6;
		int k;
		while (true) {
			for (k = 2; k < n; k++) {
				if (!Combinatorics.isPerm(i, i * k)) break;
			}
			if (Combinatorics.isPerm(i, i * k) && k == n) {
				System.out.println("The solution is " + i);
				break;
			}
			i++;
		}
	}
}