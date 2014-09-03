/* This solution is very inefficient. Took 28.24 seconds on a 1.4 GHz Intel Core i5 */
import java.util.*;

public class Problem23
{
	// function d(n) ie returns sum of proper divisors of n
	public static int sumOfPropDiv(int n)
	{
		int sum = 0;
		int i;
		for (i = 2; i * i <= n; i++)
		{
			if (n % i == 0)
			{
				if (i != n / i)
				{
					sum += i + n / i;
				}
				else
				{
					sum += i;
				}
			}
		}
		return sum + 1; // 1 is always a proper divisor
	}
	
	public static boolean isAbundant(int n)
	{
		if (sumOfPropDiv(n) > n)
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
		ArrayList<Integer> listOfAbundants = new ArrayList<Integer>();
		int i;
		for (i = 12; i < 28123; i++)
		{
			if (isAbundant(i))
			{
				listOfAbundants.add(i);
			}
		} // listOfAbundants appears correct
		int j = 0; int sum = 0;
		for (i = 1; i < 24; i++)
		{
			sum += i; // because the smallest number that can be written as the sum of two abundant numbers is 24
		} 
		for (i = 24; i < 28124; i++)
		{
			while (true)
			{
				if (listOfAbundants.contains(i - listOfAbundants.get(j))) // i can be written as the sum of two abundant numbers
				{
					break;
				}
				if (listOfAbundants.get(j) * 2 >= i) // i cannot be written as the sum of two abundant numbers
				{
					sum += i;
					break;
				}
				j++;
			}
			j = 0;
		}
		System.out.println("The solution is " + sum);	
	}		
}