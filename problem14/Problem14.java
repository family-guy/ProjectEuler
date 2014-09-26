import java.util.*;

public class Problem14
{
	// this function takes a starting number n and returns the length of the sequence. a is the array of past values initialised at zero 
	public static int nbOfTermsCollatzWithMemory(int n, int[] a)
	{
		int nbOfTerms = 1;
		int startingNb = n; // stores the value of the starting number
		long m = (long) n; // n is going to become very large before returning to 1 so we force it to be of type long (otherwise n as an int will start taking strange values eg < 0)
		while (m != 1)
		{
			if (startingNb > m) // m has decreased to a value that we have already calculated the length for. We can deduce the length of the sequence
			{
				return nbOfTerms + a[(int) m] - 1;
			}
			else
			{
				if (m % 2 == 0)
				{
					m /= 2;
					nbOfTerms++;
				}
				else
				{
					m = 3 * m + 1;
					nbOfTerms++;
				}
			}	
		}
		return nbOfTerms;
	}
	
	public static void main(String[] arg)
	{
		int i;
		// we initiate the array of past values
		int[] arrayOfPastValues = new int[1000000];
		for (i = 0; i < 1000000; i++)
		{
			arrayOfPastValues[i] = 0;
		}
		// the main loop, i is the starting number of the sequence
		int lengthOfSequence = 0; // to store the newly generated value	
		for (i = 1; i < 1000000; i++)
		{
			// we assign the length of the sequence in arrayOfPastValues

			// we update arrayOfPastValues
			arrayOfPastValues[i] = nbOfTermsCollatzWithMemory(i, arrayOfPastValues);
		}
		// find the starting number that yields the longest sequence
		int max = 0; int maxIndex = 0;
		for (i = 1; i < 1000000; i++)
		{
			if (arrayOfPastValues[i] > max)
			{
				max = arrayOfPastValues[i];
				maxIndex = i;
			}
		}
		// print the solution
		System.out.print("The starting number, under one million, that produces the longest chain (" + max + " terms) is " + maxIndex);	
	}
}