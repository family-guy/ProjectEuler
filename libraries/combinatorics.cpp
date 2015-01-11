#include <iostream>
#include <string>
#include "combinatorics.h"
/**
 * Please refer to combinatorics.h for function descriptions
 */

// Auxiliary functions

std::string insertCharAt(std::string str, std::string ch, int i) {
	if (i == 0) return ch + str;
	if (i == str.length()) return str + ch;
	else return str.substr(0, i) + ch + str.substr(i);	
}

std::string removeCharAt(std::string str, int i) {
	return str.substr(0, i) + str.substr(i + 1);
}

std::string * allNewStrings(std::string str, std::string ch) {
	std::string *result = new std::string[str.length() + 1];
	for (int i = 0; i < str.length() + 1; i++) {
		result[i] = insertCharAt(str, ch, i);
	}
	return result;
}

std::string * allNewStrings(std::string T[], std::string ch, int l) { // l is the length of the array T
	int k = T[0].length() + 1;
	std::string *result = new std::string[l * k]; 
	int j = -1;
	for (int i = 0; i < l * k; i++) {
		if (i % k == 0) j++;
		result[i] = allNewStrings(T[j], ch)[i % k];			
	}
	return result;
}

// Functions

int * next(int T[], int t, int n) { 
	int * result = new int[t];
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

int ** enumNchooseK(int k, int n) {
	int **result = new int * [nChooseK(k, n)];
	for (int i = 0; i < nChooseK(k, n); i++) {
		result[i] = new int[k];
	}
	for (int j = 0; j < k; j++) {
		result[0][j] = j + 1;
	}
	for (int i = 1; i < nChooseK(k, n); i++) {
		int *nextEnum = next(result[i - 1], k, n);
		for (int j = 0; j < k; j++) {
			result[i][j] = nextEnum[j];
		}
	}
	return result;
}

int fact(int n) {
	if (n == 0) return 1;
	int result = 1;
	for (int i = 1; i <= n; i++) {
		result *= i;
	}
	return result;
}

int nChooseK(int k, int n) {
	if (k == 0) return 1;
	int result = 1;
	for (int i = 1; i < k + 1; i++) {
		result *= n - (k - i);
		result /= i;
	}
	return result;
}

bool isPerm(int a, int b) {
	std::string aAsStr = std::to_string(a); std::string bAsStr = std::to_string(b);
	if (aAsStr.length() != bAsStr.length()) return false;
	int *A = new int[10]; int *B = new int[10];
	for (int i = 0; i < aAsStr.length(); i++) {
		A[atoi(aAsStr.substr(i, 1).c_str())]++;
		B[atoi(bAsStr.substr(i, 1).c_str())]++;
	}
	for (int i = 0; i < 10; i++) {
		if (A[i] != B[i]) return false;
	}
	return true;
}

std::string * permutationsOfAString(std::string str) {
	if (str.length() == 2) {
		std::string *result = new std::string[2];
		result[0] = str;
		result[1] = str.substr(1) + str.substr(0, 1);
		return result;
	}
	return allNewStrings(permutationsOfAString(removeCharAt(str, str.length() - 1)), str.substr(str.length() - 1), fact(str.length() - 1)); 
}

void showStrings(std::string T[], int l) {
	for (int i = 0; i < l; i++) {
		std::cout << T[i] << std::endl;
	}
}