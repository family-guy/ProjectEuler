#include <iostream>
#include <vector>
#include "integer_arithmetic.h"
/* Problem 47
 * primeFacts: takes an integer >= 2 and returns its prime factors in a dynamic array e.g 90 returns [2, 3, 5]
 * getConsecNbs: takes two positive integers a and b and returns in an array b consecutive numbers starting from a e.g getConsecNbs(4, 3) returns {4, 5, 6}
 * getSmallestNb: given a natural number n, returns the smallest number y such that y, y + 1, ..., y + (n - 1) each have n distinct prime factors
*/

std::vector<int> primeFacts(int n, bool P[]);

int * getConsecNbs(int a, int b);

int getSmallestNb(int n, bool P[]);

int main() {
	int n = 500000000;
	bool *primes = sieveEratosthenes(n);
	std::cout << "The solution is " << getSmallestNb(4, primes) << std::endl;
	return 0;
}

std::vector<int> primeFacts(int n, bool P[]) { // this could be replaced with a function that returns the number of distinct prime factors. The array is not necessary but was used to ascertain that the correct prime factors were being generated
	std::vector<int> result;
	int i = 0;
	while (true) {
		while (!P[i]) i++;
		if (n % i == 0) {
			result.push_back(i);
			while (n % i == 0) n /= i;
		}
		if (n == 1) break;
		i++;
	}
	return result;
}

int * getConsecNbs(int a, int b) {
	int *result = new int[b];
	for (int i = 0; i < b; i++) {
		result[i] = a;
		a++;
	}
	return result;
}

int getSmallestNb(int n, bool P[]) {
	int i = 2; int j = 0;
	while (true) {
		int *consec = getConsecNbs(i, n);
		while (primeFacts(consec[j], P).size() == n && j < n - 1) j++;
		if (j == n - 1 && primeFacts(consec[j], P).size() == n) break;
		i++; j = 0;
	}
	return i;
}