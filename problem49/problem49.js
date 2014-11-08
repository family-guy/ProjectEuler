/* Problem49 */

var IntegerArithmetic = require("../../Documents/projet_personnel/informatique/javaScript/IntegerArithmetic.js");
var Combinatorics = require("../../Documents/projet_personnel/informatique/javaScript/Combinatorics.js");
var solutions = [];
var primes = IntegerArithmetic.sieveEratosthenes(9999);
var fourDigitPrimes = [];
for (var i = 1000; i < 10000; i++) {
	if (primes[i]) fourDigitPrimes.push(i);
}
for (var i = 0; i < fourDigitPrimes.length - 2; i++) {
	var ithElt = fourDigitPrimes[i];
	for (var j = i + 1; j < fourDigitPrimes.length - 1; j++) {
		var jthElt = fourDigitPrimes[j];
		if (Combinatorics.isPerm(ithElt, jthElt)) {
			var difference = jthElt - ithElt; var thirdTerm = jthElt + difference;
			if (thirdTerm < 10000 && primes[thirdTerm] && Combinatorics.isPerm(ithElt, thirdTerm)) {
				solutions.push(ithElt.toString() + jthElt.toString() + thirdTerm.toString());
			}
		}
	}
}
console.log("The concatentions of the two possible sequences are " + solutions);
for (var i = 0; i < solutions.length; i++) {
	if (solutions[i] !== "148748178147") console.log("Therefore the solution is " + solutions[i]);
}