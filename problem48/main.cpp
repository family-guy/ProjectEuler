#include <iostream>
#include <string>
/* Problem 48
 * multiply: takes a positive integer and an array of 10 integers and returns an array which is the original array multiplied by the integer e.g. multiply(5, {0, 0, 0, 0, 0, 0, 1, 0, 2, 3}) returns {0, 0, 0, 0, 0, 0, 5, 1, 1, 5}
 * power: returns the last 10 digits in an array e.g. power(2, 9) = {0, 0, 0, 0, 0, 0, 0, 5, 1, 2}
 * add: takes two arrays representing the last 10 digits of two integers and returns the last 10 digits of their sum e.g. add({0, 0, 0, 0, 0, 0, 0, 1, 2, 4}, {0, 0, 0, 0, 0, 0, 1, 6, 0, 1}) returns {0, 0, 0, 0, 0, 0, 1, 7, 2, 5}
*/

int * multiply(int n, int T[], int t);

int * power(int a, int b);

int * add(int T[], int t, int U[], int u);

int main() {
	int n = 1000; int resultLength = 10;
	int *result = new int[resultLength];
	for (int i = 1; i <= n; i++) {
		result = add(result, resultLength, power(i, i), 10);
	}
	std::string str = "";
	for (int i = 0; i < resultLength; i++) {
		str += std::to_string(result[i]);
	}
	std::cout << "The solution is " <<  str << std::endl;
	return 0;
}

int * multiply(int n, int T[], int t) { // t is the length of T
	int *result = new int[10]; int carryOver = 0;
	for (int i = t - 1; i > -1; i--) {
		result[i] = (n * T[i] + carryOver) % 10;
		carryOver = (n * T[i] + carryOver) / 10;
	}
	return result;
}

int * power(int a, int b) {
	int resultLength = 10;
	int *result = new int[resultLength];
	if (b == 0) {
		result[resultLength - 1] = 1;
		return result;
	}
	std::string aAsStr = std::to_string(a); int j = 0;
	for (int i = aAsStr.length() - 1; i > -1; i--) {
		result[resultLength - 1 - j] = atoi(aAsStr.substr(i, 1).c_str());
		j++;
	}
	for (int i = 1; i < b; i++) {
		result = multiply(a, result, resultLength);
	}
	return result;
}

int * add(int T[], int t, int U[], int u) { // t and u are the lengths of T and U respectively
	int *result = new int[10]; int carryOver = 0;
	for (int i = t - 1; i > -1; i--) {
		result[i] = (T[i] + U[i] + carryOver) % 10;
		carryOver = (T[i] + U[i] + carryOver) / 10;
	}
	return result;
}

