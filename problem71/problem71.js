/**
 * Problem 71
 */

var IntegerArithmetic = require('../libraries/IntegerArithmetic.js');
var n = 1000000;
var currentBest = [2, 5];
for (var i = 3; i <= n; i++) {
	if (i != 7) {
		var x = Math.floor((3 / 7) * i);
		if (IntegerArithmetic.gcd(x, i) == 1) {
			var frac = [];
			frac.push(x);
			frac.push(i);
			if (greaterThan(frac, currentBest)) {
				currentBest[0] = frac[0];
				currentBest[1] = frac[1];
			}
		}
	}
}
console.log('The fraction immediately to the left of 3/7 is ' + currentBest[0] + '/' + currentBest[1] + '. The solution is ' + currentBest[0]);

/*
* Returns whether frac1 is greater than frac 2
* @param frac1 array of integers
* @param frac2 array of integers
* @return boolean
*/
function greaterThan(frac1, frac2) {
	return frac1[0] * frac2[1] > frac1[1] * frac2[0];
}