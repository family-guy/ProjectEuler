import java.util.*;

public class Problem16
{
	// function that takes an array list of integers and reverses it
	public static ArrayList<Integer> reverseList(ArrayList<Integer> list)
	{
		ArrayList<Integer> result = new ArrayList<Integer>();
		int i;
		for (i = list.size() - 1; i > -1; i--)
		{
			result.add(list.get(i));
		}
		return result;
	}
	
	// function that takes an array list of digits, multiplies by an integer and returns the result in a new array list
	// eg [5 1 2] and 2 returns [1 0 2 4]
	public static ArrayList<Integer> multiplication(ArrayList<Integer> list, int n)
	{
		ArrayList<Integer> result = new ArrayList<Integer>(); // initialise an empty array list of integers
		int i; 		
		int carryOver = 0;
		int p = 0;
		for (i = 0; i < list.size() - 1; i++) // there is a -1 as the first element will be treated as a separate case
		{
			p = list.get(list.size() - 1 - i) * n + carryOver; 
			result.add(p % 10);
			carryOver = p / 10;
		}
		// treat the case of the first element
		p = list.get(0) * n + carryOver;
		while (true)
		{
			result.add(p % 10);
			if (p / 10 == 0)
			{
				break;
			}
			else
			{
				p /= 10;
			}
		}		
		return reverseList(result);
	}
	
	// function that takes an array list of integers and returns the sum of the digits
	public static int sumOfArrayList(ArrayList<Integer> list)
	{
		int sum = 0;
		int i;
		for (i = 0; i < list.size(); i++)
		{
			sum += list.get(i);
		}
		return sum;
	}
	
	public static void main(String[] arg)
	{
		int[] T = {1};
		ArrayList<Integer> list = new ArrayList<Integer>();
		int i;
		for (i = 0; i < T.length; i++)
		{
			list.add(T[i]);
		}
		for (i = 0; i < 1000; i++) 
		{
			list = multiplication(list, 2);
		}
		System.out.println(sumOfArrayList(list));	
	}
}