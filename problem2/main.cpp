#include <iostream>
/* Problem 2
 * fib: returns the n-th term of Fibonacci's sequence
*/

int fib(int n);

int main() {
	int sum = 0;
	for (int i = 1; fib(i) < 4000001; i++) {
		if (fib(i) % 2 == 0)
		sum += fib(i);
	}
	std::cout << "The solution is " << sum << std::endl;
}

int fib(int n) { 
	if (n == 1) return 1;
	if (n == 2) return 2;
	else return fib(n - 1) + fib(n - 2);
}