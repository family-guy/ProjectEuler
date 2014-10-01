/* Problem 35
 * convertIntToArray: returns a given integer as a dynamic array e.g. convertIntToArray(123) = [1, 2, 3]
 * sieveEratosthenes: returns all prime numbers less than a given positive integer
 * isPrimeCircular: takes a prime and returns whether it is circular
*/

var N = 1000000;
/* create an array of all primes less than N */
var P = sieveEratosthenes(N);
var allPrimesUnderN = [];
for (var i = 0; i < P.length; i++) {
	if (P[i]) allPrimesUnderN.push(i);
} // done
var count = 0;
for (var i = 0; i < allPrimesUnderN.length; i++) {
	if (isPrimeCircular(allPrimesUnderN[i], P)) count++;
}
console.log("The solution is " + count);

function isPrimeCircular(p, P) {
	var pAsArray = convertIntToArray(p);
	if (pAsArray.length == 1) return true;
	else {
		var pNbDigits = pAsArray.length; pRotated = [];
		for (var nbRotations = 1; nbRotations < pNbDigits; nbRotations++) {
			for (var i = 0; i < pNbDigits; i++) {
				pRotated[i] = pAsArray[(i + nbRotations) % pNbDigits];
			}
			/* convert pRotated to an integer and check if it is prime */
			var sum = 0;
			for (var j = pNbDigits - 1; j >= 0; j--) {
				sum += Math.pow(10, pNbDigits - 1 - j) * pRotated[j];
			}
			if (!P[sum]) return false;
		}
		return true;
	}
}

function convertIntToArray(n) {
	var result = []; var divisor = 1; var dividend = n;
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

function sieveEratosthenes(n) {
	result = [];
	for (var i = 0; i < n + 1; i++) { // starting case, all values are true
		result[i] = true;
	}
	result[0] = false; result[1] = false;
	for (var i = 0; i * i < n; i++) { // the exit condition comes from "(for all i in {1, ..., [sqrt n]} s.t i is prime, n is not divisible by i) => n is prime"
		if (result[i] == true) {
			for (var j = 2; i * j <= n; j++) {
				result[i * j] = false;
			}
		} 
	}
	return result;
}