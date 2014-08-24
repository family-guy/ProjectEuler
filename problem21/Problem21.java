import java.util.*;

public class Problem21
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
	
	public static void main(String[] str)
	{
		int i;
		int sum = 0;
		for (i = 2; i < 10000; i++)
		{
			if (sumOfPropDiv(i) != i && sumOfPropDiv(sumOfPropDiv(i)) == i && sumOfPropDiv(i) < 10000)
			{
				sum += i + sumOfPropDiv(i);
			}
		}
		System.out.println("The solution is " + sum / 2); // each pair is counted twice in sum
	}
}