#include <iostream>
#include <vector>
#include <cmath>
#include <integer_arithmetic.h>
/* Problem 35
 * convertIntToArray: returns a given integer as a dynamic array e.g. convertIntToArray(123) = [1, 2, 3]
 * isPrimeCircular: takes a prime and returns whether it is circular
*/

std::vector<int> convertIntToArray(int n);

bool isPrimeCircular(int p, bool P[]);

int main() {
	int N = 1000000;
	/* create an array of all primes less than N */
	bool *P = sieveEratosthenes(N);
	std::vector<int> allPrimesUnderN;
	for (int i = 0; i < N + 1; i++) {
		if (P[i]) allPrimesUnderN.push_back(i);
	} // done
	int count = 0;
	for (int i = 0; i < allPrimesUnderN.size(); i++) {
		if (isPrimeCircular(allPrimesUnderN[i], P)) count++;
	}
	std::cout << "The solution is " << count << std::endl;
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

bool isPrimeCircular(int p, bool P[]) { 
	std::vector<int> pAsArray = convertIntToArray(p);
	if (pAsArray.size() == 1) return true;
	else {
		int pNbDigits = pAsArray.size(); int *pRotated = new int[pNbDigits];
		for (int nbRotations = 1; nbRotations < pNbDigits; nbRotations++) { 
			for (int i = 0; i < pNbDigits; i++) {
				pRotated[i] = pAsArray[(i + nbRotations) % pNbDigits];
			}
			/* convert pRotated to an integer and check if it is prime */ 
			int sum = 0;
			for (int j = pNbDigits - 1; j >= 0; j--) { 
				sum += (int)pow(10, pNbDigits - 1 - j) * pRotated[j];
			}
			if (!P[sum]) return false;
		}
		return true;
	}
}



