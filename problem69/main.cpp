#include <iostream>
#include <vector>
#include <cmath>
#include "integer_arithmetic.h"
/**
 * Problem 69
 */

/**
* Returns phi n
*/
int phi(int n, int primes[], int primesLength);

int main() {
	int n = (int)sqrt(1000000);
	bool *prime = sieveEratosthenes(n);
	int nbPrimes = 0;
	for (int i = 0; i < n + 1; i++) {
		if (prime[i]) nbPrimes++;
	}
	int *primes = new int[nbPrimes];
	int j = 0;
	for (int i = 0; i < n + 1; i++) {
		if (prime[i]) {
			primes[j] = i;
			j++;
		}
	}
	float maxRatio = 0;
	int index = 0;
	for (int i = 2; i <= 1000000; i++) {
		float ratio = (float)i / (float)phi(i, primes, nbPrimes);
		if (ratio > maxRatio) {
			maxRatio = ratio;
			index = i;
		} 
	}
	std::cout << "The solution is " << index << std::endl;
	return 0;
}

int phi(int n, int primes[], int primesLength) {
	std::vector<std::vector<int> > primeDec = primeDecomp(n, primes, primesLength);
	int result = 1;
	for (int i = 0; i < primeDec.size(); i++) {
		result *= (primeDec[i][0] - 1) * (int)pow(primeDec[i][0], primeDec[i][1] - 1);
	}
	return result;
}