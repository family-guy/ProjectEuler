/* Problem 29
 * multiplication: takes a dynamic array of numbers, multiplies by an integer and returns the result in a new dynamic array e.g. multiplication([1, 2, 3], 10) returns [1, 2, 3, 0]
 * exponent: exponent(a, b) returns a^b e.g. exponent(2, 9) returns [5, 1, 2]
 * isEqual: checks whether two dynamic arrays have the same values e.g. isEqual([5, 1, 2], [5, 1, 2]) returns true
 * isIn: returns whether a dynamic array of dynamic arrays contains a dynamic array given
*/

var lowerBound = 2; var upperBound = 100; var nbOfDistinctTerms = 0;
var prevValues = [];
for (var p = lowerBound; p <= upperBound; p++) {
	for (var q = lowerBound; q <= upperBound; q++) {
		if (!isIn(prevValues, exponent(p, q))) {
			nbOfDistinctTerms++; prevValues.push(exponent(p, q));
		}
	}
}
console.log("The solution is ", nbOfDistinctTerms);

function multiplication(array, n) {
	var result = []; var carryOver = 0; var p = 0;
	for (var i = 0; i < array.length - 1; i++) {
		p = array[array.length - 1 - i] * n + carryOver; 
		result.push(p % 10); carryOver = Math.floor(p / 10); 
	}
	p = array[0] * n + carryOver;
	while (true) {
		result.push(p % 10);
		if (Math.floor(p / 10) == 0) break;
		else p = Math.floor(p / 10);
	}
	return result.reverse();
}

function exponent(a, b) {
	var result = [1];
	for (var i = 0; i < b; i++) {
		result = multiplication(result, a);
	}
	return result;
}

function isEqual(array1, array2) {
	if (array1.length != array2.length) return false;
	for (var i = 0; i < array1.length; i++) {
		if (array1[i] != array2[i]) return false;
	}
	return true;
}

function isIn(array1, array2) { // array 1 is an array of arrays
	for (var i = 0; i < array1.length; i++) {
		if (isEqual(array1[i], array2)) return true;
	}
	return false;
} 
