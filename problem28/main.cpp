// Problem 28

#include <iostream>

int main() {
	int lengthOfSpiral = 1001; int sum = 1;
	int currentTerm = 1; // current term of the sequence 1, 3, 5, 7, 9, 13, 17, 21, 25, ...
	for (int currentLength = 3; currentLength <= lengthOfSpiral; currentLength += 2) {
		for (int i = 0; i < 4; i++) {
			currentTerm += currentLength - 1;
			sum += currentTerm;
		}
	}
	std::cout << "The solution is " << sum << std::endl;
}