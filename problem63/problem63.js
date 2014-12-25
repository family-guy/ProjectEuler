/**
 * Problem 63
 */

var sum = 0;
for (var i = 1; i < 10; i++) {
	sum += nbNDigitIntsNthPow(i);
}
console.log('The solution is ' + sum);

/*
* Returns the powerful digit count for a given base
* @param integer base
* @return integer
*/
function nbNDigitIntsNthPow(base) {
	var result = 0;
	var exponent = 1;
	while (true) {
		var str = power(base, exponent);
		if (str.length == exponent) result++;
		if (str.length < exponent) break;
		exponent++;
	}
	return result;
}

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
function power(a, b) {
	var result = '1';
	for (var i = 0; i < b; i++) {
		result = multiply(result, a);
	}
	return result;
}