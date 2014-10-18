import java.util.*;
/* Problem 44
 * areSumAndDiffPen: returns whether a pair of integers has the property in the question
*/

public class Problem44 {
	public static void main(String[] arg) {
		int n = 500000000;
		boolean[] isPent = new boolean[n]; // default values are false
		for (int i = 1; i * (3 * i - 1) / 2 < isPent.length ; i++) {
			isPent[i * (3 * i - 1) / 2] = true;
		}
		int upperLimit = 10000; // upper limit is arbitrary and we assume that the minimum found with this upper limit is the minimum that we are looking for
		ArrayList<Integer> diffsOfPairs = new ArrayList<Integer>();
		for (int i = 1; i < upperLimit; i++) {
			for (int j = i + 1; j < upperLimit + 1; j++) {
				if(areSumAndDiffPen(i, j, isPent)) {
					diffsOfPairs.add(j * (3 * j - 1) / 2 - i * (3 * i - 1) / 2);
				}
			}
		}
		int minDiff = diffsOfPairs.get(0);
		for (int i = 0; i < diffsOfPairs.size(); i++) {
			if (minDiff > diffsOfPairs.get(i)) minDiff = diffsOfPairs.get(i);
		}
		System.out.println("The solution is " + minDiff);
	}
	
	public static boolean areSumAndDiffPen(int p, int q, boolean[] T) {
		int pthPentNb = p * (3 * p - 1) / 2;
		int qthPentNb = q * (3 * q - 1) / 2;
		return T[pthPentNb + qthPentNb] && T[qthPentNb - pthPentNb];
	}
}