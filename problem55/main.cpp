#include <iostream>
#include <string>
/**
 * Problem 55
 */

// Returns the number of iterations required to produce a palindrome. If not less than 50, returns -1
int nbIterations(int n);

// Takes two integers that have the same number of digits, represented by strings, and returns a string representing their sum
std::string add(std::string a, std::string b); 

// Takes a string and returns its reverse
std::string reverse(std::string str);

int main() {
	int nbLychrels = 0;
	for (int i = 0; i < 10000; i++) {
		if (nbIterations(i) == -1) nbLychrels++;
	}
	std::cout << "The solution is " << nbLychrels << std::endl;
	return 0;
}

int nbIterations(int n) {
	std::string nAsStr = std::to_string(n);
	for (int i = 1; i < 50; i++) {
		std::string sum = add(nAsStr, reverse(nAsStr));
		if (reverse(sum) == sum) return i;
		nAsStr = sum;
	}
	return -1;
}

std::string add(std::string a, std::string b) {
	std::string result = "";
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

std::string reverse(std::string str) {
	std::string result = "";
	for (int i = str.length() - 1; i > -1; i--) {
		result += str.substr(i, 1);
	}
	return result;
}