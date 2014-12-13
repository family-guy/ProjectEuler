import java.util.*;
import java.io.*;
/**
 * Problem 59
 * The frequencies of the three most common letters in the English language are E (12.5%), T (9.25%), A (8.04%)
 */

public class Problem59 {
	public static void main(String[] arg) {
		FileData file = new FileData();
		try {
			file.open("/Users/guyking/ProjectEuler/problem59/p059_cipher.txt", "read");
		}
		catch (IOException e) {
			throw new RuntimeException("Failed to open file", e);
		}
		try {
			String str = file.read();
			String[] encrypted = str.split(",");
			int[] encryptedAsInts = new int[encrypted.length];
			for (int i = 0; i < encrypted.length; i++) {
				encryptedAsInts[i] = Integer.parseInt(encrypted[i]);
			}
			String alphabet = "abcdeghijklmnopqrstuvwxyz";
			char[] key = {'a', 'a', 'a'};
			for (int i = 0; i < alphabet.length(); i++) {
				for (int j = 0; j < alphabet.length(); j++) {
					for (int k = 0; k < alphabet.length(); k++) {
						key[0] = alphabet.substring(i, i + 1).charAt(0);
						key[1] = alphabet.substring(j, j + 1).charAt(0);
						key[2] = alphabet.substring(k, k + 1).charAt(0);
						String decrypted = decrypt(encryptedAsInts, key);
						if (isDesc(nbOccurrences(decrypted)) && (float)nbOccurrences(decrypted)[0] / (float)decrypted.length() > 0.1
						&& (float)nbOccurrences(decrypted)[1] / (float)decrypted.length() > 0.07) {
							System.out.println("The plain text is: " + decrypted);
							System.out.println("The solution is " + sum(decrypted.getBytes("US-ASCII")));
						}
					}
				}
			}			
		}
  		catch (IOException e) {
  			throw new RuntimeException("Failed to read file", e);
  		}
	}
	
	/**
	* Takes an array of encrypted ASCII codes, a key and returns the decrypted cipher text using that key
	*/
	public static String decrypt(int[] encrypted, char[] key) {
		byte[] encryptedAsBytes = new byte[encrypted.length];
		for (int i = 0; i < encrypted.length; i++) {
			encryptedAsBytes[i] = (byte)(encrypted[i] ^ key[i % key.length]);
		}
		return new String(encryptedAsBytes);
	}
	
	/**
	* Gets the sum of an array
	*/
	public static int sum(byte[] T) {
		int result = 0;
		for (int i = 0; i < T.length; i++) {
			result += (int)T[i];
		}
		return result;
	}
	
	/**
	* Checks whether an array of integers is in descending order
	*/
	public static boolean isDesc(int[] T) {
		for (int i = 0; i < T.length - 1; i++) {
			if (T[i] < T[i + 1]) return false;
		}
		return true;
	}

	/**
	* Takes a string and gets the frequencies of the letters 'E', 'T' and 'A' in that string
	*/
	public static int[] nbOccurrences(String str) {
		String[] mostFreqLetters = {"E", "T", "A"};
		int[] result = new int[mostFreqLetters.length];
		for (int i = 0; i < str.length(); i++) {
			for (int j = 0; j < mostFreqLetters.length; j++) {
				if (mostFreqLetters[j].equalsIgnoreCase(str.substring(i, i + 1))) {
					result[j]++;
					break;
				} 
			}
		}
		return result;
	}
}