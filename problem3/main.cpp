#include <iostream>
#include <string>
#include <cmath>

using namespace std;

// function to see if an integer is a prime number
bool isPrime(long unsigned int n) // n is non negative
{
	if (n == 0 || n == 1)
	{
		return false;
	}
	float x = sqrt(n);
	int p(floor(x));
	int i(2);
	while (i < p + 1)
	{
		if (n % i == 0)
		{
			return false;
		}
		i++;
	}
	return true;
}
// main function
long int unsigned largestPrimeFactor(long int unsigned n, int i) // i = 2 is always the initial value
{
	if (isPrime(n))
	{
		return n;
	}
	else
	{
		while (isPrime(i) == false || n % i != 0)
		{
			i++;
		}
		n = n / i;
		return largestPrimeFactor(n, i);
	}
}
// print solution
int main()
{
	long int unsigned n(600851475143); // n is too large here
	cout << largestPrimeFactor(n, 2) << endl;
	return 0;
}
		
		
	
	
	