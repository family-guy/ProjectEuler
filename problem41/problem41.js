/* Problem 41
 * permutationsOfAString: returns all permutations of a string in an array
 * isPrime: returns whether a positive integer is prime
*/

var Combinatorics = require("../../Documents/projet_personnel/informatique/javaScript/Combinatorics.js");
var IntegerArithmetic = require("../../Documents/projet_personnel/informatique/javaScript/IntegerArithmetic.js");
var T = ["123456789", "12345678", "1234567", "123456", "12345", "1234", "123", "12"];
var nbPanPrime = 0; var max = 0;
for (var i = 0; i < T.length; i++) {
	var permutations = Combinatorics.permutationsOfAString(T[i]);
	for (var j = 0; j < permutations.length; j++) {
		var p = parseInt(permutations[j]);
		if (IntegerArithmetic.isPrime(p)) {
			nbPanPrime++;
			if (p > max) max = p;
		}
	}
	if (nbPanPrime > 0) break;
}
console.log("The solution is " + max);