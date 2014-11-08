#include <iostream>
#include <vector>
/* Problem 44
 * areSumAndDiffPen: returns whether a pair of integers has the property in the question
*/

bool areSumAndDiffPen(int p, int q, bool T[]);

int main() {
	int n = 500000000;
	bool *isPent = new bool[n]; // default values are false
	for (int i = 1; i * (3 * i - 1) / 2 < n; i++) {
		isPent[i * (3 * i - 1) / 2] = true;
	}
	int upperLimit = 10000; // upper limit is arbitrary and we assume that the minimum found with this upper limit is the minimum that we are looking for
	std::vector<int> diffsOfPairs;
	for (int i = 1; i < upperLimit; i++) {
		for (int j = i + 1; j < upperLimit + 1; j++) {
			if(areSumAndDiffPen(i, j, isPent)) {
				diffsOfPairs.push_back(j * (3 * j - 1) / 2 - i * (3 * i - 1) / 2);
			}
		}
	}
	int minDiff = diffsOfPairs[0];
	for (int i = 0; i < diffsOfPairs.size(); i++) {
		if (minDiff > diffsOfPairs[i]) minDiff = diffsOfPairs[i];
	}
	std::cout << "The solution is " << minDiff << std::endl;
	return 0;
}

bool areSumAndDiffPen(int p, int q, bool T[]) {
	int pthPentNb = p * (3 * p - 1) / 2;
	int qthPentNb = q * (3 * q - 1) / 2;
	return T[pthPentNb + qthPentNb] && T[qthPentNb - pthPentNb];
}