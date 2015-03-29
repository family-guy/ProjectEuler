/**
 * Problem 70
 */

var IntegerArithmetic = require('../libraries/IntegerArithmetic.js');
var Combinatorics = require('../libraries/Combinatorics.js');
var upperBound = Math.floor(Math.sqrt(10000000));
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
var index = 87109;
var minRatio = index / phi(index, primes);
for (var n = 2; n < 10000000; n++) {
	var phiOfn = phi(n, primes);
	if (Combinatorics.isPerm(phiOfn, n)) {
		var ratio = n / phiOfn;
		if (ratio < minRatio) {
			minRatio = ratio;
			index = n;
		} 
	}
}
console.log('The solution is ' + index);

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