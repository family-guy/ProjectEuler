/* for an nxn grid, each path is of length 2n. 
Each path consists of n downs and n rights eg for n = 2 we have 
DDRR, RRDD, RDDR, RDRD, DRDR, DRRD. If we number the rights, this gives
{3, 4}, {1, 2}, {1, 4}, {1, 3}, {2, 4}, {2, 3} which is the list of 
combinations of choosing two elements from {1, 2, 3, 4}. Thus the 
number of paths is C^2_4. Thus for n = 20, the solution is C^20_40
*/

import java.util.*;

public class Problem15
{
	public static void main(String[] arg)
	{
		// we use the formula C^k_n+1 = n+1/n+1-k * C^k_n
		int k = 20; int n;
		long product = 1;
		for(n = 20; n < 40; n++)
		{
			product = (product * (n + 1)) / (n + 1 - k);
		}
		System.out.println("The solution is " + product);
		String A = "three hundred and forty-two";
		System.out.println(A.length());
	}
}