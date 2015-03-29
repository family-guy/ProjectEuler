import java.util.*;
import java.io.*;
import java.util.regex.Pattern;
/*
 * Problem 83
 * Dijkstra's algorithm
 */

public class Problem83 {
	public static void main(String[] arg) {
		FileData file = new FileData();
		try {
			file.open("/Users/guyking/ProjectEuler/problem83/p083_matrix.txt", "read");
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
		int[][] minDistances = new int[matrix.length][matrix[0].length];
		for (int i = 0; i < minDistances.length; i++) {
			for (int j = 0; j < minDistances[i].length; j++) {
				minDistances[i][j] = -1;
			}
		}
		boolean[][] visited = new boolean[matrix.length][matrix[0].length];
		for (int i = 0; i < visited.length; i++) {
			for (int j = 0; j < visited[i].length; j++) {
				visited[i][j] = false;
			}
		}
		int currPosRow = 0;
		int currPosCol = 0;
		minDistances[0][0] = matrix[0][0];
		visited[0][0] = true;
		while (true) {
			if (currPosRow - 1 > -1) {
				if (!visited[currPosRow - 1][currPosCol]) {
					if (minDistances[currPosRow - 1][currPosCol] == -1) {
						minDistances[currPosRow - 1][currPosCol] = minDistances[currPosRow][currPosCol] + matrix[currPosRow - 1][currPosCol];
					}
					else if (minDistances[currPosRow][currPosCol] + matrix[currPosRow - 1][currPosCol] < minDistances[currPosRow - 1][currPosCol]) {
						minDistances[currPosRow - 1][currPosCol] = minDistances[currPosRow][currPosCol] + matrix[currPosRow - 1][currPosCol];
					}
				}
			}
			if (currPosRow + 1 < matrix.length) {
				if (!visited[currPosRow + 1][currPosCol]) {
					if (minDistances[currPosRow + 1][currPosCol] == -1) {
						minDistances[currPosRow + 1][currPosCol] = minDistances[currPosRow][currPosCol] + matrix[currPosRow + 1][currPosCol];
					}
					else if (minDistances[currPosRow][currPosCol] + matrix[currPosRow + 1][currPosCol] < minDistances[currPosRow + 1][currPosCol]) {
						minDistances[currPosRow + 1][currPosCol] = minDistances[currPosRow][currPosCol] + matrix[currPosRow + 1][currPosCol];
					}
				}
			} 
			if (currPosCol - 1 > -1) {
				if (!visited[currPosRow][currPosCol - 1]) {
					if (minDistances[currPosRow][currPosCol - 1] == -1) {
						minDistances[currPosRow][currPosCol - 1] = minDistances[currPosRow][currPosCol] + matrix[currPosRow][currPosCol - 1];
					}
					else if (minDistances[currPosRow][currPosCol] + matrix[currPosRow][currPosCol - 1] < minDistances[currPosRow][currPosCol - 1]) {
						minDistances[currPosRow][currPosCol - 1] = minDistances[currPosRow][currPosCol] + matrix[currPosRow][currPosCol - 1];
					}
				}
			}
			if (currPosCol + 1 < matrix[0].length) {
				if (!visited[currPosRow][currPosCol + 1]) {
					if (minDistances[currPosRow][currPosCol + 1] == -1) {
						minDistances[currPosRow][currPosCol + 1] = minDistances[currPosRow][currPosCol] + matrix[currPosRow][currPosCol + 1];
					}
					else if (minDistances[currPosRow][currPosCol] + matrix[currPosRow][currPosCol + 1] < minDistances[currPosRow][currPosCol + 1]) {
						minDistances[currPosRow][currPosCol + 1] = minDistances[currPosRow][currPosCol] + matrix[currPosRow][currPosCol + 1];
					}
				}
			}
			int min = 0;
			int minRow = 0;
			int minCol = 0;
			for (int i = 0; i < visited.length; i++) {
				for (int j = 0; j < visited[i].length; j++) {
					if (!visited[i][j] && minDistances[i][j] > -1) {
						min = minDistances[i][j];
						minRow = i;
						minCol = j;
						break;
					}
				}
				if (min > 0) break;
			}
			for (int i = 0; i < visited.length; i++) {
				for (int j = 0; j < visited[i].length; j++) {
					if (!visited[i][j] && minDistances[i][j] > -1) {
						if (minDistances[i][j] < min) {
							min = minDistances[i][j];
							minRow = i;
							minCol = j;
						}
					}
				}
			}
			visited[currPosRow][currPosCol] = true;
			currPosRow = minRow;
			currPosCol = minCol;
			if (currPosRow == matrix.length - 1 && currPosCol == matrix[0].length - 1) {
				System.out.println("The solution is " + minDistances[currPosRow][currPosCol]);
				break;
			}
		}
	}
}