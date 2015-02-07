import java.util.*;
import java.io.*;
/*
 * Problem 79
 */

public class Problem79 {
	public static void main(String[] arg) {
		String filePath = "/Users/guyking/ProjectEuler/problem79/p079_keylog.txt";
		ArrayList<ArrayList<String>> loginAttempts = processFile(filePath);
		ArrayList<String> chars = uniqueElts(loginAttempts);
		ArrayList<String> partialSol = new ArrayList<String>();
		System.out.println("The solution is " + solve(partialSol, loginAttempts, chars));
	}
	
	/*
	* Returns elements of T without duplicates
	*/
	public static ArrayList<String> uniqueElts(ArrayList<ArrayList<String>> T) {
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < T.size(); i++) {
			for (int j = 0; j < T.get(i).size(); j++) {
				String str = T.get(i).get(j);
				if (result.indexOf(str) == -1) result.add(str);
			}
		}
		return result;
	}
	
	/*
	* Parses the data into a two dimensional array
	*/
	public static ArrayList<ArrayList<String>> processFile(String filePath) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		FileData file = new FileData();
		try {
			file.open(filePath, "read");
		}
		catch (IOException e) {
			throw new RuntimeException("Failed to open file", e);
		}
		try {
			while (true) {
				String str = file.read();
				if (str == null) break;
				ArrayList<String> row = new ArrayList<String>();
				for (int i = 0; i < str.length(); i++) {
					String substr = str.substring(i, i + 1);
					row.add(substr);
				}
				result.add(row);
			}
			return result;
		}
		catch (IOException e) {
			throw new RuntimeException("Failed to read file", e);
		}
	}
	
	/*
	* Returns the solution
	*/
	public static ArrayList<String> solve(ArrayList<String> partialSol, ArrayList<ArrayList<String>> loginAttempts, ArrayList<String> chars) {
		if (partialSol.size() == chars.size() - 1) {
			for (int i = 0; i < chars.size(); i++) {
				if (partialSol.indexOf(chars.get(i)) == -1) {
					partialSol.add(chars.get(i));
					return partialSol;
				}	 
			}
		}
		for (int i = 0; i < chars.size(); i++) {
			if (partialSol.indexOf(chars.get(i)) == -1) {
				int count = 0;
				for (int p = 0; p < loginAttempts.size(); p++) {
					int index = loginAttempts.get(p).indexOf(chars.get(i));
					if (index > 0) {
						int q = 0;
						for (q = 0; q < index; q++) {
							if (partialSol.indexOf(loginAttempts.get(p).get(q)) == -1) break;
						}
						if (q < index) count++;
					}
				}
				if (count == 0) {
					partialSol.add(chars.get(i));
					return solve(partialSol, loginAttempts, chars);
				}
			}
		}
		return partialSol; // should never get here
	}
}

