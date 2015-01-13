import java.util.*;
/**
 * Problem 70
 */

public class Problem70 {
	public static void main(String[] str) {
		int upperBound = 10000000;
		int k = (int)Math.sqrt(upperBound);
		boolean[] prime = IntegerArithmetic.sieveEratosthenes(k);
		int index = 87109;
		float minRatio = (float)index / (float)phi(index, prime);
		for (int n = 2; n < upperBound; n++) {
			int phiOfn = phi(n, prime);
			if (Combinatorics.isPerm(phiOfn, n)) {
				float ratio = (float)n / (float)phiOfn;
				if (ratio < minRatio) {
					minRatio = ratio;
					index = n;
				} 
			}
		}
		System.out.println("The solution is " + index);
	}
	
	/**
	* Returns phi n
	*/
	public static int phi(int n, boolean[] prime) {
		ArrayList<ArrayList<Integer>> primeDec = IntegerArithmetic.primeDecomp(n, prime);
		int result = 1;
		for (int i = 0; i < primeDec.size(); i++) {
			result *= (primeDec.get(i).get(0) - 1) * (int)Math.pow(primeDec.get(i).get(0), primeDec.get(i).get(1) - 1);
		}
		return result;
	}
}