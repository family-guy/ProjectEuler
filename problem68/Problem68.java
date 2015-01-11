import java.util.*;
/**
 * Problem 68
 */

public class Problem68 {
	public static String max = "0";
	
	public static void main(String[] arg) {
		int n = 5; // n-gon ring
		// Get couples
		ArrayList<ArrayList<Integer>> couples = new ArrayList<ArrayList<Integer>>();
		for (int i = 1; i < 2 * n + 1; i++) {
			for (int j = i + 1; j < 2 * n + 1; j++) {
				ArrayList<Integer> coupleOne = new ArrayList<Integer>();
				ArrayList<Integer> coupleTwo = new ArrayList<Integer>();
				coupleOne.add(i); coupleOne.add(j);
				coupleTwo.add(j); coupleTwo.add(i);
				couples.add(coupleOne); couples.add(coupleTwo);
			}
		}
		boolean[] legal = new boolean[couples.size()];
		for (int i = 0; i < legal.length; i++) {
			legal[i] = true;
		}
		ArrayList<Integer> solution = new ArrayList<Integer>();
		solve(solution, couples, legal, n);
		System.out.println("The solution is " + max);
	}
	
	/**
	* Returns a solution set given a solution expressed in terms of indices of elements in an array of couples
	*/
	public static int[][] solutionSet(ArrayList<Integer> solution, ArrayList<ArrayList<Integer>> couples) {
		int[][] result = new int[solution.size()][3];
		for (int i = 0; i < solution.size(); i++) {
			for (int j = 0; j < couples.get(solution.get(i)).size(); j++) {
				result[i][j] = couples.get(solution.get(i)).get(j);
			}
			result[i][couples.get(i).size()] = couples.get(solution.get((i + 1) % solution.size())).get(1);
		}
		return result;
	}
	
	/**
	* Returns if a > b where a and b are integers represented as strings
	*/
	public static boolean greaterThan(String a, String b) {
		if (a.length() != b.length()) return a.length() > b.length();
		for (int i = 0; i < a.length(); i++) {
			int digitA = Integer.parseInt(a.substring(i, i + 1));
			int digitB = Integer.parseInt(b.substring(i, i + 1));
			if (digitA != digitB) return digitA > digitB;
		}
		return false;
	}
	
	/**
	* Converts a solution set into a string
	*/
	public static String solutionSetAsStr(int[][] solutionSet) {
		String result = "";
		for (int i = 0; i < solutionSet.length; i++) {
			for (int j = 0; j < solutionSet[i].length; j++) {
				result += Integer.toString(solutionSet[i][j]);
			}
		}
		return result;
	}
	
	/**
	* Recursive backtracking solver
	*/
	public static void solve(ArrayList<Integer> solution, ArrayList<ArrayList<Integer>> couples, boolean[] legal, int n) {
		if (solution.size() == n) {
			int total = couples.get(solution.get(0)).get(0) + couples.get(solution.get(0)).get(1) + couples.get(solution.get(1)).get(1);
			if (couples.get(solution.get(solution.size() - 1)).get(0) + couples.get(solution.get(solution.size() - 1)).get(1) + couples.get(solution.get(0)).get(1) == total) {
				int[][] solSet = solutionSet(solution, couples);
				String solSetAsStr = solutionSetAsStr(solSet);
				if (greaterThan(solSetAsStr, max) && solSetAsStr.length() == 16) max = solSetAsStr;
				return;
			}
		}
		else {
			if (solution.size() == 0) {
				for (int i = 0; i < couples.size(); i++) {
					solution.add(i);
					boolean[] sievedLegal = sieveCouples2(couples.get(i).get(0), couples, sieveCouples1(couples.get(i).get(0), couples.get(i).get(1), couples, legal));
					solve(copy(solution), couples, sievedLegal, n);
					solution.remove(solution.size() - 1);
				}
			}
			if (solution.size() == 1) {
				for (int i = 0; i < couples.size(); i++) {
					if (legal[i]) {
						if (couples.get(i).get(0) < couples.get(solution.get(0)).get(0) + couples.get(solution.get(0)).get(1)) {
							solution.add(i);
							boolean[] sievedLegal = sieveCouples1(couples.get(i).get(0), couples.get(i).get(1), couples, legal);
							solve(copy(solution), couples, sievedLegal, n);
							solution.remove(solution.size() - 1);
						}
					}
				}
			}
			if (solution.size() > 1) {
				for (int i = 0; i < couples.size(); i++) {
					if (legal[i]) {
						int total = couples.get(solution.get(0)).get(0) + couples.get(solution.get(0)).get(1) + couples.get(solution.get(1)).get(1);
						if (couples.get(i).get(1) + couples.get(solution.get(solution.size() - 1)).get(0) + couples.get(solution.get(solution.size() - 1)).get(1) == total) {
							solution.add(i);
							boolean[] sievedLegal = sieveCouples1(couples.get(i).get(0), couples.get(i).get(1), couples, legal);
							solve(copy(solution), couples, sievedLegal, n);
							solution.remove(solution.size() - 1);
						}
					}
				}
			}
		}
	}

	/**
	* Returns an updated array of booleans
	*/
	public static boolean[] sieveCouples1(int a, int b, ArrayList<ArrayList<Integer>> couples, boolean[] T) {
		boolean[] result = new boolean[T.length];
		for (int i = 0; i < result.length; i++) {
			if (couples.get(i).indexOf(a) > -1 || couples.get(i).indexOf(b) > -1) result[i] = false;
			else result[i] = T[i];
		}
		return result;
	}
	
	/**
	* Returns an updated array of booleans
	*/
	public static boolean[] sieveCouples2(int a, ArrayList<ArrayList<Integer>> couples, boolean[] T) {
		boolean[] result = new boolean[T.length];
		for (int i = 0; i < couples.size(); i++) {
			if (couples.get(i).get(0) <= a) result[i] = false;
			else result[i] = T[i];
		}
		return result;
	}
	
	/**
	* Deep copies a dynamic array 
	*/
	public static ArrayList<Integer> copy(ArrayList<Integer> T) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < T.size(); i++) {
			result.add(T.get(i));
		}
		return result;
	}
}