/* Problem 37
 * isTruncLeftToRight: takes a positive integer and a boolean array and returns whether the integer is 'truncatable' from left to right
 * isTruncRightToLeft: takes a positive integer and a boolean array and returns whether the integer is 'truncatable' from right to left
*/

var IntegerArithmetic = require("../../Documents/projet_personnel/informatique/javaScript/IntegerArithmetic.js"); // loads the file in quotation marks. Henceforth can use the functions defined there. Assumes node.js has been downloaded

var n = 1000000; var sum = 0; var count = 0;
var primes = IntegerArithmetic.sieveEratosthenes(n);
for (var i = 10; i <= n; i++) {
	if (isTruncLeftToRight(i, primes) && isTruncRightToLeft(i, primes)) {
		count++;
		sum += i;
	}
}
console.log("We can confirm that there are " + count + " such truncatable primes. The solution is " + sum);

function isTruncLeftToRight(n, p) {
	var nAsStr = n.toString();
	for (var i = 0; i < nAsStr.length; i++) {
		if (!p[parseInt(nAsStr.substring(i))]) return false;
	}
	return true;
}

function isTruncRightToLeft(n, p) {
	var nAsStr = n.toString();
	for (var i = 0; i < nAsStr.length; i++) {
		if (!p[parseInt(nAsStr.substring(0, nAsStr.length - i))]) return false;
	}
	return true;
}