import java.util.*;
/* Problem 47
 * primeFacts: takes an integer >= 2 and returns its prime factors in a dynamic array e.g 90 returns [2, 3, 5]
 * getConsecNbs: takes two positive integers a and b and returns in an array b consecutive numbers starting from a e.g getConsecNbs(4, 3) returns {4, 5, 6}
 * getSmallestNb: given a natural number n, returns the smallest number y such that y, y + 1, ..., y + (n - 1) each have n distinct prime factors
*/

public class Problem47 {
	public static void main(String[] arg) {
		int n = 500000000; 
		boolean[] primes = IntegerArithmetic.sieveEratosthenes(n);
		System.out.println("The solution is " + getSmallestNb(4, primes));
	}
	
	public static ArrayList<Integer> primeFacts(int n, boolean[] P) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		int i = 0;
		while (true) {
			while (!P[i]) i++; // get the next prime
			if (n % i == 0) {
				result.add(i);
				while (n % i == 0) n /= i;
			}
			if (n == 1) break;
			i++;
		}
		return result;
	}
	
	public static int[] getConsecNbs(int a, int b) {
		int[] result = new int[b];
		for (int i = 0; i < result.length; i++) {
			result[i] = a;
			a++;
		}
		return result;
	}
	
	public static int getSmallestNb(int n, boolean[] P) {
		int i = 2; int j = 0;
		while (true) {
			int[] consec = getConsecNbs(i, n);
			while (primeFacts(consec[j], P).size() == n && j < consec.length - 1) j++;
			if (j == consec.length - 1 && primeFacts(consec[j], P).size() == n) break;
			i++; j = 0;
		}
		return i;
	}
}