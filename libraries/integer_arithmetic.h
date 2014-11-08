#ifndef INTEGER_ARITHMETIC_H
#define INTEGER_ARITHMETIC_H
/*
 * This header file contains functions that perform simple integer arithmetic
 * sieveEratosthenes: can be used to determine whether a number is prime
 * isPrime: same as above
 * sumOfPropDiv: returns the sum of a number's proper divisors
 * gcd: returns the greatest common divisor of a number
*/

bool * sieveEratosthenes(int n);

bool isPrime(long n);

int sumOfPropDiv(int n);

int gcd(int a, int b); // a <= b

#endif