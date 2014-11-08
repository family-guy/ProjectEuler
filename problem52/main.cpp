#include <iostream>
#include "combinatorics.h"
/* Problem 52 */

int main() {
	int i = 1; int n = 6;
	int k;
	while (true) {
		for (k = 2; k < n; k++) {
			if (!isPerm(i, i * k)) break;
		}
		if (isPerm(i, i * k) && k == n) {
			std::cout << "The solution is " << i << std::endl;
			break;
		}
		i++;
	}
	return 0;
}