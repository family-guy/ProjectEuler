/* Problem 51
 * next: Suppose that we enumerate all k-combinations of a set S of n elements in lexicographic order e.g. if S = {1, 2, 3, 4, 5}, k = 3, then we have {1, 2, 3}, {1, 2, 4}, {1, 2, 5}, {1, 3, 4}, {1, 3, 5}, {1, 4, 5}, {2, 3, 4}, {2, 3, 5}, {2, 4, 5}, {3, 4, 5}. Given one of these k-combinations in the form of an array and an integer representing n, this function returns the next k-combination e.g. next([1, 4, 5], 5) returns [2, 3, 4]
* isPrimeFamAux: takes a prime, a positive integer m and a k-combination and returns if that k-combination yields a m prime value family 
* isPrimeFam: takes a prime and an integer m and returns whether that prime is part of a m prime value family
*/

var IntegerArithmetic = require("../../Documents/projet_personnel/informatique/javaScript/IntegerArithmetic.js");
var Combinatorics = require("../../Documents/projet_personnel/informatique/javaScript/Combinatorics.js");
var n = 5000000; // value of n is set arbitrarily
var isPrime = IntegerArithmetic.sieveEratosthenes(n);
// array of all primes between 10 and n
var primes = [];
for (var i = 10; i < isPrime.length; i++) {
	if (isPrime[i]) primes.push(i);
}
for (var i = 0; i < primes.length; i++) {
	if (isPrimeFam(primes[i], 8, isPrime)) {
		console.log("The solution is " + primes[i]);
		break;
	}
}

function next(T, n) {
	var result = [];
	for (var i = 0; i < T.length; i++) {
		result.push(T[i]);
	}
	var i = 0;
	while (T.length - 1 - i > -1) {
		if (T[T.length - 1 - i] < n - i) {
			result[result.length - 1 - i]++;
			for (var j = result.length - i; j < T.length; j++) {
				result[j] = result[result.length - 1 - i] + 1 + (j - (result.length - i));
			}
			break;
		}
		i++;
	}
	return result;
}

function isPrimeFamAux(n, m, T, P) {
	var nAsStr = n.toString();
	var count = 0; // counts the number of primes in the family generated
	if (T.length > 1) { // if the length of the array T is one, no need to bother with the check below
		for (var i = 1; i < T.length; i++) { // checks to see if n has the same digits in the positions proposed by the combination i.e. the array T
			if (nAsStr.substring(T[i], T[i] + 1) !== nAsStr.substring(T[0], T[0] + 1)) return false;
		}
	}
	if (T[0] === 0) {
		for (var i = 1; i <= 9; i++) {
			var generatedNb = "";
			var iAsStr = i.toString();
			for (var j = 0; j < nAsStr.length; j++) {
				if (T.indexOf(j) > -1) generatedNb += iAsStr;
				else generatedNb += nAsStr.substring(j, j + 1);
			}
			var generatedNbAsInt = parseInt(generatedNb);
			if (P[generatedNbAsInt]) count++;
		}
		if (count === m) return true;
	}
	else { // do the same as above except start from 0 rather than 1
		for (var i = 0; i <= 9; i++) {
			var generatedNb = "";
			var iAsStr = i.toString();
			for (var j = 0; j < nAsStr.length; j++) {
				if (T.indexOf(j) > -1) generatedNb += iAsStr;
				else generatedNb += nAsStr.substring(j, j + 1);
			}
			var generatedNbAsInt = parseInt(generatedNb);
			if (P[generatedNbAsInt]) count++;
		}
		if (count === m) return true;
	}
}

function isPrimeFam(n, m, P) {
	var nAsStr = n.toString();
	for (var i = 1; i < nAsStr.length; i++) {
		var startingValue = [];
		for (var j = 0; j < i; j++) {
			startingValue.push(j);
		}
		var upperLimit = Combinatorics.nChooseK(startingValue.length, nAsStr.length);
		for (var k = 0; k < upperLimit; k++) {
			if (isPrimeFamAux(n, m, startingValue, P)) return true;
			startingValue = next(startingValue, nAsStr.length - 1);
		}
	}
	return false;
}
