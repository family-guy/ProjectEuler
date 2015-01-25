#include <iostream>
#include <vector>
#include <cmath>
/**
 * Problem 76
 * http://mathworld.wolfram.com/PartitionFunctionP.html
 */

/* 
* Takes an array of previous values P(n - 1), ..., P(0) and returns P(n), n >= 2
*/
int nextValOfPn(std::vector<int> prevValsOfPn);

int functA(int k);

int functB(int k);

int main() {
	std::vector<int> prevVals;
	prevVals.push_back(1);
	prevVals.push_back(1);
	int n = 100;
	while (prevVals.size() - 1 < n) {
		prevVals.push_back(nextValOfPn(prevVals));
	}
	std::cout << "The solution is " << prevVals[prevVals.size() - 1] - 1 << std::endl;
	return 0;
}

int nextValOfPn(std::vector<int> prevValsOfPn) {
	int sumA = 0; int sumB = 0;
	int n = prevValsOfPn.size();
	for (int k = 1; k <= n; k++) {
		int termA = n - functA(k);
		if (termA < 0) break;
		sumA += pow(-1, k + 1) * prevValsOfPn[termA];
	}
	for (int k = 1; k <= n; k++) {
		int termB = n - functB(k);
		if (termB < 0) break;
		sumB += pow(-1, k + 1) * prevValsOfPn[termB];
	}
	return sumA + sumB;
}

int functA(int k) {
	return k * (3 * k - 1) / 2;
}

int functB(int k) {
	return k * (3 * k + 1) / 2;
}