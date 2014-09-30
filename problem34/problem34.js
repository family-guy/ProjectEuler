/* Problem 34
 * convertIntToArray: returns a given integer as a dynamic array e.g. convertIntToArray(123) = [1, 2, 3]
 * fact: returns the factorial of a number
 * sumFact: takes a dynamic array of positive integers and returns the sum of their factorials
*/

var maxIndex = 10000000; // numbers with 8 or more digits cannot be equal to the sum of the factorial of their digits
var sum = 0;
for (var i = 10; i < maxIndex; i++) {
	if (i == sumFact(convertIntToArray(i))) sum += i;
}
console.log("The solution is " + sum);

function convertIntToArray(n) {
	var result = []; var divisor = 1; var dividend = n;
	while (true) {
		if (divisor * 10 > n) break;
		divisor *= 10; 
	}
	while (divisor > 0) {
		result.push(Math.floor(dividend / divisor));
		dividend %= divisor;
		divisor = Math.floor(divisor / 10);
	}
	return result;
}

function fact(n) {
	if (n == 0) return 1;
	var result = 1;
	for (var i = 1; i <= n; i++) {
		result *= i;
	}
	return result;
}

function sumFact(T) {
	var sum = 0;
	for (var i = 0; i < T.length; i++) {
		sum += fact(T[i]);
	}
	return sum;
}