/**
 * Problem 58
 */

var IntegerArithmetic = require('../libraries/IntegerArithmetic.js');
var currentVal = 1; var nbPrimes = 0;
var count = 1; var ratio = 1;
var i = 2;
while(true) {
	for (var j = 0; j < 4; j++) {
		currentVal += i;
		count++;
		if (IntegerArithmetic.isPrime(currentVal)) nbPrimes++;
		if (j == 3) ratio = nbPrimes / count;
	}
	if (ratio < 0.1) {
		console.log('The solution is ' + (i + 1));
		break;
	}
	i += 2;
}