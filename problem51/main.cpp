#include <iostream>
#include <string>
#include "integer_arithmetic.h"
#include "combinatorics.h"
/* Problem 51
 * next: Suppose that we enumerate all k-combinations of a set S of n elements in lexicographic order e.g. if S = {1, 2, 3, 4, 5}, k = 3, then we have {1, 2, 3}, {1, 2, 4}, {1, 2, 5}, {1, 3, 4}, {1, 3, 5}, {1, 4, 5}, {2, 3, 4}, {2, 3, 5}, {2, 4, 5}, {3, 4, 5}. Given one of these k-combinations in the form of an array and an integer representing n, this function returns the next k-combination e.g. next([1, 4, 5], 5) returns [2, 3, 4]
* isIn: takes an integer and an array of integers and returns whether that integer is in the array
* isPrimeFamAux: takes a prime, a positive integer m and a k-combination and returns if that k-combination yields a m prime value family 
* isPrimeFam: takes a prime and an integer m and returns whether that prime is part of a m prime value family
*/

int * next(int T[], int t, int n); // t is the length of T

bool isIn(int n, int T[], int t); // t is the length of T

bool isPrimeFamAux(int n, int m, int T[], int t, bool P[]); // t is the length of T

bool isPrimeFam(int n, int m, bool P[]);

int main() {
	int n = 5000000; // value of n is set arbitrarily
	bool *isPrime = sieveEratosthenes(n);
	// get the number of primes between 10 and n
	int nbPrimes = 0;
	for (int i = 0; i < n + 1; i++) {
		if (isPrime[i]) nbPrimes++;
	}
	// array of all primes between 10 and n
	int *primes = new int[nbPrimes]; int j = 0;
	for (int i = 10; i < n + 1; i++) {
		if (isPrime[i]) {
			primes[j] = i;
			j++;
		}
	}
	for (int i = 0; i < nbPrimes; i++) {
		if (isPrimeFam(primes[i], 8, isPrime)) {
			std::cout << "The solution is " << primes[i] << std::endl;
			break;
		}
	}
	return 0;
}

int * next(int T[], int t, int n) {
	int *result = new int[t];
	for (int i = 0; i < t; i++) {
		result[i] = T[i];
	}
	int i = 0;
	while (t - 1 - i > -1) {
		if (T[t - 1 - i] < n - i) {
			result[t - 1 - i]++;
			for (int j = t - i; j < t; j++) {
				result[j] = result[t - 1 - i] + 1 + (j - (t - i));
			}
			break;
		}
		i++;
	}
	return result;
}

bool isIn(int n, int T[], int t) {
	for (int i = 0; i < t; i++) {
		if (T[i] == n) return true;
	}
	return false;
}

bool isPrimeFamAux(int n, int m, int T[], int t, bool P[]) {
	std::string nAsStr = std::to_string(n);
	int count = 0; // counts the number of primes in the family generated
	if (t > 1) { // if the length of the array T is one no need to bother with the check below
		for (int i = 1; i < t; i++) { // checks to see if n has the same digits in the positions proposed by the combination i.e. the array T
			if (nAsStr.substr(T[i], 1) != nAsStr.substr(T[0], 1)) return false;
		}
	}
	if (T[0] == 0) {
		for (int i = 1; i <= 9; i++) {
			std::string generatedNb = "";
			std::string iAsStr = std::to_string(i);
			for (int j = 0; j < nAsStr.length(); j++) {
				if (isIn(j, T, t)) generatedNb += iAsStr;
				else generatedNb += nAsStr.substr(j, 1);
			}
			int generatedNbAsInt = atoi(generatedNb.c_str());
			if (P[generatedNbAsInt]) count++;
		}
		if (count == m) return true;
	}
	else { // do the same as above except start from 0 rather than 1
		for (int i = 0; i <= 9; i++) {
			std::string generatedNb = "";
			std::string iAsStr = std::to_string(i);
			for (int j = 0; j < nAsStr.length(); j++) {
				if (isIn(j, T, t)) generatedNb += iAsStr;
				else generatedNb += nAsStr.substr(j, 1);
			}
			int generatedNbAsInt = atoi(generatedNb.c_str());
			if (P[generatedNbAsInt]) count++;
		}
		if (count == m) return true;
	}
	return false;
}

bool isPrimeFam(int n, int m, bool P[]) {
	std::string nAsStr = std::to_string(n);
	for (int i = 1; i < nAsStr.length(); i++) {
		int *startingValue = new int[i];
		for (int j = 0; j < i; j++) {
			startingValue[j] = j;
		}
		int upperLimit = nChooseK(i, nAsStr.length());
		for (int k = 0; k < upperLimit; k++) {
			if (isPrimeFamAux(n, m, startingValue, i, P)) return true;
			startingValue = next(startingValue, i, nAsStr.length() - 1);
		}
	}
	return false;
}