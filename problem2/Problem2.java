import java.util.*;

public class Problem2
{
	// function that returns the nth term of Fibonacci's suite
	public static int Fib(int n)
	{
		if (n == 1)
		{
			return 1;
		}
		if (n == 2)
		{
			return 2;
		}
		else
		{
			return Fib(n - 1) + Fib(n - 2);
		}
	}
	
	public static void main(String [] arg)
	{
		int i = 1;
		int sum = 0;
		while (Fib(i) < 4000001)
		{
			if (Fib(i) % 2 == 0)
			{
				sum += Fib(i);
			}
			i++;
		}
		System.out.println("The solution is " + sum);
	}
}




