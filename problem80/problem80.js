/**
 * Problem 80. Runtime: ~75s on a 1.4 GHz Intel Core i5
 */

var IntegerArithmetic = require('../libraries/IntegerArithmetic.js');
var contFracReps = [];
for (var i = 1; i <= 100; i++) {
	if (!isSquare(i)) {
		var row = [];
		getContFracExpInPlace(i, row);
		contFracReps.push(row);
	} 
}
var sum = 0;
for (var i = 1; i <= 100; i++) {
	if (!isSquare(i)) sum += Math.floor(Math.sqrt(i));
}
for (var i = 0; i < contFracReps.length; i++) {
	var currentVal = '';
	var k = 2;
	while (true) {
		var nextVal = divide(contFrac(contFracReps[i], k)[1], contFrac(contFracReps[i], k)[0]);
		if (currentVal == nextVal) break;
		currentVal = nextVal;
		k++;
	}
	sum += sumDigits(currentVal);
}
console.log('The solution is: ' + sum);

/**
* Returns the sum of digits of an integer represented as a string
* @param string str
* @return integer
*/
function sumDigits(str) {
	var result = 0;
	for (var i = 0; i < str.length; i++) {
		result += parseInt(str.substring(i, i + 1));
	}
	return result;
}

/**
* Performs division of two integers represented as strings to 99 decimal places
* @param string a
* @param string b
* @return string
*/
function divide(a, b) {
	var result = '';
	var remainder = '0';
	var quotient = '0';
	var dividend = a;
	for (var i = 0; i < 99; i++) {
		dividend += '0';
		var quotRem = IntegerArithmetic.quotientRemainder(dividend, b);
		quotient = quotRem[0];
		result += quotient;
		remainder = quotRem[1];
		if (remainder == '0') {
			while (result.length < 100) {
				result += '0';
			}
			break;
		}
		dividend = remainder;
	}
	return result;
}

/**
* Continued fraction expansion to n iterations, n greater than or equal to 2
* @param array of integers contFracRep
* @param integer n
* @return array of strings
*/
function contFrac(contFracRep, n) {
	var currentFrac = [];
	currentFrac[0] = (contFracRep[(n - 1) % contFracRep.length]).toString();
	currentFrac[1] = '1';
	for (var i = n - 2; i > -1; i--) {
		var next = f((contFracRep[i % contFracRep.length]).toString(), currentFrac[0], currentFrac[1]);
		currentFrac[0] = next[0];
		currentFrac[1] = next[1];
	}
	return currentFrac;	
}

function f(a, b_1, b_2) {
	var result = [];
	result[1] = b_1;
	result[0] = IntegerArithmetic.sum(IntegerArithmetic.product(a, b_1), b_2);
	return result;
}

/*
* Returns whether a positive integer is a square
* @param integer n
* @return boolean
*/
function isSquare(n) {
	var a = Math.floor(Math.sqrt(n));
	return a * a == n;
}

/*
* Takes a triple (a, b, c) representing a fraction of the form a / (sqrt(b) + c) and returns the next fraction (of the same form) in the sequence
* @param array of integers T
* @param array of integers values
* @return array of integers
*/
function nextTerm(T, values) {
	var result = [];
	result[1] = T[1];
	var intermedFracDen = T[1] - T[2] * T[2];
	var intermedFracWholePart = Math.floor(T[0] * (Math.sqrt(T[1]) - T[2]) / intermedFracDen);
	values.push(intermedFracWholePart);
	result[0] = Math.floor(intermedFracDen / T[0]); 
	result[2] = (-1) * T[2] - intermedFracWholePart * result[0];
	return result;
}

/*
* Takes a positive non-square integer and returns its continued fraction expansion in array form
* @param integer n
* @param array of integers values
* @return void
*/
function getContFracExpInPlace(n, values) {
	var a_0 = Math.floor(Math.sqrt(n));
	var firstTriple = [1, n, (-1) * a_0];
	var currentTriple = [1, n, (-1) * a_0];
	while (true) {
		var next = nextTerm(currentTriple, values);
		if (equals(next, firstTriple)) break;
		for (var i = 0; i < currentTriple.length; i++) {
			currentTriple[i] = next[i];
		}
	}
}

/*
* Returns if two arrays of integers are equal
* @param array of integers T
* @param array of integers U
* @return boolean
*/
function equals(T, U) {
	if (T.length != U.length) return false;
	for (var i = 0; i < T.length; i++) {
		if (T[i] != U[i]) return false;
	}
	return true;
}
