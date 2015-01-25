/**
 * Problem 78
 * http://mathworld.wolfram.com/PartitionFunctionP.html
 */

var prevVals = [];
prevVals.push(1);
prevVals.push(1);
while (true) {
	var nextVal = nextValOfPn(prevVals);
	if (nextVal == 0) {
		console.log("The solution is " + prevVals.length);
		break;
	}
	prevVals.push(nextVal);
}

/* 
* Takes an array of previous values P(n - 1), ..., P(0) and returns P(n), n >= 2. All values modulo one million
* @param array of integers prevValsOfPn
* @return integer
*/

function nextValOfPn(prevValsOfPn) {
	var sumA = 0; var sumB = 0;
	var m = 1000000;
	var n = prevValsOfPn.length;
	for (var k = 1; k <= n; k++) {
		var termA = n - functA(k);
		if (termA < 0) break;
		sumA += Math.pow(-1, k + 1) * (prevValsOfPn[termA] % m);
	}
	for (var k = 1; k <= n; k++) {
		var termB = n - functB(k);
		if (termB < 0) break;
		sumB += Math.pow(-1, k + 1) * (prevValsOfPn[termB] % m);
	}
	sumA = mod(sumA, m); sumB = mod(sumB, m);
	return mod(sumA + sumB, m);
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

/* 
* Returns a bar in Z / bZ
* @param integer a
* @param integer b
* @return integer
*/
function mod(a, b) {
	if (a >= 0 || a % b == 0) return a % b;
	var x = a % b;
	while (true) {
		x += b;
		if (x >= 0) return x;
	}
}

