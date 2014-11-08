#include <iostream>
#include <cmath>
#include "integer_arithmetic.h"
/* Problem 46
 * isSq: returns whether a given non negative integer is a square
 * isSumOfPrimeAndTwiceASq: takes a boolean array where the i-th element says whether i is prime and a positive integer and returns whether the integer is the sum of a prime and twice a square
*/

bool isSq(int n);

bool isSumOfPrimeAndTwiceASq(int n, bool P[]);

int main() {
	int n = 500000000; // it turns out this upper limit can be a lot smaller
	bool *primes = sieveEratosthenes(n);
	int i = 3;
	while (true) {
		while (primes[i]) i += 2; // get next odd composite number
		if(!isSumOfPrimeAndTwiceASq(i, primes)) break;
		i += 2;
	}
	std::cout << "The solution is " << i << std::endl;
	return 0;
}

bool isSq(int n) {
	if (n == 0) return false;
	return sqrt(n) == (int)sqrt(n);
}

bool isSumOfPrimeAndTwiceASq(int n, bool P[]) {
	for (int i = 0; i < n; i++) {
		if (P[i]) {
			int remainder = n - i;
			if (remainder % 2 == 0) {
				remainder /= 2;
				if (isSq(remainder)) return true;
			}
		}
	}
	return false;
}


