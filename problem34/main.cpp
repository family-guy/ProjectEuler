#include <iostream>
#include <vector>
/* Problem 34
 * convertIntToArray: returns a given integer as a dynamic array e.g. convertIntToArray(123) = [1, 2, 3]
 * fact: returns the factorial of a number
 * sumFact: takes a dynamic array of positive integers and returns the sum of their factorials
*/

std::vector<int> convertIntToArray(int n);

int fact(int n);

int sumFact(std::vector<int> T);

int main() {
	int maxIndex = 10000000; // numbers with 8 or more digits cannot be equal to the sum of the factorial of their digits
	int sum = 0;
	for (int i = 10; i < maxIndex; i++) {
		if (i == sumFact(convertIntToArray(i))) sum += i;
	}
	std::cout << "The solution is " << sum << std::endl;
	return 0;
}

std::vector<int> convertIntToArray(int n) {
	std::vector<int> result;	
	int divisor = 1; int dividend = n;
	while (true) {
		if (divisor * 10 > n) break;
		divisor *= 10;
	}
	while (divisor > 0) {
		result.push_back(dividend / divisor);
		dividend %= divisor;
		divisor /= 10;
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

int sumFact(std::vector<int> T) {
	int sum = 0;
	for (int i = 0; i < T.size(); i++) {
		sum += fact(T[i]);
	}
	return sum;
}