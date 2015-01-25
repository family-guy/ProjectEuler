#include <iostream>
#include <vector>
#include "integer_arithmetic.h"
/**
 * Problem 75
 * http://en.wikipedia.org/wiki/Pythagorean_triple#Generating_a_triple
 */

/*
* Returns all primitive Pythagorean triples with hypoteneuse less than c
*/
std::vector<std::vector<int> > pythagPrimTrip(int c);

/*
* Returns all distinct pairs of positive integers (p, q), p < q, such that p^2 + q^2 <= n, (p, q) are coprime and q - p is odd
*/
std::vector<std::vector<int> > coprimePairs(int n);

int main() {
	int L = 1500000;
	std::vector<std::vector<int> > triples = pythagPrimTrip(L / 2);
	int *freq = new int[L + 1];
	for (int i = 0; i < triples.size(); i++) {
		int a = triples[i][0];
		int b = triples[i][1];
		int c = triples[i][2];
		for (int k = 1; k * (a + b + c) <= L; k++) {
			freq[k * (a + b + c)]++;
		}
	}
	int count = 0;
	for (int i = 0; i < L + 1; i++) {
		if (freq[i] == 1) count++;
	}
	std::cout << "The solution is " << count << std::endl;
	return 0;
}

std::vector<std::vector<int> > pythagPrimTrip(int c) {
	std::vector<std::vector<int> > result;
	std::vector<std::vector<int> > pairs = coprimePairs(c);
	for (int i = 0; i < pairs.size(); i++) {
		std::vector<int> pythagTrip;
		int m = pairs[i][1];
		int n = pairs[i][0];
		pythagTrip.push_back(m * m - n * n);
		pythagTrip.push_back(2 * m * n);
		pythagTrip.push_back(m * m + n * n);
		result.push_back(pythagTrip);
	}
	return result; 
}

std::vector<std::vector<int> > coprimePairs(int n) {
	std::vector<std::vector<int> > result;
	for (int i = 1; i * i < n; i++) {
		for (int j = i + 1; j * j < n; j++) {
			if (i * i + j * j <= n) {
				if ((j - i) % 2 == 1) {
					if (gcd(i, j) == 1) {
						std::vector<int> pair;
						pair.push_back(i); pair.push_back(j);
						result.push_back(pair);
					}
				}
				
			}
		}
	}
	return result;
}