/**
 * Problem 56
 */

var max = 0;
for (var a = 1; a < 100; a++) {
	for (var b = 1; b < 100; b++) {
		var exp = exponent(a, b);
		var value = sumDigits(exp);
		if (value > max) max = value;
	}
}
console.log('The solution is ' + max);

/**
* Takes two integers, one represented as a string, and returns their product represented as a string
* @param string a
* @param integer b
* @return string
*/
function multiply(a, b) {
	var result = '';
	var carryOver = 0; var x = 0; var i = 0;
	for (i = a.length - 1; i > 0; i--) {
		x = parseInt(a.substring(i, i + 1));
		var value = (x * b + carryOver) % 10;
		result = value.toString() + result;
		carryOver = Math.floor((x * b + carryOver) / 10);
	}
	x = parseInt(a.substring(i, i + 1));
	return (x * b + carryOver).toString() + result;
}

/**
* Calculates a^b
* @param integer a
* @param integer b
* @return string
*/
function exponent(a, b) {
	var result = '1';
	for (var i = 0; i < b; i++) {
		result = multiply(result, a);
	}
	return result;
}

/**
* Takes an integer represented as a string and returns the sum of its digits
* @param string n
* @return integer
*/
function sumDigits(n) {
	var sum = 0;
	for (var i = 0; i < n.length; i++) {
		sum += parseInt(n.substring(i, i + 1));
	}
	return sum;
}