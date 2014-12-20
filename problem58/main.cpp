#include <iostream>
#include "integer_arithmetic.h"
/**
 * Problem 58
 */

int main() {
	long currentVal = 1; int nbPrimes = 0;
	int count = 1; float ratio = 1;
	int i = 2;
	while (true) {
		for (int j = 0; j < 4; j++) {
			currentVal += i;
			count++;
			if (isPrime(currentVal)) nbPrimes++;
			if (j == 3) ratio = (float)nbPrimes / (float)count;
		}
		if (ratio < 0.1) {
			std::cout << "The solution is " << i + 1 << std::endl;
			break;
		}
		i += 2;
	}
	return 0;
}