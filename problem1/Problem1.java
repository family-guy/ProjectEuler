import java.util.*;

public class Problem1
{
	public static void main(String [] arg)
	{
		int i = 1;
		int sum = 0;
		while (i < 1000)
		{
			if (i % 3 == 0 || i % 5 == 0)
			{
				sum += i;
			}
			i++;
		}
		System.out.println("The solution is " + sum);
	}
}