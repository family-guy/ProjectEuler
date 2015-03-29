#ifndef INTEGER_ARITHMETIC_H
#define INTEGER_ARITHMETIC_H
#include <vector>
#include <string>
/**
 * Functions that perform simple integer arithmetic
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

/**
* Takes two non-negative integers a and b represented as strings and returns whether a is greater than b
*/
bool greaterThan(std::string a, std::string b);

/**
* Returns the sum of two arbitrarily large non-negative integers represented as strings where the first argument is greater than or equal to the second
*/
std::string sum(std::string a, std::string b);

/**
* Returns the difference of two arbitrarily large non-negative integers represented as strings where the first argument is greater than or equal to the second
*/
std::string difference(std::string a, std::string b);

/**
* Takes two positive integers a and b represented as strings and returns the quotient a / b and the remainder
*/
std::string * quotientRemainder(std::string a, std::string b);

/**
* Returns the product of an arbitrarily large non-negative integer represented as a string and a digit
*/
std::string digitProduct(int digit, std::string b);

/**
* Returns the product of two arbitrarily large non-negative integers represented as strings where the first argument is greater than or equal to the second
*/
std::string product(std::string a, std::string b);

#endif