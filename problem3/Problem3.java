import java.util.*;

public class Problem3 {
	public static void main(String[] arg) {
		System.out.println("The solution is " + largestPrimeFactor(600851475143L));
	}
	
	public static long largestPrimeFactor(long n) { // overloaded method that allows default parameters
		return largestPrimeFactor(n, 2);
	}
	
	public static long largestPrimeFactor(long n, int i) { // full definition
		if (IntegerArithmetic.isPrime(n)) return n;
		else {
			while (IntegerArithmetic.isPrime(i) == false || n % i != 0) i++;
			n = n / i;
			return largestPrimeFactor(n, i);
		}
	}
}