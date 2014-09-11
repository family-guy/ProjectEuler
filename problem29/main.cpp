#include <iostream>
#include <vector>
/* Problem 29
 * multiplication: takes a dynamic array of numbers, multiplies by an integer and returns the result in a new dynamic array e.g. multiplication([1, 2, 3], 10) returns [1, 2, 3, 0]
 * reverse: reverses a dynamic array
 * exponent: exponent(a, b) returns a^b e.g. exponent(2, 9) returns [5, 1, 2]
 * isEqual: checks whether two dynamic arrays have the same values e.g. isEqual([5, 1, 2], [5, 1, 2]) returns true
 * isIn: returns whether a dynamic array of dynamic arrays contains a dynamic array given
*/

std::vector<int> multiplication(std::vector<int> const& dynArray, int n);

void reverseInPlace(std::vector<int>& dynArray);

std::vector<int> exponent(int a, int b);

bool isEqual(std::vector<int> const& d1, std::vector<int> const& d2);

bool isIn(std::vector<std::vector<int> > const& d1, std::vector<int> const& d2);

int main() {
	int lowerBound = 2; int upperBound = 100; int nbOfDistinctTerms = 0;
	std::vector<std::vector<int> > prevValues;
	for (int p = lowerBound; p <= upperBound; p++) {
		for (int q = lowerBound; q <= upperBound; q++) {
			if (!isIn(prevValues, exponent(p, q))) {
				nbOfDistinctTerms++; prevValues.push_back(exponent(p, q));
			}
		}
	}
	std::cout << "The solution is " << nbOfDistinctTerms << std::endl;
	return 0;
}

std::vector<int> multiplication(std::vector<int> const& dynArray, int n) {
	std::vector<int> result; int carryOver = 0; int p = 0;
	for (int i = 0; i < dynArray.size() - 1; i++) { // there is a -1 as the first element will be treated as a separate case
		p = dynArray[dynArray.size() - 1 - i] * n + carryOver;
		result.push_back(p % 10); carryOver = p / 10;
	}
	p = dynArray[0] * n + carryOver; // treat the case of the first element
	while (true) {
		result.push_back(p % 10);
		if (p / 10 == 0) break;
		else p /= 10;
	}	
	reverseInPlace(result); return result;
}

void reverseInPlace(std::vector<int>& dynArray){
	std::vector<int> copyOfDynArray;
	for (int i = 0; i < dynArray.size(); i++) {
		copyOfDynArray.push_back(dynArray[i]);
	}
	for (int i = 0; i < dynArray.size(); i++) {
		dynArray[i] = copyOfDynArray[copyOfDynArray.size() - 1 - i];
	}
}

std::vector<int> exponent(int a, int b) {
	std::vector<int> result; result.push_back(1);
	for (int i = 0; i < b; i++) {
		result = multiplication(result, a);
	}
	return result;
}

bool isEqual(std::vector<int> const& d1, std::vector<int> const& d2) {
	if (d1.size() != d2.size()) return false;
	for (int i = 0; i < d1.size(); i++) {
		if (d1[i] != d2[i]) return false;
	}
	return true;
}

bool isIn(std::vector<std::vector<int> > const& d1, std::vector<int> const& d2) {
	for (int i = 0; i < d1.size(); i++) {
		if (isEqual(d1[i], d2)) return true;
	}
	return false;
}

