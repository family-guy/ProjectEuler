#ifndef INTEGER_ARITHMETIC_H
#define INTEGER_ARITHMETIC_H
#include <vector>
/*
 * This class contains functions that perform simple integer arithmetic
 */

/**
* Returns the prime decomposition of an integer
*/
std::vector<std::vector<int> > primeDecomp(int n, int primes[], int lengthOfPrimes);

std::vector<std::vector<int> > primeDecomp(int n, int primes[], int lengthOfPrimes, std::vector<std::vector<int> > &solution, int lastIndex);

/**
* Can be used to determine whether a number is prime
*/
bool * sieveEratosthenes(int n);

/**
* Can be used to determine whether a number is prime
*/
bool isPrime(long n);

/**
* Returns the sum of a number's proper divisors
*/
int sumOfPropDiv(int n);

/**
* Returns the greatest common divisor of a number
*/
int gcd(int a, int b); // a <= b

#endif