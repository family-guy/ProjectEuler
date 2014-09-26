import java.util.*;
/* Problem 4
 * nbDigits: returns the number of digits of a non-negative integer
 * arrayDigits: returns a number's digits as an array e.g. 123 returns [1, 2, 3]
 * deepEqual: returns whether two arrays have the same values
 * reverseArray: reverses an array of integers
 * isPalindrome: returns whether an integer is a palindrome
*/

public class Problem4
{
	public static void main(String[] arg) {
		int maxValue, maxIndex1, maxIndex2; maxValue = 0; maxIndex1 = 0; maxIndex2 = 0;
		for (int i = 100; i < 1000; i++) {
			for (int j = 100; j < 1000; j++) {
				if (isPalindrome(i * j) && i * j > maxValue) {
					maxValue = i * j; maxIndex1 = i; maxIndex2 = j;
				}
			}
		}
		System.out.println("The solution is " + maxValue + " = " + maxIndex1 + " x " + maxIndex2);
	}
	
	public static int nbDigits(int n) {
		return nbDigits(n, 10, 1);
	}
	
	public static int nbDigits(int n, int a, int b) {
		if (n / a == 0) return b;
		else return nbDigits(n, a * 10, b + 1);
	}
	
	public static int[] arrayDigits(int n) {
		int p = nbDigits(n); int[] result = new int[p]; int product = 1; 
		for (int i = 0; i < p; i++) {
			product *= 10;
			result[p - 1 - i] = (n % product) / (product / 10);
		}
		return result;
	}
	
	public static boolean deepEqual(int[] T, int[] U) {
		if (T.length != U.length) return false;
		else {
			for (int i = 0; i < T.length; i++) {
				if (T[i] != U[i]) return false;
			}
			return true;
		}
	}
	
	public static int[] reverseArray(int[] T) {
		int[] result = new int[T.length];
		for (int i = 0; i < T.length; i++) {
			result[i] = T[T.length - 1 - i];
		}
		return result;
	}
	
	public static boolean isPalindrome(int n) {
		return deepEqual(arrayDigits(n), reverseArray(arrayDigits(n)));
	}	
}