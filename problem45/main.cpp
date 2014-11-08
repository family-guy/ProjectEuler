#include <iostream>
#include <algorithm>
#include <vector>
/* Problem 45 */

int main() {
	int n = 50000001;
	std::vector<long long unsigned int> hex;
	for (int i = 0; i < n; i++) {
		hex.push_back((long long unsigned int)i * (2 * (long long unsigned int)i - 1));
	}
	long long unsigned int l = 0; long long unsigned int k = 0; int i = 166;
	while (true) {
		l = (long long unsigned int)i;
		k = l * (3 * l - 1) / 2;
		if (std::binary_search (hex.begin(), hex.end(), k)) break;
		i++;
	}
	std::cout << "The solution is " << k << std::endl; // every hexagonal number is a triangle number
	return 0;
}