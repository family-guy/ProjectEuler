import java.util.*;
import java.io.*;

public class Problem11
{
	// function that given a 2d array of integers and two indices k <= l, returns the product of adjacent entries between those indices going HORIZONTALLY
	public static int productOfHorizontalEntriesInArray(int[][] T, int k, int l, int i) // we go across the i-th line
	{
		int j; int product = 1;
		for (j = k; j < l + 1; j++)
		product *= T[i][j];
		return product;
	}	
	// function that given a 2d array of integers and two indices k <= l, returns the product of adjacent entries between those indices going VERTICALLY
	public static int productOfVerticalEntriesInArray(int[][] T, int k, int l, int j) // we go across the j-th column
	{
		int i; int product = 1;
		for (i = k; i < l + 1; i++)
		product *= T[i][j];
		return product;
	}
	/* for the adjacent diagonal entries we notice the following
	1. There are two directions, going up (when moving from left to right) and going down (when moving from left to right)
	- the latter is like the diagonal in a square matrix 
	2. let n be a natural number. we have n^2 = 2(1 + ... + n-1) + n which can be interpreted geometrically with the diagonals in a grid
	3. for each direction we are going to extract all the diagonals that are "maximal" and put each one in an array
	4. for each array we find the maximum product by four adjacent entries
	5. we take the max of these maximums thus giving a maximum for each direction
	6. we take the max of these two maximums which is the max of all diagonals 
	*/
	// function that given an array returns the max product of k adjacent values in that array. Returns -1 if k > length of the array
	public static int maxProductOfAdjElts(int T[], int k)
	{
		if(k > T.length)
		return -1;
		else
		{
			int max = 0;
			int i = 0; int product = 1; int j = 0;
			while (i + k - 1 < T.length)
			{
				while (j < k)
				{
					product *= T[i + j];
					j++;
				}
				j = 0;
				if (product > max)
				max = product;
				i++; product = 1;
			}	
			return max;
		}
	}
	// main function	
	public static void main(String[] arg)
	{
		// read the file with the 20 x 20 grid and put the numbers into a 2d array
		int i; int j; 
		int[][] grid = new int[20][20];
		FileData file = new FileData();
		try
		{
			file.open("/Users/guyking/ProjectEuler/problem11/Problem11.txt", "read");
		}
		catch(IOException e)
		{
			throw new RuntimeException("Failed to open file", e);
		}
		try 
		{
			String str = "";
			int k = 0;
			for (i = 0; i < 20; i++)
			{
				str = file.read();
				for (j = 0; j < 20; j++)
				{
					grid[i][j] = Integer.parseInt(str.substring(k, k + 2)); // substring(0, 1) returns the first character of the string, string(n, n) returns the empty string
					k += 3;
				}
				k = 0; 
			}
		}
		catch(IOException e)
		{
			throw new RuntimeException("Failed to read file", e);
		}
		// declare the different max variables
		int maxLines, maxCol, maxDiagUp, maxDiagDown, maxGlobal;
		maxLines = 0; maxCol = 0; maxDiagUp = 0; maxDiagDown = 0; maxGlobal = 0;
		// get maxLines
		for (i = 0; i < 20; i++)
		{
			for (j = 0; j < 17; j++)
			{
				if (productOfHorizontalEntriesInArray(grid, j, j + 3, i) > maxLines)
				maxLines = productOfHorizontalEntriesInArray(grid, j, j + 3, i);
			}
		}
		// update maxGlobal
		if (maxGlobal < maxLines)
		maxGlobal = maxLines;
		// get maxCol
		for (j = 0; j < 20; j++)
		{
			for (i = 0; i < 17; i++)
			{
				if (productOfVerticalEntriesInArray(grid, i, i + 3, j) > maxCol)
				maxCol = productOfVerticalEntriesInArray(grid, i, i + 3, j);
			}
		}	
		// update maxGlobal
		if (maxGlobal < maxCol)
		maxGlobal = maxCol;
		// get maxDiagUp ie upwards diagonals (going from left to right)
		// we start at the top left hand corner, move down to the bottom and then right to the end
		// there are 2n - 1 iterations ie 39
		i = 0;
		j = 0; int k = 0; int l = 0;
		while (i < 19)
		{
			int T[] = new int[i + 1];
			l = i; // allows l to increment with i
			while (l > -1)
			{
				T[k] = grid[l][k];
				l--;
				k++;
			}
			i++; k = 0;
			// the array we created T is now filled in and ready to be used so we update the value of maxDiagUp
			if (maxProductOfAdjElts(T, 4) > maxDiagUp)
			maxDiagUp = maxProductOfAdjElts(T, 4);
		}
		k = 19; l = 0;
		while (i < 39) 
		{			
			int T[] = new int[39 - i];
			while (j < 39 - i)
			{
				T[j] = grid[k][l + j];
				j++;
				k--;
			}
			i++; k = 19; j = 0; l++;
			// the array we created T is now filled in and ready to be used so we update the value of maxDiagUp
			if (maxProductOfAdjElts(T, 4) > maxDiagUp)
			maxDiagUp = maxProductOfAdjElts(T, 4);
		}
		// update maxGlobal
		if (maxGlobal < maxDiagUp)
		maxGlobal = maxDiagUp;	
		// get maxDiagDown ie downwards diagonals (going from left to right)
		// we start at the top right hand corner, move down to the bottom and then left to the end
		i = 0;
		j = 0; k = 19; l = 0;
		while (i < 19)
		{
			int T[] = new int[i + 1];
			l = i; // allows l to increment with i
			while (l > -1)
			{
				T[19 - k] = grid[l][k];
				l--;
				k--;
			}
			i++; k = 19;
			// the array we created T is now filled in and ready to be used so we update the value of maxDiagUp
			if (maxProductOfAdjElts(T, 4) > maxDiagDown)
			maxDiagDown = maxProductOfAdjElts(T, 4);
		}
		k = 19; l = 0; j = 19;
		while (i < 39) 
		{			
			int T[] = new int[39 - i];
			while (j > l - 1)
			{
				T[19 - j] = grid[k][j - l];
				j--;
				k--;
			}
			i++; k = 19; j = 19; l++;
			// the array we created T is now filled in and ready to be used so we update the value of maxDiagUp
			if (maxProductOfAdjElts(T, 4) > maxDiagDown)
			maxDiagDown = maxProductOfAdjElts(T, 4);
		}
		// update maxGlobal
		if (maxGlobal < maxDiagDown)
		maxGlobal = maxDiagDown;
		// print the result
		System.out.println("The solution is " + maxGlobal);	
	}			
}