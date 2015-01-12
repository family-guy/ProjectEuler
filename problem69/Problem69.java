import java.util.*;
/**
 * Problem 69
 */

public class Problem69 {
	public static void main(String[] arg) {
		float maxRatio = 0;
		int index = 0;
		for (int i = 2; i <= 1000000; i++) {
			float ratio = (float)i / (float)phi(i);
			if (ratio > maxRatio) {
				maxRatio = ratio;
				index = i;
			} 
		}
		System.out.println("The solution is " + index);
	}

	/**
	* Returns phi n where n is less than or equal to 1000000
	*/
	public static int phi(int n) {
		int k = (int)Math.sqrt(1000000);
		boolean[] prime = IntegerArithmetic.sieveEratosthenes(k);
		ArrayList<ArrayList<Integer>> primeDec = IntegerArithmetic.primeDecomp(n, prime);
		int result = 1;
		for (int i = 0; i < primeDec.size(); i++) {
			result *= (primeDec.get(i).get(0) - 1) * (int)Math.pow(primeDec.get(i).get(0), primeDec.get(i).get(1) - 1);
		}
		return result;
	}
}