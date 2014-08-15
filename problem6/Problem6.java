import java.util.*;

public class Problem6
{
	public static int sumOfSquares(int n)
	{
		int i;
		int sum = 0;
		for (i = 1; i < n + 1; i++)
		{
			sum += i * i;
		}
		return sum;	
	}
	
	public static int squareOfSum(int n)
	{
		int i;
		int sum = 0;
		for (i = 1; i < n + 1; i++)
		{
			sum += i;
		}
		return sum * sum;
	}
	
	public static void main(String[] arg)
	{
		int solution = squareOfSum(100) - sumOfSquares(100);
		System.out.println("The solution is " + solution);
	}
}