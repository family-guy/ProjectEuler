#include <iostream>
#include <string>
#include "combinatorics.h"
/* Problem 43
 * hasProp: takes a 0 to 9 pandigital number and returns whether it has the property given in the question
*/

bool hasProp(std::string s, int T[], int n);

bool hasProp(std::string s);

int main() {
	std::string digits = "0123456789";
	std::string *permutations = permutationsOfAString(digits);
	long int sum = 0;
	for (int i = 0; i < fact(digits.length()); i++) {
		if (hasProp(permutations[i])) {
			long int n = atol(permutations[i].c_str());
			sum += n;
		}
	}
	std::cout << "The solution is " << sum << std::endl;
	return 0;
}

bool hasProp(std::string s, int T[], int n) {
	for (int i = 0; i < n; i++) {
		std::string subStr = s.substr(i + 1, 3);
		int subStrAsInt = atoi(subStr.c_str());
		if (subStrAsInt % T[i] != 0) return false;
	}
	return true;
}

bool hasProp(std::string s) {
	int T[] = {2, 3, 5, 7, 11, 13, 17};
	int lengthOfT = sizeof(T) / sizeof(T[0]);
	return hasProp(s, T, lengthOfT);
}