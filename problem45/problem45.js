/* Problem 45
 * binarySearch: returns the index of a key in an array. Returns -1 if the key is not in the array
*/
var hex = [];
var n = 50000001;
for (var i = 0; i < n; i++) {
	hex.push(i * (2 * i - 1));
}
var l = 0; var k = 0; var i = 166;
while (true) {
	k = i * (3 * i - 1) / 2;
	if (binarySearch(hex, k, 0, hex.length - 1) > -1) break;
	i++;
}
console.log("The solution is " + k); // every hexagonal number is a triangle number

function binarySearch(T, k, min, max) {
	while (max >= min) {
		var midpoint = Math.floor((max + min) / 2);
		if (T[midpoint] === k) return midpoint;
		else if (T[midpoint] < k) min = midpoint + 1;
		else max = midpoint - 1;
	}
	return -1;
}