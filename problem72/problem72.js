/**
 * Problem 72
 */

var IntegerArithmetic = require('../libraries/IntegerArithmetic.js');
var upperBound = Math.floor(Math.sqrt(1000000));
var prime = IntegerArithmetic.sieveEratosthenes(upperBound);
var nbPrimes = 0;
for (var i = 0; i < prime.length; i++) {
	if (prime[i]) nbPrimes++;
}
var primes = [];
var j = 0;
for (var i = 0; i < prime.length; i++) {
	if (prime[i]) primes.push(i);
}	
var sum = 0;
for (var d = 2; d <= 1000000; d++) {
	sum += phi(d, primes);
}
console.log('The solution is ' + sum);

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