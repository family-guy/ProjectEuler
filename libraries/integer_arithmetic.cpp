#include <iostream>
#include <vector>
#include <string>
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

bool greaterThan(std::string a, std::string b) {
	if (a.length() < b.length()) return false;
	if (a.length() == b.length()) {
		for (int i = 0; i < a.length(); i++) {
			std::string aDigit = a.substr(i, 1);
			std::string bDigit = b.substr(i, 1);
			if (aDigit != bDigit) return atoi(aDigit.c_str()) > atoi(bDigit.c_str());
		}
		return false;
	}
	return true;
}

std::string sum(std::string a, std::string b) {
	std::string result = "";
	int difference = a.length() - b.length();
	for (int i = 0; i < difference; i++) {
		b = "0" + b;
	}
	int carryOver = 0;
	for (int i = a.length() - 1; i > -1; i--) {
		int x = atoi(a.substr(i, 1).c_str());
		int y = atoi(b.substr(i, 1).c_str());
		int value = (x + y + carryOver) % 10;
		result = std::to_string(value) + result;
		carryOver = (x + y + carryOver) / 10;
	}
	if (carryOver == 0) return result;
	return std::to_string(carryOver) + result;
}

std::string difference(std::string a, std::string b) {
	std::string result = "";
	int *A = new int[a.length()];
	int *B = new int[a.length()];
	for (int i = 0; i < a.length(); i++) {
		A[i] = atoi(a.substr(i, 1).c_str());
	}
	int diffLengths = a.length() - b.length();
	for (int i = diffLengths; i < a.length(); i++) {
		B[i] = atoi(b.substr(i - diffLengths, 1).c_str());
	}
	for (int i = a.length() - 1; i > -1; i--) {
		if (A[i] < B[i]) {
			int k = 1;
			while (true) {
				if (A[i - k] != 0) {
					A[i - k]--;
					break;
				}
				A[i - k] = 9;
				k++;
			}
			result = std::to_string(A[i] + 10 - B[i]) + result;
		}
		else result = std::to_string(A[i] - B[i]) + result;
	}
	std::string resultWithoutLeadingZeros = "";
	int j = 0;
	for (j = 0; j < result.length() - 1; j++) {
		if (result.substr(j, 1) != "0") break;
	}
	for (int l = j; l < result.length(); l++) {
		resultWithoutLeadingZeros += result.substr(l, 1);
	}
	return resultWithoutLeadingZeros;
}

std::string * quotientRemainder(std::string a, std::string b) {
	std::string *result = new std::string[2];
	std::string quotient = "0";
	while (true) {
		if (greaterThan(b, a)) break;
		a = difference(a, b);
		quotient = sum(quotient, "1");
	}
	result[0] = quotient;
	result[1] = a;
	return result;
}

std::string digitProduct(int digit, std::string b) {
	if (digit == 0) return "0";
	if (digit == 1) return b;
	return sum(digitProduct(digit - 1, b), b);
}

std::string product(std::string a, std::string b) {
	std::string result = digitProduct(atoi(b.substr(0, 1).c_str()), a);
	for (int i = 0; i < b.length() - 1; i++) {
		result += "0";
	}
	for (int i = 1; i < b.length(); i++) {
		std::string substr = b.substr(i, 1);
		std::string str = digitProduct(atoi(substr.c_str()), a);
		for (int j = i; j < b.length() - 1; j++) {
			str += "0";
		}
		result = sum(result, str);
	}
	return result;
}