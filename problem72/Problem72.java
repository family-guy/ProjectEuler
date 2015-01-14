import java.util.*;

public class Problem72 {
	public static void main(String[] arg) {
		int upperBound = 1000000;
		int k = (int)Math.sqrt(upperBound);
		boolean[] prime = IntegerArithmetic.sieveEratosthenes(k);
		long sum = 0;
		for (int d = 2; d <= upperBound; d++) {
			sum += (long)phi(d, prime);
		}
		System.out.println("The solution is " + sum);
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