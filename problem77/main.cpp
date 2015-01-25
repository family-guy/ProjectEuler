#include <iostream>
#include "integer_arithmetic.h"
/**
 * Problem 77
 */

/*
* Returns the primes less than a given integer
*/
int * primesLessThanN(int n, bool prime[]);

/*
* Returns the number of ways in which an integer can be written as the sum of some given integers
*/
int nbOfWays(int t, int c[], int n);

int main() {
	int n = 1000000;
	bool *prime = sieveEratosthenes(n);
	int i = 10;
	int k = 5000;
	while (true) {
		int nbPrimes = 0;
		for (int k = 0; k < i; k++) {
			if (prime[k]) nbPrimes++;
		}
		int *primes = primesLessThanN(i, prime);
		if (nbOfWays(i, primes, nbPrimes) > k) {
			std::cout << "The solution is " << i << std::endl;
			break;
		}
		i++;
	}
	return 0;
}

int * primesLessThanN(int n, bool prime[]) {
	int nbPrimes = 0;
	for (int i = 0; i < n; i++) {
		if (prime[i]) nbPrimes++;
	}
	int *result = new int[nbPrimes];
	int j = 0;
	for (int i = 0; i < n; i++) {
		if (prime[i]) {
			result[j] = i;
			j++;
		}
	}
	return result;
}

int nbOfWays(int t, int c[], int n) {
	if (n == 1) {
		if (t % c[0] == 0 && t / c[0] > 0) return 1;
		else return 0;
	}
	int sum = 0; int *new_c = new int[n - 1];
	for (int i = 0; i < n - 1; i++) {
		new_c[i] = c[i + 1];
	}
	if (t % c[0] == 0 && t / c[0] > 0) sum++;
	while (t > 0) {
		sum += nbOfWays(t, new_c, n - 1);
		t -= c[0];
	}
	return sum;
}