import java.util.*;

public class Problem25
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
	
	// function that takes two array lists of digits and returns the sum in an array list. The lengths of the two lists are not necessarily the same but we assume length of list1 <= length of list2
	public static ArrayList<Integer> sumOfTwoArrayLists(ArrayList<Integer> list1, ArrayList<Integer> list2)
	{
		ArrayList<Integer> result = new ArrayList<Integer>(); // initialise an empty array list of integers
		int i; 		
		int carryOver = 0;
		int p = 0;
		if (list1.size() == list2.size())
		{
			for (i = 0; i < list1.size() - 1; i++) // there is a -1 as the first element will be treated as a separate case
			{
				p = list1.get(list1.size() - 1 - i) + list2.get(list2.size() - 1 - i) + carryOver; 
				result.add(p % 10);
				carryOver = p / 10;
			}
			// treat the case of the first element
			p = list1.get(0) + list2.get(0) + carryOver;
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
		else // in this case length of list1 < length of list2
		{
			// we extend list1 with zeros such that the two lists are of equal length
			int difference = list2.size() - list1.size();
			for (i = 0; i < difference; i++)
			{
				list1.add(0, 0);
			}// now list1 is the same length as list2 so we do a recursive call
			return sumOfTwoArrayLists(list1, list2);
		}	
	}
	
	public static void main(String[] arg)
	{
		ArrayList<Integer> f_nMinusTwo = new ArrayList<Integer>();
		ArrayList<Integer> f_nMinusOne = new ArrayList<Integer>();
		ArrayList<Integer> f_n = new ArrayList<Integer>();
		f_nMinusTwo.add(1); f_nMinusOne.add(1);
		int i = 3;
		while (true)
		{
			f_n = sumOfTwoArrayLists(f_nMinusTwo, f_nMinusOne);
			if (f_n.size() > 999)
			{
				break;
			}
			// update the values of f_n-2 and f_n-1
			f_nMinusTwo = f_nMinusOne;
			f_nMinusOne = f_n;
			i++;
		}	
		System.out.println("The solution is " + i);	
	}
}