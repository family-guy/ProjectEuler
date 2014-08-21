import java.util.*;

public class Problem17
{
	public static void main(String[] arg)
	{
		int[] T = new int[20]; // T[i] is the number of characters in i, for i in 1 to 19 (we will not make use of the first element in the array)
		T[1] = 3; T[2] = 3; T[3] = 5; T[4] = 4; T[5] = 4; T[6] = 3; T[7] = 5; T[8] = 5; T[9] = 4; T[10] = 3; T[11] = 6; T[12] = 6; T[13] = 8; T[14] = 8; T[15] = 7; T[16] = 7; T[17] = 9; T[18] = 8; T[19] = 8; // because "one" has 3 letters, "two" has three letters, "three" has 5 letters, etc.
		int i; int sum = 0;
		// the case of 1 to 19
		for (i = 1; i < 20; i++)
		{
			sum += T[i];
		}
		int[] U = new int[10]; // U contains the number of characters in 20, 30, ..., 100 (hundred), 1000 (thousand)
		U[0] = 6; U[1] = 6; U[2] = 5; U[3] = 5; U[4] = 5; U[5] = 7; U[6] = 6; U[7] = 6; U[8] = 7; U[9] = 8;
		// the case of 20 to 99
		for (i = 20; i < 100; i++)
		{
			if (i % 10 == 0)
			{
				sum += U[i / 10 - 2];
			}
			else
			{
				sum += T[i % 10] + U[i / 10 - 2];
			}
		}
		// the case of 100 to 999
		for (i = 100; i < 1000; i++)
		{
			// case of one hundred, two hundred etc.
			if (i % 100 == 0)
			{
				sum += T[i / 100] + U[8];
			}
			// case of numbers that are not multiples of 100
			else
			{
				// first of all get the hundreds part of the number
				sum += T[i / 100] + U[8] + 3; // 3 for the and
				// get the second part of the number eg thirty-five in two hundred and thirty-five
				// we reuse the code from above because the case 1 to 99 has already been treated
				// the case where the second part of the number is between 1 and 19
				if (i % 100 < 20)
				{
					sum += T[i % 100];
				}
				// the case where the second part of the number is between 20 and 99
				else
				{
					if ((i % 100) % 10 == 0)
					{
						sum += U[(i % 100) / 10 - 2];
					}
					else
					{
						sum += T[(i % 100) % 10] + U[(i % 100) / 10 - 2];
					}
				}
			}		
		}
		// the case of 1000
		sum += T[1] + U[9];
		System.out.println("The solution is " + sum);		
	}
}