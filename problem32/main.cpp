#include <iostream>
#include <vector>
#include <cmath>
/* Problem 32
 * convertIntToArray: returns a given integer as a dynamic array e.g. convertIntToArray(123) = [1, 2, 3]
 * nbOccurrences: returns the number of occurrences of a given integer in a given dynamic array
 * isPandigital: returns whether a dynamic array of integers, where each integer is in {0, 1, ..., 9}, is a permutation of {1, ..., 9}
 * concat: concatenates the second dynamic array to the first dynamic array
 * sumSolutions: returns the sum of products made from a n digit multiplier and a m digit multiplicand
*/

std::vector<int> convertIntToArray(int n);

bool isPandigital(const std::vector<int> & T);

int nbOccurrences(const std::vector<int> & T, int n);

void concat(std::vector<int> & T, const std::vector<int> & U);

int sumSolutions(int n, int m);

int main() {
	std::cout << "The solution is " << sumSolutions(2, 3) + sumSolutions(1, 4) << std::endl;
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

int nbOccurrences(const std::vector<int> & T, int n) {
	int count = 0;
	for (int i = 0; i < T.size(); i++) {
		if (T[i] == n) count++;
	}
	return count++;
}

bool isPandigital(const std::vector<int> & T) {
	if (T.size() != 9) return false;
	for (int i = 1; i < 10; i++) {
		if (nbOccurrences(T, i) != 1) return false;
	}
	return true;
}

void concat(std::vector<int> & T, const std::vector<int> & U) {
	for (int i = 0; i < U.size(); i++) {
		T.push_back(U[i]);
	}
}

int sumSolutions(int n, int m) {
	std::vector<int> solutions; int sum = 0; int k = 0;
	for (int i = (int)pow(10, n - 1); i < (int)pow(10, n); i++) {
		for (int j = (int)pow(10, m - 1); j < (int)pow(10, m); j++) {
			std::vector<int> multiplier = convertIntToArray(i); std::vector<int> multiplicand = convertIntToArray(j);
			std::vector<int> product = convertIntToArray(i * j); concat(product, multiplier); concat(product, multiplicand);
			if (isPandigital(product)) {
				if (solutions.empty()) {
					solutions.push_back(i * j); sum += i * j;
				}
				else {
					while (true) {
						if (i * j == solutions[k] || k == solutions.size()) break;
						k++;
					}
					if (k == solutions.size()) {
						solutions.push_back(i * j); sum += i * j;
					}
					k = 0;
				} 
			}
		}
	}
	return sum;
}

