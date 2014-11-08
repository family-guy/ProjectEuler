#include <iostream>
#include "integer_arithmetic.h"
/* Problem 50
 * consecutivePrimes: takes a prime as a starting value and returns in an array the prime, below a certain number, which can be written as the sum of the most consecutive primes as well as the number of consecutive primes that make up the sum 
*/

int * consecutivePrimes(int a, int P[], bool Q[], int q); // q is the length of Q

int main() {
	int n = 1000000;
	bool *isPrime = sieveEratosthenes(n);
	// get the number of primes under n
	int count = 0;
	for (int i = 0; i < n + 1; i++) {
		if (isPrime[i]) count++;
	}
	int *primes = new int[count]; int j = 0;
	// fill in primes
	for (int i = 0; i < n + 1; i++) {
		if (isPrime[i]) {
			primes[j] = i;
			j++;
		}
	}
	int mostConsecPrimes = 0; int sumOfMostConsecPrimes = 2;
	for (int i = 0; i < count - 1; i++) {
		int *consecPrimes = consecutivePrimes(i, primes, isPrime, n + 1);
			if (consecPrimes[0] > mostConsecPrimes) {
				mostConsecPrimes = consecPrimes[0];
				sumOfMostConsecPrimes = consecPrimes[1];
			}
		}
		std::cout << "The solution is " << sumOfMostConsecPrimes << " which can be written as the sum of " << mostConsecPrimes << " consecutive primes" << std::endl;
	return 0;
}

int * consecutivePrimes(int a, int P[], bool Q[], int q) { 
	int *result = new int[2];
	int longestStreak = 0; int longestStreakPrime = 2;
	int sum = 0; int difference = a;
	while (true) {
		sum += P[a];
		if (sum > q - 1) break;
		if (Q[sum]) {
			longestStreak = a + 1 - difference;
			longestStreakPrime = sum;
		}
		a++;
	}
	result[0] = longestStreak; result[1] = longestStreakPrime;
	return result;
}