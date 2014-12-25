/**
 * Problem 65
 */

var values = [];
var valuesLength = 99;
for (var i = 0; i < valuesLength; i++) {
	if (i % 3 == 0 || i % 3 == 2) values.push('1');
	else {
		var x = (Math.floor(i / 3) + 1) * 2;
		values.push(x.toString());
	}
}
var currentFrac = [];
currentFrac.push(values[values.length - 1]);
currentFrac.push('1');
for (var i = values.length - 2; i > -1; i--) {
	var currentInt = values[i];
	var next = f(currentInt, currentFrac[0], currentFrac[1]);
	currentFrac[0] = next[0];
	currentFrac[1] = next[1];
}
console.log('The solution is ' + sumDigits(add(mult(2, currentFrac[0]), currentFrac[1])));

/**
* Returns the sum of digits of an integer represented as a string
* @param string str
* @param integer
*/
function sumDigits(str) {
	var result = 0;
	for (var i = 0; i < str.length; i++) {
		result += parseInt(str.substring(i, i + 1));
	}
	return result;
}

/**
* Takes a triple (a, b_1, b_2) and returns a + 1 / b where b = b_1 / b_2
* @param string a
* @param string b_1
* @param string b_2
* @return array of strings
*/
function f(a, b_1, b_2) {
	var result = [];
	var aAsInt = parseInt(a);
	result.push(add(mult(aAsInt, b_1), b_2)); // numerator
	result.push(b_1); // denominator
	return result;
}

/**
* Returns the product of two integers, one of which is represented by a string
* @param integer a
* @param string b
* @return string
*/
function mult(a, b) {
	if (a == 1) return b;
	return add(mult(a - 1, b), b);
}

/**
* Takes two integers a and b, where a >= b, represented by strings, and returns a string representing their sum. 
* @param string a
* @param string b
* @return string
*/
function add(a, b) {
	var result = '';
	var difference = a.length - b.length;
	for (var i = 0; i < difference; i++) {
		b = '0' + b;
	}
	var carryOver = 0;
	for (var i = a.length - 1; i > -1; i--) {
		var x = parseInt(a.substring(i, i + 1));
		var y = parseInt(b.substring(i, i + 1));
		var value = (x + y + carryOver) % 10;
		result = value.toString() + result;
		carryOver = Math.floor((x + y + carryOver) / 10);
	}
	if (carryOver == 0) return result;
	return carryOver.toString() + result;
}