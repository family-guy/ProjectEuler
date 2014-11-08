#include <iostream>
#include <string>
#include "combinatorics.h"
#include "integer_arithmetic.h"
/* Problem 41
 * permutationsOfAString: returns all permutations of a string in an array
 * isPrime: returns whether a positive integer is prime
 * fact: returns the factorial of a non-negative integer
*/

int main() {
	std::string T[] = {"123456789", "12345678", "1234567", "123456", "12345", "1234", "123", "12"};
	int lengthOfT = sizeof(T) / sizeof(T[0]);
	int nbPanPrime = 0; int max = 0;
	for (int i = 0; i < lengthOfT; i++) {
		std::string *permutations = permutationsOfAString(T[i]);
		int lengthOfPermutations = fact(T[i].length());
		for (int j = 0; j < lengthOfPermutations; j++) {
			int p = atoi(permutations[j].c_str());
			if (isPrime(p)) {
				nbPanPrime++;
				if (p > max) max = p;
			}
		}
		if (nbPanPrime > 0) break;
	}
	std::cout << "The solution is " << max << std::endl;
	return 0;
}
