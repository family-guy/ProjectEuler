#include <iostream>
#include <cmath>
/**
 * Problem 64
 */

/*
* Returns whether a positive integer is a square
*/
bool isSquare(int n);

/*
* Takes a triple (a, b, c) representing a fraction of the form a / (sqrt(b) + c) and returns the next fraction (of the same form) in the sequence
*/
int * nextTerm(int T[]);

/*
* Takes a positive non-square integer and returns its period
*/
int period(int n); 

/*
* Returns if two arrays of integers are equal
*/
bool equals(int T[], int t, int U[], int u); 

int main() {
	int nbOddPeriods = 0;
	int N = 10000;
	for (int i = 0; i <= N; i++) {
		if (!isSquare(i)) {
			if (period(i) % 2 == 1) nbOddPeriods++;
		}
	}
	std::cout << "The solution is " << nbOddPeriods << std::endl; 
	return 0;
}

bool isSquare(int n) {
	int a = (int)sqrt(n);
	return a * a == n;
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

int period(int n) {
	int a_0 = (int)sqrt(n);
	int firstTriple[] = {1, n, (-1) * a_0};
	int currentTriple[] = {1, n, (-1) * a_0};
	int result = 1;
	while (true) {
		int *next = nextTerm(currentTriple);
		if (equals(next, 3, firstTriple, 3)) break;
		for (int i = 0; i < 3; i++) {
			currentTriple[i] = next[i];
		}
		result++;
	}
	return result;
}

bool equals(int T[], int t, int U[], int u) {
	if (t != u) return false;
	for (int i = 0; i < t; i++) {
		if (T[i] != U[i]) return false;
	}
	return true;
}