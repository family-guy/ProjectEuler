#include <iostream>
#include <algorithm>
#include "combinatorics.h"
/**
 * Problem 53
 */

// Returns the number of values of k, k in [0, ..., n], such that C^k_n > l
int nbExceedingVal(int n, int l);

int main() {
	int limit = 1000000; int sum = 0;
	for (int n = 1; n <= 100; n++) {
		sum += nbExceedingVal(n, limit);
	}
	std::cout << "The solution is " << sum << std::endl;
	return 0;
}

int nbExceedingVal(int n, int l) {
	int k = 0; int m = n + 1;
	while (k <= n / 2) {
		if (nChooseK(k, n) > l) break;
		k++;
	}
	return std::max(m - 2 * k, 0);
}