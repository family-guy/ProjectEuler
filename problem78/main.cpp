#include <iostream>
#include <vector>
#include <cmath>
/**
 * Problem 78
 * http://mathworld.wolfram.com/PartitionFunctionP.html
 */

/* 
* Takes an array of previous values P(n - 1), ..., P(0) and returns P(n), n >= 2. All values modulo one million
*/
int nextValOfPn(std::vector<int> prevValsOfPn);

int functA(int k);

int functB(int k);

/* 
* Returns a bar in Z / bZ
*/
int mod(int a, int b);

int main() {
	std::vector<int> prevVals;
	prevVals.push_back(1);
	prevVals.push_back(1);
	while (true) {
		int nextVal = nextValOfPn(prevVals);
		if (nextVal == 0) {
			std::cout << "The solution is " << prevVals.size() << std::endl;
			break;
		}
		prevVals.push_back(nextVal);
	}		
	return 0;
}

int nextValOfPn(std::vector<int> prevValsOfPn) {
	int sumA = 0; int sumB = 0;
	int m = 1000000;
	int n = prevValsOfPn.size();
	for (int k = 1; k <= n; k++) {
		int termA = n - functA(k);
		if (termA < 0) break;
		sumA += pow(-1, k + 1) * (prevValsOfPn[termA] % m);
	}
	for (int k = 1; k <= n; k++) {
		int termB = n - functB(k);
		if (termB < 0) break;
		sumB += pow(-1, k + 1) * (prevValsOfPn[termB] % m);
	}
	sumA = mod(sumA, m); sumB = mod(sumB, m);
	return mod(sumA + sumB, m);
}

int functA(int k) {
	return k * (3 * k - 1) / 2;
}

int functB(int k) {
	return k * (3 * k + 1) / 2;
}
	
int mod(int a, int b) { 
	if (a >= 0 || a % b == 0) return a % b;
	int x = a % b;
	while (true) {
		x += b;
		if (x >= 0) return x;
	}
}