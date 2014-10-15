import java.util.*;
/* Problem 41
 * permutationsOfAString: returns all permutations of a string in an array
 * isPrime: returns whether a positive integer is prime
*/

public class Problem41 {
	public static void main(String[] arg) {
		String[] T = {"123456789", "12345678", "1234567", "123456", "12345", "1234", "123", "12"};
		int nbPanPrime = 0; int max = 0;
		for (int i = 0; i < T.length; i++) {
			String[] permutations = Combinatorics.permutationsOfAString(T[i]);
			for (int j = 0; j < permutations.length; j++) {
				int p = Integer.parseInt(permutations[j]);
				if (IntegerArithmetic.isPrime(p)) {
					nbPanPrime++;
					if (p > max) max = p;
				}
			}
			if (nbPanPrime > 0) break;
		}
		System.out.println("The solution is " + max);
	}
}

