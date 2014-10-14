#include <iostream>
#include <cmath>
#include <string>
/* Problem 40
 * digit: takes a positive integer n and returns the n-th digit of the fractional part of the irrational decimal fraction
*/

int digit(int n);

int main() {
	int product = 1;
	for (int i = 0; i < 7; i++) {
		product *= digit((int)pow(10, i));
	}
	std::cout << "The solution is " << product << std::endl;
	return 0;
}

int digit(int n) {
	int i = 1; int count = 0;
	std::string iAsStr = ""; int j = 0;
	while (true) {
		iAsStr = std::to_string(i);
		for (j = 0; j < iAsStr.length(); j++) {
			count++;
			if (count == n) break;
		}
		if (count == n) break;
		i++;
	}
	return iAsStr[j] - 48;
}