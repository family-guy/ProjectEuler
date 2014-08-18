import java.util.*;

public class Problem12
{
	// function that returns the number of positive divisors of a given positive integer
	public static int nbDivisors(int n)
	{
		int i; int count = 0;
		for (i = 1; i * i < n + 1; i++)
		{
			if(n % i == 0)
			count++;
		}	
		if (i * i == n)
		return 2 * count - 1;
		else
		return 2 * count;
	}
	// we use the fact that the difference between each member of the sequence increases by 1 each time
	public static void main(String[] arg)
	{		
		int i = 2; int sum = 1;
		while (true)
		{
			if (nbDivisors(sum) > 500)
			break;
			sum += i;
			i++;
		}
		System.out.println(sum);
	}	
}