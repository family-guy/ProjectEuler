import java.util.*;
import java.io.*;

public class Problem8
{
	// function that given an array of integers and two indices k <= l, returns the product of adjacent entries between those indices
	public static long productOfEntriesInArray(int []T, int k, int l)
	{
		int i; long product = 1;
		for (i = k; i < l + 1; i++)
		product *= T[i];
		return product;
	}
		
	public static void main(String[] arg)
	{
		FileData file = new FileData();
		try 
		{
			file.open("/Users/guyking/ProjectEuler/problem8/Problem8.txt", "read");
		}
		catch(IOException e) 
		{ 
    		throw new RuntimeException("Failed to open file", e); 
  		}
  		try // to print the 1000 digit number
  		{
  			/*String str;
  			while (true)
  			{
  				str = file.read(); // the method read() reads one line then passes to the next
  				if (str == null)
  				break; // we have reached the end of the text
  				System.out.println(str);
  			}*/  			
  			String str1 = ""; String str2 = ""; // we assign the 1000 digit number to str2
  			while (true)
  			{
  				str1 = file.read();
  				if (str1 == null)
  				break;
  				str2 = str2.concat(str1);
  			}
			// convert str2 to an array of integers
			int []arrayOfDigits = new int[1000];
			int i = 0;
			while (i < str2.length())
			{
				arrayOfDigits[i] = Character.digit(str2.charAt(i), 10); // Character.digit() converts a character to an integer
				i++;
			}
			// find the maximum wanted in the question
			long currentMaxValue = 0; int currentMaxIndexStart = 0;
			i = 0; 
			while (i < 988)
			{
				if (productOfEntriesInArray(arrayOfDigits, i, i + 12) > currentMaxValue)
				{
					currentMaxValue = productOfEntriesInArray(arrayOfDigits, i, i + 12);
					currentMaxIndexStart = i;
				}
				i++;
			}
			// print the solution
			i = 0;
			System.out.print("The solution is ");
			while (i < 12)
			{
				System.out.print(arrayOfDigits[currentMaxIndexStart + i] + " x ");
				i++;
			}
			System.out.println(arrayOfDigits[currentMaxIndexStart + i] + " = " + currentMaxValue); 	
  		}
  		catch(IOException e)
  		{
  			throw new RuntimeException("Failed to read file", e);
  		} 		
	}
}