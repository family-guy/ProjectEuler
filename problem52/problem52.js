/* Problem 52 */

var Combinatorics = require("../../Documents/projet_personnel/informatique/javaScript/Combinatorics.js");
var i = 1; var n = 6;
var k = 0;
while (true) {
	for (k = 2; k < n; k++) {
		if (!Combinatorics.isPerm(i, i * k)) break;
	}
	if (Combinatorics.isPerm(i, i * k) && k === n) {
		console.log("The solution is " + i);
		break;
	}
	i++;
}
