/**
 * Problem 60
 */

var IntegerArithmetic = require('../libraries/IntegerArithmetic.js');
var n = 10000; // assumes that there exists a set of five primes with the desired property such that each prime in the set is less than 10000
var primes = [];
for (var i = 0; i < n; i++) {
	if (IntegerArithmetic.isPrime(i)) primes.push(i);
}
var minSum = n * 5;
var currentSets = primePairs(primes); // initialise the 2D dynamic array. Needs to be iterated on 3 times
for (var i = 0; i < 3; i++) {
	var result = setsOfPrimes(currentSets, primes);
	currentSets.length = 0; // clear the array before doing a deep copy
	for (var k = 0; k < result.length; k++) {
		currentSets.push(result[k]);
	}
}
// Get the minimum sum from currentSets
for (var i = 0; i < currentSets.length; i++) {
	var sum = sumElts(currentSets[i]);
	if (sum < minSum) minSum = sum;
}
console.log('The solution is ' + minSum);

/**
* Takes a set of prime pair sets of cardinality n and returns a set of prime pair sets of cardinality n + 1
* @param 2D array of integers currentSets
* @param array of integers primes 
*/
function setsOfPrimes(currentSets, primes) {
	var result = [];
	var sums = []; // used to reduce number of duplicates
	for (var t = 0; t < primes.length; t++) {
		for (var i = 0; i < currentSets.length; i++) {
			var currentSet = currentSets[i];
			var sum = sumElts(currentSet);
			if (hasPrimeSetProp(currentSet, primes[t]) && sums.indexOf(sum) == -1) {
				sums.push(sum);
				var resultSet = [];
				for (var j = 0; j < currentSet.length; j++) {
					resultSet.push(currentSet[j]);
				}
				resultSet.push(primes[t]);
				result.push(resultSet);
			}
		}
	}
	return result;
}

/**
* Takes a set of primes and a prime p and returns whether each pair that includes p is a prime pair
* @param array of integers primeSet
* @param integer p
*/
function hasPrimeSetProp(primeSet, p) {
	for (var i = 0; i < primeSet.length; i++) {
		if (!isPrimePair(primeSet[i], p)) return false;
	}
	return true;
}

/**
* Returns whether a pair of primes is a prime pair
* @param integer a
* @param integer b
*/
function isPrimePair(a, b) {
	var aAsStr = a.toString();
	var bAsStr = b.toString();
	return IntegerArithmetic.isPrime(parseInt(aAsStr + bAsStr)) && IntegerArithmetic.isPrime(parseInt(bAsStr + aAsStr));
}

/**
* Takes a set of primes and returns all the prime pairs that can be made from it
* @param array of integers primes
*/
function primePairs(primes) {
	result = [];
	for (var i = 0; i < primes.length; i++) {
		for (var j = i + 1; j < primes.length; j++) {
			if (isPrimePair(primes[i], primes[j])) {
				var pair = [];
				pair.push(primes[i]); pair.push(primes[j]);
				result.push(pair);
			}
		}
	}
	return result;
}

/**
* Returns the sum of the elements of a dynamic array
* @param array of integers T
*/
function sumElts(T) {
	var sum = 0;
	for (var i = 0; i < T.length; i++) {
		sum += T[i];
	}
	return sum;
}