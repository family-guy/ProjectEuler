// Problem 3

#include <iostream>
#include "integer_arithmetic.h"

long largestPrimeFactor(long n, int i = 2);

int main() {
	std::cout << "The solution is " << largestPrimeFactor(600851475143) << std::endl;
	return 0;
}
		
long largestPrimeFactor(long n, int i) {
	if (isPrime(n)) return n;
	else {
		while (isPrime(i) == false || n % i != 0) i++;
		n = n / i;
		return largestPrimeFactor(n, i);
	}
}		
	
	
	