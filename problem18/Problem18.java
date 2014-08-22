import java.util.*;
import java.lang.Math.*;
import java.io.*;

public class Problem18
{
	// function that given a positive integer n returns the largest integer p s.t. 2^p <= n
	public static int maxPowerOfTwo(int n)
	{
		int i = 1;
		while (true)
		{
			if (Math.pow(2, i) > n)
			{
				break;
			}
			i++;
		}
		return i - 1;
	}
	
	// function that returns the binary number of an integer given eg 5 = 2^2 + 2^0 returns 101
	public static long binaryNb(int n)
	{
		long sum = 0;
		while (n - Math.pow(2, maxPowerOfTwo(n)) != 0)
		{
			sum += Math.pow(10, maxPowerOfTwo(n));
			n -= Math.pow(2, maxPowerOfTwo(n));
		}
		return sum += Math.pow(10, maxPowerOfTwo(n));
	}
	
	// function that given an integer returns an array of its digits eg 1234 returns the array {1, 2, 3, 4}
	public static int[] getDigits(long n)
	{
		long m = n; // copy of n that will be used in the second part of the function
		// get the number of digits in the number
		int nbDigits = 1;
		while (true)
		{
			if (n / 10 == 0)
			{
				break;
			}
			nbDigits++;
			n /= 10;
		}
		// create and fill in the array to return
		int[] result = new int[nbDigits];
		int i;
		for (i = 0; i < result.length; i++)
		{
			result[result.length - 1 - i] = (int)(m % 10);
			m /= 10;
		}
		return result;
	}
	
	// function that given a positive integer n returns a 2d array of all binary numbers of length n
	public static int[][] allBinaryNbsArray(int n)
	{
		int result[][] = new int[(int)Math.pow(2, n)][n];
		// we populate the first half of the array with all those binary numbers starting with 1
		int i; int j;
		for (i = 0; i < ((int)Math.pow(2, n)) / 2; i++)
		{
			for (j = 0; j < n; j++)
			{
				result[i][j] = getDigits(binaryNb((int)Math.pow(2, n - 1) + i))[j];
			}
		}
		// we populate the second half of the array with all those binary numbers starting with zero (we take the first half and copy the entries changing each time the first element from 1 to 0)
		for (i = ((int)Math.pow(2, n)) / 2; i < (int)Math.pow(2, n); i++)
		{
			for (j = 0; j < n; j++)
			{
				if (j == 0)
				{
					result[i][j] = 0;
				}
				else
				{
					result[i][j] = result[i - ((int)Math.pow(2, n)) / 2][j];
				}
			}
		}
		return result;
	}
	
	public static void main(String[] arg)
	{
		// we are going to put the data into a 15x15 array
		int[][] dataArray = new int[15][15];
		int i; int j;
		FileData file = new FileData();
		try 
		{
			file.open("/Users/guyking/ProjectEuler/problem18/Problem18.txt", "read");
		}
		catch(IOException e) 
		{ 
    		throw new RuntimeException("Failed to open file", e); 
  		}
		try
		{
			String str = "";
			for (i = 0; i < 15; i++)
			{
				str = file.read();
				for (j = 0; j < i + 1; j++)
				{
					dataArray[i][j] = Integer.parseInt(str.substring(3 * j, 3 * j + 2));
				}
			}	
		}	
  		catch(IOException e)
  		{
  			throw new RuntimeException("Failed to read file", e);
  		} // end of putting in the data. The data is stored in dataArray
		// there are 2^14 possible paths. We are going to calculate the sum for each one
		// create the 2d array with the paths
		int[][] paths = allBinaryNbsArray(14); // 0 corresponds to move left, 1 to move right
		int k, l; k = 0; l = 0; int max = 0; int sum = 75; // each path starts with 75
		for (i = 0; i < paths.length; i++)
		{
			for (j = 0; j < paths[0].length; j++)
			{
				if (paths[i][j] == 0)
				{
					k++;
					sum += dataArray[k][l];
				}
				else
				{
					k++; l++;
					sum += dataArray[k][l];
				}
			}
			// We have calculated the sum. We compare it to the running max
			if (sum > max)
			{
				max = sum;
			}
			sum = 75; k = 0; l = 0; // reset everything to get back to the top of the triangle
		}
		System.out.println("The solution is " + max);
	}	
}