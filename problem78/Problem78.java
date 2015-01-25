import java.util.*;
/**
 * Problem 78
 * http://mathworld.wolfram.com/PartitionFunctionP.html
 */

public class Problem78 {
	public static void main(String[] arg) {
		ArrayList<Integer> prevVals = new ArrayList<Integer>();
		prevVals.add(1);
		prevVals.add(1);
		while (true) {
			int nextVal = nextValOfPn(prevVals);
			if (nextVal == 0) {
				System.out.println("The solution is " + prevVals.size());
				break;
			}
			prevVals.add(nextVal);
		}		
	}
	
	/* 
	* Takes an array of previous values P(n - 1), ..., P(0) and returns P(n), n >= 2. All values modulo one million
	*/
	public static int nextValOfPn(ArrayList<Integer> prevValsOfPn) {
		int sumA = 0; int sumB = 0;
		int m = 1000000;
		int n = prevValsOfPn.size();
		for (int k = 1; k <= n; k++) {
			int termA = n - functA(k);
			if (termA < 0) break;
			sumA += Math.pow(-1, k + 1) * (prevValsOfPn.get(termA) % m);
		}
		for (int k = 1; k <= n; k++) {
			int termB = n - functB(k);
			if (termB < 0) break;
			sumB += Math.pow(-1, k + 1) * (prevValsOfPn.get(termB) % m);
		}
		sumA = mod(sumA, m); sumB = mod(sumB, m);
		return mod(sumA + sumB, m);
	}
	
	public static int functA(int k) {
		return k * (3 * k - 1) / 2;
	}
	
	public static int functB(int k) {
		return k * (3 * k + 1) / 2;
	}
	
	/* 
	* Returns a bar in Z / bZ
	*/	
	public static int mod(int a, int b) { 
		if (a >= 0 || a % b == 0) return a % b;
		int x = a % b;
		while (true) {
			x += b;
			if (x >= 0) return x;
		}
	}
}