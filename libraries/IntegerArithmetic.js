/*
 * This module contains functions that perform simple integer arithmetic
 * sieveEratosthenes: can be used to determine whether a number is prime
 * isPrime: same as above
 * sumOfPropDiv: returns the sum of a number's proper divisors
 * gcd: returns the greatest common divisor of a number
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
}

exports.isPrime = function(n) {
	/*if (n <= 1) return false;
	else if (n == 2 || n == 3 || n == 5 || n == 7 || n == 11 || n == 13 || n == 17 || n == 19) return true;
	else if (n % 2 == 0 || n % 3 == 0 || n % 5 == 0 || n % 7 == 0 || n % 11 == 0 || n % 13 == 0 || n % 17 == 0 || n % 19 == 0) return false;
	else {
		for (var i = 2; i * i <= n; i++) {
			if (n % i == 0) return false;
		}
	}
	return true;*/
	
	if (n < 2) return false;
	if (n == 2 || n == 3) return true;
	if ((n - 1) % 6 != 0 && (n + 1) % 6 != 0) return false;
	for (var i = 2; i * i <= n; i++) {
		if (n % i == 0) return false;
	}
	return true;
}

exports.sumOfPropDiv = function(n) {
	var sum = 0;
	for (var i = 2; i * i <= n; i++) {
		if (n % i == 0) {
			if (i != n / i) sum += i + n / i;
			else sum += i;
		}
	}
	return sum + 1;
}
	
exports.gcd = function gcd(a, b) {
	if (b % a == 0) return a;
	return gcd(b % a, a);
}



