#include <iostream>
#include <string>
#include <vector>
#include "integer_arithmetic.h"
#include "combinatorics.h"
/* Problem 49
 * print: takes a dynamic array of strings and displays its contents
*/

void print(std::vector<std::string> T);

int main() {
	std::vector<std::string> solutions;
	bool *primes = sieveEratosthenes(9999);
	std::vector<int> fourDigitPrimes;
	for (int i = 1000; i < 10000; i++) {
		if (primes[i]) fourDigitPrimes.push_back(i);
	}
	for (int i = 0; i < fourDigitPrimes.size() - 2; i++) {
		int ithElt = fourDigitPrimes[i];
		for (int j = i + 1; j < fourDigitPrimes.size() - 1; j++) {
			int jthElt = fourDigitPrimes[j];
			if (isPerm(ithElt, jthElt)) {
				int difference = jthElt - ithElt; int thirdTerm = jthElt + difference;
				if (thirdTerm < 10000 && primes[thirdTerm] && isPerm(ithElt, thirdTerm)) {
					solutions.push_back(std::to_string(ithElt) + std::to_string(jthElt) + std::to_string(thirdTerm));
				}
			}
		}
	}
	std::cout << "The concatenations of the two possible sequences are ";
	print(solutions);
	for (int i = 0; i < solutions.size(); i++) {
		if (solutions[i] != "148748178147") std::cout << "Therefore the solution is " << solutions[i] << std::endl;
	}
	return 0;
}

void print(std::vector<std::string> T) {
	for (int i = 0; i < T.size(); i++) {
		std::cout << T[i] << " ";
	}
	std::cout << std::endl;
}