#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
/* Problem 36
 * reverse: takes a string and returns its reverse
 * isPalindrome: takes an integer and returns whether it is a palindrome
 * convertIntToArray: takes a positive integer and returns its value in the base given e.g. convertIntToArray(123, 10) = [1, 2, 3]
*/

std::string reverse(std::string str);

bool isPalindrome(int n);

std::vector<int> convertIntToArray(int n, int b);

int main() {
	int N = 1000000; int sum = 0;
	for (int i = 1; i < N; i++) {
		std::vector<int> T = convertIntToArray(i, 2);
		std::vector<int> U = T;
		std::reverse(U.begin(), U.end());
		if (isPalindrome(i) && T == U) sum += i;
	}
	std::cout << "The solution is " << sum << std::endl;
	return 0;
}

std::string reverse(std::string str) {
	std::string result = "";
	for (int i = 0; i < str.length(); i++) {
		result += str[str.length() - 1 - i];
	}
	return result;
}

bool isPalindrome(int n) {
	std::string nAsStr = std::to_string(n);
	return (nAsStr == reverse(nAsStr));
}

std::vector<int> convertIntToArray(int n, int b) {
	std::vector<int> result; int divisor = 1; int dividend = n;
	while (true) {
		if (divisor * b > n) break;
		divisor *= b;
	}
	while (divisor > 0) {
		result.push_back(dividend / divisor);
		dividend %= divisor;
		divisor = divisor / b;
	}
	return result;
}