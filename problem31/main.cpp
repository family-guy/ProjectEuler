#include <iostream>
/* Problem 31
 * nbOfWays: t is the target, c is the array of available coin values, n is the length of c
*/

int nbOfWays(int t, int c[], int n);

int main() {
	const int LENGTH_OF_COIN_VALUES = 8;
	int coinValues[] = {1, 2, 5, 10, 20, 50, 100, 200}; int target = 200;	
	std::cout << "The solution is " << nbOfWays(target, coinValues, LENGTH_OF_COIN_VALUES) << std::endl;
	return 0;
}

int nbOfWays(int t, int c[], int n) {
	if (n == 1) {
		if (t % c[0] == 0 && t / c[0] > 0) return 1;
		else return 0;
	}
	int sum = 0; int *new_c = new int[n - 1];
	for (int i = 0; i < n - 1; i++) {
		new_c[i] = c[i + 1];
	}
	if (t % c[0] == 0 && t / c[0] > 0) sum++;
	while (t > 0) {
		sum += nbOfWays(t, new_c, n - 1);
		t -= c[0];
	}
	return sum;
}