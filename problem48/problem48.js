/* Problem 48
 * multiply: takes a positive integer and an array of 10 integers and returns an array which is the original array multiplied by the integer e.g. multiply(5, {0, 0, 0, 0, 0, 0, 1, 0, 2, 3}) returns {0, 0, 0, 0, 0, 0, 5, 1, 1, 5}
 * power: returns the last 10 digits in an array e.g. power(2, 9) = {0, 0, 0, 0, 0, 0, 0, 5, 1, 2}
 * add: takes two arrays representing the last 10 digits of two integers and returns the last 10 digits of their sum e.g. add({0, 0, 0, 0, 0, 0, 0, 1, 2, 4}, {0, 0, 0, 0, 0, 0, 1, 6, 0, 1}) returns {0, 0, 0, 0, 0, 0, 1, 7, 2, 5}
*/

var n = 1000; var result = [];
for (var i = 0; i < 10; i++) {
	result.push(0);
}
for (var i = 1; i <= n; i++) {
	result = add(result, power(i, i));
}
var str = "";
for (var i = 0; i < result.length; i++) {
	str += result[i].toString();
}
console.log("The solution is " + str);

function multiply(n, T) {
	var result = []; var carryOver = 0;
	for (var i = 0; i < T.length; i++) {
		result.push(0);
	}
	for (var i = T.length - 1; i > -1; i--) {
		result[i] = (n * T[i] + carryOver) % 10;
		carryOver = Math.floor((n * T[i] + carryOver) / 10);
	}
	return result;
}

function power(a, b) {
	var result = [];
	for (var i = 0; i < 10; i++) {
		result.push(0);
	}
	if (b === 0) {
		result[result.length - 1] = 1;
		return result;
	}
	var aAsStr = a.toString(); var j = 0;
	for (var i = aAsStr.length - 1; i > -1; i--) {
		result[result.length - 1 - j] = parseInt(aAsStr.substring(i, i + 1));
		j++;
	}
	for (var i = 1; i < b; i++) {
		result = multiply(a, result);
	}
	return result;
}

function add(T, U) {
	var result = [];
	for (var i = 0; i < 10; i++) {
		result.push(0);
	}
	var carryOver = 0;
	for (var i = T.length - 1; i > -1; i--) {
		result[i] = (T[i] + U[i] + carryOver) % 10;
		carryOver = Math.floor((T[i] + U[i] + carryOver) / 10);
	}
	return result;
}
