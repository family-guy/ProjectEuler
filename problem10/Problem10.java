import java.util.*;
import java.lang.Math;

public class Problem10
{
	public static void main(String[] arg)
	{
		int N = 2000000;
		boolean isPrime[] = new boolean[N + 1];
		// make all values true
		int i;
		for (i = 0; i < N + 1; i++)
		isPrime[i] = true;
		// set 0 and 1 to false
		isPrime[0] = false; isPrime[1] = false;
		// sieve of Eratosthenes. 
		int j;
		for (i = 0; i * i < N; i++) // the exit condition comes from "(for all i in {1, ..., [sqrt n]} s.t i is prime, n is not divisible by i) => n is prime" 
		{
			if (isPrime[i] == true)
			{				
				for (j = 2; i * j <= N; j++)
				{
					isPrime[i * j] = false;
				}
			}
		} 
		long sum = 0;
		for (i = 0; i < N + 1; i++)
		{
			if (isPrime[i])
			sum += i;
		}
		System.out.println(sum);
	}				
}
				
				
