#include <iostream>
#include <vector>
#include "integer_arithmetic.h"

std::vector<std::vector<int> > primeDecomp(int n, int primes[], int lengthOfPrimes) {
	std::vector<std::vector<int> > solution;
	return primeDecomp(n, primes, lengthOfPrimes, solution, 0);
}

std::vector<std::vector<int> > primeDecomp(int n, int primes[], int lengthOfPrimes, std::vector<std::vector<int> > &solution, int lastIndex) {
	std::vector<int> primeFact;
	for (int i = lastIndex; i < lengthOfPrimes; i++) {
		int p = primes[i];
		if (n % p == 0) {
			primeFact.push_back(p);
			int expPrimeFact = 1;
			n /= p;
			while (true) {
				if (n % p == 0) {
					expPrimeFact++;
					n /= p;
				} 
				else break;
			}
			primeFact.push_back(expPrimeFact);
			solution.push_back(primeFact);
			if (n == 1) return solution;
			return primeDecomp(n, primes, lengthOfPrimes, solution, i + 1);
		}
	}
	primeFact.push_back(n);
	primeFact.push_back(1);
	solution.push_back(primeFact);
	return solution;
}

bool* sieveEratosthenes(int n) {
	bool *result = new bool[n + 1];
	for (int i = 0; i < n + 1; i++) {
		result[i] = true;
	}
	result[0] = false; result[1] = false;
	for (int i = 0; i * i < n; i++) {
		if (result[i] == true) {
			for (int j = 2; i * j <= n; j++) {
				result[i * j] = false;
			}
		}
	}
	return result;	
}

bool isPrime(long n) {
	if (n <= 1) return false;
	else {
		for (int i = 2; i * i <= n; i++) {
			if (n % i == 0) return false;
		}
	}
	return true;
}

int sumOfPropDiv(int n) {
	int sum = 0;
	for (int i = 2; i * i <= n; i++) {
		if (n % i == 0) {
			if (i != n / i) sum += i + n / i;
			else sum += i;
		}
	}
	return sum + 1;
}

int gcd(int a, int b) {
	if (b % a == 0) return a;
	return gcd(b % a, a);
}