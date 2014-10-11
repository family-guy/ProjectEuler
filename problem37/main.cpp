#include <iostream>
#include <string>
#include <integer_arithmetic.h>
/* Problem 37
 * isTruncLeftToRight: takes a positive integer and a boolean array and returns whether the integer is 'truncatable' from left to right
 * isTruncRightToLeft: takes a positive integer and a boolean array and returns whether the integer is 'truncatable' from right to left
*/

bool isTruncLeftToRight(int n, bool p[]);

bool isTruncRightToLeft(int n, bool p[]);

int main() {
	int n = 1000000; int sum = 0; int count = 0;
	bool *primes = sieveEratosthenes(n);
	for (int i = 10; i <= n; i++) {
		if (isTruncLeftToRight(i, primes) && isTruncRightToLeft(i, primes)) {
			count++;
			sum += i;
		}
	}
	std::cout << "We can confirm that there are " << count << " such truncatable primes. The solution is " << sum << std::endl;
	return 0;
}

bool isTruncLeftToRight(int n, bool p[]) {
	std::string nAsStr = std::to_string(n);
	for (int i = 0; i < nAsStr.length(); i++) {
		if (!p[atoi(nAsStr.substr(i).c_str())]) return false;
	}
	return true;
}

bool isTruncRightToLeft(int n, bool p[]) {
	std::string nAsStr = std::to_string(n);
	for (int i = 0; i < nAsStr.length(); i++) {
		if (!p[atoi(nAsStr.substr(0, nAsStr.length() - i).c_str())]) return false;
	}
	return true;
}