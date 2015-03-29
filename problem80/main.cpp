#include <iostream>
#include <cmath>
#include <vector>
#include <string>
#include "integer_arithmetic.h"
/**
 * Problem 80. Runtime: >> 60s on a 1.4 GHz Intel Core i5
 */

/**
* Returns the sum of digits of an integer represented as a string
*/
int sumDigits(std::string str);

/**
* Performs division of two integers represented as strings to 99 decimal places
*/
std::string divide(std::string a, std::string b);

/**
* Continued fraction expansion to n iterations, n greater than or equal to 2
*/
std::string * contFrac(std::vector<int> contFracRep, int n);

std::string * f(std::string a, std::string b_1, std::string b_2);

/*
* Returns whether a positive integer is a square
*/
bool isSquare(int n);

/*
* Takes a triple (a, b, c) representing a fraction of the form a / (sqrt(b) + c) and returns the next fraction (of the same form) in the sequence
*/
int * nextTerm(int T[], std::vector<int> &values);

/*
* Takes a positive non-square integer and returns its continued fraction expansion in array form
*/
void getContFracExpInPlace(int n, std::vector<int> &values);

/*
* Returns if two arrays of integers are equal
*/
bool equals(int T[], int TLen, int U[], int ULen);

int main() {
	std::vector<std::vector<int> > contFracReps;
	for (int i = 1; i <= 100; i++) {
		if (!isSquare(i)) {
			std::vector<int> row;
			getContFracExpInPlace(i, row);
			contFracReps.push_back(row);
		} 
	}
	int sum = 0;
	for (int i = 1; i <= 100; i++) {
		if (!isSquare(i)) sum += (int)sqrt(i);
	}
	for (int i = 0; i < contFracReps.size(); i++) {
		std::string currentVal = "";
		int k = 2;
		while (true) {
			std::string nextVal = divide(contFrac(contFracReps[i], k)[1], contFrac(contFracReps[i], k)[0]);
			if (currentVal == nextVal) break;
			currentVal = nextVal;
			k++;
		}
		sum += sumDigits(currentVal);
	}
	std::cout << "The solution is: " << sum << std::endl;
	return 0;
}

int sumDigits(std::string str) {
	int result = 0;
	for (int i = 0; i < str.length(); i++) {
		result += atoi(str.substr(i, 1).c_str());
	}
	return result;
}

std::string divide(std::string a, std::string b) {
	std::string result = "";
	std::string remainder = "0";
	std::string quotient = "0";
	std::string dividend = a;
	for (int i = 0; i < 99; i++) {
		dividend += "0";
		std::string *quotRem = quotientRemainder(dividend, b);
		quotient = quotRem[0];
		result += quotient;
		remainder = quotRem[1];
		if (remainder == "0") {
			while (result.length() < 100) {
				result += "0";
			}
			break;
		}
		dividend = remainder;
	}
	return result;
}

std::string * contFrac(std::vector<int> contFracRep, int n) {
	std::string  *currentFrac = new std::string[2];
	currentFrac[0] = std::to_string(contFracRep[(n - 1) % contFracRep.size()]);
	currentFrac[1] = "1";
	for (int i = n - 2; i > -1; i--) {
		std::string *next = f(std::to_string(contFracRep[i % contFracRep.size()]), currentFrac[0], currentFrac[1]);
		currentFrac[0] = next[0];
		currentFrac[1] = next[1];
	}
	return currentFrac;	
}

std::string * f(std::string a, std::string b_1, std::string b_2) {
	std::string *result = new std::string[2];
	result[1] = b_1;
	result[0] = sum(product(a, b_1), b_2);
	return result;
}

bool isSquare(int n) {
	int a = (int)sqrt(n);
	return a * a == n;
}

int * nextTerm(int T[], std::vector<int> &values) {
	int *result = new int[3];
	result[1] = T[1];
	int intermedFracDen = T[1] - T[2] * T[2];
	int intermedFracWholePart = (int)(T[0] * (sqrt(T[1]) - T[2]) / intermedFracDen);
	values.push_back(intermedFracWholePart);
	result[0] = intermedFracDen / T[0]; 
	result[2] = (-1) * T[2] - intermedFracWholePart * result[0];
	return result;
}

void getContFracExpInPlace(int n, std::vector<int> &values) {
	int a_0 = (int)sqrt(n);
	int firstTriple[] = {1, n, (-1) * a_0};
	int currentTriple[] = {1, n, (-1) * a_0};
	while (true) {
		int *next = nextTerm(currentTriple, values);
		if (equals(next, 3, firstTriple, 3)) break;
		for (int i = 0; i < 3; i++) {
			currentTriple[i] = next[i];
		}
	}
}

bool equals(int T[], int TLen, int U[], int ULen) {
	if (TLen != ULen) return false;
	for (int i = 0; i < TLen; i++) {
		if (T[i] != U[i]) return false;
	}
	return true;
}

