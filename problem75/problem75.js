/**
 * Problem 75
 * http://en.wikipedia.org/wiki/Pythagorean_triple#Generating_a_triple
 */

var IntegerArithmetic = require('../libraries/IntegerArithmetic.js');
var L = 1500000;
var triples = pythagPrimTrip(L / 2);
var freq = [];
for (var i = 0; i < L + 1; i++) {
	freq.push(0);
}
for (var i = 0; i < triples.length; i++) {
	var a = triples[i][0];
	var b = triples[i][1];
	var c = triples[i][2];
	for (var k = 1; k * (a + b + c) <= L; k++) {
		freq[k * (a + b + c)]++;
	}
}
var count = 0;
for (var i = 0; i < freq.length; i++) {
	if (freq[i] == 1) count++;
}
console.log("The solution is " + count);

/*
* Returns all primitive Pythagorean triples with hypoteneuse less than c
* @param integer c
* @return two dimensional array of integers
*/
function pythagPrimTrip(c) {
	var result = [];
	var pairs = coprimePairs(c);
	for (var i = 0; i < pairs.length; i++) {
		var pythagTrip = [];
		var m = pairs[i][1];
		var n = pairs[i][0];
		pythagTrip.push(m * m - n * n);
		pythagTrip.push(2 * m * n);
		pythagTrip.push(m * m + n * n);
		result.push(pythagTrip);
	}
	return result;
}

/*
* Returns all distinct pairs of positive integers (p, q), p < q, such that p^2 + q^2 <= n, (p, q) are coprime and q - p is odd
* @param integer n
* @return two dimensional array of integers
*/
function coprimePairs(n) {
	var result = [];
	for (var i = 1; i * i < n; i++) {
		for (var j = i + 1; j * j < n; j++) {
			if (i * i + j * j <= n) {
				if ((j - i) % 2 == 1) {
					if (IntegerArithmetic.gcd(i, j) == 1) {
						var pair = [];
						pair.push(i); pair.push(j);
						result.push(pair);
					}
				}
			}
		}
	}
	return result;
}