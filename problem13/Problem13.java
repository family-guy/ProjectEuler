/* We simply use the algorithm addition learnt at primary school where we start from the right and sum each column, 
generating a "carry over" integer each time. */

import java.util.*;
import java.io.*;

public class Problem13
{	
	public static void main(String[] arg)
	{
		FileData file = new FileData();
		try
		{
			file.open("/Users/guyking/ProjectEuler/problem13/Problem13.txt", "read");
		}
		catch(IOException e) 
		{
			throw new RuntimeException("Failed to open file", e);
		}
		// we are going to put the data in the file in a 2d array
		int[][] nbsToSum = new int[100][50];
		int i; int j;
		try
		{
			String str = "";
			for (i = 0; i < 100; i++)
			{
				str = file.read();
				for (j = 0; j < 50; j++)
				{
					nbsToSum[i][j] = Character.digit(str.charAt(j), 10);
				}
			}
		}
		catch(IOException e)
		{
			throw new RuntimeException("Failed to read file", e);
		}
		ArrayList<Integer>  list = new ArrayList<Integer>(); // this arraylist is going to store the digits of the solution. list is empty
		int sumCol = 0; 
		int carryOver = 0; // eg 1 + 4 + 3 + 6 gives a carry over of 1
		for (j = 49; j > 0; j--) // !!!! make sure to reverse the order of the loop as we start from the last column !!!! 
		{
			for (i = 0; i < 100; i++)
			{
				sumCol += nbsToSum[i][j]; // this gives the sum of the j-th column
			}
			// we need to treat the sum obtained to get the information wanted
			sumCol += carryOver;
			list.add(sumCol % 10);
			carryOver = sumCol / 10;
			sumCol = 0; // dont forget to reset the sum
		}
		// the sum of the last column needs to be treated as a separate case
		for (i = 0; i < 100; i++)
		{
			sumCol += nbsToSum[i][j];
		}
		sumCol += carryOver;
		list.add(sumCol);
		// get the number of digits of the last element of the list
		int lastElt = list.get(list.size() - 1);
		int nbDigitsLastElt = 1;
		while (true)
		{
			if(lastElt / 10 == 0)
				break;
			else
			{
				nbDigitsLastElt++;
				lastElt /= 10;
			}	
		}
		lastElt = list.get(list.size() - 1); // assign back the correct value
		// we proceed depending on if nbDigitsLastElt is greater than or less than 10
		if (nbDigitsLastElt > 10)
		{
			int difference = nbDigitsLastElt - 10;
			// get the right power of 10
			int x = 1;
			for (i = 0; i < difference; i++)
			{
				x *= 10;
			}
			// print solution
			System.out.println("The solution is " + lastElt / x);
		}
		if (nbDigitsLastElt == 10)
		{
			// print solution
			System.out.println("The solution is " + lastElt);
		}
		else
		{
			int difference = 10 - nbDigitsLastElt;
			// print solution
			System.out.print("The solution is " + lastElt);
			for (i = 0; i < difference; i++)
			{
				System.out.print(list.get(list.size() - 1 - 1 - i));
			}
		}		
	}
}