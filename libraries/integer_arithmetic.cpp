#include <stdlib.h>
#include "integer_arithmetic.h"

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
