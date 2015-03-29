/**
 * Problem 74
 */

var Combinatorics = require('../libraries/Combinatorics.js');
var loops = {1: 0,
			 2: 0,
			 145: 0,
			 40585: 0,
			 169: 2, 363601: 2, 1454: 2,
			 871: 1, 45361: 1,
			 872: 1, 45362: 1};
var sum = 0;
for (var i = 1; i < 1000000; i++) {
	if (nbNonRepeatTerms(i) == 60) sum++;
}
console.log('The solution is ' + sum);

/**
* Returns a given integer as an array e.g. convertIntToArray(123) = [1, 2, 3]
* @param integer n
* @result array of integers
*/
function convertIntToArray(n) {
	var result = [];
	var divisor = 1; var dividend = n;
	while (true) {
		if (divisor * 10 > n) break;
		divisor *= 10;
	}
	while (divisor > 0) {
		result.push(Math.floor(dividend / divisor));
		dividend %= divisor;
		divisor = Math.floor(divisor / 10);
	}
	return result;
}

/**
* Takes an array of positive integers and returns the sum of their factorials
* @param array of integers T
* @return integer
*/
function sumFact(T) {
	var sum = 0;
	for (var i = 0; i < T.length; i++) {
		sum += Combinatorics.fact(T[i]);
	}
	return sum;
}

/**
* Returns the number of non-repeating terms for a given integer
*/
function nbNonRepeatTerms(n) {
	var result = 1;
	var currentVal = n;
	while (true) {
		if (currentVal in loops) return result + loops[currentVal];
		result++;
		currentVal = sumFact(convertIntToArray(currentVal));
	}
}