#include <iostream>
#include <string>
#include <cmath>

using namespace std;

// function to see if an integer is a prime number
bool isPrime(int n) // n is non negative
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
// function that returns the n-th prime 
int nthPrime(int n)
{
	int i(0);
	while (n > 0)
	{
		if (isPrime(i))
		{
			n--;
		}
		i++;
	}
	return i - 1;
}

int main()
{
	// print the first few prime numbers
	int i(0);
	while (i < 50)
	{
		if (isPrime(i))
		{
			cout << i << " ";
		}
		i++; 
	}
	cout << endl;
	cout << nthPrime(10001) << endl;
	
	return 0;
}
			