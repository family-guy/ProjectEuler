import java.util.*;
import java.io.*;
/* Problem 42
 * posInAlpha: takes a character and returns its place in the alphabet e.g 'c' returns three
 * wordVal: takes a word and returns its word value
 * isTriNb: returns whether a given positive integer is a triangle number
*/

public class Problem42 {
	public static void main(String[] arg) {
		String allWords = ""; FileData file = new FileData();
		try {
			file.open("/Users/guyking/ProjectEuler/problem42/p042_words.txt", "read");
		}
		catch(IOException e) {
			throw new RuntimeException("Failed to open file", e);
		}
		try {
			allWords = file.read();
		}
		catch(IOException e) {
			throw new RuntimeException("Failed to read file", e);
		}
		allWords = allWords.replace("\"", ""); // remove quotation remarks ie replace all instances of quotation marks with the empty string
		String[] allWordsArray = allWords.split(","); // splits the string by commas, each separated part becoming an element in the array allWordsArray
		int count = 0;
		for (int i = 0; i < allWordsArray.length; i++) {
			if (isTriNb(wordVal(allWordsArray[i]))) count++;
		}
		System.out.println("The solution is " + count);
	}
	
	public static int posInAlpha(char c) {
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int i = 0;
		while (true) {
			if (alphabet.charAt(i) == c) break;
			i++;
		}
		return i + 1;
	}
	
	public static int wordVal(String s) {
		int sum = 0;
		for (int i = 0; i < s.length(); i++) {
			sum += posInAlpha(s.charAt(i));
		}
		return sum;
	}
	
	public static boolean isTriNb(int n) {
		int i = 1;
		while (true) {
			int ithTriangleNb = i * (i + 1) / 2;
			if (ithTriangleNb == n) return true;
			if (ithTriangleNb > n) return false;
			i++;
		}
	}
}

