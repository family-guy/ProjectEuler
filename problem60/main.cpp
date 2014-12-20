#include <iostream>
#include <string>
#include <vector>
#include "integer_arithmetic.h"
/**
 * Problem 60
 */

/**
* Takes a set of prime pair sets of cardinality n and returns a set of prime pair sets of cardinality n + 1
*/
std::vector<std::vector<int> > setsOfPrimes(std::vector<std::vector<int> > currentSets, bool isPrime[], int primes[], int n);

/**
* Returns whether a pair of primes is a prime pair
*/
bool isPrimePair(int a, int b, bool isPrime[]);

/**
* Takes a set of primes and returns all the prime pairs that can be made from it
*/
std::vector<std::vector<int> > primePairs(bool isPrime[], int primes[], int n);

/**
* Takes a set of primes and a prime p and returns whether each pair that includes p is a prime pair
*/
bool hasPrimeSetProp(std::vector<int> primeSet, int p, bool isPrime[]);

/**
* Returns the sum of the elements of a dynamic array
*/
int sumElts(std::vector<int> T);

int main() {
	int n = 100000000;
	bool *isPrime = sieveEratosthenes(n);
	int count = 0;
	int m = 10000; // assumes that there exists a set of five primes with the desired property such that each prime in the set is less than 10000
	for (int i = 0; i < m; i++) {
		if (isPrime[i]) count++;
	}
	int *primes = new int[count]; int j = 0;
	for (int i = 0; i < m; i++) {
		if (isPrime[i]) {
			primes[j] = i;
			j++;
		} 
	}
	std::vector<std::vector<int> > T = primePairs(isPrime, primes, count);
	int minSum = m * 5;
	std::vector<std::vector<int> > currentSets = primePairs(isPrime, primes, count); // initialise the 2D dynamic array. Needs to be iterated on 3 times
	for (int i = 0; i < 3; i++) {
		std::vector<std::vector<int> > results = setsOfPrimes(currentSets, isPrime, primes, count);
		currentSets.clear(); // clear the array before doing a deep copy
		for (int k = 0; k < results.size(); k++) {
			currentSets.push_back(results[k]);
		}
	}
	
	// Get the minimum sum from currentSets
	for (int i = 0; i < currentSets.size(); i++) {
		int sum = sumElts(currentSets[i]);
		if (sum < minSum) minSum = sum;
	}
	std::cout << "The solution is " << minSum << std::endl;
	return 0;
}

std::vector<std::vector<int> > setsOfPrimes(std::vector<std::vector<int> > currentSets, bool isPrime[], int primes[], int n) {
	std::vector<std::vector<int> > result;
	std::vector<int> sums; // used to reduce number of duplicates
	for (int t = 0; t < n; t++) {
		for (int i = 0; i < currentSets.size(); i++) {
			std::vector<int> currentSet = currentSets[i];
			int sum = sumElts(currentSet);
			if (hasPrimeSetProp(currentSet, primes[t], isPrime) && std::find(sums.begin(), sums.end(), sum) == sums.end()) {
				sums.push_back(sum); 
				std::vector<int> resultSet;
				for (int j = 0; j < currentSet.size(); j++) {
					resultSet.push_back(currentSet[j]);
				}
				resultSet.push_back(primes[t]);
				result.push_back(resultSet);
			}
		}
	}
	return result;
}

bool isPrimePair(int a, int b, bool isPrime[]) {
	std::string aAsStr = std::to_string(a);
	std::string bAsStr = std::to_string(b);
	return isPrime[atoi((aAsStr + bAsStr).c_str())] && isPrime[atoi((bAsStr + aAsStr).c_str())];
} 

std::vector<std::vector<int> > primePairs(bool isPrime[], int primes[], int n) {
	std::vector<std::vector<int> > result;
	for (int i = 0; i < n; i++) {
		for (int j = i + 1; j < n; j++) {
			if (isPrimePair(primes[i], primes[j], isPrime)) {
				std::vector<int> pair;
				pair.push_back(primes[i]); pair.push_back(primes[j]);
				result.push_back(pair);
			}
		}
	}
	return result;
}

bool hasPrimeSetProp(std::vector<int> primeSet, int p, bool isPrime[]) {
	for (int i = 0; i < primeSet.size(); i++) {
		if (!isPrimePair(primeSet[i], p, isPrime)) return false;
	}
	return true;
}

int sumElts(std::vector<int> T) {
	int sum = 0;
	for (int i = 0; i < T.size(); i++) {
		sum += T[i];
	}
	return sum;
}