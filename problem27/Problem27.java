import java.util.*;

// hint: consider the case where n = 0
public class Problem27 {
	public static void main(String[] arg) {
		boolean[] T = IntegerArithmetic.sieveEratosthenes(999);
		ArrayList<Integer> primes = new ArrayList<Integer>();
		for (int i = 0; i < T.length; i++) {
			if (T[i] == true) primes.add(i);
		}
		int max = 0; int productOfCoefficients = 0;
		for (int i = -999; i < 1000; i++) {
			for (int j = 0; j < primes.size(); j++) {
				if (maxNbPrimes(i, primes.get(j)) > max) {
					max = maxNbPrimes(i, primes.get(j)); 
					productOfCoefficients = i * primes.get(j);
				}
			}
		}
		System.out.println("The solution is " + productOfCoefficients);
	}
	
	public static int maxNbPrimes(int a, int b) {
		int i = 0;
		while (true) {
			if (!IntegerArithmetic.isPrime(i * i + a * i + b)) break;
			i++;
		}
		return i;
	}
}