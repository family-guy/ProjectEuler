/* Problem 38
 * concatenatedProd: takes a positive integer and returns its concatenated product as defined in the question
 * isOneToNinePand: takes a number represented as a string and returns whether that number is a permutation of {1, ..., 9}
 * maxPand: given two positive integers a and b, maxPand returns the largest 1 to 9 pandigital 9-digit number that can be formed as the concatenated product of an integer p with (1,2, ... , n) where n > 1 and a <= p <= b. Returns zero if no pandigital exists
*/

// the largest 1 to 9 pandigital 9-digit number must begin with 9. Thus the integer we are looking for begins with 9. Further, as n > 1, it cannot be more than 4 digits long
var startingMax = 918273645; // we know this is the result of concatenatedProd(9)
// case of 2 digits. We go through 91 to 98
var x = maxPand(91, 98); 
// case of 3 digits. We go through 912 to 987
var y = maxPand(912, 987);
// case of 4 digits. We go through 9123 to 9876
var z = maxPand(9123, 9876);
console.log("The solution is " + Math.max(Math.max(Math.max(startingMax, x), y), z));

function concatenatedProd(p) {
	var i = 1; var pAsStr = p.toString();
	while (true) {
		if (pAsStr.length >= 9) break;
		i++;
		var product = (p * i).toString();
		pAsStr += product;
	}
	return pAsStr;
}

function isOneToNinePand(n) {
	if (n.length != 9 || n.indexOf('0') > -1) return false;
	for (var i = 1; i < 10; i++) {
		var iAsStr = i.toString();
		if (n.indexOf(iAsStr) != n.lastIndexOf(iAsStr)) return false;
	}
	return true;
}

function maxPand(a, b) {
	var max = 0;
	for (var i = a; i < b + 1; i++) {
		var cp = concatenatedProd(i);
		if (cp.length == 9) {
			var cpAsInt = parseInt(cp);
			if (isOneToNinePand(cp) && cpAsInt > max) max = cpAsInt;
		}
	}
	return max;
}
