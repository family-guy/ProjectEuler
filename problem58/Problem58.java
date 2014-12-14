import java.util.*;
/**
 * Problem 58
 */

public class Problem58 {
	public static void main(String[] arg) {
		long currentVal = 1; int nbPrimes = 0;
		int count = 1; float ratio = 1;
		int i = 2;
		while (true) {
			for (int j = 0; j < 4; j++) {
				currentVal += i;
				count++;
				if (IntegerArithmetic.isPrime(currentVal)) nbPrimes++;
				if (j == 3) ratio = (float)nbPrimes / (float)count;
			}
			if (ratio < 0.1) {
				System.out.println("The solution is " + (i + 1));
				break;
			} 
			i += 2;
		}
	}
}