import java.util.*;

public class Problem9
{
	// function that sees whether a triplet is Pythagorean
	public static boolean isPythag(int a, int b, int c) // 0 < a < b < c
	{
		if (a * a + b * b == c * c)
		return true;
		else
		return false;
	}
	
	public static void main(String[] arg)
	{
		int i = 1; int j = 2; int k = 3;
		while (i < 998)
		{
			while (j < 999)
			{ 
				while (k < 1000)
				{
					if (isPythag(i, j, k) && (i + j + k == 1000))
					{
						System.out.println("The solution is " + i + " x " + j + " x " + k + " = " + (i * j * k));
						break;
					}
					else
					k++;
				}
				j++; k = j + 1;
			}
			i++; j = i + 1; k = i + 2;
		}	
	}
}