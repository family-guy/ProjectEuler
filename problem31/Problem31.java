import java.util.*;
/* Problem 31
 * nbOfWays: t is the target, c is the array of available coin values
*/

public class Problem31 {
	public static void main(String[] arg) {
		int[] coinValues = {1, 2, 5, 10, 20, 50, 100, 200}; int target = 200;	
		System.out.println("The solution is " + nbOfWays(target, coinValues));		
	}
	
	public static int nbOfWays(int t, int[] c) {
		if (c.length == 1) {
			if (t % c[0] == 0 && t / c[0] > 0) return 1;
			else return 0;
		}
		int sum = 0; int[] new_c = new int[c.length - 1];
		for (int i = 0; i < new_c.length; i++) {
			new_c[i] = c[i + 1];
		}
		if (t % c[0] == 0 && t / c[0] > 0) sum++;
		while (t > 0) {
			sum += nbOfWays(t, new_c);
			t -= c[0];
		}
		return sum; 
	}
}
