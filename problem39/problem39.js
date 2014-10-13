/* Problem 39
 * isPythag: returns whether three positive integers make up a Pythagorean triple
*/

var P = [];
for (var i = 0; i < 1001; i++) {
	P.push(0);
}
for (var i = 1; i < 998; i++) {
	for (var j = i + 1; j < 999; j++) {
		for (var k = j + 1; k < 1000; k++) {
			var p = i + j + k;
			if (p <= 1000 && isPythag(i, j, k)) {
				P[p]++;
			}
		}
	}
}
var maxIndex = 0;
for (var i = 0; i < P.length; i++) {
	if (P[maxIndex] < P[i]) maxIndex = i;
}
console.log("The value of p for which the number of solutions is maximised is " + maxIndex + " which has " + P[maxIndex] + " solutions");

function isPythag(a, b, c) {
	return (a * a + b * b === c * c);
}



