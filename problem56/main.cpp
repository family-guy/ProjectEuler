#include <iostream>
#include <string>
/**
 * Problem 56
 */

// Takes two integers, one represented as a string, and returns their product represented as a string
std::string multiply(std::string a, int b);

// Calculates a^b
std::string exponent(int a, int b);

// Takes an integer represented as a string and returns the sum of its digits
int sumDigits(std::string n);

int main() {
	int max = 0;
	for (int a = 1; a < 100; a++) {
		for (int b = 1; b < 100; b++) {
			std::string exp = exponent(a, b);
			int value = sumDigits(exp);
			if (value > max) max = value;
		}
	}
	std::cout << "The solution is " << max << std::endl;
	return 0;
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

std::string exponent(int a, int b) {
	std::string result = "1";
	for (int i = 0; i < b; i++) {
		result = multiply(result, a);
	}
	return result;
}

int sumDigits(std::string n) {
	int sum = 0;
	for (int i = 0; i < n.length(); i++) {
		sum += atoi(n.substr(i, 1).c_str());
	}
	return sum;
}