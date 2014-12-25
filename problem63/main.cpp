#include <iostream>
#include <string>
/**
 * Problem 63
 */

/*
* Returns the powerful digit count for a given base
*/
int nbNDigitIntsNthPow(int base);

/**
* Takes two integers, one represented as a string, and returns their product represented as a string
*/
std::string multiply(std::string a, int b);

/**
* Calculates a^b
*/
std::string power(int a, int b);

int main() {
	int sum = 0;
	for (int i = 1; i < 10; i++) {
		sum += nbNDigitIntsNthPow(i);
	}
	std::cout << "The solution is " << sum << std::endl;
	return 0;
}

int nbNDigitIntsNthPow(int base) {
	int result = 0;
	int exponent = 1;
	while (true) {
		std::string str = power(base, exponent);
		if (str.length() == exponent) result++;
		if (str.length() < exponent) break;
		exponent++;
	}
	return result;
}

std::string multiply(std::string a, int b) {
	std::string result = "";
	int carryOver = 0; int x = 0; int i = 0;
	for (i = a.length() - 1; i > 0; i--) {
		x = atoi(a.substr(i, 1).c_str());
		int value = (x * b + carryOver) % 10;
		result = std::to_string(value) + result;
		carryOver = (x * b + carryOver) / 10;
	}
	x = atoi(a.substr(i, 1).c_str());
	return std::to_string(x * b + carryOver) + result;
}

std::string power(int a, int b) {
	std::string result = "1";
	for (int i = 0; i < b; i++) {
		result = multiply(result, a);
	}
	return result;
}