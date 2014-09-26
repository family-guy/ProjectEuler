import java.util.*;
/* Problem 2
 * fib: returns the n-th term of Fibonacci's sequence
*/

public class Problem2 {
	public static void main(String[] arg) {
		int i = 1; int sum = 0;
		while (fib(i) < 4000001) {
			if (fib(i) % 2 == 0) sum += fib(i);
			i++;
		}
		System.out.println("The solution is " + sum);
	}

	public static int fib(int n) {
		if (n == 1) return 1;
		if (n == 2) return 2;
		else return fib(n - 1) + fib(n - 2);
	}
}




