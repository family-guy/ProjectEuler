#include <iostream>
#include <vector>
#include <cmath>
#include "integer_arithmetic.h"
#include "combinatorics.h"
/**
 * Problem 70
 */

/**
* Returns phi n
*/
int phi(int n, int primes[], int lengthOfPrimes);

void printVect(std::vector<std::vector<int> > T);

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
	int index = 87109;
	//std::cout << phi(index, primes);
	//std::cout << isPerm(phi(index, primes), index);
	float minRatio = (float)index / (float)phi(index, primes, nbPrimes);
	for (int n = 2; n < 10000000; n++) {
		int phiOfn = phi(n, primes, nbPrimes);
		if (isPerm(phiOfn, n)) {
			float ratio = (float)n / (float)phiOfn;
			if (ratio < minRatio) {
				minRatio = ratio;
				index = n;
			} 
		}
	}
	std::cout << "The solution is " << index << std::endl;
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