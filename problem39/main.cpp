#include <iostream>
/* Problem 39
 * isPythag: returns whether three positive integers make up a Pythagorean triple
*/

bool isPythag(int a, int b, int c);

int main() {
	int n = 1001;
	int *P = new int[n];
	for (int i = 1; i < 998; i++) {
		for (int j = i + 1; j < 999; j++) {
			for (int k = j + 1; k < 1000; k++) {
				int p = i + j + k;
				if (p <= 1000 && isPythag(i, j, k)) {
					P[p]++;
				}
			}
		}
	}
	int maxIndex = 0;
	for (int i = 0; i < n; i++) {
		if (P[maxIndex] < P[i]) maxIndex = i;
	}
	std::cout << "The value of p for which the number of solutions is maximised is " << maxIndex << " which has " << P[maxIndex] << " solutions" << std::endl;
	return 0;
}

bool isPythag(int a, int b, int c) {
	return (a * a + b * b == c * c);
}