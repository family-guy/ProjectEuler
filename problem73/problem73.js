/**
 * Problem 73
 */

var IntegerArithmetic = require('../libraries/IntegerArithmetic.js');
var n = 12000;
var sum = 0;
for (var i = 2; i <= n; i++) {
	var lowerBound = Math.floor(Math.ceil(i / 3));
	var upperBound = Math.floor(Math.floor(i / 2));
	for (var j = lowerBound; j <= upperBound; j++) {
		if (IntegerArithmetic.gcd(j, i) == 1) {
			if (!((j == 1 && i == 2) || (j == 1 && i == 3))) {
				sum++;
			}
		}
	}
}
console.log('The solution is ' + sum);

