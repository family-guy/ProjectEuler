/* Problem 46
 * isSq: returns whether a given non negative integer is a square
 * isSumOfPrimeAndTwiceASq: takes a boolean array where the i-th element says whether i is prime and a positive integer and returns whether the integer is the sum of a prime and twice a square
*/

var IntegerArithmetic = require("../../Documents/projet_personnel/informatique/javaScript/IntegerArithmetic.js");
var n = 50000000; // it turns out this upper limit can be a lot smaller. In JS, this is upper limit is lower than in Java and C++ due to insufficient memory
var primes = IntegerArithmetic.sieveEratosthenes(n);
var i = 3;
while (true) {
	while (primes[i]) i += 2; // get next odd composite number
	if(!isSumOfPrimeAndTwiceASq(i, primes)) break;
	i += 2;
}
console.log("The solution is " + i);

function isSq(n) {
	if (n === 0) return false;
	return Math.sqrt(n) === Math.floor(Math.sqrt(n));
}

function isSumOfPrimeAndTwiceASq(n, P) {
	for (var i = 0; i < n; i++) {
		if (P[i]) {
			var remainder = n - i;
			if (remainder % 2 === 0) {
				remainder = Math.floor(remainder / 2);
				if (isSq(remainder)) return true;
			}
		}
	}
	return false;
}