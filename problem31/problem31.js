/* Problem 31
 * nbOfWays: t is the target, c is the array of available coin values, n is the length of c
*/

var coinValues = [1, 2, 5, 10, 20, 50, 100, 200]; var target = 200;
console.log("The solution is " + nbOfWays(target, coinValues));

function nbOfWays(t, c) {
	if (c.length == 1) {
		if (t % c[0] == 0 && t / c[0] > 0) return 1;
		else return 0;
	}
	var sum = 0; var new_c = [];
	for (var i = 0; i < c.length - 1; i++) {
		new_c[i] = c[i + 1];
	}
	if (t % c[0] == 0 && t / c[0] > 0) sum++;
	while (t > 0) {
		sum += nbOfWays(t, new_c);
		t -= c[0];
	}
	return sum; 
}