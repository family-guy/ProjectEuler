import java.util.*;

public class Problem19
{
	public static int daysInMonth(int month, int year)
	{
		int result = 0;	
		switch(month)
		{
			case 1: result = 31;
			break;
			case 2: if (year % 4 == 0)
					{
						if (year % 100 == 0)
						{
							if (year % 400 == 0)
							{
								result = 29;
							}
							else
							{
								result = 28;
							}
						}
						else
						{
							result = 29;
						}
					}
					else
					{
						result = 28;
					}
			break;
			case 3: result = 31;
			break;
			case 4: result = 30;
			break;
			case 5: result = 31;
			break;
			case 6: result = 30;
			break;
			case 7: result = 31;
			break;
			case 8: result = 31;
			break;
			case 9: result = 30;
			break;
			case 10: result = 31;
			break;
			case 11: result = 30;
			break;
			case 12: result = 31;
			break;
			default: result = -1;
			break;
		}
		return result;
	}
	
	public static void main(String[] arg)
	{
		ArrayList<Integer> firstsOfMonth = new ArrayList<Integer>();
		int currentDay = 1;
		int currentMonth = 1;
		int currentYear = 1900;
		int i = 0;
		while (currentYear < 2001)
		{
			i += daysInMonth(currentMonth, currentYear);
			if (currentYear > 1900)
			{
				firstsOfMonth.add(i);
				// update the month
				if (currentMonth < 12)
				{
					currentMonth++;
				}
				else
				{
					currentMonth = 1;
					currentYear++;
				}
			}
			else
			{
				// update the month
				if (currentMonth < 12)
				{
					currentMonth++;
				}
				else
				{
					currentMonth = 1;
					currentYear++;
				}
			}
		}
		int nbOfSundays = 0;
		for (i = 0; i < firstsOfMonth.size(); i++)
		{
			if (firstsOfMonth.get(i) % 7 == 6)
			{
				nbOfSundays++;
			}
		}
		System.out.println("The number of Sundays is " + nbOfSundays);		
	}
}