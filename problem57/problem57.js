/**
 * Problem 57
 */

var a = "1"; var b = "1";
var count = 0;
for (var i = 0; i < 1000; i++) {
	var temp = a;
	a = add(add(a, b), b);
	b = add(temp, b);
	if (a.length > b.length) count++;
}
console.log('The solution is ' + count);

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