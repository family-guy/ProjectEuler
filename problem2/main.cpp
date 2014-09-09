#include <iostream>

int fib(int n); // returns the n-th term of Fibonacci's suite

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