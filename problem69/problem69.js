/**
 * Problem 69
 */

var IntegerArithmetic = require('../libraries/IntegerArithmetic.js');
var n = Math.floor(Math.sqrt(1000000));
var prime = IntegerArithmetic.sieveEratosthenes(n);
var nbPrimes = 0;
for (var i = 0; i < prime.length; i++) {
	if (prime[i]) nbPrimes++;
}
var primes = [];
var j = 0;
for (var i = 0; i < prime.length; i++) {
	if (prime[i]) primes.push(i);
}
var maxRatio = 0;
var index = 0;
for (var i = 2; i <= 1000000; i++) {
	var ratio = i / phi(i, primes);
	if (ratio > maxRatio) {
		maxRatio = ratio;
		index = i;
	} 
}
console.log ('The solution is ' + index);

/**
* Returns phi n
* @param integer n
* @param array of integers primes
* @return integer
*/
function phi(n, primes) {
	var primeDec = IntegerArithmetic.primeDecomp(n, primes);
	var result = 1;
	for (var i = 0; i < primeDec.length; i++) {
		result *= (primeDec[i][0] - 1) * Math.floor(Math.pow(primeDec[i][0], primeDec[i][1] - 1));
	}
	return result;
}