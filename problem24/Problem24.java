import java.util.*;

public class Problem24
{
	public static int factorial(int n)
	{
		if (n == 0 || n == 1)
		{
			return 1;
		}
		else
		{
			int product = 1;
			int i;
			for (i = n; i > 1; i--)
			{
				product *= i;
			}
			return product;
		}		
	}
	
	public static void main(String[] arg)
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
		ArrayList<Integer> result = new ArrayList<Integer>();
		int i; 
		for (i = 0; i < 10; i++)
		{
			list.add(i);
		}
		int k = 999999; // gives the (k+1)-th lexicographic permutation
		for (i = 10; i > 1; i--)
		{
			k %= factorial(i);
			k /= factorial(i - 1);
			result.add(list.get(k));
			list.remove(k);
			k = 999999;
		}
		result.add(list.get(0));
		// print the result not in the form of an array list
		for (i = 0; i < result.size(); i++)
		{
			System.out.print(result.get(i));
		}
		System.out.println();
	}
}