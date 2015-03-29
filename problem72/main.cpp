#include <iostream>
#include <cmath>
#include "integer_arithmetic.h"
/**
 * Problem 72
 */

/**
* Returns phi n
*/
int phi(int n, int primes[], int lengthOfPrimes);

int main() {
	int upperBound = (int)sqrt(10000000);
	bool *prime = sieveEratosthenes(upperBound);
	int nbPrimes = 0;
	for (int i = 0; i < upperBound + 1; i++) {
		if (prime[i]) nbPrimes++;
	}
	int *primes = new int[nbPrimes];
	int j = 0;
	for (int i = 0; i < upperBound + 1; i++) {
		if (prime[i]) {
			primes[j] = i;
			j++;
		}
	}
	long sum = 0;
	for (int d = 2; d <= 1000000; d++) {
		sum += (long)phi(d, primes, nbPrimes);
	}
	std::cout << "The solution is " << sum << std::endl;
	return 0;
}

int phi(int n, int primes[], int lengthOfPrimes) {
	std::vector<std::vector<int> > primeDec = primeDecomp(n, primes, lengthOfPrimes);
	int result = 1;
	for (int i = 0; i < primeDec.size(); i++) {
		result *= (primeDec[i][0] - 1) * (int)pow((double)primeDec[i][0], (double)primeDec[i][1] - 1);
	}
	return result;
}