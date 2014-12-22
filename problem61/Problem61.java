import java.util.*;

public class Problem61 {
	public static void main(String[] arg) {
		ArrayList<ArrayList<Integer>> figurates = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> triangle = new ArrayList<Integer>();
		ArrayList<Integer> square = new ArrayList<Integer>();
		ArrayList<Integer> pentagonal = new ArrayList<Integer>();
		ArrayList<Integer> hexagonal = new ArrayList<Integer>();
		ArrayList<Integer> heptagonal = new ArrayList<Integer>();
		ArrayList<Integer> octagonal = new ArrayList<Integer>();
		int i = 1;
		while (true) {
			int ithTermTri = i * (i + 1) / 2;
			int ithTermSq = i * i;
			int ithTermPent = i * (3 * i - 1) / 2;
			int ithTermHex = i * (2 * i - 1);
			int ithTermHept = i * (5 * i - 3) / 2;
			int ithTermOct = i * (3 * i - 2);
			if (ithTermTri >= 10000) break;
			if (ithTermTri >= 1000 && ithTermTri < 10000) triangle.add(ithTermTri);
			if (ithTermSq >= 1000 && ithTermSq < 10000) square.add(ithTermSq);
			if (ithTermPent >= 1000 && ithTermPent < 10000) pentagonal.add(ithTermPent);
			if (ithTermHex >= 1000 && ithTermHex < 10000) hexagonal.add(ithTermHex);
			if (ithTermHept >= 1000 && ithTermHept < 10000) heptagonal.add(ithTermHept);
			if (ithTermOct >= 1000 && ithTermOct < 10000) octagonal.add(ithTermOct);
			i++;
		}
		figurates.add(triangle); figurates.add(square); figurates.add(pentagonal); figurates.add(hexagonal); figurates.add(heptagonal); figurates.add(octagonal);
		ArrayList<Integer> unorderedSet = new ArrayList<Integer>();
		int nbFigurates = 6;
		boolean[] states = new boolean[nbFigurates];
		solve(unorderedSet, states, figurates, nbFigurates);
	}
	
	/*
	* Standard recursive backtrackng function
	*/
	public static boolean solve(ArrayList<Integer> unorderedSet, boolean[] states, ArrayList<ArrayList<Integer>> T, int nbFigurates) { 
		if (unorderedSet.size() == nbFigurates) {
			String lastTwoDigitsLastElt = Integer.toString(unorderedSet.get(nbFigurates - 1)).substring(2);
			String firstTwoDigitsFirstElt = Integer.toString(unorderedSet.get(0)).substring(0, 2);
			if (lastTwoDigitsLastElt.equals(firstTwoDigitsFirstElt)) {
				System.out.println("The ordered set of six cyclic 4-digit numbers is " + unorderedSet);
				int sum = 0;
				for (int i = 0; i < unorderedSet.size(); i++) {
					sum += unorderedSet.get(i);
				}
				System.out.println("The solution is " + sum);
				return true;
			}
		}
		for (int i = 0; i < states.length; i++) {
			if (!states[i]) {
				for (int j = 0; j < T.get(i).size(); j++) {
					if (unorderedSet.size() == 0) {
						unorderedSet.add(T.get(i).get(j));
						states[i] = true;
						if (solve(unorderedSet, states, T, nbFigurates)) return true;
						unorderedSet.remove(unorderedSet.size() - 1);
						states[i] = false;
					}
					else {
						int a = unorderedSet.get(unorderedSet.size() - 1);
						String lastTwoDigitsA = Integer.toString(a).substring(2);
						int b = T.get(i).get(j);
						String firstTwoDigitsB = Integer.toString(b).substring(0, 2);
						if (lastTwoDigitsA.equals(firstTwoDigitsB)) {
							unorderedSet.add(b);
							states[i] = true;
							if (solve(unorderedSet, states, T, nbFigurates)) return true;
							unorderedSet.remove(unorderedSet.size() - 1);
							states[i] = false;
						} 
					}
				}
			}
		}
		return false;
	}
}