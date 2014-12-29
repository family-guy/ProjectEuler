#include <iostream>
#include <string>
#include <vector>
#include <cmath>
/*
 * Problem 66
 * See http://en.wikipedia.org/wiki/Pell%27s_equation#Concise_representation_and_faster_algorithms, section 3 'Example'
 */

/*
* Returns whether a positive integer is a square
*/
bool isSquare(int n);

/*
* Takes two non-negative integers a and b represented as strings and returns whether a is greater than b
*/
bool greaterThan(std::string a, std::string b);

/*
* Returns the minimal solution in x for a given D
*/
std::string minSolInX (int D);

/*
* Takes an integer and returns the k-th term in the sequence of partial values of its infinite continued fraction
*/
std::string * kthTermInfContFrac(int k, int n);

/*
* Takes an array of integers and returns an array of length k by repeating the values in the array given e.g. repeatBlock([1, 1, 5], 7) returns [1, 1, 5, 1, 1, 5, 1]
*/
std::string * repeatBlock(std::vector<int> block, int k);

/**
* Takes a triple (a, b_1, b_2) and returns a + 1 / b where b = b_1 / b_2
*/
std::string * f(std::string a, std::string b_1, std::string b_2);

/**
* Takes two integers a and b, where a >= b, represented by strings, and returns a string representing their sum.
*/
std::string add(std::string a, std::string b);

/**
* Returns the product a * b of two non-negative integers where b is a digit and a is represented as a string
*/
std::string multAux(std::string a, int b);

/**
* Returns the product of two non-negative integers represented as strings
*/
std::string mult(std::string a, std::string b);

/*
* Takes a non-square positive integer and returns its infinite contiuned fraction e.g. infContFrac(23) returns [[4], [1, 3, 1, 8]]
*/
std::vector<std::vector<int> > infContFrac(int n);

/*
* Takes a triple (a, b, c) representing a fraction of the form a / (sqrt(b) + c) and returns the next fraction (of the same form) in the sequence
*/
int * nextTerm(int T[]);

/*
* Returns if two arrays of integers are equal
*/
bool equals(int T[], int t, int U[], int u);

int main() {
	std::string currentMax = "0";
	int currentMaxIndex = 0;
	for (int i = 0; i <= 1000; i++) {
		if (!isSquare(i)) {
			std::string str = minSolInX(i);
			if (greaterThan(str, currentMax)) {
				currentMax = str;
				currentMaxIndex = i;
			}
		} 		
	}
	std::cout << "The solution is: D equals " << currentMaxIndex << " for which the value of x is " << currentMax << std::endl;
	return 0;
}

bool isSquare(int n) {
	int a = (int)sqrt(n);
	return a * a == n;
}

bool greaterThan(std::string a, std::string b) {
	if (a.length() < b.length()) return false;
	if (a.length() == b.length()) {
		for (int i = 0; i < a.length(); i++) {
			std::string aDigit = a.substr(i, 1);
			std::string bDigit = b.substr(i, 1);
			if (aDigit != bDigit) return atoi(aDigit.c_str()) > atoi(bDigit.c_str()); 
		}
		return false;
	}
	return true;
}

std::string minSolInX (int D) {
	int i = 0;
	while (true) {
		std::string x = kthTermInfContFrac(i, D)[0];
		std::string y = kthTermInfContFrac(i, D)[1];
		if (mult(x, x) == add(mult(std::to_string(D), mult(y, y)), "1")) return x;
		i++;
	}
}

std::string * kthTermInfContFrac(int k, int n) {
	std::string *result = new std::string[2];
	std::vector<std::vector<int> > infContFracNotation = infContFrac(n);
	if (k == 0) {
		result[0] = std::to_string(infContFracNotation[0][0]);
		result[1] = "1";
		return result;
	}
	std::string *values = repeatBlock(infContFracNotation[1], k);
	std::string *currentFrac = new std::string[2];
	currentFrac[0] = values[k - 1];
	currentFrac[1] = "1";
	for (int i = k - 2; i > -1; i--) {
		std::string currentInt = values[i];
		std::string *next = f(currentInt, currentFrac[0], currentFrac[1]);
		currentFrac[0] = next[0];
		currentFrac[1] = next[1];
	}
	result[0] = add(mult(std::to_string(infContFracNotation[0][0]), currentFrac[0]), currentFrac[1]);
	result[1] = currentFrac[0];
	return result;
}

std::string * repeatBlock(std::vector<int> block, int k) {
	std::string *values = new std::string[k];
	for (int i = 0; i < k; i++) {
		values[i] = std::to_string(block[i % block.size()]);
	}
	return values;
}

std::string * f(std::string a, std::string b_1, std::string b_2) {
	std::string *result = new std::string[2];
	result[1] = b_1; // denominator
	result[0] = add(mult(a, b_1), b_2); // numerator
	return result;
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

std::string multAux(std::string a, int b) {
	if (b == 0) return "0";
	std::string result = "";
	int carryOver = 0; int p = 0;
	for (int i = 0; i < a.length() - 1; i++) {
		p = atoi(a.substr(a.length() - 1 - i, 1).c_str()) * b + carryOver;
		result = std::to_string(p % 10) + result;
		carryOver = p / 10;
	}
	p = atoi(a.substr(0, 1).c_str()) * b + carryOver;
	while (true) {
		result = std::to_string(p % 10) + result;
		if (p / 10 == 0) break;
		p /= 10;
	}
	return result;
}

std::string mult(std::string a, std::string b) {
	if (a == "0" || b == "0") return "0";
	std::string sum = "0";
	for (int i = 0; i < a.length(); i++) {
		std::string zeros = "";
		for (int j = 0; j < a.length() - 1 - i; j++) {
			zeros += "0";
		}
		// if/else because in sum(a, b), a >= b
		if (i == 0) {
			sum = add(multAux(b, atoi(a.substr(i, 1).c_str())) + zeros, sum); 
		}
		else {
			sum = add(sum, multAux(b, atoi(a.substr(i, 1).c_str())) + zeros); 
		}
	}
	return sum;
}

std::vector<std::vector<int> > infContFrac(int n) {
	std::vector<std::vector<int> > result;
	std::vector<int> firstElt;
	firstElt.push_back((int)sqrt(n));
	result.push_back(firstElt);
	std::vector<int> secondElt;
	int firstTriple[] = {1, n, (-1) * firstElt[0]};
	int currentTriple[] = {1, n, (-1) * firstElt[0]};
	int currentTripleLength = 3;
	while (true) {
		int intermedFracDen = currentTriple[1] - currentTriple[2] * currentTriple[2];
		secondElt.push_back((int)(currentTriple[0] * (sqrt(currentTriple[1]) - currentTriple[2]) / intermedFracDen));
		int *next = nextTerm(currentTriple);
		if (equals(next, 3, firstTriple, 3)) break;
		for (int i = 0; i < currentTripleLength; i++) {
			currentTriple[i] = next[i];
		}
	}
	result.push_back(secondElt);
	return result;
}

int * nextTerm(int T[]) {
	int *result = new int[3];
	result[1] = T[1];
	int intermedFracDen = T[1] - T[2] * T[2];
	int intermedFracWholePart = (int)(T[0] * (sqrt(T[1]) - T[2]) / intermedFracDen);
	result[0] = intermedFracDen / T[0];
	result[2] = (-1) * T[2] - intermedFracWholePart * result[0];
	return result;
}

bool equals(int T[], int t, int U[], int u) {
	if (t != u) return false;
	for (int i = 0; i < t; i++) {
		if (T[i] != U[i]) return false;
	}
	return true;
}