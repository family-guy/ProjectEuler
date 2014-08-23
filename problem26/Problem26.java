/* The main idea behind this solution is the long division algorithm */
import java.util.*;

public class Problem26
{
	// function that given two integers n and m returns the smallest upper bound M of n such that M = m * 10^p, p a non negative integer
	public static int smallestUpperBound(int n, int m)
	{
		while (true)
		{
			if (m >= n)
			{
				break;
			}
			else
			{
				m *= 10;
			}
		}
		return m;
	}
	
	// function that given a positive integer d returns in a list the dividends obtained whilst calculating 1/d by long division. 
	// the list stops when d divides evenly a dividend or a dividend repeats itself. If d divides evenly a dividend, returns the empty list
	public static ArrayList<Integer> dividendsLongDiv(int d)
	{
		int dividend = 1;
		dividend = smallestUpperBound(d, dividend % d);
		ArrayList<Integer> result = new ArrayList<Integer>();
		// first entry into array
		result.add(dividend);
		while (true)
		{
			if (dividend % d == 0) // case of even division
			{
				result.clear();
				break;
			}		
			if (result.contains(smallestUpperBound(d, dividend % d))) // case of a repeated dividend
			{
				result.add(smallestUpperBound(d, dividend % d)); // adding the repeated dividend to the list will be useful later
				break;
			}		
			else // case where we continue to apply the algorithm
			{
				dividend = smallestUpperBound(d, dividend % d);
				result.add(dividend);
			}
		}
		return result;		 
	}
	
	// function that given a positive integer d returns the length of the recurring cycle in the decimal representation of 1/d eg 1/3 returns one. 
	// if there is no recurring cycle eg 1/2, returns -1
	public static int lengthOfRecurringCycle(int d)
	{
		ArrayList<Integer> list = dividendsLongDiv(d);
		if (list.size() == 0)
		{
			return -1;
		}
		else
		{
			int lastIndex = list.size() - 1;
			int lastValue = list.get(lastIndex);
			return lastIndex - list.indexOf(lastValue);
		}		
	}
	
	public static void main(String[] arg)
	{
		int i; int maxLength = 0; int maxIndex = 0;
		for (i = 2; i < 1000; i++)
		{
			if (lengthOfRecurringCycle(i) > maxLength)
			{
				maxLength = lengthOfRecurringCycle(i);
				maxIndex = i;
			}
		}
		System.out.println("The solution is " + maxIndex + " as 1/" + maxIndex + " contains a recurring cycle of length " + maxLength);
	}
}