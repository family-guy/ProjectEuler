#include <iostream>
#include <string>
#include <algorithm>
/* Problem 38
 * concatenatedProd: takes a positive integer and returns its concatenated product as defined in the question
 * isOneToNinePand: takes a number represented as a string and returns whether that number is a permutation of {1, ..., 9}
 * maxPand: given two positive integers a and b, maxPand returns the largest 1 to 9 pandigital 9-digit number that can be formed as the concatenated product of an integer p with (1,2, ... , n) where n > 1 and a <= p <= b. Returns zero if no pandigital exists
*/

std::string concatenatedProd(int p);

bool isOneToNinePand(std::string n);

int maxPand(int a, int b);

int main() {
	// the largest 1 to 9 pandigital 9-digit number must begin with 9. Thus the integer we are looking for begins with 9. Further, as n > 1, it cannot be more than 4 digits long
	int startingMax = 918273645; // we know this is the result of concatenatedProd(9)
	// case of 2 digits. We go through 91 to 98
	int x = maxPand(91, 98); 
	// case of 3 digits. We go through 912 to 987
	int y = maxPand(912, 987);
	// case of 4 digits. We go through 9123 to 9876
	int z = maxPand(9123, 9876);
	std::cout << "The solution is " << std::max(std::max(std::max(startingMax, x), y), z) << std::endl;
	return 0;
}

std::string concatenatedProd(int p) {
	int i = 1; std::string pAsStr = std::to_string(p);
	while (true) {
		if (pAsStr.length() >= 9) break;
		i++;
		std::string product = std::to_string(p * i);
		pAsStr += product;
	}
	return pAsStr;
}

bool isOneToNinePand(std::string n) {
	if (n.length() != 9 || n.find("0") != std::string::npos) return false;
	for (int i = 1; i < 10; i++) {
		std::string iAsStr = std::to_string(i);
		if (n.find(iAsStr) != n.find_last_of(iAsStr)) return false;
	}
	return true;
}

int maxPand(int a, int b) {
	int max = 0;
	for (int i = a; i < b + 1; i++) {
		std::string cp = concatenatedProd(i);
		if (cp.length() == 9) {
			int cpAsInt = atoi(cp.c_str());
			if (isOneToNinePand(cp) && cpAsInt > max) max = cpAsInt;
		}
	}
	return max;
}