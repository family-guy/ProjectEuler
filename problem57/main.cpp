#include <iostream>
#include <string>
/**
 * Problem 57
 */

/**
* Takes two integers a and b, where a >= b, represented by strings, and returns a string representing their sum
*/
std::string add(std::string a, std::string b);

int main() {
	std::string a = "1"; std::string b = "1";
	int count = 0;
	for (int i = 0; i < 1000; i++) {
		std::string temp = a;
		a = add(add(a, b), b);
		b = add(temp, b);
		if (a.length() > b.length()) count++;
	}
	std::cout << "The solution is " << count << std::endl;
	return 0;
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
