/* Problem 47
 * primeFacts: takes an integer >= 2 and returns its prime factors in a dynamic array e.g 90 returns [2, 3, 5]
 * getConsecNbs: takes two positive integers a and b and returns in an array b consecutive numbers starting from a e.g getConsecNbs(4, 3) returns {4, 5, 6}
 * getSmallestNb: given a natural number n, returns the smallest number y such that y, y + 1, ..., y + (n - 1) each have n distinct prime factors
*/

var IntegerArithmetic = require("../../Documents/projet_personnel/informatique/javaScript/IntegerArithmetic.js");
var n = 50000000;
var primes = IntegerArithmetic.sieveEratosthenes(n);
console.log("The solution is " + getSmallestNb(4, primes));

function primeFacts(n, P) { // this could be replaced with a function that returns the number of distinct prime factors. The array is not necessary but was used to ascertain that the correct prime factors were being generated
	var result = [];
	var i = 0;
	while (true) {
		while (!P[i]) i++;
		if (n % i === 0) {
			result.push(i);
			while (n % i === 0) n = Math.floor(n / i);
		}
		if (n === 1) break;
		i++;
	}
	return result;
}

function getConsecNbs(a, b) {
	result = [];
	for (var i = 0; i < b; i++) {
		result.push(a);
		a++;
	}
	return result;
}

function getSmallestNb(n, P) {
	var i = 2; var j = 0;
	while (true) {
		var consec = getConsecNbs(i, n);
		while (primeFacts(consec[j], P).length === n && j < consec.length - 1) j++;
		if (j == consec.length - 1 && primeFacts(consec[j], P).length === n) break;
		i++; j = 0;
	}
	return i;
}