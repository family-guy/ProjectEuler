import java.util.*;
import java.io.*;

public class Problem67 {
	public static void main(String[] args) {
		int[][] triangle = new int[100][100]; int i; int j; 
		FileData file = new FileData();
		try {
			file.open("/Users/guyking/ProjectEuler/problem67/p067_triangle.txt", "read");
		}
		catch(IOException e) { 
			throw new RuntimeException("Failed to open file", e); 
		}
		try {
			String str = "";
			for (i = 0; i < triangle.length; i++) {
				str = file.read();
				for (j = 0; j < i + 1; j++) {
					triangle[i][j] = Integer.parseInt(str.substring(3 * j, 3 * j + 2));
				}
			}	
		}	
		catch(IOException e) {
			throw new RuntimeException("Failed to read file", e);
		} 
		System.out.println("The solution is " + getMaxSum(triangle[triangle.length - 1], triangle)[0]);
	}
	
	// T plays the role of the bottom row. Initial value of T is the last row of tri (the 100 row triangle). If T is of length 2 then we are done
	public static int[] getMaxSum(int[] T, int[][] tri) { 
		int[] result = new int[T.length - 1];
		for (int i = 0; i < T.length - 1; i++) {
			result[i] = tri[T.length - 2][i] + Math.max(T[i], T[i + 1]);
		}
		if (T.length == 2) {
			return result;
		}
		else {
			return getMaxSum(result, tri);		 
		}		
	}
}