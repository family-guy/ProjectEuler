import java.util.*;
import java.io.*;

public class Problem22
{
	// function that given a char in {A, B, ..., Z} returns its position in the alphabet eg C returns 3
	public static int posInAlphabet(char c)
	{
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int i = 0;
		while (true)
		{
			if (alphabet.charAt(i) == c)
			{
				break;
			}
			i++;
		}
		return i + 1;
	}
	
	// function that given a string in upper case returns its alphabetical value eg COLIN returns 3 + 15 + 12 + 9 + 14 = 53
	public static int alphabetValue(String str)
	{
		int sum = 0;
		int i;
		for (i = 0; i < str.length(); i++)
		{
			sum += posInAlphabet(str.charAt(i));
		}
		return sum;
	}
	
	public static void main(String[] arg)
	{
		String allNames = "";
		FileData file = new FileData();
		try 
		{
			file.open("/Users/guyking/ProjectEuler/problem22/p022_names.txt", "read");
		}
		catch(IOException e) 
		{ 
    		throw new RuntimeException("Failed to open file", e); 
  		}
		try
		{
			allNames = file.read(); // the text file is just one long line. Hence is the entire text is contained in the string allNames
		}	
  		catch(IOException e)
  		{
  			throw new RuntimeException("Failed to read file", e);
  		}

		allNames = allNames.replace("\"", ""); // remove quotation remarks ie replace all instances of quotation marks with the empty string
		String[] allNamesArray = allNames.split(","); // splits the string by commas, each separated part becoming an element in the array allNamesArray
		Arrays.sort(allNamesArray); // sorts the array by alphabetical order
		int sum = 0;
		int i;
		for (i = 0; i < allNamesArray.length; i++)
		{
			sum += alphabetValue(allNamesArray[i]) * (i + 1);
		}
		System.out.println("The solution is " + sum);
	}
}