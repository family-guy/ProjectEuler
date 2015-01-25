/*
 * This module contains functions that perform simple integer arithmetic
 */

/**
* Auxiliary function
* @param integer n
* @param primes array of integers
* @param two dimensional array of integers solution
* @param integer lastIndex
* @return two dimensional array of integers
*/
var primeDecompAux = function(n, primes, solution, lastIndex) {
	var primeFact = [];
	for (var i = lastIndex; i < primes.length; i++) {
		var p = primes[i];
		if (n % p == 0) {
			primeFact.push(p);
			var expPrimeFact = 1;
			n = Math.floor(n / p);
			while (true) {
				if (n % p == 0) {
					expPrimeFact++;
					n = Math.floor(n / p);
				}
				else break;
			}
			primeFact.push(expPrimeFact);
			solution.push(primeFact);
			if (n == 1) return solution;
			return primeDecompAux(n, primes, solution, i + 1);
		}
	}
	primeFact.push(n);
	primeFact.push(1);
	solution.push(primeFact);
	return solution;
};

exports.primeDecompAux = primeDecompAux;

/**
* Returns the greatest common divisor of two integers a and b where a is less than or equal to b
* @param integer a
* @param integer b
* @return integer
*/	
var gcd = function(a, b) {
	if (b % a == 0) return a;
	return gcd(b % a, a);
};	

exports.gcd = gcd;

/**
* Returns the prime decomposition of an integer
* @param integer n
* @param primes array of integers
* @return two dimensional array of integers
*/
exports.primeDecomp = function(n, primes) {
	var solution = [];
	return primeDecompAux(n, primes, solution, 0);
};

/**
* Can be used to determine whether a number is prime
* @param integer n
* @return array of booleans
*/
exports.sieveEratosthenes = function(n) {
	result = [];
	for (var i = 0; i < n + 1; i++) { // starting case is that all values are true
		result.push(true);
	}
	result[0] = false; result[1] = false;
	for (var i = 0; i * i < n; i++) { // the exit condition comes from "(for all i in {1, ..., [sqrt n]} s.t i is prime, n is not divisible by i) => n is prime"
		if (result[i] == true) {
			for (var j = 2; i * j <= n; j++) {
				result[i * j] = false;
			}
		} 
	}
	return result;
};

/**
* Can be used to determine whether a number is prime
* @param integer n
* @return boolean
*/
exports.isPrime = function(n) {
	if (n <= 1) return false;
	else {
		for (var i = 2; i * i <= n; i++) {
			if (n % i == 0) return false;
		}
	}
	return true;
};

/**
* Returns the sum of a number's proper divisors
* @param integer n
* @return integer
*/
exports.sumOfPropDiv = function(n) {
	var sum = 0;
	for (var i = 2; i * i <= n; i++) {
		if (n % i == 0) {
			if (i != n / i) sum += i + n / i;
			else sum += i;
		}
	}
	return sum + 1;
};



