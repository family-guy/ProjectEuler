import java.util.*;
/**
 * Problem 71
 */

public class Problem71 {
	public static void main(String[] arg) {
		int n = 1000000;
		int[] currentBest = {2, 5};
		for (int i = 3; i <= n; i++) {
			if (i != 7) {
				int x = (int)(((float)3 / (float)7) * (float)i);
				if (IntegerArithmetic.gcd(x, i) == 1) {
					int[] frac = new int[2];
					frac[0] = x;
					frac[1] = i;
					if (greaterThan(frac, currentBest)) {
						currentBest[0] = frac[0];
						currentBest[1] = frac[1];
					}
				}
			}
		}
		System.out.println("The fraction immediately to the left of 3/7 is " + currentBest[0] + "/" + currentBest[1] + ". The solution is " + currentBest[0]);
	}
	
	/*
	* Returns whether frac1 is greater than frac 2
	*/
	public static boolean greaterThan(int[] frac1, int[] frac2) {
		return frac1[0] * frac2[1] > frac1[1] * frac2[0];
	}
}