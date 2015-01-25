/**
 * Problem 77
 */

var IntegerArithmetic = require('../libraries/IntegerArithmetic.js');
var n = 1000000;
var prime = IntegerArithmetic.sieveEratosthenes(n);
var i = 10;
var k = 5000;
while (true) {
	var primes = primesLessThanN(i, prime);
	if (nbOfWays(i, primes) > k) {
		console.log("The solution is " + i);
		break;
	}
	i++;
}

/*
* Returns the primes less than a given integer
* @param integer n
* @param array of booleans prime
* @return array of integers
*/
function primesLessThanN(n, prime) {
	var result = [];
	for (var i = 0; i < n; i++) {
		if (prime[i]) result.push(i);
	}
	return result;
}

/*
* Returns the number of ways in which an integer can be written as the sum of some given integers
* @param integer t
* @param array of integers c
* @param integer
*/
function nbOfWays(t, c) {
	if (c.length == 1) {
		if (t % c[0] == 0 && t / c[0] > 0) return 1;
		else return 0;
	}
	var sum = 0; var new_c = [];
	for (var i = 0; i < c.length - 1; i++) {
		new_c[i] = c[i + 1];
	}
	if (t % c[0] == 0 && t / c[0] > 0) sum++;
	while (t > 0) {
		sum += nbOfWays(t, new_c);
		t -= c[0];
	}
	return sum; 
}