/* Problem 30
 * sumOfPowersOfDigits: returns the sum described e.g. sumOfPowersOfDigits(123, 2) returns 1^2 + 2^2 + 3^2
*/

var map = {}; // map will map the set {"0", ..., "9"} to {0, ..., 9}
for (var i = 0; i < 10; i++) {
	map[i.toString()] = i;
}

var sum = 0;
for (var i = 2; i < 1000000; i++) {
	if (sumOfPowersOfDigits(i, 5) == i) sum += i;
}
console.log("The solution is " + sum);

function sumOfPowersOfDigits(n, exp) {
	var sum = 0; 
	var nAsStr = n.toString();
	for (var i = 0; i < nAsStr.length; i++) {
		sum += Math.pow(map[nAsStr[i]], exp);
	}
	return sum;
}




 



