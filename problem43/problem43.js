/* Problem 42
 * hasProp: takes a 0 to 9 pandigital number and returns whether it has the property given in the question
*/

var Combinatorics = require("../../Documents/projet_personnel/informatique/javaScript/Combinatorics.js");
var permutations = Combinatorics.permutationsOfAString("0123456789");
var sum = 0;
for (var i = 0; i < permutations.length; i++) {
	if (hasProp(permutations[i])) {
		var n = parseInt(permutations[i]);
		sum += n;
	}
}
console.log("The solution is " + sum);

function hasPropAux(s, T) {
	for (var i = 0; i < T.length; i++) {
		var subStr = s.substring(i + 1, i + 4);
		var subStrAsInt = parseInt(subStr);
		if (subStrAsInt % T[i] != 0) return false;
	}
	return true;
}

function hasProp(s) {
	var T = [2, 3, 5, 7, 11, 13, 17];
	return hasPropAux(s, T);
}