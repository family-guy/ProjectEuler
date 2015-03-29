#include <iostream>
#include <cmath>
#include "integer_arithmetic.h"
/**
 * Problem 73
 */

int main() {
	int n = 12000;
	int sum = 0;
	for (int i = 2; i <= n; i++) {
		int lowerBound = (int)ceil((float)i / (float)3);
		int upperBound = (int)floor((float)i / (float)2);
		for (int j = lowerBound; j <= upperBound; j++) {
			if (gcd(j, i) == 1) {
				if (!((j == 1 && i == 2) || (j == 1 && i == 3))) {
					sum++;
				}
			}
				
		}
	}
	std::cout << "The solution is " << sum << std::endl;
	return 0;
}