/* Problem 50
 * consecutivePrimes: takes a prime as a starting value and returns in an array the prime, below a certain number, which can be written as the sum of the most consecutive primes as well as the number of consecutive primes that make up the sum 
*/

var IntegerArithmetic = require("../../Documents/projet_personnel/informatique/javaScript/IntegerArithmetic.js");
var n = 1000000;
var isPrime = IntegerArithmetic.sieveEratosthenes(n);
// get the primes under n
var primes = [];
for (var i = 0; i < isPrime.length; i++) {
	if (isPrime[i]) {
		primes.push(i);
	}
}
var mostConsecPrimes = 0; var sumOfMostConsecPrimes = 2;
for (var i = 0; i < primes.length - 1; i++) {
	var consecPrimes = consecutivePrimes(i, primes, isPrime);
	//console.log(i);
	if (consecPrimes[0] > mostConsecPrimes) {
		mostConsecPrimes = consecPrimes[0];
		sumOfMostConsecPrimes = consecPrimes[1];
	}
}
console.log("The solution is " + sumOfMostConsecPrimes + " which can be written as the sum of " + mostConsecPrimes + " consecutive primes");

function consecutivePrimes(a, P, Q) { // a is the index of the starting value to be used in conjunction with P
	var result = [];
	var longestStreak = 0; var longestStreakPrime = 2;
	var sum = 0; var difference = a;
	while (true) {
		sum += P[a];
		if (sum > Q.length - 1) break;
		if (Q[sum]) {
			longestStreak = a + 1 - difference;
			longestStreakPrime = sum;
		}
		a++;
	}
	result.push(longestStreak); result.push(longestStreakPrime);
	return result;
}