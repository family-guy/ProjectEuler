#include <iostream>
#include <map>
#include <vector>
#include "combinatorics.h"
/**
 * Problem 74
 */

/**
* Returns a given integer as a dynamic array e.g. convertIntToArray(123) = [1, 2, 3]
*/
std::vector<int> convertIntToArray(int n);

/**
* Takes a dynamic array of positive integers and returns the sum of their factorials
*/
int sumFact(std::vector<int> T);

/**
* Returns the number of non-repeating terms for a given integer
*/
int nbNonRepeatTerms(int n);

std::map<int, int> loops;

int main() {
	loops[1] = 0;
	loops[2] = 0;
	loops[145] = 0;
	loops[40585] = 0;
	loops[169] = 2; loops[363601] = 2; loops[1454] = 2;
	loops[871] = 1; loops[45361] = 1;
	loops[872] = 1; loops[45362] = 1;
	int sum = 0;
	for (int i = 1; i < 1000000; i++) {
		if (nbNonRepeatTerms(i) == 60) sum++;
	}
	std::cout << "The solution is " << sum << std::endl;
	return 0;
}

std::vector<int> convertIntToArray(int n) {
	std::vector<int> result;
	int divisor = 1; int dividend = n;
	while (true) {
		if (divisor * 10 > n) break;
		divisor *= 10;
	}
	while (divisor > 0) {
		result.push_back(dividend / divisor);
		dividend %= divisor;
		divisor /= 10;
	}
	return result;
}

int sumFact(std::vector<int> T) {
	int sum = 0;
	for (int i = 0; i < T.size(); i++) {
		sum += fact(T[i]);
	}
	return sum;
}

int nbNonRepeatTerms(int n) {
	int result = 1;
	int currentVal = n;
	while (true) {
		if (loops.find(currentVal) != loops.end()) return result + loops[currentVal];
		result++;
		currentVal = sumFact(convertIntToArray(currentVal));
	}
}
