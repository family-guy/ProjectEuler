/**
 * Problem 64
 */

var nbOddPeriods = 0;
var N = 10000;
for (var i = 0; i <= N; i++) {
	if (!isSquare(i)) {
		if (period(i) % 2 == 1) nbOddPeriods++;
	}
}
console.log('The solution is ' + nbOddPeriods);

/*
* Returns whether a positive integer is a square
* @param integer n
* @return boolean
*/
function isSquare(n) {
	var a = Math.floor(Math.sqrt(n));
	return a * a == n
}

/*
* Takes a triple (a, b, c) representing a fraction of the form a / (sqrt(b) + c) and returns the next fraction (of the same form) in the sequence
* @param array of integers T
* @return array of integers
*/
function nextTerm(T) {
	var result = [];
	for (var i = 0; i < 3; i++) {
		result.push(0);
	}
	result[1] = T[1];
	var intermedFracDen = T[1] - T[2] * T[2];
	var intermedFracWholePart = Math.floor(T[0] * (Math.sqrt(T[1]) - T[2]) / intermedFracDen);
	result[0] = Math.floor(intermedFracDen / T[0]);
	result[2] = (-1) * T[2] - intermedFracWholePart * result[0];
	return result;
}

/*
* Takes a positive non-square integer and returns its period
* @param integer n
* @return integer
*/
function period(n) {
	var a_0 = Math.floor(Math.sqrt(n));
	var firstTriple = [1, n, (-1) * a_0];
	var currentTriple = [1, n, (-1) * a_0];
	var result = 1;
	while (true) {
		var next = nextTerm(currentTriple);
		if (equals(next, firstTriple)) break;
		for (var i = 0; i < currentTriple.length; i++) {
			currentTriple[i] = next[i];
		}
		result++;
 	}
	return result;
}

/*
* Returns if two arrays of integers are equal
* @param array of integers T
* @param array of integers U
* @return boolean
*/
function equals(T, U) {
	if (T.length != U.length) return false;
	for (var i = 0; i < T.length; i++) {
		if (T[i] != U[i]) return false;
	}
	return true;
}
