/* Problem 44
 * areSumAndDiffPen: returns whether a pair of integers has the property in the question
*/

var n = 50000000;
var isPent = []; // default values are false
for (var i = 0; i < n; i++) {
	isPent.push(false);
}
for (var i = 1; i * (3 * i - 1) / 2 < isPent.length ; i++) {
	isPent[i * (3 * i - 1) / 2] = true;
}
var upperLimit = 10000; // upper limit is arbitrary and we assume that the minimum found with this upper limit is the minimum that we are looking for
var diffsOfPairs = [];
for (var i = 1; i < upperLimit; i++) {
	for (var j = i + 1; j < upperLimit + 1; j++) {
		if(areSumAndDiffPen(i, j, isPent)) {
			diffsOfPairs.push(j * (3 * j - 1) / 2 - i * (3 * i - 1) / 2);
		}
	}
}
var minDiff = diffsOfPairs[0];
for (var i = 0; i < diffsOfPairs.length; i++) {
	if (minDiff > diffsOfPairs[i]) minDiff = diffsOfPairs[i];
}
console.log("The solution is " + minDiff);

function areSumAndDiffPen(p, q, T) {
	var pthPentNb = p * (3 * p - 1) / 2;
	var qthPentNb = q * (3 * q - 1) / 2;
	return T[pthPentNb + qthPentNb] && T[qthPentNb - pthPentNb];
}