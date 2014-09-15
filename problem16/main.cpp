#include <iostream>
#include <vector>
/* Problem 16
 * multiplication: takes a dynamic array of numbers, multiplies by an integer and returns the result in a new dynamic array e.g. multiplication([1, 2, 3], 10) returns [1, 2, 3, 0]
 * reverseInPlace: reverses a dynamic array
 * sumOfArray: returns the sum of the numbers in a dynamic array
*/

std::vector<int> multiplication(std::vector<int> dynArray, int n);

void reverseInPlace(std::vector<int>& dynArray); // without the &, we do a passage by copy meaning the function does not modify dynArray

int sumOfArray(std::vector<int> dynArray);

int main() {
	std::vector<int> V; V.push_back(1);
	for (int i = 0; i < 1000; i++) {
		V = multiplication(V, 2);
	}
	std::cout << "The solution is " << sumOfArray(V) << std::endl;
	return 0;
}

std::vector<int> multiplication(std::vector<int> dynArray, int n) {
	std::vector<int> result; int carryOver = 0; int p = 0;
	for (int i = 0; i < dynArray.size() - 1; i++) { // there is a -1 as the first element will be treated as a separate case
		p = dynArray[dynArray.size() - 1 - i] * n + carryOver;
		result.push_back(p % 10); carryOver = p / 10;
	}
	p = dynArray[0] * n + carryOver; // treat the case of the first element
	while (true) {
		result.push_back(p % 10);
		if (p / 10 == 0) break;
		else p /= 10;
	}	
	reverseInPlace(result); return result;
}

void reverseInPlace(std::vector<int>& dynArray){
	std::vector<int> copyOfDynArray;
	for (int i = 0; i < dynArray.size(); i++) {
		copyOfDynArray.push_back(dynArray[i]);
	}
	for (int i = 0; i < dynArray.size(); i++) {
		dynArray[i] = copyOfDynArray[copyOfDynArray.size() - 1 - i];
	}
}

int sumOfArray(std::vector<int> dynArray) {
	int sum = 0;
	for (int i = 0; i < dynArray.size(); i++) {
		sum += dynArray[i];
	}
	return sum;
}