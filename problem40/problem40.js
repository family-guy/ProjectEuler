/* Problem 40
 * digit: takes a positive integer n and returns the n-th digit of the fractional part of the irrational decimal fraction
*/

var product = 1;
for (var i = 0; i < 7; i++) {
	product *= digit(Math.pow(10, i));
}
console.log("The solution is " + product);

function digit(n) {
	var i = 1; var count = 0;
	var iAsStr = ""; var j = 0;
	while (true) {
		iAsStr = i.toString();
		for (j = 0; j < iAsStr.length; j++) {
			count++;
			if (count === n) break;
		}
		if (count === n) break;
		i++;
	}
	return parseInt(iAsStr.charAt(j));
}