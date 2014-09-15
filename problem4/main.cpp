#include <iostream>
#include <vector>
/* Problem 4
 * nbDigits: returns the number of digits of a non-negative integer
 * arrayDigits: returns a number's digits as an array e.g. 123 returns [1, 2, 3]
 * deepEqual: returns whether two arrays have the same values
 * reverseArray: reverses an array of integers
 * isPalindrome: returns whether an integer is a palindrome
*/

int nbDigits(int n, int a = 10, int b = 1);

std::vector<int> arrayDigits(int n);

bool deepEqual(std::vector<int> T, std::vector<int> U);

std::vector<int> reverseArray(std::vector<int> T);

bool isPalindrome(int n);

int main() {
	int maxValue, maxIndex1, maxIndex2; maxValue = 0; maxIndex1 = 0; maxIndex2 = 0;
	for (int i = 100; i < 1000; i++) {
		for (int j = 100; j < 1000; j++) {
			if (isPalindrome(i * j) && i * j > maxValue) {
				maxValue = i * j; maxIndex1 = i; maxIndex2 = j;
			}
		}
	}
	std::cout << "The solution is " << maxValue << " = " << maxIndex1 << " x " << maxIndex2 << std::endl;
	return 0;
}

int nbDigits(int n, int a, int b) {
	if (n / a == 0) return b;
	else return nbDigits(n, a * 10, b + 1);
}

std::vector<int> arrayDigits(int n) {
	int p = nbDigits(n); std::vector<int> result(p); int product = 1; 
	for (int i = 0; i < p; i++) {
		product *= 10;
		result[p - 1 - i] = (n % product) / (product / 10);
	}
	return result;
}

bool deepEqual(std::vector<int> T, std::vector<int> U) {
	if (T.size() != U.size()) return false;
	else {
		for (int i = 0; i < T.size(); i++) {
			if (T[i] != U[i]) return false;
		}
		return true;
	}
}

std::vector<int> reverseArray(std::vector<int> T) {
	std::vector<int> result(T.size());
	for (int i = 0; i < T.size(); i++) {
		result[i] = T[T.size() - 1 - i];
	}
	return result;
}

bool isPalindrome(int n) {
	return deepEqual(arrayDigits(n), reverseArray(arrayDigits(n)));
}