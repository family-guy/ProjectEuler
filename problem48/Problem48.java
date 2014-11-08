import java.util.*;
/* Problem 48
 * multiply: takes a positive integer and an array of 10 integers and returns an array which is the original array multiplied by the integer e.g. multiply(5, {0, 0, 0, 0, 0, 0, 1, 0, 2, 3}) returns {0, 0, 0, 0, 0, 0, 5, 1, 1, 5}
 * power: returns the last 10 digits in an array e.g. power(2, 9) = {0, 0, 0, 0, 0, 0, 0, 5, 1, 2}
 * add: takes two arrays representing the last 10 digits of two integers and returns the last 10 digits of their sum e.g. add({0, 0, 0, 0, 0, 0, 0, 1, 2, 4}, {0, 0, 0, 0, 0, 0, 1, 6, 0, 1}) returns {0, 0, 0, 0, 0, 0, 1, 7, 2, 5}
*/

public class Problem48 {
	public static void main(String[] arg) {
		int n = 1000; int[] result = new int[10];
		for (int i = 1; i <= n; i++) {
			result = add(result, power(i, i));
			for (int j = 0; j < 10; j++) {
				System.out.print(result[j] + " ");
			}
			System.out.println();
		}
		String str = "";
		for (int i = 0; i < result.length; i++) {
			str += Integer.toString(result[i]);
		}
		System.out.println("The solution is " + str);
	}
	
	public static int[] multiply(int n, int[] T) {
		int[] result = new int[10]; int carryOver = 0;
		for (int i = T.length - 1; i > -1; i--) {
			result[i] = (n * T[i] + carryOver) % 10;
			carryOver = (n * T[i] + carryOver) / 10;
		}
		return result;
	}
	
	public static int[] power(int a, int b) {
		int[] result = new int[10];
		if (b == 0) {
			result[result.length - 1] = 1;
			return result;
		}
		String aAsStr = Integer.toString(a); int j = 0;
		for (int i = aAsStr.length() - 1; i > -1; i--) { 
			result[result.length - 1 - j] = Character.digit(aAsStr.charAt(i), 10);
			j++;
		}
		for (int i = 1; i < b; i++) {
			result = multiply(a, result);
		}
		return result;
	}
	
	public static int[] add(int[] T, int[] U) {
		int[] result = new int[10]; int carryOver = 0;
		for (int i = T.length - 1; i > -1; i--) {
			result[i] = (T[i] + U[i] + carryOver) % 10;
			carryOver = (T[i] + U[i] + carryOver) / 10;
		}
		return result;
	}
}