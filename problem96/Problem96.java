import java.util.*;
import java.io.*;
import java.math.*;

public class Problem96 {
	public static void main(String[] args) {
		int[][] grid = new int[9][9]; 
		FileData file1 = new FileData();	// open the text file containing the sudoku grid. Makes use of FileData class
		try {
			file1.open("/Users/guyking/ProjectEuler/problem96/p096_sudoku.txt", "read");
		}
		catch(IOException e) {
			throw new RuntimeException("Failed to open file", e); 
		}
		try {
			String str = ""; int row, column; int[][] T = new int[9][9]; int sum = 0;
			for (int k = 0; k < 50; k++) {
				str = file1.read();
				for (int i = 0; i < grid.length; i++) { 
					str = file1.read(); // parses the current line of the file
					for (int j = 0; j < grid[0].length; j++) {
						grid[i][j] = Character.digit(str.charAt(j), 10);
					} 
				}// data parsed. Grid is ready to be solved
				row = nextBlank(grid)[0]; column = nextBlank(grid)[1]; 
				solve(grid, row, column, T); sum += T[0][0] * 100 + T[0][1] * 10 + T[0][2];
				}
				System.out.println("The solution is " + sum);
			}
		catch(IOException e) {
			throw new RuntimeException("Failed to read file", e);
		}
	}

	public static boolean solve(int[][] g, int r, int c, int[][] T) { // r is the row of the current blank we are trying to fill, c the column. n is the number of the grid
		if (nextBlank(g)[0] == -1 && nextBlank(g)[1] == -1) { // a position of (-1, -1) means there are no more blanks. T enables retrieval of the solution grid
			for (int i = 0; i < T.length; i++) {
				for (int j = 0; j < T[0].length; j++) {
					T[i][j] = g[i][j];
				}
			}
			return true; // assumes a unique solution exists
		}
		else {
			for (int guess = 1; guess < 10; guess++) { // recursive backtracking template code is used (same as in 8 queens problem)
				if (isMoveLegal(guess, r, c, g)) {
					g[r][c] = guess;
					if (solve(constraintProp(g), nextBlank(constraintProp(g))[0], nextBlank(constraintProp(g))[1], T)) return true; // the partial solution led to a full solution
					else g[r][c] = 0; // the solution attempt failed so the recursive call above is popped off the stack and the for loop continues ie we try the next number up
					// we find again the previous values on the stack so the only thing that needs to be changed back is line38 
				}
			} 
			return false; // we have exited the for loop which means all numbers 1 to 9 tried failed. Triggers backtracking
		}
	}
	
	public static void showGrid(int[][] g) {
		for (int i = 0; i < g.length; i++) {
			for (int j = 0; j < g[0].length; j++) {
				System.out.print(g[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static boolean isNbLegal(int x, int r, int c, int[][] g) { // checks for row, column and square conflicts. The number being checked is x
		if (g[r][c] != 0) {
			if (x == g[r][c]) return true;
			else return false;
		}
		else {
			for (int i = 0; i < g.length; i++) {
				if (g[i][c] == x) return false;
			}
			for (int j = 0; j < g[0].length; j++) {
				if (g[r][j] == x) return false;
			}
			int sqIndex = (r / 3) * 3 + c / 3;
			for (int i = (sqIndex / 3) * 3; i < (sqIndex / 3) * 3 + 3; i++) {
				for (int j = (sqIndex % 3) * 3; j < (sqIndex % 3) * 3 + 3; j++) {
					if (g[i][j] == x) return false;
				}
			}
			return true;
		}
	}
	
	public static boolean isMoveLegal(int x, int r, int c, int[][] g) { // performs the depth first part of the search using constraint propagation
		if (!isNbLegal(x, r, c, g)) return false;
		else {
			int[][] deepCopyOfg = new int[g.length][g[0].length]; // we do not want to change g hence the deep copy
			for (int i = 0; i < g.length; i++) {
				for (int j = 0; j < g[0].length; j++) {
					deepCopyOfg[i][j] = g[i][j];
				}
			}
			deepCopyOfg[r][c] = x;
			constraintProp(deepCopyOfg);
			if (isGridLegal(deepCopyOfg)) return true;
			else return false;
		}
	}
	
	public static int nbOfLegalValues(int r, int c, int[][] g) {
		int sum = 0;
		for (int k = 1; k < 10; k++) {
			if (isNbLegal(k, r, c, g)) sum++;
		}
		return sum;
	}
	
	public static boolean isGridLegal(int[][] g) {
		for (int i = 0; i < g.length; i++) {
			for (int j = 0; j < g[0].length; j++) {
				if (nbOfLegalValues(i, j, g) == 0) return false;
			}
		}
		return true;
	}
	
	public static int[][] constraintProp(int[][] g) { // this function can be optimised as many potential constaint propagations are being ignored
		int[][] result = new int[g.length][g[0].length]; 
		for (int i = 0; i < g.length; i++) {
			for (int j = 0; j < g[0].length; j++) {
				result[i][j] = g[i][j];
			}
		}
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[0].length; j++) {
				if (result[i][j] == 0 && nbOfLegalValues(i, j, result) == 1) { 
					for (int k = 1; k < 10; k++) {
						if (isNbLegal(k, i, j, result)) result[i][j] = k;
					}
				}
			}
		}
		return result;
	}
	
	public static int[] nextBlank(int[][] g) { // returns the row and column of the next blank as a couple in the form of an array
		int[] result = new int[2]; result[0] = -1; result[1] = -1;
		for (int i = 0; i < g.length; i++) {
			for (int j = 0; j < g[0].length; j++) {
				if (g[i][j] == 0) {
					result[0] = i; result[1] = j; return result;
				}
			}
		}
		return result;
	}	
}
