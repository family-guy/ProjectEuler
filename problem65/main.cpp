#include <iostream>
#include <string>
/**
 * Problem 65
 */

/**
* Returns the sum of digits of an integer represented as a string
*/
int sumDigits(std::string str);

/**
* Takes a triple (a, b_1, b_2) and returns a + 1 / b where b = b_1 / b_2
*/
std::string * f(std::string a, std::string b_1, std::string b_2);

/**
* Returns the product of two integers, one of which is represented by a string
*/
std::string mult(int a, std::string b);

/**
* Takes two integers a and b, where a >= b, represented by strings, and returns a string representing their sum. 
*/
std::string add(std::string a, std::string b);

int main() {
	int valuesLength = 99;
	std::string *values = new std::string[valuesLength];
	for (int i = 0; i < valuesLength; i++) {
		if (i % 3 == 0 || i % 3 == 2) values[i] = "1";
		else {
			int x = (i / 3 + 1) * 2;
			values[i] = std::to_string(x);
		}
	}
	std::string *currentFrac = new std::string[2];
	currentFrac[0] = values[valuesLength - 1];
	currentFrac[1] = "1";
	for (int i = valuesLength - 2; i > -1; i--) {
		std::string currentInt = values[i];
		std::string *next = f(currentInt, currentFrac[0], currentFrac[1]);
		currentFrac[0] = next[0];
		currentFrac[1] = next[1];
	}
	std::cout << "The solution is " << sumDigits(add(mult(2, currentFrac[0]), currentFrac[1])) << std::endl;
	return 0;
}

int sumDigits(std::string str) {
	int result = 0;
	for (int i = 0; i < str.length(); i++) {
		result += atoi(str.substr(i, 1).c_str());
	}
	return result;
}

std::string * f(std::string a, std::string b_1, std::string b_2) {
	std::string *result = new std::string[2];
	result[1] = b_1; // denominator 
	int aAsInt = atoi(a.c_str());
	result[0] = add(mult(aAsInt, b_1), b_2); // numerator
	return result;
}

std::string mult(int a, std::string b) {
	if (a == 1) return b;
	return add(mult(a - 1, b), b);
}

std::string add(std::string a, std::string b) {
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