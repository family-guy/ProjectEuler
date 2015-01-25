/**
 * Problem 76
 * http://mathworld.wolfram.com/PartitionFunctionP.html
 */

var prevVals = [];
prevVals.push(1);
prevVals.push(1);
var n = 100;
while (prevVals.length - 1 < n) {
	prevVals.push(nextValOfPn(prevVals));
}
console.log("The solution is " + (prevVals[prevVals.length - 1] - 1));

/* 
* Takes an array of previous values P(n - 1), ..., P(0) and returns P(n), n >= 2
* @param array of integers prevValsOfPn
* @return integer
*/
function nextValOfPn(prevValsOfPn) {
	var sumA = 0; var sumB = 0;
	var n = prevValsOfPn.length;
	for (var k = 1; k <= n; k++) {
		var termA = n - functA(k);
		if (termA < 0) break;
		sumA += Math.pow(-1, k + 1) * prevValsOfPn[termA];
	}
	for (var k = 1; k <= n; k++) {
		var termB = n - functB(k);
		if (termB < 0) break;
		sumB += Math.pow(-1, k + 1) * prevValsOfPn[termB];
	}
	return sumA + sumB;
}

/*
* @param integer k
* @return integer
*/
function functA(k) {
	return k * (3 * k - 1) / 2;
}

/*
* @param integer k
* @return integer
*/
function functB(k) {
	return k * (3 * k + 1) / 2;
}	

