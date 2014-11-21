/**
 * Problem 53
 */

var Combinatorics = require("../libraries/Combinatorics.js");
var limit = 1000000; var sum = 0;
for (var n = 1; n <= 100; n++) {
	sum += nbExceedingVal(n, limit);
}
console.log("The solution is " + sum);

/**
* Returns the number of values of k, k in [0, ..., n], such that C^k_n > l
* @param integer n, integer l
* @return integer
*/
function nbExceedingVal(n, l) {
	var k = 0; var m = n + 1;
	while (k <= Math.floor(n / 2)) {
		if (Combinatorics.nChooseK(k, n) > l) break;
		k++;
	}
	return Math.max(m - 2 * k, 0);
}