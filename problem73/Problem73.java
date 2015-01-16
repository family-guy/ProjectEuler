import java.util.*;
/**
 * Problem 73
 */

public class Problem73 {
	public static void main(String[] str) {
		int n = 12000;
		int sum = 0;
		for (int i = 2; i <= n; i++) {
			int lowerBound = (int)Math.ceil((float)i / (float)3);
			int upperBound = (int)Math.floor((float)i / (float)2);
			for (int j = lowerBound; j <= upperBound; j++) {
				if (IntegerArithmetic.gcd(j, i) == 1) {
					if (!((j == 1 && i == 2) || (j == 1 && i == 3))) {
						sum++;
					}
				}
					
			}
		}
		System.out.println("The solution is " + sum);
	}
}