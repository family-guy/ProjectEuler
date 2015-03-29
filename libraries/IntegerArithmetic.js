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

/**
* Takes two non-negative integers and returns whether the first is greater than the second
* @param string a, the first integer to be compared
* @param string b, the second integer to be compared
* @return boolean
*/
var greaterThan = function(a, b) {
	if (a.length < b.length) return false;
	if (a.length == b.length) {
		for (var i = 0; i < a.length; i++) {
			var aDigit = a.substring(i, i + 1);
			var bDigit = b.substring(i, i + 1);
			if (aDigit != bDigit) return parseInt(aDigit) > parseInt(bDigit);
		}
		return false;
	}
	return true;
}
exports.greaterThan = greaterThan;

/**
* Returns the sum of two arbitrarily large non-negative integers where the first is greater than or equal to the second
* @param string a, the first integer
* @param string b, the second integer
* @return string
*/
var sum = function(a, b) {
	var result = '';
	var difference = a.length - b.length;
	for (var i = 0; i < difference; i++) {
		b = '0' + b;
	}
	var carryOver = 0;
	for (var i = a.length - 1; i > -1; i--) {
		var x = parseInt(a.substring(i, i + 1));
		var y = parseInt(b.substring(i, i + 1));
		var value = (x + y + carryOver) % 10;
		result = value.toString() + result;
		carryOver = Math.floor((x + y + carryOver) / 10);
	}
	if (carryOver == 0) return result;
	return carryOver.toString() + result;
}
exports.sum = sum;

/**
* Returns the difference of two arbitrarily large non-negative integers where the first is greater than or equal to the second
* @param string a, the first integer
* @param string b, the second integer
* @return string
*/
var difference = function(a, b) {
	var result = '';
	var A = [];
	var B = [];
	for (var i = 0; i < a.length; i++) {
		A.push(parseInt(a.substring(i, i + 1)));
	}
	var diffLengths = a.length - b.length;
	for (var i = 0; i < diffLengths; i++) {
		B.push(0);
	}
	for (var i = diffLengths; i < a.length; i++) {
		B.push(parseInt(b.substring(i - diffLengths, i - diffLengths + 1)));
	}
	for (var i = a.length - 1; i > -1; i--) {
		if (A[i] < B[i]) {
			var k = 1;
			while (true) {
				if (A[i - k] != 0) {
					A[i - k]--;
					break;
				}
				A[i - k] = 9;
				k++
			}
			result = (A[i] + 10 - B[i]).toString() + result;
		}
		else result = (A[i] - B[i]).toString() + result;
	}
	var resultWithoutLeadingZeros = '';
	var j;
	for (j = 0; j < result.length - 1; j++) {
		if (result.substring(j, j + 1) != '0') break;
	}
	for (var l = j; l < result.length; l++) {
		resultWithoutLeadingZeros += result.substring(l, l + 1);
	}
	return resultWithoutLeadingZeros;
}
exports.difference = difference;

/**
* Takes two positive integers a and b and returns the quotient a / b and the remainder
* @param string a
* @param string b
* @param array of strings
*/
exports.quotientRemainder = function(a, b) {
	var result = [];
	var quotient = '0';
	while (true) {
		if (greaterThan(b, a)) break;
		a = difference(a, b);
		quotient = sum(quotient, '1');
	}
	result.push(quotient);
	result.push(a);
	return result;
}

/**
* Returns the product of an arbitrarily large non-negative integer and a digit
* @param integer digit
* @param string b
* @return string
*/
var digitProduct = function(digit, b) {
	if (digit == 0) return '0';
	if (digit == 1) return b;
	return sum(digitProduct(digit - 1, b), b);
}
exports.digitProduct = digitProduct;

/**
* Returns the product of two arbitrarily large non-negative integers where the first is greater than or equal to the second
* @param string a, the first integer
* @param string b, the second integer
* @return string
*/
exports.product = function(a, b) {
	var result = digitProduct(parseInt(b.substring(0, 1)), a);
	for (var i = 0; i < b.length - 1; i++) {
		result += '0';
	}
	for (var i = 1; i < b.length; i++) {
		var substr = b.substring(i, i + 1);
		var str = digitProduct(parseInt(substr), a);
		for (var j = i; j < b.length - 1; j++) {
			str += '0';
		}
		result = sum(result, str);
	}
	return result;
}