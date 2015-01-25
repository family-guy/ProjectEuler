import java.util.*;
/**
 * Problem 76
 * http://mathworld.wolfram.com/PartitionFunctionP.html
 */

public class Problem76 {
	public static void main(String[] arg) {
		ArrayList<Integer> prevVals = new ArrayList<Integer>();
		prevVals.add(1);
		prevVals.add(1);
		int n = 100;
		while (prevVals.size() - 1 < n) {
			prevVals.add(nextValOfPn(prevVals));
		}
		System.out.println("The solution is " + (prevVals.get(prevVals.size() - 1) - 1));
	}
	
	/* 
	* Takes an array of previous values P(n - 1), ..., P(0) and returns P(n), n >= 2
	*/
	public static int nextValOfPn(ArrayList<Integer> prevValsOfPn) {
		int sumA = 0; int sumB = 0;
		int n = prevValsOfPn.size();
		for (int k = 1; k <= n; k++) {
			int termA = n - functA(k);
			if (termA < 0) break;
			sumA += Math.pow(-1, k + 1) * prevValsOfPn.get(termA);
		}
		for (int k = 1; k <= n; k++) {
			int termB = n - functB(k);
			if (termB < 0) break;
			sumB += Math.pow(-1, k + 1) * prevValsOfPn.get(termB);
		}
		return sumA + sumB;
	}
	
	public static int functA(int k) {
		return k * (3 * k - 1) / 2;
	}
	
	public static int functB(int k) {
		return k * (3 * k + 1) / 2;
	}
}