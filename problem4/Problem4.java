/* assume we have a function isPalindrome
we go through numbers from 100 to 999 like this : (100, 100), (100, 101), ..., (100, 999), (101, 100), (101, 101), ..., (101, 999)
for each couple we take the product
the first couple that produces a palindrome becomes the max
ie we set max = 0 and do if product > max, max = product*/

import java.util.*;

public class Problem4
{
	// function that given a non negative integer returns the number of digits
	public static int nbDigits(int n, int a, int b) // n is the integer, a = 10, b = 1
	{
		if (n / a == 0)
		{
			return b;
		}
		else
		{
			return nbDigits(n, a * 10, b + 1);
		}
	}
	
	// function that given a non negative integer returns an array filled with its digits eg 123 returns the array [1 2 3]
	public static int [] arrayDigits(int n)
	{
		int p = nbDigits(n, 10, 1);
		int []result = new int[p];
		int i = 0;
		int q = 1; // to calculate the powers of 10 we use a running product
		while (i < p)
		{
			q *= 10;
			result[p - 1 - i] = (n % q) / (q / 10);
			i++;
		}	
		return result;
	}
	
	// function that checks if two arrays of integers are equal
	public static boolean arraysAreEqual(int []T, int []U)
	{
		if (T.length != U.length)
		{
			return false;
		}
		else
		{
			int i = 0;
			while (i < T.length)
			{
				if (T[i] != U[i])
				{
					return false;
				}
				i++;
			}
			return true;
		}
	}
	
	// function that reverses an array of integers
	public static int [] reverseArray(int []T)
	{
		int []result = new int[T.length];
		int i = 0;
		while (i < T.length)
		{
			result[i] = T[T.length - 1 - i];
			i++;
		}
		return result;
	}
	
	public static boolean isPalindrome(int n)
	{
		if (arraysAreEqual(arrayDigits(n), reverseArray(arrayDigits(n))))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static void main(String[] arg)
	{
		int i = 100; int j = 100; int max = 0; 
		// indices for the max
		int k = 0, l = 0;
		while (i < 1000)
		{
			while (j < 1000)
			{
				if (isPalindrome(i * j) && i * j > max)
				{
					max = i * j;
					k = i; l = j;
				}
				j++;
			}
			j = 0;
			i++;
		}
		System.out.println("The solution is " + max + " = " + k + " x " + l);
	}
			
}