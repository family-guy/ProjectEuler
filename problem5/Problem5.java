/* write a function that says whether a number is divisible by 1, 2, ..., 20
Go through 1, 2, ... and test each time using the function created. Return the 
first number that tests positive */

import java.util.*;
/*
public class Problem5
{
	public static boolean isDivisible(int n)
	{
		int i;
		for (i = 1; i < 11; i++)
		{
			if (n % i != 0)
			{
				return false;
			}
		}
		return true;
	}
	
	public static int recursiveSolution(int n)
	{
		if (isDivisible(n))
		{
			return n;
		}
		else
		{
			return recursiveSolution(n + 1);
		}
	}

	public static void main(String[] arg)
	{
		System.out.println("The solution is " + recursiveSolution(10)); // 0 < n <= 9 means not n not divisible by 10
	}
}
*/

// the above solution works for 10 but cannot be extended to 20 as too many recursive calls, no more memory on the stack. 
public class Problem5
{
	public static boolean isDivisible(int n)
	{
		int i;
		for (i = 1; i < 21; i++)
		{
			if (n % i != 0)
			{
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] arg)
	{/*
		int i = 0;
		do 
		{
			i += 2520; // we can use this increment because we know 2520 is the smallest number divisible by 1, ..., 10
		}
		while (isDivisible(i) == false);*/ // do while loops are rarely used thus equivalents are generally preferred
		
		int i = 2520;
		while (true)
		{
			if (isDivisible(i))
			break;
			i += 2520;
		}	
		System.out.println("The solution is " + i);
	}
}