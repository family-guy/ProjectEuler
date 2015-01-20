import java.util.*;
/**
 * Problem 75
 * http://en.wikipedia.org/wiki/Pythagorean_triple#Generating_a_triple
 */

public class Problem75 {
	public static void main(String[] arg) {
		int L = 1500000;
		ArrayList<ArrayList<Integer>> triples = pythagPrimTrip(L / 2);
		int[] freq = new int[L + 1];
		for (int i = 0; i < triples.size(); i++) {
			int a = triples.get(i).get(0);
			int b = triples.get(i).get(1);
			int c = triples.get(i).get(2);
			for (int k = 1; k * (a + b + c) <= L; k++) {
				freq[k * (a + b + c)]++;
			}
		}
		int count = 0;
		for (int i = 0; i < freq.length; i++) {
			if (freq[i] == 1) count++;
		}
		System.out.println("The solution is " + count);
	}
	
	/*
	* Returns all primitive Pythagorean triples with hypoteneuse less than c
	*/
	public static ArrayList<ArrayList<Integer>> pythagPrimTrip(int c) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> pairs = coprimePairs(c);
		for (int i = 0; i < pairs.size(); i++) {
			ArrayList<Integer> pythagTrip = new ArrayList<Integer>();
			int m = pairs.get(i).get(1);
			int n = pairs.get(i).get(0);
			pythagTrip.add(m * m - n * n);
			pythagTrip.add(2 * m * n);
			pythagTrip.add(m * m + n * n);
			result.add(pythagTrip);
		}
		return result; 
	}
	

	/*
	* Returns all distinct pairs of positive integers (p, q), p < q, such that p^2 + q^2 <= n, (p, q) are coprime and q - p is odd
	*/
	public static ArrayList<ArrayList<Integer>> coprimePairs(int n) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		for (int i = 1; i * i < n; i++) {
			for (int j = i + 1; j * j < n; j++) {
				if (i * i + j * j <= n) {
					if ((j - i) % 2 == 1) {
						if (IntegerArithmetic.gcd(i, j) == 1) {
							ArrayList<Integer> pair = new ArrayList<Integer>();
							pair.add(i); pair.add(j);
							result.add(pair);
						}
					}
					
				}
			}
		}
		return result;
	}
}