/**
 * Problem 55
 */

var nbLychrels = 0;
for (var i = 1; i < 10000; i++) {
	if (nbIterations(i) == -1) nbLychrels++;
}
console.log("The solution is " + nbLychrels);

/**
* Returns the number of iterations required to produce a palindrome. If not less than 50, returns -1
* @param integer n
* @param integer
*/
function nbIterations(n) {
	var nAsStr = n.toString();
	for (var i = 1; i < 50; i++) {
		var sum = add(nAsStr, reverse(nAsStr));
		if (reverse(sum) == sum) return i;
		nAsStr = sum;
	}
	return -1;
}

/**
* Takes two integers that have the same number of digits, represented by strings, and returns a string representing their sum. 
* @param string a, string b
* @return string
*/
function add(a, b) {
	var result = "";
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

/**
* Takes a string and returns its reverse
* @param string str
* @return string
*/
function reverse(str) {
	var result = "";
	for (var i = str.length - 1; i > -1; i--) {
		result += str.substring(i, i + 1);
	}
	return result;
}


