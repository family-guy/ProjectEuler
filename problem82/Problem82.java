import java.util.*;
import java.io.*;
import java.util.regex.Pattern;

public class Problem82 {
	public static void main(String[] arg) {
		FileData file = new FileData();
		try {
			file.open("/Users/guyking/ProjectEuler/problem82/p082_matrix.txt", "read");
		}
		catch (IOException e) {
			throw new RuntimeException("Failed to open file", e);
		}
		int[][] matrix = new int[80][80];
		int row = 0;
		try {
			while (true) {
				String str = file.read();
				if (str == null) break;
				String[] parts = str.split(Pattern.quote(","));
				for (int j = 0; j < parts.length; j++) {
					matrix[row][j] = Integer.parseInt(parts[j]);
				}
				row++;
			}
		}
		catch (IOException e) {
			throw new RuntimeException("Failed to read file", e);
		}
		int n = matrix.length;
		int m = matrix[0].length;
		int[] mins = new int[n];
		int[] currentCol = getColumn(matrix, 0);
		int[] nextCol = getColumn(matrix, 1);
		for (int i = 1; i < m; i++) {
			nextCol = getColumn(matrix, i);
			currentCol = getMins(currentCol, nextCol);
		}
		System.out.println("The solution is " + minArray(currentCol));	
	}
	
	/**
	* Returns minimum values for all subsequent iterations
	*/
	public static int[] getMins(int[] currentCol, int[] nextCol) {
		int[] result = new int[currentCol.length];
		for (int i = 0; i < result.length; i++) {
			int[] mins = new int[result.length];
			for (int k = 0; k < result.length; k++) {
				if (k <= i) {
					int[] subArrayOne = getSubArray(currentCol, k, i);
					int[] subArrayTwo = getSubArray(nextCol, k, i);
					mins[k] = minPathSum(subArrayOne, subArrayTwo);
				}
				else {
					int[] subArrayOne = getSubArray(currentCol, i, k);
					int[] subArrayTwo = getSubArray(nextCol, i, k);
					mins[k] = minPathSum(reverseArray(subArrayOne), reverseArray(subArrayTwo));
				}		
			}
			result[i] = minArray(mins);
		}
		return result;
	}
	
	/**
	* Returns the reverse of an array
	*/
	public static int[] reverseArray(int[] T) {
		int[] result = new int[T.length];
		for (int i = 0; i < T.length; i++) {
			result[i] = T[T.length - 1 - i];
		}
		return result;
	}
	
	/**
	* Returns the minimum of an array
	*/
	public static int minArray(int[] T) {
		int result = T[0];
		for (int i = 1; i < T.length; i++) {
			if (T[i] < result) result = T[i];
		}
		return result;
	}

	/**
	* Returns a column from a matrix
	*/
	public static int[] getColumn(int[][] T, int colIndex) {
		int[] result = new int[T.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = T[i][colIndex];
		}
		return result;
	}
	
	/**
	* Takes two arrays of integers, the first being less than or equal in length than the second, and returns the minumum sum path starting at the first element of the first array and finishing at the last element of the second array e.g. minPathSum([2, 4, 3], [4, 1, 2, 7]) returns 16
	*/
	public static int minPathSum(int[] T, int[] U) {
		int result = T[0];
		for (int i = 0; i < U.length; i++) {
			result += U[i];
		}
		int sum = 0;
		for (int k = 1; k < T.length; k++) {
			for (int i = 0; i < k + 1; i++) {
				sum += T[i];
			}
			for (int i = k; i < U.length; i++) {
				sum += U[i];
			}
			if (sum < result) result = sum;
			sum = 0;
		}
		return result;
	}
	
	/* 
	* Returns a subset of an array according to two indices a, b where b >= a e.g. subsetArray([1, 5, 2, 6], 2, 3) returns [2, 6]
	*/
	public static int[] getSubArray(int[] T, int a, int b) {
		int[] result = new int[b - a + 1];
		for (int i = 0; i < result.length; i++) {
			result[i] = T[a + i];
		}
		return result;
	}
	
}